<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/ajax-guideline-listen-edit.html" var="editListenGuideLineUrl">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:when test="${empty messageResponse}">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <c:if test="${not empty item.pojo.listenGuideLineId}">
                        <h5 class="modal-title" id="exampleModalLabel">
                            <fmt:message key="label.guideline.list.reapir" bundle="${lang}"/>
                        </h5>
                    </c:if>
                    <c:if test="${empty item.pojo.listenGuideLineId}">
                        <h5 class="modal-title" id="exampleModalLabel">
                            <fmt:message key="label.guidline.list.add" bundle="${lang}"/>
                        </h5>
                    </c:if>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="user" action="${editListenGuideLineUrl}" method="post" id="editListenGuideLineForm">
                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" name="pojo.title"
                                   placeholder="<fmt:message key="label.guideline.listen.title" bundle="${lang}"/>"
                                   id="title" value="${item.pojo.title}">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control form-control-user" name="pojo.content"
                                   placeholder="<fmt:message key="label.guideline.listen.content" bundle="${lang}"/>"
                                   id="content" value="${item.pojo.content}">
                        </div>
                        <c:if test="${not empty item.pojo.listenGuideLineId}">
                            <input type="hidden" name="pojo.listenGuideLineId" value="${item.pojo.listenGuideLineId}"/>
                        </c:if>
                        <input type="hidden" name="crudaction" id="crudactionEdit"/>
                        <c:if test="${not empty item.pojo.createdDate}">
                            <input type="hidden" name="pojo.createdDate" value="${item.pojo.createdDate}"/>
                        </c:if>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal"><fmt:message key="label.cancel"
                                                                                                      bundle="${lang}"/></button>
                    <button type="button" class="btn btn-primary" id="btnSave"><fmt:message key="label.update"
                                                                                            bundle="${lang}"/></button>
                </div>
            </div>
        </div>
    </c:when>
</c:choose>
