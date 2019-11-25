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
    <title>Quản lý sản phẩm</title>
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
            <h6 class="m-0 font-weight-bold text-primary">Sản phẩm</h6>
        </div>
        <div class="card-body">
            <form action="${listUrl}" method="get" id="formUrl">
                <div class="row">
                    <div class="col-sm-12">
                        <div style="float:right;text-align: right">
                            <button type="button" class="btn btn-danger btn-circle" id="deleteAll" disabled
                                    data-toggle="tooltip" onclick="warningBeforeDelete()"
                                    title="<fmt:message key="label.admin.button.deleteAll" bundle="${lang}"/>">
                                <i class="fas fa-trash"></i>
                            </button>
                        </div>
                        <div style="float: right;text-align: right;margin-right: 5px">
                            <button type="button" class="btn btn-success btn-circle" data-toggle="tooltip"
                                    title="<fmt:message key="label.admin.button.add" bundle="${lang}"/>"
                                    onclick="update(this)">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                        <br/>
                        <br/>
                        <div class="table-responsive">
                            <table class="table table-bordered" id="mydataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá bán</th>
                                    <th>Ảnh</th>
                                    <th style="text-align: center">
                                        <fieldset class="form-group">
                                            <input style="height:17px;margin:0 .25em;width:17px" type="checkbox"
                                                   id="checkAll"/>
                                        </fieldset>
                                    </th>
                                    <th>Chức năng</th>
                                    <%--<th><fmt:message key="label.guideline.listen.id" bundle="${lang}"/></th>--%>
                                    <%--<th><fmt:message key="label.guideline.listen.title" bundle="${lang}"/></th>--%>
                                    <%--<th><fmt:message key="label.guideline.listen.content" bundle="${lang}"/></th>--%>
                                    <%--<th style="text-align: center">--%>
                                    <%--<fieldset class="form-group">--%>
                                    <%--<input style="height:17px;margin:0 .25em;width:17px" type="checkbox"--%>
                                    <%--id="checkAll"/>--%>
                                    <%--</fieldset>--%>
                                    <%--</th>--%>
                                    <%--<th><fmt:message key="label.guideline.listen.fun" bundle="${lang}"/></th>--%>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Tên sản phẩm</th>
                                    <th>Giá bán</th>
                                    <th>Ảnh</th>
                                    <th>Checkbox</th>
                                    <th>Chức năng</th>
                                    <%--<th><fmt:message key="label.guideline.listen.id" bundle="${lang}"/></th>--%>
                                    <%--<th><fmt:message key="label.guideline.listen.title" bundle="${lang}"/></th>--%>
                                    <%--<th><fmt:message key="label.guideline.listen.content" bundle="${lang}"/></th>--%>
                                    <%--<th style="text-align: center">--%>
                                    <%--check--%>
                                    <%--</th>--%>
                                    <%--<th><fmt:message key="label.guideline.listen.fun" bundle="${lang}"/></th>--%>
                                </tr>
                                </tfoot>
                                <tbody>
                                <tr>
                                    <td>pubg</td>
                                    <td>15$</td>
                                    <td>pubg.jpg</td>
                                    <td style="text-align: center">
                                        <fieldset>
                                            <input style="height:17px;margin:0 .25em;width:17px" type="checkbox"
                                                   name="checkList" id="checkbox_${user.listenGuideLineId}"
                                                   value="${user.listenGuideLineId}" class="check-box"/>
                                        </fieldset>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-success btn-circle btn-sm"
                                                data-toggle="tooltip"
                                                title="Thêm key">
                                            <i class="fas fa-plus"></i>
                                        </button>
                                        <button type="button" class="btn btn-info btn-circle btn-sm"
                                                data-toggle="tooltip"
                                                title="Sửa">
                                            <i class="fas fa-pen"></i>
                                        </button>
                                        <button type="button" class="btn btn-primary btn-circle btn-sm"
                                                data-toggle="tooltip"
                                                title="Xem key">
                                            <i class="fas fa-search"></i>
                                        </button>
                                    </td>
                                </tr>
                                <%--<c:forEach var="user" items="${items}">--%>
                                <%--<tr>--%>
                                <%--<td><c:out value="${user.listenGuideLineId}"/></td>--%>
                                <%--<td><c:out value="${user.title}"/></td>--%>
                                <%--<td><c:out value="${user.content}"/></td>--%>
                                <%--<td style="text-align: center">--%>
                                <%--<fieldset>--%>
                                <%--<input style="height:17px;margin:0 .25em;width:17px" type="checkbox"--%>
                                <%--name="checkList" id="checkbox_${user.listenGuideLineId}"--%>
                                <%--value="${user.listenGuideLineId}" class="check-box"/>--%>
                                <%--</fieldset>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                <%--<c:url value="/ajax-guideline-listen-edit.html" var="editListenUrl">--%>
                                <%--<c:param name="pojo.listenGuideLineId"--%>
                                <%--value="${user.listenGuideLineId}"/>--%>
                                <%--<c:param name="urlType" value="url_edit"/>--%>
                                <%--</c:url>--%>
                                <%--<button type="button" class="btn btn-info btn-circle btn-sm"--%>
                                <%--data-toggle="tooltip" data-placement="bottom"--%>
                                <%--title="<fmt:message key="label.admin.button.repair" bundle="${lang}"/>"--%>
                                <%--src-url="${editListenUrl}" onclick="update(this)">--%>
                                <%--<i class="fas fa-pen"></i>--%>
                                <%--</button>--%>
                                <%--</td>--%>
                                <%--</tr>--%>
                                <%--</c:forEach>--%>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true"/>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script>
    $(document).ready(function () {

    });

    function warningBeforeDelete() {
        showAlertBeforeDelete(function () {
            $('#urlType').val('url_list');
            $('#crudaction').val('redirect_delete');
            $('#formUrl').submit();
        });
    }

    function showAlertBeforeDelete(callback) {
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false,
        });

        swalWithBootstrapButtons.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Yes, delete it!',
            cancelButtonText: 'No, cancel!',
            reverseButtons: true
        }).then((result) => {
            if (result.value) {
                callback();
            } else if (
                // Read more about handling dismissals
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                )
            }
        })
    }

    function update(btn) {
        var editUrl = $(btn).attr('src-url');
        if (typeof editUrl == 'undefined') {
            editUrl = '${listenGuidelineEditUrl}';
        }
        $('#myModal').load(editUrl, '', function () {
            $('#myModal').modal('toggle');
            addOrEditListenGuideLine();
        });
    }

    function addOrEditListenGuideLine() {
        $('#btnSave').click(function () {
            $('#editListenGuideLineForm').submit();
        });
        $('#editListenGuideLineForm').submit(function (e) {
            e.preventDefault();
            $('#crudactionEdit').val('insert_update');
            $.ajax({
                type: 'POST',
                url: $(this).attr('action'),
                data: $(this).serialize(),
                dataType: 'html',
                success: function (res) {
                    if (res.trim() == "redirect_insert") {
                        $('#crudaction').val('redirect_insert');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_update") {
                        $('#crudaction').val('redirect_update');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    } else if (res.trim() == "redirect_error") {
                        $('#crudaction').val('redirect_error');
                        $('#urlType').val('url_list');
                        $('#formUrl').submit();
                    }
                },
                error: function (res) {
                    console.log(res);
                }
            });
        })
    }

</script>
</body>
</html>
