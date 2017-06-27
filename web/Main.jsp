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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <div>
                    <h3>Các tin tức nổi bật</h3>
                    <table border="0">
                        <tr>
                            <td class="cover">
                                <div>
                                    <img src="Images/placeholder-blue.png" alt=""/>
                                </div>
                                <div>
                                    <h2>Tiêu đề 1</h2>
                                </div>
                                <div>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                </div>
                            </td>
                            <td class="cover">
                                <div>
                                    <img src="Images/placeholder-blue.png" alt=""/>
                                </div>
                                <div>
                                    <h2>Tiêu đề 2</h2>
                                </div>
                                <div>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="break-line">
                    <hr/>
                </div>
                <div class="row">
                    <div style="width: 75%">
                        <h3>Các tin mới</h3>
                        <table>
                            <c:set var="result" value="${requestScope.result}"/>
                            <c:if test="${not empty result}">
                                <c:forEach var="entity" items="${result}">
                                    <tr>
                                        <td>
                                            <div class="article-image">
                                                <img src="${entity.tblNewsList[0].tblImageList[0].link}" alt=""/>
                                            </div>
                                            <div class="article-text">
                                                <h2><a href="ProcessServlet?location=article&id=${entity.id}">${entity.tittle}</a></h2>
                                                <h5>${entity.tblNewsList[0].authorID.lastname}</h5>
                                                ${entity.description}
                                            </div>
                                            <div style="float: bottom">
                                                <fm:formatDate value="${entity.date}" var="fmDate" type="date" pattern="MM-dd-yyyy" />
                                                ${fmDate}
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>
