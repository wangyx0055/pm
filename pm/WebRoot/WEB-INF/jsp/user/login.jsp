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
<link href="styles/login.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/strapdown.js"></script>
<script type="text/javascript" src="js/bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div class="container">
		<form class="form-signin" action="userController/login" method="post">
			<h2 class="form-signin-heading">请登陆</h2>
			<label for="email" class="sr-only">Email</label> <input
				type="email" id="email" name="email" class="form-control"
				placeholder="Email" required autofocus> <label
				for="password" class="sr-only">Password</label> <input
				type="password" id="password" name="password" class="form-control"
				placeholder="Password" required>
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
