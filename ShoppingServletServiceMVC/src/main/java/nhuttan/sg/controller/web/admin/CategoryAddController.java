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
@MultipartConfig
public class CategoryAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Chuyển đến trang thêm mới
        req.getRequestDispatcher("/admin/add-category.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        // --- BẮT ĐẦU DEBUG ---
        System.out.println(">>> ĐÃ VÀO CategoryAddController.doPost()!");
        
        Category category = new Category();
        
        try {
            // Lấy dữ liệu từ form text
            String cateName = req.getParameter("name");
            category.setCateName(cateName);
            System.out.println(">>> Tên danh mục nhận được: " + cateName);
            
            // Lấy dữ liệu từ session
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("account");
            category.setUserId(user.getId());
            System.out.println(">>> ID của User tạo danh mục: " + user.getId());

            // Xử lý file upload
            Part filePart = req.getPart("icon");
            String fileName = getFileName(filePart);
            System.out.println(">>> Tên file gốc: " + fileName);

            if (fileName != null && !fileName.isEmpty()) {
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                
                String dirPath = Constant.DIR + File.separator + "category";
                File dirFile = new File(dirPath);
                if (!dirFile.exists()) {
                    System.out.println(">>> Thư mục " + dirPath + " không tồn tại, đang tạo...");
                    dirFile.mkdirs();
                }

                String filePath = dirPath + File.separator + uniqueFileName;
                System.out.println(">>> Sẽ lưu file vào: " + filePath);
                filePart.write(filePath); // Lưu file
                
                category.setIcons("category/" + uniqueFileName);
            } else {
                System.out.println(">>> Không có file nào được upload.");
            }

            System.out.println(">>> Chuẩn bị gọi cateService.insert()");
            cateService.insert(category);
            System.out.println(">>> Gọi cateService.insert() thành công!");
            
            System.out.println(">>> Chuyển hướng về trang danh sách...");
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (Exception e) {
            System.err.println("!!! LỖI TRONG QUÁ TRÌNH THÊM CATEGORY !!!");
            e.printStackTrace();
        }
        // --- KẾT THÚC DEBUG ---
    }
    
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                if (!fileName.isEmpty()) {
                    return fileName;
                }
            }
        }
        return null;
    }
}