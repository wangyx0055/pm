<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
%>
<div class="tab-content">
	<div role="tabpanel" class="tab-pane active" id="first">
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
					<td id="pId" class="hide"><c:out
							value="${uVO.getProjectId() }"></c:out></td>
					<td id="sequence"><c:out value="${uVO.getSequence() }"></c:out></td>
					<td id="projectNameTD"><a name="projectName"
						href="javascript:void(0)" id="projectName"> <c:out
								value="${uVO.getName() }"></c:out>
					</a></td>
					<td id="projectDesc"><c:out value="${uVO.getDescribes() }"></c:out></td>
					<td id="projectCreateTime"><c:out
							value="${uVO.getCreateTime() }"></c:out></td>
					<td>
						<button id="editPro" name='editPro' class="btn btn-link">
							<span class="glyphicon glyphicon glyphicon-edit"></span>
						</button>
						<button id="deletePro" name='deletePro' class="btn btn-link">
							<span class="glyphicon glyphicon-trash"></span>
						</button> <%-- <c:if test="${user.getRole() == '0' }">
							<button class="btn btn-link">
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if> 
						<c:if test="${user.getRole() != '0' }">
							<button class="btn btn-link" disabled>
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link"
								disabled>
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if> --%>
					</td>
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
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			$("#last").data("totalPageNoHidden", "${page.totalPageNo }");
			$("#first").click(function(e) {
				pageClick(1);
			});
			$("#last").click(function(e) {
				//var totalPageNoHidden = $("#totalPageNoHidden").val();
				var totalPageNoHidden = $("#last").data("totalPageNoHidden");
				console.log("这是我的测试！！" + totalPageNoHidden);
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
	</div>
	<!-- 第一个tab -->
	<!-- 第二个tab -->
	<div role="tabpanel" class="tab-pane" id="second">
		<table class="table table-striped table-hover">
			<tr>
				<th>项目编号</th>
				<th>名称</th>
				<th>描述</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${isDoingPros }" var="uVO">
				<tr>
					<td id="pId" class="hide"><c:out
							value="${uVO.getProjectId() }"></c:out></td>
					<td id="sequence"><c:out value="${uVO.getSequence() }"></c:out></td>
					<td id="projectNameTD"><a name="projectName"
						href="javascript:void(0)" id="projectName"> <c:out
								value="${uVO.getName() }"></c:out>
					</a></td>
					<td id="projectDesc"><c:out value="${uVO.getDescribes() }"></c:out></td>
					<td id="projectCreateTime"><c:out
							value="${uVO.getCreateTime() }"></c:out></td>
					<td>
						<button id="editIsDoingPro" name='editIsDoingPro' class="btn btn-link">
							<span class="glyphicon glyphicon glyphicon-edit"></span>
						</button>
						<button id="deleteIsDoingPro" name='deleteIsDoingPro' class="btn btn-link">
							<span class="glyphicon glyphicon-trash"></span>
						</button> <%-- <c:if test="${user.getRole() == '0' }">
							<button class="btn btn-link">
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if> 
						<c:if test="${user.getRole() != '0' }">
							<button class="btn btn-link" disabled>
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link"
								disabled>
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if> --%>
					</td>
				</tr>
			</c:forEach>
		</table>

		<!-- 分页 -->
		<div class="row">
			<div class="col-md-offset-4">
				<ul class="pagination">
					<li><a href="javascript:void(0)" id="first">&laquo;</a></li>
					<c:forEach var="i" begin="1" end="${isDoingPage.totalPageNo }" step="1">
						<c:if test="${isDoingPage.currentPageNo==i }">
							<li class="active"><a href="javascript:void(0)"
								name="pageNo">${i }</a></li>
						</c:if>
						<c:if test="${isDoingPage.currentPageNo!=i }">
							<li><a href="javascript:void(0)" name="pageNo">${i }</a></li>
						</c:if>
					</c:forEach>
					<li><a href="javascript:void(0)" id="last">&raquo;</a></li>
					<%-- <input type="hidden" id="totalPageNoHidden" value="${page.totalPageNo }"> --%>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			$("#last").data("totalPageNoHidden", "${isDoingPage.totalPageNo }");
			$("#first").click(function(e) {
				pageClick(1);
			});
			$("#last").click(function(e) {
				//var totalPageNoHidden = $("#totalPageNoHidden").val();
				var totalPageNoHidden = $("#last").data("totalPageNoHidden");
				console.log("这是我的测试！！" + totalPageNoHidden);
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
	</div>
	<!-- 第二个tab -->
	<!-- 第三个tab -->
	<div role="tabpanel" class="tab-pane" id="third">
		<table class="table table-striped table-hover">
			<tr>
				<th>项目编号</th>
				<th>名称</th>
				<th>描述</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			<c:forEach items="${haveDonePros }" var="uVO">
				<tr>
					<td id="pId" class="hide"><c:out
							value="${uVO.getProjectId() }"></c:out></td>
					<td id="sequence"><c:out value="${uVO.getSequence() }"></c:out></td>
					<td id="projectNameTD"><a name="projectName"
						href="javascript:void(0)" id="projectName"> <c:out
								value="${uVO.getName() }"></c:out>
					</a></td>
					<td id="projectDesc"><c:out value="${uVO.getDescribes() }"></c:out></td>
					<td id="projectCreateTime"><c:out
							value="${uVO.getCreateTime() }"></c:out></td>
					<td>
						<button id="editHaveDonePro" name='editHaveDonePro' class="btn btn-link">
							<span class="glyphicon glyphicon glyphicon-edit"></span>
						</button>
						<button id="delHaveDonePro" name='delHaveDonePro' class="btn btn-link">
							<span class="glyphicon glyphicon-trash"></span>
						</button> <%-- <c:if test="${user.getRole() == '0' }">
							<button class="btn btn-link">
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if> 
						<c:if test="${user.getRole() != '0' }">
							<button class="btn btn-link" disabled>
								<span class="glyphicon glyphicon glyphicon-edit"></span>
							</button>
							<button id="deletePro" name='deletePro' class="btn btn-link"
								disabled>
								<span class="glyphicon glyphicon-trash"></span>
							</button>
						</c:if> --%>
					</td>
				</tr>
			</c:forEach>
		</table>

		<!-- 分页 -->
		<div class="row">
			<div class="col-md-offset-4">
				<ul class="pagination">
					<li><a href="javascript:void(0)" id="first">&laquo;</a></li>
					<c:forEach var="i" begin="1" end="${haveDonePage.totalPageNo }" step="1">
						<c:if test="${haveDonePage.currentPageNo==i }">
							<li class="active"><a href="javascript:void(0)"
								name="pageNo">${i }</a></li>
						</c:if>
						<c:if test="${isDoingPage.currentPageNo!=i }">
							<li><a href="javascript:void(0)" name="pageNo">${i }</a></li>
						</c:if>
					</c:forEach>
					<li><a href="javascript:void(0)" id="last">&raquo;</a></li>
					<%-- <input type="hidden" id="totalPageNoHidden" value="${page.totalPageNo }"> --%>
				</ul>
			</div>
		</div>
		<script type="text/javascript">
			$("#last").data("totalPageNoHidden", "${haveDonePage.totalPageNo }");
			$("#first").click(function(e) {
				pageClick(1);
			});
			$("#last").click(function(e) {
				//var totalPageNoHidden = $("#totalPageNoHidden").val();
				var totalPageNoHidden = $("#last").data("totalPageNoHidden");
				console.log("这是我的测试！！" + totalPageNoHidden);
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
	</div>
	<!-- 第三个tab -->
</div>

<script>
	$(function() {
		$('#tablist a:first').tab('show');
	});
</script>
