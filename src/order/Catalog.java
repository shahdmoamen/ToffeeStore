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
    @Override
    public String toString() {
        String s = "";
        for (Item item : items) {
            s += item.getId()+" "+item.getName() +" Price "+item.getPrice()+" Dicount "+item.getDiscount() +"\n";
        }return s;
    }
    //search for item by id
    public Item searchItemById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }
}
