<%-- 
    Document   : admin
    Created on : Mar 16, 2020, 12:35:54 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin - CookingIsFun</title>
    </head>

    <body>
        <div class="container text-center">
            <h1>Welcome to Admin Page</h1>
            <c:set value="${sessionScope.RECIPE_DOMAINS}" var="recipeDomains"/>
            <c:set value="${sessionScope.INGREDIENT_DOMAINS}" var="ingredientDomains"/>
            <form action="FrontController" method="post">
                <div>
                    <label>Domain:</label>
                    <input type="text" value="${recipeDomains.origin}" name="domain" readonly="true">
                    <label>Path:</label>
                    <select name="path">
                        <c:forEach items="${recipeDomains.paths.path}" var="item">
                            <option value="${item.id}">${item.value}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" value="crawlRecipe" name="action">Crawl Recipe</button>
                </div>
            </form>

            <form action="FrontController" method="post">
                <div>
                    <label>Domain:</label>
                    <input type="text" value="${ingredientDomains.origin}" readonly="true">
                    <label>Path:</label>
                    <select name="path">
                        <c:forEach items="${ingredientDomains.paths.path}" var="item">
                            <option value="${item.id}">${item.value}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" value="crawlIngredient" name="action">Crawl Ingredient</button>
                </div>
            </form>
        </div>
    </body>

</html>