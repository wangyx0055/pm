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
  	<title>项目管理系统--写字板</title>
    <base href="<%=basePath%>">
	
	<!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/backstage.css" rel="stylesheet">
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap/js/bootstrap.js"></script>
  	
    <!-- add summernote -->
    <link rel="stylesheet" href="js/bootstrap/summernote/dist/font-awesome.min.css" />
    <link href="js/bootstrap/summernote/dist/summernote.css" rel="stylesheet">
    <script src="js/bootstrap/summernote/dist/summernote.js"></script>
    <script src="js/bootstrap/summernote/lang/summernote-zh-CN.js"></script>
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
					  <li role="presentation" class="active"><a href="javascript:void(0)" id="writeBoard">写字板</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="workHours">工时</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="files">文件资料</a></li>
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
		        		<!-- 表单内容：查找、新增 -->
		        		<div class="col-xs-4">
  							<div class="input-group">
  								<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
		        				<select id="find_discuss" class="form-control" selector="select"></select>
							</div>
						</div>
  						<div class="col-xs-4">
							<div class="input-group">
								<div class="col-xs-9"></div>
								<div class="col-xs-3">
									<button type="button" class="btn btn-info" name="new_discuss_btn">新增写字板</button>
								</div>
								<script type="text/javascript">
									$("button[name='new_discuss_btn']").click(function(e) {
										$("#addDiscussModal").modal('show');
									});
								</script>
      						</div>
  						</div>
					</div>
		        	<script type="text/javascript">
		        		var proId = $("#hiddenProId").val();
		        		$.ajax({
		        			url: "discussController/discussTypes",
		        			data: "id="+proId,
		        			type: "post",
		        			dataType: "json",
		        			success: function(data) {
		        				var discusses = data;
		        				var op = "<option value=''>"+'所有写字板'+"</option>";
		        				$("#find_discuss").append(op);
		        				for(var i = 0; i < discusses.length; i++) {
		        					var option = "<option value='"+discusses[i].type+"'>"+discusses[i].typeName+"</option>";
		        					$("select[selector='select']").append(option);
		        				}
		        				$("select[selector='select']").val("");
		        			},
		        			error: function(data) {
		        				alert("error"+data);
		        			}
		        		});
		        		$("#find_discuss").change(function(e) {
		        			var value = $("#find_discuss").val();
		        			var text = $("#find_discuss").find("option[value="+value+"]").text();
							$.ajax({
								url: "discussController/discusses",
								data: "type="+value+"&id="+proId,
								type: "post",
								dataType: "html",
								success: function(data) {
									$("#infoTable").empty();
									$("#infoTable").html(data);
								},
								error: function(data) {
									alert("error"+data);
								}
							});
		        		});
		        	</script>
		        	<div style="height: 20px;"></div>
		        	<div id="infoTable" class="row">
		        		<!-- 内部置入table -->
		        		<jsp:include page="discussList.jsp"></jsp:include>
					</div>
				</div>
	        </div>
	        
	        <!-- 项目成员 -->
	        <jsp:include page="../common/pro/members.jsp"></jsp:include>
        </div>
    	<!-- 内容区结束 -->
    	
    </div>
    
    
    <!-- 新增写字板 -->
	<div id="addDiscussModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  		<div class="modal-dialog modal-lg">
    		<div class="modal-content">
    			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增写字板</h4>
				</div>
				<div class="modal-body">
				<form id="addDiscussForm" class="form-horizontal" role="form">
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<label for="discuss_title" class="col-sm-2 control-label">标题</label>
								<div class="col-sm-4">
									<input name="discuss_title" type="text" class="form-control" placeholder="标题">
								</div>
								<div class="col-sm-4">
									<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
		        						<select id="discuss_type" class="form-control" selector="select"></select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div id="summernote"></div>
		        		</div>
		        		<script type="text/javascript">
		        			$(document).ready(function() {
		        				$('#summernote').summernote({
		        					lang: 'zh-CN',
		        					height : 300,
		        					minHeight : null,
		        					maxHeight : null,
		        					focus : true
		        				});
		        			});
		        		</script>
					</div>
				</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="saveDiscuss" type="button" class="btn btn-primary" form="addDiscussForm">提交</button>
				</div>
				<script type="text/javascript">
					$("#saveDiscuss").click(function(){
						var projectId = $('#hiddenProId').val();
						var title = $("input[name='discuss_title']").val();
						var type = $('#discuss_type').val();
						var content = $('#summernote').code();
						$.ajax({
							url: "discussController/addDiscuss",
							data: "title="+title+"&content="+content+"&type="+type+"&projectId="+projectId,
							type: "post",
							dataType: "html",
							success: function(data) {
								$("#addDiscussModal").modal('hide');
								$("#infoTable").empty();
								$("#infoTable").html(data);
							},
							error: function(data) {
								alert("error"+data);
							}
						});
					});
				</script>
    		</div>
  		</div>
	</div><!-- 新增写字板结束 -->
	
	
	
	
	<!-- 编辑写字板 -->
	<div id="editDiscussModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
  		<div class="modal-dialog modal-lg">
    		<div class="modal-content">
    			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">更新写字板</h4>
				</div>
				<div class="modal-body">
				<form id="editDiscussForm" class="form-horizontal" role="form">
					<input type="hidden" id="edit_discuss_id">
					<div class="row">
						<div class="col-sm-12">
							<div class="form-group">
								<label for="edit_discuss_title" class="col-sm-2 control-label">标题</label>
								<div class="col-sm-4">
									<input id="edit_discuss_title" name="edit_discuss_title" type="text" class="form-control" placeholder="标题">
								</div>
								<div class="col-sm-4">
									<div class="input-group">
  										<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
		        						<select id="edit_discuss_type" class="form-control" selector="select"></select>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div id="edit_content"></div>
		        		</div>
		        		<script type="text/javascript">
		        			$(document).ready(function() {
		        				$('#edit_content').summernote({
		        					lang: 'zh-CN',
		        					height : 300,
		        					minHeight : null,
		        					maxHeight : null,
		        					focus : true
		        				});
		        			});
		        		</script>
					</div>
				</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="edit_discuss" type="button" class="btn btn-primary" form="editDiscussForm">提交</button>
				</div>
				<script type="text/javascript">
					$("#edit_discuss").click(function(){
						var discussId = $("#edit_discuss_id").val();
						var projectId = $('#hiddenProId').val();
						var title = $("input[name='edit_discuss_title']").val();
						var type = $('#edit_discuss_type').val();
						var content = $('#edit_content').code();
						$.ajax({
							url: "discussController/editDiscuss",
							data: "title="+title+"&content="+content+"&type="+type+"&projectId="+projectId+"&id="+discussId,
							type: "post",
							dataType: "html",
							success: function(data) {
								$("#editDiscussModal").modal('hide');
								$("#infoTable").empty();
								$("#infoTable").html(data);
							},
							error: function(data) {
								alert("error"+data);
							}
						});
					});
				</script>
    		</div>
  		</div>
	</div><!-- 编辑写字板结束 -->
	
	<!-- 删除写字板 -->
	<div id="delete_discuss_modal" class="modal fade" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">删除写字板</h4>
	      </div>
	      <div class="modal-body">
	      	<input type="hidden" id="delete_discuss">
	        <p>确定要删除当前写字板吗？</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">不</button>
	        <button id="delete_discuss_btn" type="button" class="btn btn-primary">是的</button>
	        <script type="text/javascript">
	        	$("#delete_discuss_btn").click(function(e){
	        		var discuss_id = $("#delete_discuss").val();
	        		var project_id = $("#hiddenProId").val();
	        		$.ajax({
	        			url: "discussController/deleteDiscuss",
	        			data: "id="+discuss_id+"&projectId="+project_id,
	        			type: "post",
	        			dataType: "html",
	        			success: function(data) {
	        				$("#delete_discuss_modal").modal('hide');
	        				$("#infoTable").empty();
							$("#infoTable").html(data);
	        			},
	        			error: function(data) {
	        				alert("error"+data);
	        			}
	        		});
	        	});
	        </script>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- 删除写字板结束 -->
	
  </body>
</html>

























