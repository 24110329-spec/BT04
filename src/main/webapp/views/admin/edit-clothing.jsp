<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa quần áo</title>
</head>
<body>
    <div class="row justify-content-center my-3">
        <div class="col-md-8 col-lg-7">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="card-title mb-0 fw-bold"><i class="bi bi-pencil-square me-2"></i>Chỉnh Sửa Sản Phẩm Quần Áo</h5>
                </div>
                <div class="card-body p-4">

                    <c:if test="${not empty errorMsg}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            <div>${errorMsg}</div>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/admin/clothing/edit" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        <input type="hidden" name="clothingId" value="${clothing.clothingId}" />

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Tên quần áo <span class="text-danger">*</span></label>
                            <input class="form-control" name="clothingName" value="${clothing.clothingName}" required />
                            <div class="invalid-feedback">Vui lòng nhập tên sản phẩm quần áo.</div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Danh mục <span class="text-danger">*</span></label>
                            <select name="cateId" class="form-select" required>
                                <c:forEach items="${categories}" var="c">
                                    <option value="${c.id}" ${clothing.cateId == c.id ? 'selected' : ''}>${c.name}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Vui lòng chọn danh mục sản phẩm.</div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Kích thước (Size)</label>
                                <input class="form-control" name="size" value="${clothing.size}" />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Màu sắc</label>
                                <input class="form-control" name="color" value="${clothing.color}" />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Số lượng <span class="text-danger">*</span></label>
                                <input type="number" min="0" class="form-control" name="quantity" value="${clothing.quantity}" required />
                                <div class="invalid-feedback">Vui lòng nhập số lượng (>= 0).</div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-semibold">Giá bán (VNĐ) <span class="text-danger">*</span></label>
                                <input type="number" min="1" step="1000" class="form-control" name="price" value="${clothing.price}" required />
                                <div class="invalid-feedback">Vui lòng nhập giá bán hợp lệ (> 0).</div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Ảnh sản phẩm hiện tại</label>
                            <c:if test="${not empty clothing.image}">
                                <div class="mb-2">
                                    <img src="${pageContext.request.contextPath}/${clothing.image}" class="img-thumbnail" style="max-height: 100px;" alt="Clothing Image">
                                </div>
                            </c:if>
                            <input type="file" class="form-control" name="image" accept="image/png, image/jpeg, image/gif, image/webp" />
                            <div class="form-text">Để trống nếu không muốn thay đổi ảnh.</div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">Mô tả sản phẩm</label>
                            <textarea class="form-control" rows="3" name="description">${clothing.description}</textarea>
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/admin/clothing/list" class="btn btn-outline-secondary">
                                <i class="bi bi-arrow-left me-1"></i>Hủy / Quay lại
                            </a>
                            <button type="submit" class="btn btn-primary px-4">
                                <i class="bi bi-save me-1"></i>Cập nhật quần áo
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