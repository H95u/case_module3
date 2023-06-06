<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<html>
<head>
    <title>Hiển thị thông tin đã thuê</title>
    <link rel="stylesheet" href="demothongtinthanhtoan.css">
    <script src="style/js/jquery.min.js"></script>
    <script src="style/js/popper.js"></script>
    <script src="style/js/bootstrap.min.js"></script>
    <script src="style/js/main.js"></script>
    <style>
        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f8f8f8;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
        }

        p {
            margin: 0;
        }

        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

    </style>
</head>
<body>
<div class="container">
    <h1>Thông tin thuê</h1>

    <form action="/payment" method="POST">
        <div class="profile-img">
        <c:if test="${user.image == null}">
        <img style="width: 200px; height: 250px; padding: 65px" src="/img/default.png" alt="Default Image" class="default-info-img"/>
        </c:if>
        <c:if test="${user.image != null}">
        <img style="border-radius: 50px;height: 250px;width: 250px"
             src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(user.image)}"/>
        </c:if>

        </div>
        <div class="form-group">
            <label>Nickname:</label>
            <p>${booking.partner.nickname}</p>
        </div>

        <div class="form-group">
            <label>Địa chỉ:</label>
            <p>${booking.partner.address}</p>
        </div>

        <div class="form-group">
            <label>Thời gian:</label>
            <p>${time} (giờ)</p>
        </div>

        <div class="form-group">
            <label>Giá tiền:</label>
            <p>${booking.partner.hourlyRate} /h</p>
        </div>

        <div class="form-group">
            <label>Ngày sinh:</label>
            <p>${booking.partner.dob}</p>
        </div>

        <div class="form-group">
            <label>Trạng thái:</label>
            <c:if test="${partner.availability == 1}">
                <p>Có thể thuê</p>
            </c:if>
            <c:if test="${partner.availability == 0}">
                <p>Đang bận</p>
            </c:if>
        </div>
        <div class="form-group">
            <label>Giới tính:</label>
            <p>${partner.gender == 1 ? 'Nam' : "Nữ"}</p>
        </div>
        <div class="form-group">
            <label>Tổng tiền:</label>
            <p>${totalPrice}</p>
        </div>
        <button type="submit" class="btn btn-primary">Thanh toán</button>
    </form>
</div>
</body>
<script>
    document.getElementById("paymentForm").addEventListener("submit", function(event) {
        event.preventDefault();
        alert("Thanh toán thành công!");
        window.location.href = "/home";
    });
</script>
</html>
