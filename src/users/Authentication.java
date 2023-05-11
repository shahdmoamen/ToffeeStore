package users;
import java.util.ArrayList;
import dataManagement.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Authentication {
private  ArrayList<Customer> customers;
public Authentication(ArrayList<Customer> customers){
    this.customers=customers;}
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
}

    public boolean login(String email, String password) {
    DataManager dataManager = new DataManager();
        ArrayList<Customer> customers = dataManager.loadCustomers();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(Customer customer) {
        DataManager dataManager = new DataManager();
        ArrayList<Customer> customers = dataManager.loadCustomers();
        for (Customer c : customers) {
            if (c.getEmail().equals(customer.getEmail())) {
                return false;
            }
        }
        customers.add(customer);
        dataManager.saveCustomers(customers);
        return true;
    }

}
