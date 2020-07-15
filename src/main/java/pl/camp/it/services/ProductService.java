package pl.camp.it.services;

import pl.camp.it.dao.ProductDAO;
import pl.camp.it.model.Category;
import pl.camp.it.model.Product;

import java.util.List;

public class ProductService {
    private static ProductDAO productDAO = new ProductDAO();


    public void saveProduct(String name, int amount, double price, long barcode, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setAmount(amount);
        product.setPrice(price);
        product.setBarcode(barcode);
        product.setCategory(category);

        productDAO.saveProductToDataBase(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProductsFromDataBase();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productDAO.getProductsByCategoryFromDataBase(category.getId());
    }

    public void updateProductCategoryToBrakKategorii(List<Product> products, Category brakKategorii) {

        for (Product product : products) {
            product.setCategory(brakKategorii);
            productDAO.saveProductToDataBase(product);
        }
    }
}