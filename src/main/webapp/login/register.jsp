<%--
  Created by IntelliJ IDEA.
  User: Minh Tran
  Date: 6/6/2023
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
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
        <form action="/login?action=create">
            <div>
                <h2>Đăng Ký</h2>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-user'></i></span>
                    <input type="username" name="regName" required>
                    <label>Họ & Tên</label>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-envelope'></i></span>
                    <input type="email" name="regEmail" required>
                    <label>Email</label>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-user'></i></span>
                    <input type="username" name="regUsername" required>
                    <label>Tên Đăng Nhập</label>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-lock'></i></span>
                    <input type="password" name="regPassword" required>
                    <label>Mật Khẩu</label>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-user'></i></span>
                    <input type="tel" name="regPhoneNumber" required>
                    <label>Số Điện Thoại</label>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bx-male-female'></i></span>
                    <%--                    <input type="gender" required>--%>
                    <div class="gender">
                        <select name="gender">
                            <option>Giới tính</option>
                            <option value="1">Nam</option>
                            <option value="0">Nữ</option>
                        </select>
                    </div>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-map'></i></span>
                    <input type="text" name="regAddress" required>
                    <label>Địa chỉ</label>
                </div>
                <div class="input-box">
                    <span class="icon"><i class='bx bxs-calendar'></i></span>
                    <input class="date1" style="width: 338px" type="date" name="regDOB" required>
                </div>
                <div class="remember-forget">
                    <label><input type="checkbox">Lưu Thông Tin</label>
<%--                    <a href="#">Quên Mật Khẩu</a>--%>
                </div>
                <button type="submit">Đăng Ký</button>
                <div class="register-link">
                    <p>Bạn đã có tài khoản? <a href="login.jsp">Đăng nhập</a></p>
                </div>
            </div>
        </form>

    </div>
</section>

</body>

<script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
</html>
