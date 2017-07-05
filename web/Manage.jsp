<%-- 
    Document   : index
    Created on : May 29, 2017, 8:14:48 AM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Articles</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <c:import url="WEB-INF/manageArticles.xsl" var="xslDoc" charEncoding="UTF-8"/>
                <h2>Tin tức</h2>
                <x:transform xml="${xmlDoc}" xslt="${xslDoc}">
                    <x:param name="pattern" value="1"/>
                </x:transform>
                <h2 style="margin-top: 128px;">Góc nhìn</h2>
                <x:transform xml="${xmlDoc}" xslt="${xslDoc}">
                    <x:param name="pattern" value="2"/>
                </x:transform>
                <div id="error1">                  
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>

<script>
    function editArticle(id) {
        window.location.href='ProcessServlet?location=edit&id=' + id;
    }

    function deleteArticle(id) {
        var xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        if (confirm("Are you sure?") == true) {
            var url = "ProcessServlet?location=delete&id=" + id;
            xmlHttp.open("POST", url, true);
            xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    var result = JSON.parse(xmlHttp.responseText);
                    if (result.success) {
                        alert("Delete success!");
                        window.location.href = "ProcessServlet?location=manage";
                    } else {
                        var data = result.error;
                        document.getElementById("error1").innerHTML = "<font color='red'>" + data + "</font>";
                    }
                }
            };
            xmlHttp.send();
        }
    }

    function GetXMLHttpObject() {
        var xmlHttp = null;
        try {
            xmlHttp = new XMLHttpRequest();
        } catch (e) {
            try {
                xmlHttp = new ActiveXObject("Mxsml2.XMLHTTP");
            } catch (e) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
        }

        return xmlHttp;
    }
</script>
