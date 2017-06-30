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
                                <div class="dropdown">
                                    <div onclick="myFunction()" class="dropbtn">
                                        <span class="small-avatar"><img src="${sessionScope.user.imageID.link}" alt=""/></span> ${sessionScope.user.lastname}
                                    </div>
                                    <div id="myDropdown" class="dropdown-content">
                                        <a href="#">Write Article</a>
                                        <a href="#">Edit your Articles</a>
                                        <a href="#">Your profile</a>
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