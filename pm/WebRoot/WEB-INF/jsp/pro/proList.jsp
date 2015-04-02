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
  	<title>项目管理系统--项目概览</title>
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
		<h2>
			<i>Project List</i><span id="showTime2" class="label pull-right"></span><span
				id="showTime" class="label label-primary pull-right"></span>
		</h2>
		<div class="line-spacing"></div>
		
		<!-- 项目报表 -->
		<div id="chart"></div>
		<!-- 用于放置项目列表 -->
		<div id="proList">


<!-- 内容区 -->
<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">项目列表</h3>
	</div>
	<div class="panel-body">
		<ul id="tablist" class="nav nav-pills" role="tablist">
			<button id="addPro" type="button" class="btn btn-success pull-right" data-toggle="modal">项目新增</button>
			<li role="presentation">
				<a name="fst" href="#first" aria-controls="first" role="tab" data-toggle="tab">
					所有项目
					<span class="badge">
						<c:out value="${size }"></c:out>
					</span>
				</a>
			</li>
			<li role="presentation">
				<a name = "sec" href="#second" aria-controls="second" role="tab" data-toggle="tab">
					在实施的项目
					<span class="badge">
						<c:out value="${countIsDoing }"></c:out>
					</span>
				</a>
			</li>
			<li role="presentation">
				<a name = "thd" href="#third" aria-controls="third" role="tab" data-toggle="tab">
					已完成的项目
					<span class="badge">
						<c:out value="${countHaveDone }"></c:out>
					</span>
				</a>
			</li>
		</ul>
		<br>
		<!-- 项目列表 -->
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane" id="first"></div>
			<div role="tabpanel" class="tab-pane" id="second"></div>
			<div role="tabpanel" class="tab-pane" id="third"></div>
		</div>
		<script type="text/javascript">
			$('#tablist a:first').tab('show');
			$('#tablist li a').click(function (e) {
				e.preventDefault();
				if(this.name == "fst") {
					$.ajax({
						url: "projectController/allPros",
						data: "currentPageNo=1",
						type: "post",
						dataType: "html",
						success: function(data){
							$("#first").html(data);
						},
						error: function(data) {
							alert("error"+data.err);
						}
					});
				} else if(this.name == "sec") {
					$.ajax({
						url: "projectController/isDoingPros",
						type: "post",
						dataType: "html",
						success: function(data){
							$("#second").html(data);
						},
						error: function(data) {
							alert("error"+data.err);
						}
					});
				} else if(this.name == "thd") {
					$.ajax({
						url: "projectController/haveDonePros",
						type: "post",
						dataType: "html",
						success: function(data){
							$("#third").html(data);
						},
						error: function(data) {
							alert("error"+data.err);
						}
					});
				}
			});
		</script>
	</div>
</div>

<!-- 内容区结束 -->
<!-- 确认删除选中项目 -->
<div id="deleteProModal" class="modal fade" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">项目删除</h4>
			</div>
			<div class="modal-body">
				<p>确定要删除当前选中项目吗？该操作将删除相关的数据！！</p>
				<input type="hidden" id="hiddenProId">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="btnSave" type="button" class="btn btn-primary">确认删除</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- 确认删除选中项目结束 -->
<!-- 提示删除成功与否 -->
<div id="successDel" class="modal fade bs-example-modal-sm"
	tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">删除成功！！</div>
	</div>
</div>
<div id="failDel" class="modal fade bs-example-modal-sm" tabindex="-1"
	role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">删除失败！！</div>
	</div>
</div>
<!-- 提示删除成功与否 -->

<!-- 新增项目模态框 -->
<div id="addProModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">项目新增</h4>
			</div>
			<div class="modal-body">
				<form id="addProjectForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="proName" class="col-sm-2 control-label">项目名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="proName"
								placeholder="项目名称" required autofocus>
						</div>
					</div>
					<div id="prodescDiv" class="form-group">
						<label for="proDesc" class="col-sm-2 control-label">项目描述</label>
						<div class="col-sm-10">
							<textarea rows="3" class="form-control" id="proDesc"
								placeholder="项目描述" required></textarea>
						</div>
					</div>
					<div class="form-group" id="addMember" name="addMember">
						<label class="col-sm-2 control-label">邀请成员</label>
						<div class="col-sm-3">
							<input type="email" class="form-control" id="proUserEmail"
								placeholder="电子邮件" required>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="proUserNick"
								placeholder="昵称" required>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="proUserPassword"
								value="111111" disabled>
						</div>
						<label class="col-sm-1 control-label" style="margin-left:-10px;">
							<a href="javascript:addMember()"><span
								class="glyphicon glyphicon-plus"></span></a>
						</label>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="savePro" type="button" class="btn btn-primary"
					form="addProjectForm">提交</button>
			</div>
		</div>
	</div>
