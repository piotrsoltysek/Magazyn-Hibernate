package pl.camp.it.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.camp.it.App;
import pl.camp.it.model.Product;
import java.util.List;

public class ProductDAO implements IProductDAO {

    @Override
    public void saveProductToDataBase(Product product) {
        Session session = App.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(product);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Product> getAllProductsFromDataBase() {
        Session session = App.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.camp.it.model.Product");
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }

    @Override
    public List<Product> getProductsByCategoryFromDataBase(int id) {
        Session session = App.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.camp.it.model.Product WHERE category_id = :id");
        query.setParameter("id", id);
        List<Product> products = query.getResultList();
        session.close();
        return products;
    }
}
