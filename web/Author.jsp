<%-- 
    Document   : Author
    Created on : May 31, 2017, 11:34:28 AM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Góc nhìn - Web Name</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="/header" />
            
            <!-- BODY -->
            <div class="body-main">
                <div class="container">
                    <c:set var="au1" value="${requestScope.top5}"/>
                    <c:forEach var="en" items="${au1}">
                        <div class="container-item">
                            <div>
                                <img src="${en.image}"/>
                            </div>
                            <h4>${en.firstname}<i> </i>${en.lastname}</h4>
                        </div>
                    </c:forEach>
                </div>
            </div>
            
            <!-- Footer -->
            <c:import url="/footer" />
        </div>
    </body>
</html>
