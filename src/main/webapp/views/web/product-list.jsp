<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Sản Phẩm - Phân Trang 6 SP / Trang</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="/views/common/topbar.jsp"></jsp:include>
    <jsp:include page="/views/common/header.jsp"></jsp:include>

    <div class="container my-4">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <div>
                <h3 class="fw-bold mb-1">Danh Sách Sản Phẩm</h3>
                <p class="text-muted small mb-0">URL: <code>/product</code> | Phân trang: <b>6 sản phẩm / trang</b> (Tổng số: ${totalProducts} sản phẩm)</p>
            </div>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-outline-secondary btn-sm">&larr; Quay lại Trang Chủ</a>
        </div>

        <!-- Product Grid (6 products per page) -->
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4 mb-4">
            <c:forEach items="${productList}" var="p">
                <div class="col">
                    <div class="card h-100 shadow-sm border-0">
                        <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}">
                            <c:choose>
                                <c:when test="${not empty p.image}">
                                    <img src="${pageContext.request.contextPath}/image?fname=${p.image}" class="card-img-top" style="height: 220px; object-fit: cover;" alt="${p.productName}">
                                </c:when>
                                <c:otherwise>
                                    <img src="https://via.placeholder.com/300x220?text=Product" class="card-img-top" style="height: 220px; object-fit: cover;" alt="No Image">
                                </c:otherwise>
                            </c:choose>
                        </a>
                        <div class="card-body d-flex flex-column">
                            <span class="badge bg-secondary mb-2 align-self-start">${p.categoryName}</span>
                            <h5 class="card-title fw-bold">
                                <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}" class="text-dark text-decoration-none">${p.productName}</a>
                            </h5>
                            <p class="card-text text-danger fw-bold fs-5 mb-2">
                                <fmt:formatNumber value="${p.price}" pattern="#,###" /> đ
                            </p>
                            <p class="card-text small text-muted text-truncate mb-3">${p.description}</p>
                            <a href="${pageContext.request.contextPath}/product/detail?id=${p.productId}" class="btn btn-primary mt-auto">Xem chi tiết sản phẩm</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

            <c:if test="${empty productList}">
                <div class="col-12 text-center py-5">
                    <p class="text-muted fs-5">Không có sản phẩm nào ở trang này.</p>
                </div>
            </c:if>
        </div>

        <!-- Pagination Controls -->
        <c:if test="${totalPages > 1}">
            <nav aria-label="Product pagination" class="d-flex justify-content-center my-4">
                <ul class="pagination pagination-lg">
                    <!-- Previous Page -->
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/product?page=${currentPage - 1}">&laquo; Trước</a>
                    </li>

                    <!-- Page Numbers -->
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                            <a class="page-link" href="${pageContext.request.contextPath}/product?page=${i}">${i}</a>
                        </li>
                    </c:forEach>

                    <!-- Next Page -->
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="${pageContext.request.contextPath}/product?page=${currentPage + 1}">Sau &raquo;</a>
                    </li>
                </ul>
            </nav>
        </c:if>
    </div>

    <jsp:include page="/views/common/footer.jsp"></jsp:include>
</body>
</html>
