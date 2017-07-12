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
    <c:set var="author" value="${requestScope.author}"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author - Website Name</title>
    </head>
    <body>
        <!-- Header -->
        <c:import url="PartialPages/Header.jsp"/>

        <!-- BODY -->
        <div class="body-centered">
            <div class="body-main">
                <div class="auArticle-image">
                    <img src="${author.imageID.link}" onerror="this.src='Images/placeholder-blue.png'"/>
                </div>
                <div class="auArticle-text">
                    <div class="row">
                        <h3>${author.firstname}<i> </i>${author.middlename}<i> </i>${author.lastname}</h3>
                        <fm:formatDate value="${author.birthday}" var="fmDate" type="date" pattern="MM-dd-yyyy" />
                        Ngày sinh: ${fmDate}<br/>
                        Địa chỉ: ${author.address}<br/>
                        Số điện thoại: ${author.phone}<br/>
                    </div>
                    <div class="break-line">
                        <hr/>
                    </div>
                    <div class="row">
                        <c:if test="${not empty author.tblNewsList}">
                            <h2>Các bài viết đã đăng</h2>
                            <div style="margin: 0px auto 0px auto; width: 85%;">
                                <table>
                                    <tbody>
                                        <c:forEach var="entity" items="${author.tblNewsList}" varStatus="status">
                                            <tr>
                                                <td>
                                                    <div>
                                                        <h3><a href="ProcessServlet?location=read&id=${entity.tblNewsHeader.id}">${entity.tblNewsHeader.tittle}</a></h3>
                                                            ${entity.tblNewsHeader.description}
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table> 
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <c:import url="PartialPages/Footer.jsp"/>
    </body>
</html>
