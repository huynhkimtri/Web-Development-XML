<%-- 
    Document   : ingredient-search
    Created on : Apr 13, 2020, 3:16:57 AM
    Author     : huynh
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm kiếm nguyên liệu - CookingIsFun</title>
        <%@include file="common/link.jsp" %>
        <link href="resources/css/ingredient.css" rel="stylesheet">
        <script>
            var xmlHttp;
            var PAGE_SIZE = 6;
            var num = 0;
            var parser = new DOMParser();
            function getXmlHttpObject() {
                var xmlHttp = null;
                if (window.XMLHttpRequest) {
                    // code for modern browsers
                    xmlHttp = new XMLHttpRequest();
                } else {
                    // code for old IE browsers
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                return xmlHttp;
            }

            function search() {
                num++;
                xmlHttp = getXmlHttpObject();
                if (xmlHttp === null) {
                    alert("Your browser not support AJAX");
                    return;
                }
                var url = "FrontController?act=ingSearch&q=" + document.getElementById("txtSearch").value + "&size=" + PAGE_SIZE + "&page=" + num;
                xmlHttp.open("GET", url, true);
                xmlHttp.addEventListener("load", handleResponse);
                xmlHttp.addEventListener("error", handleError);
                xmlHttp.addEventListener("abort", handleAbort);
                xmlHttp.send();
            }
        </script>
    </head>
    <body>
        <%@include file="common/header.jsp" %>
        <div id="header" class="heading">
            <div class="container">
                <div class="row">
                    <c:set value="Tìm kiếm nguyên liệu" var="pageTitle"/>
                    <div class="col-md-10 offset-md-1">
                        <div class="page-title">
                            <h2><c:out value="${pageTitle}"/></h2>                
                        </div>
                        <ol class="breadcrumb">
                            <li><a href="#">Trang chủ</a></li>
                            <li><c:out value="${pageTitle}"/></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <h1>Tìm kiếm nguyên liệu dựa trên từ khóa</h1>
            <div class="search-center">
                <input class="txtSearch form-control" type="text" id="txtSearch" value="${param.txtSearch}" /><br/>
                <input class="btn btn-primary" type="button" onclick="search()" value="Search"/>
            </div>
            <div class="row" id="list-ingredients"></div>
            <div class="load-button" id="load-button-ingredients"></div>
        </div>
        <%@include file="common/footer.jsp" %>
        <script>
            var loadButton = document.getElementById("load-button-ingredients");
            loadButton.addEventListener('click', search);
            function handleResponse() {
                if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
                    var tmp = xmlHttp.responseText;
                    var domTree = parser.parseFromString(tmp, "application/xml");
                    console.log(domTree);
                    displayResult(domTree);
                }
            }

            function handleError(evt) {
                console.log("An error occurred while Searching.");
            }

            function handleAbort(evt) {
                console.log("The searching has been canceled by the user.");
            }

            function displayResult(docXML) {
                var lists = document.getElementById("list-ingredients");
                var ingredients = docXML.getElementsByTagName('ingredient');
                var showedElement = '';
                for (var i = 0; i < ingredients.length; i++) {
                    var recipe = ingredients[i];
                    var name = recipe.getElementsByTagName('name')[0].textContent;
                    var price = recipe.getElementsByTagName('price')[0].textContent;
                    var link = recipe.getElementsByTagName('link')[0].textContent;
                    var image = recipe.getElementsByTagName('image')[0].textContent;
                    var description = recipe.getElementsByTagName('description')[0].textContent;
                    var td = document.createElement('td');
                    td.classList.add('image-product');
                    var img = document.createElement('img');
                    img.src = image;
                    img.alt = "failed";
                    var div = document.createElement('div');
                    div.classList.add("info");
                    var p = document.createElement('p');
                    p.innerHTML = name;
                    td.appendChild(img);
                    div.appendChild(p)
                    td.appendChild(div);

                    showedElement += `<tr class="product-item">
        <td class="image-product">
            <img src="\${image}" alt="Hình ảnh của \${name}" >
            <div class="info">
                <p>\${name}</p>
            </div>
        </td>
        <td class="content-product">
            <div class="content">
               <h3>Giá: \${price} đồng</h3>
                <p>\${description}</p>
            </div>
            <div>
                <a class="button" href="\${link}">Tới chỗ mua</a>
            </div>
        </td>
    </tr>`;
                }

                if (ingredients.length < PAGE_SIZE) {
                    loadButton.disabled = true;
                } else {
                    loadButton.disabled = false;
                }

                if (ingredients.length === 0) {
                    showedElement = '<p>Không còn kết quả có thể tìm thấy. Vui lòng thử với từ khóa khác</p>';
                    num = 0;
                }
                lists.innerHTML = showedElement;
            }
        </script>
    </body>
</html>
