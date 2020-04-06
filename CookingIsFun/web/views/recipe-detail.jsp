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
        <style>

        </style>
    </head>
    <body style="width: 1200px; margin: auto" class="container">
        <div >
            <c:set var="recipe" value="${requestScope.RECIPE}"/>
            <c:if test="${not empty recipe}">
                <div class="food-detail">
                    <div class="image-food" style="text-align: center">
                        <img src="${recipe.image}" style="width: 30%" alt="Hình ảnh của ${recipe.name}" width="100%">
                    </div>

                    <div id="xhr-result"></div>

                    <div class="content-cooking">
                        <div class="element" style="width: 20%; float: left">
                            <h3 class="title">Thành phần</h3>
                            <div class="content">
                                <c:forEach items="${recipe.listIngredients.ingredient}" var="ing">
                                    <c:url value="FrontController" var="ingredient_info">
                                        <c:param name="action" value="Lookup"/>
                                        <c:param name="txtSearch" value="${ing.name}"/>
                                    </c:url>
                                    <p>${ing.quantity} ${ing.unit} <a class="ingredient" href="${ingredient_info}">${ing.name}</a></p>
                                    </c:forEach>
                            </div>
                        </div>
                        <div class="cooking"  style="width: 80%; float: right">
                            <h2 class="title">
                                ${recipe.name}
                            </h2>
                            <p class="description">
                                ${recipe.description}
                            </p>
                            <div class="detail-cooking">
                                <div class="quick-view">
                                    <ul>
                                        <li>Khẩu phần: ${recipe.servings} người</li>
                                        <li>Chuẩn bị: ${recipe.prepTime} phút</li>
                                        <li>Thực hiện: ${recipe.cookTime} phút</li>
                                    </ul>
                                </div>
                                <div class="instruction">
                                    <h3 class="title">Hướng dẫn</h3>
                                    <div class="content">
                                        <c:forEach items="${recipe.listIntructions.instruction}" var="step">
                                            <p>${step.step}. ${step.detail}</p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="print-cooking" onclick="prinPDFwithXHR()">
                                <a href="${recipe_info}">In công thức</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty recipe}">
                <h1>Something wrong</h1>
            </c:if>
        </div>
    </body>
</html>
