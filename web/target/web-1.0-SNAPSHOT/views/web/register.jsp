<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/login.html" var="loginUrl">
    <c:param name="action" value="login"/>
</c:url>
<c:url value="/login.html" var="registerUrl">
    <c:param name="action" value="register"/>
</c:url>
<c:url value="/home.html" var="homeUrl"/>
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
<<!--================Login Box Area =================-->
<section class="login_box_area p_120">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div class="login_box_img">
                    <img class="img-fluid" src="<c:url value='/template/web/img/login.jpg'/>" alt="">
                    <div class="hover">
                        <h4>Bạn mới đến cửa hàng?</h4>
                        <p>Có những tiến bộ được thực hiện trong khoa học và công nghệ hàng ngày, và một ví dụ tốt về
                            điều
                            này là</p>
                        <a class="main_btn" href="${registerUrl}">Tạo một tài khoản</a>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="login_form_inner reg_form">
                    <h3>Tạo tài khoản</h3>
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible fade show">
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <form class="row login_form" action="${registerUrl}" method="post" id="registerForm"
                          novalidate="novalidate">
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="firstname" name="firstName"
                                   placeholder="Họ đệm">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="lastname" name="lastName"
                                   placeholder="Tên">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="text" class="form-control" id="username" name="pojo.userName"
                                   placeholder="Tên tài khoản">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="passwordre" name="pojo.password"
                                   placeholder="Mật khẩu">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="password" class="form-control" id="conpass" name="repeatPass"
                                   placeholder="Xác nhận mật khẩu">
                        </div>
                        <div class="col-md-12 form-group">
                            <input type="submit" value="Đăng ký" class="btn submit_btn">
                            <a href="${loginUrl}">Already have an account? Login!</a>
                        </div>
                        <input type="hidden" name="roleId" value="2"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
<script src="<c:url value='/template/web/js/jquery.validate.min.js'/>"></script>
<script>
    $(document).ready(function () {
        registerValidate();
    });

    function registerValidate() {
        $.validator.addMethod(
            "regex",
            function (value, element, regexp) {
                var re = new RegExp(regexp);
                return this.optional(element) || re.test(value);
            },
            "Please check your input."
        );
        $('#registerForm').validate({
            rules: [],
            messages: []
        });
        $('#firstname').rules("add", {
            required: true,
            messages: {
                required: "Không để trống Họ đệm !"
            }
        });
        $('#passwordre').rules("add", {
            regex: "^[a-zA-Z]\\w{3,14}$",
            required: true,
            minlength: 6,
            messages: {
                regex : "Bạn nhập mật khẩu không đúng định dạng !",
                required: "Không để trống mật khẩu !",
                minlength: "Hãy nhập ít nhất 6 ký tự !"
            }
        });
        $('#conpass').rules("add", {
            equalTo: "#passwordre",
            messages: {
                equalTo: "Hai mật khẩu không khớp nhau !"
            }
        });
        $('#lastname').rules("add", {
            required: true,
            messages: {
                required: "Không để trống tên !"
            }
        });
        $('#username').rules("add", {
            regex: "^[a-zA-Z]\\w{3,14}$",
            required: true,
            minlength: 6,
            messages: {
                regex: "Bạn nhập tên đăng nhập không đúng định dạng !",
                required: "Không để trống tên đăng nhập !",
                minlength: "Hãy nhập ít nhất 6 ký tự !"
            }
        })
    };
</script>
</body>
</html>