<%-- 
    Document   : Article
    Created on : May 30, 2017, 9:43:43 PM
    Author     : thegu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Author Article - Website Name</title>
    </head>
    <body>
        <div class="body-centered">
            <!-- Header -->
            <c:import url="PartialPages/Header.jsp"/>

            <!-- BODY -->
            <div class="body-main">
                <div class="row">
                    <c:import url="WEB-INF/authorarticle.xml" var="xmlDoc" charEncoding="UTF-8"/>
                    <c:import url="WEB-INF/authorarticle.xsl" var="xslDoc" charEncoding="UTF-8"/>
                    <x:transform xml="${xmlDoc}" xslt="${xslDoc}"/>
                    <div class="auArticle-image">
                        <img src="#" style="visibility: hidden"/>
                    </div>
                    <div class="auArticle-text">
                        <div class="row" style="padding-top: 60px">
                            <h4>Các bài viết tương tự</h4>
                            <c:forEach var="ran" items="${requestScope.ran3}">
                                <div class="row">
                                    >>>> <a href="ProcessServlet?location=read&id=${ran.id}">${ran.tittle}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="auArticle-image">
                        <img src="#" style="visibility: hidden"/>
                    </div>
                    <div class="auArticle-text">
                        <div class="row" style="padding-top: 48px">
                            <h5>Comments</h5>
                            <c:if test="${empty sessionScope.user}">
                                <div class="row">
                                    <div style="margin: 0px auto 0px auto; width: 60%; padding: 8px 8px 8px 8px; background-color: #ccccff; text-align: center;">
                                        Hãy <a href="ProcessServlet?location=loginPage">đăng nhập</a> trước khi comment
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${not empty sessionScope.user}">
                                <div>
                                    Post your comment: <input type="text" id="comment" placeholder="Write your comment ..." maxlength="150"/>
                                    <p id="error">Bạn còn 150 ký tự còn lại</p>
                                </div>
                            </c:if>
                            <table border="0" id="dataTable" style="margin-top: 24px;">
                            </table>
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
    var com = document.getElementById("comment");
    if (com != null) {
        com.addEventListener("keyup", function (event) {
            var s = document.getElementById("comment");
            var e = document.getElementById("error");
            var len = 150 - s.value.length
            var msg = 'Bạn còn ' + len + ' ký tự còn lại';
            e.innerHTML = msg;
            if (event.keyCode == 13) {
                event.preventDefault();
                document.getElementById("comment").disabled = true;
                if (s.value != '') {
                    postComment(s.value);
                }
            }
        });
    }

    function postComment(comment) {
        xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        var new_id = '${entity.id}';
        var user_id = '${sessionScope.user != null ? sessionScope.user.userId : "-1"}';

        var url = "ProcessServlet?location=post";
        xmlHttp.open("POST", url, true);
        xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                var result = JSON.parse(xmlHttp.responseText);
                if (result.success) {
                    alert('Success!');
                    var s = document.getElementById("comment");
                    s.value = '';
                    var e = document.getElementById("error");
                    var msg = 'Bạn còn 150 ký tự còn lại';
                    document.getElementById("comment").disabled = false;
                    e.innerHTML = msg;
                    traversalDOMTree(table);
                }
            }
        };
        xmlHttp.send("newid=" + new_id + "&userid=" + user_id + "&content=" + comment);
    }

    var cells = [];
    var new_XMLDOM = null;
    var xmlHttp;
    var xmlDOM;

    var table = 'dataTable';
    window.onload = traversalDOMTree(table);

    function addRow(tableId, cells) {
        var tableElem = document.getElementById(tableId);
        var newRow = tableElem.insertRow(tableElem.rows.length);
        var newCell;
        for (var i = 0; i < cells.length; i++) {
            newCell = newRow.insertCell(newRow.cells.length);
            newCell.innerHTML = cells[i];
        }

        return newRow;
    }

    function deleteRow(tableId, rowNumber) {
        var tableElem = document.getElementById(tableId);
        if (rowNumber < tableElem.rows.length) {
            tableElem.deleteRow(rowNumber);
        } else {
            console.log("Delete row failed");
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

    function searchNode(node, tableName) {
        if (node == null) {
            return;
        }

        if (node.tagName == "imageLink") {
            var sibling;
            var image = "<img src='" + node.firstChild.nodeValue + "' alt='' class='ar-image' onerror='this.src=Images/placeholder-blue.png/>";
            sibling = node.nextElementSibling;
            var fullname = sibling.firstChild.nodeValue;
            sibling = sibling.nextElementSibling;
            var content = sibling.firstChild.nodeValue;

            var text =
                    "<div class='row'>" +
                    "<div class='auArticle-image'>" +
                    image +
                    "</div>" +
                    "<div class='auArticle-text'>" +
                    "<h4>" + fullname + "</h4>" +
                    "<span>" + content + "</span>" +
                    "</div>" +
                    "</div>";

            cells[0] = text;
            addRow(tableName, cells);
        }

        var childs = node.childNodes;
        for (var i = 0; i < childs.length; i++) {
            searchNode(childs[i], tableName);
        }
    }

    function traversalDOMTree(tableName) {
        var tableElem = document.getElementById(tableName);
        var i = 0;
        while (i < tableElem.rows.length) {
            deleteRow(tableName, i);
        }
        update(tableName);
    }

    function update(tableName) {
        xmlHttp = GetXMLHttpObject();
        if (xmlHttp == null) {
            console.log("your browser does not support AJAX");
            return;
        }

        var new_id = '${entity.id}';
        var url = "ProcessServlet?location=comment&id=" + new_id;
        xmlHttp.open("GET", url, true);
        xmlHttp.setRequestHeader('Content-Type', 'application/xml');
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                xmlDOM = xmlHttp.responseXML;
                console.log(xmlDOM);
                searchNode(xmlDOM, tableName);
            }
        };
        xmlHttp.send();
    }
</script>
