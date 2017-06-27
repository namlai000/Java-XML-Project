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
    <c:set var="entity" value="${requestScope.auArticle}"/>
    <c:set var="blank" value="${empty entity.tittle}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${blank ? 'Not Found' : entity.tittle} - Website Name</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <div class="row">
                    <div class="auArticle-image">
                        <img src="Images/avatar.png"/>
                    </div>
                    <div class="auArticle-text">
                        <h2>${entity.tittle}</h2>
                        <h3><a href="ProcessServlet?location=detail&id=${entity.tblNewsList[0].authorID.userId.id}">${entity.tblNewsList[0].authorID.lastname}</a></h3>
                        <hr/>
                        ${entity.description}
                        <br/>
                        ${entity.tblNewsList[0].content}
                        <div class="row" style="padding-top: 60px">
                            <h4>Các bài viết tương tự</h4>
                            <c:forEach var="ran" items="${requestScope.ran3}">
                                <div class="row">
                                    >>>> <a href="ProcessServlet?location=read&id=${ran.id}">${ran.tittle}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>
