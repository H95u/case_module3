<%--
  Created by IntelliJ IDEA.
  User: Hieu's PC
  Date: 6/4/2023
  Time: 10:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Option</title>
    <link rel="stylesheet" href="/user_info/update.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container" style="border: 1px solid black;width: 600px;margin-top: 100px">
    <h3>Các dịch vụ cung cấp</h3>
    <c:forEach var="o" items="${optionList}">
        <c:set var="isChecked" value=""/>
        <c:forEach var="p" items="${optionOfPartner}">
            <c:if test="${o.id == p.id}">
                <c:set var="isChecked" value="checked"/>
            </c:if>
        </c:forEach>
        <div class="row">
            <div class="col-lg-5">${o.name}</div>
            <div class="col-lg-7">
                <input type="radio" name="option${o.id}" value="${o.name}" ${isChecked} />
            </div>
        </div>
        <hr>
    </c:forEach>
</div>
</body>
</html>
