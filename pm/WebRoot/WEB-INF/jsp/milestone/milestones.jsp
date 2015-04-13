<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<table class="table table-striped table-hover">
	<tr>
		<th></th>
		<th>#</th>
		<th>名称</th>
		<th>负责人</th>
		<th>进度</th>
		<th>截止时间</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${milestones }" var="milestone">
		<tr>
			<td name="TD0" class="hide"><c:out value="${milestone.id }"></c:out></td>
			<td name="TD1"><input type="checkbox"></td>
			<td name="TD2"><c:out value="${milestone.sequence }"></c:out></td>
			<td name="TD3"><c:out value="${milestone.name }"></c:out></td>
			<td name="TD4"><c:out value="${milestone.performer }"></c:out></td>
			<td name="TD5" style="width: 150px;">
				<div class="progress" name="sliderBar">
					<div class="progress-bar progress-bar-striped active"
						role="progressbar" aria-valuenow="${milestone.progress }"
						aria-valuemin="0" aria-valuemax="100"
						style="width: ${milestone.progress }%">
						<c:out value="${milestone.progress }%"></c:out>
					</div>
				</div>
			</td>
			<td name="TD6"><c:out value="${milestone.endDate }"></c:out></td>
			<td name="TD7"><a id="editmilestone" name='editmilestone'
				href="javascript:void(0)"><span class="glyphicon glyphicon-edit"></span></a>
			</td>
			<td name="TD8"><a id="deletemilestone" name='deletemilestone'
				href="javascript:void(0)"><span
					class="glyphicon glyphicon-trash"></span></a></td>
			<td name="TD9" class="hide"><c:out value="${milestone.progress }"></c:out></td>
			<td name="TD10" class="hide"><c:out value="${milestone.performerId }"></c:out></td>
			<td name="TD11" class="hide"><c:out value="${milestone.status }"></c:out></td>
		<tr>
	</c:forEach>
</table>
<script type="text/javascript">
	/* 点击编辑里程碑 */
	$("a[name='editmilestone']").click(function(e) {
		var milestoneId = $(this).parent("td").parent("tr").children(
				"td[name='TD0']").text().trim();
		var performerId = $(this).parent("td").parent("tr").children(
				"td[name='TD10']").text().trim();
		var name = $(this).parent("td").parent("tr").children(
				"td[name='TD3']").text().trim();
		var endDate = $(this).parent("td").parent("tr").children(
				"td[name='TD6']").text().trim();
		var progress = $(this).parent("td").parent("tr").children(
				"td[name='TD9']").text().trim();
		var status = $(this).parent("td").parent("tr").children(
		"td[name='TD11']").text().trim();
		
		$("#editmilestoneModal").modal('show');
		$("#editmilestoneModal").find("#mile_status").val(status);
		$("#editmilestoneModal").find("#editMilestoneId").val(milestoneId);
		$("#editmilestoneModal").find("#milestone_name").val(name);
		$("#editmilestoneModal").find("#user_name").val(performerId);
		$("#editmilestoneModal").find("#end_date").val(endDate);
		$("#editmilestoneModal").find("#mile_progress").children("div").attr(
				"aria-valuenow", progress);
		$("#editmilestoneModal").find("#mile_progress").children(
				"div[role='progressbar']").attr("aria-valuenow",
				progress).html(progress + "%");
		$("#editmilestoneModal").find("#mile_progress").children(
				"div[role='progressbar']")
				.css("width", +progress + "%");
	});
	
	// 点击删除任务
	$("a[name='deletemilestone']").click(function(e) {
		var milestoneId = $(this).parent("td").parent("tr").children("td[name='TD0']").text().trim();
		var status = $(this).parent("td").parent("tr").children("td[name='TD11']").text().trim();
		$("#milestone_id_hidden").val(milestoneId);
		$("#milestone_status_hidden").val(status);
		$("#deleteModal").modal('show');
	});
</script>


