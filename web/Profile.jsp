<%-- 
    Document   : Article
    Created on : May 30, 2017, 9:43:43 PM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <c:set var="user" value="${sessionScope.user}"/>
                <div class="row">
                    <div style="min-width: 400px">
                        <div style="margin: 0px auto 0px auto; width: 60%; text-align: center">
                            <h3>Thông tin cá nhân</h3>
                            <div>
                                <input id="firstname" type="text" placeholder="First name" value="${user.firstname}" class="form-control"/>
                            </div>
                            <div>
                                <input id="middlename" type="text" placeholder="Middle name" value="${user.middlename}" class="form-control"/>
                            </div>
                            <div>
                                <input id="lastname" type="text" placeholder="Last name" value="${user.lastname}" class="form-control"/>
                            </div>
                            <div>
                                <input id="address" type="text" placeholder="Address" value="${user.address}" class="form-control"/>
                            </div>
                            <div>
                                <input id="phone" type="number" placeholder="Phone" value="${user.phone}" class="form-control"/>
                            </div>
                            <div>
                                <img id="viewer" src="${user.imageID.link}" width="200px" height="200px" /> <input id="image" type="file" accept="image/*" class="form-control" onchange="readURL(this)"/>
                            </div>
                            <div>
                                <fm:formatDate value="${user.birthday}" var="fmDate" type="date" pattern="yyyy-MM-dd" />
                                <input id="birthday" type="date" placeholder="Birthday" value="${fmDate}" class="form-control"/>
                            </div>
                            <div id="error1">
                            </div>
                            <button type="type" class="button-green" onclick="UploadImage()">Cập nhật</button>
                        </div>
                    </div>
                </div>
                <!--                <div class="row">
                                    <div style="min-width: 400px">
                                        <div style="margin: 0px auto 0px auto; width: 40%; text-align: center">
                                            <h3>Thay đổi mật khẩu</h3>
                                            <div>
                                                <input id="oldpass" type="password" placeholder="Old Password" class="form-control"/>
                                            </div>
                                            <div>
                                                <input id="newpass" type="password" placeholder="New Password" class="form-control"/>
                                            </div>
                                            <div>
                                                <input id="confirmpass" type="password" placeholder="Confirm new Password" class="form-control"/>
                                            </div>
                                            <div id="error2">
                                            </div>
                                            <button type="button" class="button-green">Thay đổi</button>
                                        </div>
                                    </div>
                                </div>-->
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>

<script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                document.getElementById("viewer").src = e.target.result;
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    var root = 'tblUserInfo';
    var child = 'imageID';

    var xmlDoc;
    
    function UploadImage() {
        var xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        var formData = new FormData();
        formData.append("image", document.getElementById("image").files[0]);

        var url = "ProcessServlet?location=upload&multi=false";
        xmlHttp.open("POST", url, true);
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                var result = JSON.parse(xmlHttp.responseText);
                console.log(result);
                CreateXMLDom(result.location, result.id);
            }
        };
        xmlHttp.send(formData);
    }

    function CreateXMLDom(image, id) {
        xmlDoc = document.implementation.createDocument(null, root);

        var node = xmlDoc.createElement("firstname");
        var newText = xmlDoc.createTextNode(document.getElementById("firstname").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("middlename");
        var newText = xmlDoc.createTextNode(document.getElementById("middlename").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("lastname");
        var newText = xmlDoc.createTextNode(document.getElementById("lastname").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("address");
        var newText = xmlDoc.createTextNode(document.getElementById("address").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("phone");
        var newText = xmlDoc.createTextNode(document.getElementById("phone").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);

        var node = xmlDoc.createElement(child);
        elements[0].appendChild(node);
        
        var node = xmlDoc.createElement("id");
        var newText = xmlDoc.createTextNode(id);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(child);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("link");
        var newText = xmlDoc.createTextNode(image);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(child);
        elements[0].appendChild(node);

        var node = xmlDoc.createElement("birthday");
        var newText = xmlDoc.createTextNode(document.getElementById("birthday").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);

        console.log(xmlDoc);

        Update();
    }

    function Update() {
        document.getElementById("error1").innerHTML = "";

        var xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        var url = "ProcessServlet?location=editprofile&content=" + "<?xml version='1.0' encoding='UTF-8'?>" + new XMLSerializer().serializeToString(xmlDoc);
        xmlHttp.open("POST", url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                var result = JSON.parse(xmlHttp.responseText);
                if (result.success) {
                    alert("Update success!");
                    window.location.href = "ProcessServlet?location=profile";
                } else {
                    var data = result.error;
                    document.getElementById("error1").innerHTML = "<font color='red'>" + data + "</font>";
                }
            }
        };
        xmlHttp.send(url);
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
</script>
