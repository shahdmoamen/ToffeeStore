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
//
//    private static Scanner scanner = new Scanner(System.in);
//    private static DataManager dataManager = new DataManager();
//    private static Catalog catalog = new Catalog(dataManager.loadItems());
//    private static ArrayList<Cart> carts = new Cart(dataManager.loadCarts());
//
//
//    public static void main(String[] args) {
//        boolean exitProgram = false;
//        while (!exitProgram) {
//            // Display menu and ask user to choose mode
//            System.out.println("Select mode:");
//            System.out.println("1. Customer");
//            System.out.println("2. Admin");
//            System.out.println("3. Exit program");
//            int modeChoice = scanner.nextInt();
//            scanner.nextLine(); // consume newline character
//
//            switch (modeChoice) {
//                case 1:
//                    runCustomerMode();
//                    break;
//                case 2:
//                    runAdminMode();
//                    break;
//                case 3:
//                    exitProgram = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please select again.");
//            }
//        }
//        System.out.println("Program exited. Goodbye!");
//    }
//
//    private static void runCustomerMode() {
//        boolean loggedIn = false;
//        String username = "";
//        while (!loggedIn) {
//            // Display menu and ask user to choose action
//            System.out.println("Select action:");
//            System.out.println("1. Register new customer account");
//            System.out.println("2. Login with existing customer account");
//            System.out.println("3. Return to mode selection");
//            int actionChoice = scanner.nextInt();
//            scanner.nextLine(); // consume newline character
//
//            switch (actionChoice) {
//                case 1:
//                    if (Authentication.()) {
//                        System.out.println("Customer account registered successfully.");
//                    } else {
//                        System.out.println("Failed to register customer account.");
//                    }
//                    break;
//                case 2:
//                    username = authentication.loginCustomer();
//                    if (username != null) {
//                        System.out.println("Customer account logged in successfully.");
//                        loggedIn = true;
//                    } else {
//                        System.out.println("Failed to log in customer account.");
//                    }
//                    break;
//                case 3:
//                    return; // exit customer mode and return to mode selection
//                default:
//                    System.out.println("Invalid choice. Please select again.");
//            }
//        }
//
//        // User is now logged in as a customer
//        boolean exitCustomerMode = false;
//        while (!exitCustomerMode) {
//            // Display menu and ask user to choose action
//            System.out.println("Select action:");
//            System.out.println("1. View catalog of items");
//            System.out.println("2. Add item to cart");
//            System.out.println("3. View shopping cart");
//            System.out.println("4. Place order");
//            System.out.println("5. Logout");
//            int actionChoice = scanner.nextInt();
//            scanner.nextLine(); // consume newline character
//
//            switch (actionChoice) {
//                case 1:
//                    catalog.viewCatalog();
//                    break;
//                case 2:
//                    shoppingCart.addItemToCart(username);
//                    break;
//                case 3:
//                    shoppingCart.viewCart(username);
//                    break;
//                case 4:
//                    if (orderManager.placeOrder(username)) {
//                        System.out.println("Order placed successfully.");
//                    } else {
//                        System.out.println("Failed to place order.");
//                    }
//                    break;
//                case 5:
//                    exitCustomerMode = true;
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please select again.");
//
//            }}}}
//
//public class View {
//    private Authentication auth;
//    private Catalog catalog;
//    private Cart cart;
//
//    public View() {
//        auth = new Authentication();
//        catalog = new Catalog();
//        cart = new Cart();
//    }
//
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        boolean running = true;
//
//        while (running) {
//            System.out.println("Choose mode: 1-Customer\n2-Admin");
//            String mode = scanner.nextLine();
//
//            switch (mode) {
//                case "1":
//                    boolean loggedIn = false;
//                    Customer customer = null;
//
//                    while (!loggedIn) {
//                        System.out.println("Choose action: 1-Register\n2-Login\n3-view catalog\n4-Exit");
//                        String action = scanner.nextLine();
//
//                        switch (action) {
//                            case "1":
//                                System.out.println("Enter first name:");
//                                String firstName = scanner.nextLine();
//                                System.out.println("Enter last name:");
//                                String lastName = scanner.nextLine();
//                                System.out.println("Enter email:");
//                                String email = scanner.nextLine();
//                                System.out.println("Enter password:");
//                                String password = scanner.nextLine();
//                                System.out.println("Enter phone number:");
//                                String phoneNumber = scanner.nextLine();
//                                SSystem.out.print("Enter country: ");
//                                String country = scanner.nextLine();
//                                System.out.print("Enter city: ");
//                                String city = scanner.nextLine();
//                                System.out.print("Enter street: ");
//                                String street = scanner.nextLine();
//                                System.out.print("Enter zipcode: ");
//                                String zipcode = scanner.nextLine();
//                                System.out.print("Enter building number: ");
//                                String buildingNumber = scanner.nextLine();
//                                System.out.print("Enter apartment number: ");
//                                String apartmentNumber = scanner.nextLine();
//                                System.out.print("Enter floor number: ");
//                                String floorNumber = scanner.nextLine();
//                                Address address = new Address(country, city, street, zipcode,buildingNumber, floorNumber, apartmentNumber);
//                                customer = auth.register(firstName, lastName, email, password, phoneNumber, address);
//                                if (customer != null) {
//                                    loggedIn = true;
//                                    System.out.println("Registration successful!");
//                                } else {
//                                    System.out.println("Registration failed. Try again.");
//                                }
//
//                                break;
//                            case "login":
//                                System.out.println("Enter email:");
//                                String loginEmail = scanner.nextLine();
//                                System.out.println("Enter password:");
//                                String loginPassword = scanner.nextLine();
//
//                                customer = auth.login(loginEmail, loginPassword);
//
//                                if (customer != null) {
//                                    loggedIn = true;
//                                    System.out.println("Login successful!");
//                                } else {
//                                    System.out.println("Login failed. Try again.");
//                                }
//
//                                break;
//                            default:
//                                System.out.println("Invalid action. Try again.");
//                                break;
//                        }
//                    }
//
//                    boolean shopping = true;
//
//                    while (shopping) {
//                        System.out.println("Choose action: view catalog / view cart / checkout / exit");
//                        String action = scanner.nextLine();
//
//                        switch (action) {
//                            case "view catalog":
//                                catalog.displayItems();
//                                break;
//                            case "view cart":
//                                cart.displayItems();
//                                break;
//                            case "checkout":
//                                if (cart.getItems().isEmpty()) {
//                                    System.out.println("Cart is empty. Add items to cart first.");
//                                } else {
//                                    double totalPrice = cart.getTotalPrice();
//                                    Payment paymentMethod = choosePaymentMethod(scanner);
//
//                                    if (paymentMethod == null) {
//                                        System.out.println("Invalid payment method. Try again.");
//                                    } else if (customer.makePayment(paymentMethod, totalPrice)) {
//                                        orderManager.placeOrder(customer.getId(), cart.getItems(), totalPrice, paymentMethod, customer.getPhoneNumber(), customer.getAddress());
//                                        cart.clear();
//                                        System.out.println("Order successful!");
//                                    } else {
//                                        System.out.println("Payment failed. Try again.");
//                                    }
//                                }
//
//                                break;
//                            case "exit":
//                                shopping = false;
//                                break;
//                            default:
//                                System.out.println("Invalid action
//
//
////public class View {
////    private Scanner scanner;
////    private Authentication authentication;
////    private ArrayList<Item> catalog;
////    private ArrayList<Item> cart;
////    private Customer customer;
////    private Admin admin;
////
////    public View() {
////        scanner = new Scanner(System.in);
////        authentication = new Authentication();
////        catalog = new ArrayList<>();
////        cart = new ArrayList<>();
////    }
////
////    public void start() {
////        System.out.println("Welcome to our store!");
////        while (true) {
////            System.out.println("\nWhat would you like to do?");
////
////            if (customer == null && admin == null) {
////                System.out.println("1. Register a new account");
////                System.out.println("2. Login");
////                System.out.println("3. Browse items");
////                System.out.println("4. Exit");
////                int choice = Integer.parseInt(scanner.nextLine());
////                switch (choice) {
////                    case 1:
////                        register();
////                        break;
////                    case 2:
////                        login();
////                        break;
////                    case 3:
////                        displayCatalog();
////                        break;
////                    case 4:
////                        System.exit(0);
////                    default:
////                        System.out.println("Invalid choice, please try again.");
////                }
////            } else if (customer != null) {
////                System.out.println("1. Browse items");
////                System.out.println("2. Add item to cart");
////                System.out.println("3. View cart");
////                System.out.println("4. Place order");
////                System.out.println("5. Logout");
////                int choice = Integer.parseInt(scanner.nextLine());
////                switch (choice) {
////                    case 1:
////                        displayCatalog();
////                        break;
////                    case 2:
////                        addToCart();
////                        break;
////                    case 3:
////                        viewCart();
////                        break;
////                    case 4:
////                        placeOrder();
////                        break;
////                    case 5:
////                        customer = null;
////                        System.out.println("Logged out successfully.");
////                        break;
////                    default:
////                        System.out.println("Invalid choice, please try again.");
////                }
////            } else if (admin != null) {
////                System.out.println("1. Add item");
////                System.out.println("2. Close order");
////                System.out.println("3. Logout");
////                int choice = Integer.parseInt(scanner.nextLine());
////                switch (choice) {
////                    case 1:
////                        addItem();
////                        break;
////                    case 2:
////                        closeOrder();
////                        break;
////                    case 3:
////                        admin = null;
////                        System.out.println("Logged out successfully.");
////                        break;
////                    default:
////                        System.out.println("Invalid choice, please try again.");
////                }
////            }
////        }
////    }
////
////    private void register() {
////        System.out.println("Please enter your details:");
////        System.out.print("First Name: ");
////        String firstName = scanner.nextLine();
////        System.out.print("Last Name: ");
////        String lastName = scanner.nextLine();
////        System.out.print("Email: ");
////        String email = scanner.nextLine();
////        System.out.print("Password: ");
////        String password = scanner.nextLine();
////        System.out.print("Phone number: ");
////        String phoneNumber = scanner.nextLine();
////        System.out.print("Address:\n");
////        System.out.print("Country: ");
////        String country = scanner.nextLine();
////        System.out.print("City: ");
////        String city = scanner.nextLine();
////        System.out.print("Street: ");
////        String street = scanner.nextLine();
////        System.out.print("Zipcode: ");
////        String zipcode = scanner.nextLine();
////        System.out.print("Building number: ");
////        String buildingNumber = scanner.nextLine();
////        System.out.print("Apartment number: ");
////        String apartmentNumber = scanner.nextLine();
////        System.out.print("Floor number: ");
////        String floorNumber = scanner.nextLine();
////        Address address = new Address(country, city, street, , zipcode,buildingNumber, floorNumber, apartmentNumber);
////
////        if (authentication.register(firstName, lastName, email, password, phoneNumber, address)) {
////            System.out.println("Account created successfully. You can now login.");
////        } else {
////            System.out.println("Failed to create account, please try again.");
////        }
////    }
////
////    private void login() {
////        System.out.print("Email: ");
////        String email = scanner.nextLine();
////        System.out.print("Password: ");
////        String password = scanner.nextLine();
////
////        if (authentication.login(email, password)) {
////            customer = authentication.getCustomer(email);
////
