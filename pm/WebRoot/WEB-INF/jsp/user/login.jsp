<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="styles/bootstrap.css" rel="stylesheet">
<!-- <link href="styles/bootstrap-theme-sandstone.css" rel="stylesheet"> -->
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
</head>

<body style="font-family: 微软雅黑;">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				
				<!-- <a class="navbar-brand" href="#"><span style="font-family: 时尚中黑简体; color: blue;">ICKER PM</span></a> -->
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<a class="navbar-brand" href="#"><span style="font-family: 时尚中黑简体; color: blue;">ICKER PM</span></a>
				<!-- <form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form> -->
				<ul class="nav navbar-nav navbar-right">
					<li><a href="javascript:void(0)">登陆</a></li>
					<li><a href="javascript:void(0)">注册</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="container">
		<form class="form-signin" action="userController/login" method="post">
			<h2 class="form-signin-heading">请登陆</h2>
			<label for="email" class="sr-only">Email</label> <input type="email"
				id="email" name="email" class="form-control" placeholder="Email"
				required autofocus> <label for="password" class="sr-only">Password</label>
			<input type="password" id="password" name="password"
				class="form-control" placeholder="Password" required>
			<div class="checkbox">
				<label> <input type="checkbox" value="remember-me">
					记住我
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">登
				陆</button>
		</form>

	</div>
	<!-- /container -->

</body>
</html>
