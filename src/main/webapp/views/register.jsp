<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
</head>
<body>

<div class="row justify-content-center my-4">
    <div class="col-md-7 col-lg-6">
        <div class="card shadow-sm border-0 rounded-3">
            <div class="card-header bg-success text-white text-center py-3">
                <h4 class="mb-0 fw-bold"><i class="bi bi-person-plus me-2"></i>Đăng Ký Tài Khoản Mới</h4>
            </div>
            <div class="card-body p-4">

                <c:if test="${not empty alert}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="bi bi-exclamation-triangle-fill me-2"></i>
                        <div>${alert}</div>
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <form action="${pageContext.request.contextPath}/register" method="post" class="needs-validation" novalidate>
                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-person me-1"></i>Tên đăng nhập <span class="text-danger">*</span></label>
                        <input type="text" placeholder="Nhập tên đăng nhập (ít nhất 3 ký tự)" name="username" class="form-control" value="${not empty username ? username : param.username}" required minlength="3" pattern="^[a-zA-Z0-9_]+$">
                        <div class="invalid-feedback">Vui lòng nhập tên đăng nhập (tối thiểu 3 ký tự, không chứa ký tự đặc biệt).</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-card-heading me-1"></i>Họ và tên <span class="text-danger">*</span></label>
                        <input type="text" placeholder="Nhập họ và tên" name="fullname" class="form-control" value="${not empty fullname ? fullname : param.fullname}" required maxlength="100">
                        <div class="invalid-feedback">Vui lòng nhập họ và tên.</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-envelope me-1"></i>Địa chỉ Email <span class="text-danger">*</span></label>
                        <input type="email" placeholder="ví dụ: user@gmail.com" name="email" class="form-control" value="${not empty email ? email : param.email}" required>
                        <div class="invalid-feedback">Vui lòng nhập địa chỉ email hợp lệ.</div>
                    </div>

                    <div class="mb-3">
                        <label class="form-label fw-semibold"><i class="bi bi-telephone me-1"></i>Số điện thoại</label>
                        <input type="text" placeholder="ví dụ: 0912345678" name="phone" class="form-control" value="${not empty phone ? phone : param.phone}" pattern="^0[0-9]{9,10}$">
                        <div class="invalid-feedback">Số điện thoại phải gồm 10-11 chữ số bắt đầu bằng số 0.</div>
                    </div>

                    <div class="mb-4">
                        <label class="form-label fw-semibold"><i class="bi bi-lock me-1"></i>Mật khẩu <span class="text-danger">*</span></label>
                        <input type="password" placeholder="Nhập mật khẩu (ít nhất 6 ký tự)" name="password" class="form-control" required minlength="6">
                        <div class="invalid-feedback">Mật khẩu phải có ít nhất 6 ký tự.</div>
                    </div>

                    <button type="submit" class="btn btn-success w-100 py-2 fw-bold shadow-sm">
                        <i class="bi bi-check-lg me-1"></i>Đăng ký tài khoản
                    </button>

                    <div class="text-center mt-3">
                        <span class="text-muted">Đã có tài khoản? </span>
                        <a href="${pageContext.request.contextPath}/login" class="fw-bold text-decoration-none">Đăng nhập ngay</a>
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