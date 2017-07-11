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
        <div class="body-centered">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <div class="row">
                    <h2>Title</h2>
                    <p><input id="tittle" type="text" class="form-control" placeholder="Title" value="${requestScope.article.tittle}"/></p>
                    <h3>Description</h3>
                    <p><input id="description" type="text" class="form-control" placeholder="Description" value="${requestScope.article.description}"/></p>
                </div>
                <div class="row">
                    <div class="row">
                        <button type="button" class="button-green" onclick="AddBigWord('')">Thêm chữ to</button>
                        <button type="button" class="button-green" onclick="AddParagraph('')">Thêm đoạn văn</button>
                        <button id="addimage" type="button" class="button-green" onclick="AddImage('')">Thêm hình ảnh</button>
                    </div>
                    <div id="article">
                    </div>
                </div>
                <div id="error">
                </div>
                <div class="row">
                    <button type="button" class="button-green" onclick="UploadMultipleImages()">Cập nhật bài</button>
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>

<script>
    var ar = 'article';
    var contentTmp = '${requestScope.article.tblNews.content}';
    <fm:formatDate value="${requestScope.article.date}" var="fmdate1" type="date" pattern="YYYY-MM-dd" />
    <fm:formatDate value="${requestScope.article.date}" var="fmdate2" type="date" pattern="hh:mm:ss" />
    var date = '${fmdate1}T${fmdate2}';
    console.log(date);

    var type = '${requestScope.article.type}';
    var views = '${requestScope.article.views}';

    if (type != '1') {
        var remove = document.getElementById("addimage");
        remove.outerHTML = "";
    }

    var category = '${requestScope.article.tblNews.catID.id}';
    var id = '${requestScope.article.id}';
    console.log(type + ' - ' + category + ' - ' + id);

    var count = 0;

    var images = [];

    window.onload = function () {
    <c:forEach items="${requestScope.article.tblNews.tblImageList}" var="images" varStatus="status">
        var a = new Object();
        a.id = '${images.id}';
        a.link = '${images.link}';
        images.push(a);
    </c:forEach>
        console.log(images);

        var res = contentTmp.split("</p>");
        console.log(res);
        for (var i = 0; i < res.length; i++) {
            if (res[i] != '') {
                var result = res[i];
                var format = result.replaceAll("<br/>", "\n").replaceAll("<p>", "");
                if (format.contains("<b>")) {
                    AddBigWord(format.replaceAll("<b>", "").replaceAll("</b>", ""));
                } else if (format.contains('<img name="image" src="none" class="ar-image"/>')) {
                    AddImage(images[count++].link);
                } else {
                    AddParagraph(format);
                }
            }
        }
    }

    function AddImage(link) {
        var article = document.getElementById(ar);
        var container = document.createElement("p");
        container.className = "parent";
        container.innerHTML = "<img class='viewer' src='" + link + "' style='max-height: 200px;'/><input name='image' type='file' accept='image/*' class='form-control' onchange='readURL(this)' /> <button class='delete'>X</button>";
        article.appendChild(container);

        var x = document.querySelectorAll(".parent");
        for (var i = 0; i < x.length; i++) {
            x[i].querySelector(".delete").onclick = registerClickHandler;
        }
    }

    function readURL(input) {
        console.log(input.parentNode);
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                input.parentNode.querySelector(".viewer").src = e.target.result;
            }
            reader.readAsDataURL(input.files[0]);
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
        var f = document.getElementsByClassName("viewer");
        for (var i = 0; i < e.length; i++) {
            if (e[i].files[0] == null || e[i].files[0] == '') {
                formData.append("image", f[i].src);
            } else {
                formData.append("image", e[i].files[0]);
            }
        }

//        for (var pair of formData.entries()) {
//            console.log(pair[0] + ', ' + pair[1]);
//        }

        var url = "ProcessServlet?location=uploadandcheck";
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
        var count = 0;
        var e = document.getElementById(ar).querySelectorAll('*');
        var f = document.getElementsByClassName("viewer");
        for (var i = 0; i < e.length; i++) {
            if (e[i].getAttribute("name") == "head" && e[i].value != "") {
                content += '<p><b>' + e[i].value + '</b></p>';
            } else if (e[i].getAttribute("name") == "paragraph" && e[i].value != "") {
                content += '<p>' + e[i].value.replace(/(?:\r\n|\r|\n)/g, '<br/>') + '</p>';
            } else if ((e[i].getAttribute("name") == "image" && e[i].value != '') || (e[i].getAttribute("name") == "image" && f[count++].src != '')) {
                content += '<p><img name="image" src="none" class="ar-image"/></p>';
            }
        }

        var node = xmlDoc.createElement("id");
        var newText = xmlDoc.createTextNode(id);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
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
        var node = xmlDoc.createElement("date");
        var newText = xmlDoc.createTextNode(date);
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

        var e = document.getElementById("article").querySelectorAll('*');
        var count = 0;
        var count2 = 0;
        for (var i = 0; i < e.length; i++) {
            if ((e[i].getAttribute("name") == "image" && e[i].value != '') || (e[i].getAttribute("name") == "image" && f[count2++].src != '')) {
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

        if (category != '') {
            var elements = xmlDoc.getElementsByTagName(child);
            var node = xmlDoc.createElement(child3);
            elements[0].appendChild(node);
            var node = xmlDoc.createElement("id");
            var newText = xmlDoc.createTextNode(category);
            node.appendChild(newText);
            var elements = xmlDoc.getElementsByTagName(child3);
            elements[0].appendChild(node);
        }

        var node = xmlDoc.createElement("type");
        var newText = xmlDoc.createTextNode(type);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("views");
        var newText = xmlDoc.createTextNode(views);
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

        var url = "ProcessServlet?location=editarticle" 
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
                    document.getElementById("error").innerHTML = "<font color='red'>" + data + "</font>";
                }
            }
        };
        xmlHttp.send("content=" + "<?xml version='1.0' encoding='UTF-8'?>" + new XMLSerializer().serializeToString(xmlDoc));
    }
</script>
