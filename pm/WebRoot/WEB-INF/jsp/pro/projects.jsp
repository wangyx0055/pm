<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
%>

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
				<td id="type" class="hide">1</td>
				<td id="pId" class="hide"><c:out value="${uVO.getProjectId() }"></c:out></td>
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
						<li class="active"><a href="javascript:void(0)" name="pageNo">${i }</a></li>
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
<!-- 第一个tab -->
