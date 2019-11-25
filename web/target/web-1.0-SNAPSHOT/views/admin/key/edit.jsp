<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/ajax-edit-key.html" var="editKeyUrl">
    <c:param name="urlType" value="url_key"/>
</c:url>
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: blue;">
                Thêm key cho sản phẩm
            </h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
            </button>
        </div>
        <div class="modal-body">
            <div class="container-fluid">
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <form class="login_form_inner" action="${editKeyUrl}" method="post"
                              id="editKeyForm">
                            <div class="form-group">
                                <label>Key sản phẩm :</label>
                                <br/><br/>
                                <input type="text" class="form-control" name="pojo.productKey"
                                       placeholder="Key sản phẩm" required pattern="^[0-9A-Z]\w{4}-[0-9A-Z]\w{4}-[0-9A-Z]\w{4}$"
                                       id="title">
                            </div>
                            <br/><br/>
                            <div style="text-align: right">
                                <button class="btn btn-secondary" type="button" data-dismiss="modal"><fmt:message
                                        key="label.cancel"
                                        bundle="${lang}"/></button>
                                <input type="submit" class="btn btn-primary" id="btnSave" value="<fmt:message key="label.update"
                                                                                                        bundle="${lang}"/>">
                            </div>
                            <c:if test="${not empty item.productId}">
                                <input type="hidden" name="productId" value="${item.productId}">
                            </c:if>
                            <c:if test="${not empty item.producerDTO.producerId}">
                                <input type="hidden" name="producerId" value="${item.producerDTO.producerId}">
                            </c:if>
                            <c:if test="${not empty item.categoryDTO.categoryId}">
                                <input type="hidden" name="categoryId" value="${item.categoryDTO.categoryId}">
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
