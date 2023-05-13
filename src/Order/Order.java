/**
 * Order class represents an order made by a customer.
 */
package Order;

import java.util.ArrayList;

import Users.*;

public class Order {

    /**
     * The ID of the order.
     */
    private String orderId;

    /**
     * The list of order items.
     */
    private ArrayList<OrderItem> items;

    /**
     * The customer who placed the order.
     */
    private Customer customer;

    /**
     * The total price of the order.
     */
    private double totalPrice;

    /**
     * The shipping address of the order.
     */
    private String shippingAddress;

    /**
     * The phone number of the customer who placed the order.
     */
    private String phone;

    /**
     * The payment method used for the order.
     */
    private Payment paymentmethod;

    /**
     * The status of the order.
     */
    private String status;

    /**
     * Constructs an Order object with the specified parameters.
     *
     * @param orderId         the ID of the order
     * @param customer        the customer who placed the order
     * @param totalPrice      the total price of the order
     * @param phone           the phone number of the customer who placed the order
     * @param status          the status of the order
     * @param shippingAddress the shipping address of the order
     * @param items           the list of order items
     * @param paymentmethod   the payment method used for the order
     */
    public Order(String orderId, Customer customer, double totalPrice, String phone, String status,
                 String shippingAddress, ArrayList<OrderItem> items, Payment paymentmethod) {
        this.orderId = orderId;
        this.items = items;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.paymentmethod = paymentmethod;
        this.status = status;
    }

    /**
     * Returns the ID of the order.
     *
     * @return the ID of the order
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Returns the list of order items.
     *
     * @return the list of order items
     */
    public ArrayList<OrderItem> getItems() {
        return items;
    }

    /**
     * Returns the customer who placed the order.
     *
     * @return the customer who placed the order
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the total price of the order.
     *
     * @return the total price of the order
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the ID of the order.
     *
     * @param orderId the ID of the order
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Sets the list of order items.
     *
     * @param items the list of order items
     */
    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    /**
     * Sets the customer who placed the order.
     *
     * @param customerId the customer who placed the order
     */
    public void setCustomer(Customer customerId) {
        this.customer = customerId;
    }

    /**
     * Sets the total price of the order.
     *
     * @param totalPrice the total price of the order
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

/**
 * Sets the shipping address of the order.
 *
 * @param shippingAddress the shipping address of the order
 */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    /**
     * Sets the phone number of the customer who placed the order.
     *
     * @param phone the phone number of the customer who placed the order
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * Sets the payment method used for the order.
     *
     * @param paymentmethod the payment method used for the order
     */
    public void setPaymentmethod(Payment paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
    /**
     * Sets the status of the order.
     *
     * @param status the status of the order
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * * Returns the shipping address of the order.
     *
     * @return the shipping address of the order
     */
    public String getShippingAddress() {
        return shippingAddress;
    }
    /**
     * Returns the phone number of the customer who placed the order.
     *
     * @return the phone number of the customer who placed the order
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Returns the payment method used for the order.
     *
     * @return the payment method used for the order
     */
    public Payment getPaymentmethod() {
        return paymentmethod;
    }
    /**
     * Returns the status of the order.
     *
     * @return the status of the order
     */
    public String getStatus() {
        return status;
    }

    /**
     * closes the order(changes the status from pending to delivered).
     *
     * @param status the status of the order
     */
    /**
     * Closes the order by setting its status to "delivered".
     */
    public void closeOrder() {
        this.status = "delivered";
    }

    /**
     * Prints the details of the order to the console, including its ID, customer, total price, shipping address, phone, payment method, status, and items.
     */
    public void printOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Phone: " + phone);
        System.out.println("Payment Method: " + paymentmethod.getPaymentDetails());
        System.out.println("Status: " + status);
        System.out.println("Items: ");
        for (OrderItem item : items) {
            System.out.println("Item ID: " + item.getItem().getId());
            System.out.println("Item Name: " + item.getItem().getName());
            System.out.println("Item Price: " + item.getItem().getPrice());
            System.out.println("Item Quantity: " + item.getQuantity());
        }
        System.out.println("--------------------------------------------------");
    }

}
