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
        <title>Search Page</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <c:import url="WEB-INF/searchResult.xml" var="xmlDoc" charEncoding="UTF-8"/>
                <c:import url="WEB-INF/searchResult.xsl" var="xslDoc" charEncoding="UTF-8"/>
                <x:transform xml="${xmlDoc}" xslt="${xslDoc}"/>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>
