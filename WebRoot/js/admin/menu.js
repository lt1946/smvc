$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
});	
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	}
};
zNodes =[
	{ id:1, pId:0, name:"Portal后台管理", open:true},
	{ id:21, pId:1, name:"聊天室管理", open:true,icon:"../images/icon_admin.gif"},
	{ id:211, pId:21, name:"聊天室",url:"talk/index.jsp",target:"mainFrame", icon:"../images/icon_crawl.gif"},
	{ id:31, pId:1, name:"新闻管理", open:true,icon:"../images/icon_newscolumn.gif"},
	{ id:311, pId:31, name:"新闻栏目管理",url:"../gb/goodsview.html",target:"mainFrame", icon:"../images/icon_column.gif"},
	{ id:312, pId:31, name:"新闻管理",url:"../gb/sitesview.html", target:"mainFrame", icon:"../images/icon_news.gif"},
	{ id:32, pId:1, name:"新闻采集", url:"news/newsruleview.jsp", icon:"../images/icon_crawl.gif",target:"mainFrame"},
	{ id:33, pId:1, name:"会员管理", open:true, icon:"../images/icon_member.gif"},
	{ id:331, pId:33, name:"会员级别管理",url:"../gb/api.html", target:"mainFrame", icon:"../images/icon_level.gif"},
	{ id:332, pId:33, name:"会员管理",url:"../gb/api.html", target:"mainFrame", icon:"../images/icon_member.gif"},
	{ id:4, pId:1, name:"商品管理", open:true, icon:"../images/icon_catemer.gif"},
	{ id:41, pId:4, name:"商品分类",  open:true, icon:"../images/icon_cate.gif"},
	{ id:411, pId:41, name:"商品管理",url:"../spider/gurview.html", target:"mainFrame", icon:"../images/icon_mer.gif"},
	{ id:42, pId:4, name:"订单管理", open:true, icon:"../images/icon_order.gif"},
	{ id:5, pId:1, name:"流量统计", open:true, icon:"../images/icon_trafic.gif"},
	{ id:51, pId:5, name:"IP统计",url:"../job/index.html", target:"mainFrame", icon:"../images/icon_ip.gif"},
	{ id:52, pId:5, name:"PV统计",url:"../job/viewjoblog.html", target:"mainFrame", icon:"../images/icon_pv.gif"},
	{ id:6, pId:1, name:"系统用户管理", open:true, icon:"../images/icon_admin.gif",target:"mainFrame"},
	{ id:7, pId:1, name:"安全退出", open:true, icon:"../images/icon_exit.gif"}
];

