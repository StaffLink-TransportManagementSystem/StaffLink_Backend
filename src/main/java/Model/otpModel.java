package Model;

import DAO.otpDAO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class otpModel {
    private int otp_id;
    private String email;
    private int otp;

    public otpModel(){}

    public otpModel(int otp_id, String email, int otp) {
        this.otp_id = otp_id;
        this.email = email;
        this.otp = otp;
    }

    public void setId(int otp_id) {
        this.otp_id = otp_id;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setOTP(int otp) {
        this.otp = otp;
    }

    public int getId() {
        return otp_id;
    }
    public String getEmail() {
        return email;
    }
    public int getOTP() {
        return otp;
    }


    public boolean createOTP(){
        otpDAO otpDAO = new otpDAO();
        boolean status = otpDAO.createOTP(this);
        return  status;
    }
}
