<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/home">
            <i class="bi bi-bag-check-fill text-warning"></i>
            <span>QUẢN LÝ QUẦN ÁO</span>
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarMain">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarMain">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="${pageContext.request.contextPath}/home">
                        <i class="bi bi-house-door me-1"></i>Trang Chủ
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-info fw-semibold" href="${pageContext.request.contextPath}/product">
                        <i class="bi bi-grid me-1"></i>Sản Phẩm (Phân trang)
                    </a>
                </li>
                <c:if test="${not empty categories}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">
                            <i class="bi bi-list-task me-1"></i>Danh Mục
                        </a>
                        <ul class="dropdown-menu">
                            <c:forEach items="${categories}" var="c">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/home?cateId=${c.id}">
                                        ${c.name}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:if>
            </ul>

            <form class="d-flex me-3" action="${pageContext.request.contextPath}/home" method="get">
                <div class="input-group">
                    <input class="form-control form-control-sm" type="search" name="keyword" value="${param.keyword}" placeholder="Tìm quần áo...">
                    <button class="btn btn-warning btn-sm" type="submit">
                        <i class="bi bi-search"></i>
                    </button>
                </div>
            </form>

            <ul class="navbar-nav">
                <c:choose>
                    <c:when test="${not empty sessionScope.account}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle d-flex align-items-center gap-2" href="#" role="button" data-bs-toggle="dropdown">
                                <c:choose>
                                    <c:when test="${not empty sessionScope.account.avatar}">
                                        <img src="${pageContext.request.contextPath}/${sessionScope.account.avatar}" class="avatar-nav" alt="Avatar">
                                    </c:when>
                                    <c:otherwise>
                                        <i class="bi bi-person-circle fs-5"></i>
                                    </c:otherwise>
                                </c:choose>
                                <span>${sessionScope.account.fullName}</span>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end shadow">
                                <li>
                                    <a class="dropdown-item d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/profile">
                                        <i class="bi bi-person-badge text-primary"></i> Thông tin cá nhân
                                    </a>
                                </li>
                                <c:if test="${sessionScope.account.roleid == 1}">
                                    <li>
                                        <a class="dropdown-item d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/admin/home">
                                            <i class="bi bi-speedometer2 text-danger"></i> Trang Admin
                                        </a>
                                    </li>
                                </c:if>
                                <c:if test="${sessionScope.account.roleid == 2}">
                                    <li>
                                        <a class="dropdown-item d-flex align-items-center gap-2" href="${pageContext.request.contextPath}/manager/home">
                                            <i class="bi bi-gear-wide-connected text-success"></i> Trang Quản lý
                                        </a>
                                    </li>
                                </c:if>
                                <li><hr class="dropdown-divider"></li>
                                <li>
                                    <a class="dropdown-item d-flex align-items-center gap-2 text-danger" href="${pageContext.request.contextPath}/logout">
                                        <i class="bi bi-box-arrow-right"></i> Đăng xuất
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="btn btn-outline-light btn-sm me-2" href="${pageContext.request.contextPath}/login">
                                <i class="bi bi-box-arrow-in-right me-1"></i>Đăng nhập
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/register">
                                <i class="bi bi-person-plus me-1"></i>Đăng ký
                            </a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>