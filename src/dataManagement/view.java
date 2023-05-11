package dataManagement;
import order.*;
import users.*;

import java.util.Scanner;


public class view {
    private static DataManager dataManager = new DataManager();
    private static Authentication authentication = new Authentication(dataManager.loadCustomers());
    private static Catalog catalog=new Catalog(dataManager.loadItems());
    private static Customer customer;
    private static Admin admin;

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our store!");
        while (true) {
            System.out.println("\nWhat would you like to do?");

            if (customer == null && admin == null) {
                System.out.println("1. Register a new account");
                System.out.println("2. Login");
                System.out.println("3. Browse items");
                System.out.println("4. Exit");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
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
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } else if (customer != null) {
                System.out.println("1. Browse items");
                System.out.println("2. Add item to cart");
                System.out.println("3. View cart");
                System.out.println("4. Place order");
                System.out.println("5. Logout");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        browseCatalog();
                        break;
                    case 2:
                        addItem();
                        break;
                    case 3:
                        viewCart();
                        break;
                    case 4:
                        checkout();
                        break;
                    case 5:
                        customer = null;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            } else if (customer != null) {
                System.out.println("1. Browse items");
                System.out.println("2. Add item to cart");
                System.out.println("3. View cart");
                System.out.println("4. Place order");
                System.out.println("5. Logout");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        browseCatalog();
                        break;
                    case 2:
                        addItem();
                        break;
                    case 3:
                        viewCart();
                        break;
                    case 4:
                        checkout();
                        break;
                    case 5:
                        customer = null;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }

            }
            else if (admin != null) {
                System.out.println("1. Add item");
                System.out.println("2. Close order");
                System.out.println("3. Logout");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addItem();
                        break;
                    case 2:
                        closeOrder();
                        break;
                    case 3:
                        admin = null;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
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
            System.out.println("Customer account created successfully.");}
        else{
            System.out.println("Failed to create customer account.");
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
        customer.getCart().toString();
    }
    public static void checkout(){
        //choose payment method
        double amount = customer.getCart().getTotal();
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
                customer.getCart().checkout(p);
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
                customer.getCart().checkout(p1);
                break;
            case 3:
                //get eWallet prompt
                System.out.println("Enter eWallet number: ");
                String eWalletNumber = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                Payment p2 = new EWalletPayment(amount, eWalletNumber, password);
                customer.getCart().checkout(p2);
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
        Order order = customer.getOrders().get(id);
        order.closeOrder();
    }
}
