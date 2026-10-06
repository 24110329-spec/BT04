<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Portal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="/views/common/topbar.jsp"></jsp:include>
    <jsp:include page="/views/common/header.jsp"></jsp:include>

    <div class="container my-4">
        <div class="alert alert-info">
            <h4>Trang Quản Lý (Manager)</h4>
            <p class="mb-0">Xin chào <strong>${sessionScope.account.fullName}</strong>. Đây là giao diện dành cho vai trò Quản lý.</p>
        </div>

        <div class="row">
            <div class="col-md-6">
                <h5>Danh Mục Sản Phẩm</h5>
                <ul class="list-group">
                    <c:forEach items="${categoryList}" var="cat">
                        <li class="list-group-item d-flex justify-content-between">${cat.name} <span class="badge bg-secondary">ID: ${cat.id}</span></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-md-6">
                <h5>Tồn Kho Quần Áo</h5>
                <ul class="list-group">
                    <c:forEach items="${clothingList}" var="c">
                        <li class="list-group-item d-flex justify-content-between">${c.clothingName} <span class="badge bg-success">Số lượng: ${c.quantity}</span></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>

    <jsp:include page="/views/common/footer.jsp"></jsp:include>
</body>
</html>