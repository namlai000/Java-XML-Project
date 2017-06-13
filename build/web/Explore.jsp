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
                    <div>
                        <h3>Xếp hạng</h3>
                        <ul>
                            <li>
                                <a href="ProcessServlet?location=explore&menu=1">
                                    <c:if test="${(empty param.menu) or (param.menu eq 1)}"><b></c:if>
                                            Các tin mới nổi
                                        <c:if test="${(empty param.menu) or (param.menu eq 1)}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=2">
                                    <c:if test="${param.menu eq 2}"><b></c:if>
                                            Nổi bật nhất
                                        <c:if test="${param.menu eq 2}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=3">
                                    <c:if test="${param.menu eq 3}"><b></c:if>
                                            Mới nhất
                                        <c:if test="${param.menu eq 3}"></b></c:if>
                                    </a>
                                </li>
                            </ul>
                            <h3>Các chuyên mục</h3>
                            <ul>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=4">
                                    <c:if test="${param.menu eq 4}"><b></c:if>
                                            Thời sự
                                        <c:if test="${param.menu eq 4}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=5">
                                    <c:if test="${param.menu eq 5}"><b></c:if>
                                            Quốc tế
                                        <c:if test="${param.menu eq 5}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=6">
                                    <c:if test="${param.menu eq 6}"><b></c:if>
                                            Công nghệ
                                        <c:if test="${param.menu eq 6}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=7">
                                    <c:if test="${param.menu eq 7}"><b></c:if>
                                            Sức khỏe
                                        <c:if test="${param.menu eq 7}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=8">
                                    <c:if test="${param.menu eq 8}"><b></c:if>
                                            Giải trí
                                        <c:if test="${param.menu eq 8}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=9">
                                    <c:if test="${param.menu eq 9}"><b></c:if>
                                            Xe cộ
                                        <c:if test="${param.menu eq 9}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=10">
                                    <c:if test="${param.menu eq 10}"><b></c:if>
                                            Thời trang
                                        <c:if test="${param.menu eq 10}"></b></c:if>
                                    </a>
                                </li>
                                <li>
                                    <a href="ProcessServlet?location=explore&menu=11">
                                    <c:if test="${param.menu eq 11}"><b></c:if>
                                            Bóng đá
                                        <c:if test="${param.menu eq 11}"></b></c:if>
                                    </a>
                                </li>
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
                                                        <img src="${entity.image}" alt="" />
                                                    </div>
                                                    <div>
                                                        <h2><a href="ProcessServlet?location=article&id=${entity.id}">${entity.title}</a></h2>
                                                        <h5>${entity.author}</h5>
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
                                                        <h5>${entity.author}</h5>    
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
                                    <a href="ProcessServlet?location=explore&menu=${param.menu}&page=${pages}">
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

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>
