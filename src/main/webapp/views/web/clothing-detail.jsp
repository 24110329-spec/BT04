<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Quần Áo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/views/common/topbar.jsp"></jsp:include>
    <jsp:include page="/views/common/header.jsp"></jsp:include>

    <div class="container my-4">
        <a href="${pageContext.request.contextPath}/home" class="btn btn-sm btn-secondary mb-3">« Quay lại</a>

        <c:if test="${not empty clothing}">
            <div class="card p-3">
                <div class="row">
                    <div class="col-md-4 text-center">
                        <c:choose>
                            <c:when test="${not empty clothing.image}">
                                <img src="${pageContext.request.contextPath}/image?fname=${clothing.image}" class="img-fluid border" style="max-height: 300px;" alt="${clothing.clothingName}">
                            </c:when>
                            <c:otherwise>
                                <img src="https://via.placeholder.com/300x300?text=No+Image" class="img-fluid border" alt="No Image">
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-md-8">
                        <h3>${clothing.clothingName}</h3>
                        <h4 class="text-danger fw-bold"><fmt:formatNumber value="${clothing.price}" pattern="#,###" /> VNĐ</h4>
                        <table class="table table-bordered mt-3" style="max-width: 400px;">
                            <tr><th style="width: 140px;">Danh mục</th><td>${clothing.categoryName}</td></tr>
                            <tr><th>Kích cỡ (Size)</th><td>${clothing.size}</td></tr>
                            <tr><th>Màu sắc</th><td>${clothing.color}</td></tr>
                            <tr><th>Số lượng còn</th><td>${clothing.quantity}</td></tr>
                            <tr><th>Mô tả</th><td>${clothing.description}</td></tr>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <jsp:include page="/views/common/footer.jsp"></jsp:include>
</body>
</html>