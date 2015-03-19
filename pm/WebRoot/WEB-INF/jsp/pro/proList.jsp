<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 内容区 -->
<div class="panel panel-default">

	<div class="panel-heading">
		<h3 class="panel-title">项目列表</h3>
	</div>
	<div class="panel-body">
		<ul class="nav nav-pills" role="tablist">
			<button id="addPro" type="button" class="btn btn-success pull-right"
				data-toggle="modal" data-target="#addProModal">项目新增</button>
			<li role="presentation" class="active"><a
				href="javascript:void(0)">所有项目<span class="badge"><c:out
							value="${allUserProjectVOs.size() }"></c:out></span></a></li>
			<li role="presentation"><a href="javascript:void(0)">在实施的项目</a></li>
			<li role="presentation"><a href="javascript:void(0)">已完成的项目</a></li>
		</ul>
		<br>

		<table class="table table-striped table-hover">
			<tr>
				<th>项目编号</th>
				<th>名称</th>
				<th>描述</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${upVOs }" var="uVO">
				<tr>
					<td id="pId"><c:out value="${uVO.getProjectId() }"></c:out></td>
					<td><a name="projectName" href="javascript:void(0)"
						id="projectName"> <c:out value="${uVO.getName() }"></c:out>
					</a></td>
					<td id="projectDesc"><c:out value="${uVO.getDescribes() }"></c:out></td>
					<td id="projectCreateTime"><c:out
							value="${uVO.getCreateTime() }"></c:out></td>
					<td><c:if test="${user.getRole() == '0' }">
							<button class="btn btn-link">
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if> <c:if test="${user.getRole() != '0' }">
							<button class="btn btn-link" disabled>
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link"
								disabled>
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if></td>
				</tr>
			</c:forEach>
		</table>

		<!-- 分页 -->
		<div class="row">
			<div class="col-md-offset-4">
				<ul class="pagination">
					<li><a href="javascript:void(0)" id="first">&laquo;</a></li>
					<c:forEach var="i" begin="1" end="${page.totalPageNo }" step="1">
						<c:if test="${page.currentPageNo==i }">
							<li class="active"><a href="javascript:void(0)"
								name="pageNo">${i }</a></li>
						</c:if>
						<c:if test="${page.currentPageNo!=i }">
							<li><a href="javascript:void(0)" name="pageNo">${i }</a></li>
						</c:if>
					</c:forEach>
					<li><a href="javascript:void(0)" id="last">&raquo;</a></li>
					<%-- <input type="hidden" id="totalPageNoHidden" value="${page.totalPageNo }"> --%>

					<script>
						$("#last").data("totalPageNoHidden",
								"${page.totalPageNo }");
						$("#first").click(function(e) {
							pageClick(1);
						});
						$("#last")
								.click(
										function(e) {

											//var totalPageNoHidden = $("#totalPageNoHidden").val();
											var totalPageNoHidden = $("#last")
													.data("totalPageNoHidden");
											console.log("这是我的测试！！"
													+ totalPageNoHidden);
											pageClick(totalPageNoHidden);
										});
						$("a[name='pageNo']").click(function(e) {
							var currentPageNo = $(this).text();
							pageClick(currentPageNo);
						});
						function pageClick(currentPageNo) {
							$.ajax({
								url : "pro/ProListServlet",
								data : "currentPageNo=" + currentPageNo,
								success : function(data) {
									$("#proList").html(data);
								}
							});
						}
					</script>
				</ul>
			</div>
		</div>

	</div>
</div>

<!-- 内容区结束 -->
<!-- 确认删除选中项目 -->
<div id="deleteProModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">项目删除</h4>
			</div>
			<div class="modal-body">
				<p>确认删除当前选中项目吗？该操作将删除相关的其余数据！！</p>
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
	aria-labelledby="mySmallModalLabel" aria-hidden="true">
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
								value="初始密码：111111" disabled>
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




<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//项目详情
						$("a[name='projectName']").click(
								function(e) {
									var proId = $(this).parent("td").parent(
											"tr").children("#pId").text();
									$(this).attr(
											"href",
											"project/ProjectDetailsServlet?proId="
													+ proId);
								});

						//删除项目
						$("button[name='deletePro']").click(
								function(e) {
									var proId = $(this).parent("td").parent(
											"tr").children("#pId").text();
									$("#hiddenProId").val(proId);
									$("#deleteProModal").modal("show");
								});
						$("#btnSave")
								.click(
										function(e) {
											var proId = $("#hiddenProId").val();
											$
													.ajax({
														url : "project/ProjectDeleteServlet",
														data : "proId=" + proId,
														dataType : "json",
														success : function(data) {
															if (data.flag) {
																$(
																		"#deleteProModal")
																		.modal(
																				"hide");
																location.href = "common/WelcomeServlet";
															} else {
																$(
																		"#deleteProModal")
																		.modal(
																				"hide");
																$("#failDel")
																		.modal(
																				"show");
															}
														}
													});
										});

						//新增项目
						$("#savePro")
								.click(
										function() {
											var proUserEmails = "";
											var proUserNicks = "";
											var proUserPasswords = "";

											//名字的each可以选中所有，id只能选择一个
											$("[name='addMember']")
													.each(
															function() {
																proUserEmails += $(
																		this)
																		.find(
																				"#proUserEmail")
																		.val()
																		+ ",";
																proUserNicks += $(
																		this)
																		.find(
																				"#proUserNick")
																		.val()
																		+ ",";
																proUserPasswords += $(
																		this)
																		.find(
																				"#proUserPassword")
																		.val()
																		+ ",";
															});

											$
													.ajax({
														url : "user/AddProServlet",
														data : "proName="
																+ $("#proName")
																		.val()
																+ "&proDesc="
																+ $("#proDesc")
																		.val()
																+ "&proUserEmails="
																+ proUserEmails
																+ "&proUserNicks="
																+ proUserNicks
																+ "&proUserPasswords="
																+ proUserPasswords,
														dataType : "json",
														success : function(data) {
															if (data.flag) {
																location.href = "common/WelcomeServlet";
															} else {
																alert("新建项目失败！！！");
															}
														}
													});
										});
					});
	function addMember() {
		$("#prodescDiv").after($("#addMember").clone());
	}
</script>
