<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Trang Chủ</title></head>
<body>
    <h1>Chào mừng, ${sessionScope.account.fullName}!</h1>
    <p>Đây là trang chủ dành cho người dùng.</p>
    <a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
</body>
</html>