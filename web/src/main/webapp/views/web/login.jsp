<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/login.html" var="loginUrl">
    <c:param name="act
    ion" value="login"/>
</c:url>
<c:url value="/home.html" var="homeUrl"/>
<c:url value="/login.html" var="registerUrl">
    <c:param name="action" value="register"/>
</c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<section class="banner_area">
    <div class="banner_inner d-flex align-items-center">
        <div class="container">
            <div class="banner_content text-center">
                <h2>Đăng nhập/Đăng ký</h2>
                <div class="page_link">
                    <a href="${homeUrl}">Trang chủ</a>
                    <a href="${loginUrl}">Đăng nhập</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================Login Box Area =================-->
<section class="login_box_area p_120">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="login_box_img">
                    <img class="img-fluid" src="<c:url value ='/template/web/img/login.jpg'/>" alt="">
                    <div class="hover">
                        <h4>Bạn mới đến cửa hàng?</h4>
                        <p>Có những tiến bộ được thực hiện trong khoa học và công nghệ hàng ngày, và một ví dụ tốt về điều
                            này là</p>
                        <a class="main_btn" href="${registerUrl}">Tạo một tài khoản</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner">
                    <h3>Nhập thông tin</h3>
                    <form class="row login_form" action="${loginUrl}" method="post" id="contactForm"
                          novalidate="novalidate">
                        <span id="error_login" style="color: red;font-size: 14px;display: inline"></span></br></br>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="frmUsername" name="name"
                                   placeholder="Tên tài khoản">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="frmPassword" name="password"
                                   placeholder="Mật khẩu">
                        </div>
                        </br></br></br></br></br>
                        <div class="col-md-12 form-group">
                            <button type="button" id="btnSubmit" class="btn submit_btn" onclick="login()">Đăng nhập</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
<script>
    function    login() {
        var user = $('#frmUsername').val();
        var password = $('#frmPassword').val();
        $.ajax({
            type: "POST",
            url: $('#contactForm').attr('action'),
            data: JSON.stringify({user: user, password: password}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (res) {
                if (res.sta == "ERROR") {
                    $('#error_login').text("Tên đăng nhập hoặc mật khẩu không chính xác !");
                } else if (res.sta == "ADMIN") {
                    window.location.href = "/admin-home.html?urlType=url_list";
                } else if (res.sta == "USER") {
                    window.location.href = "/home.html";
                }
            },
            error: function (res) {
                console.log(res);
            }
        });
    };
</script>
</body>
</html>