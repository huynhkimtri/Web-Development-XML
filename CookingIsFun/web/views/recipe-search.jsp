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
        <title>Tìm kiếm công thức - CookingIsFun</title>
        <%@include file="common/link.jsp" %>
        <link href="resources/css/recipe-search.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <div id="header" class="heading">
            <div class="container">
                <div class="row">
                    <div class="col-md-10 offset-md-1">
                        <div class="page-title">
                            <h2>Công thức nấu ăn</h2>                
                        </div>
                        <ol class="breadcrumb">
                            <li><a href="#">Trang chủ</a></li>
                            <li>Công thức nấu ăn</li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div id="content" class="main">
            <div class="container">
                <div class="row">
                    <div class="content col-md-12">
                        <div class="row sort">
                            <div class="col-md-8 col-sm-8 col-xs-9">
                                <form class="form-inline" method="get" action="?" id="sort-form">
                                    <span>Sort by : </span>
                                    <div class="form-group">
                                        <label class="sr-only" for="sortby">Sort by:&nbsp;</label>
                                        <select class="form-control" name="filter-sort-by">
                                            <option value="">Sort by</option>
                                            <option value="price">Price</option>
                                            <option value="title">Title</option>
                                            <option value="published">Published</option>
                                        </select>
                                    </div> &nbsp; &nbsp;
                                    <span>Show : </span>
                                    <div class="form-group">
                                        <label class="sr-only" for="show">Show : </label>
                                        <select class="form-control" name="filter-sort-order">
                                            <option value="">Order</option>
                                            <option value="asc">ASC</option>
                                            <option value="desc">DESC</option>
                                        </select>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <c:forEach items="${requestScope.LIST_RECIPES}" var="recipe">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="recipe-box">
                                        <div class="recipe-box-list">
                                            <c:url value="FrontController" var="detail">
                                                <c:param name="id" value="${recipe.id}"/>
                                                <c:param name="act" value="recipeDetail"/>
                                            </c:url>
                                            <div class="recipe-img">
                                                <a href="${detail}">
                                                    <img src="${recipe.image}" class="wp-post-image" alt="${recipe.name}">  
                                                </a>
                                            </div>
                                            <div class="recipe-heading">
                                                <div class="recipe-header">
                                                    <h6 class="recipe-title">
                                                        <a href="${detail}">${recipe.name}</a>
                                                    </h6>
                                                </div>
                                                <div class="recipe-excerpt">${recipe.description}</div>
                                                <div class="recipe-details">
                                                    <div class="prep-time">
                                                        <span><i class="fa fa-user"></i>&nbsp;&nbsp;Khẩu phần: ${recipe.servings} người</span>
                                                        <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;Chuẩn bị: 10 min</span>
                                                        <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;Thực hiện: 15 min</span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="list-tag">
                <ul class="wrap-list">
                    <c:url value="FrontController" var="repAdvance">
                        <c:param name="act" value="AdvanceSearch"/>
                    </c:url>
                    <c:url value="FrontController" var="ingAdvance">
                        <c:param name="act" value="IngIndex"/>
                    </c:url>
                    <li class="item-list"><a href="${repAdvance}"><span>Recipe</span></a></li>
                    <li class="item-list"><a href="${ingAdvance}"><span>Ingredient</span></a></li>
                </ul>
            </div>

            <div class="search-right">
                <form action="FrontController" method="GET" >
                    <input type="text" name="q" placeholder="Lẩu, Bún, Cơm,..."/>
                    <button type="submit" value="search" name="act">Tìm kiếm</button>
                </form>
            </div>
        </div>
        <%@include file="common/footer.jsp" %>
    </body>
</html>
