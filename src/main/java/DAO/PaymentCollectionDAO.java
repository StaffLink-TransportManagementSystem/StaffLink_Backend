package DAO;

import Database.DBConnection;
import Model.PaymentCollectionModel;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PaymentCollectionDAO {
    public List<PaymentCollectionModel> getPaymentCollection(String driverEmail) {
        Connection connection = DBConnection.getInstance().getConnection();
        boolean status = false;
        List<PaymentCollectionModel> paymentCollectionModelList = new ArrayList<>();
        try {
            String query = "SELECT passengerpayments.*, vehicles.*, drivers.* " +
                    "FROM passengerpayments " +
                    "INNER JOIN vehicles ON passengerpayments.vehicleNo = vehicles.vehicleNo " +
                    "INNER JOIN drivers ON vehicles.driverEmail = drivers.email " +
                    "WHERE passengerpayments.deleteState = 0 AND vehicles.deleteState = 0 AND drivers.deleteState = 0 AND drivers.email = ? AND passengerpayments.paymentType = \"Cash\";";
            java.sql.PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, driverEmail);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PaymentCollectionModel paymentCollectionModel = new PaymentCollectionModel();
                paymentCollectionModel.setId(resultSet.getInt("id"));
                paymentCollectionModel.setPassengerEmail(resultSet.getString("passengerEmail"));
                paymentCollectionModel.setVehicleNo(resultSet.getString("vehicleNo"));
                paymentCollectionModel.setDate(resultSet.getString("date"));
                paymentCollectionModel.setAmount(resultSet.getFloat("amount"));
                paymentCollectionModel.setReservationID(resultSet.getInt("reservationID"));
                paymentCollectionModel.setPaymentStatus(resultSet.getString("status"));
                paymentCollectionModel.setOwnerEmail(resultSet.getString("ownerEmail"));
                paymentCollectionModel.setPassengerName(resultSet.getString("name"));
                paymentCollectionModelList.add(paymentCollectionModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paymentCollectionModelList;
    }
}
