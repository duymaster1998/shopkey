<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin-trade.html" var="tradeUrl"/>
<html>
<head>
    <title>Hóa đơn</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-${alert} alert-dismissible fade show">
                    <strong>${messageResponse}</strong>
                </div>
            </c:if>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Hóa đơn</h6>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-12">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Mã hóa đơn</th>
                                <th>Ngày lập</th>
                                <th>Tổng tiền</th>
                                <th>Tên tài khoản</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${items}" var="item">
                                <tr>
                                    <td>${item.billId}</td>
                                    <td>${item.createdDate}</td>
                                    <td>$${item.totalMoney}.00</td>
                                    <td>${item.userDTO.userName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
</body>
</html>
