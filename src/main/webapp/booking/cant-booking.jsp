<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/7/2023
  Time: 11:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>cant booking</title>
</head>
<body onload="alertBooking(<%=request.getParameter("bookingCode")%>)">

</body>
<script>
    function alertBooking(bookingCode) {
        if (bookingCode === 0) {
            alert("Bạn không thể book thêm do chưa thanh toán !!")
            window.location.href = "/home"
        }else if (bookingCode == 1){
            alert("Thời gian kết thúc phải sau thời gian bắt đầu thuê !!")
            window.location.href = "/home"
        }else if (bookingCode == 2){
            alert("Phải book ít nhất 1 tiếng !!")
            window.location.href = "/home"
        }else {
            alert("Thời gian bạn nhập không hợp lệ")
            window.location.href = "/home"
        }
    }
</script>
</html>
