<%@page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<table class="table table-striped table-hover">
	<tr>
		<th>文件</th>
		<th>操作</th>
	</tr>
	<c:forEach items="${resources }" var="resource">
		<tr>
			<td><input type="hidden" id="addressFile"
				value="${resource.getAddress() }"> <a
				href="javascript:void(0)" name="fileDown">${resource.getName() }</a>
			</td>
			<td>
				<button class="btn btn-link">
					<span class="glyphicon glyphicon glyphicon-edit"></span>
				</button> <input type="hidden" id="fileId" value="${resource.getId() }">
				<button id="deleteFile" name='deleteFile' class="btn btn-link">
					<span class="glyphicon glyphicon-trash"></span>
				</button>
			</td>
		</tr>
	</c:forEach>

</table>

<script>
	$(document).ready(
			function() {
				//点击下载
				$("a[name='fileDown']").click(
						function(e) {
							var address = $(this).prev("#addressFile").val();
							var filename = $(this).text();
							console.log(address + "\t" + filename);
							$(this)
									.attr(
											"href",
											"file/FileDownloadServlet?address="
													+ address + "&filename="
													+ filename);
						});
				//点击删除
				$("button[name='deleteFile']").click(function(e) {
					var fileId = $(this).prev("#fileId").val();
					$.ajax({
						url : "file/FileDeleteServlet",
						data : "id=" + fileId,
						success : function(data) {
							$("#fileList").html(data);
						}
					});
				});
			});
</script>
