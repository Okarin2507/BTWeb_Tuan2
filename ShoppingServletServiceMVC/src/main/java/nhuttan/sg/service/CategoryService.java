package nhuttan.sg.service;

import java.util.List;
import nhuttan.sg.model.Category;

public interface CategoryService {
    void insert(Category category);
    void edit(Category category);
    void delete(int id);
    Category get(int id);
    List<Category> getAll();
    List<Category> getAllByUserId(int userId);
}