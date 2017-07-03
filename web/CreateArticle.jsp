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
                <div class="row">
                    <button type="button" class="button-green" onclick="AddBigWord()">Add Big Word</button>
                    <button type="button" class="button-green" onclick="AddParagraph()">Add Paragraph</button>
                    <button type="button" class="button-green" onclick="AddImage()">Add Image</button>
                </div>
                <div class="row">
                    <div id="article">
                    </div>
                </div>
                <div class="break-line"><hr/></div>
                <div class="row">
                    <button type="button" class="button-green" onclick="UploadMultipleImages()">Submit</button>
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>

<script>
    var article = document.getElementById("article");

    function AddBigWord() {
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
        var container = document.createElement("p");
        container.className = "parent";
        container.innerHTML = "<input name='image' type='file' accept='image/*' class='form-control'/> <button class='delete'>X</button>";
        article.appendChild(container);

        var x = document.querySelectorAll(".parent");
        for (var i = 0; i < x.length; i++) {
            x[i].querySelector(".delete").onclick = registerClickHandler;
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
        var e = document.getElementById("article").querySelectorAll('*');
        for (var i = 0; i < e.length; i++) {
            if (e[i].getAttribute("name") == "head" && e[i].value != "") {
                content += '<p><b>' + e[i].value + '</b></p>';
            } else if (e[i].getAttribute("name") == "paragraph" && e[i].value != "") {
                content += '<p>' + e[i].value + '</p>';
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

        var e = document.getElementById("article").querySelectorAll('*');
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

        console.log(xmlDoc);
        
        Submit();
    }

    function Submit() {
        var xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        var url = "ProcessServlet?location=createarticle&content=" + "<?xml version='1.0' encoding='UTF-8'?>" + new XMLSerializer().serializeToString(xmlDoc);
        xmlHttp.open("POST", url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                var result = JSON.parse(xmlHttp.responseText);
                console.log(result);
            }
        };
        xmlHttp.send(url);
    }
</script>