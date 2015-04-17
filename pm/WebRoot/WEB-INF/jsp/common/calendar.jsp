<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<link href="js/bootstrap-calendar/css/calendar.css" rel="stylesheet">
<script type="text/javascript"
	src="js/bootstrap-calendar/components/underscore/underscore-min.js"></script>
<script type="text/javascript"
	src="js/bootstrap-calendar/js/calendar.js"></script>
<script type="text/javascript"
	src="js/bootstrap-calendar/js/language/zh-CN.js"></script>
<script type="text/javascript"
	src="js/bootstrap-calendar/components/jstimezonedetect/jstz.min.js"></script>

</head>

<body>
	<div class="page-header">
		<span style="font-size: 24px;"></span>
		<div class="pull-right">
			<div class="btn-group">
				<button class="btn btn-primary" data-calendar-nav="prev"><<
					Prev</button>
				<button class="btn btn-default" data-calendar-nav="today">Today</button>
				<button class="btn btn-primary" data-calendar-nav="next">Next
					>></button>
			</div>
			<div class="btn-group">
				<button class="btn btn-warning" data-calendar-view="year">Year</button>
				<button class="btn btn-warning active" data-calendar-view="month">Month</button>
				<button class="btn btn-warning" data-calendar-view="week">Week</button>
				<button class="btn btn-warning" data-calendar-view="day">Day</button>
			</div>
		</div>
	</div>
	<div style="height:20px;"></div>
	<div id="calendar"></div>

	<div class="modal fade" id="events-modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h3 class="modal-title">事件</h3>
				</div>
				<input type="hidden" id="event_id_hidden">
				<div class="modal-body" style="height: 400px"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/bootstrap-calendar/js/app.js"></script>
</body>
</html>
