<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/7/2023
  Time: 8:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<html>
<head>
    <title>showAlbum</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <img style="border-radius: 50px;height: 250px;width: 250px"
                 src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(partner.image)}"/>
        </div>
        <div class="col-lg-6">

        </div>
    </div>
</div>
<hr>
<form id="myForm" action="/album?action=upload&id=${partner.id}" method="post" enctype="multipart/form-data">
    <div class="container">
        <h4 style="text-align: center">Danh sách ảnh</h4>
        <div class="row">
            <c:forEach items="${albumList}" var="a">
                <c:if test="${a.image == null}">
                    <div class="col-lg-4" style="text-align: center;margin-top: 10px">
                        <img width="80" height="80" src="/img/default.png" alt="Default Image"
                             class="default-info-img"/>
                    </div>
                </c:if>
                <c:if test="${a.image != null}">
                    <div class="col-lg-4" style="text-align: center;margin-top: 10px">
                        <img style="border-radius:30px;height: 250px;width: 250px"
                             src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(a.image)}"/>
                    </div>
                </c:if>
            </c:forEach>
            <div style="margin-top: 5px;" class="file btn btn-lg btn-primary">
                Thêm Ảnh
                <input oninput="submitForm();" type="file" name="file"/>
            </div>
        </div>
    </div>
    </div>
</form>
</body>
<script>
    function submitForm() {
        var form = document.getElementById("myForm");
        form.submit();
    }
</script>
</html>
