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
            var ingredients = [];
            var suggestions = [];
            var tags = [];
            var suggestionIndex = 0;
            var recipes = [];
            var page = 0;
            let PAGE_SIZE = 6;
            <c:forEach items="${requestScope.LIST_RECIPES}" var="r">
            recipes.push({
                id: ${r.id},
                image: '${r.image}',
                name: '${r.name}',
                servings: '${r.servings}',
                description: '${r.description}',
                prepTime: ${r.prepTime},
                cookTime: ${r.cookTime},
                time: ${r.prepTime + r.cookTime}
            });
            </c:forEach>
            <c:set value="${sessionScope.RECIPE_DOMAINS.paths.path}" var="ing"/>
            <c:forEach items="${ing}" var="temp" varStatus="counter">
                <c:if test="${counter.count != 1}">
            ingredients.push({
                id: ${temp.id},
                value: '${temp.value}'
            });
                </c:if>
            </c:forEach>

            function removeSuggestion(id) {
                document.getElementById("param-index-" + id).outerHTML = "";
                tags = tags.filter(item => item.id !== id);
                renderSuggestions();
            }

            function suggest(event) {
                if (event.keyCode === 13) {
                    const suggestions = document.getElementsByClassName("sug");
                    if (suggestions.length > suggestionIndex) {
                        tags.push({
                            id: suggestions[suggestionIndex].dataset.id,
                            value: suggestions[suggestionIndex].innerHTML
                        });
                    }
                    renderSuggestions();
                    document.getElementById("tags-included").innerHTML = tags.map(item =>
                            `<div class="innerSuggest badge badge-pill badge-success" id="param-index-\${item.id}" onclick='removeSuggestion(\${item.id})'>\${item.value}</div>`
                    ).join(' ');
                    document.getElementById("tags").value = tags.map(item => item.id);
                } else if (event.keyCode === 38) {
                    // up
                    if (suggestionIndex > 0) {
                        suggestionIndex--;
                        renderSuggestions();
                    }
                } else if (event.keyCode === 40) {
                    //down
                    if (suggestionIndex < suggestions.length - 1) {
                        suggestionIndex++;
                        renderSuggestions();
                    }
                } else if (event.keyCode === 27) {
                    // esc
                    document.getElementById("suggestion").style.visibility = 'hidden';
                } else {
                    const text = event.target.value;
                    suggestions = ingredients.filter(item => {
                        return item.value.toLowerCase().includes(text.toLowerCase());
                    });
                    suggestionIndex = 0;
                    renderSuggestions();
                }
            }

            function renderSuggestions() {
                const suggestionComponents = suggestions
                        .filter(item => !(tags.map(i => i.value)).includes(item.value))
                        .map((item, index) =>
                                `<span class="badge badge-pill badge-secondary sug \${index === suggestionIndex ? 'choosing' : ''}"  data-id="\${item.id}">\${item.value}</span>`
                        );
                const area = document.getElementById("suggestion");
                area.innerHTML = suggestionComponents.join(' ');
                area.style.visibility = 'visible';
            }

            function loadSuggestions() {
                document.getElementById("input-search").addEventListener('keyup', suggest);
                document.getElementById("input-search").addEventListener('blur', function () {
                    document.getElementById("suggestion").style.visibility = 'hidden';
                });
                document.getElementById("searchForm").addEventListener('submit', function (e) {
                    e.preventDefault();
                    return false;
                });
            }
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
                        <div class="">
                            <h3>Tìm kiếm công thức nấu ăn dựa trên loại nguyên liệu</h3>
                            <small>(Sắp xếp tăng dần theo thời gian)</small>
                            <div class="search-center my-4 row">
                                <div class="col-md-4">
                                    <form id="searchForm" autocomplete="off">
                                        <input id="input-search" 
                                               class="txtSearch form-control" 
                                               type="text" 
                                               placeholder="Ví dụ: rau, thịt, nấm,..." name="keyword" />
                                    </form>
                                    <div id="tags-included" class="mt-2"></div>
                                    <div id="suggestion"></div>
                                </div>
                                <div class="col-md-4">
                                    <form action="FrontController" method="get" >
                                        <input id="tags" type="hidden" name="tags" value="" />
                                        <button class="btn btn-primary" type="submit" value="AdvanceSearch" name="act">Tìm kiếm</button>
                                    </form>
                                </div>
                            </div>
                            <script type="text/javascript">loadSuggestions();</script>
                            <div id="list-recipe"></div>
                            <div class="load-button btn btn-primary" id="load-button-recipes">Load more</div>
                        </div>
                    </div>
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
                                    <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;Chuẩn bị: \${item.prepTime} phút</span>
                                    <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;Thực hiện: \${item.cookTime} phút</span>
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
