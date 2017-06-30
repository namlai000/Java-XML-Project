<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="PartialPages/Plugins.jsp"/>
<div>
    <table border="0">
        <tr class="nav-header">
            <td colspan="5">
                <div>
                    <h2 style="display: inline-table">Title Placeholder</h2>
                    <span style="display: inline-table">
                        <form id="form" action="ProcessServlet">
                            <input type="hidden" name="location" value="search"/>
                            <input id="txtSearch" type="text" name="query" placeholder="Search"/>
                        </form>
                    </span>
                    <span style="display: inline-table; float: right; margin-top: 30px">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <span class="small-avatar"><img src="${sessionScope.user.imageID.link}" alt=""/></span> ${sessionScope.user.lastname}
                                </c:when>
                                <c:otherwise>
                                <a href="ProcessServlet?location=loginPage">Login / Sign Up</a>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>
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

<script>
    document.getElementById("txtSearch")
            .addEventListener("keydown", function (event) {
                if (event.keyCode == 13) {
                    event.preventDefault();
                    var s = document.getElementById("txtSearch");
                    if (s.value != '') {
                        document.getElementById("form").submit();
                    }
                }
            });
</script>