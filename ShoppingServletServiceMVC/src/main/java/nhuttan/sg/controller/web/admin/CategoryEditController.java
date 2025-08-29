package nhuttan.sg.controller.web.admin;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import nhuttan.sg.model.Category;
import nhuttan.sg.service.CategoryService;
import nhuttan.sg.service.impl.CategoryServiceImpl;
import nhuttan.sg.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig
public class CategoryEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        req.getRequestDispatcher("/admin/edit-category.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        Category newCategory = new Category();

        newCategory.setCateId(Integer.parseInt(req.getParameter("id")));
        newCategory.setCateName(req.getParameter("name"));

        Part filePart = req.getPart("icon");
        String fileName = getFileName(filePart);

        if (fileName != null && !fileName.isEmpty()) {

            String originalFileName = fileName;
            int index = originalFileName.lastIndexOf(".");
            String ext = originalFileName.substring(index + 1);
            String uniqueFileName = System.currentTimeMillis() + "." + ext;

            String dir = Constant.DIR + "/category";
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            String filePath = dir + File.separator + uniqueFileName;
            filePart.write(filePath);
            
            newCategory.setIcons("category/" + uniqueFileName);
        }

        cateService.edit(newCategory);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }

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
