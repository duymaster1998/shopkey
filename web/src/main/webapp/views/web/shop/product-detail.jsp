<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/home.html" var="homeUrl"/>
<c:url var="shopUrl" value="/shopping-cart.html">
    <c:param name="dir" value="default"/>
    <c:param name="page" value="1"/>
    <c:param name="maxPageItems" value="8"/>
</c:url>
<c:url var="loginUrl" value="/login.html">
    <c:param name="action" value="login"/>
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
                <h2>Chi tiết sản phẩm</h2>
                <div class="page_link">
                    <a href="${homeUrl}">Trang chủ</a>
                    <a href="${shopUrl}">Shop</a>
                    <a href="#">Chi tiết</a>
                </div>
            </div>
        </div>
    </div>
</section>
<c:if test="${not empty item}">
    <div class="product_image_area">
        <div class="container">
            <div class="row s_product_inner">
                <div class="col-lg-6">
                    <div class="s_product_img">
                        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100"
                                         src="<c:url value='/repository/${item.image}'/>"
                                         alt="First slide">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5 offset-lg-1">
                    <div class="s_product_text">
                        <c:url value="/cart.html" var="productDetailUrl"/>
                        <form action="${productDetailUrl}" method="get">
                            <h3>${item.productName}</h3>
                            <h2>$${item.price}.00</h2>
                            <ul class="list">
                                <li>
                                    <span>Nhà sản xuất</span> : ${item.producerDTO.producerName}
                                </li>
                                <li>
                                    <span>Thể loại</span> : ${item.categoryDTO.categoryName}
                                </li>
                            </ul>
                                ${item.productDescription}
                            <c:if test="${item.quantity > 0}">
                                <div class="product_count">
                                    <label for="sst">Số lượng:</label>
                                    <input type="text" name="productDetailQty" id="sst" maxlength="12" value="1"
                                           title="Quantity:"
                                           class="input-text qty">
                                    <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst < ${item.quantity}) result.value++;return false;"
                                            class="increase items-count" type="button">
                                        <i class="lnr lnr-chevron-up"></i>
                                    </button>
                                    <button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 1 ) result.value--;return false;"
                                            class="reduced items-count" type="button">
                                        <i class="lnr lnr-chevron-down"></i>
                                    </button>
                                </div>
                                <div class="card_area">
                                    <c:choose>
                                        <c:when test="${not empty loginName}">
                                            <button class="main_btn btn btn-primary" type="submit">Thêm vào giỏ</button>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="main_btn" href="${loginUrl}">Thêm vào giỏ</a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </c:if>
                            <input type="hidden" name="pojo.productId" value="${item.productId}">
                            <input type="hidden" name="crudaction" value="insert">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--================End Single Product Area =================-->

    <!--================End Home Banner Area =================-->
    <section class="product_description_area">
        <div class="container">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <strong style="color: blue">Nội dung</strong>
                </li>
            </ul>
            <div style="color: blue">
                    ${item.content}
            </div>
        </div>
    </section>
</c:if>
<section class="clients_logo_area">
    <div class="container-fluid">
        <div class="clients_slider owl-carousel">
            <%--<div class="item">--%>
            <c:if test="${not empty items}">
                <c:forEach var="rmdProduct" items="${items}">
                    <c:url var="reCmdProductUrl" value="/product-detail.html">
                        <c:param name="pojo.productId" value="${rmdProduct.productId}"/>
                        <c:param name="producerId" value="${rmdProduct.producerDTO.producerId}"/>
                        <c:param name="categoryId" value="${rmdProduct.categoryDTO.categoryId}"/>
                    </c:url>
                    <c:url value="/cart.html" var="addToCartUrl">
                        <c:param name="pojo.productId" value="${rmdProduct.productId}"/>
                        <c:param name="crudaction" value="insert"/>
                    </c:url>
                    <div class="f_p_item">
                        <div class="f_p_img">
                            <img class="img-fluid"
                                 src="<c:url value='/repository/${rmdProduct.image}'/>" alt="">
                            <div class="p_icon">
                                <a href="${reCmdProductUrl}">
                                    <i class="lnr lnr-magnifier"></i>
                                </a>
                                <c:choose>
                                    <c:when test="${not empty loginName}">
                                        <c:if test="${rmdProduct.quantity > 0}">
                                            <a href="${addToCartUrl}">
                                                <i class="lnr lnr-cart"></i>
                                            </a>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${rmdProduct.quantity > 0}">
                                            <a href="${loginUrl}">
                                                <i class="lnr lnr-cart"></i>
                                            </a>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <a href="#">
                            <h4>${rmdProduct.productName}</h4>
                        </a>
                        <h5>$${rmdProduct.price}.00</h5>
                        <h5>Số lượng : ${rmdProduct.quantity}</h5>
                    </div>
                </c:forEach>
            </c:if>
            <%--</div>--%>
        </div>
    </div>
</section>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
</body>
</html>