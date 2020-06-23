<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<%@include file="base/head.jsp"%>
	</head>
	<body>
		<%@include file="base/top.jsp"%>
		<c:if test="${empty cart.cartItems}">
			<h1>购物车空空如也~</h1>
		</c:if>
		<c:if test="${not empty cart.cartItems}">
			<div class="container">
				<div class="row">

					<div style="margin:0 auto; margin-top:10px;width:950px;">
						<strong style="font-size:16px;margin:5px 0;">订单详情</strong>
						<table class="table table-bordered">
							<tbody>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
							<c:forEach items="${cart.cartItems}" var="entry">
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${pageContext.request.contextPath}/${entry.value.product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank">${entry.value.product.pname}</a>
									</td>
									<td width="20%">
										￥${entry.value.product.shop_price}
									</td>
									<td width="10%">
											${entry.value.count}
									</td>
									<td width="15%">
										<span class="subtotal">￥${entry.value.subPrice}</span>
									</td>
									<td>
										<a href="javascript:void(0)" onclick="deleteItem('${entry.key}')" class="delete">删除</a>
									</td>
								</tr>
							</c:forEach>
							<script>
								function deleteItem(pid) {
									if(confirm("确定从购物车中移除这个商品吗？")){
										location.href = "${pageContext.request.contextPath}/CartServlet?method=removeCart&pid="+pid;
									}
								}
								function clearCart() {
									if(confirm("确定清空购物车吗？")){
										location.href = "${pageContext.request.contextPath}/CartServlet?method=clearCart";
									}
								}
							</script>
							</tbody>
						</table>
					</div>
				</div>

				<div style="margin-right:130px;">
					<div style="text-align:right;">
						<em style="color:#ff6600;">
							登录后确认是否享有优惠&nbsp;&nbsp;
						</em> 赠送积分: <em style="color:#ff6600;">596</em>&nbsp; 商品金额: <strong style="color:#ff6600;">￥${cart.totalPrice}元</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<a href="#" onclick="clearCart()" id="clear" class="clear">清空购物车</a>
						<a href="${pageContext.request.contextPath}/OrderServlet?method=addOrder">
							<input type="button" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
									height:35px;width:100px;color:white;">
						</a>
					</div>
				</div>
			</div>
		</c:if>
		<%@include file="base/footer.jsp"%>
	</body>

</html>