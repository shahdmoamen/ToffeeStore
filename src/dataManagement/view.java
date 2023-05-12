package dataManagement;
import order.*;
import users.*;

import java.util.ArrayList;
import java.util.Scanner;


public class view {
    private static DataManager dataManager = new DataManager();
    private static Authentication authentication = new Authentication(dataManager.loadCustomers());
    private static Catalog catalog = new Catalog(dataManager.loadItems());
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
                        register();
                        break;
                    case 2:
                        login();
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
                System.out.println("2. View cart");
                System.out.println("3. Place order");
                System.out.println("4. Logout");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        browseCatalog();
                        break;
                    case 2:
                        viewCart();
                        break;
                    case 3:
                        checkout();
                        break;
                    case 4:
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

    private static void register() {
        // Prompt user for customer details
        //choose account type
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose account type:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                registerCustomer();
                break;
            case 2:
                registerAdmin();
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }

    }
    public static void registerCustomer(){
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
        Customer customer = new Customer(dataManager.getNextCustomerId(), firstName, lastName, email, password, phoneNumber, address);
        if(authentication.registerCustomer(customer)){
            System.out.println("Customer account created successfully.");}
        else{
            System.out.println("Failed to create customer account.");
        }
    }
    public static void registerAdmin(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Admin admin = new Admin(dataManager.getNextAdminId(), firstName, lastName, email, password);
        if(authentication.registerAdmin(admin)){
            System.out.println("Admin account created successfully.");}
        else{
            System.out.println("Failed to create admin account.");
        }




    }
    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose account type:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        switch (option) {
            case 1:
                if (authentication.loginCustomer(email, password)) {
                    customer = authentication.getCustomer();
                    System.out.println("Customer account logged in successfully.");
                } else {
                    System.out.println("Failed to log into customer account.");
                }
                break;
            case 2:
                if (authentication.loginAdmin(email, password)) {
                    admin = authentication.getAdmin();
                    System.out.println("Admin account logged in successfully.");
                } else {
                    System.out.println("Failed to log into admin account.");
                }
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
    public static void browseCatalog(){
        System.out.println("Catalog:");
        System.out.println(catalog.toString());
        if (customer != null) {
            System.out.println("If you want to add an item to your cart, enter its ID. If not, enter 0.");
            Scanner scanner = new Scanner(System.in);
            String id = scanner.nextLine();
            if (id.equals("0")) {
                return;
            }
            catalog.searchItemById(id);
            addItemToCart(id);
        }

    }
    public static void addItemToCart(String id){
        //get quantity
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter quantity: ");
        int quantity = scanner.nextInt();

        //check if customer has a cart
        Cart cart = Cart.getCartByCustomer(customer);
        if (cart == null) {
            cart = new Cart(customer, new ArrayList<>());
            customer.setCart(cart);
        }
        //add item to cart
        Item item = catalog.searchItemById(id);
        if (item != null) {
            OrderItem orderItem = new OrderItem(item, quantity);
            if (cart.addItemToCart(orderItem)) {
                System.out.println("Item added to cart successfully.");
            } else {
                System.out.println("Failed to add item to cart.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }

    public static void viewCart() {
        Cart cart = Cart.getCartByCustomer(customer);
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("Items in your cart:");
        for (OrderItem item : cart.getItems()) {
            System.out.println(item.getItem().getName() + " x " + item.getQuantity() +
                    " = " + item.getSubtotal());
        }
        System.out.println("Total price: " + cart.getTotal());
    }
    public static void checkout() {
        // Get the Customer object from somewhere

        // Get the Cart object associated with the Customer object
        Cart cart = Cart.getCartByCustomer(customer);

        // Check if the Cart object is null or empty
        if (cart == null || cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        double amount = cart.getTotal();
        System.out.println("The amount to be paid is: " + amount + " L.E.");
        System.out.println("Choose payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.println("3. Debit Card");
        int option = scanner.nextInt();
scanner.nextLine();

        switch (option) {
            case 1:
                SendOTP sendotp = new SendOTP();
                if (sendotp.sendVerificationCode(customer)){
                    System.out.println("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.println("Enter phoneNumber: ");
                    String number = scanner.nextLine();
                    System.out.println("Delivery man will call you on " + number);
                    System.out.println("Enter amount of cash: ");
                    double cash = scanner.nextDouble();
                    Payment p = new CashPayment(amount, cash);
                    cart.checkout(p, customer,number,address);
                    }else {
                        System.out.println("Failed to send OTP");
                    }
                break;
            case 2:
                System.out.println("Enter visa number: ");
                String visaNumber = scanner.nextLine();
                System.out.println("Enter expiry date: ");
                String expiryDate = scanner.nextLine();
                System.out.println("Enter cvv: ");
                String cvv = scanner.nextLine();
                Payment p1 = new VisaPayment(amount, visaNumber, expiryDate, cvv);
                cart.checkout(p1, customer,customer.getPhoneNumber(),customer.getAddress());
                break;

            case 3:
                System.out.println("Enter eWallet number: ");
                String eWalletNumber = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                Payment p2 = new EWalletPayment(amount, eWalletNumber, password);
                cart.checkout(p2, customer,customer.getPhoneNumber(),customer.getAddress());
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
        DataManager dataManager = new DataManager();
        ArrayList<Order> orders = dataManager.loadOrders();
        Scanner scanner = new Scanner(System.in);
        for (Order order: orders){
            order.printOrder();
            System.out.println(orders.size());
        }
        System.out.println("Enter order id: ");
        String id = scanner.nextLine();
            for (Order o : orders) {
                if (o.getOrderId().equals(id)) {
                    if(o.getStatus().equals("Closed")){
                        System.out.println("Order is already closed.");
                        return;
                    }
                    o.closeOrder();
                    dataManager.saveOrders(orders);
                    System.out.println("Order closed successfully.");
                    return;
                }else {
                    System.out.println("Order not found.");
            }

        }
    }
}
