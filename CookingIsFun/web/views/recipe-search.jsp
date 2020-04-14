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
        <script>
            var recipes = [];
            var page = 0;
            const PAGE_SIZE = 3;
            <c:forEach items="${requestScope.LIST_RECIPES}" var="r">
            recipes.push({
                id: ${r.id},
                image: '${r.image}',
                name: '${r.name}',
                servings: '${r.servings}',
                description: '${r.description}',
                time: ${r.prepTime + r.cookTime}
            });
            </c:forEach>
        </script>
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
                        <h1>Tìm kiếm công thức nấu ăn dựa trên từ khóa</h1>
                        <div class="search-center my-4 row col-md-12">
                            <form class="" method="get" 
                                  action="FrontController">
                                <div class="row">
                                    <div class="col-md-9">
                                        <div class="form-group">
                                            <div class="input-group">
                                                <input type="text" name="keyword"
                                                       class="form-control"
                                                       placeholder="Tìm kiếm công thức nấu ăn theo thành phần, món ăn hoặc từ khóa" 
                                                       value="${requestScope.lastKeyword}">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <button class="btn btn-primary" name="act" type="submit" value="search">
                                                <i class="fa fa-search"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div id="list-recipe"></div>
                        <div class="load-button btn btn-primary" id="load-button-recipes">Xem thêm</div>
                    </div>
                </div>
            </div>

            <%@include file="common/footer.jsp" %>
            <script>
                const loadButtonRep = document.getElementById("load-button-recipes");
                function loadMoreRecipes() {
                    var showedRecipes = recipes.slice(page * PAGE_SIZE, (page + 1) * PAGE_SIZE).map(item =>
                            `<div class="row">
                <div class="col-md-12">
                    <div class="recipe-box">
                        <div class="recipe-box-list">

                            <div class="recipe-img">
                                <a href="#">
                                    <img src="\${item.image}" class="wp-post-image" alt="\${item.name}">  
                                </a>
                            </div>
                            <div class="recipe-heading">
                                <div class="recipe-header">
                                    <h6 class="recipe-title">
                                        <a href="FrontController?id=\${item.id}&act=recipeDetail">\${item.name}</a>
                                    </h6>
                                </div>
                                <div class="recipe-excerpt">\${item.description}</div>
                                <div class="recipe-details">
                                    <div class="prep-time">
                                        <span><i class="fa fa-user"></i>&nbsp;&nbsp;Khẩu phần: \${item.servings} người</span>
                                        <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;Tổng thời gian thực hiện: \${item.time} phút</span>
                                    </div>
                                </div>
                                <div>
                                    <form action="FrontController" method="get">
                                        <input type="hidden" name="id" value="\${item.id}"/>
                                        <button class="btn btn-success" type="submit" value="recipeDetail" name="act">Chi tiết</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>`
                    );
                    document.getElementById("list-recipe").innerHTML += showedRecipes.join('');
                    page++;
                    if (page * PAGE_SIZE > recipes.length) {
                        loadButtonRep.outerHTML = "";
                    }
                }
                loadMoreRecipes();
                loadButtonRep.addEventListener('click', loadMoreRecipes);
            </script>
    </body>
</html>
