package DAO;

import Database.DBConnection;
import Model.PassengerPrice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.Date;

public class PassengerPrinceDAO {
    public static boolean setPassengerPrice(PassengerPrice passengerPrice){
        System.out.println("Hello from PassengerPrinceDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;
        try {
            String sql = "INSERT INTO passengerprice (Date,normalPrice, acPrice, luxuryPrice) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, today());
            preparedStatement.setDouble(2, passengerPrice.getNormalPrice());
            preparedStatement.setDouble(3, passengerPrice.getAcPrice());
            preparedStatement.setDouble(4, passengerPrice.getLuxuryPrice());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return success;
    }

    public static String today() {
        // Get the current date and time
        Date currentDate = new Date();


        // If you need only the current date without the time information
        Calendar calendar = Calendar.getInstance();
        Date currentDateWithoutTime = getStartOfDay(calendar.getTime());

        // Print the current date without time
        System.out.println("Current Date without Time: " + currentDateWithoutTime);
        return currentDateWithoutTime.toString();
    }

    // Utility method to set the time to the start of the day
    private static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Set the time to 00:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static PassengerPrice getPassengerPrice(){
        System.out.println("Hello from PassengerPrinceDAO");
        Connection connection = DBConnection.getInstance().getConnection();
        PassengerPrice passengerPrice = new PassengerPrice();
        try {
            String sql = "SELECT * FROM passengerprice WHERE Date = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, today());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return passengerPrice;
    }
    public static boolean deletePassengerPrice(PassengerPrice passengerPrice) {
        System.out.println("Inside deletePassengerPrice");
        Connection connection = DBConnection.getInstance().getConnection();
        boolean success = false;

        try {
            String sql = "DELETE FROM passengerprice WHERE Date = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, today());
            preparedStatement.executeUpdate();
            success = true;
        } catch (Exception e) {
            System.out.println(e);
        }

        return success;
    }
}
