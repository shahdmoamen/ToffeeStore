package users;
import java.util.Scanner;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendOTP {
    public static boolean SendOTP(String email, int code) {
        String host = "smtp.gmail.com";
        String username = "fcai.toffeeshop@gmail.com";
        String password = "dfpzbhgihyfxtbjp";
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
            throw new RuntimeException(e);
        }
    }

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
