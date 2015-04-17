<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link href="<%=basePath %>styles/bootstrap.css" rel="stylesheet">
<div class="row">
	<div class="col-xs-12">
		<p>
			<c:if test="${task.status=='0' }">
				<label class="label label-warning">未完成任务</label>
			</c:if>
			<c:if test="${task.status=='1' }">
				<label class="label label-success">已完成任务</label>
			</c:if>
			<c:if test="${task.status=='2' }">
				<label class="label label-danger">延期任务</label>
			</c:if>
			：${task.description }
		</p>
	</div>
</div>
