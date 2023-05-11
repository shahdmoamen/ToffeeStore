package dataManagement;
import order.*;
import users.*;

import java.io.*;
import java.util.ArrayList;


public class DataManager {

    private final String CUSTOMERS_FILE = "customers.csv";
    private final String ADMINS_FILE = "admins.csv";
    private final String ORDERS_FILE = "orders.csv";
    private final String ITEMS_FILE = "items.csv";
    private final String CARTS_FILE ="carts.csv";

    // Save methods
    public void saveCustomers(ArrayList<Customer> customers) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMERS_FILE))) {
            for (Customer customer : customers) {
                // id,fname,lname,email,pass,phone,address
                writer.println(customer.getId()+","+ customer.getFirstName() + "," + customer.getLastName() + "," + customer.getEmail() + "," + customer.getPassword() + "," + customer.getPhoneNumber() + "," + customer.getAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAdmins(ArrayList<Admin> admins) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ADMINS_FILE))) {
            for (Admin admin : admins) {
                // id,fname,lname,email,pass
                writer.println(admin.getId()+","+ admin.getFirstName() + "," + admin.getLastName() + "," + admin.getEmail() + "," + admin.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveOrders(ArrayList<Order> orders) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDERS_FILE))) {
            for (Order order : orders) {
                // ORDER,orderid,customerid,totalprice,phone,status,address,items,payement
                writer.println( "ORDER," +order.getOrderId() + "," + order.getCustomerId() + "," + order.getTotalPrice() + "," + order.getPhone() + "," + order.getStatus() + "," + order.getShippingAddress());
                for (OrderItem item : order.getItems()) {
                    //ITEM,itemid,itemname,description,price,itemstockquantity,discount,brand,categoryquantitybought
                    writer.println("ITEM," + item.getItem().getId() + "," + item.getItem().getName()+ "," + item.getItem().getDescription()+ ","  + item.getItem().getPrice() + ","+ item.getItem().getQuantity()+ "," + item.getItem().getDiscount()+ "," + item.getItem().getBrand()+ "," + item.getItem().getCategory()+ ","  + item.getQuantity());
                }

                    if (order.getPaymentmethod()instanceof VisaPayment) {
                        writer.println("VISA_PAYMENT," + order.getPaymentmethod().getAmount() + "," + ((VisaPayment) order.getPaymentmethod()).getCardNumber() + "," + ((VisaPayment) order.getPaymentmethod()).getExpirationDate());
                    } else if (order.getPaymentmethod() instanceof CashPayment) {
                        writer.println("CASH_PAYMENT," + order.getPaymentmethod().getAmount() + "," + ((CashPayment) order.getPaymentmethod()).getCashTendered());
                    } else if (order.getPaymentmethod() instanceof EWalletPayment) {
                        writer.println("EWALLET_PAYMENT," + order.getPaymentmethod().getAmount() + "," + ((EWalletPayment) order.getPaymentmethod()).getEWalletId());
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // save Items

    public void saveItems(ArrayList<Item> items){
        try (PrintWriter writer = new PrintWriter(new FileWriter(ITEMS_FILE))) {
            for (Item item : items) {
                //itemid,itemname,description,,price,itemstockquantity,discount,brand,category
                writer.println(item.getId() + "," + item.getName() + "," + item.getDescription() + "," + item.getPrice() + "," + item.getQuantity() + "," + item.getDiscount() + "," + item.getBrand() + "," + item.getCategory());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // save cart
    public void saveCarts(ArrayList<Cart> carts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CARTS_FILE))) {
            for (Cart cart : carts) {
                writer.println(cart.getTotalDiscount() + "," + cart.getTotal());
                writer.println("CUSTOMER"+","+cart.getCustomer().getId() + "," + cart.getCustomer().getFirstName() + "," + cart.getCustomer().getLastName() + "," + cart.getCustomer().getEmail() + "," + cart.getCustomer().getPassword() + "," + cart.getCustomer().getPhoneNumber() + "," + cart.getCustomer().getAddress());
                for (OrderItem item : cart.getItems()) {
                    //ITEM,itemid,itemname,description,price,itemstockquantity,discount,brand,categoryquantitybought
                    writer.println("ITEM," + item.getItem().getId() + "," + item.getItem().getName()+ "," + item.getItem().getDescription()+ ","  + item.getItem().getPrice() + ","+ item.getItem().getQuantity()+ "," + item.getItem().getDiscount()+ "," + item.getItem().getBrand()+ "," + item.getItem().getCategory()+ ","  + item.getQuantity());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  ArrayList<Customer> loadCustomers() {
        ArrayList<Customer>customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // id,fname,lname,email,pass,phone,address
                String id = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                String email = parts[3];
                String password = parts[4];
                String phone = parts[5];
                String address = parts[6];
                Customer customer = new Customer(id,firstName,lastName,email,password,phone,address);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return customers;
    }
    public ArrayList<Admin> loadAdmins() {
        ArrayList<Admin>admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMINS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // id,fname,lname,email,pass
                String id = parts[0];
                String firstName = parts[1];
                String lastName = parts[2];
                String email = parts[3];
                String password = parts[4];
                Admin admin = new Admin(id,firstName,lastName,email,password);
                admins.add(admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return admins;
    }
    public ArrayList<Order> loadOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(",");
                // ORDER,orderid,customerid,totalprice,phone,status,address,items,payement
                if (parts[0].equals("ORDER")) {
                    String orderId = parts[1];
                    String customerId = parts[2];
                    double totalPrice = Double.parseDouble(parts[3]);
                    Payment paymentmethod = null;
                    String phone = parts[5];
                    String status = parts[6];
                    String shippingAddress = parts[7];
                    ArrayList<OrderItem> items = new ArrayList<>();
                    while ((line = reader.readLine()) != null && !line.equals("END_ORDER")) {
                        parts = line.split(",");
                        if (parts[0].equals("ITEM")) {
                            //"ITEM',itemid,itemname,description,price,itemstockquantity,discount,brand,category
                            String itemId = parts[1];
                            String itemName = parts[2];
                            String description = parts[3];
                            double price = Double.parseDouble(parts[4]);
                            int stockQuantity= Integer.parseInt(parts[5]);
                            double discount = Double.parseDouble(parts[6]);
                            String brand = parts[7];
                            String category = parts[8];
                            int quantity=Integer.parseInt(parts[9]);
                            Item item = new Item(itemId, itemName, description, price,stockQuantity,discount,brand,category);
                            items.add(new OrderItem(item, quantity));

                            items.add(new OrderItem(item, quantity));
                        } else if (parts[0].equals("VISA_PAYMENT")) {
                            double amount = Double.parseDouble(parts[1]);
                            String cardNumber = parts[2];
                            String expirationDate = parts[3];
                            String cvv=parts[4];
                            paymentmethod = new VisaPayment(amount, cardNumber, expirationDate,cvv);
                        } else if (parts[0].equals("CASH_PAYMENT")) {
                            double amount = Double.parseDouble(parts[1]);
                            double cashTendered = Double.parseDouble(parts[2]);
                            paymentmethod = new CashPayment(amount, cashTendered);
                        } else if (parts[0].equals("EWALLET_PAYMENT")) {
                            double amount = Double.parseDouble(parts[1]);
                            String eWalletId = parts[2];
                            String eWalletPassword= parts[3];
                            paymentmethod = new EWalletPayment(amount, eWalletId,eWalletPassword);
                        }

                        orders.add(new Order(orderId, customerId, totalPrice, phone,status, shippingAddress, items,paymentmethod));
                }
            }
            }} catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<Item> loadItems() {
        ArrayList<Item>items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEMS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                //itemid,itemname,description,price,itemstockquantity,discount,brand,category
                String itemId = parts[0];
                String itemName = parts[1];
                String description = parts[2];
                int quantity= Integer.parseInt(parts[4]);
                double discount = Double.parseDouble(parts[5]);
                String brand = parts[6];
                String category = parts[7];
                double price = Double.parseDouble(parts[3]);

                Item item = new Item(itemId, itemName, description, price,quantity,discount,brand,category);
                    items.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }return items;
    }
    public ArrayList<Cart> loadCarts() {
        ArrayList<Cart> carts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(CARTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("CUSTOMER")) {
                    // Create a new orderItems list for each customer
                    ArrayList<OrderItem> orderItems = new ArrayList<>();

                    // Parse customer info
                    String[] customerInfo = line.split(",");
                    String customerId = customerInfo[1];
                    String firstName = customerInfo[2];
                    String lastName = customerInfo[3];
                    String email = customerInfo[4];
                    String password = customerInfo[5];
                    String phoneNumber = customerInfo[6];
                    String address = customerInfo[7];
                    Customer customer = new Customer(customerId, firstName, lastName, email, password, phoneNumber, address);

                    double totalDiscount = 0.0;
                    double totalPrice = 0.0;

                    // Read the next line
                    line = reader.readLine();

                    while (line != null && line.startsWith("ITEM")) {
                        // Parse item info
                        String[] itemInfo = line.split(",");
                        String itemId = itemInfo[1];
                        String itemName = itemInfo[2];
                        String description = itemInfo[3];
                        double price = Double.parseDouble(itemInfo[4]);
                        int stockQuantity = Integer.parseInt(itemInfo[5]);
                        double discount = Double.parseDouble(itemInfo[6]);
                        String brand = itemInfo[7];
                        String category = itemInfo[8];
                        int quantity = Integer.parseInt(itemInfo[9]);
                        Item item = new Item(itemId, itemName, description, price, stockQuantity, discount, brand, category);
                        OrderItem orderItem = new OrderItem(item, quantity);
                        orderItems.add(orderItem);

                        totalPrice += (price-discount) * quantity;

                        totalDiscount += discount * quantity;

                        // Read the next line
                        line = reader.readLine();
                    }

                    // Create a new cart for the current customer
                    Cart cart = new Cart(customer, orderItems, totalDiscount, totalPrice);
                    carts.add(cart);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return carts;
    }
    public String getNextOrderId() {
        int nextOrderId = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("orders.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                int orderId = Integer.parseInt(data[0]);
                if (orderId > nextOrderId) {
                    nextOrderId = orderId;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(nextOrderId+1);
    }


    public String getNextCustomerId() {
        int nextCustomerId = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("customers.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                int orderId = Integer.parseInt(data[0]);
                if (orderId > nextCustomerId) {
                    nextCustomerId = orderId;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(nextCustomerId+1);
    }
    public String getNextAdminId() {
        int nextAdminId = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("admins.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                int orderId = Integer.parseInt(data[0]);
                if (orderId > nextAdminId) {
                    nextAdminId = orderId;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(nextAdminId+1);
    }
    public String getNextItemId() {
        int nextItemId = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("items.csv"));
            String line = reader.readLine();
            while (line != null) {
                String[] data = line.split(",");
                int orderId = Integer.parseInt(data[0]);
                if (orderId > nextItemId) {
                    nextItemId = orderId;
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.toString(nextItemId+1);
    }



}

