<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/5/2023
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.time.Duration" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="booking.css">
<html>
<head>
    <title>Confirm booking</title>
</head>
<body>
<form method="post" action="/booking?action=confirm&id=${booking.id}">
    <div class="container">
        <div><h3 style="text-align: center">Xác nhận thông tin booking</h3></div>
        <div id="formConfirmBooking">
            <img style="border-radius: 50px;height: 250px;width: 250px"
                 src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(booking.partner.image)}"/>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-6">
                Nickname
            </div>
            <div class="col-lg-6">
                ${booking.partner.nickname}
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-6">
                Giá
            </div>
            <div class="col-lg-6">
                ${booking.partner.hourlyRate} /h
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-6">
                Ngày sinh
            </div>
            <div class="col-lg-6">
                ${booking.partner.dob}
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-6">
                Địa chỉ
            </div>
            <div class="col-lg-6">
                ${booking.partner.address}
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-6">
                Thời gian
            </div>
            <div class="col-lg-6">
                ${time} (giờ)
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-6">
                Tổng tiền
            </div>
            <div class="col-lg-6">
                ${totalPrice}
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-lg-6">
                Dịch vụ thêm
            </div>
            <div class="col-lg-6">
                ${booking.option.name}
            </div>
        </div>
        <hr>
        <button class="btn btn-primary" type="button" onclick="confirmBooking()">Xác nhận</button>
        <button class="btn btn-danger" type="submit">Hủy</button>
    </div>
</form>
</body>
<script>
    function confirmBooking() {
        window.location.href = "/home";
    }
</script>
</html>
