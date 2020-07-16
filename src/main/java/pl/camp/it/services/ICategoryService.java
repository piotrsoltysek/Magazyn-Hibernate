package pl.camp.it.services;

import pl.camp.it.model.Category;
import java.util.List;

public interface ICategoryService {
    void saveCategory(String name);
    Category getCategoryByName(String name);
    List<Category> GetAllCategories();
    void deleteCategory(Category category);
    boolean categoryExist(String category);
    boolean categoryExistWithDeleted(String category);
}