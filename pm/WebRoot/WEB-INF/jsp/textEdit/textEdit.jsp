<%@page import="com.icker.vo.UserProjectVO"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
  	<title>项目管理系统--写字板</title>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- Bootstrap -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
        <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="././js/jquery.js"></script>
    
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="keywords" content="opensource rich wysiwyg text editor jquery bootstrap execCommand html5" />
    <meta name="description" content="This tiny jQuery Bootstrap WYSIWYG plugin turns any DIV into a HTML5 rich text editor" />
    <link rel="apple-touch-icon" href="//mindmup.s3.amazonaws.com/lib/img/apple-touch-icon.png" />
    <link rel="shortcut icon" href="http://mindmup.s3.amazonaws.com/lib/img/favicon.ico" >
    <link href="jsp/textEdit/external/google-code-prettify/prettify.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.no-icons.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-responsive.min.css" rel="stylesheet">
	<link href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css" rel="stylesheet">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
	<script src="jsp/textEdit/external/jquery.hotkeys.js"></script>
	
	
	<!-- 自己加的 -->
	<link href="././css/bootstrap.css" rel="stylesheet">
    <link href="././css/backstage.css" rel="stylesheet">
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="././js/bootstrap.js"></script>
    
    <!-- <script src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js"></script> -->
    <script src="jsp/textEdit/external/google-code-prettify/prettify.js"></script>
	<link href="jsp/textEdit/index.css" rel="stylesheet">
    <script src="jsp/textEdit/bootstrap-wysiwyg.js"></script>
    
  </head>
  
  <body>
  	<div class="container container-own">
		<jsp:include page="/jsp/common/header.jsp"></jsp:include>
		
	    <jsp:include page="/jsp/common/sideBar.jsp"></jsp:include>
	    <h2><i>${projects.name }</i><span id="showTime2" class="label pull-right"></span><span id="showTime" class="label label-primary pull-right"></span></h2>
	    <div class="line-spacing"></div>
	    
	    <!-- 内容区 -->	
	    <div class="row">
	    	<div class="col-md-11">
		        <div class="panel panel-default">
		            <ul class="nav nav-pills" role="tablist">
					  <li role="presentation"><a href="javascript:void(0)" id="proDetails">项目概览</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="msgEdition">消息版</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="milepost">里程碑</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="taskList">任务列表</a></li>
					  <li role="presentation" class="active"><a href="javascript:void(0)" id="writeBoard">写字板</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="workHours">工时</a></li>
					  <li role="presentation"><a href="javascript:void(0)" id="files">文件</a></li>
					</ul>
		        </div>
		        
		        <!-- 写字板内容 -->
		        <div class="jumbotron" style="background-color: #ffffff">
					<jsp:include page="toolbar.jsp"></jsp:include>
					
					<div id="editor">
				    	
				    </div>
				    <div id="alerts"></div>
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
    			
    			//文本编辑器
    			function initToolbarBootstrapBindings() {
    				var fonts = [ 'Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
    						'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact',
    						'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
    						'Times New Roman', 'Verdana' ], fontTarget = $(
    						'[title=Font]').siblings('.dropdown-menu');
    				$.each(
    					fonts,
    					function(idx, fontName) {
    						fontTarget.append($('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'
    							+ fontName + '</a></li>'));
    					});
    				$('a[title]').tooltip({
    					container : 'body'
    				});
    				$('.dropdown-menu input').click(function() {
    					return false;
    				}).change(
    						function() {
    							$(this).parent('.dropdown-menu').siblings(
    									'.dropdown-toggle').dropdown('toggle');
    						}).keydown('esc', function() {
    					this.value = '';
    					$(this).change();
    				});

    				$('[data-role=magic-overlay]').each(
    						function() {
    							var overlay = $(this), target = $(overlay.data('target'));
    							overlay.css('opacity', 0).css('position', 'absolute')
    									.offset(target.offset()).width(
    											target.outerWidth()).height(
    											target.outerHeight());
    						});
    				if ("onwebkitspeechchange" in document.createElement("input")) {
    					var editorOffset = $('#editor').offset();
    					$('#voiceBtn').css('position', 'absolute').offset({
    						top : editorOffset.top,
    						left : editorOffset.left + $('#editor').innerWidth() - 35
    					});
    				} else {
    					$('#voiceBtn').hide();
    				}
    			}
    			;
    			function showErrorAlert(reason, detail) {
    				var msg = '';
    				if (reason === 'unsupported-file-type') {
    					msg = "Unsupported format " + detail;
    				} else {
    					console.log("error uploading file", reason, detail);
    				}
    				$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
    								+ '<strong>File upload error</strong> '
    								+ msg
    								+ ' </div>').prependTo('#alerts');
    			}
    			;
    			initToolbarBootstrapBindings();
    			$('#editor').wysiwyg({
    				fileUploadError : showErrorAlert
    			});
    			window.prettyPrint && prettyPrint();
    			
    		});
    	</script>
    </div>
  </body>
</html>
