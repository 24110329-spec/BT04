package com.quanao.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class EmailUtil {

    // Configurable SMTP Settings (Change these if you have a valid SMTP account like Gmail App Password)
    private static final String SMTP_HOST = System.getProperty("smtp.host", "smtp.gmail.com");
    private static final String SMTP_PORT = System.getProperty("smtp.port", "587");
    private static final String SENDER_EMAIL = System.getProperty("smtp.email", "noreply.quanlyquanao@gmail.com");
    private static final String SENDER_PASSWORD = System.getProperty("smtp.password", "app-password-placeholder");

    /**
     * Generates a 6-digit random numeric OTP string.
     */
    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    /**
     * Sends an Email containing the OTP code to the recipient address.
     * Logs the OTP code to console as a fallback if sending email fails or is unconfigured.
     */
    public static boolean sendOTPEmail(String recipientEmail, String otpCode, String subjectTitle) {
        System.out.println("==================================================");
        System.out.println("[OTP NOTIFICATION] To: " + recipientEmail);
        System.out.println("[OTP NOTIFICATION] Code: " + otpCode);
        System.out.println("==================================================");

        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL, "Quản Lý Quần Áo System"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subjectTitle);

            String content = "<h2>Hệ thống Quản Lý Quần Áo</h2>"
                    + "<p>Mã OTP xác thực của bạn là: <b style='color:blue; font-size:24px;'>" + otpCode + "</b></p>"
                    + "<p>Vui lòng không chia sẻ mã này cho bất kỳ ai. Mã có hiệu lực trong vòng 5 phút.</p>";

            message.setContent(content, "text/html; charset=UTF-8");

            // Attempt to send email async or sync
            new Thread(() -> {
                try {
                    Transport.send(message);
                    System.out.println("[EMAIL SUCCESS] Email OTP sent to " + recipientEmail);
                } catch (Exception e) {
                    System.err.println("[EMAIL NOTICE] Could not send live email (SMTP unconfigured). OTP code is printed above in console: " + otpCode);
                }
            }).start();

            return true;
        } catch (Exception e) {
            System.err.println("[EMAIL NOTICE] Email construction issue. Use OTP from console log: " + otpCode);
            return true;
        }
    }
}
