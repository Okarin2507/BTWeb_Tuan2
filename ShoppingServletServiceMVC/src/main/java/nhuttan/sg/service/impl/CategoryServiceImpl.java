package nhuttan.sg.service.impl;

import java.io.File;
import java.util.List;
import nhuttan.sg.dao.CategoryDao;
import nhuttan.sg.dao.impl.CategoryDaoImpl;
import nhuttan.sg.model.Category;
import nhuttan.sg.service.CategoryService;
import nhuttan.sg.util.Constant;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public void edit(Category newCategory) {
        Category oldCategory = categoryDao.get(newCategory.getCateId());
        
        oldCategory.setCateName(newCategory.getCateName());
        
        if (newCategory.getIcons() != null) {
            // Xóa ảnh cũ
            String fileName = oldCategory.getIcons();
            if (fileName != null) {
                 File file = new File(Constant.DIR + "/" + fileName);
                if (file.exists()) {
                    file.delete();
                }
            }
            oldCategory.setIcons(newCategory.getIcons());
        }
        
        categoryDao.edit(oldCategory);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    @Override
    public Category get(int id) {
        return categoryDao.get(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
    
    @Override
    public List<Category> getAllByUserId(int userId) {
        return categoryDao.getAllByUserId(userId);
    }
}