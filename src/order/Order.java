
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


    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", items=" + items +
                ", customer=" +customer.getId() +
                ", totalPrice=" + totalPrice +
                ", shippingAddress=" + shippingAddress +
                ", phone='" + phone + '\'' +
                ", paymentmethod=" + paymentmethod +
                ", status='" + status + '\'' +
                '}';
    }//get order by id
    public static Order getOrderById(String id) {
        DataManager dataManager = new DataManager();
        ArrayList<Order> orders = dataManager.loadOrders();
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(id)) {
                return orders.get(i);

            }
        }
        return null;
    }


    }



