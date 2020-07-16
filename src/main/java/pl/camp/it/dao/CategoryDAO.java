package pl.camp.it.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.camp.it.App;
import pl.camp.it.model.Category;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    @Override
    public void saveCategoryToDataBase(Category category) {
        Session session = App.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(category);
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
    public boolean checkCategoryInDataBase(String category) {
        for (Category category2 : getAllCategoriesFromDataBase()) {
            if (category2.getName().equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkCategoryInDataBaseWithDeleted(String category) {
        for (Category category2 : getAllCategoriesFromDataBaseWithDeleted()) {
            if (category2.getName().equals(category)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteCategoryFromDataBase(Category category) {
        Session session = App.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(category);
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
    public Category getCategoryFromDataBase(int id) {
        Session session = App.sessionFactory.openSession();
        Query<Category> query = session.createQuery("FROM pl.camp.it.model.Category WHERE id = :id");
        query.setParameter("id", id);
        Category tempCategory = query.getSingleResult();
        session.close();
        return tempCategory;
    }

    @Override
    public Category getCategoryFromDataBase(String name) {
        Session session = App.sessionFactory.openSession();
        Query<Category> query = session.createQuery("FROM pl.camp.it.model.Category WHERE name = :name");
        query.setParameter("name", name);
        Category tempCategory = query.getSingleResult();
        session.close();
        return tempCategory;
    }


    @Override
    public List<Category> getAllCategoriesFromDataBase() {
        Session session = App.sessionFactory.openSession();
        Query<Category> query = session.createQuery("FROM pl.camp.it.model.Category WHERE deleted = false");
        List<Category> categories = query.getResultList();
        session.close();
        return categories;
    }

    @Override
    public List<Category> getAllCategoriesFromDataBaseWithDeleted() {
        Session session = App.sessionFactory.openSession();
        Query<Category> query = session.createQuery("FROM pl.camp.it.model.Category");
        List<Category> categories = query.getResultList();
        session.close();
        return categories;
    }
}
