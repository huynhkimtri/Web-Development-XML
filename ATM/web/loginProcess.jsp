<%-- 
    Document   : loginProcess
    Created on : Mar 4, 2020, 8:05:12 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Process Page</title>
    </head>
    <body>
        <div>
            <c:set var="username" value="${param.username}" scope="session"/>
            <c:set var="password" value="${param.password}"/>
            <c:import var="xml" url="WEB-INF/accountATM.xml"/>
            <x:parse var="doc" doc="${xml}" scope="session"/>
            <c:if test="${not empty doc}">
                <x:set var="checkLogin" 
                       select="$doc//*[local-name()='allowed' and @username=$username and pin=$password]"/>
                <x:if select="$checkLogin">
                    <jsp:forward page="transaction.jsp"/>
                </x:if>
            </c:if>
            <h2 style="color: red">Invalid username or password!</h2>
        </div>
    </body>
</html>
