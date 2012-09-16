<%@ page contentType="text/html; charset=gbk"%>
<%@ page import="java.util.List"  %>
<%@ page import="com.iatb.pojo.Newscolumns"  %>
<%@include file="../../common/admin_head.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title><fmt:message key="admin_title"/></title>
<link href="<%=path%>/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<center>
	j<br/><div class="titleText"><fmt:message key="rule_add"/></div>
	<div class="formDiv">
	  <s:form action="rule_addNewsrule">	
		<table width="700" align="center" border="0" cellpadding="0" cellspacing="0">
		  <tr>
		    <td align="right"><fmt:message key="rule_newscolumns"/>：</td>
		    <td> 		
			    <select  name="newscolumnsid" >
			    <%List<Newscolumns> list=(List<Newscolumns>)request.getAttribute("newscolumnsList");
				   if(list!=null){
					    for(Newscolumns nc:list){
					    	out.print("<option value=\""+nc.getID()+"\">"+nc.getColumnName()+"</option>");
					    }
					}else{
					    	out.print("<option value=\"\"></option>");
				    }
			     %>	    
			    </select>
			</td>
		  </tr>		
		  <tr>
		    <td align="right"><fmt:message key="rule_rulename"/>：</td>
		    <td>
		    <textarea name="ruleName" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_url"/>：</td>
		    <td><textarea name="url" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_encode"/>：</td>
		    <td><textarea name="encode" rows="2" cols="80"></textarea></td>		    	    
		  </tr>		  
		  <tr>		    
		    <td align="right"><fmt:message key="rule_listbegin"/>：</td>
		    <td><textarea name="listBegin" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_listend"/>：</td>
		    <td><textarea name="listEnd" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_titlebegin"/>：</td>
		    <td><textarea name="titleBegin" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>		    	    
		  </tr>	
		  <tr>		    
		    <td align="right"><fmt:message key="rule_titleend"/>：</td>
		    <td><textarea name="titleEnd" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_urllike"/>：</td>
		    <td><textarea name="urlLike" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		   <tr>           
            <td align="right"><fmt:message key="rule_unurllike"/>：</td>
            <td><textarea name="unUrlLike" rows="2" cols="80"></textarea></td>                    
          </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_untitlelike"/>：</td>
		    <td><textarea name="unTitleLike" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_authorbegin"/>：</td>
		    <td><textarea name="authorBegin" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_authorend"/>：</td>
		    <td><textarea name="authorEnd" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_frombegin"/>：</td>
		    <td><textarea name="fromBegin" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_fromend"/>：</td>
		    <td><textarea name="fromEnd" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_contentbegin"/>：</td>
		    <td><textarea name="contentBegin" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>		    	    
		  </tr>	
		  <tr>		    
		    <td align="right"><fmt:message key="rule_contentend"/>：</td>
		    <td><textarea name="contentEnd" rows="2" cols="80"></textarea>&nbsp;<span class="redText">*</span></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_contentpageurl"/>：</td>
		    <td><textarea name="contentPageUrl" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_midbegin"/>：</td>
		    <td><textarea name="midBegin" rows="2" cols="80"></textarea></td>		    	    
		  </tr>
		  <tr>		    
		    <td align="right"><fmt:message key="rule_midend"/>：</td>
		    <td><textarea name="midEnd" rows="2" cols="80"></textarea></td>		    	    
		  </tr>		  
		   <tr>           
            <td align="right"><fmt:message key="rule_isDownPhoto"/>：</td>
            <td>是<input type="radio" value="1" name="isDownPhoto" >否<input type="radio" value="0" name="isDownPhoto" checked="checked" > <br><br></td>                    
          </tr>		  			  	  		  		  		  		  
		</table>
		<br>
		  <div align="center">
			<s:submit key="label_submit"/>&nbsp;
			<s:reset key="label_reset"/>&nbsp;
			<s:set name="label_return" value="%{getText('label_return')}"/>
		    <input type="button" name="btn_ret" value="${label_return}" onClick="window.location='rule_browseNewsrule.action';">
		  </div>
		</s:form>	
	</div>
</center>
</body>
</html>
