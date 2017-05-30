<%-- 
    Document   : index
    Created on : May 29, 2017, 8:14:48 AM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<!-- INSERT SCRIPTS HERE-->

<!-- LINK CSS HERE-->
<link href="CSS/Main.css" rel="stylesheet" type="text/css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="/header" />

            <!-- BODY -->
            <div class="body-main">
                <div class="left-menu">
                    <c:import url="/explore" />
                </div>
                <div class="right-menu">
                    <c:set var="result" value="${requestScope.list}"/>
                    <c:if test="${not empty result}">
                        <table>
                            <c:forEach var="entity" items="${result}">
                                <tr>
                                    <td>
                                        <div class="article-image">
                                            <img src="${entity.image}" alt=""/>
                                        </div>
                                        <div class="article-text">
                                            <h2>${entity.title}</h1>
                                            ${entity.description}
                                        </div> 
                                    </td>
                                </tr>
                            </c:forEach>  
                        </table>
                    </c:if>
                </div> 
            </div>

            <!-- Footer -->
            <c:import url="/footer" />
        </div>
    </body>
</html>
