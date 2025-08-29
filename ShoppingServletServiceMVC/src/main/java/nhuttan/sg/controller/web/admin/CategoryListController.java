package nhuttan.sg.controller.web.admin;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nhuttan.sg.model.Category;
import nhuttan.sg.model.User;
import nhuttan.sg.service.CategoryService;
import nhuttan.sg.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // --- BẮT ĐẦU DEBUG ---
        System.out.println(">>> ĐÃ VÀO CategoryListController.doGet()!");
        
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        
        if (user == null) {
            System.err.println("!!! LỖI: User trong session là NULL. Chuyển hướng về trang đăng nhập.");
            resp.sendRedirect(req.getContextPath() + "/login");
            return; // Dừng thực thi ngay lập tức
        }
        
        System.out.println(">>> User ID lấy từ session: " + user.getId());
        

        List<Category> cateList = cateService.getAllByUserId(user.getId());
        
        if (cateList != null) {
            System.out.println(">>> Tìm thấy " + cateList.size() + " danh mục.");
        } else {
            System.err.println("!!! LỖI: cateList là NULL.");
        }
        
        req.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/list-category.jsp");
        
        System.out.println(">>> Chuẩn bị chuyển hướng đến list-category.jsp");
        // --- KẾT THÚC DEBUG ---
        
        dispatcher.forward(req, resp);
    }
}