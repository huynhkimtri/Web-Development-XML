<%-- 
    Document   : draft
    Created on : Mar 16, 2020, 11:58:26 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <form action="FrontController" method="get">
                <div>
                    <label>Domain:</label>
                    <input type="text" value="${recipeDomains.origin}" readonly="true">
                    <label for="domain">Path:</label>
                    <select name="path">
                        <c:forEach items="${recipeDomains.paths.path}" var="item">
                            <option value="${item.id}">${item.value}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" value="crawlRecipe" name="action">Crawl Recipe</button>
                </div>
            </form>

            <form action="FrontController" method="get" class="form-inline">
                <div class="form-group">
                    <label for="domain">Domain:</label>
                    <input type="text" value="${ingredientDomains.origin}" class="form-control mx-sm-3"readonly="true">
                    <label for="domain">Path:</label>
                    <select name="path" class="form-control mx-sm-3">
                        <c:forEach items="${ingredientDomains.paths.path}" var="item">
                            <option value="${item.id}">${item.value}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-primary" value="crawlIngredient" name="action">Crawl Ingredient</button>
                </div>
            </form>
        </div>
    </body>
</html>
