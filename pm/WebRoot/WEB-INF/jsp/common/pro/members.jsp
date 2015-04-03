<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="col-md-1"
	style="background-color:#ffffff; border-radius:15px;">
	<div style="padding-top:10px;" id="proMem">
		<b>项目成员</b>
		<hr>
		<!-- js控制放置成员 -->
		<hr>
	</div>
</div>
<script>
	$(document).ready(function() {
		var proId = $("#hiddenProId").val();
		$.ajax({
			url : "projectController/projectMember",
			data : "id=" + proId,
			success : function(data) {
				for (var i = 0; i < data.length; i++) {
					var a = Math.ceil(Math.random() * 6);
					var style = "label label-default";
					switch (a) {
					case 1:
						style = "label label-default";
						break;
					case 2:
						style = "label label-primary";
						break;
					case 3:
						style = "label label-success";
						break;
					case 4:
						style = "label label-info";
						break;
					case 5:
						style = "label label-warning";
						break;
					case 6:
						style = "label label-danger";
						break;
					}
					$("#proMem").children("hr:last").before(
							"<span class='"+style+"'>" + data[i].name
									+ "</span><br>");
				}
			}
		});
	});
</script>