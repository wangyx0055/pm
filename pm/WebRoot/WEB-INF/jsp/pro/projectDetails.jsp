<%@page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  	<!-- 隐藏信息域 -->
	<input id="hiddenProId" type="hidden" value="${project.id }">

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
					  <li role="presentation" class="active"><a href="javascript:void(0)" id="proDetails">项目概览</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="msgEdition">消息版</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="milepost">里程碑</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="taskList">任务列表</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="writeBoard">写字板</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="workHours">工时</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="files">文件</a></li>
					</ul>
		        </div>
		        
		        <!-- 主体内容 -->
		        <div class="jumbotron" style="background-color: #ffffff">
				 <label class="label label-success" style="font-size:24px;">${project.name }</label><hr>
				 ${project.description }
				</div>
	        </div>
	        
	        <!-- 项目成员 -->
	        <jsp:include page="../common/pro/members.jsp"></jsp:include>
        </div>
    	<!-- 内容区结束 -->
    	
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
    				$(this).attr("href","discussController/findDiscuss?id="+proId);
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
























