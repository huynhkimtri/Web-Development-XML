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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
              crossorigin="anonymous">
        <script>
            var loadingElement = '<h1>Crawling...</h1><div id="loading" class="d-flex justify-content-center mt-4"><div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">'
                    + '<span class="sr-only">Crawling...</span></div></div>';
            /**
             * 
             * @param {type} origin
             * @returns {undefined}
             */
            function crawlRecipe(origin) {
                var button = document.getElementById('btn-crawl-recipe-xhr');
                button.disabled = true;
                button.innerHTML = 'Crawling';
                var result = document.getElementById("crawl-result");
                result.innerHTML = loadingElement;
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.status === 200 && xhr.readyState === 4) {
                        var res = xhr.responseText;
                        result.innerHTML = '<h1>Result:</h1>';
                        result.innerHTML += res;
                        button.disabled = false;
                        button.innerHTML = 'Crawl';
                    }
                };
                var request = 'FrontController?origin=' + origin + '&action=crawlRecipe';
                xhr.open('POST', request, true);
                xhr.send();
            }
            /**
             * 
             * @param {type} origin
             * @returns {undefined}
             */
            function crawlIngredient(origin) {
                var button = document.getElementById('btn-crawl-ingredient-xhr');
                button.disabled = true;
                button.innerHTML = 'Crawling';
                var result = document.getElementById("crawl-result");
                result.innerHTML = loadingElement;
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    if (xhr.status === 200 && xhr.readyState === 4) {
                        result.innerHTML = '<h1>Result:</h1>';
                        result.innerHTML += xhr.responseText;
                        button.disabled = false;
                        button.innerHTML = 'Crawl';
                    }
                };
                var request = 'FrontController?origin=' + origin + '&action=crawlIngredient';
                xhr.open('POST', request, true);
                xhr.send();
            }
        </script>
    </head>

    <body>
        <div class="container">
            <div class="container justify-content-center">
                <div class="text-center mb-5 mt-5">
                    <h1>Welcome to Admin Page</h1>
                </div>
                <c:set value="${sessionScope.RECIPE_DOMAINS}" var="recipeDomains"/>
                <c:set value="${sessionScope.INGREDIENT_DOMAINS}" var="ingredientDomains"/>

                <form action="FrontController" method="post">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="select-domain-recipe">
                                Recipe Domain:
                            </label>
                        </div>
                        <select class="custom-select" id="select-domain-recipe" name="origin">
                            <option value="${recipeDomains.origin}">
                                ${recipeDomains.origin}
                            </option>
                        </select>
                        <button id="btn-crawl-recipe-xhr" class="btn btn-primary ml-3" 
                                type="button" onclick="crawlRecipe('${recipeDomains.origin}')">
                            Crawl
                        </button>
                    </div>
                </form>
                <form action="FrontController" method="post">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <label class="input-group-text" for="select-domain-recipe">Ingredient Domain:</label>
                        </div>
                        <select class="custom-select" id="select-domain-recipe" name="origin">
                            <option value="${ingredientDomains.origin}">
                                ${ingredientDomains.origin}
                            </option>
                        </select>
                        <button id="btn-crawl-ingredient-xhr" class="btn btn-primary ml-3" 
                                type="button" onclick="crawlIngredient('${ingredientDomains.origin}')">
                            Crawl
                        </button>
                    </div>
                </form>
                <div id="crawl-result" class="text-center mb-4 mt-4">
                </div>

            </div>
        </div>
    </body>

</html>