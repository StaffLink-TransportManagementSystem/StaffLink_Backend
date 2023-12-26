package DAO;

import Database.DBConnection;
import Model.VehicleModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VehicleImageDAO {
    public boolean insertVehicleImages(VehicleModel vehicleModel){
        Connection connection = DBConnection.getInstance().getConnection();

        try{
            String query = "INSERT INTO vehicle_images(front_image, back_image, side_image, inside_image, certificate, insurance, front_image_type, back_image_type, side_image_type, inside_image_type, certificate_type, insurance_type, vehicle_no, deleteStatus) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicleModel.getFrontImage());
            preparedStatement.setString(2, vehicleModel.getBackImage());
            preparedStatement.setString(3, vehicleModel.getSideImage());
            preparedStatement.setString(4, vehicleModel.getInsideImage());
            preparedStatement.setString(5, vehicleModel.getCertificate());
            preparedStatement.setString(6, vehicleModel.getInsurance());
            preparedStatement.setString(7, vehicleModel.getFrontImageType());
            preparedStatement.setString(8, vehicleModel.getBackImageType());
            preparedStatement.setString(9, vehicleModel.getSideImageType());
            preparedStatement.setString(10, vehicleModel.getInsideImageType());
            preparedStatement.setString(11, vehicleModel.getCertificateType());
            preparedStatement.setString(12, vehicleModel.getInsuranceType());
            preparedStatement.setString(13, vehicleModel.getVehicleNo());
            preparedStatement.setInt(14, 0);

            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVehicleImages(VehicleModel vehicleModel){
        Connection connection = DBConnection.getInstance().getConnection();

        try{
            String query = "UPDATE vehicle_images SET front_image = ?, back_image = ?, side_image = ?, inside_image = ?, certificate = ?, insurance = ?, front_image_type = ?, back_image_type = ?, side_image_type = ?, inside_image_type = ?, certificate_type = ?, insurance_type = ? WHERE vehicle_no = ? AND deleteStatus = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicleModel.getFrontImage());
            preparedStatement.setString(2, vehicleModel.getBackImage());
            preparedStatement.setString(3, vehicleModel.getSideImage());
            preparedStatement.setString(4, vehicleModel.getInsideImage());
            preparedStatement.setString(5, vehicleModel.getCertificate());
            preparedStatement.setString(6, vehicleModel.getInsurance());
            preparedStatement.setString(7, vehicleModel.getFrontImageType());
            preparedStatement.setString(8, vehicleModel.getBackImageType());
            preparedStatement.setString(9, vehicleModel.getSideImageType());
            preparedStatement.setString(10, vehicleModel.getInsideImageType());
            preparedStatement.setString(11, vehicleModel.getCertificateType());
            preparedStatement.setString(12, vehicleModel.getInsuranceType());
            preparedStatement.setString(13, vehicleModel.getVehicleNo());

            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVehicleImages(String vehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();

        try{
            String query = "UPDATE vehicle_images SET deleteStatus = 1 WHERE vehicle_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicleNo);

            preparedStatement.executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<VehicleModel> getVehicleImages(String vehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();
        List<VehicleModel> vehicleImages = new ArrayList<>();

        try{
            String query = "SELECT * FROM vehicle_images WHERE vehicle_no = ? AND deleteStatus = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                VehicleModel vehicleModel = new VehicleModel();
                vehicleModel.setFrontImage(resultSet.getString(2));
                vehicleModel.setBackImage(resultSet.getString(3));
                vehicleModel.setSideImage(resultSet.getString(4));
                vehicleModel.setInsideImage(resultSet.getString(5));
                vehicleModel.setCertificate(resultSet.getString(6));
                vehicleModel.setInsurance(resultSet.getString(7));
                vehicleModel.setFrontImageType(resultSet.getString(8));
                vehicleModel.setBackImageType(resultSet.getString(9));
                vehicleModel.setSideImageType(resultSet.getString(10));
                vehicleModel.setInsideImageType(resultSet.getString(11));
                vehicleModel.setCertificateType(resultSet.getString(12));
                vehicleModel.setInsuranceType(resultSet.getString(13));
                vehicleModel.setVehicleNo(resultSet.getString(14));

                vehicleImages.add(vehicleModel);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return vehicleImages;
        }
    }

    public VehicleModel getVehicleImage(String vehicleNo){
        Connection connection = DBConnection.getInstance().getConnection();
        VehicleModel vehicleImages = new VehicleModel();

        try{
            String query = "SELECT * FROM vehicle_images WHERE vehicle_no = ? AND deleteStatus = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, vehicleNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                vehicleImages.setFrontImage(resultSet.getString(2));
                vehicleImages.setBackImage(resultSet.getString(3));
                vehicleImages.setSideImage(resultSet.getString(4));
                vehicleImages.setInsideImage(resultSet.getString(5));
                vehicleImages.setCertificate(resultSet.getString(6));
                vehicleImages.setInsurance(resultSet.getString(7));
                vehicleImages.setFrontImageType(resultSet.getString(8));
                vehicleImages.setBackImageType(resultSet.getString(9));
                vehicleImages.setSideImageType(resultSet.getString(10));
                vehicleImages.setInsideImageType(resultSet.getString(11));
                vehicleImages.setCertificateType(resultSet.getString(12));
                vehicleImages.setInsuranceType(resultSet.getString(13));
                vehicleImages.setVehicleNo(resultSet.getString(14));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return vehicleImages;
        }
    }

}
