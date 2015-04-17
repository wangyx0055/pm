<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 文件上传 -->
	<div class="col-xs-12">
		<input type="hidden" name="type_hidden" value="${type }">
		<input id="input_files" name="resources" type="file" multiple class="file-loading">
		<script>
			$(document).ready(function(){
				var proId = $("#hiddenProId").val();
				var filetype = $("input[name='type_hidden']").val();
				$("#input_files").fileinput({
					uploadUrl: "resourceController/batchUpload",
					uploadAsync: true,
					maxFileCount: 5,
					uploadExtraData: {id: proId, type:filetype}
				});
				$("#input_files").on('filebatchpreupload', function(event, data, jqXHR) {
					var type = $("#resource_type").val();
					data.extra.type=type;
				});
				$('#input_files').on('filebatchuploadcomplete', function(event, files, extra) {
					$("#input_files").fileinput('reset');
					$("#collapseExample").collapse('hide');
				});
				$("#input_files").on('fileuploaded', function(event, data, previewId, index) {
				    var proId = $("#hiddenProId").val();
					$.ajax({
		 				url: "resourceController/allResources",
		  				data: "id="+proId,
		  				type: "post",
		  				dataType: "html",
		  				success: function(data) {
		  					$("#info_table").empty();
		  					$("#info_table").html(data);
		  				},
		  				error: function(data) {
		  					alert("error"+data);
		  				}
		  			});
				});
			});
		</script>
	</div>


<%-- <!-- 文件上传 -->
<div class="col-xs-12">
	<input type="hidden" name="type_hidden" value="${type }">
	<input id="input_files" name="resources" type="file" multiple
		class="file-loading">
	<script>
		$(document).ready(function() {
			var proId = $("#hiddenProId").val();
			var filetype = $("input[name='type_hidden']").val();
			$("#input_files").fileinput({
				uploadUrl : "resourceController/batchUpload",
				uploadAsync : false,
				maxFileCount : 5,
				uploadExtraData : {
					id : proId,
					type : filetype
				}
			});
		});
	</script>
</div> --%>




















