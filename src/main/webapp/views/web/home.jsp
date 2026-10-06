<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Chủ - Danh Sách Quần Áo</title>
</head>
<body>

<!-- Top 10 Newest Products Section -->
<div class="d-flex justify-content-between align-items-center mb-3">
    <h3 class="mb-0 text-primary fw-bold"><i class="bi bi-fire me-2 text-danger"></i>10 Sản Phẩm Mới Nhất</h3>
    <a href="${pageContext.request.contextPath}/product" class="btn btn-sm btn-outline-primary fw-semibold">
        Xem tất cả sản phẩm (Phân trang) <i class="bi bi-arrow-right ms-1"></i>
    </a>
</div>

<div class="row row-cols-1 row-cols-md-3 row-cols-lg-5 g-3 mb-5">
    <c:forEach items="${top10Products}" var="p">
        <div class="col">
            <div class="card h-100 shadow-sm border-0 rounded-3">
                <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}">
                    <c:choose>
                        <c:when test="${not empty p.image}">
                            <img src="${pageContext.request.contextPath}/image?fname=${p.image}" class="card-img-top rounded-top-3" style="height: 180px; object-fit: cover;" alt="${p.productName}">
                        </c:when>
                        <c:otherwise>
                            <img src="https://via.placeholder.com/200x180?text=Product" class="card-img-top rounded-top-3" style="height: 180px; object-fit: cover;" alt="No Image">
                        </c:otherwise>
                    </c:choose>
                </a>
                <div class="card-body d-flex flex-column">
                    <h6 class="card-title fw-bold text-truncate">
                        <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}" class="text-dark text-decoration-none">${p.productName}</a>
                    </h6>
                    <p class="card-text text-danger fw-bold mb-1">
                        <fmt:formatNumber value="${p.price}" pattern="#,###" /> đ
                    </p>
                    <span class="badge bg-secondary mb-2 align-self-start">${p.categoryName}</span>
                    <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}" class="btn btn-sm btn-primary mt-auto">
                        <i class="bi bi-eye me-1"></i>Xem chi tiết
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
    <c:if test="${empty top10Products}">
        <div class="col-12 text-center py-4">
            <p class="text-muted">Chưa có sản phẩm nào trong bảng products.</p>
        </div>
    </c:if>
</div>

<h4 class="mb-3 border-bottom pb-2 fw-bold text-dark"><i class="bi bi-grid-fill me-2 text-warning"></i>Danh Sách Quần Áo</h4>

<!-- Filter Buttons -->
<div class="mb-4 d-flex flex-wrap gap-2">
    <a href="${pageContext.request.contextPath}/home" class="btn ${empty selectedCateId ? 'btn-dark' : 'btn-outline-dark'} btn-sm px-3">Tất cả</a>
    <c:forEach items="${categories}" var="cat">
        <a href="${pageContext.request.contextPath}/home?cateId=${cat.id}" class="btn ${selectedCateId == cat.id ? 'btn-dark' : 'btn-outline-dark'} btn-sm px-3">
            ${cat.name}
        </a>
    </c:forEach>
</div>

<!-- Product Grid -->
<div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-3">
    <c:forEach items="${clothingList}" var="c">
        <div class="col">
            <div class="card h-100 shadow-sm border-0 rounded-3">
                <c:choose>
                    <c:when test="${not empty c.image}">
                        <img src="${pageContext.request.contextPath}/image?fname=${c.image}" class="card-img-top rounded-top-3" style="height: 200px; object-fit: cover;" alt="${c.clothingName}">
                    </c:when>
                    <c:otherwise>
                        <img src="https://via.placeholder.com/200x200?text=Quan+Ao" class="card-img-top rounded-top-3" style="height: 200px; object-fit: cover;" alt="No Image">
                    </c:otherwise>
                </c:choose>
                <div class="card-body d-flex flex-column">
                    <h6 class="card-title fw-bold">${c.clothingName}</h6>
                    <p class="card-text text-danger fw-bold mb-1">
                        <fmt:formatNumber value="${c.price}" pattern="#,###" /> đ
                    </p>
                    <p class="card-text small text-muted mb-2">Loại: ${c.categoryName} | Size: ${c.size} | Màu: ${c.color}</p>
                    <a href="${pageContext.request.contextPath}/product/detail?id=${c.clothingId}" class="btn btn-sm btn-outline-primary w-100 mt-auto">
                        <i class="bi bi-eye me-1"></i>Xem chi tiết
                    </a>
                </div>
            </div>
        </div>
    </c:forEach>
    <c:if test="${empty clothingList}">
        <div class="col-12 text-center py-4">
            <p class="text-muted">Không có sản phẩm nào phù hợp.</p>
        </div>
    </c:if>
</div>

</body>
</html>