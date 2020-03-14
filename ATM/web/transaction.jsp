<%-- 
    Document   : transaction
    Created on : Mar 4, 2020, 8:23:28 AM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
            <form action="transaction.jsp">
                <table>
                    <tr>
                        <td>From Date (yyyy/mm/dd):</td>
                        <td><input type="text" name="from" value="${param.from}" /></td>
                    </tr>
                    <tr>
                        <td>To Date (yyyy/mm/dd):</td>
                        <td><input type="text" name="to" value="${param.to}"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="View transaction"/></td>
                    </tr>
                </table>
            </form>
            <c:set var="from" value="${param.from}"/>
            <c:set var="to" value="${param.to}"/>
            <c:if test="${not empty from and not empty to}">
                <c:set var="from" value="${fn:replace(from, '/', '')}"/>
                <c:set var="to" value="${fn:replace(to, '/', '')}"/>
                <x:set var="transaction"
                       select="$currentUser//transaction[translate(date,'/','')>=$from and translate(date,'/','')<=$to]"/>
                <x:if select="$transaction">
                    <table border="1">
                        <tr>
                            <th>No</th>
                            <th>Date</th>
                            <th>Amount</th>
                            <th>Type</th>
                        </tr>
                        <x:forEach var="tran" select="$transaction" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td><x:out select="$tran/date"/></td>
                                <td><x:out select="$tran/amount"/></td>
                                <td>
                                    <x:choose>
                                        <x:when select="$tran[type=0]">
                                            withdraw
                                        </x:when>
                                        <x:when select="$tran[type=1]">
                                            deposit
                                        </x:when>
                                        <x:when select="$tran[type=2]">
                                            transfer
                                        </x:when>
                                        <x:otherwise>
                                            hacked
                                        </x:otherwise>
                                    </x:choose>
                                </td>
                            </tr>
                        </x:forEach>
                    </table>
                </x:if>
                <x:if select="not ($transaction)">
                    <p>No records matched!</p>
                </x:if>
            </c:if>
        </div>
    </body>
</html>
