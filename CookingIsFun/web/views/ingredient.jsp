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
        <link href="resources/css/ingredient.css" rel="stylesheet">
        <style>
            .btn.btn-success {
                width: 100%;
                text-transform: uppercase;
            }
        </style>
        <script>
            <c:set value="${requestScope.LIST_INGREDIENTS}" var="ingredients"/>
            var ingredients = [];
            var page = 0;
            let PAGE_SIZE = 3;
            <c:forEach items="${ingredients}" var="item">
            ingredients.push({
                id: `${item.id}`,
                name: `${item.name}`,
                price: ${item.price},
                link: `${item.link}`,
                image: `${item.image}`,
                description: `${item.description}`
            });
            </c:forEach>

        </script>
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <div id="header" class="heading">
            <div class="container">
                <div class="row">
                    <c:set value="Thành phần - Nguyên liệu" var="pageTitle"/>
                    <div class="col-md-10 offset-md-1">
                        <div class="page-title">
                            <h2><c:out value="${pageTitle}"/></h2>                
                        </div>
                        <ol class="breadcrumb">
                            <li><a href="#">Trang chủ</a></li>
                            <li><c:out value="${pageTitle}"/></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div id="content" class="main">
            <div class="container">
                <c:if test="${not empty ingredients}" var="check">
                    <div class="row" id="list-ingredients"></div>
                    <button class="btn btn-primary" id="load-button-ingredients">Xem thêm</button>
                </c:if>
                <c:if test="${not check}">
                    <h3>Không tìm thấy nguyên liệu, vui lòng thử với từ khóa khác</h3>
                </c:if>
            </div>
        </div>
        <%@include file="common/footer.jsp" %>
        <script>
            const loadButtonIng = document.getElementById("load-button-ingredients");
            function loadMoreIngredients() {
                var showedItem = ingredients.slice(page * PAGE_SIZE, (page + 1) * PAGE_SIZE)
                        .map(item => `<div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="recipe-box">
                                <div class="recipe-img">
                                    <a href="#">
                                        <img width="643" height="428" src="\${item.image}" 
                                             class="img-responsive wp-post-image" alt="\${item.name}">
                                    </a>
                                </div>
                                <div class="recipe-heading">
                                    <div class="recipe-header">
                                        <h6 class="recipe-title">
                                            <a href="#">
            \${item.name}</a>
                                        </h6>
                                        <p>\${item.description}</p>
                                    </div>
                                    <div class="recipe-details">
                                        <div class="prep-time">
                                            <h3>Giá: \${item.price} đồng </h3>
                                        </div>
                                        <div style="width: 100%">
                                            <a class="btn btn-success" href="\${item.link}" 
                                               target="_blank">Đến chỗ mua</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>`);
                document.getElementById('list-ingredients').innerHTML += showedItem.join('');
                page++;
                if (page * PAGE_SIZE >= ingredients.length) {
                    loadButtonIng.outerHTML = "";
                }
            }
            loadMoreIngredients();
            loadButtonIng.addEventListener('click', loadMoreIngredients);
        </script>
    </body>
</html>
