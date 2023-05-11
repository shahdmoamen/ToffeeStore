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
    SendOTP sendotp=new SendOTP();
        DataManager dataManager = new DataManager();
        ArrayList<Customer> customers = dataManager.loadCustomers();
        //check if  email is already is in the write format
        if (!customer.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            System.out.println("Invalid email format");
            return false;
        }
        for (Customer c : customers) {
            if (c.getEmail().equals(customer.getEmail())) {
                return false;
            }
        }if(!sendotp.sendVerificationCode(customer)){
            return false;
        }
        customers.add(customer);
        dataManager.saveCustomers(customers);
        return true;
    }


}
