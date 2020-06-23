<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<%@include file="base/head.jsp"%>
	</head>

	<body>
		<%@include file="base/top.jsp"%>
		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<c:forEach items="${page.data}" var="order">
							<tbody>
								<tr class="success">
									<th colspan="5">订单编号:${order.oid} </th>
								</tr>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
								</tr>
								<c:forEach items="${order.orderItems}" var="orderItem">
									<tr class="active">
										<td width="60" width="40%">
											<input type="hidden" name="id" value="22">
											<img src="${pageContext.request.contextPath}/${orderItem.product.pimage}" width="70" height="60">
										</td>
										<td width="30%">
											<a target="_blank">${orderItem.product.pname}</a>
										</td>
										<td width="20%">
											￥${orderItem.product.shop_price}
										</td>
										<td width="10%">
											${orderItem.count}
										</td>
										<td width="15%">
											<span class="subtotal">￥${orderItem.subtotal}</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</div>
<%--			class="active"  class="disabled"--%>
			<div style="text-align: center;">
				<ul class="pagination">
					<li ><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrder&pageNumber=1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
					<c:if test="${page.pageNumber != 1}">
						<li ><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrder&pageNumber=${page.pageNumber-1}" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
					</c:if>
					<c:forEach begin="${page.start}" end="${page.end}" var="num" step="1">
						<li ${page.pageNumber == num?"class='active'":""}><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrder&pageNumber=${num}">${num}</a></li>
					</c:forEach>
					<c:if test="${page.pageNumber != page.totalPage}">
						<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrder&pageNumber=${page.pageNumber+1}" aria-label="Next"><span aria-hidden="true">下一页</span></a></li>
					</c:if>
					<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrder&pageNumber=${page.totalPage}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
				</ul>
			</div>
		</div>

		<%@include file="base/footer.jsp"%>
	</body>

</html>