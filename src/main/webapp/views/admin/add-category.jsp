<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục mới</title>
</head>
<body>
    <div class="row justify-content-center my-3">
        <div class="col-md-6">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white py-3">
                    <h5 class="card-title mb-0 fw-bold"><i class="bi bi-folder-plus me-2"></i>Thêm Danh Mục Mới</h5>
                </div>
                <div class="card-body p-4">

                    <c:if test="${not empty errorMsg}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle-fill me-2"></i>
                            <div>${errorMsg}</div>
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/admin/category/add" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                        <div class="mb-3">
                            <label class="form-label fw-semibold">Tên danh mục <span class="text-danger">*</span></label>
                            <input type="text" class="form-control" placeholder="Nhập tên danh mục..." name="name" value="${not empty name ? name : param.name}" required maxlength="100" />
                            <div class="invalid-feedback">Vui lòng nhập tên danh mục (tối đa 100 ký tự).</div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-semibold">Ảnh đại diện (Icon)</label>
                            <input type="file" class="form-control" name="icon" accept="image/png, image/jpeg, image/gif, image/webp" />
                            <div class="form-text">Định dạng file: JPG, JPEG, PNG, GIF, WEBP.</div>
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-outline-secondary">
                                <i class="bi bi-arrow-left me-1"></i>Hủy / Quay lại
                            </a>
                            <button type="submit" class="btn btn-primary px-4">
                                <i class="bi bi-check-lg me-1"></i>Thêm danh mục
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