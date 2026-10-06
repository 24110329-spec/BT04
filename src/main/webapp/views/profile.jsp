<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Thông tin cá nhân</title>
</head>
<body>

<div class="row justify-content-center">
    <div class="col-md-8 col-lg-6">
        <div class="card shadow-sm border-0 rounded-3">
            <div class="card-header bg-primary text-white py-3">
                <h5 class="card-title mb-0 fw-bold">
                    <i class="bi bi-person-circle me-2"></i>Cập nhật thông tin cá nhân
                </h5>
            </div>
            <div class="card-body p-4">

                <c:if test="${param.success == '1'}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="bi bi-check-circle-fill me-2"></i>Cập nhật thông tin tài khoản thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <c:if test="${not empty errorMsg}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                        <div>${errorMsg}</div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                    
                    <!-- Avatar Preview & Upload -->
                    <div class="text-center mb-4">
                        <div class="position-relative d-inline-block">
                            <c:choose>
                                <c:when test="${not empty user.avatar}">
                                    <img id="avatarPreview" src="${pageContext.request.contextPath}/${user.avatar}" 
                                         class="rounded-circle img-thumbnail shadow-sm" 
                                         style="width: 140px; height: 140px; object-fit: cover;">
                                </c:when>
                                <c:otherwise>
                                    <img id="avatarPreview" src="https://via.placeholder.com/140?text=No+Avatar" 
                                         class="rounded-circle img-thumbnail shadow-sm" 
                                         style="width: 140px; height: 140px; object-fit: cover;">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="mt-2 text-muted small">Ảnh đại diện hiện tại</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            <i class="bi bi-image me-1"></i>Thay đổi ảnh đại diện (Tối đa 5MB)
                        </label>
                        <input type="file" name="image" id="imageInput" class="form-control" accept="image/png, image/jpeg, image/gif, image/webp" onchange="previewFile()">
                        <div class="form-text">Định dạng được hỗ trợ: JPG, JPEG, PNG, GIF, WEBP.</div>
                    </div>

                    <!-- Readonly Username -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            <i class="bi bi-person me-1"></i>Tên đăng nhập (Username)
                        </label>
                        <input type="text" class="form-control bg-light" value="${user.userName}" readonly>
                    </div>

                    <!-- Readonly Email -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            <i class="bi bi-envelope me-1"></i>Email
                        </label>
                        <input type="email" class="form-control bg-light" value="${user.email}" readonly>
                    </div>

                    <!-- Editable FullName -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">
                            <i class="bi bi-card-heading me-1"></i>Họ và tên <span class="text-danger">*</span>
                        </label>
                        <input type="text" name="fullName" class="form-control" value="${user.fullName}" required maxlength="100" placeholder="Nhập họ và tên...">
                        <div class="invalid-feedback">Vui lòng nhập họ và tên (tối đa 100 ký tự).</div>
                    </div>

                    <!-- Editable Phone -->
                    <div class="mb-4">
                        <label class="form-label fw-semibold">
                            <i class="bi bi-telephone me-1"></i>Số điện thoại <span class="text-danger">*</span>
                        </label>
                        <input type="text" name="phone" class="form-control" value="${user.phone}" required pattern="^0[0-9]{9,10}$" placeholder="Ví dụ: 0912345678">
                        <div class="invalid-feedback">Vui lòng nhập số điện thoại hợp lệ (10-11 chữ số bắt đầu bằng 0).</div>
                    </div>

                    <!-- Buttons -->
                    <div class="d-flex justify-content-between align-items-center">
                        <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-secondary">
                            <i class="bi bi-arrow-left me-1"></i>Quay lại
                        </a>
                        <button type="submit" class="btn btn-primary px-4">
                            <i class="bi bi-save me-1"></i>Lưu thay đổi
                        </button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function previewFile() {
        const preview = document.getElementById('avatarPreview');
        const file = document.getElementById('imageInput').files[0];
        const reader = new FileReader();

        reader.addEventListener("load", function () {
            preview.src = reader.result;
        }, false);

        if (file) {
            reader.readAsDataURL(file);
        }
    }

    // Bootstrap client-side form validation script
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