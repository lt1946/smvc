<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'w.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <link rel="stylesheet"	type="text/css" href="<%=path%>/css/admin.css"/>
	<link rel="stylesheet" type="text/css"	href="<%=path%>/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css"	href="<%=path%>/css/themes/icon.css">
	<script type='text/javascript' src='<%=path%>/js/jquery/jquery-easyui-1.2.5/jquery-1.7.1.min.js'></script>
	<script type="text/javascript"	src="<%=path%>/js/jquery/jquery-easyui-1.2.5/jquery.easyui.min.js"></script>
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <script type="text/javascript">
    	$(function(){
	    	$(".easyui-window").window('close');
    	});
    </script>
  </head>
  <body>
  <center>
			  <input type="button" value="显示" onclick="$('.easyui-window').window('open');" >
	  <div id='ww'  >
	    <div id="w" class="easyui-window" title="My Window"  iconCls="icon-save" style="width:620px;height:220px;padding:5px;">
				到时候会
		</div>
	  </div>
  </center>