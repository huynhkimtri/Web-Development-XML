<%-- 
    Document   : show
    Created on : Mar 8, 2020, 5:38:47 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Page</title>
    </head>
    <body>
        <div>
            <h1>Show Informations</h1>
            <h3><a href="multiplePipeline.jsp">Click here to back Transfer page</a></h3>
            <c:set var="info" value="${requestScope.INFO}"/>
            <c:if test="${not empty info}">
                <table border="1">
                    <tr>
                        <td>Book Name:</td>
                        <td>${info.name}</td>
                    </tr>
                    <tr>
                        <td>Author</td>
                        <td>${info.author}</td>
                    </tr>
                    <tr>
                        <td>Price:</td>
                        <td>${info.price}</td>
                    </tr>
                </table>
                <p>The XML document structure has structure as: </p>
                <c:import url="WEB-INF/books.xml"/>
            </c:if>
            <c:if test="${empty info}">
                <h3 style="color: red">No information is not available!</h3>
            </c:if>
        </div>
    </body>
</html>
