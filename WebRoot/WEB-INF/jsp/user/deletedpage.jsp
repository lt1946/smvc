
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>Insert title here</title>
</head>
<body>

<h1>Persons</h1>

<p>你于
<%= new java.util.Date() %>
根据${id }删除了一个Person
</p>

</body>
</html>