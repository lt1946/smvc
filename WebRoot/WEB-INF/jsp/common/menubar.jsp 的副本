<%@ page contentType="text/html; charset=gbk"%>
<!--Logo区-->
<div class="logo_left">
	<img src="<%=basepath%>/images/logo.gif" />
</div>
<div class="logo_right">
	<img src="<%=basepath%>/images/ad1.gif" />
</div>
<!-- 栏间留空 -->	
<div class="split"></div>	
<!--导航菜单区-->
<div class="menu">
	<ul>	
		<li><a target="_self" href="<%=basepath%>/index.jsp">首&nbsp;&nbsp;页</a></li>
		<li><a target="_self" href="<%=basepath%>/webs/newmer.jsp">新品上架</a></li>
		<li><a target="_self" href="<%=basepath%>/webs/prommer.jsp">促销商品</a></li>
		<li><a target="_self" href="<%=basepath%>/webs/video.jsp">视频购物</a></li>
		<li><a target="_self" href="<%=basepath%>/webs/viewCart.action">购物车管理</a></li>	
		<li><a target="_self" href="<%=basepath%>/webs/loadAllOrders.action">订单管理</a></li>
		<li><a target="_self" href="<%=basepath%>/webs/news.jsp">商城快讯</a></li>
		<li><a target="_self" href="<%=basepath%>/webs/search.jsp">商情搜索</a></li>	
		<li><a target="_self" href="<%=basepath%>/html/bbs.html">商城论坛</a></li>
		<li><a target="_self" href="<%=basepath%>/html/blog.html">商家博客</a></li>
		<li><span onclick="editMember()">修改注册资料</span></li>			
	</ul>	
</div>
<script language='javascript'>
	//修改注册资料
	function editMember(){
		<s:if test="#session.member==null">
			alert('对不起,您尚未登录,请登录后再修改注册资料,谢谢合作!');
		</s:if>
		<s:else>
			window.location='<%=basepath%>/webs/editReg.jsp?tmp='+(new Date()).getTime();
		</s:else>		
	}
	
	//响应"搜索"按钮
	function search(){
		var p1 = document.getElementById("searchtype").value;
		var p2 = document.getElementById("keyword").value;
		if (p2==''){
			alert('对不起,请先输入关键字然后再搜索,谢谢合作!');
		}else{
			if (p1=='newmer' || p1=='prommer'){//搜索所有商品
				window.location='<%=basepath%>/webs/searchMer.jsp?searchtype='+p1+'&keyword='+escape(escape(p2));
			}else if (p1=='news'){//搜索商城资讯
				window.location='<%=basepath%>/webs/searchNews.jsp?keyword='+escape(escape(p2));
			}else if (p1=='info'){//商情检索
			
			}			
		}
	}
</script>		
<!--搜索与注册登录区-->	
<div class="search">
	<div class="search_left"></div>
	<div class="search_main">
<!--注册登录区-->			
		<div class="login">
			<div class="login_left"></div>
			<div class="login_main">			 	
				<s:if test="#session.member==null">
					<!-- 尚未登录 -->	
					<script language='javascript'>
						//表单验证
						function checkForm(){
							if (document.getElementById('loginName').value==''){
								alert('对不起,登录用户名不能为空!');
							}else if (document.getElementById('loginPwd').value==''){
								alert('对不起,登录密码不能为空!');
							}else{
								//提交表单
								loginform.submit();
							}
						}
					</script>			
					<s:form action="/webs/loginMember.action" id="loginform" target="_self">
						用户名：<s:textfield name="loginName" id="loginName" size="16"/>
						密码：<s:password name="loginPwd" id="loginPwd" size="16"/>
						<input type="image" src="<%=basepath%>/images/login.jpg" onclick="checkForm(); return false;"/>
						&nbsp;&nbsp;<a href="<%=basepath%>/webs/reg.jsp" target="_self">注册新会员</a>
					</s:form>					
				</s:if>				
				<s:else>
					<!-- 已登录 -->
					<span class="actionMessage">
						<s:property value="#session.member.memberlevel.levelName"/><s:property value="#session.member.loginName"/>,您好!
						 欢迎光临ePortal商城! 您目前的积分为<s:property value="#session.member.integral"/>分! 
					</span>
					<a href="<%=basepath%>/webs/logoutMember.action" target="_self"><span class="blueText">安全退出</span></a>
				</s:else>
			</div>
			<div class="login_right"></div>				
		</div>
<!--搜索区-->
		<div class="sou">
			<div class="sou_left"></div>
			<div class="sou_main">
				类型：<select id="searchtype" name="searchtype">
				<s:if test="#session.searchtype!=null && #session.searchtype.equals('newmer')">
					<option value="newmer" selected="true">所有商品</option>
				</s:if>
				<s:else>
					<option value="newmer" selected="true">所有商品</option>
				</s:else>
				<s:if test="#session.searchtype!=null && #session.searchtype.equals('prommer')">
					<option value="prommer" selected="true">促销商品</option>
				</s:if>
				<s:else>
					<option value="prommer">促销商品</option>
				</s:else>				
				<s:if test="#session.searchtype!=null && #session.searchtype.equals('news')">
					<option value="news" selected="true">商城资讯</option>
				</s:if>
				<s:else>
					<option value="news">商城资讯</option>
				</s:else>					
					<option value="info">商情检索</option>												
				</select>
				&nbsp;关键字：<input type="text" id="keyword" name="keyword" size=28 value="${keyword}"/>
			</div>
			<div class="sou_right">
				<input type="image" src="<%=basepath%>/images/search_right.jpg" name="submit" onclick="search()"/>
			</div>					
		</div>						
	</div>
	<div class="search_right"></div>
</div>