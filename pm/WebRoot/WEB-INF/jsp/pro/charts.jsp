<%@ page import="com.icker.pm.pojo.User"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	User user = (User) request.getSession().getAttribute("user");
%>
<div class="row">
	<div class="col-sm-6">
		<div id="total" style="height:200px;"></div>
		<script type="text/javascript">
			$(function() {
				var proId = $("#hiddenProId").val();
				$.ajax({
					url : "projectController/totalCharts",
					data : "id=" + proId,
					type : "post",
					dataType : "json",
					success : function(data) {
						showTotal(eval(data));
					},
					error : function(data) {
						alert("error" + data);
					}
				});

				function showTotal(data) {
					$('#total').highcharts({
						chart : {
							type : 'column'
						},
						title : {
							text : '总统计柱状图'
						},
						subtitle : {
							text : '项目相关任务、里程碑、文件、写字板总数'
						},
						xAxis : {
							categories : [ '项目成员', '任务', '里程碑',
									'资源', '写字板' ]
						},
						yAxis : {
							min : 0,
							title : {
								text : '总数(个)'
							}
						},
						tooltip : {
							headerFormat : '<span style="font-size:10px">{point.key}</span><br>',
							shared : true,
							useHTML : true
						},
						plotOptions : {
							column : {
								pointPadding : 0.2,
								borderWidth : 0
							}
						},
						series : data
					});
				}
			});
		</script>
	</div>
	<div class="col-xs-6">
		<div id="totalPie" style="height:200px;"></div>
		<script type="text/javascript">
			$(document).ready(function() {
				var proId = $("#hiddenProId").val();
				$.ajax({
					url : "projectController/totalPieCharts",
					data : "id=" + proId,
					type : "post",
					dataType : "json",
					success : function(data) {
						showTotalPie(eval(data));
					},
					error : function(data) {
						alert("error" + data);
					}
				});
				
				function showTotalPie(data) {
					$('#totalPie').highcharts({
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false
						},
						title : {
							text : '总统计饼状图'
						},
						subtitle : {
							text : '项目相关任务、里程碑、文件、写字板总数'
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}{point.key}%</b>'
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : false
								},
								showInLegend : false
							}
						},
						series : [ {
							type : 'pie',
							name : '比例',
							data : data
						} ]
					});
				}
			});
		</script>
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