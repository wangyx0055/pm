function showTotal(data) {
	$('#total').highcharts({
		chart : {
			type : 'column'
		},
		title : {
			text : '总统计报表'
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
		series : [ {
			name : '活动',
			data : [ 2, 5, 2, 6 ]
		} ]
	});
}