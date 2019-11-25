<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Quản lý người dùng</title>
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
            <h6 class="m-0 font-weight-bold text-primary">Người dùng</h6>
        </div>
        <div class="card-body">
            <form action="#" method="get" id="formUrl">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.user.firstname" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.user.lastname" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.user.username" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.password" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.phonenumber" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.hometown" bundle="${lang}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="user" items="${items}">
                                    <tr>
                                        <td>${user.firstName}</td>
                                        <td>${user.lastName}</td>
                                        <td>${user.userName}</td>
                                        <td>${user.password}</td>
                                        <td>${user.phoneNumber}</td>
                                        <td>${user.homeTown}</td>
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
