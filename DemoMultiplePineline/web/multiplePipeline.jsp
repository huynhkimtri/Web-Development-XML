<%-- 
    Document   : multiplePipeline
    Created on : Mar 8, 2020, 5:04:37 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Multiple Pipeline Page</title>
    </head>
    <body>
        <div>
            <h1>Multiple Pipeline</h1>
            <form action="Controller" method="get">
                <table>
                    <tr>
                        <td>Book</td>
                        <td>
                            <select name="name">
                                <option>XML</option>
                                <option>XSL</option>
                                <option>Schema</option>
                                <option>DTD</option>
                                <option>JAXB</option>
                                <option>SAX</option>
                                <option>StAX</option>
                                <option>Others</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Author</td>
                        <td><input type="text" name="author" value=""/></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="number" name="price" value=""/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="action" value="Transfer"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
