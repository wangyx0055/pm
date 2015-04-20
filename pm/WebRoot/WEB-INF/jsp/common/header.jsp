<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.icker.pm.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User)request.getSession().getAttribute("user");
%>
<!-- 导航栏 -->
<nav class="navbar navbar-default " role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

		</div>
		<div id="navbar" class="navbar-collapse collapse">

			<div class="dropdown nav navbar-nav navbar-right">
				<c:if test="${user != null}">
					<button id="dLabel" type="button" class="btn btn-link"
						style="height:50px;" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">
						<%=user.getName() %><span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
						<li><a id="signOut" href="user/ExitServlet">安全退出</a></li>
					</ul>
				</c:if>
				<c:if test="${user == null}">
					<button id="login" type="button" class="btn btn-link"
						style="height:50px;" data-toggle="modal" data-target="#loginModal">
						登陆</button>
					<button id="register" type="button" class="btn btn-link"
						style="height:50px;" data-toggle="modal"
						data-target="#registerModal">注册</button>
				</c:if>
			</div>
			<c:if test="${user != null}">
				<a href="javascript:void(0)"> <span class="pull-right"><img
						class="img-circle" src="images/deng.jpg" style="margin-top:8px;"
						width="30px" height="30px"></span>
				</a>
			</c:if>
			<form class="navbar-form navbar-left" method="post" action="">
				<div class="input-group input-group-sm" style="margin-left:-20px">
					<input id="info" type="text" class="form-control"
						placeholder="Search..."> <span class="input-group-btn">
						<button type="submit" name="submit" class="btn btn-success">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			</form>
		</div>
	</div>
</nav>
<!-- 导航栏结束 -->



<script type="text/javascript">
	$(document).ready(function(e) {
		//一秒刷新一次显示时间
		showTime();
		var timeid = window.setInterval(showTime, 1000);
	});
	function showTime() {
		//获得当前时间,刻度为一千分一秒
		var initializationTime = (new Date()).getTime();
		var now = new Date();
		var year = now.getUTCFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var hours = now.getHours();
		var minutes = now.getMinutes();
		var seconds = now.getSeconds();

		$("#showTime").html(
				"<small style='color:#f2f2f2'>" + month + "月" + day
						+ "日</small>");
		$("#showTime2").html(
				"<small style='color:#428bca'>" + hours + ":" + minutes + ":"
						+ seconds + "</small>");
	}
</script>