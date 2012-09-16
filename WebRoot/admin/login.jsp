<%@include file="../common/admin_head.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
	<title><fmt:message key="admin_title"/></title>
	<link href="../css/admin.css" rel="stylesheet" type="text/css" />
	<link href="../css/dialog.css" rel="stylesheet" media="screen" type="text/css" /> 
	<script src="../js/dialog2.js" type="text/javascript" ></script>
	<script src="../js/jquery-1.6.2.min.js" type="text/javascript" ></script>
	<script src="../js/admin/login.js" type="text/javascript" ></script>
</head>
<body>
<br/><br/><br/>

<form action="login.do" name="loginform" method="post">
	<c:if test="${error!=null}">
		<input id="error" value=" <fmt:message key="${error}" />" type="hidden">
	</c:if>
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
			<td><input name="loginPwd" size="26" type="password"></td>
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
				<input type="submit" name="<fmt:message key="login_submit"/>">
				&nbsp;
				<input type="reset" name="<fmt:message key="label_reset"/>">
			</td>
		</tr>
		<tr height="50">
			<td colspan="5" align="center"></td>
		</tr> 
	</table>
</form>
<s:if test="hasFieldErrors()">
	<e:msgdialog basepath="${ctx}">
		<s:fielderror/>
	</e:msgdialog>
</s:if>
<s:if test="hasActionErrors()">
	<e:msgdialog basepath="${ctx}">
		<s:actionerror/>
	</e:msgdialog>
</s:if>
</body>
</html>
