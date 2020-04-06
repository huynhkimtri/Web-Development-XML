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
        <title>Search Recipe - CookingIsFun</title>
    </head>
    <body style="width: 1200px; margin: auto">
        <div>
            <h1>CookingIsFun</h1>
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
            <c:forEach items="${requestScope.LIST_RECIPES}" var="recipe">
                <table class="product-item">
                    <td class="image-product">
                        <img src="${recipe.image}" alt="${recipe.name}">
                    </td>
                    <td class="content-product">
                        <div class="content">
                            <h2>${recipe.name}</h2>
                            <h3>Khẩu phần: ${recipe.servings} người </h3>
                            <p>${recipe.description}</p>
                        </div>
                        <div>
                            <c:url value="FrontController" var="detail">
                                <c:param name="id" value="${recipe.id}"/>
                                <c:param name="action" value="RecipeDetail"/>
                            </c:url>
                            <a href="${detail}">Chi tiết</a>
                        </div>
                    </td>
                </table>
            </c:forEach>
        </div>
    </body>
</html>
