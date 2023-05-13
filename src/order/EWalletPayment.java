package order;

public class EWalletPayment extends Payment {
    private String eWalletId;
    private String eWalletPassword;

    public EWalletPayment(double amount, String eWalletId, String eWalletPassword) {
        super(amount);
        this.eWalletId = eWalletId;
        this.eWalletPassword = eWalletPassword;
    }
public EWalletPayment(double amount) {
        super(amount);
    }
    public String getEWalletId() {
        return eWalletId;
    }

    public void setEWalletId(String eWalletId) {
        this.eWalletId = eWalletId;
    }

    public String getEWalletPassword() {
        return eWalletPassword;
    }

    public void setEWalletPassword(String eWalletPassword) {
        this.eWalletPassword = eWalletPassword;
    }
    @Override
    public String getPaymentDetails() {
        return "Payment of " + this.amount + " made from e-wallet account " + this.eWalletId + ".";
    }

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