<%-- 
    Document   : recipe-search
    Created on : Mar 27, 2020, 3:20:48 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nguyên liệu - CookingIsFun</title>
        <%@include file="common/link.jsp" %>
        <link href="resources/css/recipe-search.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <div id="header" class="heading">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 offset-md-1">
                        <div class="page-title">
                            <h2>Nguyên liệu chế biến</h2>                
                        </div>
                        <ol class="breadcrumb">
                            <li><a href="#">Trang chủ</a></li>
                            <li>Nguyên liệu chế biến</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div id="content" class="main">
            <div class="container">
                <div class="row">
                    <div class="content col-md-12">
                        <c:forEach items="${requestScope.LIST_INGREDIENTS}" var="item">
                            <div class="product-item">
                                <div class="image-product">
                                    <img src="${item.image}" alt="Hình ảnh của ${item.name}">
                                    <div class="info">
                                        <p>${item.name}</p>
                                    </div>
                                </div>
                                <div class="content-product">
                                    <div class="content">
                                        <h3>Giá :${item.price} đồng </h3>
                                        <p>${item.description}</p>
                                    </div>
                                    <div>
                                        <form action="${item.link}" method="POST">
                                            <input class="button" type="submit" value="Tới chỗ mua" name="action" />
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="common/footer.jsp" %>
    </body>
</html>
