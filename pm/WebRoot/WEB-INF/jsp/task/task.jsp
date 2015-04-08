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
  	<title>项目管理系统--任务详情</title>
    <base href="<%=basePath%>">
	
	<!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/backstage.css" rel="stylesheet">
	<link href="styles/slider.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" media="screen" href="styles/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" type="text/css" media="all" href="styles/daterangepicker-bs3.css"/>
	
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.10.3.mouse_core.js"></script>
	
	<script src="js/bootstrap/js/bootstrap.js"></script>
	<script src="js/bootstrap/expand/bootstrapslider.js"></script>
	<script src="js/jquery.ui.touch-punch.js"></script>
	<script src="js/bootstrap/js/bootstrap-datetimepicker.js"></script>
	<script src="js/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<script src="js/highcharts/highcharts.js"></script>
	<script src="js/highcharts/exporting.js"></script>
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
					  <li role="presentation" class="active"><a href="javascript:void(0)" id="taskList">任务列表</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="writeBoard">写字板</a></li>
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
  								<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		        				<select class="form-control" selector="select"></select>
							</div>
						</div>
						<div class="col-xs-4">
							<div class="input-group">
   								<input name="task_name" type="text" class="form-control" placeholder="任务名">
      							<span class="input-group-btn">
        							<button class="btn btn-default" type="button" name="go">搜索</button>
      							</span>
      							<script type="text/javascript">
      								$("button[name='go']").click(function(e){
      									var proId = $("#hiddenProId").val();
      									var name = $("input[name='task_name']").val();
      									$.ajax({
      										url: "taskController/findTasksByName",
      										data: "id="+proId+"&name="+name,
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
      						</div>
  						</div>
  						<div class="col-xs-4">
							<div class="input-group">
								<div class="col-xs-9"></div>
								<div class="col-xs-3">
									<button type="button" class="btn btn-primary" name="new_task">新增任务</button>
								</div>
								<script type="text/javascript">
									$("button[name='new_task']").click(function(e) {
										$("#addTaskModal").modal('show');
									});
								</script>
      						</div>
  						</div>
					</div>
		        	<script type="text/javascript">
		        		var proId = $("#hiddenProId").val();
		        		$.ajax({
		        			url: "projectController/findUsers",
		        			data: "id="+proId,
		        			type: "post",
		        			dataType: "json",
		        			success: function(data) {
		        				var users = data.users;
		        				var op = "<option value=''>"+'所有成员'+"</option>";
		        				$("select[selector='select']").append(op);
		        				for(var i = 0; i < users.length; i++) {
		        					var option = "<option value='"+users[i].id+"'>"+users[i].name+"</option>";
		        					$("select[selector='select']").append(option);
		        				}
		        				$("select[selector='select']").val("");
		        			},
		        			error: function(data) {
		        				alert("error"+data);
		        			}
		        		});
		        		$("select[selector='select']").change(function(e) {
		        			var value = $("select[selector='select']").val();
		        			var text = $("select[selector='select']").find("option[value="+value+"]").text();
							$.ajax({
								url: "taskController/userTasks",
								data: "performer="+value+"&id="+proId,
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
		        	<table class="table table-striped table-hover">
		        		<tr>
		        			<th></th>
		        			<th>序号</th>
		        			<th>名称</th>
		        			<th>负责人</th>
		        			<th>进度</th>
		        			<th>截止时间</th>
		        			<th></th>
		        			<th></th>
		        		</tr>
		        		<c:forEach items="${tasks }" var="task">
		        			<tr>
		        			<td name="taskTD0" class="hide"><c:out value="${task.id }"></c:out></td>
		        			<td name="taskTD1"><input type="checkbox"></td>
		        			<td name="taskTD2"><c:out value="${task.sequence }"></c:out></td>
		        			<td name="taskTD3">
		        				<c:if test="${task.priority ==1}">
		        					<span name="tName" class="label label-danger" data-container="body" data-toggle="popover" data-placement="right" title="细节描述" data-content="${task.description }" style="cursor: pointer;"><c:out value="${task.name }"></c:out></span>
		        				</c:if>
		        				<c:if test="${task.priority ==2}">
		        					<span name="tName" class="label label-warning" data-container="body" data-toggle="popover" data-placement="right" title="细节描述" data-content="${task.description }" style="cursor: pointer;"><c:out value="${task.name }"></c:out></span>
		        				</c:if>
		        				<c:if test="${task.priority ==3}">
		        					<span name="tName" class="label label-info" data-container="body" data-toggle="popover" data-placement="right" title="细节描述" data-content="${task.description }" style="cursor: pointer;"><c:out value="${task.name }"></c:out></span>
		        				</c:if>
		        				<script>
		        				$(function (){
		    			        	$("[data-toggle='popover']").popover().on("mouseenter", function () {
		    		                    var _this = this;
		    		                    $(this).popover("show");
		    		                    $(this).siblings(".popover").on("mouseleave", function () {
		    		                        $(_this).popover('hide');
		    		                    });
		    		                }).on("mouseleave", function () {
		    		                    var _this = this;
		    		                    setTimeout(function () {
		    		                        if (!$(".popover:hover").length) {
		    		                            $(_this).popover("hide");
		    		                        }
		    		                    }, 100);
		    		                });
		    	     			});
   								</script>
		        			</td>
		        			<td name="taskTD4"><c:out value="${task.performer }"></c:out></td>
		        			<td name="taskTD5" style="width: 150px;">
		        				<div class="progress" name="sliderBar">
  									<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="${task.progress }" aria-valuemin="0" aria-valuemax="100" style="width: ${task.progress }%">
   										<c:out value="${task.progress }%"></c:out>
  									</div>
								</div>
		        			</td>
		        			<td name="taskTD6"><c:out value="${task.endDate }"></c:out></td>
		        			<td name="taskTD7">
		        				<a id="editTask" name='editTask' href="javascript:void(0)"><span class="glyphicon glyphicon-edit"></span></a>
		        			</td>
		        			<td name="taskTD8">
		        				<a id="deleteTask" name='deleteTask' href="javascript:void(0)"><span class="glyphicon glyphicon-trash"></span></a>
		        			</td>
		        			<td name="taskTD9" class="hide"><c:out value="${task.startDate }"></c:out></td>
		        			<td name="taskTD10" class="hide"><c:out value="${task.priority }"></c:out></td>
		        			<td name="taskTD11" class="hide"><c:out value="${task.progress }"></c:out></td>
		        			<td name="taskTD12" class="hide"><c:out value="${task.performerId }"></c:out></td>
		        			<td name="taskTD13" class="hide"><c:out value="${task.description }"></c:out></td>
		        			</tr>
		        		</c:forEach>
					</table>
					
					<script type="text/javascript">
						// 点击编辑修改任务
						$("a[name='editTask']").click(function(e) {
							var taskId = $(this).parent("td").parent("tr").children("td[name='taskTD0']").text().trim();
							var performerId = $(this).parent("td").parent("tr").children("td[name='taskTD12']").text().trim();
							var name = $(this).parent("td").parent("tr").children("td[name='taskTD3']").children("span").text().trim();
							var startDate = $(this).parent("td").parent("tr").children("td[name='taskTD9']").text().trim();
							var endDate = $(this).parent("td").parent("tr").children("td[name='taskTD6']").text().trim();
							var priority = $(this).parent("td").parent("tr").children("td[name='taskTD10']").text().trim();
							var progress = $(this).parent("td").parent("tr").children("td[name='taskTD11']").text().trim();
							var description = $(this).parent("td").parent("tr").children("td[name='taskTD13']").text().trim();
							$("#editTaskModal").modal('show');
							$("#editTaskModal").find("#editTaskId").val(taskId);
							$("#editTaskModal").find("#editTaskName").val(name);
							$("#editTaskModal").find("#editUser").val(performerId);
							$("#editTaskModal").find("#editStartDate").val(startDate);
							$("#editTaskModal").find("#editEndDate").val(endDate);
							$("#editTaskModal").find("#editPriority").val(priority);
							$("#editTaskModal").find("#editTaskDesc").val(description);
							$("#editTaskModal").find("#editProgress").children("div").attr("aria-valuenow",progress);
							$("#editTaskModal").find("#editProgress").children("div[role='progressbar']").attr("aria-valuenow", progress).html(progress+"%");
							$("#editTaskModal").find("#editProgress").children("div[role='progressbar']").css("width",+progress+"%");
						});
						// 点击删除任务
						$("a[name='deleteTask']").click(function(e) {
							var taskId = $(this).parent("td").parent("tr").children("td[name='taskTD0']").text().trim();
							$("#deleteModal").modal('show');
							$("#task_id_hidden").val(taskId);
						});
					</script>
					</div>
				</div>
	        </div>
	        
	        <!-- 项目成员 -->
	        <jsp:include page="../common/pro/members.jsp"></jsp:include>
        </div>
    	<!-- 内容区结束 -->
    </div>
    
    
    
    <!-- 删除任务模态框 -->
    <div id="deleteModal" class="modal fade" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title">任务删除</h4>
      			</div>
      			<input id="task_id_hidden" type="hidden">
      			<div class="modal-body">
       	 			<p>确定要删除这个任务吗？</p>
      			</div>
			    <div class="modal-footer">
			    	<button type="button" class="btn btn-default" data-dismiss="modal">不是</button>
			    	<button id="removeTask" type="button" class="btn btn-primary">是的</button>
			    </div>
			    <script type="text/javascript">
			    	$("#removeTask").click(function(e){
			    		var proId = $("#hiddenProId").val();
			    		var taskId = $("#task_id_hidden").val();
			    		$.ajax({
			    			url: "taskController/deleteTask",
			    			data: "id="+taskId+"&project="+proId,
			    			type: "post",
			    			dataType: "html",
			    			success: function(data) {
			    				$("#deleteModal").modal("hide");
			    				$("#infoTable").empty();
								$("#infoTable").html(data);
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
    
    <!-- 新增任务模态框 -->
	<div id="addTaskModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">任务新增</h4>
			</div>
			<div class="modal-body">
				<form id="addTaskForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="taskName" class="col-sm-2 control-label">任务名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="taskName"
								placeholder="任务名称" required autofocus>
						</div>
						<label for="userName" class="col-sm-2 control-label">负责人</label>
						<div class="col-sm-4">
		        			<select class="form-control" name="userName"></select>
		        		</div>
		        		<script type="text/javascript">
		        			var proId = $("#hiddenProId").val();
		        			$.ajax({
		        				url: "projectController/findUsers",
		        				data: "id="+proId,
		        				type: "post",
		        				dataType: "json",
		        				success: function(data) {
		        					var users = data.users;
		        					for(var i = 0; i < users.length; i++) {
		        						var option = "<option value='"+users[i].id+"'>"+users[i].name+"</option>";
		        						$("select[name='userName']").append(option);
		        					}
		        					$("select[name='userName']").val("");
		        				},
		        				error: function(data) {
		        					alert("error"+data);
		        				}
		        			});
		        		</script>
					</div>
					<div class="form-group">
						<label for="startDate" class="col-sm-2 control-label">开始日期</label>
						<div id="startDateDiv" class="col-sm-4 input-append date form_datetime">
							<input type="text" class="form-control" id="startDate"
								value="" placeholder="开始日期" readonly required autofocus>
						</div>
						<script type="text/javascript">
							$(document).ready(function(){
								$("#startDate").datetimepicker({
									language:  'zh-CN',
									format: 'yyyy/mm/dd hh:mm:ss',
						    	    weekStart: 1,
						   	    	todayBtn:  1,
						       		autoclose: 1,
						   	    	todayHighlight: 1,
							      	startView: 2,
						   	    	minView: 2,
							       	inputMask: true,
							       	forceParse: 0
								});
							});
						</script>
						<label for="endDate" class="col-sm-2 control-label">截止日期</label>
						<div id="endDateDiv" class="col-sm-4 input-append date form_datetime">
							<input type="text" class="form-control" id="endDate"
								value="" placeholder="截止日期" readonly required autofocus>
						</div>
						<script type="text/javascript">
							$(document).ready(function(){
								$("#endDate").datetimepicker({
									language:  'zh-CN',
									format: 'yyyy/mm/dd hh:mm:ss',
						    	    weekStart: 1,
						   	    	todayBtn:  1,
						       		autoclose: 1,
						   	    	todayHighlight: 1,
						        	startView: 2,
						        	minView: 2,
						        	inputMask: true,
						        	forceParse: 0
								});
							});
						</script>
					</div>
					<div class="form-group">
						<label for="priority" class="col-sm-2 control-label">优先级</label>
						<div class="col-sm-4">
							<select class="form-control" id="priority" name="priority">
								<option>选择优先级</option>
								<option value="1">高</option>
								<option value="2">中</option>
								<option value="3">低</option>
							</select>
						</div>
					</div>
					<div id="taskDescDiv" class="form-group">
						<label for="taskDesc" class="col-sm-2 control-label">任务描述</label>
						<div class="col-sm-10">
							<textarea rows="3" class="form-control" id="taskDesc"
								placeholder="任务描述" required></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="saveTask" type="button" class="btn btn-primary"
					form="addTaskForm">提交</button>
			</div>
		</div>
	  </div>
	</div><!-- 新增任务模态框结束 -->
	
	<!-- 编辑任务模态框 -->
	<div id="editTaskModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">任务更新</h4>
			</div>
			<div class="modal-body">
				<form id="addTaskForm" class="form-horizontal" role="form">
					<div class="form-group">
						<input type="hidden" id="editTaskId">
						<label for="editTaskName" class="col-sm-2 control-label">任务名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="editTaskName"
								placeholder="任务名称" required autofocus>
						</div>
						<label for="editUser" class="col-sm-2 control-label">负责人</label>
						<div class="col-sm-4">
		        			<select class="form-control" name="editUser" id="editUser"></select>
		        		</div>
		        		<script type="text/javascript">
		        			var proId = $("#hiddenProId").val();
		        			$.ajax({
		        				url: "projectController/findUsers",
		        				data: "id="+proId,
		        				type: "post",
		        				dataType: "json",
		        				success: function(data) {
		        					var users = data.users;
		        					for(var i = 0; i < users.length; i++) {
		        						var option = "<option value='"+users[i].id+"'>"+users[i].name+"</option>";
		        						$("select[name='editUser']").append(option);
		        					}
		        				},
		        				error: function(data) {
		        					alert("error"+data);
		        				}
		        			});
		        		</script>
					</div>
					<div class="form-group">
						<label for="editStartDate" class="col-sm-2 control-label">开始日期</label>
						<div id="editStartDateDiv" class="col-sm-4 input-append date form_datetime">
							<input type="text" class="form-control" id="editStartDate"
								value="" placeholder="开始日期" readonly required autofocus>
						</div>
						<script type="text/javascript">
							$(document).ready(function(){
								$("#editStartDate").datetimepicker({
									language:  'zh-CN',
									format: 'yyyy/mm/dd hh:mm:ss',
						    	    weekStart: 1,
						   	    	todayBtn:  1,
						       		autoclose: 1,
						   	    	todayHighlight: 1,
							      	startView: 2,
						   	    	minView: 2,
							       	inputMask: true,
							       	forceParse: 0
								});
							});
						</script>
						<label for="editEndDate" class="col-sm-2 control-label">截止日期</label>
						<div id="editEndDateDiv" class="col-sm-4 input-append date form_datetime">
							<input type="text" class="form-control" id="editEndDate"
								value="" placeholder="截止日期" readonly required autofocus>
						</div>
						<script type="text/javascript">
							$(document).ready(function(){
								$("#editEndDate").datetimepicker({
									language:  'zh-CN',
									format: 'yyyy/mm/dd hh:mm:ss',
						    	    weekStart: 1,
						   	    	todayBtn:  1,
						       		autoclose: 1,
						   	    	todayHighlight: 1,
						        	startView: 2,
						        	minView: 2,
						        	inputMask: true,
						        	forceParse: 0
								});
							});
						</script>
					</div>
					<div class="form-group">
						<label for="editPriority" class="col-sm-2 control-label">优先级</label>
						<div class="col-sm-4">
							<select class="form-control" id="editPriority" name="editPriority">
								<option>选择优先级</option>
								<option value="1">高</option>
								<option value="2">中</option>
								<option value="3">低</option>
							</select>
						</div>
						<label for="editProgress" class="col-sm-2 control-label">任务进度</label>
						<div class="col-sm-4">
							<div class="progress slider" id="editProgress" name="editProgress" style="height: 30px;">
  								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="padding-top: 5px;">
   									0%
  								</div>
							</div>
							<script type="text/javascript">
								$(document).ready(function(){
									$("div[name='editProgress']").on("sliderchange", function(e,result){
										$(this).children("div[role='progressbar']").text(result.value+'%');
									});
								});
							</script>
						</div>
					</div>
					<div id="editTaskDescDiv" class="form-group">
						<label for="taskDesc" class="col-sm-2 control-label">任务描述</label>
						<div class="col-sm-10">
							<textarea rows="3" class="form-control" id="editTaskDesc"
								placeholder="任务描述" required></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="editTaskBtn" type="button" class="btn btn-primary"
					form="editTaskForm">提交</button>
			</div>
		</div>
	  </div>
	</div><!-- 编辑任务模态框结束 -->
	<script type="text/javascript">
		$(document).ready(function(data){
			var performerId = '';
			var priority = '';
			var proId = $("#hiddenProId").val();
			$("select[name='userName']").change(function(e) {
				performerId = $("select[name='userName']").val();
			});
			$("select[name='priority']").change(function(e) {
				priority = $("select[name='priority']").val();
			});
			$("select[name='userName']").change(function(e) {
				performerId = $("select[name='userName']").val();
			});
			$("#saveTask").click(function(e) {
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				var taskDesc = $("#taskDesc").val();
				var name = $("#taskName").val();
				$.ajax({
					url: "taskController/addTask",
					data: "project="+proId+"&name="+name+"&performerId="+performerId+"&startDate="+startDate+"&endDate="+endDate+"&priority="+priority+"&description="+taskDesc,
					type: "post",
					dataType: "html",
					success: function(data) {
						$("#addTaskModal").modal("hide");
						$("#infoTable").empty();
						$("#infoTable").html(data);
					},
					error: function(data) {
						alert("error"+data);
					}
				});
			});
			
			// 编辑任务
			$("#editTaskBtn").click(function(e){
				var taskId = $("#editTaskModal").find("#editTaskId").val();
				var taskName = $("#editTaskModal").find("#editTaskName").val();
				var user = $("#editTaskModal").find("#editUser").val();
				var startDate = $("#editTaskModal").find("#editStartDate").val();
				var endDate = $("#editTaskModal").find("#editEndDate").val();
				var priority = $("#editTaskModal").find("#editPriority").val();
				var progress = $("#editTaskModal").find("#editProgress").children("div").attr(
						"aria-valuenow");
				var description = $("#editTaskModal").find("#editTaskDesc").val();
				$.ajax({
					url: "taskController/updateTask",
					data: "id="+taskId+"&name="+taskName+"&performerId="+user+"&startDate="+startDate+"&endDate="+endDate+"&priority="+priority+"&progress="+progress+"&description="+description+"&project="+proId,
					type: "post",
					dataType: "html",
					success: function(data) {
						$("#editTaskModal").modal("hide");
						$("#infoTable").empty();
						$("#infoTable").html(data);
					},
					error: function(data){
						alert("error"+data);
					}
				});
			});
		});
	</script>
	
	
	
	
	
	
	
	
	
  </body>
</html>
























