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
        <%@include file="common/footer.jsp" %>
    </body>
</html>
