package dataManagement;
import order.*;
import users.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;
import java.util.Scanner;
import java.util.Scanner;

public class view {
    private static DataManager dataManager = new DataManager();
    private static Authentication authentication = new Authentication(dataManager.loadCustomers());
    private static Catalog catalog;
    private static Customer currentCustomer;
    private static Admin currentAdmin;

    public static void run() {
        // Load data from files
        dataManager.loadCustomers();
        dataManager.loadItems();
        dataManager.loadCarts();

        // Initialize catalog
        catalog = new Catalog(dataManager.loadItems());

        // Start program loop
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;
        while (!exitProgram) {
            // Check if user is logged in
            boolean isLoggedIn = (currentCustomer != null || currentAdmin != null);

            // Print menu options
            System.out.println("\n--- ONLINE SHOP ---");
            System.out.println("1. Register new customer");
            System.out.println("2. Login");
            System.out.println("3. Browse catalog");
            System.out.println("4. View cart");
            System.out.println("5. Checkout");
            System.out.println("6. Add item");
            System.out.println("7. Close order");
//            if (isLoggedIn) {
//                if (currentAdmin != null) {
//                    System.out.println("6. Add item");
//                    System.out.println("7. Close order");
//                } else {
//                    System.out.println("6. Logout");
//                }
//            } else {
//                System.out.println("6. Exit");
//            }

            // Prompt user for input
            System.out.print("Enter option number: ");
            int option = scanner.nextInt();

            // Handle input
            switch (option) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    loginCustomer();
                    break;
                case 3:
                    browseCatalog();
                    break;
                case 4:
                    viewCart();
                    break;
                case 5:
                    checkout();
                    break;
                case 6:
                    addItem();
                    break;
                case 7:
                    closeOrder();
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }


    }

    private static void registerCustomer() {
        // Prompt user for customer details
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        // Create new customer and add to list
        Customer customer = new Customer(dataManager.getNextCustomerId(), firstName, lastName, email, password, phoneNumber, address);
        if(authentication.register(customer)){
            System.out.println("Customer registered successfully.");}
        else{
            System.out.println("Customer already exists.");
        }

    }

    private static void loginCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if(authentication.login(email,password)){
            System.out.println("Customer account logged in successfully.");

        }else{
            System.out.println("Failed to log in customer account.");
        }
    }
    public static void browseCatalog(){
        catalog.displayCatalog();
    }
    public static void viewCart(){
        currentCustomer.getCart().toString();
    }
    public static void checkout(){
        //choose payment method
        double amount = currentCustomer.getCart().getTotal();
        Scanner scanner = new Scanner(System.in);
        System.out.println("The amount to be paid is: " + amount + " L.E.");
        System.out.println("Choose payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.println("3. Debit Card");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                //get cash prompt
                System.out.println("Enter amount of cash: ");
                double cash = scanner.nextDouble();
                Payment p = new CashPayment(amount, cash);
                currentCustomer.getCart().checkout(p);
                break;
            case 2:
                //get visa prompt
                System.out.println("Enter visa number: ");
                String visaNumber = scanner.nextLine();
                System.out.println("Enter expiry date: ");
                String expiryDate = scanner.nextLine();
                System.out.println("Enter cvv: ");
                String cvv = scanner.nextLine();
                Payment p1 = new VisaPayment(amount, visaNumber, expiryDate, cvv);
                currentCustomer.getCart().checkout(p1);
                break;
            case 3:
                //get eWallet prompt
                System.out.println("Enter eWallet number: ");
                String eWalletNumber = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                Payment p2 = new EWalletPayment(amount, eWalletNumber, password);
                currentCustomer.getCart().checkout(p2);
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }

    }
    public static void addItem(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter item quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter item discount: ");
        double discount = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter item brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter item category: ");
        String category = scanner.nextLine();
        Item item = new Item(dataManager.getNextItemId(), name, description,price,quantity, discount, brand, category);
        catalog.addItemToCatalog(item);
    }
    public static void closeOrder(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id: ");
        int id = scanner.nextInt();
        Order order = currentCustomer.getOrders().get(id);
        order.closeOrder();
    }
}

