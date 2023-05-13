package order;
import java.util.Scanner;
import java.util.regex.Pattern;

public class VisaPayment extends Payment {
    private String cardNumber;
    private String expirationDate;
    private String cvv;

    public VisaPayment(double amount, String cardNumber, String expirationDate, String cvv) {
        super(amount);
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }public VisaPayment(double amount) {
        super(amount);
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
    public String getPaymentDetails() {
        return "Payment of " + this.amount + " made from Visa card " + this.cardNumber + ".";
    }

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

