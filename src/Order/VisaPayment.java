/**
 * The VisaPayment class represents a payment made using a Visa credit card.
 * It extends the Payment abstract class and implements its abstract methods.
 */
package Order;

import java.util.Scanner;
import java.util.regex.Pattern;

public class VisaPayment extends Payment {

    /** The card number associated with the Visa credit card. */
    private String cardNumber;

    /** The expiration date of the Visa credit card in the format MM/YY. */
    private String expirationDate;

    /** The CVV code of the Visa credit card. */
    private String cvv;

    /**
     * Creates a new VisaPayment object with the specified amount and card details.
     *
     * @param amount The amount to be paid.
     * @param cardNumber The card number associated with the Visa credit card.
     * @param expirationDate The expiration date of the Visa credit card in the format MM/YY.
     * @param cvv The CVV code of the Visa credit card.
     */
    public VisaPayment(double amount, String cardNumber, String expirationDate, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    /**
     * Creates a new VisaPayment object with the specified amount only.
     *
     * @param amount The amount to be paid.
     */
    public VisaPayment(double amount) {
        super(amount);
    }

    /**
     * Gets the card number associated with the Visa credit card.
     *
     * @return The card number associated with the Visa credit card.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the card number associated with the Visa credit card.
     *
     * @param cardNumber The card number associated with the Visa credit card.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets the expiration date of the Visa credit card in the format MM/YY.
     *
     * @return The expiration date of the Visa credit card in the format MM/YY.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the expiration date of the Visa credit card in the format MM/YY.
     *
     * @param expirationDate The expiration date of the Visa credit card in the format MM/YY.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Gets the CVV code of the Visa credit card.
     *
     * @return The CVV code of the Visa credit card.
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the CVV code of the Visa credit card.
     *
     * @param cvv The CVV code of the Visa credit card.
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * Returns the payment details for this VisaPayment object.
     *
     * @return The payment details for this VisaPayment object.
     */
    @Override
    public String getPaymentDetails() {
        return "Payment of " + this.amount + " made from Visa card " + this.cardNumber + ".";
    }

    /**
     * Processes a payment made using a Visa credit card.
     *
     * @param amount The amount to be paid.
     *
     * @return true if the payment was processed successfully, false otherwise.
     */
    @Override
    public boolean processPayment(double amount){
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the card number--make sure to write the 16 number --: ");
             cardNumber = sc.nextLine();
            System.out.print("Enter the expiration date (MM/YY): ");
            String expDate = sc.nextLine();
            System.out.print("Enter the CVV code: ");
            int cvv = sc.nextInt();
            sc.nextLine();
            if ((cardNumber.length() != 16) &&(!Pattern.matches("[0-9]+", cardNumber))) {
                System.out.println("Invalid card number.");
                return false;
            }
            if (!Pattern.matches("[0-9]{2}/[0-9]{2}", expDate)) {
                System.out.println("Invalid expiration date.");
                return false;
            }
            if (cvv < 100 || cvv > 999) {
                System.out.println("Invalid CVV.");
                return false;
            }
            System.out.println("Payment of " + this.amount + " made from Visa card " + this.cardNumber + ".");
            return true;
        }
    }

