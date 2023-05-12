
package order;
import java.util.ArrayList;

import dataManagement.DataManager;
import users.*;
public class Order {
    private String orderId;
    private ArrayList<OrderItem> items;
    private Customer customer; ;
    private double totalPrice;
    private String shippingAddress;
    private String phone;
    private Payment paymentmethod;
    private String status;
    // ORDER,orderid,customerid,totalprice,phone,status,address,items,payement

    public Order(String orderId,Customer customer, double totalPrice , String phone, String status, String shippingAddress, ArrayList<OrderItem> items, Payment paymentmethod)  {
        this.orderId = orderId;
        this.items = items;
        this.customer = customer;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.paymentmethod = paymentmethod;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public void setCustomer(Customer customerId) {
        this.customer = customerId;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setPaymentmethod(Payment paymentmethod) {
        this.paymentmethod = paymentmethod;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }
    public String getPhone() {
        return phone;
    }
    public Payment getPaymentmethod() {
        return paymentmethod;
    }
    public String getStatus() {
        return status;
    }
    public void closeOrder() {
        this.status = "closed";
    }

    public void printOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Phone: " + phone);
        System.out.println("Payment Method: " + paymentmethod);
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



