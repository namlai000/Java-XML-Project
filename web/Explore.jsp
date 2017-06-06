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
                <div class="left-menu">
                    <c:import url="PartialPages/ExplorePartial.jsp"/>
                </div>
                <div class="right-menu">
                    <c:set var="result" value="${requestScope.exploreList}"/>
                    <c:if test="${not empty result}">
                        <table>
                            <tbody>
                                <c:forEach var="entity" items="${result}" varStatus="status">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${status.first}">
                                                    <div class="first-image">
                                                        <img src="${entity.image}" alt="" />
                                                    </div>
                                                    <div>
                                                        <h2><a href="ProcessServlet?location=article&id=${entity.id}">${entity.title}</a></h2>
                                                        ${entity.description}
                                                    </div>
                                                    <div class="break-line">
                                                        <hr/>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="article-image">
                                                        <img src="${entity.image}" alt=""/>
                                                    </div>
                                                    <div class="article-text">
                                                        <h4><a href="ProcessServlet?location=article&id=${entity.id}">${entity.title}</a></h4>
                                                        ${entity.description}
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>  
                            </tbody>
                        </table>
                    </c:if>
                </div> 
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>
