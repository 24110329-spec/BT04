<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .sidebar { background: #007bff; min-height: 90vh; color: white; padding: 15px 0; }
        .sidebar a { color: white; display: block; padding: 10px 20px; text-decoration: none; border-bottom: 1px solid rgba(255,255,255,0.1); }
        .sidebar a:hover, .sidebar a.active { background: #0056b3; font-weight: bold; }
    </style>
</head>
<body>
    <!-- Top Header -->
    <div class="bg-primary text-white p-3 d-flex justify-content-between align-items-center">
        <h4 class="mb-0 fw-bold">Dashboard</h4>
        <div>
            <span>Xin chào <strong>${sessionScope.account.fullName}</strong></span> |
            <a href="${pageContext.request.contextPath}/logout" class="text-white text-decoration-none">Đăng xuất</a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <!-- Left Sidebar -->
            <div class="col-md-2 px-0 sidebar">
                <div class="text-center py-2">
                    <p class="mb-0"><strong>Bạn là Admin</strong></p>
                </div>
                <a href="${pageContext.request.contextPath}/admin/home" class="active">Dashboard</a>
                <a href="${pageContext.request.contextPath}/admin/category/list">Quản lý Danh mục</a>
                <a href="${pageContext.request.contextPath}/admin/clothing/list">Quản lý Quần áo</a>
                <a href="${pageContext.request.contextPath}/admin/user/list">Quản lý Tài khoản</a>
                <a href="${pageContext.request.contextPath}/home">Xem Trang Chủ</a>
            </div>

            <!-- Content Area -->
            <div class="col-md-10 p-4">
                <h2>Tổng quan hệ thống</h2>
                <hr>
                <div class="row g-3 mb-4">
                    <div class="col-md-4">
                        <div class="card bg-light p-3 border">
                            <h5>Danh mục quần áo</h5>
                            <h3>${categoryCount} danh mục</h3>
                            <a href="${pageContext.request.contextPath}/admin/category/list" class="btn btn-sm btn-outline-primary mt-2">Quản lý</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card bg-light p-3 border">
                            <h5>Sản phẩm quần áo</h5>
                            <h3>${clothingCount} sản phẩm</h3>
                            <a href="${pageContext.request.contextPath}/admin/clothing/list" class="btn btn-sm btn-outline-primary mt-2">Quản lý</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card bg-light p-3 border">
                            <h5>Tài khoản người dùng</h5>
                            <h3>${userCount} tài khoản</h3>
                            <a href="${pageContext.request.contextPath}/admin/user/list" class="btn btn-sm btn-outline-primary mt-2">Quản lý</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>