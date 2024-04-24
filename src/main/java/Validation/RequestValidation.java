package Validation;

import Model.RequestModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestValidation {
    public boolean validateVehicleNo(String vehicleNo) {
//        String vehicleNoRegex = "^[A-Za-z]{3}-\\\\d{4}$|^[A-Za-z]{2}-\\\\d{4}$";
        if(vehicleNo == null) {
            System.out.println("VehicleNo validation error");
            return false;
        }

        vehicleNo = vehicleNo.replaceAll("[^a-zA-Z0-9\\s-]", "");

        // Check if the vehicle number matches the specified formats with a dash or a space
        Pattern pattern1 = Pattern.compile("^[A-Za-z]{3}-\\d{4}$");
        Pattern pattern2 = Pattern.compile("^[A-Za-z]{2}-\\d{4}$");
        Pattern pattern3 = Pattern.compile("^[A-Za-z]{3} \\d{4}$");
        Pattern pattern4 = Pattern.compile("^[A-Za-z]{2} \\d{4}$");

        // New patterns to allow for vehicle numbers with either three or two letters
        Pattern pattern5 = Pattern.compile("^[A-Za-z]{3}\\d{4}$");
        Pattern pattern6 = Pattern.compile("^[A-Za-z]{2}\\d{4}$");

        Matcher matcher1 = pattern1.matcher(vehicleNo);
        Matcher matcher2 = pattern2.matcher(vehicleNo);
        Matcher matcher3 = pattern3.matcher(vehicleNo);
        Matcher matcher4 = pattern4.matcher(vehicleNo);
        Matcher matcher5 = pattern5.matcher(vehicleNo);
        Matcher matcher6 = pattern6.matcher(vehicleNo);

        if(matcher1.matches() || matcher2.matches() || matcher3.matches() || matcher4.matches() || matcher5.matches() || matcher6.matches()){
            System.out.println("VehicleNo validation success");
            return true;
        } else {
            System.out.println("VehicleNo validation error");
            return false;
        }

//        return matcher1.matches() || matcher2.matches() || matcher3.matches() || matcher4.matches();
    }
    public boolean validationPassengerEmail(String passengerEmail) {
//        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$";
        if(passengerEmail == null) {
            System.out.println("PassengerEmail validation error");
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(passengerEmail);

        if(matcher.matches()) {
            System.out.println("PassengerEmail validation success");
        } else {
            System.out.println("PassengerEmail validation error");
        }

        // Return true if the email matches the pattern, false otherwise
        return matcher.matches();
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
        if(validateVehicleNo(requestModel.getVehicleNo()) && validationPassengerEmail(requestModel.getPassengerEmail()) && validatePrice(requestModel.getPrice()) && validateStartingPoint(requestModel.getStartingLatitude()) && validateStartingPoint(requestModel.getStartingLongitude()) && validateEndingPoint(requestModel.getEndingLatitude()) && validateEndingPoint(requestModel.getEndingLongitude()) && startingPointAndEndingPointAreSame(requestModel.getStartingLatitude(), requestModel.getEndingLatitude()) && startingPointAndEndingPointAreSame(requestModel.getStartingLongitude(), requestModel.getEndingLongitude()) && validateStartingDate(requestModel.getStartingDate()) && validateEndingDate(requestModel.getEndingDate(), requestModel.getStartingDate()) && onTime(requestModel.getOnTime()) && offTime(requestModel.getOffTime()) && validateStatus(requestModel.getStatus())) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validateRequestOnUpdate(RequestModel requestModel) {
        boolean valid = true;
        if(requestModel.getVehicleNo() == null && requestModel.getPassengerEmail() == null) {
            return false;
        }
        if(requestModel.getVehicleNo() != null) {
            valid = validateVehicleNo(requestModel.getVehicleNo());
        }
        if(valid && requestModel.getPassengerEmail() != null) {
            valid = validationPassengerEmail(requestModel.getPassengerEmail());
        }
        if (valid){
            RequestModel currentRequest = new RequestModel();
            currentRequest = currentRequest.getRequest(requestModel.getVehicleNo(), requestModel.getPassengerEmail());

            if(requestModel.getPrice() == 0){
                requestModel.setPrice(currentRequest.getPrice());
            }
            if(requestModel.getStartingLatitude() == null){
                requestModel.setStartingLatitude(currentRequest.getStartingLatitude());
            }
            if(requestModel.getStartingLongitude() == null){
                requestModel.setStartingLongitude(currentRequest.getStartingLongitude());
            }
            if(requestModel.getEndingLatitude() == null){
                requestModel.setEndingLatitude(currentRequest.getEndingLatitude());
            }
            if(requestModel.getEndingLongitude() == null){
                requestModel.setEndingLongitude(currentRequest.getEndingLongitude());
            }
            if(requestModel.getStartingDate() == null){
                requestModel.setStartingDate(currentRequest.getStartingDate());
            }
            if(requestModel.getEndingDate() == null){
                requestModel.setEndingDate(currentRequest.getEndingDate());
            }
            if(requestModel.getOnTime() == null){
                requestModel.setOnTime(currentRequest.getOnTime());
            }
            if(requestModel.getOffTime() == null){
                requestModel.setOffTime(currentRequest.getOffTime());
            }
            if(requestModel.getStatus() == null){
                requestModel.setStatus(currentRequest.getStatus());
            }

        }
        if(valid && requestModel.getPrice() != 0) {
            valid = validatePrice(requestModel.getPrice());
        }
        if(valid && requestModel.getStartingLatitude() != null) {
            valid = validateStartingPoint(requestModel.getStartingLatitude());
        }
        if(valid && requestModel.getEndingLatitude() != null) {
            valid = validateEndingPoint(requestModel.getEndingLatitude());
        }
        if(valid && requestModel.getStartingLongitude() != null) {
            valid = validateStartingPoint(requestModel.getStartingLongitude());
        }
        if(valid && requestModel.getEndingLongitude() != null) {
            valid = validateEndingPoint(requestModel.getEndingLongitude());
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

    public RequestModel updateRequest(RequestModel requestModel) {
        RequestModel currentRequest = new RequestModel();
        currentRequest = currentRequest.getRequest(requestModel.getVehicleNo(), requestModel.getPassengerEmail());

        if(requestModel.getPrice() == 0){
            requestModel.setPrice(currentRequest.getPrice());
        }
        if(requestModel.getStartingLatitude() == null){
            requestModel.setStartingLatitude(currentRequest.getStartingLatitude());
        }
        if(requestModel.getStartingLongitude() == null){
            requestModel.setStartingLongitude(currentRequest.getStartingLongitude());
        }
        if(requestModel.getEndingLatitude() == null){
            requestModel.setEndingLatitude(currentRequest.getEndingLatitude());
        }
        if(requestModel.getEndingLongitude() == null){
            requestModel.setEndingLongitude(currentRequest.getEndingLongitude());
        }
        if(requestModel.getStartingDate() == null){
            requestModel.setStartingDate(currentRequest.getStartingDate());
        }
        if(requestModel.getEndingDate() == null){
            requestModel.setEndingDate(currentRequest.getEndingDate());
        }
        if(requestModel.getOnTime() == null){
            requestModel.setOnTime(currentRequest.getOnTime());
        }
        if(requestModel.getOffTime() == null){
            requestModel.setOffTime(currentRequest.getOffTime());
        }
        if(requestModel.getStatus() == null){
            requestModel.setStatus(currentRequest.getStatus());
        }
        return requestModel;
    }
}
