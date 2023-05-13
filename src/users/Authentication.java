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
        ArrayList<Admin> admins = dataManager.loadAdmins();
        if (!customer.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            System.out.println("Invalid email format");
            return false;
        }
        //regex for phone number
        if (!customer.getPhoneNumber().matches("^01[0125][0-9]{8}$")) {
            System.out.println("Invalid phone number");
            return false;
        }
        for (Customer c : customers) {
            if (c.getEmail().equals(customer.getEmail())) {
                System.out.println("Email already exists");
                return false;
            }
        }
        for (Admin a : admins) {
            if ((a.getEmail().equals(admin.getEmail()))) {
                System.out.println("Email already exists");
                return false;
            }
        }
            if(!sendotp.sendVerificationCode(customer)){
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
        ArrayList<Customer> customers = dataManager.loadCustomers();
        if (!admin.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            System.out.println("Invalid email format");
            return false;
        }
        if (!customer.getPhoneNumber().matches("^^01[0125][0-9]{8}$")) {
            System.out.println("Invalid phone number");
            return false;
        }
        for (Admin a : admins) {
            if ((a.getEmail().equals(admin.getEmail()))) {
                System.out.println("Email already exists");
                return false;
            }
            for (Customer c : customers) {
                if (c.getEmail().equals(admin.getEmail())) {
                    System.out.println("Email already exists");
                    return false;
                }
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