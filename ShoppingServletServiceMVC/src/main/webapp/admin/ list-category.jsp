<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Quản lý Danh mục</title></head>
<body>
    <h2>Danh sách Danh mục</h2>
    <a href="${pageContext.request.contextPath}/admin/category/add">Thêm mới</a>
    <table border="1">
        <tr><th>STT</th><th>Hình ảnh</th><th>Tên danh mục</th><th>Hành động</th></tr>
        <c:forEach items="${cateList}" var="cate" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>
                    <c:url value="/image?fname=${cate.icon}" var="imgUrl" />
                    <img src="${imgUrl}" width="100" alt="Icon" />
                </td>
                <td>${cate.cateName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.cateId}">Sửa</a> |
                    <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.cateId}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>