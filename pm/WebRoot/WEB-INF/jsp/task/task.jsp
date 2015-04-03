<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<title>项目管理系统--项目详情</title>
    <base href="<%=basePath%>">
	
	<!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/backstage.css" rel="stylesheet">
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap/js/bootstrap.js"></script>
	<script src="js/highcharts/highcharts.js"></script>
	<script src="js/highcharts/exporting.js"></script>
    
  </head>
  
  <body>
  	<div class="container container-own">
		<jsp:include page="../common/header.jsp"></jsp:include>
	    <jsp:include page="../common/sideBar.jsp"></jsp:include>
	    <h2><i>${project.name }</i><span id="showTime2" class="label pull-right"></span><span id="showTime" class="label label-primary pull-right"></span></h2>
	    <div class="line-spacing"></div>
	    
	    <!-- 内容区 -->	
	    <div class="row">
	    	<div class="col-md-11">
		        <div class="panel panel-default">
		            <ul class="nav nav-pills" role="tablist">
					  <li role="presentation"><a href="javascript:void(0)" id="proDetails">项目概览</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="msgEdition">消息版</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="milepost">里程碑</a></li>
					  <li role="presentation" class="active"><a href="javascript:void(0)" id="taskList">任务列表</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="writeBoard">写字板</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="workHours">工时</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="files">文件</a></li>
					</ul>
		        </div>
		        
		        <!-- 主体内容 -->
		        <div class="jumbotron" style="background-color: #ffffff">
		        	<table class="table table-striped table-hover">
		        		<tr>
		        			<th>序号</th>
		        			<th>名称</th>
		        			<th>负责人</th>
		        			<th>进度</th>
		        			<th>截止时间</th>
		        			<th>操作</th>
		        		</tr>
		        		<c:forEach items="${tasks }" var="task">
		        			<tr>
		        			<td id="taskTD0" class="hide"><c:out value="${task.id }"></c:out></td>
		        			<td id="taskTD1"><c:out value="${task.sequence }"></c:out></td>
		        			<td id="taskTD2">
		        				<c:if test="${task.priority ==1}">
		        					<span class="label label-danger"><c:out value="${task.name }"></c:out></span>
		        				</c:if>
		        				<c:if test="${task.priority ==2}">
		        					<span class="label label-warning"><c:out value="${task.name }"></c:out></span>
		        				</c:if>
		        				<c:if test="${task.priority ==3}">
		        					<span class="label label-info"><c:out value="${task.name }"></c:out></span>
		        				</c:if>
		        			</td>
		        			<td id="taskTD3"><c:out value="${task.performer }"></c:out></td>
		        			<td id="taskTD4"  style="width: 150px;">
		        				<div class="progress">
  									<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${task.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${task.progress }%">
   										<c:out value="${task.progress }%"></c:out>
  									</div>
								</div>
		        				
		        			</td>
		        			<td id="taskTD5"><c:out value="${task.endDate }"></c:out></td>
		        			<td id="taskTD6">
		        				<button id="editTask" name='editTask' class="btn btn-link">
									<span class="glyphicon glyphicon glyphicon-edit"></span>
								</button>
								<button id="deleteTask" name='deleteTask' class="btn btn-link">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
		        			</td>
		        			<tr>
		        		</c:forEach>
					</table>
				</div>
	        </div>
	        
	        <!-- 项目成员 -->
	        <jsp:include page="../common/pro/members.jsp"></jsp:include>
        </div>
    	<!-- 内容区结束 -->
    	
    	<input id="hiddenProId" type="hidden" value="${project.id }">
    	<script type="text/javascript">
    		$(document).ready(function(){
    			var proId = $("#hiddenProId").val();
    			
    			//项目概览
    			$("#proDetails").click(function(e){
    				$(this).attr("href","projectController/ProjectDetails?id="+proId);
    			});
    			//文件
    			$("#files").click(function(e){
    				$(this).attr("href","jsp/pro/proFile.jsp");
    			});
    			//写字板
    			$("#writeBoard").click(function(e){
    				$(this).attr("href","jsp/textEdit/textEdit.jsp");
    			});
    			// 任务列表
    			$("#taskList").click(function(e){
    				$(this).attr("href","taskController/tasks?id="+proId);
    			});
    		});
    	</script>
    </div>
  </body>
</html>
























