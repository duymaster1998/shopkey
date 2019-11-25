<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/admin-home.html" var="addOrEditUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url var="userListUrl" value="/admin-user-list.html"/>
<c:url var="tradeUrl" value="/admin-trade.html"/>
<c:url var="reportUrl" value="/admin-charts.html"/>
<c:url var="categoryListUrl" value="/admin-list-category.html"/>
<c:url var="listKeyUrl" value="/admin-list-key.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${addOrEditUrl}">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Admin Home</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <%--<li class="nav-item">--%>
        <%--<a class="nav-link" href="index.html">--%>
            <%--<i class="fas fa-fw fa-tachometer-alt"></i>--%>
            <%--<span>Dashboard</span></a>--%>
    <%--</li>--%>

    <!-- Divider -->
    <%--<hr class="sidebar-divider">--%>

    <!-- Heading -->
    <%--<div class="sidebar-heading">--%>
        <%--Interface--%>
    <%--</div>--%>

    <!-- Nav Item - Pages Collapse Menu -->
    <%--<li class="nav-item">--%>
        <%--<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">--%>
            <%--<i class="fas fa-fw fa-cog"></i>--%>
            <%--<span>Components</span>--%>
        <%--</a>--%>
        <%--<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">--%>
            <%--<div class="bg-white py-2 collapse-inner rounded">--%>
                <%--<h6 class="collapse-header">Custom Components:</h6>--%>
                <%--<a class="collapse-item" href="buttons.html">Buttons</a>--%>
                <%--<a class="collapse-item" href="cards.html">Cards</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</li>--%>

    <!-- Nav Item - Utilities Collapse Menu -->
    <%--<li class="nav-item">--%>
        <%--<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">--%>
            <%--<i class="fas fa-fw fa-wrench"></i>--%>
            <%--<span>Utilities</span>--%>
        <%--</a>--%>
        <%--<div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">--%>
            <%--<div class="bg-white py-2 collapse-inner rounded">--%>
                <%--<h6 class="collapse-header">Custom Utilities:</h6>--%>
                <%--<a class="collapse-item" href="utilities-color.html">Colors</a>--%>
                <%--<a class="collapse-item" href="utilities-border.html">Borders</a>--%>
                <%--<a class="collapse-item" href="utilities-animation.html">Animations</a>--%>
                <%--<a class="collapse-item" href="utilities-other.html">Other</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</li>--%>

    <!-- Divider -->
    <%--<hr class="sidebar-divider">--%>

    <!-- Heading -->
    <div class="sidebar-heading">
        Addons
    </div>

    <li class="nav-item">
        <a class="nav-link" href="${reportUrl}">
            <i class="fas fa-fw fa-chart-area"></i>
            <span>Báo cáo</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-list-ul"></i>
            <span>Quản lý sản phẩm</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Menu:</h6>
                <a class="collapse-item" href="${addOrEditUrl}">Danh sách sản phẩm</a>
                <a class="collapse-item" href="${listKeyUrl}">Danh sách key</a>
            </div>
        </div>
    </li>

    <!-- Nav Item - Charts -->
    <li class="nav-item">
        <a class="nav-link" href="${userListUrl}">
            <i class="fas fa-user"></i>
            <span>Danh sách người dùng</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="${categoryListUrl}">
            <i class="fas fa-list-alt"></i>
            <span>Danh sách thể loại & nhà sản xuất</span></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="${tradeUrl}">
            <i class="fas fa-list-ul"></i>
            <span>Danh sách hóa đơn</span></a>
    </li>


<%--<li class="nav-item">--%>
        <%--<a class="nav-link" href="${tradeUrl}">--%>
            <%--<i class="fas fa-fw fa-tachometer-alt"></i>--%>
            <%--<span>Hóa đơn</span></a>--%>
    <%--</li>--%>


    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

</ul>
<!-- End of Sidebar -->