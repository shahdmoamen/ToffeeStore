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

    //itemid,itemname,description,,price,itemstockquantity,discount,brand,category

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

    // getters and setters for all fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Item changeQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand= brand;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category= category;
    }

    @Override
    public String toString() {
        return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", quantity="
                + quantity + ", discount=" + discount + ", brand=" + brand + ", category=" + category + "]";
    }
}
