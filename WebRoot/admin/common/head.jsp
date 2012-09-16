<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="talk">
<meta http-equiv="description" content="talk">
<link rel="stylesheet"	type="text/css" href="<%=path%>/css/admin.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/icon.css">
<script type='text/javascript' src='<%=path%>/js/jquery/jquery-easyui-1.2.5/jquery-1.7.1.min.js'></script>
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery/wbox/wbox.js"></script>
<script type="text/javascript" src="<%=path%>/js/json2.js"></script>
<input id="ctx" value="<%=path %>"  type="hidden">
<style type="text/css">
#main {
	width:100%;
	text-align: center;
	margin-left:auto;
	margin-right:auto;
	white-space: nowrap;
	text-overflow: ellipsis
}
</style>
