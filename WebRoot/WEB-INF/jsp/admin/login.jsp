<%@ page contentType="text/html; charset=gbk"%>
<%@ taglib prefix="e" uri="/eportal"%> 
<%@include file="../common/admin_head.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<title><fmt:message key="admin_title"/></title>
	<!--
	<title><s:text  name="admin_title"/></title>
	  -->
	<link href="../css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<br/><br/><br/>
<form action="" name="loginform" method="post">
	<table border="0" cellpadding="0" cellspacing="0" align="center" width="476" height="298" background="../images/login_back.jpg">
		<tr height="110">
			<td colspan="5"></td>
		</tr>
		<tr>
			<td width="60"></td>
			<td class="labeltext" width="100"><fmt:message key="login_label_loginname"></fmt:message>:</td>
			<td><input name="loginName" size="26"> </td>
			<td></td>
			<td width="60"></td>
		</tr>
		<tr>
			<td width="60"></td>
			<td class="labeltext" width="100"><fmt:message key="login_label_password"/>:</td>
			<td><input name="loginPwd" size="26"></td>
			<td></td>
			<td width="60"></td>
		</tr>
		<tr>
			<td width="60"></td>
			<td class="labeltext" width="100"><fmt:message key="login_label_rand"/>:</td>
			<td><input name="rand" size="26">&nbsp;<img src="../common/rand.jsp" height="16" border="1"/></td>
			<td></td>
			<td width="60"></td>
		</tr>
		<tr>
			<td colspan="5" align="center">
				<s:submit key="login_submit"/>&nbsp;
				<s:reset key="label_reset"/>
			</td>
		</tr>
		<tr height="50">
			<td colspan="5" align="center"></td>
		</tr> 
	</table>
</form>
</body>
</html>
