<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>项目管理系统--项目列表</title>
<base href="<%=basePath%>">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="styles/bootstrap.css" rel="stylesheet">
<link href="styles/backstage.css" rel="stylesheet">
<script src="js/jquery.js"></script>
<script src="js/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div class="container container-own">
		<jsp:include page="common/header.jsp"></jsp:include>
		<jsp:include page="common/sideBar.jsp"></jsp:include>
		<h2>
			<i>Project List</i><span id="showTime2" class="label pull-right"></span><span
				id="showTime" class="label label-primary pull-right"></span>
		</h2>
		<div class="line-spacing"></div>

		<!-- 用于放置项目列表 -->
		<div id="proList"></div>
		<script>
			$.ajax({
				url : "projectController/getProjects",
				data : "currentPageNo=1",
				success : function(data) {
					$("#proList").html(data);
				}
			});
		</script>

		<jsp:include page="common/loginRegisterModal.jsp"></jsp:include>
	</div>
</body>
</html>
