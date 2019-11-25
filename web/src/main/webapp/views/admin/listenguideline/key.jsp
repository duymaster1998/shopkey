<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value='/ajax-guideline-listen-edit.html' var="listenGuidelineEditUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url value="/admin-guideline-listen-list.html" var="listUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title>Quản lý key game</title>
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
            <h6 class="m-0 font-weight-bold text-primary">Key</h6>
        </div>
        <div class="card-body">
            <form action="${listUrl}" method="get" id="formUrl">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Mã key</th>
                                    <th>Ngày nhập</th>
                                    <th>Ngày xuất</th>
                                    <th>Trạng thái</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Mã key</th>
                                    <th>Ngày nhập</th>
                                    <th>Ngày xuất</th>
                                    <th>Trạng thái</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <tr>
                                    <td>JKHX300590</td>
                                    <td>12/12/2018</td>
                                    <td>22/12/2018</td>
                                    <td>Đã bán</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="crudaction" id="crudaction"/>
                <input type="hidden" name="urlType" id="urlType"/>
            </form>
        </div>
    </div>
</div>
<script src="<c:url value='/template/admin/js/jquery-3.2.1.min.js'/>"></script>
</body>
</html>
