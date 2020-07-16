package pl.camp.it.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.camp.it.App;
import pl.camp.it.model.User;
import java.util.List;

public class UserDAO implements IUserDAO {

    @Override
    public void saveUserToDataBase(User user) {
        Session session = App.sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
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
    public List<User> getAllUsers() {
        Session session = App.sessionFactory.openSession();
        Query<User> query = session.createQuery("FROM pl.camp.it.model.User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }
}
