/**
 * The Cart class represents the shopping cart of a customer. It contains an ArrayList of OrderItems,
 * the total discount, total price, and a reference to the Customer that owns the cart. It also provides methods
 * to add and remove items from the cart, calculate the total discount, clear the cart, and checkout.
 */
package Order;

import SystemManagement.DataManager;
import Users.Customer;

import java.util.ArrayList;

public class Cart {
    private ArrayList<OrderItem> cartItems;
    private double totalDiscount;
    private double totalPrice;
    private Customer customer;

    /**
     * Constructs a new Cart object with a given customer and list of cart items.
     *
     * @param customer  the customer who owns the cart
     * @param cartItems the list of cart items
     */
    public Cart(Customer customer, ArrayList<OrderItem> cartItems) {
        this.cartItems = cartItems;
        this.customer = customer;
        totalDiscount = calculateTotalDiscount();
        totalPrice = calculateTotalPrice();
    }

    /**
     * Constructs a new Cart object with a given customer, list of cart items, total discount, and total price.
     *
     * @param customer      the customer who owns the cart
     * @param cartItems     the list of cart items
     * @param totalDiscount the total discount applied to the cart
     * @param totalPrice    the total price of the cart
     */
    public Cart(Customer customer, ArrayList<OrderItem> cartItems, double totalDiscount, double totalPrice) {
        this.cartItems = cartItems;
        this.customer = customer;
        this.totalDiscount = totalDiscount;
        this.totalPrice = totalPrice;
    }

    /**
     * Sets the customer who owns the cart.
     *
     * @param customer the customer who owns the cart
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the customer who owns the cart.
     *
     * @return the customer who owns the cart
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the list of cart items.
     *
     * @return the list of cart items
     */
    public ArrayList<OrderItem> getItems() {
        return cartItems;
    }

    /**
     * Returns the total price of the cart.
     *
     * @return the total price of the cart
     */
    public double getTotal() {
        return totalPrice;
    }

    /**
     * Returns the total discount applied to the cart.
     *
     * @return the total discount applied to the cart
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Removes all items from the cart.
     */
    public void clear() {
        cartItems.clear();
    }

    /**
     * Returns true if the cart is empty, false otherwise.
     *
     * @return true if the cart is empty, false otherwise
     */
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    /**
     * Returns a string representation of the cart.
     *
     * @return a string representation of the cart
     */
    @Override
    public String toString() {
        String result = "";
        for (OrderItem item : cartItems) {
            result += item.getItem().getName() + " " + item.getQuantity() + " " + item.getSubtotal() + "\n";
        }
        result += "Total Discount: " + totalDiscount + "\n";
        result += "Total Price: " + totalPrice + "\n";
        return result;
    }

/**
 * Checks out the cart by processing a payment, creating a new order, and updating the inventory.
 *
 *
 */
    /**

     Allows a customer to checkout and create a new order with the provided payment method, customer details, phone number and shipping address.

     @param payment Payment object representing the payment method selected by the customer

     @param customer Customer object representing the customer checking out

     @param phone String representing the phone number of the customer

     @param shippingAddress String representing the shipping address of the customer
     */
    public void checkout(Payment payment, Customer customer, String phone, String shippingAddress) {
        DataManager dataManager = new DataManager();
        ArrayList<Order> orders = dataManager.loadOrders();
        ArrayList<Item> items = dataManager.loadItems();
        ArrayList<Cart> carts = dataManager.loadCarts();
        Cart cart = getCartByCustomer(customer);
        if (cart.getItems().size() == 0) {
            System.out.println("Cart is empty!");
            return;
        }

        if (payment == null) {
            System.out.println("Payment method not set!");
            return;
        }

// Check if quantity is enough
        for (OrderItem orderItem : cart.getItems()) {
            Item item = orderItem.getItem();
            for (Item i : items) {
                if (i.getName().equals(item.getName())) {
                    if (i.getQuantity() < orderItem.getQuantity()) {
                        System.out.println(i.getName() + " is out of stock!");
                        return;
                    }
                }
                break;
            }
        }

        double total = cart.getTotal();
        boolean success = payment.processPayment(total);

        if (success) {
            Order order = new Order(dataManager.getNextOrderId(), customer, total, phone, "pending", shippingAddress, cart.getItems(), payment);
            orders.add(order);
            dataManager.saveOrders(orders);
            for (OrderItem orderItem : cart.getItems()) {
                Item item = orderItem.getItem();
                for (Item i : items) {
                    if (i.getName().equals(item.getName())) {
                        int oldStockQuantity = i.getQuantity();
                        int newStockQuantity = oldStockQuantity - orderItem.getQuantity();
                        i.setQuantity(newStockQuantity);
                        dataManager.saveItems(items);
                        break;
                    }
                }
            }
            if (cart != null) {
                removeFromCarts(cart, carts);
                dataManager.saveCarts(carts);
            }
        } else {
            System.out.println("Payment failed!");
        }
    }

