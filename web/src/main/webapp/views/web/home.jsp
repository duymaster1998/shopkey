<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/shopping-cart.html" var="shopUrl">
    <c:param name="dir" value="default"/>
    <c:param name="page" value="1"/>
    <c:param name="maxPageItems" value="8"/>
</c:url>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<section class="home_banner_area" style="background: url('<c:url
        value="/template/web/img/banner/call-of-duty_-modern-warfare-3-hd-wallpapers-32861-649477.jpg"/>')">
    <div class="overlay"></div>
    <div class="banner_inner d-flex align-items-center">
        <div class="container">
            <div class="banner_content row">
                <div class="offset-lg-2 col-lg-8">
                    <h3>Chào mừng
                        <br/>Đến với shop game nhóm 1</h3>
                    <p>Đến với cửa hàng các bạn sẽ được chăm sóc tận tình, uy tín,
                        chất lượng.</p>
                    <a class="white_bg_btn" href="${shopUrl}">Shop ngay</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================Hot Deals Area =================-->
<section class="hot_deals_area section_gap">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6">
                <div class="hot_deal_box">
                    <img class="img-fluid" src="<c:url value='/template/web/img/product/hot_deals/deal1.jpg'/>" alt="">
                    <div class="content">
                        <h2>Hot Deals of this Month</h2>
                        <p>shop now</p>
                    </div>
                    <a class="hot_deal_link" href="#"></a>
                </div>
            </div>

            <div class="col-lg-6">
                <div class="hot_deal_box">
                    <img class="img-fluid" src="<c:url value='/template/web/img/product/hot_deals/deal1.jpg'/>" alt="">
                    <div class="content">
                        <h2>Hot Deals of this Month</h2>
                        <p>shop now</p>
                    </div>
                    <a class="hot_deal_link" href="#"></a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================Clients Logo Area =================-->
<section class="clients_logo_area">
    <div class="container-fluid">
        <div class="clients_slider owl-carousel">
            <div class="item">
                <img src="<c:url value='/template/web/img/clients-logo/c-logo-1.png'/>" alt="">
            </div>
            <div class="item">
                <img src="<c:url value='/template/web/img/clients-logo/c-logo-2.png'/>" alt="">
            </div>
            <div class="item">
                <img src="<c:url value='/template/web/img/clients-logo/c-logo-3.png'/>" alt="">
            </div>
            <div class="item">
                <img src="<c:url value='/template/web/img/clients-logo/c-logo-4.png'/>" alt="">
            </div>
            <div class="item">
                <img src="<c:url value='/template/web/img/clients-logo/c-logo-5.png'/>" alt="">
            </div>
        </div>
    </div>
</section>
<!--================End Clients Logo Area =================-->
<!--================Feature Product Area =================-->
<section class="feature_product_area section_gap">
    <div class="main_box">
        <div class="container-fluid">
            <div class="row">
                <div class="main_title">
                    <h2>Hot</h2>
                    <p>Top 10 sản phẩm bán chạy trong tháng.</p>
                </div>
            </div>
            <div class="row">
                <%--<c:set var="index" value="${1}"/>--%>
                <%--<c:forEach var="listenguideline" items="${items}">--%>
                    <%--<div class="col col${index}">--%>
                        <%--<div class="f_p_item">--%>
                            <%--<div class="f_p_img">--%>
                                <%--<img class="img-fluid"--%>
                                     <%--src="<c:url value='/repository/${listenguideline.image}'/>" alt="">--%>
                                <%--<div class="p_icon">--%>
                                    <%--<a href="#">--%>
                                        <%--<i class="lnr lnr-magnifier"></i>--%>
                                    <%--</a>--%>
                                    <%--<a href="#">--%>
                                        <%--<i class="lnr lnr-cart"></i>--%>
                                    <%--</a>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<a href="#">--%>
                                <%--<h4>${listenguideline.title}</h4>--%>
                            <%--</a>--%>
                            <%--<h5>$150.00</h5>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<c:set var="index" value="${index + 1}"/>--%>
                <%--</c:forEach>--%>
            </div>
        </div>
    </div>
</section>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
</body>
</html>