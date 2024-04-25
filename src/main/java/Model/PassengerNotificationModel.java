package Model;

import DAO.PassengerNotificationDAO;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class PassengerNotificationModel {
    int notificationId;
    int userId;
    String email;
    String message;
    String Date;

    int deleted;

    public PassengerNotificationModel() {
    }

    public PassengerNotificationModel(int id, String email, int userId, String Date, String message) {
        this.notificationId = id;
        this.email = email;
        this.userId = userId;
        this.Date = Date;
        this.message = message;
    }


    public void setId(int id) {
        this.notificationId = id;
    }

    public void setDeleted(int id) {
        this.deleted = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getId() {
        return notificationId;
    }

    public String setFromDate(String fromDate) {
        return fromDate;
    }

    public String setToDate(String toDate) {
        return toDate;
    }

    public List<PassengerNotificationModel> getNotification(String email){
        PassengerNotificationDAO passengerNotificationDAO = new PassengerNotificationDAO();
        return passengerNotificationDAO.getNotification(email);
    }

}


