<%@ page contentType="text/html; charset=gbk"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/struts"%>
 <fmt:setLocale value="zh_CN"/> 
 <!-- 
 <fmt:setBundle basename="message" var="msg"/>  
 <fmt:message key="admin_title" var="title" bundle="${msg}"></fmt:message>
  -->
<%
	request.setAttribute("ctx",request.getContextPath());	
%>
