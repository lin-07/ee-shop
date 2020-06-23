<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<%@include file="base/head.jsp"%>
	</head>

	<body>
		<%@include file="base/top.jsp"%>
		<div class="row" style="width:1210px;margin:0 auto;">
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
				</ol>
			</div>
			<c:forEach items="${page.data}" var="product">
				<div class="col-md-2">
					<a href="${pageContext.request.contextPath}/ProductServlet?method=findByPid&pid=${product.pid}">
						<img src="${pageContext.request.contextPath}/${product.pimage}" width="170" height="170" style="display: inline-block;">
					</a>
					<p><a href="${pageContext.request.contextPath}/ProductServlet?method=findByPid&pid=${product.pid}" style='color:green'>&nbsp;&nbsp;&nbsp;${product.pname}</a></p>
					<p><font color="#FF0000">商城价：&yen;${product.market_price}</font></p>
				</div>
			</c:forEach>
		</div>

		<!--分页 class="active" class="disabled"-->
		<div class="text-center" style="width:1210px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<li ><a href="${pageContext.request.contextPath}/ProductServlet?method=findByCid&cid=${cid}&&pageNumber=1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
				<c:if test="${page.pageNumber != 1}">
					<li ><a href="${pageContext.request.contextPath}/ProductServlet?method=findByCid&cid=${cid}&pageNumber=${page.pageNumber-1}" aria-label="Previous"><span aria-hidden="true">上一页</span></a></li>
				</c:if>
				<c:forEach begin="${page.start}" end="${page.end}" var="num" step="1">
					<li ${page.pageNumber == num?"class='active'":""}><a href="${pageContext.request.contextPath}/ProductServlet?method=findByCid&cid=${cid}&pageNumber=${num}">${num}</a></li>
				</c:forEach>
				<c:if test="${page.totalPage != page.pageNumber}">
					<li><a href="${pageContext.request.contextPath}/ProductServlet?method=findByCid&cid=${cid}&&pageNumber=${page.pageNumber+1}" aria-label="Next"><span aria-hidden="true">下一页</span></a></li>
				</c:if>
				<li><a href="${pageContext.request.contextPath}/ProductServlet?method=findByCid&cid=${cid}&&pageNumber=${page.totalPage}" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
			</ul>
		</div>
		<!-- 分页结束=======================        -->

		<!--
       		商品浏览记录:
        -->
		<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

			<h4 style="width: 50%;float: left;font: 14px/30px " 微软雅黑 ";">浏览记录</h4>
			<div style="width: 50%;float: right;text-align: right;"><a href="">more</a></div>
			<div style="clear: both;"></div>

			<div style="overflow: hidden;">

				<ul style="list-style: none;">
					<li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;"><img src="${pageContext.request.contextPath}/products/1/cs10001.jpg" width="130px" height="130px" /></li>
				</ul>

			</div>
		</div>
		<%@include file="base/footer.jsp"%>
	</body>

</html>