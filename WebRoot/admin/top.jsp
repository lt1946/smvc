<%@ page contentType="text/html; charset=gbk"%>
<%@ page import="java.util.Date"  %>
<%@include file="../common/admin_head.jsp"%>
<%request.setAttribute("date",new Date()); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title><fmt:message key="admin_title"/></title>
<link href="../css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-image:url(../images/top_back.jpg); background-repeat:repeat-x;">
<div class="admin_logo">
	<img src="../images/admin_logo.jpg"/>
</div>
<div class="admin_prompt">
	<s:bean name="java.util.Date" id="now"/>
	<fmt:message key="admin_welcome">
		<fmt:param>${admin.loginName}</fmt:param>
	</fmt:message>
	<br/>
	<fmt:message key="admin_datetime">
		<fmt:param>
			<fmt:formatDate value="${date}" pattern="yyyy年MM月dd日 HH:mm:ss E" />
		</fmt:param>
	</fmt:message>
</div>
</body>
</html>
