/**
 * Admin class is a subclass of User class and represents an admin user in the system.
 */
package Users;

public class Admin extends User{

    /**
     * Constructor for Admin class.
     *
     * @param id the id of the admin user
     * @param username the username of the admin user
     * @param password the password of the admin user
     * @param name the name of the admin user
     * @param email the email of the admin user
     */
    public Admin(String id, String username, String password, String name, String email) {
        super(id ,username, password, name, email);
    }
}