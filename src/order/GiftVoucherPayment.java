//package order;
//import dataManagement.DataManager;
//public class GiftVoucherPayment extends Payment {
//    private String voucherCode;
//
//    public GiftVoucherPayment(double amount, String voucherCode) {
//        super(amount);
//        this.voucherCode = voucherCode;
//    }
//
//    public String getVoucherCode() {
//        return voucherCode;
//    }
//
//    public void setVoucherCode(String voucherCode) {
//        this.voucherCode = voucherCode;
//    }
//    @Override
//    public boolean processPayment() {
//        // Check if voucher code is valid and has enough balance
//        if (checkVoucherBalance(voucherCode, getAmount())) {
//            // Process payment by deducting amount from voucher balance
//            deductFromVoucherBalance(voucherCode, getAmount());
//            return true;
//        }
//        return false;
//    }
//
//    // Function to check if voucher code is valid and has enough balance
//    private boolean checkVoucherBalance(String voucherCode, double amount) {
//        // Check voucher code against list of valid voucher codes and get its balance
//        double voucherBalance = getVoucherBalance(voucherCode);
//
//        // If voucher code is valid and has enough balance, return true
//        if (voucherBalance >= amount) {
//            return true;
//        }
//        return false;
//    }
//
//    // Function to deduct payment amount from voucher balance
//    private void deductFromVoucherBalance(String voucherCode, double amount) {
//        // Get voucher balance and deduct payment amount
//        double voucherBalance = getVoucherBalance(voucherCode);
//        voucherBalance -= amount;
//
//        // Update voucher balance in database
//        updateVoucherBalance(voucherCode, voucherBalance);
//    }
//
//    // Function to get voucher balance from database
//    private double getVoucherBalance(String voucherCode) {
//        // Get voucher balance from database and return it
//        return DataManager.getVoucherBalance(voucherCode);
//    }
//
//    // Function to update voucher balance in database
//    private void updateVoucherBalance(String voucherCode, double newBalance) {
//        // Update voucher balance in database
//        DataManager.updateVoucherBalance(voucherCode, newBalance);
//    }
//}
//    @Override
//    public String toString() {
//        return "GiftVoucherPayment{" +
//                "voucherCode='" + voucherCode + '\'' +
//                ", amount=" + amount +
//                '}';
//    }
//}
