<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Sản Phẩm (Bảng Products)</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .sidebar { background: #007bff; min-height: 90vh; color: white; padding: 15px 0; }
        .sidebar a { color: white; display: block; padding: 10px 20px; text-decoration: none; border-bottom: 1px solid rgba(255,255,255,0.1); }
        .sidebar a:hover, .sidebar a.active { background: #0056b3; font-weight: bold; }
    </style>
</head>
<body>
    <div class="bg-primary text-white p-3 d-flex justify-content-between align-items-center">
        <h4 class="mb-0 fw-bold">Dashboard Admin</h4>
        <div>
            <span>Xin chào <strong>${sessionScope.account.fullName}</strong></span> |
            <a href="${pageContext.request.contextPath}/logout" class="text-white text-decoration-none">Đăng xuất</a>
        </div>
    </div>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2 px-0 sidebar">
                <div class="text-center py-2"><p class="mb-0"><strong>Menu Quản Lý</strong></p></div>
                <a href="${pageContext.request.contextPath}/admin/home">Dashboard</a>
                <a href="${pageContext.request.contextPath}/admin/category/list">Quản lý Danh mục</a>
                <a href="${pageContext.request.contextPath}/admin/products" class="active">Quản lý Products (CRUD)</a>
                <a href="${pageContext.request.contextPath}/admin/clothing/list">Quản lý Quần áo</a>
                <a href="${pageContext.request.contextPath}/admin/user/list">Quản lý Tài khoản</a>
                <a href="${pageContext.request.contextPath}/home">Xem Trang Chủ</a>
                <a href="${pageContext.request.contextPath}/product">Xem Trang Phân Trang /product</a>
            </div>

            <div class="col-md-10 p-4">
                <h2>Quản lý Sản Phẩm (Products Table)</h2>
                <hr>
                <div class="d-flex justify-content-between mb-3">
                    <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-success btn-sm">+ Thêm Sản Phẩm Mới</a>
                </div>

                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Hình ảnh</th>
                            <th>Tên Sản Phẩm</th>
                            <th>Danh Mục</th>
                            <th>Số Lượng</th>
                            <th>Giá (VNĐ)</th>
                            <th>Mô Tả</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${productList}" var="p">
                            <tr>
                                <td>${p.productId}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty p.image}">
                                            <img height="60" width="60" style="object-fit: cover; border-radius: 4px;" src="${pageContext.request.contextPath}/image?fname=${p.image}" alt="${p.productName}" />
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted small">No Image</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><strong>${p.productName}</strong></td>
                                <td>${p.categoryName}</td>
                                <td>${p.quantity}</td>
                                <td class="text-danger fw-bold"><fmt:formatNumber value="${p.price}" pattern="#,###" /> đ</td>
                                <td class="small text-muted" style="max-width: 250px;">${p.description}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}" class="btn btn-sm btn-info text-white me-1" target="_blank">Xem</a>
                                    <a href="${pageContext.request.contextPath}/admin/product/edit?id=${p.productId}" class="btn btn-sm btn-primary me-1">Sửa</a>
                                    <a href="${pageContext.request.contextPath}/admin/product/delete?id=${p.productId}" class="btn btn-sm btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này?');">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty productList}">
                            <tr>
                                <td colspan="8" class="text-center py-4 text-muted">Chưa có sản phẩm nào trong bảng products.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
