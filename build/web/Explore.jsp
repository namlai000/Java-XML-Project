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
        <!-- Header -->
        <c:import url="PartialPages/Header.jsp"/>

        <!-- BODY -->
        <div class="body-centered">
            <div class="body-main">
                <div class="left-menu">
                    <div>
                        <h3>Các chuyên mục</h3>
                        <ul>
                            <c:forEach var="entity" items="${requestScope.menuList}" varStatus="status">
                                <li>
                                    <h4>${entity.mainCategoryName}</h4>
                                    <ul>
                                        <c:forEach var="sub" items="${entity.tblSubCategoryList}" varStatus="status">
                                            <li>
                                                <a href="ProcessServlet?location=explore&menu=${sub.id}">
                                                    <c:if test="${param.menu eq sub.id or (empty param.menu and sub.id eq 1)}"><b></c:if>
                                                        ${sub.subCategoryName}
                                                        <c:if test="${param.menu eq sub.id or (empty param.menu and sub.id eq 1)}"></b></c:if>
                                                    </a>
                                                </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
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
                                                <c:when test="${status.first and ((empty param.page) or (param.page eq 1))}">
                                                    <div class="first-image">
                                                        <img src="${entity.tblNews.tblImageList[0].link}" onerror="this.src='Images/placeholder-blue.png'"/>
                                                    </div>
                                                    <div>
                                                        <h2><a href="ProcessServlet?location=article&id=${entity.id}">${entity.tittle}</a></h2>
                                                        <h5>${entity.tblNews.authorID.lastname}</h5>
                                                        ${entity.description}
                                                    </div>
                                                    <div class="break-line">
                                                        <hr/>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="article-image">
                                                        <img src="${entity.tblNews.tblImageList[0].link}" onerror="this.src='Images/placeholder-blue.png'"/>
                                                    </div>
                                                    <div class="article-text">
                                                        <h4><a href="ProcessServlet?location=article&id=${entity.id}">${entity.tittle}</a></h4>
                                                        <h5>${entity.tblNews.authorID.lastname}</h5>
                                                        ${entity.description}
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <hr/>
                        <div style="float: left">
                            Trang ${param.page}<c:if test="${empty param.page}">1</c:if> trong tổng số ${fn:length(requestScope.pages)}
                            </div>
                            <div style="float: right">
                            <c:forEach var="pages" items="${requestScope.pages}" >
                                <span>
                                    <c:set var="a" value="${param.menu}"/>
                                    <c:if test="${not empty a}">
                                        <a href="ProcessServlet?location=explore&menu=${a}&page=${pages}">
                                        </c:if>
                                        <c:if test="${empty a}">
                                            <a href="ProcessServlet?location=explore&menu=1&page=${pages}">
                                            </c:if>
                                            <button type="button">
                                                <c:if test="${pages eq param.page}"><b></c:if>
                                                    ${pages}
                                                    <c:if test="${pages eq param.page}"></b></c:if>
                                                </button>
                                            </a>
                                    </span>
                            </c:forEach>
                        </div>
                    </c:if>
                </div> 
            </div>
        </div>

        <!-- Footer -->
        <c:import url="PartialPages/Footer.jsp"/>
    </body>
</html>
