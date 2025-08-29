package nhuttan.sg.controller.web.admin;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import nhuttan.sg.model.Category;
import nhuttan.sg.model.User;
import nhuttan.sg.service.CategoryService;
import nhuttan.sg.service.impl.CategoryServiceImpl;
import nhuttan.sg.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig // Annotation để kích hoạt tính năng upload file
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        Category category = new Category();
        
        // Lấy dữ liệu từ form text
        String cateName = req.getParameter("name");
        category.setCateName(cateName);
        
        // Lấy dữ liệu từ session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        category.setUserId(user.getId());

        // Xử lý file upload
        Part filePart = req.getPart("icon"); // Lấy file từ form
        String fileName = getFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {
            // Tạo tên file duy nhất
            String originalFileName = fileName;
            int index = originalFileName.lastIndexOf(".");
            String ext = originalFileName.substring(index + 1);
            String uniqueFileName = System.currentTimeMillis() + "." + ext;
            
            // Đường dẫn lưu file
            String dir = Constant.DIR + "/category";
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs(); // Tạo thư mục nếu chưa có
            }

            String filePath = dir + File.separator + uniqueFileName;
            filePart.write(filePath); // Lưu file
            
            category.setIcons("category/" + uniqueFileName);
        }

        cateService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
    
    // Hàm tiện ích để lấy tên file từ Part
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}