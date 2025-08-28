<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div style="background-color: #333; color: white; padding: 10px; text-align: right;">
    <c:choose>
        <c:when test="${sessionScope.account == null}">
            <a href="${pageContext.request.contextPath}/login" style="color: white; text-decoration: none;">Đăng nhập</a> |
            <a href="${pageContext.request.contextPath}/register" style="color: white; text-decoration: none;">Đăng ký</a>
        </c:when>
        <c:otherwise>
            <span>Chào, ${sessionScope.account.fullName}</span> |
            <a href="${pageContext.request.contextPath}/logout" style="color: white; text-decoration: none;">Đăng Xuất</a>
        </c:otherwise>
    </c:choose>
</div>