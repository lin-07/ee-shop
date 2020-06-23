<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<%@include file="/jsp/base/head.jsp"%>
	</head>
	<body>
		<%@include file="/jsp/base/top.jsp"%>
		<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('${pageContext.request.contextPath}/images/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>
			<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN
				<div>&nbsp;${msg}</div>
				<form class="form-horizontal" action="${pageContext.request.contextPath}/UserServlet" method="post">
					<input type="hidden" name="method" value="login">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
						  <input type="text" name="username" value="${cookie.rememberUserName.value}" class="form-control" id="username" placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
						  <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
						</div>
				  	</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
						  <input type="text" name="verificationCode" class="form-control" style="width: 110px" id="inputPassword3" placeholder="请输入验证码">
						</div>
						<div class="col-sm-3" style="margin-left: 10px;margin-top: 3px">
						  <img id="codeImg" src="${pageContext.request.contextPath}/CodeServlet" onclick="toggleCode()"/>
						</div>
						<script>
							function toggleCode() {
								$("#codeImg").attr("src","${pageContext.request.contextPath}/CodeServlet?date="+new Date());
							}
						</script>
				  	</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						  <div class="checkbox">
							<label>
							  <input type="checkbox" name="autoLogin" value="1"> 自动登录
							</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<label>
							  <input type="checkbox" name="remember" value="1" ${not empty cookie.rememberUserName?"checked":""}> 记住用户名
							</label>
						  </div>
						</div>
				  	</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit"  width="100" value="登录" name="submit" border="0"
							style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
							height:35px;width:100px;color:white;">
						</div>
					</div>
				</form>
		</div>
			</div>
		</div>
		</div>
		<%@include file="/jsp/base/footer.jsp"%>
	</body>
</html>