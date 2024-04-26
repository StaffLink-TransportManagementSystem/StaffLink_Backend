package Validation;

import Model.AbsentModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class AbasntValidation {
    boolean validatePassengerEmail(String passengerEmail) {
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

    public static boolean validateStartingDate(String date) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        Pattern pattern = Pattern.compile(dateRegex);

        if ((date != null) && (pattern.matcher(date).matches())) {
            // Parse the input date using DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate inputDate = LocalDate.parse(date, formatter);

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

    //days of abasnet shoud be integer
    public boolean validateDaysOfAbsent(int daysOfAbsent) {
        if(daysOfAbsent <= 0) {
            System.out.println("DaysOfAbsent validation error");
            return false;
        } else {
            System.out.println("DaysOfAbsent validation error");
            return false;
        }
    }

    public boolean validateAbsantOnInsert(AbsentModel absentModel){
        if(validatePassengerEmail(absentModel.getPassengerEmail()) && validateReservationId(absentModel.getReservationId()) && validateDaysOfAbsent(absentModel.getDaysOfAbsent()) && validateStartingDate(absentModel.getStartingDate()) && validateEndingDate(absentModel.getEndingDate(), absentModel.getStartingDate())){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean validateAbsantOnUpdate(AbsentModel absentModel){
        boolean valid = true;
        if(valid && absentModel.getPassengerEmail()!=null){
            valid = validatePassengerEmail(absentModel.getPassengerEmail());
        }
        if(valid && absentModel.getReservationId()<=0){
            valid = validateReservationId(absentModel.getId());
        }
        if(valid){
            valid = validateDaysOfAbsent(absentModel.getDaysOfAbsent());
        }
        if(valid && absentModel.getStartingDate()!=null){
            valid = validateStartingDate(absentModel.getStartingDate());
        }
        if(valid && absentModel.getEndingDate()!=null){
            valid = validateEndingDate(absentModel.getEndingDate(), absentModel.getStartingDate());
        }
        if(valid){
            System.out.println("Absent validation success");
            return true;
        }
        else{
            System.out.println("Absent validation error");
            return false;
        }
    }

    public boolean validateReservationId(int reservationId) {
        if(reservationId > 0) {
            return true;
        } else {
            return false;
        }
    }
}
