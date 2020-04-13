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
            <c:forEach items="${requestScope.LIST_RECIPE}" var="r">
            recipes.push({
                id: ${r.id},
                image: '${r.image}',
                title: '${r.title}',
                description: '${r.description}',
                time: ${r.preparetime + r.cookingtime}
            });
            </c:forEach>
            <c:set value="${sessionScope.RECIPE_WEBSITE.subdomains.subdomain}" var="jspIng"/>
            <c:forEach items="${jspIng}" var="temp" varStatus="counter">
                <c:if test="${counter.count != 1}">
            ingredients.push({id: ${temp.id}, value: `${temp.value}`});
                </c:if>
            </c:forEach>

            function removeSuggestion(id) {
                document.getElementById("param-index-" + id).outerHTML = "";
                tags = tags.filter(item => item.id != id);
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
                    document.getElementById("tags-included").innerHTML = tags.map(item => `<div class="innerSuggest" id="param-index-\${item.id}" onclick='removeSuggestion(\${item.id})'>\${item.value}</div>`).join('');
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
                                `<div class="sug \${index === suggestionIndex ? 'choosing' : ''}" data-id="\${item.id}">\${item.value}</div>`
                        );
                const area = document.getElementById("suggestion");
                area.innerHTML = suggestionComponents.join('');
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
                        <div>
                            <h1>Tìm kiếm công thức nấu ăn dựa trên nguyên liệu</h1>
                            <div class="search-center">
                                <form id="searchForm" autocomplete="off">
                                    <input id="input-search" 
                                           class="txtSearch" 
                                           type="text" 
                                           placeholder="i.e: Mỳ/Bún/Miến" name="txtSearch" />
                                </form>
                                <div id="tags-included"></div>
                                <div id="suggestion"></div>
                                <form action="MainController" method="POST" >
                                    <input id="tags" type="hidden" name="tags" value="" />
                                    <input class="button" type="submit" value="AdvanceSearch" name="action" />
                                </form>
                            </div>
                            <script type="text/javascript">loadSuggestions();</script>
                            <div id="list-recipe"></div>
                            <div class="load-button" id="load-button-recipes">Load more</div>
                        </div>
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
        <%@include file="common/footer.jsp" %>
    </body>
</html>
