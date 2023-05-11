package order;
import dataManagement.DataManager;
import users.Address;
import users.Customer;

import java.util.ArrayList;

public class Cart {
    private ArrayList<OrderItem> cartItems;
    private float totalDiscount;
    private float totalPrice;
    private Customer customer;

    public Cart(Customer customer, ArrayList<OrderItem> cartItems , float totalDiscount, float totalPrice) {
        this.cartItems = cartItems;
        this.customer = customer;
        this.totalDiscount = totalDiscount;
        this.totalPrice = totalPrice;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Customer getCustomer() {
        return customer;
    }

    public void addItem(Item item) {

    for (OrderItem orderItem : cartItems) {
            if (orderItem.getItem().getId().equals(item.getId())) {
                orderItem.setQuantity(orderItem.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(new OrderItem(item, 1));}

    public void removeItem(Item item) {
        for (OrderItem orderItem : cartItems) {
            if (orderItem.getItem().getId().equals(item.getId())) {
                if (orderItem.getQuantity() > 1) {
                    orderItem.setQuantity(orderItem.getQuantity() - 1);
                } else {
                    cartItems.remove(orderItem);
                }
                return;
            }
        }
    }


    public ArrayList<OrderItem> getItems() {
        return cartItems;
    }

    public void setTotal() {
        for (OrderItem item : cartItems) {
            totalPrice += item.getSubtotal();
        }
    }
    public float getTotal() {
        return totalPrice;
    }
    public void setTotalDiscount() {
        for (OrderItem item : cartItems) {
                totalDiscount += item.getItem().getDiscount() * item.getQuantity();
       }
    }
    public float getTotalDiscount() {
        return totalDiscount;
    }

    public void clear() {
        cartItems.clear();
    }

    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    @Override
    public String toString() {
        String result = "";
        for (OrderItem item : cartItems) {
            result += item.toString() + "\n";
        }
        result += "Total Discount: " + totalDiscount + "\n";
        result += "Total Price: " + totalPrice + "\n";
        return result;
    }
//display cart


    public void checkout(Payment payment) {
        DataManager dataManager = new DataManager();
        ArrayList<Order>orders = dataManager.loadOrders();
        ArrayList<Item>items= dataManager.loadItems();
        ArrayList<Customer>customers = dataManager.loadCustomers();


        if (items.size() == 0) {
            System.out.println("Cart is empty!");
            return;
        }
        if (payment == null) {
            System.out.println("Payment method not set!");
            return;
        }
        double total = getTotal();
        // get customer info
        String shippingAddress = customer.getAddress();
        String phone = customer.getPhoneNumber();
        boolean success = payment.processPayment(total) ;

        if (success) {
            System.out.println("Payment successful!");
            Order order = new Order(dataManager.getNextOrderId(), customer.getId(), total,phone,"pending", shippingAddress,cartItems, payment);
            orders.add(order);
            dataManager.saveOrders(orders);
            // update item stock quantity
            for (OrderItem orderItem : cartItems) {
                items.remove(orderItem);

            }
            dataManager.saveItems(items);
            items.clear();
        } else {
            System.out.println("Payment failed!");
        }
    }
}


