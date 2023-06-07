<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Base64" %>
<html lang="en">
<head>
    <title>Lover web</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="style/css/style.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
            crossorigin="anonymous"></script>
</head>
<body>

<div class="wrapper d-flex align-items-stretch">
    <nav id="sidebar">
        <div class="p-4 pt-5">
            <c:if test="${user != null}">
                <c:if test="${user.image != null}">
                    <a href="/userInfo?action=showInfo&id=${user.id}">
                        <img width="50" height="50" style="border-radius: 30px"
                             src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(user.image)}"/></a>
                    <hr>
                </c:if>
                <c:if test="${user.image == null}">
                    <a href="/userInfo?action=showInfo&id=${user.id}">
                        <img height="50" width="50" src="img/default.png" alt="" style="border-radius: 30px"></a>
                </c:if>
            </c:if>
            <ul class="list-unstyled components mb-5">
                <li class="active">
                    <a href="/home" aria-expanded="false">Trang
                        Chủ</a>
                </li>
                <li>
                    <a href="#">Về Chúng Tôi</a>
                </li>
                <li>
                    <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Dịch
                        Vụ</a>
                    <ul class="collapse list-unstyled" id="pageSubmenu">
                        <c:forEach var="o" items="${optionList}">
                            <li>
                                <a href="/home?action=search&oid=${o.id}">${o.name}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </li>
                <c:if test="${sessionScope.user != null}">
                    <li>
                        <a href="/booking?id=${sessionScope.user.id}">Xem đơn</a>
                    </li>
                </c:if>
                <li>
                    <a href="#">Liên Hệ</a>
                </li>
            </ul>

            <div class="footer">
                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                <hr>
                Web thuê người yêu</a>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
            </div>

        </div>
    </nav>

    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5" style="background-color: #a7045d">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <button type="button" id="sidebarCollapse" class="btn btn-primary">
                    <i class="fa fa-bars"></i>
                    <span class="sr-only">Toggle Menu</span>
                </button>
                <div style="margin-left: 10px">
                    <div class="btn-group">
                        <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            Giới tính
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="/home?action=searchByGender&gender=1">Nam</a></li>
                            <li><a class="dropdown-item" href="/home?action=searchByGender&gender=0">Nữ</a></li>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            Dịch vụ
                        </button>
                        <ul class="dropdown-menu">
                            <c:forEach var="o" items="${optionList}">
                                <li>
                                    <a class="dropdown-item" href="/home?action=search&oid=${o.id}">${o.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-success dropdown-toggle" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            Trạng thái
                        </button>
                        <ul class="dropdown-menu">
                                <li>
                                    <a class="dropdown-item" href="/home?action=searchByStatus&status=1">Có thể thuê</a>
                                    <a class="dropdown-item" href="/home?action=searchByStatus&status=0">Đang bận</a>
                                </li>
                        </ul>
                    </div>

                </div>
                <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <i class="fa fa-bars"></i>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="nav navbar-nav ml-auto">

                        <div class="container-input">
                            <form action="/home?action=searchByName" method="post">
                                <input type="text" placeholder="Tìm kiếm" name="name" class="input">
                                <button type="submit">
                                    <svg fill="#000000" width="20px" height="20px" viewBox="0 0 1920 1920"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path d="M790.588 1468.235c-373.722 0-677.647-303.924-677.647-677.647 0-373.722 303.925-677.647 677.647-677.647 373.723 0 677.647 303.925 677.647 677.647 0 373.723-303.924 677.647-677.647 677.647Zm596.781-160.715c120.396-138.692 193.807-319.285 193.807-516.932C1581.176 354.748 1226.428 0 790.588 0S0 354.748 0 790.588s354.748 790.588 790.588 790.588c197.647 0 378.24-73.411 516.932-193.807l516.028 516.142 79.963-79.963-516.142-516.028Z"
                                              fill-rule="evenodd"></path>
                                    </svg>
                                </button>
                            </form>
                        </div>
                        <li class="nav-item active">
                            <a class="nav-link" href="/home">Trang chủ</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Về Chúng Tôi</a>
                        </li>
                        <c:if test="${user == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="/login/login.jsp">Đăng nhập</a>
                            </li>
                        </c:if>
                        <li class="nav-item">
                            <a class="nav-link">Xin chào ,<c:if test="${user != null}">
                                <span style="color: red">${user.username}</span>
                            </c:if>
                                <c:if test="${user == null}">
                                    <span style="color: red">Khách</span>
                                </c:if>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <h3 style="text-align: center;color: yellow;font-style: italic">Danh sách hot girl - hot boy</h3>
        <div class="row" style="margin: auto">
            <c:forEach var="p" items="${partnerList}">
                <div class="col-lg-3" style="text-align: center;">
                    <a href="/home?action=partnerInfo&id=${p.id}">
                        <c:if test="${p.image != null}">
                            <img style="border-radius: 30px" width="200" height="200"
                                 src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(p.image)}"/>
                        </c:if>
                        <p>Nickname : ${p.nickname}</p>
                        <p>Giá : ${p.hourlyRate} /h </p>
                        <c:if test="${p.availability == 1}">
                            <p>Trạng thái : Có thể thuê</p>
                        </c:if>
                        <c:if test="${p.availability == 0}">
                            <p>Trạng thái : Đang bận</p>
                        </c:if>
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<script src="style/js/jquery.min.js"></script>
<script src="style/js/popper.js"></script>
<script src="style/js/bootstrap.min.js"></script>
<script src="style/js/main.js"></script>
</body>

</html>