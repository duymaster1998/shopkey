<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/home.html" var="homeUrl"/>
<c:url value="/shopping-cart.html" var="shopUrl">
    <c:param name="dir" value="default"/>
    <c:param name="page" value="1"/>
    <c:param name="maxPageItems" value="8"/>
</c:url>
<c:url var="updateCartUrl" value="/cart.html"/>
<c:url var="checkOutUrl" value="/check-out.html">
    <c:param name="status" value="views"/>
</c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<!--================Home Banner Area =================-->
<section class="banner_area">
    <div class="banner_inner d-flex align-items-center">
        <div class="container">
            <div class="banner_content text-center">
                <h2>Giỏ hàng</h2>
                <div class="page_link">
                    <a href="${homeUrl}">Trang chủ</a>
                    <a href="#">Giỏ hàng</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Home Banner Area =================-->
<section class="cart_area">
    <div class="container">
        <div class="cart_inner">
            <c:choose>
                <c:when test="${empty orderUser.itemDTOS}">
                    <br/><br/>
                    <div class="row">
                        <div class="col-12 col-sm-2"></div>
                        <div class="col-12 col-sm-2">
                            <img class="img-fluid"
                                 src="<c:url value='/template/image/empty-cart.png'/>" alt="">
                        </div>
                        <div class="col-12 col-sm-8">
                            <h1>Giỏ hàng của bạn đang trống</h1>
                            <div>
                                <span style="font-size: 18px">Bạn không có sản phẩm nào trong giỏ hàng của mình.</span>
                            </div>
                            <span style="font-size: 18px">Bấm <a href="${shopUrl}">vào đây</a> để tiếp tục mua sắm.</span>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <c:if test="${not empty messageResponse}">
                            <div class="alert alert-${alert} alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <strong>${messageResponse}</strong>
                            </div>
                        </c:if>
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Thành tiền</th>
                                <th scope="col"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <form action="${updateCartUrl}" method="post" id="cartForm">
                                <c:set var="total" value="0"/>
                                <c:forEach var="i" items="${orderUser.itemDTOS}">
                                    <tr>
                                        <td>
                                            <div class="media">
                                                <div class="d-flex">
                                                    <img src="<c:url value='/repository/${i.productDTO.image}'/>"
                                                         height="120ox" width="100px" alt="">
                                                </div>
                                                <div class="media-body">
                                                    <p>${i.productDTO.productName}</p>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <h4>$${i.price}.00</h4>
                                        </td>
                                        <td>
                                            <div class="product_count">
                                                <input type="text" name="quantity" id="sst_${i.productDTO.productId}"
                                                       maxlength="12"
                                                       value="${i.quantity}" title="Quantity:"
                                                       class="input-text qty">
                                                <button onclick="var result = document.getElementById('sst_${i.productDTO.productId}'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst < ${i.productDTO.quantity}) result.value++;return false;"
                                                        class="increase items-count" type="button">
                                                    <i class="lnr lnr-chevron-up"></i>
                                                </button>
                                                <button onclick="var result = document.getElementById('sst_${i.productDTO.productId}'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 1 ) result.value--;return false;"
                                                        class="reduced items-count" type="button">
                                                    <i class="lnr lnr-chevron-down"></i>
                                                </button>
                                            </div>
                                        </td>
                                        <td>
                                            <h4>$${i.price * i.quantity}.00</h4>
                                        </td>
                                        <td>
                                            <c:url value="/cart.html" var="deleteProductOnCartUrl">
                                                <c:param name="pojo.productId" value="${i.productDTO.productId}"/>
                                                <c:param name="crudaction" value="delete"/>
                                            </c:url>
                                            <a style="text-decoration: none" href="${deleteProductOnCartUrl}">
                                                <button type="button" class="close" aria-label="Close"
                                                        style="color: red">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </a>
                                        </td>
                                    </tr>
                                    <c:set var="total" value="${total + i.price * i.quantity}"/>
                                    <input type="hidden" name="productIdList" value="${i.productDTO.productId}">
                                </c:forEach>
                            </form>
                            <tr class="bottom_button">
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="cupon_text">
                                    </div>
                                </td>
                                <td>

                                </td>

                            </tr>
                            <tr>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <h4>Thành tiền giỏ hàng</h4>
                                </td>
                                <td>
                                    <h4>$${total}.00</h4>
                                </td>
                                <td>

                                </td>
                            </tr>
                            <tr class="out_button_area">
                                <td>
                                    <button class="gray_btn btn btn-primary" type="button" onclick="updateCart()">Cập
                                        nhật giỏ
                                        hàng
                                    </button>
                                </td>
                                <td>

                                </td>
                                <td>

                                </td>
                                <td>
                                    <div class="checkout_btn_inner">
                                        <a class="gray_btn" href="${shopUrl}">Tiếp tục mua hàng</a>
                                        <c:choose>
                                            <c:when test="${not empty orderUser.itemDTOS}">
                                                <a class="main_btn" href="${checkOutUrl}">Thanh toán giỏ hàng</a>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="main_btn btn btn-primary" type="button" disabled>Thanh
                                                    toán giỏ hàng
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </td>
                                <td>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</section>
<!--================End Cart Area =================-->
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
<script>
    function updateCart() {
        $('#cartForm').submit();
    }
</script>
</body>
</html>