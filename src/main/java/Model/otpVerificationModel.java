package Model;

import DAO.*;

public class otpVerificationModel {
    private String email;
    private int otp;

    public otpVerificationModel(){}

    public otpVerificationModel(String email, int otp) {
        this.email = email;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public int getOTP() {
        return otp;
    }

    public boolean getValidOTP(String email, int otp){
        otpDAO otpDAO = new otpDAO();
        boolean status = otpDAO.validOTP(email, otp);
        return  status;
    }

    public PassengerModel getPassenger(){
        PassengerDAO passengerDAO = new PassengerDAO();
        PassengerModel passenger = passengerDAO.getPassenger(this.email);
        return  passenger;
    }
}
