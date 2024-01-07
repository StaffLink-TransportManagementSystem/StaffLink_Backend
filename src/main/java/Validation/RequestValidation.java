package Validation;

import Model.RequestModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class RequestValidation {
    public boolean validateVehicleNo(String vehicleNo) {
        String vehicleNoRegex = "^[A-Za-z]{3}-\\\\d{4}$|^[A-Za-z]{2}-\\\\d{4}$";
        if(vehicleNo == null) {
            System.out.println("VehicleNo validation error");
            return false;
        }
        if(vehicleNo.matches(vehicleNoRegex)) {
            System.out.println("VehicleNo validation success");
            return true;
        } else {
            System.out.println("VehicleNo validation error");
            return false;
        }
    }
    public boolean validationPassengerEmail(String passengerEmail) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
        if(passengerEmail == null) {
            System.out.println("PassengerEmail validation error");
            return false;
        }
        if(passengerEmail.matches(EMAIL_REGEX)) {
            System.out.println("PassengerEmail validation success");
            return true;
        } else {
            System.out.println("PassengerEmail validation error");
            return false;
        }
    }
    public boolean validatePrice(float price) {
        if(price > 0) {
            System.out.println("Price validation success");
            return true;
        } else {
            System.out.println("Price validation error");
            return false;
        }
    }
    public boolean validateStartingPoint(String startingPoint) {
        if(startingPoint == null) {
            System.out.println("StartingPoint validation error");
            return false;
        }
        if(startingPoint.length() > 0) {
            System.out.println("StartingPoint validation success");
            return true;
        } else {
            System.out.println("StartingPoint validation error");
            return false;
        }
    }
    public boolean validateEndingPoint(String endingPoint) {
        if(endingPoint == null) {
            System.out.println("EndingPoint validation error");
            return false;
        }
        if(endingPoint.length() > 0) {
            System.out.println("EndingPoint validation success");
            return true;
        } else {
            System.out.println("EndingPoint validation error");
            return false;
        }
    }
    public boolean startingPointAndEndingPointAreSame(String startingPoint, String endingPoint) {
        if(startingPoint.equals(endingPoint)) {
            System.out.println("StartingPoint and EndingPoint are same");
            return true;
        } else {
            System.out.println("StartingPoint and EndingPoint are not same");
            return false;
        }
    }
    public boolean validateStartingDate(String startingDate) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        Pattern pattern = Pattern.compile(dateRegex);
        if((startingDate != null) && (pattern.matcher(startingDate).matches())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate inputDate = LocalDate.parse(startingDate, formatter);

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Check if the input date is after today
            if (inputDate.isAfter(currentDate)) {
                return true;
            }
        }
        return false;
    }
    public boolean validateEndingDate(String endingDate, String startingDate) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        Pattern pattern = Pattern.compile(dateRegex);

        if ((endingDate != null) && (pattern.matcher(endingDate).matches())) {
            // Parse the input date using DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate inputDate = LocalDate.parse(endingDate, formatter);

            // Parse the starting date using DateTimeFormatter
            LocalDate inputStartingDate = LocalDate.parse(startingDate, formatter);

            // Check if the input date is after the starting date
            if (inputDate.isAfter(inputStartingDate) || inputDate.isEqual(inputStartingDate)) {
                return true;
            }
        }
        return false;
    }
    public boolean onTime(String onTime){
        String timeRegex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(timeRegex);
        if((onTime != null) && (pattern.matcher(onTime).matches())){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean offTime(String offTime){
        String timeRegex = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern pattern = Pattern.compile(timeRegex);
        if((offTime != null) && (pattern.matcher(offTime).matches())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean validateStatus(String status) {
        status = status.toLowerCase();
        if(status.equals("pending") || status.equals("accepted") || status.equals("rejected")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validateRequestOnInsert(RequestModel requestModel) {
        if(validateVehicleNo(requestModel.getVehicleNo()) && validationPassengerEmail(requestModel.getPassengerEmail()) && validatePrice(requestModel.getPrice()) && validateStartingPoint(requestModel.getStartingPoint()) && validateEndingPoint(requestModel.getEndingPoint()) && startingPointAndEndingPointAreSame(requestModel.getStartingPoint(), requestModel.getEndingPoint()) && validateStartingDate(requestModel.getStartingDate()) && validateEndingDate(requestModel.getEndingDate(), requestModel.getStartingDate()) && onTime(requestModel.getOnTime()) && offTime(requestModel.getOffTime()) && validateStatus(requestModel.getStatus())) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validateRequestOnUpdate(RequestModel requestModel) {
        boolean valid = true;
        if(valid && requestModel.getVehicleNo() != null) {
            valid = validateVehicleNo(requestModel.getVehicleNo());
        }
        if(valid && requestModel.getPassengerEmail() != null) {
            valid = validationPassengerEmail(requestModel.getPassengerEmail());
        }
        if(valid && requestModel.getPrice() != 0) {
            valid = validatePrice(requestModel.getPrice());
        }
        if(valid && requestModel.getStartingPoint() != null) {
            valid = validateStartingPoint(requestModel.getStartingPoint());
        }
        if(valid && requestModel.getEndingPoint() != null) {
            valid = validateEndingPoint(requestModel.getEndingPoint());
        }
        if(valid && requestModel.getStartingDate() != null) {
            valid = validateStartingDate(requestModel.getStartingDate());
        }
        if(valid && requestModel.getEndingDate() != null) {
            valid = validateEndingDate(requestModel.getEndingDate(), requestModel.getStartingDate());
        }
        if(valid && requestModel.getOnTime() != null) {
            valid = onTime(requestModel.getOnTime());
        }
        if(valid && requestModel.getOffTime() != null) {
            valid = offTime(requestModel.getOffTime());
        }
        if(valid && requestModel.getStatus() != null) {
            valid = validateStatus(requestModel.getStatus());
        }
        return valid;
    }
}
