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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
              crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top py-3">
            <div class="container">
                <a class="navbar-brand" href="#">CookingIsFun</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="#">Home
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Services</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-4">
            <content>
                <div>
                    <c:forEach items="${requestScope.TOP_RECIPES}" var="r">
                        <table class="product-item">
                            <td class="image-product">
                                <img src="${r.image}" alt="${r.name}">
                                <div class="info">
                                    <p>${r.name}</p>
                                </div>
                            </td>
                            <td class="content-product">
                                <div class="content">
                                    <h3>Khẩu phần: ${r.servings} người </h3>
                                    <p>${r.description}</p>
                                </div>
                                <div>
                                    <c:url value="FrontController" var="detail">
                                        <c:param name="id" value="${r.id}"/>
                                    </c:url>
                                    <form action="${detail}" method="POST">
                                        <input class="button" type="submit" value="RecipeDetail" name="action" />
                                    </form>
                                </div>
                            </td>

                        </table>
                    </c:forEach>
                </div>
            </content>
        </div>
        <footer class="footer py-4 bg-light">
            <div class="container">
                <p class="m-0 text-center">Copyright © CookingIsFun 2020 <a href="FrontController?action=admin">Admin</p>
            </div>
        </footer>
    </body>
</html>
