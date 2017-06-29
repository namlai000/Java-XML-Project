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
                    <div>
                        <h1>${entity.tittle}</h1>
                        <fm:formatDate value="${entity.date}" var="fmDate" type="date" pattern="MM-dd-yyyy" />
                        <h3>${entity.tblNews.authorID.lastname} | ${fmDate}</h3>
                    </div>
                    <div style="margin-bottom: 18px;">
                        <b>${entity.description}</b>
                    </div>
                    <div class="news-image">
                        <c:forEach var="image" items="${entity.tblNews.tblImageList}">
                            <img src="${image.link}" alt=""/>                            
                        </c:forEach>
                    </div>
                    <div>
                        ${entity.tblNews.content}
                    </div>
                    <div class="break-line">
                        <hr/>
                    </div>
                    <div>
                        <i>Tags: 
                            <c:forEach var="cat" items="${entity.tblNews.tblSubCategoryList}">
                                <span><a href="ProcessServlet?location=explore&menu=${cat.id}">${cat.subCategoryName}</a> | </span>                   
                            </c:forEach>
                        </i>
                    </div>
                </div>
                <div class="row" style="padding-top: 60px">
                    <h4>Các bài báo tương tự</h4>
                    <c:forEach var="ran" items="${requestScope.ran3}">
                        <div class="row">
                            >>>> <a href="ProcessServlet?location=article&id=${ran.id}">${ran.tittle}</a>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>
