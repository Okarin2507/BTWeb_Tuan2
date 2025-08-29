<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Danh mục</title>
<style>
body {
	font-family: sans-serif;
	padding: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.action-links a {
	margin-right: 10px;
}

.add-btn {
	display: inline-block;
	padding: 10px 15px;
	background-color: #28a745;
	color: white;
	text-decoration: none;
	border-radius: 4px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
	<h1>Danh sách Danh mục của bạn, ${sessionScope.account.fullName}</h1>
	<a href="${pageContext.request.contextPath}/admin/category/add"
		class="add-btn">Thêm Danh mục mới</a>
	<a href="${pageContext.request.contextPath}/logout"
		style="float: right;">Đăng xuất</a>

	<table>
		<thead>
			<tr>
				<th>STT</th>
				<th>Hình ảnh</th>
				<th>Tên Danh mục</th>
				<th>Hành động</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categories}" var="cat" varStatus="loop">
				<tr>
					<td>${loop.index + 1}</td>
					<td><c:url value="/image?fname=${cat.icons}" var="imgUrl"></c:url>
						<img src="${imgUrl}" alt="Ảnh danh mục" width="100"></td>
					<td>${cat.cate_name}</td>
					<td class="action-links"><c:url
							value="/category/edit?id=${cat.cate_id}" var="editUrl"></c:url> <a
						href="${editUrl}">Sửa</a> <c:url
							value="/category/delete?id=${cat.cate_id}" var="deleteUrl"></c:url>
						<a href="${deleteUrl}"
						onclick="return confirm('Bạn có chắc chắn muốn xóa?')">Xóa</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>