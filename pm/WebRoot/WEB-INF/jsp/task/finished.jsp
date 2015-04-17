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
		<th>完成时间</th>
	</tr>
	<c:forEach items="${completed }" var="task">
		<tr>
			<td name="taskTD0" class="hide"><c:out value="${task.id }"></c:out></td>
			<td name="taskTD1">
				<input name="check" checked="checked" type="checkbox">
			</td>
			<td name="taskTD2"><c:out value="${task.sequence }"></c:out></td>
			<td name="taskTD3">
				<c:if test="${task.priority ==1}">
		        	<span name="tName" class="label label-danger" data-container="body" data-toggle="popover" data-placement="right" title="细节描述" data-content="${task.description }" style="cursor: pointer;"><c:out value="${task.name }"></c:out></span>
			    </c:if>
			    <c:if test="${task.priority ==2}">
			        <span name="tName" class="label label-warning" data-container="body" data-toggle="popover" data-placement="right" title="细节描述" data-content="${task.description }" style="cursor: pointer;"><c:out value="${task.name }"></c:out></span>
			    </c:if>
			    <c:if test="${task.priority ==3}">
			        <span name="tName" class="label label-info" data-container="body" data-toggle="popover" data-placement="right" title="细节描述" data-content="${task.description }" style="cursor: pointer;"><c:out value="${task.name }"></c:out></span>
			    </c:if>
			    <script>
			    	$(function (){
			        	$("[data-toggle='popover']").popover().on("mouseenter", function () {
		                    var _this = this;
		                    $(this).popover("show");
		                    $(this).siblings(".popover").on("mouseleave", function () {
		                        $(_this).popover('hide');
		                    });
		                }).on("mouseleave", function () {
		                    var _this = this;
		                    setTimeout(function () {
		                        if (!$(".popover:hover").length) {
		                            $(_this).popover("hide");
		                        }
		                    }, 100);
		                });
	     			});
	   			</script>
		    </td>
			<td name="taskTD4"><c:out value="${task.performer }"></c:out></td>
			<td name="taskTD5"><c:out value="${task.finishDate }"></c:out></td>
		<tr>
	</c:forEach>
</table>
<script>
	$("input[name='check']").change(
		function(e) {
			var taskId = $(this).parent("td").parent("tr").children(
					"td[name='taskTD0']").text().trim();
			var proId = $("#hiddenProId").val();
			/* 当前被选中 */
			if($(this)[0].checked) {
				$.ajax({
					url : "taskController/completeTask",
					data : "id=" + taskId + "&proId=" + proId,
					type : "post",
					dataType : "html",
					success : function(data) {
						$("#infoTable").empty();
						$("#infoTable").html(data);
					},
					error : function(data) {
						alert("error" + data);
					}
				});
			} else {	/* 当前被释放 */
				$.ajax({
					url : "taskController/releaseTask",
					data : "id=" + taskId + "&proId=" + proId,
					type : "post",
					dataType : "html",
					success : function(data) {
						$("#infoTable").empty();
						$("#infoTable").html(data);
					},
					error : function(data) {
						alert("error" + data);
					}
				});
			}
		});
</script>
