//Sara Tamer and Maya Ayman have business for selling sweets like candy, chocolate, toffee and others. Some of these items are sold in packages and some are sold by kilo. They want to expand by making an online store for their goods. They hired CS251 Company to do their e-commerce web site.
//The site will offer a catalog of goods that customers can order. The customer can choose to pay upon delivery or via payment methods like smart wallets. A general user can view the catalog but to buy s/he must register and login. A user can view all items in the catalog, items in a specific category, or search for an item by name or by brand. Registration requires a valid email, a password and an address. Password should follow secure password guidelines. Registration is completed by sending an OTP to the email used, that the user has to enter to complete the registration; otherwise, registration is rejected.
//A logged-in user can shop and add items to his or her shopping cart until s/he is finished, then s/he can check-out. Sealed items are sold by unit and the client chooses up to 50 units of each item in one order. Loose items are sold by kilo, and the buyer can buy any amount up to 50 kilos of an item in one order. Shoppers can also buy gift vouchers to give as a gift to someone. A gift voucher has a unique code and can be redeemed once when making an order to reduce the total price by the value of the voucher.
//A system admin can update catalog with new items, cancel items or update item info. An item has a name, category, description, image, brand, price and a discount percentage (if any). Admin can view all orders, set loyalty points scheme, suspend a user and view statistics. Statistics provide store owners with a view of how the store is doing. This may include daily and monthly sales, sales of each item in a period of time, most popular products, among other statistics.
//Buyers can place an order by selecting the items they want and the quantity the want from each item. Then they check-out. They can also view their order history and they can re-order a previous order by clicking "re-corder" button which makes the same exact order with the same exact conditions. When making an order, a buyer earns loyalty points according to the scheme decided by the admin.
//Upon making an order, shoppers have to specify the shipping address; if it is the same address registered on their profile or another address (e.g., in case they are buying a gift from someone). If they are paying upon delivery, they have to enter to a valid phone number. An OTP is sent to the mobile number and the buyer has to enter it to verify the phone number. To pay for an order, a buyer can:
//1)	Use one gift voucher or more to pay for their order or part of it.
//2)	Redeem some of the loyalty points to pay for the order or part of it.
//3)	Pay for the order or the remaining amount via payment methods like smart wallets.
//4)	Pay for the order or the remaining amount with cash upon delivery.

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




