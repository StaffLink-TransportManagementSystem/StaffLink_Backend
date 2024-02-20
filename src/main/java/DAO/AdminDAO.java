package DAO;

import Database.DBConnection;
import Model.AdminModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO
{
    public static AdminModel getAdmin(String email) {


        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        AdminModel admin = new AdminModel();

        try {
            String sql = "SELECT * FROM admins WHERE email = ? AND deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setEmail(resultSet.getString("email"));
                admin.setNIC(resultSet.getString("NIC"));
                admin.setContactNo(resultSet.getString("contact"));
                admin.setName(resultSet.getString("name"));
                admin.setPassword(resultSet.getString("password"));
            }

            resultSet.close();
            preparedStatement.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    public static boolean createAdmin(AdminModel admin) {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        System.out.println(admin.getContactNo());
        boolean success = false;
        try {
            System.out.println("try");
            String sql = "INSERT INTO admins (name,email,NIC,contact,password) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getNIC());
            preparedStatement.setString(4, admin.getContactNo());
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()) {
                success = true;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public static boolean updateAdmin(AdminModel admin) {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        boolean success = false;
        try {
            System.out.println("try");
            String sql = "UPDATE admins SET name = ?, email = ?, NIC = ?, contact = ?, password = ? WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, admin.getName());
            preparedStatement.setString(2, admin.getEmail());
            preparedStatement.setString(3, admin.getNIC());
            preparedStatement.setString(4, admin.getContactNo());
            preparedStatement.setString(5, admin.getPassword());
            preparedStatement.setString(6, admin.getEmail());

            if(preparedStatement.executeUpdate() > 0) {
                success = true;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public static boolean deleteAdmin(AdminModel admin) {
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        boolean success = false;
        try {
            System.out.println("try");
            String sql = "UPDATE admins SET deleteState = 1 WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, admin.getEmail());

            if(preparedStatement.executeUpdate() > 0) {
                success = true;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public static List<AdminModel> viewAllAdmins(){
        Connection connection = DBConnection.getInstance().getConnection();
        System.out.println("Inside CO");
        List <AdminModel> adminList = new ArrayList<>();
        AdminModel admin = new AdminModel();
        try {
            String sql = "SELECT * FROM admins WHERE deleteState = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                admin.setId(resultSet.getInt("id"));
                admin.setEmail(resultSet.getString("email"));
                admin.setNIC(resultSet.getString("NIC"));
                admin.setContactNo(resultSet.getString("contact"));
                admin.setName(resultSet.getString("name"));
                admin.setPassword(resultSet.getString("password"));
                adminList.add(admin);
            }

            resultSet.close();
            preparedStatement.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return adminList;

    }
}
