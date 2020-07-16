package pl.camp.it;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.camp.it.gui.LoginGUI;

public class App {
    public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        LoginGUI.showLoginMainMenu();
    }
}