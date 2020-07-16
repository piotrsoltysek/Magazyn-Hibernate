package pl.camp.it.dao;

import pl.camp.it.model.Product;
import java.util.List;

public interface IProductDAO {
    void saveProductToDataBase(Product product);
    List<Product> getAllProductsFromDataBase();
    List<Product> getProductsByCategoryFromDataBase(int id);
}
