<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<!-- Re-use login styling -->
<style>
    body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f0f2f5; }
    .login-container { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); width: 350px; }
    h2, h3 { text-align: center; }
    .alert-danger { color: red; background-color: #f8d7da; border: 1px solid #f5c6cb; padding: .75rem; border-radius: .25rem; margin-bottom: 1rem; }
    .form-control { width: 100%; padding: 10px; margin-bottom: 1rem; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;}
    .btn { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
    .btn:hover { background-color: #218838; }
</style>
</head>
<body>
    <div class="login-container">
        <h2>Tạo tài khoản mới</h2>

        <c:if test="${alert != null}">
            <h3 class="alert-danger">${alert}</h3>
        </c:if>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div>
                <input type="text" placeholder="Tên đầy đủ" name="fullname" class="form-control" required>
            </div>
            <div>
                <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
            </div>
            <div>
                <input type="email" placeholder="Nhập Email" name="email" class="form-control" required>
            </div>
             <div>
                <input type="text" placeholder="Số điện thoại" name="phone" class="form-control" required>
            </div>
            <div>
                 <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
            </div>
            <button type="submit" class="btn">Tạo tài khoản</button>
        </form>
         <p style="text-align:center; margin-top: 1rem;">
            Nếu bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </p>
    </div>
</body>
</html>