<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table class="table table-striped table-hover">
	<tr>
		<th>#</th>
		<th>标题</th>
		<th>发布者</th>
		<th>所属</th>
		<th>创建时间</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${discusses }" var="discuss">
		<tr>
			<td name="taskTD0" class="hide"><c:out value="${discuss.id }"></c:out></td>
			<td name="taskTD1" class="hide"><c:out value="${discuss.type }"></c:out></td>
			<td name="taskTD2" class="hide"><c:out value="${discuss.authorId }"></c:out></td>
			<td name="taskTD3"><c:out value="${discuss.sequence }"></c:out></td>
			<td name="taskTD4">
				<a name="title_a" href="javascript:void(0)">
					<c:out value="${discuss.title }"></c:out>
				</a>
			</td>
			<td name="taskTD5"><c:out value="${discuss.author }"></c:out></td>
			<td name="taskTD6">
				<label>
					<c:out value="${discuss.typeName }"></c:out>
				</label>
			</td>
			<td name="taskTD7"><c:out value="${discuss.createTime }"></c:out></td>
			<td name="taskTD8">
				<a id="editDiscuss" name='editDiscuss' href="javascript:void(0)">
					<span class="glyphicon glyphicon-edit"></span>
				</a>
			</td>
			<td name="taskTD9">
				<a id="deleteDiscuss" name='deleteDiscuss' href="javascript:void(0)">
				<span class="glyphicon glyphicon-trash"></span>
				</a>
			</td>
			<td name="taskTD10" class="hide"><c:out value="${discuss.content }"></c:out></td>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript">
$(document).ready(function(data) {
	$("td[name='taskTD6']").each(function(){
		var type = $(this).parent("tr").children("td[name='taskTD1']").text().trim();
		switch(type){
		case "1":
			$(this).children("label").addClass("label label-danger");
			break;
		case "2":
			$(this).children("label").addClass("label label-warning");
			break;
		case "3":
			$(this).children("label").addClass("label label-success");
			break;
		case "4":
			$(this).children("label").addClass("label label-info");
			break;
		case "0":
			$(this).children("label").addClass("label label-primary");
			break;
		}
	});
	
	
	/* 显示写字板内容 */
	$("a[name='title_a']").click(function(e){
		var title = $(this).text().trim();
		var content = $(this).parent("td").parent("tr").children("td[name='taskTD10']").text().trim();
		content = decodeURIComponent(content);
		$("#showContent").modal('show');
		$("#show_title").text(title);
		$("#show_content").html(content);
	});
	
	/* 编辑 */
	$("a[name='editDiscuss']").click(function(){
		var discussId = $(this).parent("td[name='taskTD8']").parent("tr").children("td[name='taskTD0']").text().trim();
		var title = $(this).parent("td[name='taskTD8']").parent("tr").children("td[name='taskTD4']").text().trim();
		var type = $(this).parent("td[name='taskTD8']").parent("tr").children("td[name='taskTD1']").text().trim();
		var content = $(this).parent("td[name='taskTD8']").parent("tr").children("td[name='taskTD10']").text().trim();
		$("#editDiscussModal").modal('show');
		content = decodeURIComponent(content);
		$('#edit_content').code(content);
		$('#edit_discuss_title').val(title);
		$('#edit_discuss_type').val(type);
		$('#edit_discuss_id').val(discussId);
	});

	$("a[name='deleteDiscuss']").click(function(){
		var discussId = $(this).parent("td[name='taskTD9']").parent("tr").children("td[name='taskTD0']").text().trim();
		$("#delete_discuss_modal").modal('show');
		$("#delete_discuss").val(discussId);
	});
});

</script>