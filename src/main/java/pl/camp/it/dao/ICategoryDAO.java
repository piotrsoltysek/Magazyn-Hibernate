package pl.camp.it.dao;

import pl.camp.it.model.Category;
import java.util.List;

public interface ICategoryDAO {
    void saveCategoryToDataBase(Category category);
    boolean checkCategoryInDataBase(String category);
    boolean checkCategoryInDataBaseWithDeleted(String category);
    void deleteCategoryFromDataBase(Category category);
    Category getCategoryFromDataBase(int id);
    Category getCategoryFromDataBase(String name);
    List<Category> getAllCategoriesFromDataBase();
    List<Category> getAllCategoriesFromDataBaseWithDeleted();
    }
