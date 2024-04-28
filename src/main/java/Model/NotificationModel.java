package Model;

import DAO.NotificationDAO;

import java.util.List;

public class NotificationModel {
    int notificationId;
    int userId;
    String email;
    String message;
    String Date;
    int viewStatus;
    int deleted;

    public NotificationModel() {
    }

    public NotificationModel(int id, String email, int userId, String Date, String message) {
        this.notificationId = id;
        this.email = email;
        this.userId = userId;
        this.Date = Date;
        this.message = message;
    }


    public void setId(int id) {
        this.notificationId = id;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public int getViewStatus() {
        return viewStatus;
    }

    public void setDeleted(int id) {
        this.deleted = id;
    }

    public void setViewStatus(int id) {
        this.viewStatus = id;
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

    public List<NotificationModel> getAdminNotification(String email){
        NotificationDAO notificationDAO = new NotificationDAO();
        return notificationDAO.getAdminNotification(email);
    }

    public List<NotificationModel> getOwnerNotification(String email){
        NotificationDAO notificationDAO = new NotificationDAO();
        return notificationDAO.getOwnerNotification(email);
    }

    public List<NotificationModel> getDriverNotification(String email){
        NotificationDAO notificationDAO = new NotificationDAO();
        return notificationDAO.getDriverNotification(email);
    }

    public List<NotificationModel> getPassengerNotification(String email){
        NotificationDAO notificationDAO = new NotificationDAO();
        return notificationDAO.getPassengerNotification(email);
    }

    public boolean updateNotificationViewState(){
        NotificationDAO notificationDAO = new NotificationDAO();
        return notificationDAO.updateNotificationViewState(this);
    }

    public int getAdminNotificationCount(){
        NotificationDAO notificationDAO = new NotificationDAO();
        int NotificationCount = notificationDAO.getAdminNotificationCount(email);
        return NotificationCount;
    }

    public int getOwnerNotificationCount(){
        NotificationDAO notificationDAO = new NotificationDAO();
        int NotificationCount = notificationDAO.getOwnerNotificationCount(email);
        return NotificationCount;
    }

    public int getDriverNotificationCount(){
        NotificationDAO notificationDAO = new NotificationDAO();
        int NotificationCount = notificationDAO.getDriverNotificationCount(email);
        return NotificationCount;
    }

    public int getPassengerNotificationCount(){
        NotificationDAO notificationDAO = new NotificationDAO();
        int NotificationCount = notificationDAO.getPassengerNotificationCount(email);
        return NotificationCount;
    }

}


