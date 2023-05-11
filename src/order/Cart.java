package order;
import dataManagement.DataManager;
import users.Address;
import users.Customer;

import java.util.ArrayList;
import java.util.Arrays;

public class Cart {
    private ArrayList<OrderItem> cartItems;
    private double totalDiscount;
    private double totalPrice;
    private Customer customer;

    public Cart(Customer customer, ArrayList<OrderItem> cartItems ) {
        this.cartItems = cartItems;
        this.customer = customer;
        totalDiscount= calculateTotalDiscount();
        totalPrice = calculateTotalPrice();
    }
    public Cart(Customer customer, ArrayList<OrderItem> cartItems, double totalDiscount, double totalPrice) {
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

    public void addItem(Item item ,int quantity, Customer customer) {
        DataManager dataManager = new DataManager();
        ArrayList<Cart> carts = dataManager.loadCarts();
        for (Cart c : carts) {
            if (c.getCustomer().getEmail().equals(customer.getEmail())) {
                //check if item is already in the cart
                for (OrderItem orderItem : c.getItems()) {
                    if (orderItem.getItem().getId().equals(item.getId())) {
                        orderItem.setQuantity(orderItem.getQuantity() + quantity);
                        //update the  cart to the carts arraylist
                        carts.set(carts.indexOf(c), c);
                        dataManager.saveCarts(carts);
                        return;
                    }
                }
                c.getItems().add(new OrderItem(item, quantity));
                // the  cart to the carts arraylist
                carts.set(carts.indexOf(c), c);
                dataManager.saveCarts(carts);
            }
            else {
                ArrayList<OrderItem> OrderItems = new ArrayList<>();
                OrderItems.add(new OrderItem(item, 1));
                carts.add(new Cart(customer, OrderItems));
                dataManager.saveCarts(carts);
            }

        }
    }

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

    public double getTotal() {
        return totalPrice;
    }

    public double getTotalDiscount() {
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
            result += item.getItem().getName()+" "+ item.getQuantity()+" "+item.getSubtotal() + "\n";
        }
        result += "Total Discount: " + totalDiscount + "\n";
        result += "Total Price: " + totalPrice + "\n";
        return result;
    }
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
    public boolean addItemToCart(OrderItem orderItem) {
        DataManager dataManager = new DataManager();
        ArrayList<Cart> carts = dataManager.loadCarts();

        // Check if the customer has a cart
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

        dataManager.saveCarts(carts);
        return true;
    }

    public double calculateTotalDiscount() {
        double total = 0;
        for (OrderItem item : cartItems) {
            total += item.getItem().getDiscount() * item.getQuantity();
        }
        return total;
    }
    public double calculateTotalPrice() {
        double total = 0;
        for (OrderItem item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }
    //get cart by customer
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


