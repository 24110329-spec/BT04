<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quên Mật Khẩu</title>
</head>
<body>

<div class="row justify-content-center my-4">
    <div class="col-md-6 col-lg-5">
        <div class="card shadow-sm border-0 rounded-3">
            <div class="card-header bg-warning text-dark text-center py-3">
                <h4 class="mb-0 fw-bold"><i class="bi bi-key me-2"></i>Quên Mật Khẩu</h4>
            </div>
            <div class="card-body p-4">
                <p class="text-muted small text-center mb-4">Vui lòng nhập email đăng ký. Hệ thống sẽ gửi mã OTP khôi phục về email của bạn.</p>

                <c:if test="${not empty alert}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>${alert}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/forgot-password" method="post" class="needs-validation" novalidate>
                    <div class="mb-4">
                        <label class="form-label fw-semibold"><i class="bi bi-envelope me-1"></i>Địa chỉ Email <span class="text-danger">*</span></label>
                        <input type="email" name="email" class="form-control" value="${email}" placeholder="ví dụ: user@gmail.com" required autofocus>
                        <div class="invalid-feedback">Vui lòng nhập địa chỉ email hợp lệ.</div>
                    </div>

                    <button type="submit" class="btn btn-warning w-100 py-2 fw-bold text-dark shadow-sm">
                        <i class="bi bi-send me-1"></i>Gửi Mã OTP Qua Email
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
