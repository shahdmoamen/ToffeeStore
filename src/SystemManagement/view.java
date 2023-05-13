/**
 * The view class is responsible for handling the user interface. It provides the user with various options to register, login, browse items, view cart, place an order, add an item, close an order and logout. The class has an instance of DataManager, Authentication and Catalog. The instance of Authentication class is used to register, login and get the current user. The instance of Catalog class is used to browse items. The class has an instance of Customer and Admin that are used to handle the user account and role respectively.
 */
package SystemManagement;

import Order.*;
import Users.*;

import java.util.ArrayList;
import java.util.Scanner;

public class view {
    /**
     * An instance of DataManager class
     */
    private static DataManager dataManager = new DataManager();

    /**
     * An instance of Authentication class
     */
    private static Authentication authentication = new Authentication(dataManager.loadCustomers());

    /**
     * An instance of Catalog class
     */
    private static Catalog catalog = new Catalog(dataManager.loadItems());

    /**
     * An instance of Customer class
     */
    private static Customer customer;

    /**
     * An instance of Admin class
     */
    private static Admin admin;

    /**
     * This method is the main method that handles the user interface. It provides the user with various options to register, login, browse items, view cart, place an order, add an item, close an order and logout.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("!Welcome to our store!");
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
                        System.out.println("Goodbye!");
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

/**
 * This method is used to register a new account.
 * The user is prompted to choose an account type: customer or admin.
 * If the user chooses customer, the registerCustomer() method is called.
 * If the user chooses admin, the registerAdmin() method is called.
 * If the user chooses an invalid option, an error message is displayed.
 */
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
    /**
     * This method is used to login to an existing account.
     * The user is prompted to choose an account type: customer or admin.
     * If the user chooses customer, the loginCustomer() method is called.
     * If the user chooses admin, the loginAdmin() method is called.
     * If the user chooses an invalid option, an error message is displayed.
     */
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
    /**
     * This method is used to register a new admin account.
     * The user is prompted to enter the admin details.
     * If the admin account is created successfully, a success message is displayed.
     * If the admin account is not created successfully, an error message is displayed.
     */
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
    /**
     * This method is used to log in to an existing account.
     * The user is prompted to choose an account type: customer or admin.
     * If the user chooses customer, the loginCustomer() method is called.
     * If the user chooses admin, the loginAdmin() method is called.
     * If the user chooses an invalid option, an error message is displayed.
     */
    private static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose account type:");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                scanner.nextLine();
                System.out.print("Enter email: ");
                String customerEmail = scanner.nextLine();
                System.out.print("Enter password: ");
                String customerPassword = scanner.nextLine();
                if (authentication.loginCustomer(customerEmail, customerPassword)) {
                    customer = authentication.getCustomer();
                    System.out.println("Customer account logged in successfully.");
                } else {
                    System.out.println("Failed to log into customer account.");
                }
                break;
            case 2:
                scanner.nextLine();
                System.out.print("Enter email: ");
                String adminEmail = scanner.nextLine();
                System.out.print("Enter password: ");
                String AdminPassword = scanner.nextLine();
                if (authentication.loginAdmin(adminEmail, AdminPassword)) {
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

    /**
     * This method is used to view the catalog.
     * If the user is logged in, they are prompted to add an item to their cart.
     * If the user chooses to add an item to their cart, the addItemToCart() method is called.
     */
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

    /**
     * This method is used to add an item to the cart.
     * The user is prompted to enter the quantity of the item they want to add to the cart.
     * If the item is added successfully, a success message is displayed.
     * If the item is not added successfully, an error message is displayed.
     * @param id
     */
    public static void addItemToCart(String id){
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
                viewCart();
            } else {
                System.out.println("Failed to add item to cart.");
            }
        } else {
            System.out.println("Item not found.");
        }
    }


    /**
     * This method is used to view the cart.
     * The user is prompted to choose an option: add item, remove item, or exit.
     * If the user chooses to add an item, the browseCatalog() method is called and the user is prompted to enter the ID of the item they want to add.
     * If the user chooses to remove an item, the removeItemFromCart() method is called.
     * If the user chooses to exit, the method returns.
     * If the user chooses an invalid option, an error message is displayed.
     * If the cart is empty, an error message is displayed.
     * If the cart is not empty, the items in the cart are displayed.
     */
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an option:");
        System.out.println("1. Add item");
        System.out.println("2. Exit");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                browseCatalog();
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid choice, please try again.");
        }

    }

    /**
     * This method is used to check out.
     * The user is prompted to choose a payment method: cash, credit card, or debit card.
     * If the user chooses cash, the amount to be paid is displayed.
     * If the user chooses credit card, the amount to be paid is displayed and the user is prompted to enter their credit card number.
     * If the user chooses E-Payment, the amount to be paid is displayed and the user is prompted to enter their E-Wallet number and password.
     * If the user chooses an invalid option, an error message is displayed.
     * If the cart is empty, an error message is displayed.
     */
    public static void checkout() {

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
        System.out.println("3. E-Payment");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                SendOTP sendotp = new SendOTP();
                System.out.println("Enter address: ");
                String address = scanner.nextLine();
                System.out.println("Enter phoneNumber: ");
                String number = scanner.nextLine();
                if (!number.matches("^^01[0125][0-9]{8}$")) {
                    System.out.println("Invalid phone number");
                    return;
                }
                if (sendotp.sendVerificationCode(customer)){
                    System.out.println("Delivery man will call you on " + number);
                    System.out.println("Enter amount of cash: ");
                    double cash = scanner.nextDouble();
                    Payment p = new CashPayment(amount, cash);
                    cart.checkout(p, customer,number,address);
                    }else {
                        System.out.println("redirecting to home page");
                    }
                break;
            case 2:
                Payment p1 = new VisaPayment(amount);
                cart.checkout(p1, customer,customer.getPhoneNumber(),customer.getAddress());
                break;

            case 3:
                Payment p2 = new EWalletPayment(amount);
                cart.checkout(p2, customer,customer.getPhoneNumber(),customer.getAddress());
                break;

            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    /**
     * This method is used to add an item to the catalog.
     * The user is prompted to enter the item name, description, price, quantity, discount, brand, and category.
     * If the item is added successfully, a success message is displayed.
     * If the item is not added successfully, an error message is displayed.
     * If the user enters an invalid input, an error message is displayed.
     */
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

    /**
     * This method is used to close an order.
     * The user is prompted to enter the order ID.
     * If the order is found, the order is closed and a success message is displayed.
     * If the order is not found, an error message is displayed.
     * If the order is already closed, an error message is displayed.
     */
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
                if(o.getStatus().equals("delivered")){
                    System.out.println("Order is already closed.");
                    return;
                }
                o.closeOrder();
                dataManager.saveOrders(orders);
                System.out.println("Order closed successfully.");
                return;
            }
        }
        System.out.println("Order not found.");
    }

}
