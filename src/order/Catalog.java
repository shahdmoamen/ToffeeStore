/**
 * The Catalog class represents a catalog of items available in the system.
 * It provides methods for adding and removing items from the catalog, as well as retrieving all the items.
 */
package order;

import dataManagement.DataManager;
import users.Customer;

import java.util.ArrayList;

public class Catalog {
    private ArrayList<Item> items;

    /**
     * Creates a new Catalog object with the given list of items.
     *
     * @param items the list of items to add to the catalog.
     */
    public Catalog(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Adds an item to the catalog.
     *
     * @param item the item to add to the catalog.
     */
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

    /**
     * Removes an item from the catalog.
     *
     * @param item the item to remove from the catalog.
     */
    public void removeItem(Item item) {
        DataManager dataManager = new DataManager();
        ArrayList<Item> items = dataManager.loadItems();
        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                items.remove(i);
                System.out.println("Item removed successfully");
                return;
            }
        }
        dataManager.saveItems(items);
    }

    /**
     * Returns the list of all items in the catalog.
     *
     * @return the list of all items in the catalog.
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Returns a string representation of the catalog.
     *
     * @return a string representation of the catalog.
     */
    @Override
    public String toString() {
        String result = "ID->NAME->PRICE->DISCOUNT\n";

        for (Item item : items) {
            result += item.getId() + "->" + item.getName() + "->" + item.getPrice() + "L.E->" + item.getDiscount() + "L.E ";

            if (item.getQuantity() < 2) {
                result += "!!!!Hurry up! Only " + item.getQuantity() + " left in stock!!!!" + "\n";
            } else {
                result += "\n";
            }
        }

        return result;
    }

    /**
     * Searches for an item in the catalog with the specified ID.
     *
     * @param id the ID of the item to search for.
     * @return the item with the specified ID, or null if the item is not found.
     */
    public Item searchItemById(String id) {
        for (Item item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null;
    }
    /**
     * Searches for an item in the catalog with the specified name.
     * @param name the name of the item to search for.
     * @return the item with the specified name, or null if the item is not found.
     */
    public Item searchItemByName(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
}