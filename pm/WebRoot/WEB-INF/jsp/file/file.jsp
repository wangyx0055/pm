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
					  <li role="presentation"><a href="javascript:void(0)" id="milestone">里程碑</a></li>
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
    					//消息版
    					$("#msgEdition").click(function(e){
    						$(this).attr("href","messageController/message?id="+proId);
    					});
    					//里程碑
    					$("#milestone").click(function(e){
    						$(this).attr("href","milestoneController/milestone?id="+proId);
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


				<div class="jumbotron" style="background-color: #ffffff">
					<div class="row">
						<div class="col-xs-4">
							<div class="input-group">
  								<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
								<select id="r_type" name="r_type" class="form-control" selector="select"></select>
							</div>
						</div>
						<div class="col-xs-4">
							<div class="input-group">
  								<input name="resource_name" type="text" class="form-control" placeholder="文件名">
      							<span class="input-group-btn">
        							<button class="btn btn-default" type="button" name="go">搜索</button>
      							</span>
      							<script type="text/javascript">
      								$("button[name='go']").click(function(e){
      									var proId = $("#hiddenProId").val();
      									var name = $("input[name='resource_name']").val();
      									$.ajax({
      										url: "resourceController/findResourceByName",
      										data: "projectId="+proId+"&name="+name,
      										type: "post",
      										dataType: "html",
      										success: function(data) {
      											$("#info_table").empty();
      											$("#info_table").html(data);
      										},
      										error: function(data) {
      											alert("error"+data);
      										}
      									});
      								});
      							</script>
							</div>
						</div>
						<div class="col-xs-4">
							<div class="input-group">
  								<button id="resource_btn" style="width:150px;" class="btn btn-success" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
								  <span class="glyphicon glyphicon-cloud-upload"></span>&nbsp;上&nbsp;传
								</button>
							</div>
						</div>
					</div>
					
					<div style="height: 20px; "></div>
					<div class="collapse" id="collapseExample">
						<div class="well">
							<div class="row">
								<div class="col-xs-12">
									<div class="input-group">
								  		<span class="input-group-addon">
								  			<span class="glyphicon glyphicon-list"></span>
								  		</span>
										<select id="resource_type" name="resource_type" class="form-control" selector="select"></select>
									</div>
								</div>
								<div class="col-xs-12" style="height: 5px;"></div>
							</div>
							<div class="row" name="upload_div">
								<jsp:include page="upload.jsp"></jsp:include>
							</div>
						</div>
					</div>
					<div class="row" id="info_table">
						<%-- <jsp:include page="files.jsp"></jsp:include> --%>
					</div>
					<script type="text/javascript">
						$(document).ready(function(){
							var proId = $("#hiddenProId").val();
							$.ajax({
				 				url: "resourceController/allResources",
				  				data: "id="+proId,
				  				type: "post",
				  				dataType: "html",
				  				success: function(data) {
				  					$("#info_table").empty();
				  					$("#info_table").html(data);
				  				},
				  				error: function(data) {
				  					alert("error"+data);
				  				}
				  			});
			        		$.ajax({
			        			url: "resourceController/resourceTypes",
			        			data: "id="+proId,
			        			type: "post",
			        			dataType: "json",
			        			success: function(data) {
			        				var discusses = data;
			        				$("select[selector='select']").append("<option value=''>所有文件</option>");
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
				  			
				  			$("#r_type").change(function(e) {
					    		var filetype = $("#r_type").val();
					    		var proId = $("#hiddenProId").val();
					    		$.ajax({
					    			url: "resourceController/findResources",
					    			data: "type="+filetype+"&projectId="+proId,
					    			type: "post",
					    			dataType: "html",
					    			success: function(data) {
					    				$("#info_table").empty();
					    				$("#info_table").html(data);
					    			},
					    			error: function(data) {
					    				alert("error"+data);
					    			}
					    		});
					    	});
				  		});
				  	</script>
				</div>
	        </div>
	        <!-- 项目成员 -->
	        <jsp:include page="../common/pro/members.jsp"></jsp:include>
        </div><!-- 内容区结束 -->
        
        <!-- 预览文件 -->
        <div id="previewModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" data-backdrop="static">
		  <div class="modal-dialog modal-lg">
		    <div class="modal-content">
		      	<div class="modal-header">
		        	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        	<h4 class="modal-title" id="myModalLabel">预览文档</h4>
		      	</div>
		      	<input type="hidden" id="resource_id">
		      	<div class="modal-body">
		        	是多少
		      	</div>
		    </div>
		  </div>
		</div>
		
		<!-- 删除文件 -->
		<div id="deleteModal" class="modal fade" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title">文件删除</h4>
      			</div>
      			<input id="del_resource_id" type="hidden">
      			<input id="del_path" type="hidden">
      			<div class="modal-body">
       	 			<p>确定要删除这个文件吗？</p>
      			</div>
			    <div class="modal-footer">
			    	<button type="button" class="btn btn-default" data-dismiss="modal">不是</button>
			    	<button id="removeResource" type="button" class="btn btn-primary">是的</button>
			    </div>
			    <script type="text/javascript">
			    	$("#removeResource").click(function(e){
			    		var proId = $("#hiddenProId").val();
			    		var path = $("#del_path").val();
			    		var resourceId = $("#del_resource_id").val();
			    		$.ajax({
			    			url: "resourceController/deleteResource",
			    			data: "id="+resourceId+"&projectId="+proId+"&path="+path,
			    			type: "post",
			    			dataType: "html",
			    			success: function(data) {
			    				$("#deleteModal").modal("hide");
			    				$("#info_table").empty();
								$("#info_table").html(data);
			    			},
			    			error: function(data) {
			    				alert("error"+data);
			    			}
			    		});
			    	});
			    </script>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    </div>
  </body>
</html>

























