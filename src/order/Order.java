
package order;
import java.util.ArrayList;
import users.*;
public class Order {
    private String orderId;
    private ArrayList<OrderItem> items;
    private String customerId;
    private double totalPrice;
    private String shippingAddress;
    private String phone;
    private Payment paymentmethod;
    private String status;
    // ORDER,orderid,customerid,totalprice,phone,status,address,items,payement

    public Order(String orderId,String customerId, double totalPrice , String phone, String status, String shippingAddress, ArrayList<OrderItem> items, Payment paymentmethod)  {
        this.orderId = orderId;
        this.items = items;
        this.customerId = customerId;
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

    public String getCustomerId() {
        return customerId;
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

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
                ", customer=" + customerId +
                ", totalPrice=" + totalPrice +
                ", shippingAddress=" + shippingAddress +
                ", phone='" + phone + '\'' +
                ", paymentmethod=" + paymentmethod +
                ", status='" + status + '\'' +
                '}';
    }
    //display orders
    public void viewAllOrders(ArrayList<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i).toString());
        }
    }
    //reOrder a specific order in orders of the user
//    public void reOrder(ArrayList<Order> orders, int orderId) {
//        for (int i = 0; i < orders.size(); i++) {
//            if (orders.get(i).getOrderId() == orderId) {
//                System.out.println(orders.get(i).toString());
//            }
//        }
//    }
}




