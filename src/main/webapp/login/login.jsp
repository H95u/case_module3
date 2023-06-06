
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="style.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
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

<section>
    <div class="login-box">
        <form action="/login?action=login" method="post">
            <div>
                <h2>Login</h2>
                <div class="input-box">
                    <span class="icon"><ion-icon name="accessibility-outline"></ion-icon></span>
                    <input type="username" name="userNameLogin" required>
                    <label>Tên Đăng Nhập</label>
                </div>
                <div class="input-box">
                    <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                    <input type="password" name="passWordLogin" required>
                    <label>Mật Khẩu</label>
                </div>
                <div class="remember-forget">
                    <label><input type="checkbox">Lưu Thông Tin</label>
                    <a href="#">Quên Mật Khẩu</a>
                </div>
                <button type="submit">Đăng Nhập</button>
                <div class="register-link">
                    <p>Bạn chưa có tài khoản? <a href="register.jsp">Đăng Ký</a></p>
                </div>
                <div class="register-link">
                    <p>Quay lại trang chủ? <a href="/home"><i class='bx bxs-home' ></i></a></p>
                </div>
            </div>
        </form>

    </div>
</section>

</body>

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</html>
