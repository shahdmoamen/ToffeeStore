/**

 The Customer class represents a customer of the Toffee Store. It extends the User class and
 includes additional fields and methods specific to customers.
 */
package Users;
import Order.Cart;
import Order.Order;
import java.util.ArrayList;
public class Customer extends User {
    private ArrayList<Order> orders;
    private Cart cart;
    private String phoneNumber;
    private String address;

    /**
     * Constructs a new Customer object with the given parameters.
     *
     * @param id          the unique ID of the customer
     * @param firstName   the first name of the customer
     * @param lastName    the last name of the customer
     * @param email       the email address of the customer
     * @param password    the password of the customer's account
     * @param phoneNumber the phone number of the customer
     * @param address     the address of the customer
     */
    public Customer(String id, String firstName, String lastName, String email, String password, String phoneNumber, String address) {
        super(id, firstName, lastName, email, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * Returns an ArrayList of all the orders made by the customer.
     *
     * @return an ArrayList of all the orders made by the customer
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Adds the given Order object to the list of orders made by the customer.
     *
     * @param order the Order object to add to the list of orders
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Returns the Cart object of the customer.
     *
     * @return the Cart object of the customer
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the Cart object of the customer to the given Cart object.
     *
     * @param cart the new Cart object to set for the customer
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return the phone number of the customer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the customer to the given phone number.
     *
     * @param phoneNumber the new phone number to set for the customer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the address of the customer.
     *
     * @return the address of the customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer to the given address.
     *
     * @param address the new address to set for the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }
}

