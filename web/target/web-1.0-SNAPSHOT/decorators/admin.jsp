<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/login.html" var="logoutUrl">
    <c:param name="action" value="logout"/>
</c:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><dec:title default="Admin Page"/></title>
    <!-- Custom fonts for this template-->
    <link href="<c:url value='/template/admin/assets/vendor/fontawesome-free/css/all.min.css'/>" rel="stylesheet"
          type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <!-- jQuery library -->
    <!-- Custom styles for this template-->
    <link href="<c:url value='/template/admin/assets/css/sb-admin-2.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/template/admin/assets/vendor/datatables/dataTables.bootstrap4.min.css'/> "
          rel="stylesheet">
    <dec:head/>
</head>
<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
    <%@include file="/common/admin/menu.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <%@include file="/common/admin/header.jsp" %>
            <dec:body/>
        </div>
        <%@include file="/common/admin/footer.jsp" %>
    </div>
</div>
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>
<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Bạn chắc chắn muốn thoát không?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Bấm 'Thoát' nếu như bạn chắc chắn rằng mình muốn thoát.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal"><fmt:message key="label.cancel"
                                                                                                  bundle="${lang}"/></button>
                <a class="btn btn-primary" href="${logoutUrl}"><fmt:message key="label.logout.out" bundle="${lang}"/></a>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="<c:url value='/template/admin/assets/vendor/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

<!-- Core plugin JavaScript-->
<script src="<c:url value='/template/admin/assets/vendor/jquery-easing/jquery.easing.min.js'/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url value='/template/admin/assets/js/sb-admin-2.min.js'/>"></script>

<!-- Page level plugins -->
<%--<script src="<c:url value='/template/admin/assets/vendor/chart.js/Chart.min.js'/>"></script>--%>

<script src="<c:url value='/template/admin/assets/vendor/datatables/jquery.dataTables.min.js'/>"></script>
<script src="<c:url value='/template/admin/assets/vendor/datatables/dataTables.bootstrap4.min.js'/>"></script>
<!-- Page level custom scripts -->
<%--<script src="<c:url value='/template/admin/assets/js/demo/chart-area-demo.js'/>"></script>--%>
<script src="<c:url value='/template/admin/assets/js/demo/chart-pie-demo.js'/>"></script>
<script src="<c:url value='/template/admin/assets/js/demo/datatables-demo.js'/>"></script>
</body>
</html>