<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập</title>
</head>
<body>

<div class="row justify-content-center my-4">
    <div class="col-md-6 col-lg-5">
        <div class="card shadow-sm border-0 rounded-3">
            <div class="card-header bg-primary text-white text-center py-3">
                <h4 class="mb-0 fw-bold"><i class="bi bi-box-arrow-in-right me-2"></i>Đăng Nhập Hệ Thống</h4>
            </div>
            <div class="card-body p-4">

                <c:if test="${not empty sessionScope.registerSuccess}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="bi bi-check-circle-fill me-2"></i>${sessionScope.registerSuccess}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                    <% session.removeAttribute("registerSuccess"); %>
                </c:if>

                <c:if test="${not empty alert}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>${alert}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <c:if test="${not empty alertHtml}">
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-circle-fill me-2"></i>${alertHtml}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/login" method="post" class="needs-validation" novalidate>
                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-person me-1"></i>Tên đăng nhập hoặc Email <span class="text-danger">*</span></label>
                        <input type="text" placeholder="Nhập tên đăng nhập hoặc email" name="username" class="form-control" value="${param.username}" required>
                        <div class="invalid-feedback">Vui lòng nhập tên đăng nhập hoặc email.</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-lock me-1"></i>Mật khẩu <span class="text-danger">*</span></label>
                        <input type="password" placeholder="Nhập mật khẩu" name="password" class="form-control" required minlength="1">
                        <div class="invalid-feedback">Vui lòng nhập mật khẩu.</div>
                    </div>

                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="remember" id="remember">
                            <label class="form-check-label text-secondary" for="remember">Ghi nhớ đăng nhập</label>
                        </div>
                        <a href="${pageContext.request.contextPath}/forgot-password" class="small text-decoration-none fw-semibold">Quên mật khẩu?</a>
                    </div>

                    <button type="submit" class="btn btn-primary w-100 py-2 fw-bold shadow-sm">
                        <i class="bi bi-box-arrow-in-right me-1"></i>Đăng nhập
                    </button>

                    <div class="text-center mt-3">
                        <span class="text-muted">Chưa có tài khoản? </span>
                        <a href="${pageContext.request.contextPath}/register" class="fw-bold text-decoration-none">Đăng ký ngay</a>
                    </div>

                    <hr class="my-4">
                    <div class="p-3 bg-light rounded text-secondary small">
                        <strong class="text-dark"><i class="bi bi-info-circle me-1"></i>Tài khoản dùng thử:</strong><br>
                        • Admin: <code>admin</code> / <code>12345</code><br>
                        • Manager: <code>manager</code> / <code>12345</code><br>
                        • User: <code>user</code> / <code>12345</code>
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