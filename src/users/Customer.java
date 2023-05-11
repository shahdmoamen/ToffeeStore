package users;
import order.Cart;
import order.Order;
import java.util.ArrayList;
public class Customer extends User {
    private ArrayList<Order> orders;
    private Cart cart;
    private String phoneNumber;
    private String address;

    public Customer(String id,String firstName, String lastName, String email, String password, String phoneNumber, String address) {
        super(id,firstName, lastName, email, password);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    public ArrayList<Order> getOrders() {
        return orders;
    }
    public void addOrder(Order order) {
        orders.add(order);
    }
    public Cart getCart(){
        return cart;
    }
public void setCart(Cart cart){
    this.cart=cart;
}
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

