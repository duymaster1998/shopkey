<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel" style="color: blue;">
                Chi tiết hóa đơn
            </h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">×</span>
            </button>
        </div>
        <div class="modal-body">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th style="text-align: center">Tên game</th>
                    <th style="text-align: center">Key</th>
                    <th style="text-align: center">Giá</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="detail" items="${items}">
                    <tr>
                        <td style="text-align: center">
                            ${detail.name}
                        </td>
                        <td style="text-align: center">
                            ${detail.key}
                        </td>
                        <td style="text-align: center">
                                $${detail.price}.00
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
