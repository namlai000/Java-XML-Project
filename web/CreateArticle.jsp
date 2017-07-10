<%-- 
    Document   : Article
    Created on : May 30, 2017, 9:43:43 PM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Article</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <div class="row">
                    <h2>Title</h2>
                    <p><input id="tittle" type="text" class="form-control" placeholder="Title"/></p>
                    <h3>Description</h3>
                    <p><input id="description" type="text" class="form-control" placeholder="Description"/></p>
                </div>
                <div class="break-line"><hr/></div>
                <div class="tab">
                    <c:if test="${requestScope.cur.tblUser.role.id lt 2}">
                        <button class="tablinks" onclick="openTab(event, 'div1');changeType(1);">Viết báo</button>
                    </c:if>
                    <c:if test="${requestScope.cur.tblUser.role.id lt 3}">
                        <button class="tablinks" onclick="openTab(event, 'div2');changeType(2);">Viết góc nhìn</button>
                    </c:if>
                </div>
                <div id="div1" class="tabcontent">
                    <div class="row">
                        <button type="button" class="button-green" onclick="AddBigWord()">Thêm chữ to</button>
                        <button type="button" class="button-green" onclick="AddParagraph()">Thêm đoạn văn</button>
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
                    <div id="error1">
                    </div>
                    <div class="break-line"><hr/></div>
                    <div class="row">
                        <button type="button" class="button-green" onclick="UploadMultipleImages()">Đăng bài</button>
                    </div>
                </div>
                <div id="div2" class="tabcontent">
                    <div class="row">
                        <button type="button" class="button-green" onclick="AddBigWord()">Thêm chữ to</button>
                        <button type="button" class="button-green" onclick="AddParagraph()">Thêm đoạn văn</button>
                    </div>
                    <div class="row">
                        <div id="article2">
                        </div>
                    </div>
                    <div id="error2">
                    </div>
                    <div class="break-line"><hr/></div>
                    <div class="row">
                        <button type="button" class="button-green" onclick="UploadMultipleImages()">Đăng bài</button>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>

<script>
    console.log(${requestScope.cur.tblUser.role.id});
    
    var typeArticle = '1';

    var ar = 'article1';
    var error = 'error1';

    function changeType(val) {
        typeArticle = val;
        if (val == 1) {
            ar = 'article1';
            error = 'error1';
        } else {
            ar = 'article2';
            error = 'error2';
        }
        console.log(typeArticle + ar);
        var x = document.querySelectorAll(".parent");
        for (var i = 0; i < x.length; i++) {
            x[i].querySelector(".delete").click()
        }
    }

    function AddBigWord() {
        var article = document.getElementById(ar);
        var container = document.createElement("p");
        container.className = "parent";
        container.innerHTML = "<input name='head' type='text' class='form-control' placeholder='New big word'/> <button class='delete'>X</button>";
        article.appendChild(container);

        var x = document.querySelectorAll(".parent");
        for (var i = 0; i < x.length; i++) {
            x[i].querySelector(".delete").onclick = registerClickHandler;
        }
    }

    function AddParagraph() {
        var article = document.getElementById(ar);
        var container = document.createElement("p");
        container.className = "parent";
        container.innerHTML = "<textarea name='paragraph' class='form-control' style='resize: none' rows='8' placeholder='New paragraph'></textarea> <button class='delete'>X</button>";
        article.appendChild(container);

        var x = document.querySelectorAll(".parent");
        for (var i = 0; i < x.length; i++) {
            x[i].querySelector(".delete").onclick = registerClickHandler;
        }
    }

    function AddImage() {
        var article = document.getElementById(ar);
        var container = document.createElement("p");
        container.className = "parent";
        container.innerHTML = "<img class='viewer' src='#' style='max-height: 200px;'/><input name='image' type='file' accept='image/*' class='form-control' onchange='readURL(this)'/> <button class='delete'>X</button>";
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
        var newText = xmlDoc.createCDATASection(content);
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

        if (ar == 'article1') {
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
        }


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

    function openTab(evt, cityName) {
        // Declare all variables
        var i, tabcontent, tablinks;

        // Get all elements with class="tabcontent" and hide them
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }

        // Get all elements with class="tablinks" and remove the class "active"
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" active", "");
        }

        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(cityName).style.display = "block";
        evt.currentTarget.className += " active";
    }
</script>