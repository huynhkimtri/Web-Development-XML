<%-- 
    Document   : admin
    Created on : Mar 11, 2020, 9:11:51 PM
    Author     : huynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page - PRX</title>
        <script>
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    myFunction(this);
                }
            };
            xhttp.open("GET", "./recipe-domains.xml", true);
            xhttp.send();
            function myFunction(xml) {
                var xmlDoc = xml.responseXML;
                document.getElementById("demo").innerHTML = xmlDoc.toString();
            }
        </script>
    </head>

    <body>
        <div>
            <div id="demo" style="background-color: activecaption"></div>
        </div>
    </body>

</html>