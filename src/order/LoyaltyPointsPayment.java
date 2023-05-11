//package order;
//
//public class LoyaltyPointsPayment extends Payment {
//    private int pointsToRedeem;
//
//    public LoyaltyPointsPayment(double amount, int pointsToRedeem) {
//        super(amount);
//        this.pointsToRedeem = pointsToRedeem;
//    }
//    public int getPointsToRedeem() {
//        return pointsToRedeem;
//    }
//
//    @Override
//    public boolean processPayment() {
//        if (UserManager.getCurrentUser() == null) {
//            System.out.println("Error: No user logged in.");
//            return false;
//        }
//
//        User currentUser = UserManager.getCurrentUser();
//        int currentPoints = currentUser.getLoyaltyPoints();
//
//        if (currentPoints < pointsToRedeem) {
//            System.out.println("Error: Insufficient loyalty points.");
//            return false;
//        }
//
//        double totalAmount = this.getAmount();
//        double pointsValue = pointsToRedeem * 0.1; // Assume 1 point = $0.10
//        if (pointsValue > totalAmount) {
//            System.out.println("Error: Loyalty points value exceeds total amount.");
//            return false;
//        }
//
//        // Update user's loyalty points balance
//        currentUser.setLoyaltyPoints(currentPoints - pointsToRedeem);
//
//        // Reduce the total amount by the value of redeemed loyalty points
//        totalAmount -= pointsValue;
//
//        // Record the transaction in the data manager
//        DataManager.getInstance().addTransaction(new Transaction("Loyalty Points Payment", totalAmount));
//
//        System.out.println("Payment successful: $" + totalAmount + " paid with " + pointsToRedeem + " loyalty points.");
//        return true;
//    }
//    @Override
//    public String toString() {
//        return "LoyaltyPointsPayment{" +
//                "loyaltyPoints=" + loyaltyPoints +
//                ", amount=" + amount +
//                '}';
//    }
//}
//
