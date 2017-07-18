<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="PartialPages/Plugins.jsp"/>
<div class="nav-top">
    <div class="body-centered">
        <div style="vertical-align: middle; text-align: right">
            <span style="display: inline-block; margin-right: 24px;">
                <c:set var="user" value="${sessionScope.user}"/>
                <c:choose>
                    <c:when test="${not empty user}">
                        <div class="dropdown">
                            <div onclick="myFunction()" class="dropbtn">
                                <span class="small-avatar"><img src="${user.imageID.link}" alt="" onerror="this.src='Images/avatar.png'"/></span> ${user.lastname}
                            </div>
                            <div id="myDropdown" class="dropdown-content">
                                <c:if test="${user.tblUser.role.id lt 3}">
                                    <a href="ProcessServlet?location=create">Write Article</a>
                                    <a href="ProcessServlet?location=manage">Manage your Articles</a>
                                </c:if>
                                <c:if test="${user.tblUser.role.id gt 3}">
                                    <a href="ProcessServlet?location=crawl">Craw Data From VNExpress</a>
                                    <a href="ProcessServlet?location=manage">Manage your Articles</a>
                                </c:if>
                                <a href="ProcessServlet?location=profile">Your profile</a>
                                <hr/>
                                <a href="ProcessServlet?location=logout">Logout</a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <a href="ProcessServlet?location=loginPage">Login | Sign Up</a>
                    </c:otherwise>
                </c:choose>
            </span>
            <span style="display: inline-block">
                <form id="form" action="ProcessServlet">
                    <input type="hidden" name="location" value="search"/>
                    <input id="txtSearch" type="text" name="query" placeholder="Search" class="form-control form-search"/>
                </form>
            </span>
        </div>
    </div>
</div>
<div class="body-centered">
    <table border="0" cellspacing="0">
        <tr class="nav-header">
            <td colspan="5">
                <div>
                    <div class="nav-logo">
                        <img src="Images/logo.png" alt=""/>   
                    </div>
                    <h2 style="display: inline-table">FPT Insiders</h2>
                </div>
            </td>
        </tr>
    </table>
</div>
<div>
    <div class="body-centered">
        <table border="0" cellspacing="0">
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
        <hr/>
    </div>
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