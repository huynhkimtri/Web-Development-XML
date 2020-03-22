<%-- 
    Document   : newjsp
    Created on : Mar 20, 2020, 12:11:29 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import var="xml" url="WEB-INF/tomcat-users.xml"/>
    <x:parse var="doc" xml="${xml}"/>
    <x:if select="$doc/tomcat-users/user[@username='ide' and password='yAeHnGV3']">
        <jsp:forward page="welcome.jsp"/>
    </x:if>
    <h2><font color="red">Invalid username or password!!!!</font> </h2>
</body>
</html>
