package pl.camp.it.services;

import pl.camp.it.dao.IProductDAO;
import pl.camp.it.dao.ProductDAO;
import pl.camp.it.model.Category;
import pl.camp.it.model.Product;
import java.util.List;

public class ProductService implements IProductService {
    private static IProductDAO productDAO = new ProductDAO();

    @Override
    public void saveProduct(String name, int amount, double price, long barcode, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setAmount(amount);
        product.setPrice(price);
        product.setBarcode(barcode);
        product.setCategory(category);

        productDAO.saveProductToDataBase(product);
    }

    @Override
    public void saveProductWithNewCategory(String name, int amount, double price, long barcode, String category) {
        Product product = new Product();
        product.setName(name);
        product.setAmount(amount);
        product.setPrice(price);
        product.setBarcode(barcode);

        Category category1 = new Category();
        category1.setName(category);
        category1.setDeleted(false);
        product.setCategory(category1);

        productDAO.saveProductToDataBase(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProductsFromDataBase();
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productDAO.getProductsByCategoryFromDataBase(category.getId());
    }

    @Override
    public void updateProductCategoryToBrakKategorii(List<Product> products, Category brakKategorii) {

        for (Product product : products) {
            product.setCategory(brakKategorii);
            productDAO.saveProductToDataBase(product);
        }
    }
}