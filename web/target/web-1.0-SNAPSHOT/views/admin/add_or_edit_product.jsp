<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="editUrl" value="/admin-add-product.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Thêm/Cập nhật sản phẩm</title>
    <script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js"/>"></script>
    <style>
        .error {
            font-size: 14px;
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Sản phẩm</h6>
        </div>
        <div class="card-body">
            <!-- Begin Page Content -->
            <form class="login_form_inner" action="${editUrl}" method="post" id="formEdit"
                  enctype="multipart/form-data">
                <div class="row">
                    <div class="col-md-3 form-group">
                        <label>Tên sản phẩm :</label>
                        <input type="text" class="form-control"
                               name="pojo.productName"
                               id="productName"
                               placeholder="Tên sản phẩm" value="${item.pojo.productName}">
                        <br/>
                        <label>Giá sản phẩm :</label>
                        <input type="text" class="form-control"
                               name="pojo.price"
                               id="productPrice"
                               placeholder="Giá sản phẩm" value="${item.pojo.price}">
                        <br/>
                        <c:choose>
                            <c:when test="${empty item.pojo.productId}">
                                <label for="productCategory">Chọn thể loại :</label>
                                <select class="form-control" id="productCategory" name="categoryId">
                                    <c:forEach var="category" items="${categorys}">
                                        <option value="${category.categoryId}">${category.categoryName}</option>
                                    </c:forEach>
                                </select>
                                <br/>
                                <label for="productProducer">Chọn nhà sản xuất :</label>
                                <select class="form-control" id="productProducer" name="producerId">
                                    <c:forEach var="producer" items="${producers}">
                                        <option value="${producer.producerId}">${producer.producerName}</option>
                                    </c:forEach>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <label for="productCategory">Chọn thể loại :</label>
                                <select class="form-control" id="productCategory" name="categoryId">
                                    <option value="${item.pojo.categoryDTO.categoryId}">${item.pojo.categoryDTO.categoryName}</option>
                                    <c:forEach var="category" items="${categorys}">
                                        <c:if test="${item.pojo.categoryDTO.categoryId != category.categoryId}">
                                            <option value="${category.categoryId}">${category.categoryName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <br/>
                                <label for="productProducer">Chọn nhà sản xuất :</label>
                                <select class="form-control" id="productProducer" name="producerId">
                                    <option value="${item.pojo.producerDTO.producerId}">${item.pojo.producerDTO.producerName}</option>
                                    <c:forEach var="producer" items="${producers}">
                                        <c:if test="${item.pojo.producerDTO.producerId != producer.producerId}">
                                            <option value="${producer.producerId}">${producer.producerName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                        <br/>
                        <label>Đăng hình ảnh :</label>
                        <c:choose>
                            <c:when test="${empty item.pojo.productId}">
                                <input type="file" id="uploadImage" name="file">
                            </c:when>
                            <c:otherwise>
                                <input type="file" name="file" id="updateImage">
                                <input type="hidden" name="productImage" value="${item.pojo.image}">
                            </c:otherwise>
                        </c:choose>
                        <br/><br/>
                        <c:if test="${not empty item.pojo.image}">
                            <c:set value="/repository/${item.pojo.image}"
                                   var="image"/>
                        </c:if>
                        <img src="${image}" id="viewImage" width="150px" height="235ox">
                    </div>
                    <div class="col-md-9 form-group">
                        <label>Giới thiệu sản phẩm :</label>
                        <c:if test="${not empty item.pojo.productDescription}">
                            <c:set value="${item.pojo.productDescription}" var="description"/>
                        </c:if>
                        <textarea name="pojo.productDescription" cols="100" rows="10"
                                  id="produceDescription">${description}</textarea>
                        <br/>
                        <label>Nội dung sản phẩm :</label>
                        <c:if test="${not empty item.pojo.content}">
                            <c:set value="${item.pojo.content}" var="content"/>
                        </c:if>
                        <textarea name="pojo.content" cols="100" rows="10"
                                  id="productContent">${content}</textarea>
                        <br/><br/>
                        <input type="submit" class="btn btn-primary" id="btnSubmitEdit" value="Hoàn thành">
                    </div>
                    <c:if test="${not empty item.pojo.productId}">
                        <input type="hidden" name="pojo.productId" value="${item.pojo.productId}">
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- /.container-fluid -->
<script src="<c:url value='/template/web/js/jquery-3.2.1.min.js'/>"></script>
<script src="<c:url value='/template/web/js/jquery.validate.min.js'/>"></script>
<script>
    $(document).ready(function () {
        CKEDITOR.replace('produceDescription');
        CKEDITOR.replace('productContent');
        $('#uploadImage').change(function () {
            readURL(this, "viewImage");
        });
        $('#updateImage').change(function () {
           readURL(this,"viewImage");
        });
        validateForm();
    });

    function validateForm() {
        $.validator.addMethod(
            "regex",
            function (value, element, regexp) {
                var re = new RegExp(regexp);
                return this.optional(element) || re.test(value);
            },
            "Please check your input."
        );
        $('#formEdit').validate({
            ignore: [],
            rules: [],
            messages: []
        });
        $('#productName').rules("add", {
            regex: "^[a-zA-Z\\ ():-\\w]+$",
            required: true,
            messages: {
                regex: "Không đúng định dạng !",
                required: "Không để trống tên sản phẩm !"
            }
        });
        $('#uploadImage').rules("add", {
            required: true,
            messages: {
                required: "Không để trống hình ảnh !"
            }
        });
        $('#productPrice').rules("add", {
            regex: "^[0-9]*$",
            required: true,
            messages: {
                regex: "Giá chỉ ở dạng số !",
                required: "Không để trống giá !"
            }
        });
        $('#productCategory').rules("add", {
            required: true,
            messages: {
                required: "Không để trống thể loại !"
            }
        });
        $('#productProducer').rules("add", {
            required: true,
            messages: {
                required: "Không để trống nhà sản xuất !"
            }
        });
        $('#produceDescription').rules("add", {
            required: function () {
                CKEDITOR.instances.produceDescription.updateElement();
            },
            messages: {
                required: "Không để trống giới thiệu !"
            }
        });
        $('#productContent').rules("add", {
            required: function () {
                CKEDITOR.instances.productContent.updateElement();
            },
            messages: {
                required: "Không để trống nội dung !"
            }
        });
    }

    function readURL(input, imageId) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' + imageId).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    };
</script>
</body>
</html>