<%-- 
    Document   : search
    Created on : Feb 24, 2020, 11:40:57 PM
    Author     : huynhkimtri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <div>
            <p>Welcome, ${sessionScope.FULL_NAME}</p>
            <h1>Search</h1>
            <form action="MainController" method="get">
                Address: <input type="text" name="searchValue" value="${param.searchValue}"/>
                <button type="submit" name="action" value="search">Search</button>
                <button type="reset">Clear</button>
                <br/>
            </form>
            <c:set value="${param.searchValue}" var="searchValue"/>
            <c:if test="${not empty searchValue}">
                <c:set value="${requestScope.SEARCH_RESULTS}" var="searchResults"/>
                <c:if test="${not empty searchResults}">
                    <table style="margin-top: 10px;" border="1">
                        <tr>
                            <th>No.</th>
                            <th>Id</th>
                            <th>Full Name</th>
                            <th>Class</th>
                            <th>Address</th>
                            <th>Sex</th>
                            <th>Status</th>
                            <th colspan="2">Action</th>
                        </tr>
                        <c:forEach var="std" items="${searchResults}" varStatus="counter">
                            <form action="ProcessServlet" method="get">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${std.id}<input type="hidden" name="id" value="${std.id}"/></td>
                                    <td>${std.lastname}&nbsp;${std.middlename}&nbsp;${std.firstname}</td>
                                    <td><input type="text" value="${std.classname}" name="classname"/></td>
                                    <td>${std.address}</td>
                                    <td><c:if test="${std.sex == true}">Male</c:if>
                                        <c:if test="${std.sex != true}">Female</c:if></td>
                                    <td><input type="text" value="${std.status}" name="status"/></td>
                                    <td>
                                        <c:url var="deleteUrl" value="ProcessServlet">
                                            <c:param name="action" value="delete"/>
                                            <c:param name="id" value="${std.id}"/>
                                            <c:param name="lastSearchValue" value="${param.searchValue}"/>
                                        </c:url>
                                        <a href="${deleteUrl}">Delete</a>
                                    </td>
                                    <td>
                                        <button type="submit" value="update" name="action">Update</button>
                                        <input type="hidden" nane="lastSearchValue" value="${param.searchValue}"/>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${empty searchResults}">
                    <p>No record is matched!</p>
                </c:if>
            </c:if>
        </div>
    </body>
</html>
