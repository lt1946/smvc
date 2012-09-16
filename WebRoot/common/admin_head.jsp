<%@ page contentType="text/html; charset=gbk"%>
<%@ taglib prefix="e" uri="/eportal"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/struts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <fmt:setLocale value="zh_CN"/> 
 <!-- 
 <fmt:setBundle basename="message" var="msg"/>  
 <fmt:message key="admin_title" var="title" bundle="${msg}"></fmt:message>
  -->
<%
	request.setAttribute("ctx",request.getContextPath());	
%>
<script type="text/javascript" src="../js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="../js/jquery/activebar-2.0.1/activebar2.js"></script>
<script type="text/javascript">
    jQuery.noConflict();
	/*
	jQuery(document).ready(function($) {
           $('<div></div>').html('This page may not be displayed correctly in this browser. You are strongly encouraged to update to a current release of <a href="http://mozilla.org/firefox">Firefox</a>')
                           .activebar({
                               'url': 'http://mozilla.org/firefox'
                           });
       });
       */
</script>