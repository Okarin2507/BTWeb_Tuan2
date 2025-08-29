package nhuttan.sg.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nhuttan.sg.model.User;
import nhuttan.sg.service.UserService;
import nhuttan.sg.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        User user = userService.validatePasswordResetToken(token);

        if (user != null) {
            req.setAttribute("token", token);
            req.getRequestDispatcher("/views/reset-password.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        String newPassword = req.getParameter("password");
        
        User user = userService.validatePasswordResetToken(token);
        if (user != null) {
            userService.resetPassword(token, newPassword);
            req.setAttribute("alert", "Mật khẩu của bạn đã được thay đổi thành công!");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}