</div>

<!-- 编辑项目模态框 -->
<div id="editProModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel" aria-hidden="true" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">项目编辑</h4>
			</div>
			<div class="modal-body">
				<form id="editProjectForm" class="form-horizontal" role="form">
					<input type="hidden" id="editProId">
					<input type="hidden" id="proType">
					<div class="form-group">
						<label for="proName" class="col-sm-2 control-label">项目名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="editProName"
								placeholder="项目名称" required autofocus>
						</div>
					</div>
					<div id="editProdescDiv" class="form-group">
						<label for="proDesc" class="col-sm-2 control-label">项目描述</label>
						<div class="col-sm-10">
							<textarea rows="3" class="form-control" id="editProDesc"
								placeholder="项目描述" required></textarea>
						</div>
					</div>
					<div class="form-group" id="editAddMember" name="editAddMember">
						<label class="col-sm-2 control-label">邀请成员</label>
							<input type="hidden" class="form-control" name="userId" id="userId"
								placeholder="用户ID" required>
						<div class="col-sm-4">
							<input type="email" class="form-control" name="uEMail" id="uEMail"
								placeholder="电子邮件" required disabled>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="userName" id="userName"
								placeholder="昵称" required disabled>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="userPassword" id="userPassword"
								value="111111" disabled>
						</div>
						<!-- <label class="col-sm-1 control-label" style="margin-left:-10px;">
							<a href="javascript:editAddMember()"><span
								class="glyphicon glyphicon-plus"></span></a>
						</label> -->
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="editProject" type="button" class="btn btn-primary"
					form="addProjectForm">提交</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
