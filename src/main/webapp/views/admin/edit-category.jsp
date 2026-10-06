<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa danh mục</title>
</head>
<body>
    <div class="row justify-content-center my-3">
        <div class="col-md-6">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="card-title mb-0 fw-bold"><i class="bi bi-pencil-square me-2"></i>Chỉnh Sửa Danh Mục</h5>
                </div>
                <div class="card-body p-4">

                    <c:if test="${not empty errorMsg}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            <div>${errorMsg}</div>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/admin/category/edit" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        <input type="hidden" name="id" value="${category.id}" />

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Mã danh mục (ID)</label>
                            <input type="text" class="form-control bg-light" value="${category.id}" readonly />
                        </div>

                        <div class="mb-3">
                            <label class="form-label fw-semibold">Tên danh mục <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" name="name" value="${category.name}" required maxlength="100" />
                            <div class="invalid-feedback">Vui lòng nhập tên danh mục (tối đa 100 ký tự).</div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">Ảnh đại diện (Icon hiện tại)</label>
                            <c:if test="${not empty category.icon}">
                                <div class="mb-2">
                                    <img src="${pageContext.request.contextPath}/${category.icon}" class="img-thumbnail" style="max-height: 80px;" alt="Category Icon">
                                </div>
                            </c:if>
                            <input type="file" class="form-control" name="icon" accept="image/png, image/jpeg, image/gif, image/webp" />
                            <div class="form-text">Để trống nếu không muốn thay đổi ảnh.</div>
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-outline-secondary">
                                <i class="bi bi-arrow-left me-1"></i>Hủy / Quay lại
                            </a>
                            <button type="submit" class="btn btn-primary px-4">
                                <i class="bi bi-save me-1"></i>Cập nhật danh mục
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