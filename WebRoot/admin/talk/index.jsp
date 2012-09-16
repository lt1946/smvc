<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Object userid="";
if(session.getAttribute("isReg")==null){
	session.setAttribute("isReg","true");
}else{
	session.setAttribute("isReg","false");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="talk">
	<meta http-equiv="description" content="talk">
	<script type='text/javascript' src='<%=path%>/js/jquery-1.6.2.min.js'></script>
	<script type='text/javascript' src='<%=path%>/push/interface/pushinfo.js'></script>
	<script type='text/javascript' src='<%=path%>/push/engine.js'></script>
	<script type='text/javascript' src='<%=path%>/push/util.js'></script>
	<script type="text/javascript">
		$jq=jQuery.noConflict();
		$jq(function(){
			dwr.engine.setActiveReverseAjax(true);
			if(${isReg})pushinfo.setUserId('${admin.loginName}');
			//alert(document.getElementById("userid").value)
		});
	</script>
  </head>
  <body>
	<input type="hidden" id="userid" value="${admin.loginName}">
	<input type="hidden" id="isReg" value="${isReg}">

  </body>
</html>
