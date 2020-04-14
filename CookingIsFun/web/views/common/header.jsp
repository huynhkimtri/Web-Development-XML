<%-- 
    Document   : header
    Created on : Mar 16, 2020, 5:52:16 PM
    Author     : huynh
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-default" id="navbar">
    <div class="container collapse navbar-collapse">
        <a href="./">
            <img style="width: 230px;" class="nav-item" href="#" src="resources/images/new-brand.png" height="80"/>
        </a>
        <li class="nav-item">
            <a class="nav-link" href="FrontController?act=recPage">Tìm kiếm công thức theo từ khóa</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="FrontController?act=recIngPage">Tìm kiếm công thức theo nguyên liệu</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="FrontController?act=ingPage">Tìm kiếm nguyên liệu</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="FrontController?act=admin">Admin</a>
        </li>
    </div>
</nav>
