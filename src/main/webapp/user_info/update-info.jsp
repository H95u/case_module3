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
    <title>Edit info</title>
    <link rel="stylesheet" href="/user_info/update.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="background: -webkit-linear-gradient(left, #3931af, #00c6ff);">
<form action="/userInfo?action=update&id=${user.id}" method="post">
    <div id="formRegister">
        <fieldset style="width: 700px;height: 600px">
            <legend style="text-align: center">Sửa thông tin</legend>
            <p>Họ và tên</p>
            <input type="text" placeholder="Họ và tên" name="regName" value="${user.fullName}"/><br>
            <p>Email</p>
            <input type="text" placeholder="Email" name="regEmail" value="${user.email}"/><br>
            <p>SĐT</p>
            <input type="text" placeholder="Số điện thoại" name="regPhoneNumber" value="${user.phoneNumber}"/><br>
            <p>Giới tính</p>
            <select name="gender">
                <option value="1" selected>Nam</option>
                <option value="0">Nữ</option>
                <option value="2">Khác</option>
            </select>
            <hr>
            <p>Địa chỉ</p>
            <input type="text" placeholder="Địa chỉ" name="regAddress" value="${user.address}"/>
            <p>Ngày sinh</p>
            <input type="date" name="regDOB" value="${user.dob}"/><br>
            <button style="margin-left: 150px" class="btn btn-warning" type="submit">
                Sửa
            </button>
            <button style="margin-left: 200px" class="btn btn-warning" >
                <a href="/userInfo?action=showInfo&id=${user.id}">
                Quay lại
                </a>
            </button>
        </fieldset>
    </div>
</form>
</body>
</html>
