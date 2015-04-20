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
<div class="row">
	<!-- 各类型资源数量柱状图 -->
	<div class="col-sm-6">
		<div class="panel-group" id="accordion_resource" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-success">
				<div class="panel-heading" role="tab" id="heading_resource">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion_resource"
							href="#collapse_resource" aria-expanded="true"
							aria-controls="collapse"> 各类型资源数量柱状图 </a>
					</h4>
				</div>
				<div id="collapse_resource" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="heading_resource">
					<div class="panel-body" id="resourceCharts" style="height: 300px;">
					</div>
					
					<script type="text/javascript">
						$(document).ready(function(){
							findWeather();
							function findWeather() {
								
							    var cityUrl = 'http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js';
							    $.getScript(cityUrl, function(script, textStatus, jqXHR) {
							        var citytq = remote_ip_info.city ;// 获取城市
							        console.log(citytq);
							        citytq = "厦门";
							        var url = "http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&city=" + citytq + "&day=0&dfc=3";
							        $.ajax({
							            url : url,
							            dataType : "script",
							            scriptCharset : "gbk",
							            success : function(data) {
							            	console.log(window.SWther);
							                var _w = window.SWther.w[citytq][0];
							                var _f= _w.f1+"_0.png";
							                if(new Date().getHours() > 17){
							                     _f= _w.f2+"_1.png";                
							                }
							                var img = "<img width='64px' height='64px' src='http://i2.sinaimg.cn/dy/main/weather/weatherplugin/wthIco/20_20/" +_f
							                + "' />";
							                var tq = citytq + " " + img + " " + _w.s1 + " " + _w.t1 + "℃～" + _w.t2 + "℃  " + _w.d1 + _w.p1 + "级";
							                $('#resourceCharts').html(tq);
							            }
							        });
							    });
							}
						});
					</script>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 任务、里程碑完成度柱状图 -->
	<div class="col-sm-6">
		<div class="panel-group" id="accordion_task" role="tablist"
			aria-multiselectable="true">
			<div class="panel panel-success">
				<div class="panel-heading" role="tab" id="heading_task">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-parent="#accordion_task"
							href="#collapse_task" aria-expanded="true"
							aria-controls="collapse"> 任务、里程碑完成度柱形图 </a>
					</h4>
				</div>
				<div id="collapse_task" class="panel-collapse collapse in"
					role="tabpanel" aria-labelledby="heading_task">
					<div class="panel-body" id="taskHistogram" style="height: 300px;">
					</div>
					<script type="text/javascript">
						$(function() {
							var proId = $("#hiddenProId").val();
							$.ajax({
								url : "projectController/taskHistogram",
								data : "id=" + proId,
								type : "post",
								dataType : "json",
								success : function(data) {
									showFinishStatus(eval(data));
								},
								error : function(data) {
									alert("error" + data);
								}
							});
			
							function showFinishStatus(data) {
								$('#taskHistogram').highcharts({
									chart : {
										type : 'column'
									},
									title : {
										text : '完成度柱状图'
									},
									xAxis : {
										categories : [ '已完成', '未完成', '延期']
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