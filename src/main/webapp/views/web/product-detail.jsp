<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Sản Phẩm - ${product.productName}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="/views/common/topbar.jsp"></jsp:include>
    <jsp:include page="/views/common/header.jsp"></jsp:include>

    <div class="container my-5">
        <nav aria-label="breadcrumb" class="mb-4">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/home">Trang Chủ</a></li>
                <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/product">Sản Phẩm</a></li>
                <li class="breadcrumb-item active" aria-current="page">${product.productName}</li>
            </ol>
        </nav>

        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <div class="row g-4 align-items-center">
                    <div class="col-md-5 text-center">
                        <c:choose>
                            <c:when test="${not empty product.image}">
                                <img src="${pageContext.request.contextPath}/image?fname=${product.image}" class="img-fluid rounded shadow-sm" style="max-height: 380px; object-fit: cover;" alt="${product.productName}">
                            </c:when>
                            <c:otherwise>
                                <img src="https://via.placeholder.com/400x380?text=No+Image" class="img-fluid rounded" alt="No Image">
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="col-md-7">
                        <span class="badge bg-secondary mb-2">${product.categoryName}</span>
                        <h2 class="fw-bold mb-3">${product.productName}</h2>
                        <h3 class="text-danger fw-bold mb-3">
                            <fmt:formatNumber value="${product.price}" pattern="#,###" /> VNĐ
                        </h3>
                        <p class="mb-2"><strong>Mã sản phẩm (ID):</strong> #${product.productId}</p>
                        <p class="mb-3"><strong>Số lượng còn lại:</strong> <span class="badge bg-success fs-6">${product.quantity}</span></p>
                        <hr>
                        <h5 class="fw-bold mb-2">Mô tả sản phẩm:</h5>
                        <p class="text-muted leading-relaxed mb-4">${not empty product.description ? product.description : 'Chưa có mô tả cho sản phẩm này.'}</p>

                        <div class="d-flex gap-2">
                            <button class="btn btn-danger btn-lg px-4" onclick="alert('Cảm ơn bạn đã quan tâm sản phẩm!')">Thêm Vào Giỏ Hàng</button>
                            <a href="${pageContext.request.contextPath}/product" class="btn btn-outline-secondary btn-lg px-4">&larr; Quay lại danh sách sản phẩm</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/views/common/footer.jsp"></jsp:include>
</body>
</html>
