<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa sản phẩm</title>
</head>
<body>
    <div class="row justify-content-center my-3">
        <div class="col-md-8 col-lg-7">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="card-title mb-0 fw-bold"><i class="bi bi-pencil-square me-2"></i>Chỉnh Sửa Sản Phẩm (Products)</h5>
                </div>
                <div class="card-body p-4">

                    <c:if test="${not empty errorMsg}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            <div>${errorMsg}</div>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/admin/product/edit" method="post" class="needs-validation" novalidate>
                        <input type="hidden" name="id" value="${product.productId}" />

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Tên sản phẩm <span class="text-danger">*</span></label>
                            <input type="text" name="productName" class="form-control" value="${product.productName}" required>
                            <div class="invalid-feedback">Vui lòng nhập tên sản phẩm.</div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Danh mục <span class="text-danger">*</span></label>
                            <select name="cateId" class="form-select" required>
                                <c:forEach items="${categories}" var="c">
                                    <option value="${c.id}" ${product.category.id == c.id ? 'selected' : ''}>${c.name}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Vui lòng chọn danh mục sản phẩm.</div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Giá bán (VNĐ) <span class="text-danger">*</span></label>
                                <input type="number" min="1" step="1000" name="price" class="form-control" value="${product.price}" required>
                                <div class="invalid-feedback">Vui lòng nhập giá bán (> 0).</div>
                            </div>

                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Số lượng <span class="text-danger">*</span></label>
                                <input type="number" min="0" name="quantity" class="form-control" value="${product.quantity}" required>
                                <div class="invalid-feedback">Vui lòng nhập số lượng (>= 0).</div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Tên file / URL hình ảnh</label>
                            <input type="text" name="image" class="form-control" value="${product.image}">
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">Mô tả sản phẩm</label>
                            <textarea name="description" class="form-control" rows="3">${product.description}</textarea>
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-outline-secondary">
                                <i class="bi bi-arrow-left me-1"></i>Hủy / Quay lại
                            </a>
                            <button type="submit" class="btn btn-primary px-4">
                                <i class="bi bi-save me-1"></i>Cập nhật sản phẩm
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

<script>
    (function () {
        'use strict'
        var forms = document.querySelectorAll('.needs-validation')
        Array.prototype.slice.call(forms).forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
        })
    })()
</script>
</body>
</html>
