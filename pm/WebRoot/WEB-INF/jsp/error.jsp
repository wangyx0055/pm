<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>错误页面</title>
<style type="text/css">
	*{
		font-family:"时尚中黑简体";
		margin:0;
		padding:0;
	}
	html body{
		width:100%;
		height:100%;
	}
	body{
		background-image:url("http://localhost:8089/pm/images/278353.jpg");
	}
	div{
		color:#416ABC;
		font-size:36px;
		width:60%;
		position:absolute;
		left:50%;
		top:60px;
		margin-left:-25%;
		height:500px;
	}
	label{
		color:#F78302;
		font-size: 60px;
	}
	h1{
		color:#333333;
	}
	p{
		font-family:Ebrima;
	}
</style>
</head>

<body>
	<div><%=request.getAttribute("ex") %></div>
</body>
</html>
