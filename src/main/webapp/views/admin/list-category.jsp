<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
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
                <a href="${pageContext.request.contextPath}/admin/category/list" class="active">Quản lý Danh mục</a>
                <a href="${pageContext.request.contextPath}/admin/clothing/list">Quản lý Quần áo</a>
                <a href="${pageContext.request.contextPath}/admin/user/list">Quản lý Tài khoản</a>
                <a href="${pageContext.request.contextPath}/home">Xem Trang Chủ</a>
            </div>

            <div class="col-md-10 p-4">
                <h2>Quản lý danh mục</h2>
                <p class="text-muted">Nơi bạn có thể quản lý danh mục của mình</p>
                
                <div class="d-flex justify-content-between mb-3">
                    <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary btn-sm">+ Thêm danh mục mới</a>
                    <form action="${pageContext.request.contextPath}/admin/category/list" method="get" class="d-flex">
                        <input type="text" name="keyword" value="${keyword}" class="form-control form-control-sm me-2" placeholder="Tìm danh mục...">
                        <button type="submit" class="btn btn-secondary btn-sm">Tìm</button>
                    </form>
                </div>

                <table class="table table-bordered table-striped align-middle">
                    <thead>
                        <tr>
                            <th style="width: 60px;">STT</th>
                            <th style="width: 150px;">Hình ảnh</th>
                            <th>Tên danh mục</th>
                            <th style="width: 150px;">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cateList}" var="cate" varStatus="STT">
                            <tr class="odd gradeX">
                                <td>${STT.index+1}</td>
                                <td>
                                    <c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
                                    <c:choose>
                                        <c:when test="${not empty cate.icon}">
                                            <img height="80" width="100" style="object-fit: cover;" src="${imgUrl}" />
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">No Image</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${cate.name}</td>
                                <td>
                                    <a href="<c:url value='/admin/category/edit?id=${cate.id}'/>" class="btn btn-sm btn-outline-primary">Sửa</a> |
                                    <a href="<c:url value='/admin/category/delete?id=${cate.id}'/>" class="btn btn-sm btn-outline-danger" onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
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