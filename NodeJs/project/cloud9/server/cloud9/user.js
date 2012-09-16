var sys = require("sys");
var util = require("./util");
var EventEmitter = require("events").EventEmitter;

var User = function (uid, permissions, data) {
    EventEmitter.call(this);
    
    // TODO deprecated
    Object.defineProperty(this, "name",
       {get: function() { console.log("DEPRECATED: name use uid"); console.trace(); return uid; }}
    );
    
    this.uid = uid;
    this.permissions = permissions;
    this.data = data;
    this.clients = [];
    this.last_message_time = new Date().getTime();
    this.$server_exclude = {};
};

sys.inherits(User, EventEmitter);

User.OWNER_PERMISSIONS = {
    client_exclude: "",
    server_exclude: "",
    dav: "rw"
};

User.COLLABORATOR_PERMISSIONS = {
    client_exclude: "",
    server_exclude: "git",
    dav: "rw"
};

User.VISITOR_PERMISSIONS = {
    client_exclude: [
        "ext/save/save",
        "ext/newresource/newresource",
        "ext/undo/undo",
        "ext/searchreplace/searchreplace",
        "ext/quickwatch/quickwatch",
        "ext/extmgr/extmgr",
        "ext/run/run", //Add location rule
        "ext/debugger/debugger", //Add location rule
        "ext/noderunner/noderunner", //Add location rule
        "ext/watcher/watcher",
        "c9/ext/projectinfo/projectinfo",
        
        "ext/tabbehaviors/tabbehaviors"
    ].join("|"),
    server_exclude: [
        "git",
        "debugger",
        "shell",
        "runvm"
    ].join("|"),
    dav: "ro"
};

(function() {
    
    this.setPermissions = function(permissions) {
        this.$server_exclude = util.arrayToMap(permissions.server_exclude.split("|"));
        if (this.permissions === permissions)
            return;
        
        this.permissions = permissions;
        this.emit("changePermissions", this);
    };
    
    this.getPermissions = function(permissions) {
        return this.permissions;
    };
    
    this.addClientConnection = function(client, message) {
        if (this.clients.indexOf(client) !== -1)
            return;
            
        this.clients.push(client);
        this.onClientCountChange();
        
        var _self = this;
        client.on("message", function(message) {
            _self.onClientMessage(message, client);
        });
        
        client.on("disconnect", function() {
            _self.emit("disconnectClient", {
                user: _self,
                client: client
            });
            var idx = _self.clients.indexOf(client);
            if (idx !== -1)
                _self.clients.splice(idx, 1);
            _self.onClientCountChange();
        });
        
        if (message)
            _self.onClientMessage(message, client);
    };
    
    this.disconnectClients = function() {
        console.log("disconnecting all connected clients");
        for (var i = this.clients.length - 1; i >= 0; --i)
            this.clients[i].disconnect();
    };
    
    this.onClientMessage = function(message, client) {
        try {
            if (typeof message == "string")
                message = JSON.parse(message);
        }
        catch (e) {
            return this.error("Error parsing message: " + e + "\nmessage: " + message, 8);
        }

        this.emit("message", {
            message: message,
            user: this,
            client: client
        });
    };
    
    this.onClientCountChange = function() {
        var count = this.clients.length;
        this.emit("clientCountChange", count);
        
        if (count === 0) {
            this.dconn_time = new Date().getTime();
            this.emit("disconnectUser", this);
        }
    };
    
    this.error = function(description, code, message, client) {
        //console.log("Socket error: " + description, new Error().stack);
        var sid = (message || {}).sid || -1;
        var error = {
            "type": "error",
            "sid": sid,
            "code": code,
            "message": description
        };

        // pass a lambda to enable socket.io ACK
        if (client)
            client.send(JSON.stringify(error), function() {});
        else
            this.broadcast(error);
    };
    
    this.broadcast = function(msg, scope) {
        if (scope && this.$server_exclude[scope])
            return;

        for (var i = 0, l = this.clients.length; i < l; ++i)
            this.clients[i].send(msg);
    };
    
}).call(User.prototype);

module.exports = User;
