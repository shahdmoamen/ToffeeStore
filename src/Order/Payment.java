/**
 * An abstract class representing a payment made for an order.
 */
package Order;

public abstract class Payment {
    /**
     * The amount of the payment.
     */
    protected double amount;

    /**
     * Creates a new payment with the specified amount.
     *
     * @param amount the amount of the payment
     */
    public Payment(double amount) {
        this.amount = amount;
    }

    /**
     * Returns the amount of the payment.
     *
     * @return the amount of the payment
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the payment.
     *
     * @param amount the new amount of the payment
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Processes the payment for the specified amount.
     *
     * @param amount the amount to be processed
     * @return true if the payment was successful, false otherwise
     */
    public abstract boolean processPayment(double amount);

    /**
     * Returns a string representation of the payment details.
     *
     * @return a string representation of the payment details
     */
    public abstract String getPaymentDetails();
}