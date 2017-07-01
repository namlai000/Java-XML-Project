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
        <title>Login</title>
    </head>
    <body>
        <div style="margin: 0px auto 0px auto; width: 60%;">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <div class="row">
                    <div style="width: 50%; min-width: 400px; float: left;">
                        <div style="margin: 0px auto 0px auto; width: 60%; text-align: center">
                            <h3>Đăng nhập</h3>
                            <div>
                                <input id="usernameLogin" type="text" name="username" placeholder="Username" class="form-control"/>
                            </div>
                            <div>
                                <input id="passwordLogin" type="password" name="password" placeholder="Password" class="form-control"/>
                            </div>
                            <div id="errorLogin">
                            </div>
                            <button type="submit" class="button-green" onclick="Login()">Login</button>
                        </div>
                    </div>
                    <div style="width: 50%; min-width: 400px; float: left;">
                        <div style="margin: 0px auto 0px auto; width: 60%; text-align: center">
                            <h3>Đăng ký</h3>
                            <div>
                                <input id="username" type="text" name="username" placeholder="Username" class="form-control"/>
                            </div>
                            <div>
                                <input id="password" type="password" name="password" placeholder="Password" class="form-control"/>
                            </div>
                            <div>
                                <input id="firstname" type="text" name="firstname" placeholder="Firstname" class="form-control"/>
                            </div>
                            <div>
                                <input id="lastname" type="text" name="lastname" placeholder="Lastname" class="form-control"/>
                            </div>
                            <div>
                                <input id="iDNumber" type="number" name="iDNumber" placeholder="Identification Card Number" class="form-control"/>
                            </div>
                            <div id="error" style="text-align: left">
                            </div>
                            <button type="button" class="button-green" onclick="CreateXMLDom()">Đăng ký</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Footer -->
            <c:import url="PartialPages/Footer.jsp"/>
        </div>
    </body>
</html>

<script>
    var root = 'tblUserInfo';
    var child = 'tblUser';

    var xmlDoc;

    function CreateXMLDom() {
        xmlDoc = document.implementation.createDocument(null, root);

        var node = xmlDoc.createElement("firstname");
        var newText = xmlDoc.createTextNode(document.getElementById("firstname").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("lastname");
        var newText = xmlDoc.createTextNode(document.getElementById("lastname").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("iDNumber");
        var newText = xmlDoc.createTextNode(document.getElementById("iDNumber").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(root);
        elements[0].appendChild(node);

        var node = xmlDoc.createElement(child);
        elements[0].appendChild(node);

        var node = xmlDoc.createElement("username");
        var newText = xmlDoc.createTextNode(document.getElementById("username").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(child);
        elements[0].appendChild(node);
        var node = xmlDoc.createElement("password");
        var newText = xmlDoc.createTextNode(document.getElementById("password").value);
        node.appendChild(newText);
        var elements = xmlDoc.getElementsByTagName(child);
        elements[0].appendChild(node);

        console.log(xmlDoc);

        Register();
    }

    function Register() {
        document.getElementById("error").innerHTML = "";

        var xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        var url = "ProcessServlet?location=register&content=" + "<?xml version='1.0' encoding='UTF-8'?>" + new XMLSerializer().serializeToString(xmlDoc);
        xmlHttp.open("POST", url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                var result = JSON.parse(xmlHttp.responseText);
                if (result.success) {
                    alert("Register success!");
                    window.location.href = "ProcessServlet";
                } else {
                    var data = result.error;
                    document.getElementById("error").innerHTML = "<font color='red'>" + data + "</font>";
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
    
    function Login() {
        document.getElementById("errorLogin").innerHTML = "";

        var xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        var url = "ProcessServlet?location=login";
        xmlHttp.open("POST", url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                var result = JSON.parse(xmlHttp.responseText);
                if (result.success) {
                    alert("Login success!");
                    window.location.href = "ProcessServlet";
                } else {
                    var data = result.error;
                    document.getElementById("errorLogin").innerHTML = "<font color='red'>" + data + "</font>";
                }
            }
        };
        xmlHttp.send("username=" + document.getElementById("usernameLogin").value + "&password=" + document.getElementById("passwordLogin").value);
    }
</script>
