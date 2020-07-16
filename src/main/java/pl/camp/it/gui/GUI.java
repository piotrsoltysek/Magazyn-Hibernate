package pl.camp.it.gui;

import pl.camp.it.App;
import pl.camp.it.dao.CategoryDAO;
import pl.camp.it.model.Category;
import pl.camp.it.services.CategoryService;
import pl.camp.it.services.ProductService;

import java.util.Scanner;

public class GUI {
    private static Scanner scanner = new Scanner(System.in);
    private static CategoryService categoryService = new CategoryService();
    private static ProductService productService = new ProductService();

    public static void showMainMenu() {
        System.out.println("------------------------");
        System.out.println("1. Dostępne produkty");
        System.out.println("2. Dostępne produkty z danej kategorii");
        System.out.println("3. Dostępne kategorie");
        System.out.println("4. Dodaj produkt");
        System.out.println("5. Dodaj kategorię");
        System.out.println("6. Usuń kategorię");
        System.out.println("7. Exit");
        System.out.print("Podaj cyfrę: ");

        String choose = scanner.nextLine();

        switch (choose) {
            case "1":
                System.out.println(productService.getAllProducts());
                showMainMenu();
                break;
            case "2":
                showProductsFromCategoryScreen();
                showMainMenu();
                break;
            case "3":
                System.out.println(categoryService.GetAllCategories());
                showMainMenu();
                break;
            case "4":
                showAddProductScreen();
                showMainMenu();
                break;
            case "5":
                showAddCategoryScreen();
                showMainMenu();
                break;
            case "6":
                showDeleteProductScreen();
                showMainMenu();
                break;
            case "7":
                App.sessionFactory.close();
                System.exit(0);
            default:
                System.out.println("Nieprawidłowy wybór !!");
                showMainMenu();
                break;
        }
    }

    public static void showProductsFromCategoryScreen() {
        System.out.println("Wpisz nazwę kategorii:");
        String category = scanner.nextLine();
        if (categoryService.categoryExist(category)) {
            Category category2 = categoryService.getCategoryByName(category);
            System.out.println(productService.getProductsByCategory(category2));
        } else {
            System.out.println("Nie ma takiej kategorii");
        }
    }


    public static void showAddProductScreen() {

        System.out.println("Wpisz nazwę produktu:");
        String name = scanner.nextLine();
        System.out.println("Wpisz ilość:");
        int amount = Integer.parseInt(scanner.nextLine());
        System.out.println("Wpisz cenę:");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Wpisz kod kreskowy:");
        long barcode = Long.parseLong(scanner.nextLine());
        System.out.println("Wpisz kategorię:");
        String category = scanner.nextLine();
        if (categoryService.categoryExist(category)) {
            Category category2 = categoryService.getCategoryByName(category);
            productService.saveProduct(name, amount, price, barcode, category2);
            System.out.println("Produkt dodany do istniejącej kategorii");
        } else if (categoryService.categoryExistWithDeleted(category)) {
            System.out.println("Taka kategoria była już wcześniej usunięta, nie można dodać ponownie");
        } else {
            productService.saveProductWithNewCategory(name, amount, price, barcode, category);
            System.out.println("Produkt dodany, utworzono nową kategorię");
            }
        }


    public static void showAddCategoryScreen() {
        System.out.println("Podaj nazwę kategorii:");
        String name = scanner.nextLine();
        if (categoryService.categoryExist(name)) {
            System.out.println("Podana kategoria już istnieje");
        } else if (categoryService.categoryExistWithDeleted(name)) {
            System.out.println("Podana kategoria była już wcześniej dodana i została usunięta. Nie można dodać ponownie");
        } else {
            categoryService.saveCategory(name);
            System.out.println("Dodano kategorię");
        }
    }


    public static void showDeleteProductScreen() {
        System.out.println("Wpisz nazwę kategorii:");
        String category = scanner.nextLine();
        if (category.equals("Brak kategorii")) {
            System.out.println("Nie można usunąć tej kategorii");
            return;
        }
        if (categoryService.categoryExist(category)) {
            Category category2 = categoryService.getCategoryByName(category);
            Category brakKategorii = categoryService.getCategoryByName("Brak kategorii");
            productService.updateProductCategoryToBrakKategorii(productService.getProductsByCategory(category2), brakKategorii);
            categoryService.deleteCategory(category2);
            System.out.println("Kategoria usunięta, produkty przerzucone do: Brak kategorii");
        } else {
            System.out.println("Nie ma takiej kategorii");
        }
    }
}
