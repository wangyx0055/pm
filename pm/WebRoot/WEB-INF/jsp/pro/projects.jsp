<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
%>

	<table class="table table-striped table-hover">
		<tr>
			<th>#</th>
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
				<li><a href="javascript:void(0)" name="former">&laquo;</a></li>
				<c:forEach var="i" begin="1" end="${page.totalPageNo }" step="1">
					<c:if test="${page.currentPageNo==i }">
						<li class="active"><a href="javascript:void(0)" name="pageNo">${i }</a></li>
					</c:if>
					<c:if test="${page.currentPageNo!=i }">
						<li><a href="javascript:void(0)" name="pageNo">${i }</a></li>
					</c:if>
				</c:forEach>
				<li><a href="javascript:void(0)" name="last">&raquo;</a></li>
				<input type="hidden" name="totalPageNoHidden" value="${page.totalPageNo }">
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		$("a[name='last']").data("totalPageNoHidden", "${page.totalPageNo }");
		$("a[name='former']").click(function(e) {
			pageClick(1);
		});
		$("a[name='last']").click(function(e) {
			var totalPageNoHidden = $("input[name=totalPageNoHidden]").val();
			pageClick(totalPageNoHidden);
		});
		$("a[name='pageNo']").click(function(e) {
			var currentPageNo = $(this).text();
			pageClick(currentPageNo);
		});
		function pageClick(currentPageNo) {
			$.ajax({
				url : "projectController/allPros",
				data : "currentPageNo=" + currentPageNo,
				type: "post",
				dataType :"html",
				success : function(data) {
					$("#first").html(data);
				}
			});
		}
	</script>
	<script>
		$(document).ready(function(){
			//项目详情
			$("a[name='projectName']").click(
				function(e) {
					var proId = $(this).parent("td").parent("tr").children("#pId").text();
					$(this).attr(
						"href",
						"projectController/ProjectDetails?id=" + proId);
			});
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
	</script>
<!-- 第一个tab -->
