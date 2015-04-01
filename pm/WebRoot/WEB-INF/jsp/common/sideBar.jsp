<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
%>
<!-- 左侧列表栏 -->
<div class="listbar">
	<!-- 大标题 -->
	<div class="listbar-logo">
		<em><label class=" text-primary">PM</label></em>
	</div>

	<div class="listbar-list">
		<!-- 头像 -->
		<div class="listbar-list-head">
			<div style="width:100px; height:100px; float:left;">
				<a href="javascript:void(0)"> <img
					class="img-circle listbar-img" width="60px" height="60px"
					src="images/deng.jpg">
				</a>
			</div>
			<div style="padding-top:30px;">
				欢迎光临<br /> <label class="text-primary" style="margin-left:20px;"><a
					href="javascript:void(0)"><%=user.getName()%>.</a></label><br /> 状态：<label
					class="text-primary"> <c:if test="${user == null }">
						离线
					</c:if> <c:if test="${user != null }">
						在线
					</c:if>
				</label>
			</div>
		</div>

		<!-- 功能列表组 -->
		<div class="list-group listbar-listgroup">
			<a class="list-group-item" href="userController/home">
				<span class="glyphicon glyphicon-home" style="margin-left:10px; margin-right:30px;"></span>
				登陆首页
			</a>
			<a id='projectList' class="list-group-item" href="projectController/projectList">
				<span class="glyphicon glyphicon-tasks" style="margin-left:10px; margin-right:30px;"></span>
				项目列表
			</a>
			<a class="list-group-item" href="javascript:void(0)">
				<span class="glyphicon glyphicon-time" style="margin-left:10px; margin-right:30px;"></span>
				工时报告
			</a>
			<a class="list-group-item" href="javascript:void(0)">
				<span class="glyphicon glyphicon-user" style="margin-left:10px; margin-right:30px;"></span>
				所有成员
			</a>
			<a class="list-group-item" href="javascript:void(0)">
				<span class="glyphicon glyphicon-cog" style="margin-left:10px; margin-right:30px;"></span>
				系统设置
			</a>
		</div>
		
		<!-- 功能列表区结束 -->
	</div>
	<!--  -->
</div>
<!-- 左侧列表栏结束 -->