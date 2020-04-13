<%-- 
    Document   : header
    Created on : Mar 16, 2020, 5:52:16 PM
    Author     : huynh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default" id="navbar">
    <div class="container justify-content-md-center">
        <c:url value="FrontController" var="recipeSearch">
            <c:param name="act" value="recPage"/>
        </c:url>
        <li class="nav-item">
            <a class="nav-link" href="${recipeSearch}">Tìm kiếm công thức</a>
        </li>
        <a href="./">
            <img style="width: 230px;" class="nav-item" href="#" src="resources/images/new-brand.png" height="80"/>
        </a>
         <c:url value="FrontController" var="ingredientSearch">
            <c:param name="act" value="ingPage"/>
        </c:url>
        <li class="nav-item">
            <a class="nav-link" href="${ingredientSearch}">Tìm kiếm nguyên liệu</a>
        </li>
    </div>
</nav>
