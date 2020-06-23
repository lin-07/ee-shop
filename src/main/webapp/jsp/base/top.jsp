<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
时间：2015-12-30
描述：菜单栏
-->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png" />
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png" />
    </div>
    <div class="col-md-3" style="padding-top:20px">
        <ol class="list-inline">
            <c:if test="${empty user}">
                <li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
                <li><a href="cart.htm">购物车</a></li>
            </c:if>
            <c:if test="${not empty user}">
                <li><a href="#">欢迎您，${user.username}</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
                <li><a href="${pageContext.request.contextPath}/UserServlet?method=logOut">退出</a></li>
                <li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrder&pageNumber=1">我的订单</a></li>
            </c:if>
        </ol>
    </div>
</div>
<!--
时间：2015-12-30
描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/IndexServlet">首页</a>
            </div>
            <%--class="active"
                ${}
            --%>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav" id="categoryList">
                </ul>
                <script>
                    $(function () {
                        // $.post(url,params,function(data){
                        //
                        // },"json")
                        $.post("${pageContext.request.contextPath}/CategoryServlet",{"method":"findAll"},function(data){
                            $(data).each(function () {
                                $("#categoryList").append("<li><a href='${pageContext.request.contextPath}/ProductServlet?method=findByCid&pageNumber=1&cid="+this.cid+"'>"+this.cname+"</a></li>")
                            })
                        },"json")
                    })
                </script>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>