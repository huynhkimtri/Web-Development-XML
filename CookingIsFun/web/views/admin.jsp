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
        <style>
            .loader {
                border: 8px solid #d3efff; /* Light Blue */
                border-top: 8px solid #0099ff; /* Blue */
                border-radius: 50%;
                width: 50px;
                height: 50px;
                animation: spin 2s linear infinite;
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>
        <script>
            function crawlRecipe(origin) {
                var result = document.getElementById("crawl-result");
                result.innerHTML = "Status: Crawling...";
                result.innerHTML += '<div class="loader"></div>';
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.status === 200 && xhr.readyState === 4) {
                        var res = xhr.responseText;
                        result.innerHTML = "Status: Crawled!\n";
                        result.innerHTML += "Response:\n";
                        result.innerHTML += res;
                    }
                };
                var request = 'FrontController?origin=' + origin + '&action=crawlRecipe';
                xhr.open('POST', request, true);
                xhr.send();
            }
        </script>
    </head>

    <body>
        <div>
            <h1>Welcome to Admin Page</h1>
            <c:set value="${sessionScope.RECIPE_DOMAINS}" var="recipeDomains"/>
            <c:set value="${sessionScope.INGREDIENT_DOMAINS}" var="ingredientDomains"/>
            <form action="FrontController" method="post">
                <div>
                    <label>Recipe Domain:</label>
                    <select name="origin">
                        <option value="${recipeDomains.origin}">${recipeDomains.origin}</option>
                    </select>
                    <button type="submit" value="crawlRecipe" name="action">Crawl</button>
                    <button type="button" onclick="crawlRecipe('${recipeDomains.origin}')">Crawl with XHR</button>
                </div>
            </form>
            <form action="FrontController" method="post">
                <div>
                    <label>Ingredient Domain:</label>
                    <select name="origin">
                        <option value="${ingredientDomains.origin}">${ingredientDomains.origin}</option>
                    </select>
                    <button type="submit" value="crawlIngredient" name="action">Crawl</button>
                </div>
            </form>
            <div id="crawl-result"></div>
        </div>
    </body>

</html>