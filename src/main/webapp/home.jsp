<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Lover web</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="home.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="header">
    <div class="row">
        <div class="col-lg-3">
            <a href="/home" style="position: fixed;z-index: 10">
                <img style="width: 50px;height: 50px" src="/img/logo.png">
            </a>
        </div>
        <div class="col-lg-6">
            <select>
                <option>Dịch vụ</option>
                <c:forEach var="o" items="${optionList}">
                    <option oninput="searchByOption(${o.id});">${o.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-lg-3">
            <c:if test="${user != null}">
                <c:if test="${user.image != null}">
                    <a href="/userInfo?action=showInfo&id=${user.id}">
                        <img width="50" height="50" style="float: right;border-radius: 30px"
                             src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(user.image)}"/>
                    </a>
                </c:if>
                <c:if test="${user.image == null}">
                    <a href="/userInfo?action=showInfo&id=${user.id}">
                        <img height="50" width="50" src="/img/default.png" alt=""
                             style="float: right;border-radius: 5px">
                    </a>
                </c:if>
            </c:if>
        </div>
    </div>
</div>
<div style="margin-top: 40px;margin-left: 320px" class="banner">
    <img style="width: 100%;height: 150px;border-radius: 30px" src="img/39954a5f77a37fdcfbb7e64562491215.jpg">
</div>
<hr>
<h4 style="text-align: center;font-style: italic;color: #de093f">Danh sách các hot girl , hot boy</h4>
<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;"
     id="mySidebar"><br>

    <div class="w3-container">
        <h3 class="w3-padding-64"><b>Home</b></h3>
    </div>
    <div class="w3-bar-block">
        <a href="#" class="w3-bar-item w3-button w3-hover-white">Trang chủ</a>
        <a href="#showcase" class="w3-bar-item w3-button w3-hover-white">Danh mục</a>
        <a href="#services" class="w3-bar-item w3-button w3-hover-white">Services</a>
        <a href="#designers" class="w3-bar-item w3-button w3-hover-white">Contact</a>
        <c:if test="${sessionScope.user == null}">
            <a href="/login/login.jsp" class="w3-bar-item w3-button w3-hover-white">Đăng nhập</a>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <a href="/home?action=logout" class="w3-bar-item w3-button w3-hover-white">Đăng xuất</a>
        </c:if>
        <c:if test="${sessionScope.user.userRole == 1 }">
            <a href="/partners?action=create" class="w3-bar-item w3-button w3-hover-white">Thêm đối tác mới</a>
        </c:if>
    </div>
</nav>

<div class="body-content" style="margin-left: 300px;text-align: center;margin-top: 10px">

    <div class="row">
        <c:forEach var="p" items="${partnerList}">
            <div class="col-lg-3" style="border: 1px dotted #98b215;padding: 10px">
                <a href="/home?action=partnerInfo&id=${p.id}">
                    <c:if test="${p.image != null}">
                        <img style="border-radius: 50px" width="200" height="200"
                             src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(p.image)}"/>
                    </c:if>
                    <p>Nickname : ${p.nickname}</p>
                    <p>Giá : ${p.hourlyRate}</p>
                    <c:if test="${p.availability == 1}">
                        <p>Trạng thái : Có thể thuê</p>
                    </c:if>
                    <c:if test="${p.availability == 0}">
                        <p>Trạng thái : Đang bận</p>
                    </c:if>
                </a>
            </div>
        </c:forEach>
    </div>
    <!-- Footer-->
    <div class="w3-light-grey w3-container w3-padding-32"
         style="margin-top:75px;padding-right:58px;background: -webkit-linear-gradient(left, #3931af, #00c6ff);"><p
            class="w3-right">
        WEB THUÊ NGƯỜI YÊU</p>
    </div>
</div>
</body>
<script>
    function searchByOption(optionId) {

    }
</script>
</html>