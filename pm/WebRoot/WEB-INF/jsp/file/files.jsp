<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="col-xs-12">
	<table class="table">
		<tr>
			<th>#</th>
			<th>文件</th>
			<th>类型</th>
			<th>所属</th>
			<th>上传时间</th>
			<th>上传者</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${resources }" var="resource">
			<tr>
				<td name="taskTD0" class="hide"><c:out value="${resource.id }"></c:out></td>
				<td name="taskTD1" class="hide"><c:out value="${resource.size }"></c:out></td>
				<td name="taskTD2"><c:out value="${resource.sequence }"></c:out></td>
				<td name="taskTD3">
					<a name="down" href="javascript:void(0)" data-toggle="tooltip" data-placement="top" title="大小：${resource.size } KB">
						<c:out value="${resource.name }"></c:out>
					</a>
					<script type="text/javascript">
						$("a[name='down']").tooltip('toggle');
					</script>
				</td>
				<td name="taskTD4"><c:out value="${resource.format }"></c:out></td>
				<td name="taskTD5"><c:out value="${resource.typeName }"></c:out></td>
				<td name="taskTD6"><c:out value="${resource.upTime }"></c:out></td>
				<td name="taskTD7"><c:out value="${resource.uploader }"></c:out></td>
				<td name="taskTD8"><!-- 文件下载 -->
					<a id="previewResource" name='previewResource' href="javascript:void(0)">
						<span class="glyphicon glyphicon-cloud-download"></span>
					</a>
				</td>
				<td name="taskTD9">
					<a id="deleteResource" name='deleteResource' href="javascript:void(0)"> 
					<span class="glyphicon glyphicon-trash"></span>
					</a>
				</td>
				<td name="taskTD10" class="hide"><c:out value="${resource.type }"></c:out></td>
				<td name="taskTD11" class="hide"><c:out value="${resource.path }"></c:out></td>
				<td name="taskTD12" class="hide"><c:out value="${resource.typeName }"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	<script type="text/javascript">
		$(document).ready(function(data) {
			$("td[name='taskTD10']").each(function(){
				var typeName = $(this).parent("tr").children("td[name='taskTD12']").text().trim();
				var type = $(this).text().trim();
				var cor = "label label-default";
				switch (type) {
				case "0":
					cor = "label label-default";
					break;
				case "1":
					cor = "label label-info";
					break;
				case "2":
					cor = "label label-success";
					break;
				case "3":
					cor = "label label-warning";
					break;
				case "4":
					cor = "label label-danger";
					break;
				case "5":
					cor = "label label-primary";
					break;
				default:
					cor = "";
					break;
				}
				$(this).parent("tr").children("td[name='taskTD5']");
				$(this).parent("tr").children("td[name='taskTD5']").html(
						"<span class='"+cor+"'>" + typeName
								+ "</span><br>");
			});
			$("a[name='down']").click(function(){
				var path = $(this).parent("td[name='taskTD3']").parent("tr").children("td[name='taskTD11']").text().trim();
				window.location.href="resourceController/download?path="+path;
			});
			
			// 文件下载
			$("a[name='previewResource']").click(function() {
				var path = $(this).parent("td[name='taskTD8']").parent("tr").children("td[name='taskTD11']").text().trim();
				window.location.href="resourceController/download?path="+path;
			});
			
			$("a[name='deleteResource']").click(function() {
				var resource_id = $(this).parent("td[name='taskTD9']").parent("tr").children("td[name='taskTD0']").text().trim();
				var path = $(this).parent("td[name='taskTD9']").parent("tr").children("td[name='taskTD11']").text().trim();
				$("#del_resource_id").val(resource_id);
				$("#del_path").val(path);
				$("#deleteModal").modal("show");
			});
		});
	</script>
</div>



