<%-- 
    Document   : Author
    Created on : May 31, 2017, 11:34:28 AM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Góc nhìn - Web Name</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <div class="container">
                    <h2>Các tác giả tiêu biểu tuần này</h2>
                    <c:set var="au1" value="${requestScope.top5}"/>
                    <c:forEach var="en" items="${au1}">
                        <div class="container-item">
                            <div>
                                <img src="${en.image}"/>
                            </div>
                            <h4><a href="ProcessServlet?location=detail&id=${en.id}">${en.firstname}<i> </i>${en.lastname}</a></h4>
                        </div>
                    </c:forEach>
                </div>
                <div class="break-line">
                    <hr/>
                </div>
                <div class="container">
                    <div class="left-menu">
                        <h3>Tác giả mới nhất</h3>
                        <ul>
                            <c:set var="au1" value="${requestScope.newest10}"/>
                            <c:forEach var="en" items="${au1}">
                                <li>
                                    <div class="container">
                                        <div class="small-avatar">
                                            <img src="${en.image}"/>
                                        </div>
                                        <div>
                                            <a href="ProcessServlet?location=detail&id=${en.id}">${en.firstname}<i> </i>${en.lastname}</a> 
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="right-menu">
                        <h3>Góc nhìn mới nhất</h3>
                        <table>
                            <c:set var="au2" value="${requestScope.authorList}"/>
                            <c:forEach var="en" items="${au2}">
                                <tr>
                                    <td>
                                        <div class="article-image avatar">
                                            <img src="${en.author.image}"/>
                                        </div>
                                        <div class="article-text">
                                            <h3><a href="ProcessServlet?location=read&id=${en.id}">${en.title}</a></h3>
                                            <h5><a href="ProcessServlet?location=detail&id=${en.author.id}">${en.author.firstname}<i> </i>${en.author.lastname}</a></h5>
                                            <hr/>
                                            ${en.description}
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <hr/>
                        <div style="float: left">
                            Trang ${param.page}<c:if test="${empty param.page}">1</c:if> trong tổng số ${fn:length(requestScope.pages)}
                            </div>
                            <div style="float: right">
                            <c:forEach var="pages" items="${requestScope.pages}" >
                                <span>
                                    <a href="ProcessServlet?location=author&page=${pages}">
                                        <button type="button">
                                            <c:if test="${pages eq param.page}"><b></c:if>
                                                ${pages}
                                                <c:if test="${pages eq param.page}"></b></c:if>
                                            </button>
                                        </a>
                                    </span>
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
