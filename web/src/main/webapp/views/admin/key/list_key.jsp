<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Danh sách key</title>
</head>
<body>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Danh sách key</h6>
        </div>
        <div class="card-body">
            <form action="#" method="get" id="formUrl">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.key.productkey" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.key.keystatus" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.key.createddate" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.key.exportdate" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.key.productName" bundle="${lang}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="key" items="${items}">
                                    <tr>
                                        <td>${key.productKey}</td>
                                        <td>${key.keyStatus}</td>
                                        <td>${key.createdDate}</td>
                                        <td>${key.exportDate}</td>
                                        <td>${key.productDTO.productName}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<c:url value='/template/admin/assets/vendor/jquery/jquery.min.js'/>"></script>
</body>
</html>