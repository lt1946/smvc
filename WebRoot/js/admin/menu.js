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
	{ id:1, pId:0, name:"Portal��̨����", open:true},
	{ id:21, pId:1, name:"�����ҹ���", open:true,icon:"../images/icon_admin.gif"},
	{ id:211, pId:21, name:"������",url:"talk/index.jsp",target:"mainFrame", icon:"../images/icon_crawl.gif"},
	{ id:31, pId:1, name:"���Ź���", open:true,icon:"../images/icon_newscolumn.gif"},
	{ id:311, pId:31, name:"������Ŀ����",url:"../gb/goodsview.html",target:"mainFrame", icon:"../images/icon_column.gif"},
	{ id:312, pId:31, name:"���Ź���",url:"../gb/sitesview.html", target:"mainFrame", icon:"../images/icon_news.gif"},
	{ id:32, pId:1, name:"���Ųɼ�", url:"news/newsruleview.jsp", icon:"../images/icon_crawl.gif",target:"mainFrame"},
	{ id:33, pId:1, name:"��Ա����", open:true, icon:"../images/icon_member.gif"},
	{ id:331, pId:33, name:"��Ա�������",url:"../gb/api.html", target:"mainFrame", icon:"../images/icon_level.gif"},
	{ id:332, pId:33, name:"��Ա����",url:"../gb/api.html", target:"mainFrame", icon:"../images/icon_member.gif"},
	{ id:4, pId:1, name:"��Ʒ����", open:true, icon:"../images/icon_catemer.gif"},
	{ id:41, pId:4, name:"��Ʒ����",  open:true, icon:"../images/icon_cate.gif"},
	{ id:411, pId:41, name:"��Ʒ����",url:"../spider/gurview.html", target:"mainFrame", icon:"../images/icon_mer.gif"},
	{ id:42, pId:4, name:"��������", open:true, icon:"../images/icon_order.gif"},
	{ id:5, pId:1, name:"����ͳ��", open:true, icon:"../images/icon_trafic.gif"},
	{ id:51, pId:5, name:"IPͳ��",url:"../job/index.html", target:"mainFrame", icon:"../images/icon_ip.gif"},
	{ id:52, pId:5, name:"PVͳ��",url:"../job/viewjoblog.html", target:"mainFrame", icon:"../images/icon_pv.gif"},
	{ id:6, pId:1, name:"ϵͳ�û�����", open:true, icon:"../images/icon_admin.gif",target:"mainFrame"},
	{ id:7, pId:1, name:"��ȫ�˳�", open:true, icon:"../images/icon_exit.gif"}
];

