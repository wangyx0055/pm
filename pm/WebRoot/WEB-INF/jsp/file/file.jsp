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
  	<title>项目管理系统--文件资料</title>
    <base href="<%=basePath%>">
	
	<!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/backstage.css" rel="stylesheet">
	<script src="js/jquery.js"></script>
	
    <!-- add fileinput -->
    <link href="js/bootstrap/fileinput/css/fileinput.css" rel="stylesheet">
    <script src="js/bootstrap/fileinput/js/fileinput.js"></script>
    <script src="js/bootstrap/fileinput/js/fileinput_locale_zh.js"></script>
    <script src="js/bootstrap/js/bootstrap.js"></script>
  </head>
  
  <body>
  	<div class="container container-own">
  		<!-- 隐藏域 -->
  		<input type="hidden" id="hiddenProId" value="${project.id }">
  		
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
					  <li role="presentation"><a href="javascript:void(0)" id="taskList">任务列表</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="writeBoard">写字板</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="workHours">工时</a></li>
					  <li role="presentation" class="active"><a href="javascript:void(0)" id="files">文件资料</a></li>
					</ul>
		        </div>
		        <script type="text/javascript">
    				$(document).ready(function(){
    					var proId = $("#hiddenProId").val();
    					//项目概览
    					$("#proDetails").click(function(e){
    						$(this).attr("href","projectController/ProjectDetails?id="+proId);
    					});
    					//文件资料
    					$("#files").click(function(e){
    						$(this).attr("href","resourceController/resources?id="+proId);
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

		        <!-- 主体内容 -->
		        <div class="jumbotron" style="background-color: #ffffff">
		          <div class="row">
		            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-success">
						<div class="panel-heading" role="tab" id="headingOne">
						  <h4 class="panel-title">
						    <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
						      文件上传
						    </a>
						  </h4>
						</div>
						<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
						  <div class="panel-body">
			      		    <!-- 文件上传 -->
					        <div class="row">
					          <div class="col-xs-12">
					        	<input id="input_files" name="resources" type="file" multiple class="file-loading">
								<script>
								  $("#input_files").fileinput({
									uploadUrl: "resourceController/batchUpload",
									uploadAsync: true,
									maxFileCount: 5
								  });
								</script>
							  </div>
							</div>
						  </div>
						</div>
					  </div>
					  <div class="panel panel-info">
						<div class="panel-heading" role="tab" id="headingTwo">
						  <h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							  资源预览下载
							</a>
						  </h4>
						</div>
						<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
						  <div class="panel-body">
							资源预览下载
						  </div>
						</div>
					  </div>
					</div>
				  </div>
				</div>
	        </div>
	        <!-- 项目成员 -->
	        <jsp:include page="../common/pro/members.jsp"></jsp:include>
        </div><!-- 内容区结束 -->
    </div>
  </body>
</html>

























