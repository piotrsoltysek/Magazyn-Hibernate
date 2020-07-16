package pl.camp.it.gui;

import org.apache.commons.codec.digest.DigestUtils;
import pl.camp.it.App;
import pl.camp.it.services.IUserService;
import pl.camp.it.services.UserService;

import java.util.Scanner;

public class LoginGUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static IUserService userService = new UserService();

    public static void showLoginMainMenu() {
        System.out.println("1. Zaloguj");
        System.out.println("2. Zarejestruj");
        System.out.println("3. Exit");
        System.out.print("Podaj cyfrę: ");


        switch (scanner.nextLine()) {
            case "1":
                showLoginScreen();
                showLoginMainMenu();
                break;
            case "2":
                showRegisterScreen();
                showLoginMainMenu();
                break;
            case "3":
                App.sessionFactory.close();
                System.exit(0);
                break;
            default:
                System.out.println("Nieprawidłowy wybór");
                showLoginMainMenu();
                break;
        }
    }

    private static void showLoginScreen() {
        System.out.println("Wpisz login:");
        String login = scanner.nextLine();
        System.out.println("Wpisz hasło:");
        String pass = scanner.nextLine();

        if (userService.checkLoginAndPassword(login, pass)) {
            GUI.showMainMenu();
        }
    }

    private static void showRegisterScreen() {
        System.out.println("Podaj login:");
        String login = scanner.nextLine();

        if (userService.checkUserExist(login)) {
            System.out.println("Login zajęty, spróbuj ponownie");
            showRegisterScreen();
            return;
        }

        System.out.println("Podaj hasło:");
        String pass = scanner.nextLine();

        String hashedPassword = DigestUtils.md5Hex(pass);
        userService.saveUser(login, hashedPassword);
        System.out.println("Rejestracja zakończona");
    }
}