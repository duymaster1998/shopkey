<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="productEditUrl" value="/admin-add-product.html">
    <c:param name="urlType" value="url_edit"/>
    <c:param name="crudaction" value="insert"/>
</c:url>
<c:url var="listUrl" value="/admin-home.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title>Quản lý sản phẩm</title>
    <style>
        .tooltipc {
            position: relative;
            display: inline-block;
        }

        .tooltipc .tooltiptextc {
            visibility: hidden;
            width: 120px;
            background-color: black;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;

            /* Position the tooltip */
            position: absolute;
            z-index: 1;
            top: -5px;
            right: 105%;
        }

        .tooltipc:hover .tooltiptextc {
            visibility: visible;
        }
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
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
            <form action="${listUrl}" method="post" id="formUrl">
                <div class="row">
                    <div class="col-sm-12">
                        <div style="float:right;text-align: right">
                            <button type="button" class="btn btn-danger btn-circle" id="deleteAll" disabled
                                    onclick="warningBeforeDelete()"
                                    data-toggle="tooltip" data-placement="bottom"
                                    title="<fmt:message key="label.admin.button.deleteAll" bundle="${lang}"/>">
                                <i class="fas fa-trash"></i>
                            </button>
                        </div>
                        <div style="float: right;text-align: right;margin-right: 5px">
                            <a href="${productEditUrl}" style="text-decoration: none">
                                <button type="button" class="btn btn-success btn-circle" data-toggle="tooltip"
                                        title="<fmt:message key="label.admin.button.add" bundle="${lang}"/>"
                                        data-placement="bottom">
                                    <i class="fas fa-plus"></i>
                                </button>
                            </a>
                        </div>
                        <br/>
                        <br/>
                        <div class="table-responsive">
                            <table class="table table-bordered" id="mydataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th><fmt:message key="label.product.name" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.product.price" bundle="${lang}"/></th>
                                    <th><fmt:message key="label.product.image" bundle="${lang}"/></th>
                                    <th style="text-align: center">
                                        <fieldset>
                                            <input style="height:17px;margin:0 .25em;width:17px" type="checkbox"
                                                   id="checkAll"/>
                                        </fieldset>
                                    </th>
                                    <th><fmt:message key="label.guideline.listen.fun" bundle="${lang}"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="product" items="${items}">
                                    <tr>
                                        <td>${product.productName}</td>
                                        <td>$${product.price}.00</td>
                                        <td style="text-align: center"><img src="<c:url value="/repository/${product.image}"/>" id="viewImage" width="100px" height="150ox"></td>
                                        <td style="text-align: center">
                                            <fieldset>
                                                <input style="height:17px;margin:0 .25em;width:17px" type="checkbox"
                                                       name="checkList" id="checkbox_${product.productId}"
                                                       value="${product.productId}" class="check-box"/>
                                            </fieldset>
                                        </td>
                                        <td>
                                            <c:url value="/ajax-edit-key.html" var="keyUrl">
                                                <c:param name="urlType" value="url_key"/>
                                                <c:param name="pojo.productId" value="${product.productId}"/>
                                                <c:param name="categoryId" value="${product.categoryDTO.categoryId}"/>
                                                <c:param name="producerId" value="${product.producerDTO.producerId}"/>
                                            </c:url>
                                            <c:url var="editProductUrl" value="/admin-add-product.html">
                                                <c:param name="urlType" value="url_edit"/>
                                                <c:param name="crudaction" value="update"/>
                                                <c:param name="pojo.productId" value="${product.productId}"/>
                                            </c:url>
                                            <div style="text-align: left">
                                                <button type="button" class="btn btn-success btn-circle btn-sm tooltipc"
                                                        onclick="update('${keyUrl}')">
                                                    <i class="fas fa-plus"></i>
                                                    <span class="tooltiptextc"><fmt:message key="label.admin.button.add.key"
                                                                                            bundle="${lang}"/></span>
                                                </button>
                                                <a href="${editProductUrl}" style="text-decoration: none">
                                                    <button type="button" class="btn btn-info btn-circle btn-sm tooltipc">
                                                        <i class="fas fa-pen"></i>
                                                        <span class="tooltiptextc"><fmt:message
                                                                key="label.admin.button.repair" bundle="${lang}"/></span>
                                                    </button>
                                                </a>
                                                <c:url value="/admin-list-key.html" var="listKeyUrl">
                                                    <c:param name="urlType" value="url_key"/>
                                                    <c:param name="productId" value="${product.productId}"/>
                                                </c:url>
                                                <a href="${listKeyUrl}" style="text-decoration: none">
                                                    <button type="button"
                                                            class="btn btn-primary btn-circle btn-sm tooltipc">
                                                        <i class="fas fa-search"></i>
                                                        <span class="tooltiptextc"><fmt:message
                                                                key="label.admin.button.search.key"
                                                                bundle="${lang}"/></span>
                                                    </button>
                                                </a>
                                            </div>
                                        </td>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true"/>
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
<%--<script src="<c:url value='/template/web/js/jquery.validate.min.js'/>"></script>--%>
<script src="<c:url value='/template/admin/js/global_admin_script_8.js'/>"></script>
<script>
    $(document).ready(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    function update(keyUrl) {
        var editUrl = keyUrl;
        $('#myModal').load(editUrl, '', function () {
            $('#myModal').modal('toggle');
        });
    }
    function warningBeforeDelete() {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.value) {
                $('#formUrl').submit();
            } else if (
                result.dismiss === Swal.DismissReason.cancel
            ) {
                Swal.fire(
                    'Cancelled',
                    'Your imaginary file is safe :)',
                    'error'
                )
            }
        });
    }
</script>
</body>
</html>
