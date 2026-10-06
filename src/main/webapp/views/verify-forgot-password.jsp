<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Xác Thực OTP & Đặt Lại Mật Khẩu</title>
</head>
<body>

<div class="row justify-content-center my-4">
    <div class="col-md-6 col-lg-5">
        <div class="card shadow-sm border-0 rounded-3">
            <div class="card-header bg-primary text-white text-center py-3">
                <h4 class="mb-0 fw-bold"><i class="bi bi-shield-lock me-2"></i>Đặt Lại Mật Khẩu</h4>
            </div>
            <div class="card-body p-4">

                <c:if test="${not empty sessionScope.otpNotice}">
                    <div class="alert alert-info alert-dismissible fade show small" role="alert">
                        <i class="bi bi-info-circle-fill me-2"></i>${sessionScope.otpNotice}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <c:if test="${not empty alert}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>${alert}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/verify-forgot-password" method="post" class="needs-validation" novalidate>
                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-envelope me-1"></i>Email <span class="text-danger">*</span></label>
                        <input type="email" name="email" class="form-control" value="${email}" placeholder="Email của bạn" required>
                        <div class="invalid-feedback">Vui lòng nhập địa chỉ email hợp lệ.</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-key me-1"></i>Mã OTP (6 chữ số) <span class="text-danger">*</span></label>
                        <input type="text" name="otp" class="form-control text-center fw-bold fs-4" placeholder="123456" maxlength="6" pattern="^[0-9]{6}$" required autofocus>
                        <div class="invalid-feedback">Vui lòng nhập mã OTP gồm 6 chữ số.</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-lock me-1"></i>Mật khẩu mới <span class="text-danger">*</span></label>
                        <input type="password" name="newPassword" class="form-control" placeholder="Nhập mật khẩu mới (ít nhất 6 ký tự)" required minlength="6">
                        <div class="invalid-feedback">Mật khẩu mới phải có ít nhất 6 ký tự.</div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label fw-semibold"><i class="bi bi-lock-fill me-1"></i>Xác nhận mật khẩu mới <span class="text-danger">*</span></label>
                        <input type="password" name="confirmPassword" class="form-control" placeholder="Xác nhận lại mật khẩu mới" required minlength="6">
                        <div class="invalid-feedback">Vui lòng xác nhận mật khẩu mới.</div>
                    </div>

                    <button type="submit" class="btn btn-success w-100 py-2 fw-bold shadow-sm">
                        <i class="bi bi-check-circle me-1"></i>Lưu Mật Khẩu Mới
                    </button>

                    <div class="text-center mt-3">
                        <a href="${pageContext.request.contextPath}/login" class="small text-decoration-none fw-semibold">
                            <i class="bi bi-arrow-left me-1"></i>Quay lại Đăng nhập
                        </a>
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
