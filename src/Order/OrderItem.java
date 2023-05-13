package Order;

/**
 * Represents an item in an order and the quantity of that item.
 */
public class OrderItem {
    private Item item;
    private int quantity;

    /**
     * Constructs an OrderItem object with the given item and quantity.
     * @param item the item to be ordered
     * @param quantity the quantity of the item
     */
    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Returns the item of this OrderItem.
     * @return the item of this OrderItem
     */
    public Item getItem() {
        return item;
    }

    /**
     * Sets the item of this OrderItem.
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the quantity of this OrderItem.
     * @return the quantity of this OrderItem
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of this OrderItem.
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the subtotal of this OrderItem, which is the price of the item multiplied by the quantity,
     * minus the discount applied to the item multiplied by the quantity.
     * @return the subtotal of this OrderItem
     */
    public double getSubtotal() {
        return (item.getPrice() * quantity)-(item.getDiscount() * quantity);
    }

    /**
     * Returns a string representation of this OrderItem.
     * @return a string representation of this OrderItem
     */
    @Override
    public String toString() {
        return "OrderItem{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
