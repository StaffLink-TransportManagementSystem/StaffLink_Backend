package Model;

import Database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class OTPService {

    public static String generateAndStoreOTP(String email) {
        // Generate a random 6-digit OTP
        String otp = generateOTP();

        // Store OTP in the database
        storeOTPInDatabase(email, otp);

        return otp;
    }

    private static String generateOTP() {
        // Generate a random 6-digit OTP
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private static void storeOTPInDatabase(String email, String otp) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO otp_storage (email, otp) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, otp);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getStoredOTP(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String storedOTP = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "SELECT otp FROM otp_storage WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                storedOTP = resultSet.getString("otp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return storedOTP;
    }

    public static void deleteStoredOTP(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            String sql = "DELETE FROM otp_storage WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendOTPByEmail(String email, String otp) {
        // Email configurations
        String host = "smtp.gmail.com"; // Change this if using a different SMTP server
        String from = "your_email@gmail.com"; // Change to your email
        String password = "your_password"; // Change to your password

        // Email properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        // Get Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("OTP Verification");

            // Set Content: text
            message.setText("Your OTP for password reset is: " + otp);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
