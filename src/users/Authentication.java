/**

 The Authentication class is responsible for authenticating users, registering new customers and admins,
 and providing access to the corresponding customer or admin object once they are authenticated.
 The class maintains an ArrayList of all customers that have been registered.
 **/
 package users;
 import java.util.ArrayList;
 import dataManagement.*;
 import java.util.Scanner;
 public class Authentication {
 Customer customer;
 Admin admin;
 private ArrayList<Customer> customers;
     /**
      * Constructor for the Authentication class.
      * @param customers an ArrayList of Customer objects
      */
     public Authentication(ArrayList<Customer> customers){
         this.customers = customers;
     }

     /**
      * Setter method for the customers ArrayList.
      * @param customers an ArrayList of Customer objects
      */
     public void setCustomers(ArrayList<Customer> customers) {
         this.customers = customers;
     }

     /**
      * Getter method for the admin object.
      * @return the admin object
      */
     public Admin getAdmin() {
         return admin;
     }
     /**
      * Setter method for the admin object.
      * @param admin  the admin object
      */
     public void setAdmin(Admin admin) {
         this.admin = admin;
     }
     /**
      * Getter method for the customer object.
      * @return the customer object
      */
     public Customer getCustomer() {
         return customer;
     }
     /**
      * Setter method for the customer object.
      * @param customer the customer object
      */
     public void setCustomer(Customer customer) {
         this.customer = customer;
     }

     /**
      * Getter method for the customers ArrayList.
      * @return an ArrayList of Customer objects
      */
     public ArrayList<Customer> getCustomers() {
         return customers;
     }

     /**
      * Authenticates a customer with the given email and password.
      * @param email the customer's email
      * @param password the customer's password
      * @return true if the authentication is successful, false otherwise
      */
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

     /**
      * Authenticates an admin with the given email and password.
      * @param email the admin's email
      * @param password the admin's password
      * @return true if the authentication is successful, false otherwise
      */
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

     /**
      * Registers a new customer with the given customer object.
      * @param customer a Customer object representing the new customer
      * @return true if the registration is successful, false otherwise
      */
     public boolean registerCustomer(Customer customer) {
         SendOTP sendotp = new SendOTP();
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
         if (!sendotp.sendVerificationCode(customer)) {
             return false;
         }
         customers.add(customer);
         dataManager.saveCustomers(customers);
         return true;
     }

     /**
      * Registers a new Admin in the system and saves their information to the data manager.
      * Sends a verification code to the Admin's email address for confirmation.
      *
      * @param admin The Admin to be registered.
      * @return True if registration is successful, False otherwise.
      */
     public boolean registerAdmin(Admin admin) {
         SendOTP sendotp=new SendOTP();
         DataManager dataManager = new DataManager();
         ArrayList<Admin> admins = dataManager.loadAdmins();
         ArrayList<Customer> customers = dataManager.loadCustomers();

         // Check if the email format is valid.
         if (!admin.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
             System.out.println("Invalid email format");
             return false;
         }

         // Check if the email already exists for an Admin or a Customer.
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
         }

         // Send verification code to Admin's email.
         if(!sendotp.sendVerificationCode(admin)){
             return false;
         }

         // Add the new Admin to the list of admins and save to data manager.
         admins.add(admin);
         dataManager.saveAdmins(admins);
         return true;
     }



}