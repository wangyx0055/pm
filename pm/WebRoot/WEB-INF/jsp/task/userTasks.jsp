<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div id="unfinished">
	<jsp:include page="unfinished.jsp"></jsp:include>
</div>


<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true">
	<div class="panel panel-success">
		<div class="panel-heading" role="tab" id="headingOne">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="accordion"
					href="#collapseOne" aria-expanded="true"
					aria-controls="collapseOne"> 已完成的任务 </a>
			</h4>
		</div>
		<div id="collapseOne" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="headingOne">
			<div class="panel-body" id="finished">
				<jsp:include page="finished.jsp"></jsp:include>
			</div>
		</div>
	</div>
</div>
<c:if test="${completed.size() == 0 }">
	<script type="text/javascript">
		$("#accordion").addClass("hidden");
	</script>
</c:if>

