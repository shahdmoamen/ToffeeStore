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

    public Cart(Customer customer, ArrayList<OrderItem> cartItems) {
        this.cartItems = cartItems;
        this.customer = customer;
        totalDiscount = calculateTotalDiscount();
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

    public void addItem(Item item, int quantity, Customer customer) {
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
            } else {
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
            result += item.getItem().getName() + " " + item.getQuantity() + " " + item.getSubtotal() + "\n";
        }
        result += "Total Discount: " + totalDiscount + "\n";
        result += "Total Price: " + totalPrice + "\n";
        return result;
    }

    public void checkout(Payment payment, Customer customer,String phone,String shippingAddress){
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
            }if(cart!=null){
                // remove the cart
                removeFromCarts(cart , carts);
//                carts.remove(cart);
                dataManager.saveCarts(carts);
                }

        }
        else {
            System.out.println("Payment failed!");
        }
    }
    public void removeFromCarts(Cart cart , ArrayList<Cart> carts){
        for(int i = 0 ; i < carts.size() ; i++){
            if(cart.getCustomer().getEmail().equals(carts.get(i).getCustomer().getEmail())){
                carts.remove(i);
                break;
            }
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
        for(int i = 0 ; i < carts.size() ; i++){
            if(cart.getCustomer().getEmail().equals(carts.get(i).getCustomer().getEmail())){
                carts.set(i,cart);
                break;
            }
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
