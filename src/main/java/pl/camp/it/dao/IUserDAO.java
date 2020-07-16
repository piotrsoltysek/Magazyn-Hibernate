package pl.camp.it.dao;

import pl.camp.it.model.User;
import java.util.List;

public interface IUserDAO {
    void saveUserToDataBase(User user);
    List<User> getAllUsers();
}