package order;

public class Item {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private double discount;
    private String brand;
    private String category;

    /**
     * Constructs an Item object with the specified properties.
     *
     * @param id          the ID of the item
     * @param name        the name of the item
     * @param description the description of the item
     * @param price       the price of the item
     * @param quantity    the quantity of the item in stock
     * @param discount    the discount applied to the item
     * @param brand       the brand of the item
     * @param category    the category of the item
     */
    public Item(String id, String name, String description, double price, int quantity, double discount, String brand, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
        this.brand = brand;
        this.category = category;
    }

    /**
     * Returns the ID of the item.
     *
     * @return the ID of the item
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of the item.
     *
     * @param id the ID of the item
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item.
     *
     * @param name the name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the item.
     *
     * @return the description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the item.
     *
     * @param description the description of the item
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item.
     *
     * @param price the price of the item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Changes the quantity of the item in stock.
     *
     * @param quantity the new quantity of the item in stock
     * @return the updated Item object
     */
    public Item changeQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Returns the quantity of the item in stock.
     *
     * @return the quantity of the item in stock
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the item in stock.
     *
     * @param quantity the quantity of the item in stock
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the discount applied to the item.
     *
     * @return the discount applied to the item
     */
    public double getDiscount() {
        return discount;
    }
    /**
    * Sets the discount applied to the item.
     *
     * @param discount the discount applied to the item
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    /**
     * Returns the brand of the item.
     *
     * @return the brand of the item
     */
    public String getBrand() {
        return brand;
    }
    /**
     * Sets the brand of the item.
     *
     * @param brand the brand of the item
     */
    public void setBrand(String brand) {
        this.brand= brand;
    }
    /**
     * Returns the category of the item.
     *
     * @return the category of the item
     */
    public String getCategory() {
        return category;
    }
    /**
     * Sets the category of the item.
     *
     * @param category the category of the item
     */
    public void setCategory(String category) {
        this.category= category;
    }

    /**
     * @return a string representation of the Item object
     */
    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity="
                + quantity + ", discount=" + discount + ", brand=" + brand + ", category=" + category + "]";
    }
}
