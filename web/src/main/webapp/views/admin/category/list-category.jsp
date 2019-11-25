<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="addCategoryUrl" value="/admin-list-category.html"/>
<html>
<head>
    <title>Quản lý thể loại</title>
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
            <h6 class="m-0 font-weight-bold text-primary">Thêm thể loại</h6>
        </div>
        <div class="card-body">
            <form action="${addCategoryUrl}" method="post" id="addFormCategory">
                <div class="row">
                    <div class="col-md-4 form-group">
                    </div>
                    <div class="col-md-3 form-group">
                        <label>Tên thể loại :</label>
                        <input type="text" class="form-control" name="categoryName" required pattern="^[a-zA-Z\s]+$">
                    </div>
                    <div class="col-md-5"></div>
                    <div class="col-md-5"></div>
                    <input type="submit" class="btn btn-primary" value="Thêm thể loại">
                </div>
            </form>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Thể loại & Nhà sản xuất</h6>
        </div>
        <div class="card-body">
            <form action="#" method="get" id="formUrl">
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-bordered" id="producerTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th><fmt:message key="label.producer.producerid" bundle="${lang}"/></th>
                                <th><fmt:message key="label.producer.producername" bundle="${lang}"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="producer" items="${itemsProducer}">
                                <tr>
                                    <td>${producer.producerId}</td>
                                    <td>${producer.producerName}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="col-md-6">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.category.categoryid" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.category.categoryname" bundle="${lang}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="category" items="${items}">
                                    <tr>
                                        <td>${category.categoryId}</td>
                                        <td>${category.categoryName}</td>
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
<script>
    $(document).ready(function () {
       $('#producerTable').dataTable();
    });
</script>
</body>
</html>