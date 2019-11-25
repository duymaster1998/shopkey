$(document).ready(function () {
    loginFormValidate();
});
function loginFormValidate() {
    $('#contactForm').validate({
        rules : [],
        messages : []
    });
    $('#name').rules("add",{
        required : true,
        messages: {
            required: "Không để trống tên đăng nhập !"
        }
    });
    $('#password').rules("add",{
        required : true,
        messages : {
            required: "Không để trống mật khẩu !"
        }
    });
};