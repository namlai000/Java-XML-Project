<%-- 
    Document   : Article
    Created on : May 30, 2017, 9:43:43 PM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
    <c:set var="entity" value="${requestScope.news}"/>
    <c:set var="blank" value="${empty entity.title}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${blank ? 'Not Found' : entity.title} - Website Name</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <%@include file="PartialPages/Header.jsp" %>

            <!-- BODY -->
            <div class="body-main">
                <div>
                    <h1>${entity.title}</h1>
                    <h3>${entity.author}</h3>
                </div>
                <div class="news-image">
                    <img src="${entity.image}"/>
                </div>
                <div>
                    ${entity.description}
                </div>
                <div>
                    <fm:formatDate value="${entity.date}" var="fmDate" type="date" pattern="MM-dd-yyyy" />
                    ${fmDate}
                </div>
            </div>

            <!-- Footer -->
            <%@include file="PartialPages/Footer.jsp" %>
        </div>
    </body>
</html>
