<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/3/2023
  Time: 6:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/user_info/user-info.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</head>
<body>
<div>
        <a href="/home">
            <img style="width: 50px;height: 50px" src="/img/logo.png">
        </a>
    <h1 style="text-align: center;color: white">THÔNG TIN CÁ NHÂN</h1>
</div>
<hr>
<div class="container emp-profile">
    <form id="myForm" method="post" action="/userInfo?action=upload&id=${user.id}" enctype="multipart/form-data">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <c:if test="${user.image == null}">
                        <img src="/img/default.png" alt="Default Image" class="default-info-img"/>
                    </c:if>
                    <c:if test="${user.image != null}">
                        <img style="border-radius: 50px;height: 250px;width: 250px"
                             src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(user.image)}"/>
                    </c:if>
                    <div style="margin-top: 5px" class="file btn btn-lg btn-primary" onclick="showUploadBtn()">
                        Thêm Ảnh
                        <input oninput="submitForm()" type="file" name="file"/>
                    </div>
                </div>
            </div>
            <button id="submit-upload-btn" type="submit" style="display: none;
            width: 100px;height: 50px;position: fixed;
            margin-top: 290px;margin-left: 100px">Submit
            </button>
            <div class="col-md-4">
                <div class="profile-head">
                    <h5>
                        HỌ & TÊN
                    </h5>
                    <h6>
                        Địa Chỉ
                    </h6>
                    <p class="proile-rating">Loại DV : <span>Giá/h</span></p>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                               aria-controls="home" aria-selected="true">Thông tin chi tiết</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                               aria-controls="profile" aria-selected="false">Timeline</a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-2">
                <a href="/userInfo?action=update&id=${user.id}"> <input class="profile-edit-btn" name="btnAddMore"
                                                                        value="Sửa thông tin"/></a>
            </div>
            <div class="col-md-2">
                <input class="profile-edit-btn" name="btnAddMore" value="Trạng thái"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="profile-work">
                    <p>FACEBBOOK LINK</p>
                    <a href="">FaceBook Link</a><br/>
                    <!--            <a href="">Bootsnipp Profile</a><br/>-->
                    <!--            <a href="">Bootply Profile</a>-->
                    <p>MÔ TẢ</p>
                    <p>Lịch sự và chuyên nghiệp</p>
                    <!--            <a href="">Web Designer</a><br/>-->
                    <!--            <a href="">Web Developer</a><br/>-->
                    <!--            <a href="">WordPress</a><br/>-->
                    <!--            <a href="">WooCommerce</a><br/>-->
                    <!--            <a href="">PHP, .Net</a><br/>-->
                </div>
            </div>
            <div class="col-md-8">
                <div class="tab-content profile-tab" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <label>Họ và tên</label>
                            </div>
                            <div class="col-md-6">
                                <p>${user.fullName}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Địa chỉ</label>
                            </div>
                            <div class="col-md-6">
                                <p>${user.address}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Email</label>
                            </div>
                            <div class="col-md-6">
                                <p>${user.email}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>SĐT</label>
                            </div>
                            <div class="col-md-6">
                                <p>${user.phoneNumber}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label>Ngày Sinh</label>
                            </div>
                            <div class="col-md-6">
                                <p>${user.dob}</p>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </form>
</div>
</body>
<script>
    function submitForm() {
        var form = document.getElementById("myForm");
        form.submit();
    }
</script>
</html>
