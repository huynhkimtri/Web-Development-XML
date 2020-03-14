<%-- 
    Document   : products
    Created on : Mar 8, 2020, 12:53:08 PM
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
        <div>
            <h1>XML Transforming with JSP Taglib</h1>
            <c:import url="WEB-INF/products.xml" var="xmldoc"/>
            <c:import url="WEB-INF/products.xsl" var="xsldoc"/>
            <x:transform xml="${xmldoc}" xslt="${xsldoc}"/>
        </div>
    </body>
</html>
