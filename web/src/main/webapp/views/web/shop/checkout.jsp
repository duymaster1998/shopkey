<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/home.html" var="homeUrl"/>
<c:url value="/account-profile.html" var="checkUrl">
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
                <h2>Thanh toán</h2>
                <div class="page_link">
                    <a href="${homeUrl}">Trang chủ</a>
                    <a href="#">Thanh toán</a>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="checkout_area section_gap">
    <div class="container">
        <div class="billing_details">
            <div class="row">
                <div class="col-lg-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-${alert} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert">&times;</button>
                            <strong>${messageResponse}</strong>
                        </div>
                    </c:if>
                    <br/>
                    <span style="color: red">Lưu ý : bạn có thể xem lại lịch sử giao dịch trong thông tin cá nhân !</span>
                    <div class="order_details_table">
                        <h2>Giỏ hàng của bạn</h2>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th scope="col">Tên sản phẩm</th>
                                    <th scope="col">Số lượng</th>
                                    <th scope="col">Thành tiền</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:set var="total" value="0"/>
                                <c:forEach var="i" items="${orderUser.itemDTOS}">
                                    <tr>
                                        <td>
                                            <p>${i.productDTO.productName}</p>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${i.quantity < 10}">
                                                    <h5>x 0${i.quantity}</h5>
                                                </c:when>
                                                <c:otherwise>
                                                    <h5>x ${i.quantity}</h5>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <p>$${i.quantity * i.price}.00</p>
                                        </td>
                                    </tr>
                                    <c:set var="total" value="${total + i.price * i.quantity}"/>
                                </c:forEach>
                                <tr>
                                    <td>
                                        <h4>Tổng tiền</h4>
                                    </td>
                                    <td>
                                        <h5></h5>
                                    </td>
                                    <td>
                                        <p>$${total}.00</p>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col">
                    <c:if test="${balance < total}">
                        <span style="color: red">Số dư không đủ vui lòng bơm thêm !</span>
                        <br/>
                    </c:if>
                </div>
                <div class="col"></div>
                <div class="col"></div>
                <div class="col"></div>
                <div class="col">
                    <br/>
                    <c:url value="/check-out.html" var="proceedCheckOutUrl">
                        <c:param name="pojo.totalMoney" value="${total}"/>
                        <c:param name="status" value="check-out"/>
                    </c:url>
                    <c:choose>
                        <c:when test="${not empty orderUser.itemDTOS}">
                            <c:choose>
                                <c:when test="${balance >= total}">
                                    <a class="main_btn" href="${proceedCheckOutUrl}">Tiến hành thanh toán</a>
                                </c:when>
                                <c:otherwise>
                                    <a class="main_btn" href="${checkUrl}">Nạp thêm tiền</a>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <button class="main_btn btn btn-primary" type="button" disabled>Tiến hành thanh toán
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <br/>
            <table class="table table-bordered" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Mã key</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="list" items="${items}">
                    <tr>
                        <td>${list.name}</td>
                        <td>${list.key}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
</body>
</html>