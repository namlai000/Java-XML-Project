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
                            <input id="txtSearch" type="text" name="query" placeholder="Search" class="form-control form-search"/>
                        </form>
                    </span>
                    <span style="display: inline-table; float: right; margin-top: 30px">
                        <c:set var="user" value="${sessionScope.user}"/>
                        <c:choose>
                            <c:when test="${not empty user}">
                                <div class="dropdown">
                                    <div onclick="myFunction()" class="dropbtn">
                                        <span class="small-avatar"><img src="${user.imageID.link}" alt="" onerror="this.src='Images/avatar.png'"/></span> ${user.lastname}
                                    </div>
                                    <div id="myDropdown" class="dropdown-content">
                                        <c:if test="${user.tblUser.role.id ne 3}">
                                            <a href="ProcessServlet?location=create">Write Article</a>
                                            <a href="#">Edit your Articles</a>
                                        </c:if>
                                        <a href="ProcessServlet?location=profile">Your profile</a>
                                        <hr/>
                                        <a href="ProcessServlet?location=logout">Logout</a>
                                    </div>
                                </div>
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

    function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {

            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }
</script>