<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${not empty sitemesh_title ? sitemesh_title : 'Quản Lý Quần Áo'}" /></title>

    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f4f6f9;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .main-content {
            flex: 1;
            padding-top: 25px;
            padding-bottom: 40px;
        }
        .navbar-brand {
            font-weight: 700;
            letter-spacing: 0.5px;
        }
        .avatar-nav {
            width: 32px;
            height: 32px;
            object-fit: cover;
            border-radius: 50%;
        }
    </style>

    ${sitemesh_head}
</head>
<body>

    <!-- Header Navigation -->
    <jsp:include page="/views/common/header.jsp"/>

    <!-- Main Content Area -->
    <main class="container main-content">
        ${sitemesh_body}
    </main>

    <!-- Footer -->
    <jsp:include page="/views/common/footer.jsp"/>

    <!-- Bootstrap 5 JavaScript Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>