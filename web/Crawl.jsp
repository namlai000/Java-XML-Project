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
                    <c:if test="${not empty requestScope.crawl}">
                        <div class="row">
                            <h2>Title</h2>
                            <p><input id="tittle" type="text" class="form-control" placeholder="Title" value="${requestScope.crawl.tittle}"/></p>
                            <h3>Description</h3>
                            <p><input id="description" type="text" class="form-control" placeholder="Description" value="${requestScope.crawl.description}"/></p>
                        </div>
                        <h3>Content</h3>
                        <div class="row">
                            <button type="button" class="button-green" onclick="AddBigWord('')">Thêm chữ to</button>
                            <button type="button" class="button-green" onclick="AddParagraph('')">Thêm đoạn văn</button>
                            <button type="button" class="button-green" onclick="AddImage()">Thêm hình ảnh</button>
                        </div>
                        <div class="row">
                            <div id="article1">
                                <select id="select" class="form-control">
                                    <c:forEach var="select" items="${requestScope.cats}">
                                        <option value="${select.id}">${select.subCategoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div id="article">
                            </div>
                        </div>
                        <div id="error">
                        </div>
                        <div class="row">
                            <button type="button" class="button-green" onclick="UploadMultipleImages()">Đăng bài</button>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <c:import url="PartialPages/Footer.jsp"/>
    </body>
</html>

<c:if test="${not empty requestScope.crawl}">
    <script>
        var ar = 'article';
        console.log(${requestScope.cur.tblUser.role.id});
        var typeArticle = '1';
        var error = 'error';
        var contentTmp = '${requestScope.crawl.tblNews.content}';

        window.onload = function () {
            var res = contentTmp.split("</p>");
            console.log(res);
            for (var i = 0; i < res.length; i++) {
                if (res[i] != '') {
                    var result = res[i];
                    var format = result.replaceAll("<br/>", "\n").replaceAll("<p>", "");
                    if (format.contains("<b>")) {
                        AddBigWord(format.replaceAll("<b>", "").replaceAll("</b>", ""));
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

        function GetXMLHttpObject() {
            var xmlHttp = null;
            try {
                xmlHttp = new XMLHttpRequest();
            } catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Mxsml2.XMLHTTP");
                } catch (e) {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
            }

            return xmlHttp;
        }

        var root = 'tblNewsHeader';
        var child = 'tblNews';
        var child2 = 'tblImageList';
        var child3 = 'catID';

        var xmlDoc;

        function UploadMultipleImages() {
            var xmlHttp = GetXMLHttpObject();
            if (xmlHttp == null) {
                console.log("your browser does not support AJAX");
                return;
            }

            var formData = new FormData();
            var e = document.getElementsByName("image");
            for (var i = 0; i < e.length; i++) {
                formData.append("image", e[i].files[0]);
            }

            var url = "ProcessServlet?location=upload&multi=true";
            xmlHttp.open("POST", url, true);
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    var result = JSON.parse(xmlHttp.responseText);
                    console.log(result);
                    if (result.success) {
                        CreateXMLDom(result.data);
                    }
                }
            };
            xmlHttp.send(formData);
        }

        function CreateXMLDom(data) {
            xmlDoc = document.implementation.createDocument(null, root);

            var content = '';
            var e = document.getElementById(ar).querySelectorAll('*');
            for (var i = 0; i < e.length; i++) {
                if (e[i].getAttribute("name") == "head" && e[i].value != "") {
                    content += '<p><b>' + e[i].value + '</b></p>';
                } else if (e[i].getAttribute("name") == "paragraph" && e[i].value != "") {
                    content += '<p>' + e[i].value.replace(/(?:\r\n|\r|\n)/g, '<br/>') + '</p>';
                } else if (e[i].getAttribute("name") == "image" && e[i].value != "") {
                    content += '<p><img name="image" src="none" class="ar-image"/></p>';
                } else if (e[i].getAttribute("name") == "video" && e[i].value != "") {
                    var regExp = /^.*(youtu\.be\/|v\/|u\/\w\/|embed\/|watch\?v=|\&v=)([^#\&\?]*).*/;
                    var match = e[i].value.match(regExp);
                    if (match && match[2].length == 11) {
                        var url = "https://www.youtube.com/embed/" + match[2] + "?autoplay=1";
                        content += '<p><iframe type="text/html" width="640" height="360" src="' + url + '" frameborder="0" allowfullscreen></iframe></p>';
                    }
                }
            }

            console.log(content);

            var node = xmlDoc.createElement("tittle");
            var newText = xmlDoc.createTextNode(document.getElementById("tittle").value);
            node.appendChild(newText);
            var elements = xmlDoc.getElementsByTagName(root);
            elements[0].appendChild(node);
            var node = xmlDoc.createElement("description");
            var newText = xmlDoc.createTextNode(document.getElementById("description").value);
            node.appendChild(newText);
            var elements = xmlDoc.getElementsByTagName(root);
            elements[0].appendChild(node);

            var node = xmlDoc.createElement(child);
            elements[0].appendChild(node);

            var node = xmlDoc.createElement("content");
            var newText = xmlDoc.createCDATASection(encodeURIComponent(content));
            node.appendChild(newText);
            var elements = xmlDoc.getElementsByTagName(child);
            elements[0].appendChild(node);

            var e = document.getElementById(ar).querySelectorAll('*');
            var count = 0;
            for (var i = 0; i < e.length; i++) {
                if (e[i].getAttribute("name") == "image" && e[i].value != "") {
                    var tmp = count++;

                    var elements = xmlDoc.getElementsByTagName(child);
                    var node = xmlDoc.createElement(child2);
                    elements[0].appendChild(node);

                    var node2 = xmlDoc.createElement("id");
                    var newText = xmlDoc.createTextNode(data[tmp].id);
                    node2.appendChild(newText);
                    node.appendChild(node2);
                    var node2 = xmlDoc.createElement("link");
                    var newText = xmlDoc.createTextNode(data[tmp].link);
                    node2.appendChild(newText);
                    node.appendChild(node2);
                }
            }

            var elements = xmlDoc.getElementsByTagName(child);
            var e = document.getElementById("select");
            var selected = e.options[e.selectedIndex].value;
            var node = xmlDoc.createElement(child3);
            elements[0].appendChild(node);
            var node = xmlDoc.createElement("id");
            var newText = xmlDoc.createTextNode(selected);
            node.appendChild(newText);
            var elements = xmlDoc.getElementsByTagName(child3);
            elements[0].appendChild(node);

            var node = xmlDoc.createElement("type");
            var newText = xmlDoc.createTextNode(typeArticle);
            node.appendChild(newText);
            var elements = xmlDoc.getElementsByTagName(root);
            elements[0].appendChild(node);

            console.log(xmlDoc);

            Submit();
        }

        function Submit() {
            var xmlHttp = GetXMLHttpObject();
            if (xmlHttp == null) {
                console.log("your browser does not support AJAX");
                return;
            }

            var url = "ProcessServlet?location=createarticle";
            xmlHttp.open("POST", url, true);
            xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=UTF-8');
            xmlHttp.onreadystatechange = function () {
                if (xmlHttp.readyState == 4) {
                    var result = JSON.parse(xmlHttp.responseText);
                    if (result.success) {
                        alert("Update success!");
                        window.location.href = 'ProcessServlet?location=main';
                    } else {
                        var data = result.error;
                        document.getElementById(error).innerHTML = "<font color='red'>" + data + "</font>";
                    }
                }
            };
            xmlHttp.send("content=" + "<?xml version='1.0' encoding='UTF-8'?>" + new XMLSerializer().serializeToString(xmlDoc));
        }
    </script>
</c:if>