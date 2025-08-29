<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm Danh mục</title>
<style>
/
body {
	font-family: sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: #f0f2f5;
}

.login-container {
	background: white;
	padding: 2rem;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	width: 300px;
}

h2 {
	text-align: center;
}

.alert-danger {
	color: red;
	background-color: #fdd;
	border: 1px solid red;
	padding: 10px;
	border-radius: 4px;
	margin-bottom: 1rem;
}

.form-group {
	margin-bottom: 1rem;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
}

.form-group input {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="form-container">
		<h2>Thêm Danh mục mới</h2>
		<a href="${pageContext.request.contextPath}/admin/category/add">Thêm mới</a>
			method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="cate_name">Tên Danh mục:</label> <input type="text"
					id="cate_name" name="cate_name" required>
			</div>
			<div class="form-group">
				<label for="icons">Ảnh đại diện:</label> <input type="file"
					id="icons" name="icons">
			</div>
			<button type="submit">Thêm</button>
			<a href="${pageContext.request.contextPath}/category/list">Hủy</a>
		</form>
	</div>
</body>
</html>