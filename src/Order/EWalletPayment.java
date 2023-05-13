package Order;

/**
 * This class represents an electronic wallet payment for an order.
 */
public class EWalletPayment extends Payment {
    private String eWalletId;
    private String eWalletPassword;

    /**
     * Constructs a new EWalletPayment object with the given amount and e-wallet credentials.
     *
     * @param amount the amount to be paid
     * @param eWalletId the ID of the e-wallet account
     * @param eWalletPassword the password of the e-wallet account
     */
    public EWalletPayment(double amount, String eWalletId, String eWalletPassword) {
        super(amount);
        this.eWalletId = eWalletId;
        this.eWalletPassword = eWalletPassword;
    }

    /**
     * Constructs a new EWalletPayment object with the given amount.
     *
     * @param amount the amount to be paid
     */
    public EWalletPayment(double amount) {
        super(amount);
    }

    /**
     * Returns the ID of the e-wallet account.
     *
     * @return the ID of the e-wallet account
     */
    public String getEWalletId() {
        return eWalletId;
    }

    /**
     * Sets the ID of the e-wallet account.
     *
     * @param eWalletId the ID of the e-wallet account
     */
    public void setEWalletId(String eWalletId) {
        this.eWalletId = eWalletId;
    }

    /**
     * Returns the password of the e-wallet account.
     *
     * @return the password of the e-wallet account
     */
    public String getEWalletPassword() {
        return eWalletPassword;
    }

    /**
     * Sets the password of the e-wallet account.
     *
     * @param eWalletPassword the password of the e-wallet account
     */
    public void setEWalletPassword(String eWalletPassword) {
        this.eWalletPassword = eWalletPassword;
    }

    /**
     * Returns a string representation of the payment details.
     *
     * @return a string representation of the payment details
     */
    @Override
    public String getPaymentDetails() {
        return "Payment of " + this.amount + " made from e-wallet account " + this.eWalletId + ".";
    }

    /**
     * Processes the payment and returns whether it was successful or not.
     *
     * @param amount the amount to be paid
     * @return true if the payment was successful, false otherwise
     */
    @Override
    public boolean processPayment(double amount){
        if (this.eWalletId == null || this.eWalletPassword == null) {
            System.out.println("Error: account ID and password are required for e-wallet payment.");
            return false;
        }
        System.out.println("Payment of " + this.amount + " made from e-wallet account " + this.eWalletId + ".");
        return true;
    }
}
