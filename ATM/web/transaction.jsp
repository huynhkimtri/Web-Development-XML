<%-- 
    Document   : transaction
    Created on : Mar 4, 2020, 8:23:28 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Page</title>
    </head>
    <body>
        <x:set var="currentUser" select="$doc//*[@username=$username]"/>
        <div>
            <h1>Transaction Page</h1>
            <p>Welcome, <x:out select="$currentUser/fullname"/></p>
            <p>Balance: <x:out select="$currentUser/@balance"/></p>
        </div>
    </body>
</html>
