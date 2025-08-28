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
            
            // FIX 3: Sửa lại điều kiện kiểm tra roleid cho đúng với database
            if (u.getRoleid() == 1) { // Admin
                resp.sendRedirect(req.getContextPath() + "/admin/home.jsp"); // Nên trỏ thẳng đến file
            } else if (u.getRoleid() == 0) { // User thường
                resp.sendRedirect(req.getContextPath() + "/home.jsp"); // Nên trỏ thẳng đến file
            } else {
                 // Trường hợp roleid không xác định, quay về trang login
                 resp.sendRedirect(req.getContextPath() + "/login");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}