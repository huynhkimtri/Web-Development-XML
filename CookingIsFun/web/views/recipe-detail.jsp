<%-- 
    Document   : detail
    Created on : Mar 26, 2020, 7:21:59 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail - CookingIsFun</title>
        <%@include file="common/link.jsp" %>
        <link href="resources/css/recipe-detail.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <c:set var="recipe" value="${requestScope.RECIPE}"/>
        <c:if test="${not empty recipe}">
            <div id="header" class="heading">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="page-title">
                                <h2>${recipe.name}</h2>                
                            </div>
                            <ol class="breadcrumb">
                                <li><a href="#">Trang chủ</a></li>
                                <li>${recipe.name}</li>
                            </ol>
                        </div>
                    </div>
                </div>
            </div>
            <div id="content" class="main style-1">
                <div class="container">
                    <div class="content col-md-10 offset-md-1">
                        <div class="recipe-info row">
                            <div class="col-md-4">
                                <img src="${recipe.image}" alt="Hình ảnh của ${recipe.name}" width="100%">
                            </div>
                            <div class="col-md-8 recipe-description">
                                <p class="text-justify">${recipe.description}</p>
                                <div class="quick-view">
                                    <ul>
                                        <li><i class="fa fa-user"></i> Khẩu phần: ${recipe.servings} người</li>
                                        <li><i class="fa fa-clock-o"></i> Chuẩn bị: ${recipe.prepTime} phút</li>
                                        <li><i class="fa fa-clock-o"></i> Thực hiện: ${recipe.cookTime} phút</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="recipe-content">
                            <div class="row">
                                <div class="col-md-4">
                                    <h4>Nguyên liệu gồm:</h4>
                                    <div class="recipe-ingredients">
                                        <ul class="ingredients-list">
                                            <c:forEach items="${recipe.listIngredients.ingredient}" var="ing">
                                                <c:url value="FrontController" var="ingredient_info">
                                                    <c:param name="action" value="Lookup"/>
                                                    <c:param name="txtSearch" value="${ing.name}"/>
                                                </c:url>
                                                <li>
                                                    ${ing.quantity} ${ing.unit} <a class="ingredient" href="${ingredient_info}">${ing.name}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <h4>Các bước thực hiện:</h4>
                                    <div class="recipe-steps">
                                        <ol>
                                            <c:forEach items="${recipe.listIntructions.instruction}" var="step">
                                                <li class="text-justify">${step.detail}</li>
                                                </c:forEach>   
                                        </ol>
                                    </div>
                                    <div class="print-cooking" onclick="prinPDFwithXHR()">
                                        <a href="${recipe_info}">In công thức</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="recipe-similar">

                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${empty recipe}">
            <h1>Something wrong</h1>
        </c:if>
        <%@include file="common/footer.jsp" %>
    </body>
</html>
