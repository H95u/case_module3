<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/4/2023
  Time: 12:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit info</title>
    <link rel="stylesheet" href="/user_info/update.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="background: -webkit-linear-gradient(left, #3931af, #00c6ff);">
<form action="/home?action=update&id=${partner.id}" method="post">
    <div id="formRegister">
        <fieldset style="width: 700px;height: 600px">
            <legend style="text-align: center">Sửa thông tin</legend>
            <p>Nick name</p>
            <input type="text" placeholder="Họ và tên" name="name" value="${partner.nickname}"/><br>
            <p>Giá mỗi giờ</p>
            <input type="text" placeholder="Email" name="hourlyRate" value="${partner.hourlyRate}"/><br>
            <p>Trạng thái</p>
            <select name="availability">
                <option value="1" selected>Có thể thuê</option>
                <option value="0">Đang bận</option>
            </select>
            <p>Giới tính</p>
            <select name="gender">
                <option value="1">Nam</option>
                <option value="0" ${partner.gender == 0 ? 'selected' : ''}>Nữ</option>
            </select>
            <hr>
            <p>Địa chỉ</p>
            <input type="text" placeholder="Địa chỉ" name="address" value="${partner.address}"/>
            <p>Ngày sinh</p>
            <input type="date" name="DOB" value="${partner.dob}"/><br>
            <button style="margin-left: 150px" class="btn btn-warning" type="submit">
                Sửa
            </button>
            <button style="margin-left: 200px" class="btn btn-warning">
                <a href="/home?action=partnerInfo&id=${partner.id}">
                    Quay lại
                </a>
            </button>
        </fieldset>
    </div>
</form>
</body>
</html>
