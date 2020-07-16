package pl.camp.it.services;

import pl.camp.it.dao.CategoryDAO;
import pl.camp.it.model.Category;

import java.util.List;

public class CategoryService {
    private static CategoryDAO categoryDAO = new CategoryDAO();

    public void saveCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category.setDeleted(false);

        categoryDAO.saveCategoryToDataBase(category);
    }

    public Category getCategoryByName(String name) {
        return categoryDAO.getCategoryFromDataBase(name);
    }

    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryFromDataBase(id);
    }

    public List<Category> GetAllCategories() {
        return categoryDAO.getAllCategoriesFromDataBase();
    }

    public void deleteCategory(Category category) {
        category.setDeleted(true);
        categoryDAO.deleteCategoryFromDataBase(category);
    }

    public boolean categoryExist(String category) {
        return categoryDAO.checkCategoryInDataBase(category);
    }

    public boolean categoryExistWithDeleted(String category) {
        return categoryDAO.checkCategoryInDataBaseWithDeleted(category);
    }
}