<%@ page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div class="row">
	<div class="col-xs-12" id="unfinished">
		<jsp:include page="milestones.jsp"></jsp:include>
	</div>
</div>

<div class="row" id="accordion_row">
	<div class="col-xs-12">
		<div class="panel-group" id="accordion" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-success">
				<div class="panel-heading" role="tab" id="headingOne">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="accordion"
							href="#collapseOne" aria-expanded="true"
							aria-controls="collapseOne"> 已完成的里程碑 </a>
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
	</div>
</div>


<!-- 日历开始 -->
<div class="row" id="calendar_div">
	<div class="col-xs-12">
		<div class="panel-group" id="accordion_calendar" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-info">
				<div class="panel-heading" role="tab" id="headingCalendar">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion_calendar"
							href="#collapse_calendar" aria-expanded="true"
							aria-controls="collapse"> 日历 </a>
					</h4>
				</div>
				<div id="collapse_calendar" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="headingCalendar">
					<div class="panel-body">
						<jsp:include page="../common/calendar.jsp"></jsp:include>
					</div>
				</div>
			</div>
		</div>
		<!-- collapse结束 -->
	</div>
	<!-- 一列 -->
</div>
<!-- 一行 -->

<c:if test="${finished.size() == 0 }">
	<script type="text/javascript">
		$("#accordion_row").addClass("hidden");
	</script>
</c:if>
