<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/home.html" var="homeUrl"/>
<c:url var="loginUrl" value="/login.html">
    <c:param name="action" value="login"/>
</c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        #result {
            cursor: pointer;
            overflow-y: auto;
            max-height: 400px;
            box-sizing: border-box;
            z-index: 1001;
        }
    </style>
</head>
<body>
<!--================Home Banner Area =================-->
<section class="banner_area">
    <div class="banner_inner d-flex align-items-center">
        <div class="container">
            <div class="banner_content text-center">
                <h2>Shop bán hàng</h2>
                <div class="page_link">
                    <a href="${homeUrl}">Trang chủ</a>
                    <a href="${homeShopUrl}">Shop</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================Category Product Area =================-->
<section class="cat_product_area section_gap">
    <div class="container-fluid">
        <div class="row flex-row-reverse">
            <div class="col-lg-9">
                <form action="${homeShopUrl}" method="get" id="productForm">
                    <div class="product_top_bar">
                        <div class="left_dorp">
                            <select class="sorting" onchange="setLocation(this.value)">
                                <c:if test="${not empty itemsProduct.page}">
                                    <c:url value="/shopping-cart.html" var="homeShopUrl">
                                        <c:param name="dir" value="default"/>
                                        <c:param name="page" value="${itemsProduct.page}"/>
                                        <c:param name="maxPageItems" value="8"/>
                                    </c:url>
                                    <c:url value="/shopping-cart.html" var="homeShopAscUrl">
                                        <c:param name="dir" value="asc"/>
                                        <c:param name="page" value="${itemsProduct.page}"/>
                                        <c:param name="maxPageItems" value="8"/>
                                    </c:url>
                                    <c:url value="/shopping-cart.html" var="homeShopDescUrl">
                                        <c:param name="dir" value="desc"/>
                                        <c:param name="page" value="${itemsProduct.page}"/>
                                        <c:param name="maxPageItems" value="8"/>
                                    </c:url>
                                </c:if>
                                <c:if test="${not empty chose}">
                                    <c:choose>
                                        <c:when test="${chose == 'default'}">
                                            <option value="${homeShopUrl}" selected="selected">Mặc định</option>
                                            <option value="${homeShopAscUrl}">Giá tăng dần</option>
                                            <option value="${homeShopDescUrl}">Giá giảm dần</option>
                                        </c:when>
                                        <c:when test="${chose == 'asc'}">
                                            <option value="${homeShopUrl}">Mặc định</option>
                                            <option value="${homeShopAscUrl}" selected="selected">Giá tăng dần</option>
                                            <option value="${homeShopDescUrl}">Giá giảm dần</option>
                                        </c:when>
                                        <c:when test="${chose == 'desc'}">
                                            <option value="${homeShopUrl}">Mặc định</option>
                                            <option value="${homeShopAscUrl}">Giá tăng dần</option>
                                            <option value="${homeShopDescUrl}" selected="selected">Giá giảm dần</option>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </select>
                            <select class="show">
                                <option value="1">All</option>
                                <option value="2">Hot</option>
                                <option value="4">New</option>
                            </select>
                        </div>
                        <div class="right_page ml-auto">
                            <nav class="cat_page" aria-label="Page navigation example">
                                <ul class="pagination" id="pagination"></ul>
                                <input type="hidden" value="" id="dir" name="dir">
                                <input type="hidden" value="" id="page" name="page">
                                <input type="hidden" value="" id="maxPageItems" name="maxPageItems">
                            </nav>
                        </div>
                    </div>
                    <div class="latest_product_inner row">
                        <c:forEach items="${itemsProduct.listResult}" var="product">
                            <c:url var="productDetailUrl" value="/product-detail.html">
                                <c:param name="pojo.productId" value="${product.productId}"/>
                                <c:param name="producerId" value="${product.producerDTO.producerId}"/>
                                <c:param name="categoryId" value="${product.categoryDTO.categoryId}"/>
                            </c:url>
                            <c:url value="/cart.html" var="cartProductUrl">
                                <c:param name="pojo.productId" value="${product.productId}"/>
                                <c:param name="crudaction" value="insert"/>
                            </c:url>
                            <div class="col-lg-3 col-md-3 col-sm-6">
                                <div class="f_p_item">
                                    <div class="f_p_img">
                                        <img class="img-fluid"
                                             src="<c:url value='/repository/${product.image}'/>" alt="">
                                        <div class="p_icon">
                                            <a href="${productDetailUrl}">
                                                <i class="lnr lnr-magnifier"></i>
                                            </a>
                                            <c:choose>
                                                <c:when test="${not empty loginName}">
                                                    <c:if test="${product.quantity > 0}">
                                                        <a href="${cartProductUrl}">
                                                            <i class="lnr lnr-cart"></i>
                                                        </a>
                                                    </c:if>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${product.quantity > 0}">
                                                        <a href="${loginUrl}">
                                                            <i class="lnr lnr-cart"></i>
                                                        </a>
                                                    </c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4>${product.productName}</h4>
                                    </a>
                                    <h5>$${product.price}.00</h5>
                                    <h5>Số lượng : ${product.quantity}</h5>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </form>
            </div>
            <div class="col-lg-3">
                <div class="left_sidebar_area">
                    <aside class="left_widgets cat_widgets">
                        <div class="l_w_title">
                            <h3>Nhà sản xuất/thể loại</h3>
                        </div>
                        <div class="widgets_inner">
                            <ul class="list">
                                <li>
                                    <a href="#">Nhà sản xuất</a>
                                    <ul class="list">
                                        <c:forEach var="producer" items="${itemsProducer}">
                                            <li>
                                                <a href="#">${producer.producerName}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                                <li>
                                    <a href="#">Thể loại</a>
                                    <ul class="list">
                                        <c:forEach var="category" items="${items}">
                                            <li>
                                                <a href="#">${category.categoryName}</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </aside>
                    <aside class="left_widgets p_filter_widgets">
                        <div class="l_w_title">
                            <h3>Tìm kiếm</h3>
                        </div>
                        <div class="widgets_inner">
                            <h4>Nhập tên sản phẩm</h4>
                            <c:url value="/shopping-cart.html" var="shopUrl"/>
                            <input type="text" class="form-control" id="search" name="searchName"
                                   placeholder="Nhập tên sản phẩm">
                            <div class="list-group" id="result"></div>
                            <br/>
                        </div>
                    </aside>
                </div>
            </div>
        </div>
    </div>
