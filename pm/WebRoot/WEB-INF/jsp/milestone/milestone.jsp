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
  	<title>项目管理系统--里程碑</title>
    <base href="<%=basePath%>">
	
	<!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/backstage.css" rel="stylesheet">
	<link href="styles/slider.css" rel="stylesheet">
	<link href="js/bootstrap/datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
	
	<script src="js/jquery.js"></script>
	<script src="js/jquery-ui-1.10.3.mouse_core.js"></script>
	
	<script src="js/bootstrap/js/bootstrap.js"></script>
	<!-- 添加进度条滑动js -->
	<script src="js/bootstrap/expand/bootstrapslider.js"></script>
	<script src="js/jquery.ui.touch-punch.js"></script>
	
    <!-- add bootstrap-datetimepicker.js -->
    <script type="text/javascript" src="js/bootstrap/datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="js/bootstrap/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<!-- 添加日历 -->
	<link href="js/bootstrap-calendar/css/calendar.css" rel="stylesheet">
	<script type="text/javascript" src="js/bootstrap-calendar/components/underscore/underscore-min.js"></script>
	<script type="text/javascript" src="js/bootstrap-calendar/js/calendar.js"></script>
	<script type="text/javascript" src="js/bootstrap-calendar/js/language/zh-CN.js"></script>
	<script type="text/javascript" src="js/bootstrap-calendar/components/jstimezonedetect/jstz.min.js"></script>
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
					  <li role="presentation"  class="active"><a href="javascript:void(0)" id="milestone">里程碑</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="taskList">任务列表</a></li>
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
		
		
				<div class="jumbotron" style="background-color: #ffffff"><!-- 新的主要内容 -->
					<div class="row">
						<!-- 表单内容：查找、新增 -->
						<div class="col-xs-3">
  							<div class="input-group">
  								<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
		        				<select class="form-control" selector="select"></select>
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
									$.ajax({
										url: "milestoneController/findByUser",
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
						</div>
						<div class="col-xs-3">
							<div class="input-group">
   								<input name="milestone_name" type="text" class="form-control" placeholder="里程碑名称">
      							<span class="input-group-btn">
        							<button class="btn btn-default" type="button" name="go">搜索</button>
      							</span>
      							<script type="text/javascript">
      								$("button[name='go']").click(function(e){
      									var proId = $("#hiddenProId").val();
      									var name = $("input[name='milestone_name']").val();
      									$.ajax({
      										url: "milestoneController/findByName",
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
  						<div class="col-xs-3">
							<div class="input-group">
								<button type="button" class="btn btn-primary" id="add_milestone_btn">创建里程碑</button>
								<script type="text/javascript">
				        			$("#add_milestone_btn").click(function(e){
				        				$("#add_milestone_modal").modal("show");
				        			});
				        		</script>
      						</div>
  						</div>
  						<div class="col-xs-3">
							<div class="input-group">
								<button type="button" class="btn btn-warning" id="calendar_btn">
									<span class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;日历
								</button>
								<script type="text/javascript">
				        			$("#calendar_btn").click(function(e){
				        				var scroll_offset = $("#calendar_div").offset();  //得到pos这个div层的offset，包含两个值，top和left
				        				$("body,html").animate({
				        					scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
				        				},400);
				        			});
				        		</script>
      						</div>
  						</div>
					</div><!-- 表单内容：查找、新增，结束 -->
					
					<div style="height: 20px;"></div>
					
					<!-- table内容开始 -->
					<div class="row" id="infoTable">
						<jsp:include page="unfinished.jsp"></jsp:include>
					</div>
					<script type="text/javascript">
						$(document).ready(function(){
							var proId = $("#hiddenProId").val();
							$.ajax({
								url: "milestoneController/findAll",
								data: "id="+proId,
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
					<!-- table内容结束 -->	
					
					
					
				</div><!-- 新的主要内容结束 -->

		    </div>
	        <!-- 项目成员 -->
	        <jsp:include page="../common/pro/members.jsp"></jsp:include>
        </div><!-- 内容区结束 -->
    </div>
    
    
    <!-- 新增里程碑 -->
    <div id="add_milestone_modal" class="modal fade" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">创建里程碑</h4>
	      </div>
	      <div class="modal-body">
	      	<form id="add_milestone_Form" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="milestoneName" class="col-sm-2 control-label">名称</label>
					<div class="col-sm-4">
						<input type="text" class="form-control" id="milestoneName"
							placeholder="里程碑名称" required autofocus>
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
					<label for="endDate" class="col-sm-2 control-label">截止日期</label>
					<div id="endDateDiv" class="col-sm-4 input-append date form_datetime">
						<input type="text" class="form-control" id="endDate"
							value="" placeholder="截止日期" readonly required autofocus>
					</div>
					<script type="text/javascript">
						$(document).ready(function(){
							$("#endDate").datetimepicker({
								language:  'zh-CN',
						        weekStart: 1,
						        todayBtn:  1,
								autoclose: 1,
								todayHighlight: 1,
								startView: 2,
								forceParse: 0,
								format: 'yyyy/mm/dd hh:ii:ss',
						        showMeridian: 1
							});
						});
					</script>
					<div class="col-sm-2"></div>
					<div class="col-sm-4">
						<div class="btn-group" data-toggle="buttons">
							<label class="btn btn-primary active">
								<input type="radio" name="sendEmail" value="1" autocomplete="off" checked>邮件通知
							</label>
							<label class="btn btn-primary">
								<input type="radio" name="sendEmail" value="0" autocomplete="off">不通知
							</label>
						</div>
					</div>
				</div>
				<div id="descriptionDiv" class="form-group">
					<label for="description" class="col-sm-2 control-label">里程碑描述</label>
					<div class="col-sm-10">
						<textarea rows="3" class="form-control" id="description"
							placeholder="任务描述" required></textarea>
					</div>
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        <button id="save_milestone_btn" type="button" class="btn btn-primary" data-loading-text="Loading..." autocomplete="off">创建</button>
	        <script type="text/javascript">
		        $("#add_milestone_modal").on('hidden.bs.modal', function (e) {
					$("#save_milestone_btn").button('reset');
				});
	        	$("#save_milestone_btn").click(function(data) {
	        		$(this).button('loading');
	        		var name = $("#milestoneName").val();
	        		var performer = $("select[name='userName']").val();
	        		var proId = $("#hiddenProId").val();
	        		var endDate = $("#endDate").val();
	        		var description = $("#description").val();
	        		var sendEmail = "";
	        		$("input[name='sendEmail']").each(function(){
	        			if(this.checked) {
	        				sendEmail = this.value;
	        			}
	        		});
	        		$.ajax({
	        			url: "milestoneController/saveMilestone",
	        			data: "name="+name+"&performer="+performer+"&endDate="+endDate+"&projectId="+proId+"&description="+description+"&sendEmail="+sendEmail,
	        			type: "post",
	        			dataType: "html",
	        			success: function(data) {
	        				$("#add_milestone_modal").modal("hide");
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
	
	
	<!-- 编辑里程碑模态框 -->
	<div id="editmilestoneModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true" data-backdrop="static">
	  <div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">里程碑更新</h4>
			</div>
			<div class="modal-body">
				<form id="addMilestoneForm" class="form-horizontal" role="form">
					<div class="form-group">
						<input type="hidden" id="editMilestoneId">
						<input type="hidden" id="mile_status">
						<label for="milestone_name" class="col-sm-2 control-label">里程碑名称</label>
						<div class="col-sm-4">
							<input type="text" class="form-control" id="milestone_name"
								placeholder="里程碑名称" required autofocus>
						</div>
						<label for="user_name" class="col-sm-2 control-label">负责人</label>
						<div class="col-sm-4">
		        			<select class="form-control" name="user_name" id="user_name"></select>
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
		        						$("select[name='user_name']").append(option);
		        					}
		        				},
		        				error: function(data) {
		        					alert("error"+data);
		        				}
		        			});
		        		</script>
					</div>
					<div class="form-group">
						<label for="end_date" class="col-sm-2 control-label">截止日期</label>
						<div id="end_dateDiv" class="col-sm-4 input-append date form_datetime">
							<input type="text" class="form-control" id="end_date"
								value="" placeholder="截止日期" readonly required autofocus>
						</div>
						<script type="text/javascript">
							$(document).ready(function(){
								$("#end_date").datetimepicker({
									language:  'zh-CN',
							        weekStart: 1,
							        todayBtn:  1,
									autoclose: 1,
									todayHighlight: 1,
									startView: 2,
									forceParse: 0,
									format: 'yyyy/mm/dd hh:ii:ss',
							        showMeridian: 1
								});
							});
						</script>
						<label for="mile_progress" class="col-sm-2 control-label">进度</label>
						<div class="col-sm-4">
							<div class="progress slider" id="mile_progress" name="mile_progress" style="height: 30px;">
  								<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="padding-top: 5px;">
   									0%
  								</div>
							</div>
							<script type="text/javascript">
								$(document).ready(function(){
									$("div[name='mile_progress']").on("sliderchange", function(e,result){
										$(this).children("div[role='progressbar']").text(result.value+'%');
									});
								});
							</script>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2"></div>
						<div class="col-sm-4">
							<div class="btn-group" data-toggle="buttons">
								<label class="btn btn-primary active">
									<input type="radio" name="sendEmailEdit" value="1" autocomplete="off" checked>邮件通知
								</label>
								<label class="btn btn-primary">
									<input type="radio" name="sendEmailEdit" value="0" autocomplete="off">不通知
								</label>
							</div>
						</div>
					</div>
					<div id="editDescriptionDiv" class="form-group">
						<label for="editDesc" class="col-sm-2 control-label">里程碑描述</label>
						<div class="col-sm-10">
							<textarea rows="3" class="form-control" id="editDesc"
								placeholder="里程碑描述" required></textarea>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="update_mile_btn" type="button" class="btn btn-primary" form="editTaskForm" data-loading-text="Loading..." autocomplete="off">提交</button>
				<script type="text/javascript">
					$("#editmilestoneModal").on('hidden.bs.modal', function (e) {
						$("#update_mile_btn").button('reset');
					});
					$("#update_mile_btn").click(function(e){
						$(this).button('loading');
						var mileId = $("#editmilestoneModal").find("#editMilestoneId").val();
						var status = $("#editmilestoneModal").find("#mile_status").val();
						var mileName = $("#editmilestoneModal").find("#milestone_name").val();
						var userName = $("#editmilestoneModal").find("#user_name").val();
						var endDate = $("#editmilestoneModal").find("#end_date").val();
						var editDesc = $("#editmilestoneModal").find("#editDesc").val();
						var sendEmail = "";
						$("input[name='sendEmailEdit']").each(function(){
							if(this.checked) {
								sendEmail = this.value;
							}
						});
						var progress = $("#editmilestoneModal").find("#mile_progress").children("div").attr("aria-valuenow");
						$.ajax({
							url: "milestoneController/editMilestone",
							data: "id="+mileId+"&name="+mileName+"&performerId="+userName+"&endDate="+endDate+"&progress="+progress+"&projectId="+proId+"&status="+status+"&description="+editDesc+"&sendEmail="+sendEmail,
							type: "post",
							dataType: "html",
							success: function(data) {
								$("#editmilestoneModal").modal("hide");
								$("#infoTable").empty();
								$("#infoTable").html(data);
							},
							error: function(data){
								alert("error"+data);
							}
						});
					});
				</script>
			</div>
		</div>
	  </div>
	</div><!-- 编辑任务模态框结束 -->
	
	
	
	<!-- 删除里程碑模态框 -->
    <div id="deleteModal" class="modal fade" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
  		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title">里程碑删除</h4>
      			</div>
      			<input id="milestone_id_hidden" type="hidden">
      			<input id="milestone_status_hidden" type="hidden">
      			<div class="modal-body">
       	 			<p>确定要删除这个里程碑吗？</p>
      			</div>
			    <div class="modal-footer">
			    	<button type="button" class="btn btn-default" data-dismiss="modal">不是</button>
			    	<button id="removeMilestone" type="button" class="btn btn-primary" data-loading-text="Loading..." autocomplete="off">是的</button>
			    </div>
			    <script type="text/javascript">
				    $("#deleteModal").on('hidden.bs.modal', function (e) {
						$("#removeMilestone").button('reset');
					});
			    	$("#removeMilestone").click(function(e){
			    		$(this).button('loading');
			    		var proId = $("#hiddenProId").val();
			    		var milestoneId = $("#milestone_id_hidden").val();
			    		var status = $("#milestone_status_hidden").val();
			    		$.ajax({
			    			url: "milestoneController/remove",
			    			data: "id="+milestoneId+"&projectId="+proId+"&status="+status,
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
	
  </body>
</html>

