$(document).ready(function() {
	$.ajax({
		url: "projectController/allPros",
		type: "post",
		dataType: "html",
		async: false,
		success: function(data){
			$("#first").html(data);
		},
		error: function(data) {
			alert("error"+data.err);
		}
	});
	
	//项目详情
	$("a[name='projectName']").click(
		function(e) {
			var proId = $(this).parent("td").parent("tr").children("#pId").text();
			$(this).attr(
				"href",
				"projectController/ProjectDetails?id=" + proId);
	});

	//删除项目
	$("button[name='deletePro']").click(function(e) {
		var proId = $(this).parent("td").parent("tr").children("#pId").text();
		$("#hiddenProId").val(proId);
		var type = $(this).parent("td").parent("tr").children("#type").text();
		$("#type").val(type);
		$("#deleteProModal").modal("show");
	});
	$("#btnSave").click(function(e) {
		var proId = $("#hiddenProId").val();
		$.ajax({
			url: "projectController/deleteProject",
			data: "id=" + proId,
			type: 'post',
			dataType: "json",
			success: function(data) {
				$("#deleteProModal").modal("hide");
			}
		});
	});
	$('#deleteProModal').on('hidden.bs.modal', function(){
		showProjects($("#type").val());
	});
	
	//编辑项目
	$("button[name='editPro']").click(function(e) {
		var proName = $(this).parent("td").parent("tr").children("td").children("#projectName").text();
		var proDesc = $(this).parent("td").parent("tr").children("#projectDesc").text();
		var pId = $(this).parent("td").parent("tr").children("#pId").text();
		var type = $(this).parent("td").parent("tr").children("#type").text();
		$.ajax({
			url: "projectController/projectMembers",
			data: "id="+pId,
			type: "post",
			success:function(data) {
				$("#editProModal").modal("show");
				$("#editProName").val(proName.trim());
				$("#editProDesc").val(proDesc.trim());
				$("#editProId").val(pId);
				$("#proType").val(type);
				
				// 去除之前的多余的邀请表单
				var sum = 0;
				$("div[name=editAddMember]").each(function(){
					sum++;
					if(sum>=2) {
						$(this.remove());
					}
				});
				// 克隆邀请表单
				for (var i = 0; i < data.members.length-1; i++) {
					$("#editProdescDiv").after($("#editAddMember").clone());
					//editAddMember();
				}
				// 赋值
				var i = 0;
				$("input[name='uEMail']").each(function(){
					$(this).val(data.members[i++].email);
				});
				i = 0;
				$("input[name='userName']").each(function(){
					$(this).val(data.members[i++].name);
				});
				i = 0;
				$("input[name='userId']").each(function(){
					$(this).val(data.members[i++].id);
				});
			},
			error: function(data) {
				alert("asdasd");
			}
		});
	});
	$("#editProject").click(function(e) {
		var proId = $("#editProId").val();
		var proName = $("#editProName").val();
		var proDesc = $("#editProDesc").val();
		var userIds= new Array();
		var userEmails = new Array();
		var userNames= new Array();
		var userPasswords = new Array();
		
		var i = 0;
		$("[name='editAddMember']").each(function() {
			userIds[i] = $(this).find("#userId").val();
			userEmails[i] = $(this).find("#uEMail").val();
			userNames[i] = $(this).find("#userName").val();
			userPasswords[i] = $(this).find("#userPassword").val();
			i++;
		});
		$.ajax({
			url: "projectController/editProject",
			data: "id="+proId+"&name="+proName+"&description="+proDesc,
			type: "post",
			async: false,
			success:function(data) {
				if(data.success) 
					$('#editProModal').modal('hide');
				else 
					alert(data.errMsg);
			},
			error: function(data) {
				alert("error");
			}
		});
		$('#editProModal').on('hidden.bs.modal', function(){
			showProjects($("#proType").val());
		});
	});
	
	//新增项目
	$("#addPro").click(function() {
		// 去除之前的多余的邀请表单
		$("#addProModal").modal('show');
		var sum = 0;
		$("div[name='addMember']").each(function(){
			sum++;
			if(sum>=2) {
				$(this.remove());
			}
		});
	});
	$("#savePro").click(function() {
		var proName = $("#proName").val();
		var proDesc = $("#proDesc").val();
		var proUserEmails = new Array();
		var proUserNicks = new Array();
		var proUserPasswords = new Array();
		
		//名字的each可以选中所有，id只能选择一个
		var i = 0;
		$("[name='addMember']").each(function() {
			proUserEmails[i] = $(this).find("#proUserEmail").val();
			proUserNicks[i] = $(this).find("#proUserNick").val();
			proUserPasswords[i] = $(this).find("#proUserPassword").val();
			i++;
		});
		$.ajax({
			url: 'projectController/addProject',
			data: 'proName='+proName+'&proDesc='+proDesc+'&proUserEmails='+proUserEmails+'&proUserNicks='+proUserNicks+'&proUserPasswords='+proUserPasswords,
			type: 'post',
			async: false,
			success: function(data) {
				$('#addProModal').modal('hide');
			},
			error: function(data) {
				alert("新建项目失败！！！");
			}
		});
		$('#addProModal').on('hidden.bs.modal', function(){
			showProjects();
		});
	});
	
	// 显示项目列表
	function showProjects(flg) {
		if(flg == '1') {
			$.ajax({
				url: "projectController/allPros",
				data: "currentPageNo=1",
				type: "post",
				dataType: "html",
				success: function(data){
					$("#first").html(data);
					 location.reload();
				},
				error: function(data) {
					alert("error"+data.err);
				}
			});
		} else if(flg == '2') {
			$.ajax({
				url: "projectController/isDoingPros",
				type: "post",
				dataType: "html",
				success: function(data){
					$("#second").html(data);
					 location.reload();
				},
				error: function(data) {
					alert("error"+data.err);
				}
			});
		} else if(flg == '3'){
			$.ajax({
				url: "projectController/haveDonePros",
				type: "post",
				dataType: "html",
				success: function(data){
					$("#first").html(data);
					location.reload();
				},
				error: function(data) {
					alert("error"+data.err);
				}
			});
		} else {
			$.ajax({
				url: "projectController/allPros",
				data: "currentPageNo=1",
				type: "post",
				dataType: "html",
				success: function(data){
					$("#first").html(data);
					 location.reload();
				},
				error: function(data) {
					alert("error"+data.err);
				}
			});
		}
	}
});

function addMember() {
	$("#prodescDiv").after($("#addMember").clone());
}
var index = 0;
function editAddMember() {
	$("#editProdescDiv").after($("#editAddMember").clone().attr({"id":"editAddMember"+index,"name":"newMember"}));
	index++;
	$("div[name='newMember']").each(function(){
		$(this).children("input[name='userId']").val(null);
	});
}
</script>
</div>
</div>
<body>