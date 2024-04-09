package Model;

import DAO.PassengerDAO;
import DAO.onlineInquiryDAO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class onlineInquiryModel {
    private int inquiry_id;
    private String name;
    private String email;

    private String message;


    public onlineInquiryModel(){}

    public onlineInquiryModel(int inquiry_id, String name, String email, String message) {
        this.inquiry_id = inquiry_id;
        this.name = name;
        this.email = email;
        this.message = message;
    }


    public void setId(int inquiry_id) {
        this.inquiry_id = inquiry_id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setMessage(String message) {
        this.message = message;
    }


    public int getId() {
        return inquiry_id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getMessage() {
        return message;
    }


    public boolean createOnlineInquiry(){
        onlineInquiryDAO inquiryDAO = new onlineInquiryDAO();
        boolean status = inquiryDAO.createOnlineInquiry(this);
        return  status;
    }

    public List<onlineInquiryModel> viewAllInquiries(){
        onlineInquiryDAO inquiryDAO = new onlineInquiryDAO();
        List<onlineInquiryModel> inquiries = onlineInquiryDAO.viewAllInquiries();
        return inquiries;
    }
}
