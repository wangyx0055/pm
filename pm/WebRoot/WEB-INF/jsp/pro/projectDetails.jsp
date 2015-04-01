<%@page import="com.icker.pm.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<title>项目管理系统--项目概览</title>
    <base href="<%=basePath%>">
	
	<!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link href="styles/bootstrap.css" rel="stylesheet">
	<link href="styles/backstage.css" rel="stylesheet">
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap/js/bootstrap.js"></script>
	<script src="js/highcharts/highcharts.js"></script>
	<script src="js/highcharts/exporting.js"></script>
    
  </head>
  
  <body>
  	<div class="container container-own">
		<jsp:include page="../common/header.jsp"></jsp:include>
	    <jsp:include page="../common/sideBar.jsp"></jsp:include>
	    <h2><i>${project.name }</i><span id="showTime2" class="label pull-right"></span><span id="showTime" class="label label-primary pull-right"></span></h2>
	    <div class="line-spacing"></div>
	    
	    <!-- 内容区 -->	
	    <div class="row">
	    	<div class="col-md-11">
		        <div class="panel panel-default">
		            <ul class="nav nav-pills" role="tablist">
					  <li role="presentation" class="active"><a href="javascript:void(0)" id="proDetails">项目概览</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="msgEdition">消息版</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="milepost">里程碑</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="taskList">任务列表</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="writeBoard">写字板</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="workHours">工时</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="files">文件</a></li>
					</ul>
		        </div>
		        <div class="jumbotron" style="background-color: #ffffff">
				 <label class="label label-success" style="font-size:24px;">${project.name }</label><hr>
				 ${project.description }
				</div>
	        </div>
	        <div class="col-md-1" style="background-color:#ffffff; border-radius:15px;">
	        	<div style="padding-top:10px;" id="proMem">
                	<b>项目成员</b>
                	<hr>
                		<!-- js控制放置成员 -->
                    <hr>
                </div>
	        </div>
        </div>
    	<!-- 内容区结束 -->
    	
    	<input id="hiddenProId" type="hidden" value="${project.id }">
    	<script type="text/javascript">
    		$(document).ready(function(){
    			var proId = $("#hiddenProId").val();
    			
    			//项目概览
    			$("#proDetails").click(function(e){
    				$(this).attr("href","projectController/ProjectDetails?id="+proId);
    			});
    			//文件
    			$("#files").click(function(e){
    				$(this).attr("href","jsp/pro/proFile.jsp");
    			});
    			//写字板
    			$("#writeBoard").click(function(e){
    				$(this).attr("href","jsp/textEdit/textEdit.jsp");
    			});
    			
    			$.ajax({
    				url:"projectController/projectMember",
    				data:"id="+proId,
    				success:function(data){
    					for(var i = 0; i < data.length; i++) {
    						var a = Math.ceil(Math.random()*6);
    						var style = "label label-default";
        					switch(a) {
        						case 1: style = "label label-default";break;
        						case 2: style = "label label-primary";break;
        						case 3: style = "label label-success";break;
        						case 4: style = "label label-info";break;
        						case 5: style = "label label-warning";break;
        						case 6: style = "label label-danger";break;
        					}
    						$("#proMem").children("hr:last").before("<span class='"+style+"'>"+data[i].name+"</span><br>");
    					}
    				}
    			});
    			
    			
    		});
    	</script>
    </div>
  </body>
</html>
























