<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý quần áo</title>
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
                <a href="${pageContext.request.contextPath}/admin/clothing/list" class="active">Quản lý Quần áo</a>
                <a href="${pageContext.request.contextPath}/admin/user/list">Quản lý Tài khoản</a>
                <a href="${pageContext.request.contextPath}/home">Xem Trang Chủ</a>
            </div>

            <div class="col-md-10 p-4">
                <h2>Quản lý quần áo</h2>
                <hr>
                <div class="d-flex justify-content-between mb-3">
                    <a href="${pageContext.request.contextPath}/admin/clothing/add" class="btn btn-success btn-sm">+ Thêm quần áo</a>
                    <form action="${pageContext.request.contextPath}/admin/clothing/list" method="get" class="d-flex">
                        <input type="text" name="keyword" value="${keyword}" class="form-control form-control-sm me-2" placeholder="Tìm tên...">
                        <select name="cateId" class="form-select form-select-sm me-2">
                            <option value="">Tất cả loại</option>
                            <c:forEach items="${categories}" var="cat">
                                <option value="${cat.id}" ${selectedCateId == cat.id ? 'selected' : ''}>${cat.name}</option>
                            </c:forEach>
                        </select>
                        <button type="submit" class="btn btn-secondary btn-sm">Lọc</button>
                    </form>
                </div>

                <table class="table table-bordered table-striped align-middle">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Hình ảnh</th>
                            <th>Tên quần áo</th>
                            <th>Loại</th>
                            <th>Size</th>
                            <th>Màu</th>
                            <th>Số lượng</th>
                            <th>Giá (VNĐ)</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${clothingList}" var="c">
                            <tr>
                                <td>${c.clothingId}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty c.image}">
                                            <img height="60" width="60" style="object-fit: cover;" src="${pageContext.request.contextPath}/image?fname=${c.image}" />
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">No Img</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><strong>${c.clothingName}</strong></td>
                                <td>${c.categoryName}</td>
                                <td>${c.size}</td>
                                <td>${c.color}</td>
                                <td>${c.quantity}</td>
                                <td class="text-danger fw-bold"><fmt:formatNumber value="${c.price}" pattern="#,###" /></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/admin/clothing/edit?id=${c.clothingId}" class="btn btn-sm btn-outline-primary">Sửa</a> |
                                    <a href="${pageContext.request.contextPath}/admin/clothing/delete?id=${c.clothingId}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>