    /**

     Removes a given cart from the list of carts.
     @param cart The Cart object to be removed from the list of carts
     @param carts The ArrayList of all carts to be searched for the given cart object
     */
    public void removeFromCarts(Cart cart, ArrayList<Cart> carts) {
        for (int i = 0; i < carts.size(); i++) {
            if (cart.getCustomer().getEmail().equals(carts.get(i).getCustomer().getEmail())) {
                carts.remove(i);
                break;
            }
        }
    }
    /**

     Adds a given OrderItem object to the cart of the current customer.

     @param orderItem The OrderItem object to be added to the cart

     @return boolean indicating whether the operation was successful
     */
    public boolean addItemToCart(OrderItem orderItem) {
        DataManager dataManager = new DataManager();
        ArrayList<Cart> carts = dataManager.loadCarts();
        ArrayList<Item> items = dataManager.loadItems();
        for (Item item : items) {
            if (item.getId().equals(orderItem.getItem().getId())) {
                if (item.getQuantity() < orderItem.getQuantity()) {
                    System.out.println("Not enough stock!");
                    return false;
                }
            }
        }

        Cart cart = Cart.getCartByCustomer(customer);
        if (cart == null) {
            cart = new Cart(customer, new ArrayList<>());
            customer.setCart(cart);
            carts.add(cart);
        }

        boolean foundItem = false;
        for (OrderItem item : cart.getItems()) {
            if (item.getItem().getId().equals(orderItem.getItem().getId())) {
                item.setQuantity(item.getQuantity() + orderItem.getQuantity());
                foundItem = true;
                break;
            }
        }
        if (!foundItem) {
            OrderItem newItem = new OrderItem(orderItem.getItem(), orderItem.getQuantity());
            cart.getItems().add(newItem);
        }
        for(int i = 0 ; i < carts.size() ; i++){
            if(cart.getCustomer().getEmail().equals(carts.get(i).getCustomer().getEmail())){
                carts.set(i,cart);
                break;
            }
        }
        dataManager.saveCarts(carts);
        return true;
    }

    /**
     * Calculates the total price of the cart.
     * @return double representing the total discount on the cart
     */
    public double calculateTotalDiscount() {
        double total = 0;
        for (OrderItem item : cartItems) {
            total += item.getItem().getDiscount() * item.getQuantity();
        }
        return total;
    }
    /**
     * Calculates the total price of the cart.
     * @return double representing the total price of the cart
     */
    public double calculateTotalPrice() {
        double total = 0;
        for (OrderItem item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }

    /**
     * Returns the cart of the given customer.
     * @param customer The customer whose cart is to be returned
     * @return Cart object of the given customer
     */
    public static Cart getCartByCustomer(Customer customer) {
        DataManager dataManager = new DataManager();
        ArrayList<Cart> carts = dataManager.loadCarts();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getCustomer().getEmail().equals(customer.getEmail())) {
                return carts.get(i);
            }
        }
        return null;
    }

}
