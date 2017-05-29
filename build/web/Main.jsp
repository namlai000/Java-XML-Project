<%-- 
    Document   : index
    Created on : May 29, 2017, 8:14:48 AM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<!-- INSERT SCRIPTS HERE-->

<!-- LINK CSS HERE-->
<link href="CSS/Main.css" rel="stylesheet" type="text/css"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="/HeaderServlet" />
            
            <!-- BODY -->
            <div class="body-main">
                <div>
                    <h3>Các tin tức nổi bật</h3>
                    <table border="0">
                        <tr>
                            <td class="cover">
                                <div>
                                    <img src="Images/placeholder-blue.png" alt=""/>
                                </div>
                                <div>
                                    <h2>Tiêu đề 1</h2>
                                </div>
                                <div>
                                    Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                    Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                    Lorem Ipsum 
                                </div>
                            </td>
                            <td class="cover">
                                <div>
                                    <img src="Images/placeholder-blue.png" alt=""/>
                                </div>
                                <div>
                                    <h2>Tiêu đề 2</h2>
                                </div>
                                <div>
                                    Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                    Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                    Lorem Ipsum 
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="break-line">
                    <hr/>
                </div>
                <div>
                    <h3>Các tin mới</h3>
                    <table>
                        <tr>
                            <td>
                                <div class="article-image">
                                    <img src="Images/placeholder-blue.png" alt=""/>
                                </div>
                                <div class="article-text">
                                    <h2>Tiêu Đề 3</h2>
                                    <div>
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum
                                        </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="article-image">
                                    <img src="Images/placeholder-blue.png" alt=""/>
                                </div>
                                <div class="article-text">
                                    <h2>Tiêu Đề 3</h2>
                                    <div>
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum
                                        </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="article-image">
                                    <img src="Images/placeholder-blue.png" alt=""/>
                                </div>
                                <div class="article-text">
                                    <h2>Tiêu Đề 3</h2>
                                    <div>
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum 
                                        Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum
                                        </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            
            <!-- Footer -->
            <c:import url="/FooterServlet" />
        </div>
    </body>
</html>
