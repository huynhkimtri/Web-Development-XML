<%-- 
    Document   : applyMultiTemplate
    Created on : Mar 8, 2020, 4:36:48 PM
    Author     : huynh
--%>

<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <h1>Apply Multi Templates</h1>
            <c:import url="WEB-INF/persons.xml" var="xmldoc"/>
            <c:import url="WEB-INF/outputPersons.xsl" var="xsldoc1"/>
            <c:import url="WEB-INF/templateHTML.xsl" var="xsldoc2"/>
            <%--<x:transform xslt="${xsldoc2}">--%>
                <x:transform xml="${xmldoc}" xslt="${xsldoc1}"/>
            <%--</x:transform>--%>
        </div>
    </body>
</html>
