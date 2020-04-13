<%-- 
    Document   : index
    Created on : Mar 16, 2020, 1:23:06 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ - CookingIsFun</title>
        <link href="resources/css/home.css" rel="stylesheet">
        <%@include file="common/link.jsp" %>
    </head>
    <body>
        <div>
            <%@include file="common/header.jsp" %>
            <%@include file="common/banner.jsp" %>
            <div class="recipe-list">
                <div class="container">
                    <h2 class="recipe-list-title">Các công thức phổ biến nhất				
                        <small>
                            Hãy tìm hiểu các công thức nấu ăn phổ biến nhất của chúng tôi			
                        </small>
                    </h2>
                    <div class="row">
                        <c:forEach items="${requestScope.TOP_RECIPES}" var="recipe">
                            <c:url value="FrontController" var="detail">
                                <c:param name="id" value="${recipe.id}"/>
                                <c:param name="act" value="recipeDetail"/>
                            </c:url>
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="recipe-box">
                                    <div class="recipe-img">
                                        <a href="#">
                                            <img width="643" height="428" src="${recipe.image}" 
                                                 class="img-responsive wp-post-image" alt="${recipe.name}">
                                        </a>
                                    </div>
                                    <div class="recipe-heading">
                                        <div class="recipe-header">
                                            <h6 class="recipe-title">
                                                <a href="${detail}">
                                                    ${recipe.name}</a>
                                            </h6>
                                            <p>${recipe.description}</p>
                                        </div>
                                        <div class="recipe-details">
                                            <div class="prep-time">
                                                <span> <i class="fa fa-user"></i> 
                                                    Khẩu phần: ${recipe.servings} người
                                                </span>
                                            </div>
                                            <div>
                                                <a class="recipe-detail" href="${detail}">Chi tiết</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="list-tag">
                <ul class="wrap-list">
                    <c:url value="FrontController" var="repAdvance">
                        <c:param name="action" value="AdvanceSearch"/>
                    </c:url>
                    <c:url value="FrontController" var="ingAdvance">
                        <c:param name="action" value="IngIndex"/>
                    </c:url>
                    <li class="item-list"><a href="${repAdvance}"><span>Recipe</span></a></li>
                    <li class="item-list"><a href="${ingAdvance}"><span>Ingredient</span></a></li>
                </ul>
            </div>

            <div class="search-right">
                <form action="FrontController" method="GET" >
                    <input type="text" name="keySearch" placeholder="Lẩu, Bún, Cơm,..."/>
                    <button type="submit" value="Search" name="action">Tìm kiếm</button>
                </form>
            </div>
        </div>
        <div>
            <h3>Công thức đa dạng</h3>
            <p>Hỗ trợ tìm kiếm thông minh dựa trên các thành phần thực phẩm mong muốn. 
                Những công thức dễ dàng nấu ăn với thời gian ngắn nhất.</p>
                <c:url value="FrontController" var="recipeSearchAdvance">
                    <c:param name="action" value="AdvanceSearch"/>
                </c:url>
            <a href="${recipeSearchAdvance}">Tìm kiếm Công thức</a>
            <h3>Thực phẩm tươi sống</h3>
            <p>Với nguồn thực phẩm, nguyên liệu đa dạng được tổng hợp và chọn lọc 
                từ nhiều cửa hàng lớn nhỏ trên địa bàn thành phố.</p>
                <c:url value="FrontController" var="ingredientSearchAdvance">
                    <c:param name="action" value="Lookup"/>
                </c:url>
            <a href="${ingredientSearchAdvance}">Tìm kiếm nguyên liệu</a>
        </div>
        <%@include file="common/footer.jsp" %>
    </body>
</html>
