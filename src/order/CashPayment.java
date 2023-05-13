package order;


public class CashPayment extends Payment {
    private double cashTendered;

    public CashPayment(double amount, double cashTendered) {
        super(amount);
        this.cashTendered = cashTendered;
    }

    public double getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }
    @Override
    public String getPaymentDetails() {
        return "Payment of " + this.amount + " made in cash.";
    }

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