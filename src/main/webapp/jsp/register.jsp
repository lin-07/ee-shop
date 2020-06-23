<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<%@include file="/jsp/base/head.jsp"%>
	</head>
	<body>
		<%@include file="/jsp/base/top.jsp"%>
		<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/image/regist_bg.jpg');">
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
					<font>会员注册</font>USER REGISTER
					<form action="${pageContext.request.contextPath}/UserServlet?method=register" method="post" class="form-horizontal" style="margin-top:5px;">
						 <div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
							  <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
							</div>
						  </div>
						   <div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
							  <input type="password" class="form-control" name="password" id="inputPassword3" placeholder="请输入密码">
							</div>
						  </div>
						   <div class="form-group">
							<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-6">
							  <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
							</div>
						  </div>
						  <div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-6">
							  <input type="email" name="email" class="form-control" id="inputEmail3" placeholder="Email">
							</div>
						  </div>
						 <div class="form-group">
							<label for="usercaption" class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-6">
							  <input type="text" name="name" class="form-control" id="usercaption" placeholder="请输入姓名">
							</div>
						  </div>
						  <div class="form-group opt">
						  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						  <div class="col-sm-6">
							<label class="radio-inline">
						  <input type="radio" name="sex" id="inlineRadio1" value="1"> 男
						</label>
						<label class="radio-inline">
						  <input type="radio" name="sex" id="inlineRadio2" value="0"> 女
						</label>
						</div>
						  </div>
						  <div class="form-group">
							<label for="date" class="col-sm-2 control-label">出生日期</label>
							<div class="col-sm-6">
							  <input type="date" name="birthday" class="form-control"  >
							</div>
						  </div>

						  <div class="form-group">
							<label for="date" class="col-sm-2 control-label">验证码</label>
							<div class="col-sm-3">
							  <input type="text" class="form-control"  >

							</div>
							<div class="col-sm-2">
							<img src="${pageContext.request.contextPath}/image/captcha.jhtml"/>
							</div>

						  </div>

						  <div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
							  <input type="submit"  width="100" value="注册" name="submit" border="0"
								style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
								height:35px;width:100px;color:white;">
							</div>
						  </div>
						</form>
				</div>
				<div class="col-md-2"></div>
			</div>
		</div>
		<%@include file="/jsp/base/footer.jsp"%>
	</body>
</html>





