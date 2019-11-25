<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="loginUrl" value="/login.html">
    <c:param name="action" value="login"/>
</c:url>
<c:url var="homeUrl" value="/home.html"/>
<c:url var="productDetailUrl" value="/product-detail.html"/>
<c:url var="cartUrl" value="/cart.html"/>
<c:url value="/shopping-cart.html" var="shopUrl">
    <c:param name="dir" value="default"/>
    <c:param name="page" value="1"/>
    <c:param name="maxPageItems" value="8"/>
</c:url>
<c:url var="profileUrl" value="/account-profile.html">
    <c:param name="status" value="account"/>
</c:url>
<c:url value="/account-profile.html" var="payInUrl">
    <c:param name="status" value="pay-in"/>
</c:url>
<!--================Header Menu Area =================-->
<header class="header_area">
    <div class="top_menu row m0">
        <div class="container-fluid">
            <div class="float-left">
                <p>Call Us: 012 44 5698 7456 896</p>
            </div>
            <c:if test="${not empty loginName}">
                <c:url value="/login.html" var="logoutUrl">
                    <c:param name="action" value="logout"/>
                </c:url>
                <div class="float-right">
                    <ul class="right_side">
                        <li style="text-transform: uppercase;font-size: 12px;">
                            Xin chào, ${loginName}
                        </li>
                        <li>
                            <a href="${logoutUrl}">
                                Đăng xuất
                            </a>
                        </li>
                        <li style="text-transform: uppercase;font-size: 12px;">
                            Số dư, $${balance}.00
                        </li>
                    </ul>
                </div>
            </c:if>
            <c:if test="${empty loginName}">
                <div class="float-right">
                    <ul class="right_side">
                        <li>
                            <a href="${loginUrl}">
                                Đăng nhập/Đăng ký
                            </a>
                        </li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="${homeUrl}">
                    <img src="<c:url value='/template/web/img/logo.png' />" alt="">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <div class="row w-100">
                        <div class="col-lg-7 pr-0">
                            <ul class="nav navbar-nav center_nav pull-right">
                                <li class="nav-item active">
                                    <a class="nav-link" href="${homeUrl}">Trang chủ</a>
                                </li>
                                <li class="nav-item submenu dropdown">
                                    <a class="nav-link" href="${shopUrl}">Shop</a>
                                </li>
                                <li class="nav-item submenu dropdown">
                                    <c:choose>
                                        <c:when test="${empty loginName}">
                                            <a class="nav-link" href="${loginUrl}">Nạp tiền</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${payInUrl}" class="nav-link">Nạp tiền</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button"
                                       aria-haspopup="true" aria-expanded="false">Pages</a>
                                    <c:if test="${empty loginName}">
                                        <ul class="dropdown-menu">
                                            <li class="nav-item">
                                                <a class="nav-link" href="${loginUrl}">Đăng nhập</a>
                                        </ul>
                                    </c:if>
                                </li>
                            </ul>
                        </div>

                        <div class="col-lg-5">
                            <ul class="nav navbar-nav navbar-right right_nav pull-right">
                                <hr>
                                <li class="nav-item submenu dropdown">
                                    <a href="#" class="icons">
                                        <i class="fa fa-search" aria-hidden="true"></i>
                                    </a>
                                </li>
                                <hr>

                                <c:if test="${not empty loginName}">
                                    <li class="nav-item">
                                        <a href="${profileUrl}" class="icons">
                                            <i class="fa fa-user" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${empty loginName}">
                                    <li class="nav-item">
                                        <a href="${loginUrl}" class="icons">
                                            <i class="fa fa-user" aria-hidden="true"></i>
                                        </a>
                                    </li>
                                </c:if>

                                <hr>

                                <hr>
                                <c:if test="${not empty loginName}">
                                    <li class="nav-item">
                                        <a href="${cartUrl}" class="icons">
                                            <i class="lnr lnr lnr-cart"></i>
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${empty loginName}">
                                    <li class="nav-item">
                                        <a href="${loginUrl}" class="icons">
                                            <i class="lnr lnr lnr-cart"></i>
                                        </a>
                                    </li>
                                </c:if>

                                <hr>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>
<!--================Header Menu Area =================-->