package nhuttan.sg.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nhuttan.sg.service.UserService;
import nhuttan.sg.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        userService.requestPasswordReset(email);

        req.setAttribute("message", "Nếu email của bạn tồn tại trong hệ thống, một link đặt lại mật khẩu đã được gửi đến.");

        req.getRequestDispatcher("/views/forgot-password.jsp").forward(req, resp);
    }
}