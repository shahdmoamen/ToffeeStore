package order;
import java.util.Scanner;

public class VisaPayment extends Payment {
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public VisaPayment(double amount, String cardNumber, String expirationDate, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount){
            System.out.println("Processing Visa payment...");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the card number: ");
            String cardNumber = sc.nextLine();
            System.out.print("Enter the expiration date (MM/YY): ");
            String expDate = sc.nextLine();
            System.out.print("Enter the CVV code: ");
            int cvv = sc.nextInt();
            sc.nextLine();
            if (cardNumber.length() != 16) {
                System.out.println("Invalid card number.");
                return false;
            }
            if (expDate.length() != 5) {
                System.out.println("Invalid expiration date.");
                return false;
            }
            if (cvv < 100 || cvv > 999) {
                System.out.println("Invalid CVV.");
                return false;
            }

            if (amount > 0) {
                System.out.printf("Payment of $%.2f successfully processed using Visa card %s.\n", amount, cardNumber);
            } else {
                System.out.println("Invalid amount.");
            }
            return true;
        }
    }

