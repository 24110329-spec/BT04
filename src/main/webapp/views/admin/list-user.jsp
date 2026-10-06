<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý tài khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .sidebar { background: #007bff; min-height: 90vh; color: white; padding: 15px 0; }
        .sidebar a { color: white; display: block; padding: 10px 20px; text-decoration: none; border-bottom: 1px solid rgba(255,255,255,0.1); }
        .sidebar a:hover, .sidebar a.active { background: #0056b3; font-weight: bold; }
    </style>
</head>
<body>
    <div class="bg-primary text-white p-3 d-flex justify-content-between align-items-center">
        <h4 class="mb-0 fw-bold">Dashboard</h4>
        <div>
            <span>Xin chào <strong>${sessionScope.account.fullName}</strong></span> |
            <a href="${pageContext.request.contextPath}/logout" class="text-white text-decoration-none">Đăng xuất</a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2 px-0 sidebar">
                <div class="text-center py-2"><p class="mb-0"><strong>Bạn là Admin</strong></p></div>
                <a href="${pageContext.request.contextPath}/admin/home">Dashboard</a>
                <a href="${pageContext.request.contextPath}/admin/category/list">Quản lý Danh mục</a>
                <a href="${pageContext.request.contextPath}/admin/clothing/list">Quản lý Quần áo</a>
                <a href="${pageContext.request.contextPath}/admin/user/list" class="active">Quản lý Tài khoản</a>
                <a href="${pageContext.request.contextPath}/home">Xem Trang Chủ</a>
            </div>

            <div class="col-md-10 p-4">
                <h2>Danh sách người dùng</h2>
                <hr>
                <table class="table table-bordered table-striped align-middle">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tài khoản</th>
                            <th>Họ và Tên</th>
                            <th>Email</th>
                            <th>Điện thoại</th>
                            <th>Role ID</th>
                            <th>Ngày tạo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${userList}" var="u">
                            <tr>
                                <td>${u.id}</td>
                                <td><strong>${u.userName}</strong></td>
                                <td>${u.fullName}</td>
                                <td>${u.email}</td>
                                <td>${u.phone}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${u.roleid == 1}"><span class="badge bg-danger">Admin (1)</span></c:when>
                                        <c:when test="${u.roleid == 2}"><span class="badge bg-warning text-dark">Manager (2)</span></c:when>
                                        <c:otherwise><span class="badge bg-info text-dark">User (3)</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${u.createdDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>