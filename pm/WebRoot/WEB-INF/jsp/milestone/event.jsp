<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="row">
	<div class="col-xs-11">
		<p>
			<c:if test="${milestone.status=='0' }">
				<label class="label label-warning">未完成里程碑</label>
			</c:if>
			<c:if test="${milestone.status=='1' }">
				<label class="label label-success">已完成里程碑</label>
			</c:if>
			<c:if test="${milestone.status=='2' }">
				<label class="label label-danger">已完成里程碑</label>
			</c:if>
			：${milestone.description }
		</p>
	</div>
</div>