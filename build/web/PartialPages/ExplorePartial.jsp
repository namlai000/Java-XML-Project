<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
