package nhuttan.sg.controller.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import nhuttan.sg.model.User;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");
            if (u.getRoleid() == 1) { // Admin
                resp.sendRedirect(req.getContextPath() + "/admin/home");
            } else if (u.getRoleid() == 2) { // User
                resp.sendRedirect(req.getContextPath() + "/home");
            } else { // Giả sử có thêm vai trò manager
                 resp.sendRedirect(req.getContextPath() + "/manager/home");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}