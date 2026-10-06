<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kích Hoạt Tài Khoản</title>
</head>
<body>

<div class="row justify-content-center my-4">
    <div class="col-md-6 col-lg-5">
        <div class="card shadow-sm border-0 rounded-3">
            <div class="card-header bg-success text-white text-center py-3">
                <h4 class="mb-0 fw-bold"><i class="bi bi-shield-check me-2"></i>Kích Hoạt Tài Khoản</h4>
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

                <form action="${pageContext.request.contextPath}/verify-otp" method="post" class="needs-validation" novalidate>
                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-envelope me-1"></i>Email tài khoản <span class="text-danger">*</span></label>
                        <input type="email" name="email" class="form-control" value="${email}" placeholder="ví dụ: user@gmail.com" required>
                        <div class="invalid-feedback">Vui lòng nhập địa chỉ email hợp lệ.</div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label fw-semibold"><i class="bi bi-key me-1"></i>Mã OTP (6 chữ số) <span class="text-danger">*</span></label>
                        <input type="text" name="otp" class="form-control text-center fw-bold fs-4" placeholder="123456" maxlength="6" pattern="^[0-9]{6}$" required autofocus>
                        <div class="invalid-feedback">Vui lòng nhập mã OTP gồm 6 chữ số.</div>
                    </div>

                    <button type="submit" class="btn btn-success w-100 py-2 fw-bold shadow-sm">
                        <i class="bi bi-check-lg me-1"></i>Kích Hoạt Tài Khoản
                    </button>

                    <div class="text-center mt-3">
                        <span class="text-muted">Đã kích hoạt? </span>
                        <a href="${pageContext.request.contextPath}/login" class="fw-bold text-decoration-none">Đăng nhập</a>
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
