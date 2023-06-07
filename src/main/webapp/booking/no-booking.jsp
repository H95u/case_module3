<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/7/2023
  Time: 12:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body onload="noBooking()">
</body>
<script>
    function noBooking() {
        alert("Bạn không có đơn nào !!")
        window.location.href = "/home"
    }
</script>
</html>
