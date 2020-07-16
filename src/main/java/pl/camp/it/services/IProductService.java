package pl.camp.it.services;

import pl.camp.it.model.Category;
import pl.camp.it.model.Product;
import java.util.List;

public interface IProductService {
    void saveProduct(String name, int amount, double price, long barcode, Category category);
    void saveProductWithNewCategory(String name, int amount, double price, long barcode, String category);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(Category category);
    void updateProductCategoryToBrakKategorii(List<Product> products, Category brakKategorii);
}
