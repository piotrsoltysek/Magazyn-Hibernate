package pl.camp.it.services;

public interface IUserService {
    void saveUser(String login, String hashedPassword);
    boolean checkUserExist(String login);
    boolean checkLoginAndPassword(String login, String password);

}
