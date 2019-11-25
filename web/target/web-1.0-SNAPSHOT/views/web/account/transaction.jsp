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
<c:url value="/account-profile.html" var="profileUrl">
    <c:param name="status" value="account"/>
</c:url>
<c:url value="/account-profile.html" var="personalUrl">
    <c:param name="status" value="personal"/>
</c:url>
<c:url value="/account-profile.html" var="changePasswordUrl">
    <c:param name="status" value="change-password"/>
</c:url>
<c:url value="/account-profile.html" var="transactionUrl">
    <c:param name="status" value="transaction"/>
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/account-profile.html" var="payInUrl">
    <c:param name="status" value="pay-in"/>
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
                <h2>Tài khoản Cá nhân</h2>
                <div class="page_link">
                    <a href="${homeUrl}">Trang chủ</a>
                    <a href="${profileUrl}">Thông tin</a>
                </div>
            </div>
        </div>
    </div>
</section>
<br/>
<br/>
<section class="blog_area">
    <div class="blog_left_sidebar">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="blog_right_sidebar">
                        <aside class="single_sidebar_widget post_category_widget">
                            <h4 class="widget_title">Profile</h4>
                            <ul class="list cat-list">
                                <li>
                                    <a href="${profileUrl}" class="d-flex justify-content-between nav-link">
                                        <p><strong style="font-size: 16px">Thông tin tài khoản</strong></p>
                                    </a>
                                </li>
                                <li>
                                    <a href="${personalUrl}" class="d-flex justify-content-between nav-link">
                                        <p><strong style="font-size: 16px">Thông tin cá nhân</strong></p>
                                    </a>
                                </li>
                                <li>
                                    <a href="${changePasswordUrl}" class="d-flex justify-content-between nav-link">
                                        <p><strong style="font-size: 16px">Thay đổi mật khẩu</strong></p>
                                    </a>
                                </li>
                            </ul>
                            <div class="br"></div>
                            <h4 class="widget_title">Shopping</h4>
                            <div class="br"></div>
                            <ul class="list cat-list">
                                <li>
                                    <a href="${transactionUrl}" class="d-flex justify-content-between nav-link">
                                        <p><strong class="active" style="font-size: 16px">Lịch xử giao dịch</strong></p>
                                    </a>
                                </li>
                                <li>
                                    <a href="${payInUrl}" class="d-flex justify-content-between nav-link">
                                        <p><strong style="font-size: 16px">Nạp tiền tự động</strong></p>
                                    </a>
                                </li>
                            </ul>
                        </aside>
                    </div>
                </div>
                <div class="col-lg-9">
                    <h4 class="blog_right_sidebar" style="color: blue;font-size: 20px">Lịch xử giao dịch</h4>
                    <br/>
                    <table class="table table-head">
                        <thead>
                        <tr style="font-size: 16px">
                            <th style="text-align: center">Mã đơn hàng</th>
                            <th style="text-align: center">Ngày lập</th>
                            <th style="text-align: center">Tổng tiền</th>
                            <th style="text-align: center">Chi tiết</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="bill" items="${items}">
                            <tr style="font-size: 16px">
                                <td style="text-align: center">
                                    ${bill.billId}
                                </td>
                                <td style="text-align: center">
                                        ${bill.createdDate}
                                </td>
                                <td style="text-align: center">
                                        $${bill.totalMoney}.00
                                </td>
                                <td style="text-align: center">
                                    <c:url var="detailBillUrl" value="/ajax-detail-bill.html">
                                        <c:param name="status" value="transaction"/>
                                        <c:param name="urlType" value="url_edit"/>
                                        <c:param name="pojo.billId" value="${bill.billId}"/>
                                    </c:url>
                                    <button type="button" data-toggle="tooltip" title="Xem chi tiết"
                                            class="btn btn-primary btn-circle btn-sm" onclick="showDetail('${detailBillUrl}')">
                                        <i class="lnr lnr-magnifier"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>
<div class="modal fade" id="billDetailModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true"/>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });
    function showDetail(detailUrl) {
        $('#billDetailModal').load(detailUrl, '', function () {
            $('#billDetailModal').modal('toggle');
        });
    };
</script>
</body>
</html>