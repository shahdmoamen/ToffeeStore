package users;
import java.util.ArrayList;
import dataManagement.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Authentication {
    Customer customer;
    Admin admin;
private  ArrayList<Customer> customers;
public Authentication(ArrayList<Customer> customers){
    this.customers=customers;}
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
}

    public boolean loginCustomer(String email, String password) {
    DataManager dataManager = new DataManager();
        ArrayList<Customer> customers = dataManager.loadCustomers();
        ArrayList<Admin> admins = dataManager.loadAdmins();
        for (Customer c : customers) {
            if (c.getEmail().equals(email) && c.getPassword().equals(password)) {
                customer = c;
                return true;
            }
        }
        return false;
    }
    public boolean loginAdmin(String email, String password) {
        DataManager dataManager = new DataManager();
        ArrayList<Customer> customers = dataManager.loadCustomers();
        ArrayList<Admin> admins = dataManager.loadAdmins();
        for (Admin a : admins) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                admin = a;
                return true;
            }
        }
        return false;
    }
    public boolean registerCustomer(Customer customer) {
    SendOTP sendotp=new SendOTP();
        DataManager dataManager = new DataManager();
        ArrayList<Customer> customers = dataManager.loadCustomers();
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
    public boolean registerAdmin(Admin admin) {
        SendOTP sendotp=new SendOTP();
        DataManager dataManager = new DataManager();
        ArrayList<Admin> admins = dataManager.loadAdmins();
        //check if  email is already is in the write format
        if (!admin.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            System.out.println("Invalid email format");
            return false;
        }
        for (Admin a : admins) {
            if (a.getEmail().equals(admin.getEmail())) {
                return false;
            }
        }if(!sendotp.sendVerificationCode(admin)){
            return false;
        }
        admins.add(admin);
        dataManager.saveAdmins(admins);
        return true;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Admin getAdmin() {
        return admin;
    }


}