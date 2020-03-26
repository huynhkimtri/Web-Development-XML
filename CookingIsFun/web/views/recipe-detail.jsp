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
    </head>
    <body>
        <div class="container">
            <c:set var="recipe" value="${requestScope.RECIPE}"/>
            <c:if test="${not empty recipe}">
                <div class="food-detail">
                    <div class="print-cooking" onclick="prinPDFwithXHR()">
                        <img class="icon" src="img/png/001-fax.png" alt=""> <a href="${recipe_info}">In công thức</a>
                    </div>
                    <div id="xhr-result"></div>
                    <div class="cooking">
                        <h2 class="title">
                            ${recipe.name}
                        </h2>
                        <p class="description">
                            ${recipe.description}
                        </p>
                        <div class="detail-cooking">
                            <div class="quick-view">
                                <ul>
                                    <li><span><img class="icon" src="img/png/004-man-user.png" alt=""> Khẩu phần: ${recipe.servings} người</span></li>
                                    <li><span><img class="icon" src="img/png/003-clock.png" alt=""> Chuẩn bị: ${recipe.prepTime} phút</span></li>
                                    <li><span><img class="icon" src="img/png/002-cooking-on-fire.png" alt=""> Thực hiện: ${recipe.cookTime} phút</span></li>
                                </ul>
                            </div>
                            <div class="image-food">
                                <img src="${recipe.image}" alt="Hình ảnh của ${recipe.name}" width="100%">
                            </div>
                            <div class="content-cooking">
                                <div class="element">
                                    <h3 class="title">Thành phần</h3>
                                    <div class="content">
                                        <c:forEach items="${recipe.listIngredients.ingredient}" var="ing">
                                            <c:url value="FrontController" var="ingredient_info">
                                                <c:param name="action" value="Lookup"/>
                                                <c:param name="txtSearch" value="${ing.name}"/>
                                            </c:url>
                                            <a class='ingredient' href="${ingredient_info}">${ing.quantity} ${ing.unit} ${ing.name}</a>
                                        </c:forEach>
                                    </div>
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
