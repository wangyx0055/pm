<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 登陆模态框 -->
<div id="loginModal" class="modal fade bs-example-modal-sm"
	tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">用户登录</h4>
			</div>
			<div class="modal-body">
				<form role="form" id="loginForm">
					<div class="form-group">
						<label class="control-label">账号：</label> <input id="email"
							type="email" placeholder="电子邮箱" class="form-control"> <label
							class="control-label">密码：</label> <input id="password"
							type="password" placeholder="密码" class="form-control"> <label
							class="control-label">验证码：</label>
						<div class="clearfix"></div>
						<input id="tokenCode" type="text" class="form-control pull-left"
							style="width:60%;" placeholder="验证码" required> <a
							id="token" href="javascript:token()"> <img id="tokenImg"
							alt="刷新" style="border-radius:5px;" src="user/ImgTokenServlet">
						</a>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<input type="button" id="loginBtn" name="loginBtn"
					class="btn btn-info" form="loginForm" value="提交">
			</div>
		</div>
	</div>
</div>
<!-- 登陆模态框结束 -->

<!-- 注册模态框 -->
<div id="registerModal" class="modal fade" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">用户注册</h4>
			</div>
			<div class="modal-body">
				<form id="registerForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-xs-3 control-label">邮箱</label>
						<div class="input-group col-xs-8">
							<span class="input-group-addon">@</span> <input id="email"
								type="email" placeholder="Enter email" class="form-control"
								required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label">昵称</label>
						<div class="input-group col-xs-8">
							<span class="input-group-addon">name</span> <input id="name"
								type="text" placeholder="Enter name" class="form-control"
								required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label">密码</label>
						<div class="input-group col-xs-8">
							<span class="input-group-addon">pass</span> <input id="password"
								type="password" placeholder="Enter password"
								class="form-control" required>
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 control-label">确认密码</label>
						<div class="input-group col-xs-8">
							<span class="input-group-addon">rpass</span> <input
								id="rpassword" type="password" placeholder="Comfirm password"
								class="form-control" required>
						</div>
					</div>
				</form>
				<span id="errMsg"></span>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<input id="subRegister" type="button" name="subRegister"
					class="btn btn-info" form="registerForm" value="注册">
			</div>
		</div>
	</div>
</div>
<!-- 注册模态框结束 -->

<script type="text/javascript">
	function token() {
		document.getElementById("tokenImg").src = "user/ImgTokenServlet?date="
				+ new Date();
	}
	//登录提交
	$("#loginBtn").click(function() {
		$.post("user/UserLoginServlet", {
			email : $("#email").val(),
			password : $("#password").val(),
			tokenCode : $("#tokenCode").val()
		}, function(data) {
			if (data.respMsg == "000") {
				location.href = "common/WelcomeServlet";
			} else if (data.respMsg == "111") {
				$("#error").html(data.errMsg);
			}
		}, "json");
	});

	//注册
	$(function() {
		$("#subRegister").click(
				function() {
					$.ajax({
						type : "post",
						url : "user/UserRegisterServlet",
						data : "email=" + $("#email").val() + "&name="
								+ $("#name").val() + "&password="
								+ $("#password").val() + "&rpassword="
								+ $("#rpassword").val(),
						success : function(data) {
							if (data.respMsg == "000") {
								$("#registerModal").modal("hide");
								location.href = "jsp/login.jsp";
							} else {
								$("#errMsg").html(data.errMsg);
							}
						},
						dataType : "json"
					});
				});
	});
	function doRegister() {
		$("#registerModal").modal("show");
	}
	function doLogin() {
		$("#loginModal").modal("show");
	}
</script>

