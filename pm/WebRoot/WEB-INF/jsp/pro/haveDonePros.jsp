<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
%>

<!-- 第三个tab -->

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
			<td id="type3" class="hide">3</td>
			<td id="pId3" class="hide"><c:out value="${uVO.getProjectId() }"></c:out></td>
			<td id="sequence3"><c:out value="${uVO.getSequence() }"></c:out></td>
			<td id="projectNameTD3"><a name="projectName3"
				href="javascript:void(0)" id="projectName3"> <c:out
						value="${uVO.getName() }"></c:out>
			</a></td>
			<td id="projectDesc3"><c:out value="${uVO.getDescribes() }"></c:out></td>
			<td id="projectCreateTime3"><c:out
					value="${uVO.getCreateTime() }"></c:out></td>
			<td>
				<button id="editHaveDonePro" name='editHaveDonePro'
					class="btn btn-link">
					<span class="glyphicon glyphicon glyphicon-edit"></span>
				</button>
				<button id="delHaveDonePro" name='delHaveDonePro'
					class="btn btn-link">
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
			<c:forEach var="i" begin="1" end="${haveDonePage.totalPageNo }"
				step="1">
				<c:if test="${haveDonePage.currentPageNo==i }">
					<li class="active"><a href="javascript:void(0)" name="pageNo">${i }</a></li>
				</c:if>
				<c:if test="${haveDonePage.currentPageNo!=i }">
					<li><a href="javascript:void(0)" name="pageNo">${i }</a></li>
				</c:if>
			</c:forEach>
			<li><a href="javascript:void(0)" name="last">&raquo;</a></li>
			<input type="hidden" id="haveDonePageTotalNoHidden" value="${haveDonePage.totalPageNo }">
		</ul>
	</div>
</div>
<script type="text/javascript">
	$("a[name='last']").data("totalPageNoHidden", "${haveDonePage.totalPageNo }");
	$("a[name='former']").click(function(e) {
		pageClick(1);
	});
	$("a[name='last']").click(function(e) {
		var totalPageNoHidden = $("#haveDonePageTotalNoHidden").val();
		pageClick(totalPageNoHidden);
	});
	$("a[name='pageNo']").click(function(e) {
		var currentPageNo = $(this).text();
		pageClick(currentPageNo);
	});
	function pageClick(currentPageNo) {
		$.ajax({
			url : "projectController/haveDonePros",
			data : "currentPageNo=" + currentPageNo,
			type: "post",
			dataType :"html",
			success : function(data) {
				$("#third").html(data);
			}
		});
	}
</script>
<!-- 第三个tab -->
<script type="text/javascript">
$(document).ready(function() {
	//项目详情
	$("a[name='projectName3']").click(
		function(e) {
			var proId = $(this).parent("td").parent("tr").children("#pId3").text();
			$(this).attr(
				"href",
				"projectController/ProjectDetails?id=" + proId);
	});
	//删除项目
	$("button[name='delHaveDonePro']").click(function(e) {
		var type = $(this).parent("td").parent("tr").children("#type3").text();
		var proId = $(this).parent("td").parent("tr").children("#pId3").text();
		$("#hiddenProId").val(proId);
		$("#type").val(type);
		$("#deleteProModal").modal("show");
	});
	
	//编辑项目
	$("button[name='editHaveDonePro']").click(function(e) {
		var proName = $(this).parent("td").parent("tr").children("td").children("#projectName3").text();
		var proDesc = $(this).parent("td").parent("tr").children("#projectDesc3").text();
		var pId = $(this).parent("td").parent("tr").children("#pId3").text();
		var type = $(this).parent("td").parent("tr").children("#type3").text();
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
				alert("error");
			}
		});
	});
});

</script>
