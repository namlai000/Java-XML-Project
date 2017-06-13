<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="PartialPages/Plugins.jsp"/>
<div>
    <table border="0">
        <tr class="nav-header">
            <td colspan="5">
                <h2>
                    Title Placeholder
                    <span style="display: inline-table; float: right;">
                        <c:choose>
                            <c:when test="${not empty sessionScope.username and not empty sessionScope.password}">
                                <span class="small-avatar"><img src="Images/avatar.png" alt=""/></span> ${sessionScope.username} ${sessionScope.password}
                            </c:when>
                            <c:otherwise>
                                <a href="ProcessServlet?location=loginPage">Login / Sign Up</a>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </h2>
            </td>
        </tr>
        <tr class="nav-bar">
            <td>
                <a href="ProcessServlet">Trang chủ</a>
            </td>
            <td>
                <a href="ProcessServlet?location=explore">Khám Phá</a>
            </td>
            <td>
                <a href="ProcessServlet?location=author">Góc nhìn</a>
            </td>
            <td>
                Về chúng tôi
            </td>
        </tr>
    </table>
</div>