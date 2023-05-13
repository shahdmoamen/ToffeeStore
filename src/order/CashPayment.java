package order;

/**
 * A class representing a cash payment made for an order.
 */
public class CashPayment extends Payment {
    private double cashTendered;

    /**
     * Constructs a new cash payment object with the given amount and cash tendered.
     *
     * @param amount the total amount due for the order
     * @param cashTendered the amount of cash tendered by the customer
     */
    public CashPayment(double amount, double cashTendered) {
        super(amount);
        this.cashTendered = cashTendered;
    }

    /**
     * Returns the amount of cash tendered for the payment.
     *
     * @return the amount of cash tendered
     */
    public double getCashTendered() {
        return cashTendered;
    }

    /**
     * Sets the amount of cash tendered for the payment.
     *
     * @param cashTendered the new amount of cash tendered
     */
    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }

    /**
     * Returns a string describing the details of the cash payment.
     *
     * @return a string describing the payment details
     */
    @Override
    public String getPaymentDetails() {
        return "Payment of " + this.amount + " made in cash.";
    }

    /**
     * Processes the cash payment for the given amount.
     *
     * @param amount the total amount due for the order
     * @return true if the payment is successful, false otherwise
     */
    @Override
    public boolean processPayment(double amount){
        if (cashTendered < amount) {
            System.out.println("Cash tendered is less than the amount due. Payment failed.");
            return false;
        }

        double change = cashTendered - amount;
        System.out.println("Payment successful. Change: " + change);
        return true;
    }
}