</section>
<!--================End Category Product Area =================-->
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
<script src="<c:url value='/template/pagging/jquery.twbsPagination.js'/>"></script>
<script>
    var totalPages = ${itemsProduct.totalPage};
    var currentPage = ${itemsProduct.page};
    var chose = '${chose}';
    var limit = 8;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 6,
            startPage: currentPage,
            first: '<i class="fa fa-fast-backward" aria-hidden="true"></i>',
            prev: '<i class="fa fa-step-backward" aria-hidden="true"></i>',
            next: '<i class="fa fa-step-forward" aria-hidden="true"></i>',
            last: '<i class="fa fa-fast-forward" aria-hidden="true"></i>',
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#dir').val(chose);
                    $('#maxPageItems').val(limit);
                    $('#page').val(page);
                    $('#productForm').submit();
                }
            }
        });
        $('#search').keyup(function () {
            var datajson = [
                <c:forEach items="${itemsProduct.listResult}" var="item" varStatus="loop">
                {
                    id: "${item.productId}",
                    name: "${item.productName}",
                    producerId: "${item.producerDTO.producerId}",
                    categoryId: "${item.categoryDTO.categoryId}"
                }<c:if test="${!loop.last}">, </c:if>
                </c:forEach>
            ];
            $('#result').html('');
            var searchField = $('#search').val();
            if (searchField !== "") {
                var expression = new RegExp(searchField, "i");
                $.each(datajson, function (key, value) {
                    if (value.name.search(expression) != -1) {
                        $('#result').append('<a href="/product-detail.html?pojo.productId=' + value.id + '&producerId=' + value.producerId + '&categoryId=' + value.categoryId + '" class="list-group-item">' + value.name + '</a>');
                    }
                });
            }
        });
    });

    function search() {
        $('#searchForm').submit();
    }

    function setLocation(url) {
        window.location.href = url;
    }
</script>
</body>
</html>