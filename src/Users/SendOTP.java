package Users;
import java.util.Scanner;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**

 A utility class to send OTP (One-Time Password) to users' email addresses.
 */
public class SendOTP {

    /**
     * Sends OTP to a user's email address.
     *
     * @param email The email address to send the OTP to.
     * @param code  The OTP code to send.
     * @return True if the OTP was sent successfully, false otherwise.
     */
    public static boolean SendOTP(String email, int code) {
        String host = "smtp.gmail.com";
        String username = "toffeestore.fcai@gmail.com";
        String password = "wlmhyuqthpimzigk";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            message.setSubject("TOFFEE SHOP VERIFICATION CODE");
            message.setText("Your OTP IS : " + code);

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            System.out.println("Failed to send email");
            return false;
        }
    }

    /**
     * This method sends a verification code to the email of the given customer and verifies it.
     *
     * @param customer the customer object whose email address is used for verification
     * @return true if the email was sent and the verification code was entered correctly, false otherwise
     */
    public boolean sendVerificationCode(Customer customer) {
        boolean isEmailSend = false;
        SendOTP sendOtp = new SendOTP();
        int code = (int) (Math.random() * 900000) + 100000;
        if (sendOtp.SendOTP(customer.getEmail(), code)) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Please enter the verification code sent to your email or enter 0 to cancel:");
                int inputCode = scanner.nextInt();
                if (inputCode == code) {
                    isEmailSend = true;
                    System.out.println("Email verified successfully.");
                    break;
                } else if (inputCode == 0) {
                    break;
                } else {
                    System.out.println("Incorrect verification code. Please try again.");
                    isEmailSend = false;
                }
            }
        }
        return isEmailSend;
    }

    /**
     * This method sends a verification code to the email of the given admin and verifies it.
     *
     * @param admin the admin object whose email address is used for verification
     * @return true if the email was sent and the verification code was entered correctly, false otherwise
     */
    public boolean sendVerificationCode(Admin admin) {
        boolean isEmailSend = false;
        SendOTP sendOtp = new SendOTP();
        int code = (int) (Math.random() * 900000) + 100000;
        if (sendOtp.SendOTP(admin.getEmail(), code)) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Please enter the verification code sent to your email or enter 0 to cancel:");
                int inputCode = scanner.nextInt();
                if (inputCode == code) {
                    isEmailSend = true;
                    System.out.println("Email verified successfully.");
                    break;
                } else if (inputCode == 0) {
                    break;
                } else {
                    System.out.println("Incorrect verification code. Please try again.");
                    isEmailSend = false;
                }
            }
        }
        return isEmailSend;
    }
}