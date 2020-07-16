package pl.camp.it.services;

import pl.camp.it.dao.CategoryDAO;
import pl.camp.it.dao.ICategoryDAO;
import pl.camp.it.model.Category;
import java.util.List;

public class CategoryService implements ICategoryService {
    private static ICategoryDAO categoryDAO = new CategoryDAO();

    @Override
    public void saveCategory(String name) {
        Category category = new Category();
        category.setName(name);
        category.setDeleted(false);

        categoryDAO.saveCategoryToDataBase(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryDAO.getCategoryFromDataBase(name);
    }

    @Override
    public List<Category> GetAllCategories() {
        return categoryDAO.getAllCategoriesFromDataBase();
    }

    @Override
    public void deleteCategory(Category category) {
        category.setDeleted(true);
        categoryDAO.deleteCategoryFromDataBase(category);
    }

    @Override
    public boolean categoryExist(String category) {
        return categoryDAO.checkCategoryInDataBase(category);
    }

    @Override
    public boolean categoryExistWithDeleted(String category) {
        return categoryDAO.checkCategoryInDataBaseWithDeleted(category);
    }
}