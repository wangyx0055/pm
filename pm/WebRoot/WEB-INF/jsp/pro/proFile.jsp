<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>项目管理系统--项目文件</title>
<base href="<%=basePath%>">

<!-- Bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="././css/bootstrap.css" rel="stylesheet">
<link href="././css/backstage.css" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="././js/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="././js/bootstrap.js"></script>

<script type="text/javascript" src="././js/jquery.uploadify-3.1.js"></script>
<script type="text/javascript" src="././js/jquery.uploadify-3.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="././css/uploadify.css" />

</head>

<body>
	<div class="container container-own">
		<jsp:include page="<%=basePath %>/jsp/common/header.jsp"></jsp:include>

		<jsp:include page="<%=basePath %>/jsp/common/sideBar.jsp"></jsp:include>
		<h2>
			<i>${projects.name }</i><span id="showTime2" class="label pull-right"></span><span
				id="showTime" class="label label-primary pull-right"></span>
		</h2>
		<div class="line-spacing"></div>

		<!-- 内容区 -->
		<div class="row">
			<div class="col-md-11">
				<div class="panel panel-default">
					<!-- 导航 -->
					<ul class="nav nav-pills" role="tablist">
						<li role="presentation"><a href="javascript:void(0)"
							id="proDetails">项目概览</a></li>
						<li role="presentation"><a href="javascript:void(0)"
							id="msgEdition">消息版</a></li>
						<li role="presentation"><a href="javascript:void(0)"
							id="milepost">里程碑</a></li>
						<li role="presentation"><a href="javascript:void(0)"
							id="taskList">任务列表</a></li>
						<li role="presentation"><a href="javascript:void(0)"
							id="writeBoard">写字板</a></li>
						<li role="presentation"><a href="javascript:void(0)"
							id="workHours">工时</a></li>
						<li role="presentation" class="active"><a
							href="javascript:void(0)" id="files">文件</a></li>
					</ul>
				</div>

				<!-- 内容 -->

				<div class="jumbotron" style="background-color: #ffffff">
					<h3 class="page-header" style="margin-top:-30px;">文件上传</h3>
					<div class="row">
						<div class="form-group">
							<label class="col-xs-1 control-label">文件</label>
							<div class="col-xs-3">
								<input type="file" placeholder="file url" id="file" name="file"
									class="form-control">
								<div id="fileQueue"></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-offset-1 col-xs-3">
							<input class="btn btn-warning" type="button" id="subBtn"
								value="上传">
						</div>
					</div>
					<h3 class="page-header">文件下载</h3>
					<!-- 放置下载文件列表 -->
					<div id="fileList"></div>
				</div>
			</div>
			<div class="col-md-1"
				style="background-color:#ffffff; border-radius:15px;">
				<div style="padding-top:10px;" id="proMem">
					<b>项目成员</b>
					<hr>
					<!-- js控制放置成员 -->
					<hr>
				</div>
			</div>
		</div>
		<!-- 内容区结束 -->

		<input id="hiddenProId" type="hidden" value="${projects.id }">
		<script type="text/javascript">
	    	//防止客户端缓存文件，造成uploadify.js不更新，而引起的“喔唷，崩溃啦”  
 	    	document.write("<script type='text/javascript' "  
	            + "src='././js/jquery.uploadify-3.1.min.js?" + new Date()  
	            + "'></s" + "cript>");
	    	
    		$(document).ready(function(){
    			//设置uploadify样式的上传按钮方式
    			uploadFiles();
    			
    			//上传
    			$("#subBtn").click(function(e){
    				location.href="project/FileServlet";
    			});
    			
    			
    			var proId = $("#hiddenProId").val();
    			//项目概览
    			$("#proDetails").click(function(e){
    				$(this).attr("href","project/ProjectDetailsServlet?proId="+proId);
    			});
    			//文件
    			$("#files").click(function(e){
    				$(this).attr("href","jsp/pro/proFile.jsp");
    			});
    			//写字板
    			$("#writeBoard").click(function(e){
    				$(this).attr("href","jsp/textEdit/textEdit.jsp");
    			});
    			
    			//文件下载列表显示
    			$.ajax({
    				url:"file/FileListServlet",
    				data:"proId="+proId,
    				success:function(data){
    					$("#fileList").html(data);
    				}
    			});
    			
    			//项目成员列表
    			$.ajax({
    				url:"pro/ProjectMemberServlet",
    				data:"proId="+proId,
    				success:function(data){
    					$("#proMem").children("hr:last").before(data);
    				}
    			});
    		});
    		
    		/**
    		 *  使用uploadify插件进行异步上传，选中文件即进行上传
    		 */
     		function uploadFiles() {
    			$("#file").uploadify({
    				'auto' : true, // 选择文件后自动上传
    				'swf' : 'js/uploadify.swf', // 上传flash文件路径
    				'uploader' : '<%=basePath%>project/FileServlet',//后台处理的请求
					'queueID' : 'fileQueue',//与下面的id对应  文件队列
					'queueSizeLimit' : 3,
					"fileObjName" : "fieldName", // 后台接受参数名称，即fieldName
					"preventCaching" : true, // 设置随机参数，防止缓存
					"progressData" : "percentage", // 显示上传进度百分比
					"removeCompleted" : false, // 上传后是否自动删除记录
					'multi' : true, // 是否支持多文件上传
					"uploadLimit" : 50, // 上传限制
					'buttonText' : '选择文件',
				});
			}
		</script>
	</div>
</body>
</html>
