<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
%>
<!-- 第二个tab -->

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
			<td id="type2" class="hide">2</td>
			<td id="pId2" class="hide"><c:out value="${uVO.getProjectId() }"></c:out></td>
			<td id="sequence2"><c:out value="${uVO.getSequence() }"></c:out></td>
			<td id="projectNameTD2"><a name="projectName2"
				href="javascript:void(0)" id="projectName2"> <c:out
						value="${uVO.getName() }"></c:out>
			</a></td>
			<td id="projectDesc2"><c:out value="${uVO.getDescribes() }"></c:out></td>
			<td id="projectCreateTime2"><c:out
					value="${uVO.getCreateTime() }"></c:out></td>
			<td>
				<button id="editIsDoingPro" name='editIsDoingPro'
					class="btn btn-link">
					<span class="glyphicon glyphicon glyphicon-edit"></span>
				</button>
				<button id="deleteIsDoingPro" name='deleteIsDoingPro'
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
			<c:forEach var="i" begin="1" end="${isDoingPage.totalPageNo }"
				step="1">
				<c:if test="${isDoingPage.currentPageNo==i }">
					<li class="active"><a href="javascript:void(0)" name="pageNo">${i }</a></li>
				</c:if>
				<c:if test="${isDoingPage.currentPageNo!=i }">
					<li><a href="javascript:void(0)" name="pageNo">${i }</a></li>
				</c:if>
			</c:forEach>
			<li><a href="javascript:void(0)" name="last">&raquo;</a></li>
			<input type="hidden" id="isDoingPageTotalNoHidden" value="${isDoingPage.totalPageNo }">
		</ul>
	</div>
</div>
<script type="text/javascript">
	$("a[name='last']").data("totalPageNoHidden", "${isDoingPage.totalPageNo }");
	$("a[name='former']").click(function(e) {
		pageClick(1);
	});
	$("a[name='last']").click(function(e) {
		var totalPageNoHidden = $("#isDoingPageTotalNoHidden").val();
		//var totalPageNoHidden = $("a[name='last']").data("totalPageNoHidden");
		pageClick(totalPageNoHidden);
	});
	$("a[name='pageNo']").click(function(e) {
		var currentPageNo = $(this).text();
		pageClick(currentPageNo);
	});
	function pageClick(currentPageNo) {
		$.ajax({
			url : "projectController/isDoingPros",
			data : "currentPageNo=" + currentPageNo,
			type: "post",
			dataType :"html",
			success : function(data) {
				$("#second").html(data);
			}
		});
	}
</script>

<script type="text/javascript">
$(document).ready(function() {
	//项目详情
	$("a[name='projectName2']").click(
		function(e) {
			var proId = $(this).parent("td").parent("tr").children("#pId2").text();
			$(this).attr(
				"href",
				"projectController/ProjectDetails?id=" + proId);
	});
	//删除项目
	$("button[name='deleteIsDoingPro']").click(function(e) {
		var type = $(this).parent("td").parent("tr").children("#type2").text();
		var proId = $(this).parent("td").parent("tr").children("#pId2").text();
		$("#hiddenProId").val(proId);
		$("#type").val(type);
		$("#deleteProModal").modal("show");
	});
	
	//编辑项目
	$("button[name='editIsDoingPro']").click(function(e) {
		var proName = $(this).parent("td").parent("tr").children("td").children("#projectName2").text();
		var proDesc = $(this).parent("td").parent("tr").children("#projectDesc2").text();
		var pId = $(this).parent("td").parent("tr").children("#pId2").text();
		var type = $(this).parent("td").parent("tr").children("#type2").text();
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

