<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/2/2023
  Time: 7:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="register.css">
<body class="background">
<c:if test="${param.loginCode eq '1'}">
    <script>
        alert("Tên đăng nhập hoặc mật khẩu không đúng !!");
    </script>
</c:if>
<c:if test="${param.regCode eq '1'}">
    <script>
        alert("Đăng ký thành công !!");
    </script>
</c:if>
<c:if test="${param.regCode eq '2'}">
    <script>
        alert("Đăng ký thất bại !!");
    </script>
</c:if>
<form action="/login?action=login" method="post">
    <div id="formLogin">
           <p style="text-align: center"> <a href="/home" style="color: #fffb00"> Trở về trang chủ </a></p>
        <fieldset style="width: 300px">
            <legend style="text-align: center">Đăng nhập</legend>
            <p>Username : <input style="width:280px" type="text" placeholder="Tên đăng nhập" name="userNameLogin"></p>
            <p>Password : <input style="width:280px" type="password" placeholder="Mật khẩu" name="passWordLogin"></p>
            <button class="btn btn-info" type="submit" style="margin-left: 60px">Đăng
                nhập
            </button>
            <button class="btn btn-info" type="button" style="margin-left: 10px"
                    onclick="showRegForm()">
                Đăng ký
            </button>
        </fieldset>
    </div>
</form>
<form action="/login?action=create" method="post">
    <div id="formRegister" style="display: none">
        <fieldset style="width: 700px;height: 600px">
            <legend style="text-align: center">Đăng ký</legend>
            <input type="text" placeholder="Họ và tên" name="regName"/><br>
            <input type="text" placeholder="Email" name="regEmail"/><br>
            <input type="text" placeholder="Tên đăng nhập" name="regUsername"/><br>
            <input type="password" placeholder="Mật khẩu" name="regPassword"/><br>
            <input type="text" placeholder="Số điện thoại" name="regPhoneNumber"/><br>
            <h5>Giới tính</h5>
            <select name="gender">
                <option value="1">Nam</option>
                <option value="0">Nữ</option>
                <option value="2">Khác</option>
            </select>
            <hr>
            <input type="text" placeholder="Địa chỉ" name="regAddress"/>
            <h5>Ngày sinh</h5>
            <input type="date" name="regDOB"/><br>
            <button style="margin-left: 10px" class="btn btn-info" type="submit">
                Tạo tài khoản
            </button>
            <button style="margin-left: 20px" class="btn btn-info" type="button"
                    onclick="showLoginForm()">Đăng nhập
            </button>
        </fieldset>
    </div>
</form>
</body>
<script>

    function showRegForm() {
        document.getElementById('formLogin').style.display = "none";
        document.getElementById('formRegister').style.display = "block";
    }

    function showLoginForm() {
        document.getElementById('formLogin').style.display = "block";
        document.getElementById('formRegister').style.display = "none";
    }

</script>
</html>
