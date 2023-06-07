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
            max-width: 1000px;
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

    <div class="profile-img">
        <c:if test="${booking.partner.image == null}">
            <img style="width: 200px; height: 250px; padding: 65px" src="/img/default.png" alt="Default Image"
                 class="default-info-img"/>
        </c:if>
        <c:if test="${booking.partner.image != null}">
            <img style="border-radius: 50px;height: 250px;width: 250px"
                 src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(booking.partner.image)}"/>
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
        <label>Ngày sinh:</label>
        <p>${booking.partner.dob}</p>
    </div>

    <div class="form-group">
        <label>Giới tính:</label>
        <p>${booking.partner.gender == 1 ? 'Nam' : "Nữ"}</p>
    </div>

    <div class="form-group">
        <label>Thời gian bắt đầu:</label>
        <input type="datetime-local" value="${booking.startTime}" disabled>
    </div>
    <div class="form-group">
        <label>Thời gian kết thúc:</label>
        <input type="datetime-local" value="${booking.endTime}" disabled>
    </div>
    <div class="form-group">
        <label>Tổng thời gian:</label>
        <p> ${time}</p>
    </div>
    <div class="form-group">
        <label>Giá tiền:</label>
        <p>${booking.partner.hourlyRate} /h</p>
    </div>
    <div class="form-group">
        <label>Dịch vụ thêm:</label>
        <p>${booking.option.name} - ${booking.option.price} </p>
    </div>

    <div class="form-group">
        <label>Tổng tiền:</label>
        <p>${booking.partner.hourlyRate * time + booking.option.price}</p>
    </div>
    <button type="submit" class="btn btn-primary" onclick="checkPayment(${booking.id})">Thanh toán</button>
</div>
</body>
<script>
    function checkPayment(id) {
        if (confirm("Xác nhận thanh toán thành công.")) {
            window.location.href = "/booking?action=payment&id=" + id;
        }
    }
</script>
</html>
