<%-- 
    Document   : index
    Created on : May 29, 2017, 8:14:48 AM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Articles</title>
    </head>
    <body>
        <!-- Header -->
        <c:import url="PartialPages/Header.jsp"/>

        <!-- BODY -->
        <div class="body-centered">
            <div class="body-main">
                <div class="row">
                    <form method="POST" action="ProcessServlet?location=crawldata">
                        <input placeholder="VNExpress Link" name="link" class="form-control"/>
                        <button type="Submit" class="button-green">Crawl</button>
                    </form>
                    <c:if test="${not empty sessionScope.crawl}">
                        <div class="row">
                            <h2>Title</h2>
                            <p><input id="tittle" type="text" class="form-control" placeholder="Title" value="${sessionScope.crawl.tittle}"/></p>
                            <h3>Description</h3>
                            <p><input id="description" type="text" class="form-control" placeholder="Description" value="${sessionScope.crawl.description}"/></p>
                        </div>
                        <h3>Content</h3>
                        <div class="row">
                            <div id="article">
                            </div>
                        </div>
                        <div id="error">
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <c:import url="PartialPages/Footer.jsp"/>
    </body>
</html>

<c:if test="${not empty sessionScope.crawl}">
    <script>
        var ar = 'article';
        var contentTmp = '${sessionScope.crawl.tblNews.content}';

        window.onload = function () {
            var res = contentTmp.split("</p>");
            console.log(res);
            for (var i = 0; i < res.length; i++) {
                if (res[i] != '') {
                    var result = res[i];
                    var format = result.replaceAll("<br/>", "\n").replaceAll("<p>", "");
                    if (format.contains("<b>")) {
                        AddBigWord(format.replaceAll("<b>", "").replaceAll("</b>", ""));
                    } else if (format.contains('<img name="image" src="none" class="ar-image"/>')) {
                        AddImage();
                    } else {
                        AddParagraph(format);
                    }
                }
            }
        }

        function AddImage() {
            var article = document.getElementById(ar);
            var container = document.createElement("p");
            container.className = "parent";
            container.innerHTML = "<img class='viewer' src='none' style='max-height: 200px;'/><input name='image' type='file' accept='image/*' class='form-control' /> <button class='delete'>X</button>";
            article.appendChild(container);

            var x = document.querySelectorAll(".parent");
            for (var i = 0; i < x.length; i++) {
                x[i].querySelector(".delete").onclick = registerClickHandler;
            }
        }

        function AddBigWord(content) {
            var article = document.getElementById(ar);
            var container = document.createElement("p");
            container.className = "parent";
            container.innerHTML = "<input name='head' type='text' class='form-control' placeholder='New big word' value='" + content + "'/> <button class='delete'>X</button>";
            article.appendChild(container);

            var x = document.querySelectorAll(".parent");
            for (var i = 0; i < x.length; i++) {
                x[i].querySelector(".delete").onclick = registerClickHandler;
            }
        }

        function AddParagraph(content) {
            var article = document.getElementById(ar);
            var container = document.createElement("p");
            container.className = "parent";
            container.innerHTML = "<textarea name='paragraph' class='form-control' style='resize: none' rows='8' placeholder='New paragraph'>" + content + "</textarea> <button class='delete'>X</button>";
            article.appendChild(container);

            var x = document.querySelectorAll(".parent");
            for (var i = 0; i < x.length; i++) {
                x[i].querySelector(".delete").onclick = registerClickHandler;
            }
        }

        String.prototype.replaceAll = function (search, replacement) {
            var target = this;
            return target.replace(new RegExp(search, 'g'), replacement);
        };
        
        String.prototype.contains = function (replace) {
            return this.indexOf(replace) != -1;
        };
        
        function registerClickHandler() {
            this.parentNode.parentNode.removeChild(this.parentNode);
        }
    </script>
</c:if>