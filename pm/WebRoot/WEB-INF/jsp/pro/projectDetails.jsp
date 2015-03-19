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
    <link href="././css/bootstrap.css" rel="stylesheet">
    <link href="././css/backstage.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="././js/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="././js/bootstrap.js"></script>
    
  </head>
  
  <body>
  	<div class="container container-own">
		<jsp:include page="<%=basePath %>/jsp/common/header.jsp"></jsp:include>
		
	    <jsp:include page="<%=basePath %>/jsp/common/sideBar.jsp"></jsp:include>
	    <h2><i>${projects.name }</i><span id="showTime2" class="label pull-right"></span><span id="showTime" class="label label-primary pull-right"></span></h2>
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
				 <label class="label label-success" style="font-size:24px;">${projects.name }</label><hr>
				 ${projects.describes }
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
    	
    	<input id="hiddenProId" type="hidden" value="${projects.id }">
    	<script type="text/javascript">
    		$(document).ready(function(){
    			var proId = $("#hiddenProId").val();
    			
    			//项目概览
    			$("#proDetails").click(function(e){
    				$(this).attr("href","project/ProjectDetailsServlet?proId="+proId);
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
    				url:"pro/ProjectMemberServlet",
    				data:"proId="+proId,
    				success:function(data){
    					$("#proMem").children("hr:last").before(data);
    				}
    			});
    			
    			
    		});
    	</script>
    </div>
  </body>
</html>
