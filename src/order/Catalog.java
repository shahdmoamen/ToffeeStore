package order;

import dataManagement.DataManager;
import users.Customer;

import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items;
    public Catalog(ArrayList<Item> items) {
        this.items = items;

    }
    public void addItemToCatalog(Item item) {
            DataManager dataManager = new DataManager();
            ArrayList<Item> items = dataManager.loadItems();
            for (Item i : items) {
                if (i.getName().equals(item.getName())) {
                    i.changeQuantity(item.getQuantity() + i.getQuantity());
                    dataManager.saveItems(items);
                    System.out.println("Item quantity updated successfully");
                    return;
                }
            }
            items.add(item);
            dataManager.saveItems(items);
            System.out.println("Item added successfully");
        }
    public void removeItem(Item item) {
        DataManager dataManager = new DataManager();
        ArrayList<Item> items = dataManager.loadItems();
        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                i.setQuantity(i.getQuantity() - item.getQuantity());
                dataManager.saveItems(items);
                System.out.println("Item removed successfully");
            }
        }
    }
    public ArrayList<Item> getItems() {
        return items;
    }
    public void displayCatalog() {
        for (Item item : items) {
            System.out.println(item.getName()+" "+item.getPrice()+" L.E "+item.getDiscount());
        }
    }
}
