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
        <title>Index - CookingIsFun</title>
        <style>

        </style>
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
        <div>
            <c:forEach items="${requestScope.TOP_RECIPES}" var="recipe">
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
        <footer style="text-align: center" class="footer py-4 bg-light">
            <div class="container">
                <p class="m-0 text-center">Copyright © CookingIsFun 2020 <a href="FrontController?action=admin">Admin</p>
            </div>
        </footer>
    </body>
</html>