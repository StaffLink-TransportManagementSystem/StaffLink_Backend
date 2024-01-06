package Validation;

import Model.PassengerPaymentsModel;

import java.util.regex.Pattern;

public class PassengerPaymentValidation {
    boolean validateRequestID(int requestID){
        if(requestID>0){
            //Have to check requestID is in the database
            return true;
        }
        else{
            return false;
        }
    }
    boolean validateReservationID(int reservationID){
        if(reservationID>0){
            //Have to check reservationID is in the database
            return true;
        }
        else{
            return false;
        }
    }
    boolean validatePassengerEmail(String passengerEmail){
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if((passengerEmail!=null) && (pattern.matcher(passengerEmail).matches())){
            //Have to check passengerEmail is in the database
            return true;
        }
        else{
            return false;
        }
    }
    boolean validateVehicleNo(String vehicleNo){
        String vehicleNoRegex = "^[A-Za-z]{3}-\\\\d{4}$|^[A-Za-z]{2}-\\\\d{4}$";
        Pattern pattern = Pattern.compile(vehicleNoRegex);
        if((vehicleNo!=null) && (pattern.matcher(vehicleNo).matches())){
            //Have to check vehicleNo is in the database
            return true;
        }
        else{
            return false;
        }
    }

    boolean validateDate(String date){
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        Pattern pattern = Pattern.compile(dateRegex);
        if((date!=null) && (pattern.matcher(date).matches())){
            return true;
        }
        else{
            return false;
        }
    }
    boolean validatePaymentType(String paymentType){
        paymentType = paymentType.toLowerCase();
        if(paymentType.equals("card") || paymentType.equals("cash")){
            return true;
        }
        else{
            return false;
        }
    }
    boolean validateAmount(float amount){
        if(amount>0){
            return true;
        }
        else{
            return false;
        }
    }
    boolean validateStatus(String status){
        status = status.toLowerCase();
        if(status.equals("paid") || status.equals("not paid")){
            return true;
        }
        else{
            return false;
        }
    }
    boolean validatePassengerPaymentOnCreate(PassengerPaymentsModel passengerPaymentsModel){
        if(validateRequestID(passengerPaymentsModel.getRequestID()) && validateReservationID(passengerPaymentsModel.getReservationID()) && validatePassengerEmail(passengerPaymentsModel.getPassengerEmail()) && validateVehicleNo(passengerPaymentsModel.getVehicleNo()) && validatePaymentType(passengerPaymentsModel.getPaymentType()) && validateAmount(passengerPaymentsModel.getAmount()) && validateStatus(passengerPaymentsModel.getStatus())){
            return true;
        }
        else{
            return false;
        }
    }

    boolean validatePassengerPaymentOnUpdate(PassengerPaymentsModel passengerPaymentsModel){
        boolean valid = true;
        if(passengerPaymentsModel.getRequestID()!=0){
            valid = validateRequestID(passengerPaymentsModel.getRequestID());
        }
        if(passengerPaymentsModel.getReservationID()!=0){
            valid = validateReservationID(passengerPaymentsModel.getReservationID());
        }
        if(passengerPaymentsModel.getPassengerEmail()!=null){
            valid = validatePassengerEmail(passengerPaymentsModel.getPassengerEmail());
        }
        if(passengerPaymentsModel.getVehicleNo()!=null){
            valid = validateVehicleNo(passengerPaymentsModel.getVehicleNo());
        }
        if(passengerPaymentsModel.getDate()!=null){
            valid = validateDate(passengerPaymentsModel.getDate());
        }
        if(passengerPaymentsModel.getPaymentType()!=null){
            valid = validatePaymentType(passengerPaymentsModel.getPaymentType());
        }
        if(passengerPaymentsModel.getAmount()!=0){
            valid = validateAmount(passengerPaymentsModel.getAmount());
        }
        if(passengerPaymentsModel.getStatus()!=null){
            valid = validateStatus(passengerPaymentsModel.getStatus());
        }
        if(valid){
            return true;
        }
        else{
            return false;
        }
    }

}
