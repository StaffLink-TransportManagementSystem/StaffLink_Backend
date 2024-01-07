package Validation;

import Model.ReservationModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReservationValidation {
    public boolean validatePassengerEmail(String passengerEmail) {
        String passengerEmailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(passengerEmail == null) {
            System.out.println("PassengerEmail validation error");
            return false;
        }
        if(passengerEmail.matches(passengerEmailRegex)) {
            System.out.println("PassengerEmail validation success");
            return true;
        } else {
            System.out.println("PassengerEmail validation error");
            return false;
        }
    }
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
    public boolean validateStartingDate(String date) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        if(date == null) {
            System.out.println("Date validation error");
            return false;
        }
        if(date.matches(dateRegex)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate inputDate = LocalDate.parse(date, formatter);

            // Get the current date
            LocalDate currentDate = LocalDate.now();
            if (inputDate.isAfter(currentDate)) {
                System.out.println("Date validation success");
                return true;
            }
        }
        return false;
    }
    public boolean validateEndingDate(String startingDate, String endingDate) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        if(endingDate == null) {
            System.out.println("Date validation error");
            return false;
        }
        if(endingDate.matches(dateRegex)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate endDate = LocalDate.parse(endingDate, formatter);
            LocalDate startDate = LocalDate.parse(startingDate, formatter);


            if (endDate.isAfter(startDate) || endDate.isEqual(startDate)) {
                System.out.println("Date validation success");
                return true;
            }
        }
        return false;
    }
    public boolean validateStartingWaypoint(int startingWaypoint) {
        if(startingWaypoint == 0) {
            System.out.println("StartingWaypoint validation error");
            return false;
        }
        return true;
    }
    public boolean validateEndingWaypoint(int endingWaypoint) {
        if(endingWaypoint == 0) {
            System.out.println("EndingWaypoint validation error");
            return false;
        }
        return true;
    }
    public boolean validateStatus(String status){
        if(status == null) {
            System.out.println("Status validation error");
            return false;
        }
        if(status.equals("reserved") || status.equals("not-reserved")) {
            System.out.println("Status validation success");
            return true;
        } else {
            System.out.println("Status validation error");
            return false;
        }
    }
    public boolean validateReservationOnInsert(ReservationModel reservationModel){
        if(validatePassengerEmail(reservationModel.getPassengerEmail()) && validateVehicleNo(reservationModel.getVehicleNo()) && validateStartingDate(reservationModel.getStartingDate()) && validateEndingDate(reservationModel.getStartingDate(), reservationModel.getEndingDate()) && validateStartingWaypoint(reservationModel.getStartingWaypoint()) && validateEndingWaypoint(reservationModel.getEndingWaypoint()) && validateStatus(reservationModel.getStatus())){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean validateReservationOnUpdate(ReservationModel reservationModel){
        boolean valid = true;
        if(valid && reservationModel.getPassengerEmail()!=null){
            valid = validatePassengerEmail(reservationModel.getPassengerEmail());
        }
        if(valid && reservationModel.getVehicleNo()!=null){
            valid = validateVehicleNo(reservationModel.getVehicleNo());
        }
        if(valid && reservationModel.getStartingDate()!=null){
            valid = validateStartingDate(reservationModel.getStartingDate());
        }
        if(valid && reservationModel.getEndingDate()!=null){
            valid = validateEndingDate(reservationModel.getStartingDate(), reservationModel.getEndingDate());
        }
        if(valid && reservationModel.getStartingWaypoint()!=0){
            valid = validateStartingWaypoint(reservationModel.getStartingWaypoint());
        }
        if(valid && reservationModel.getEndingWaypoint()!=0){
            valid = validateEndingWaypoint(reservationModel.getEndingWaypoint());
        }
        if(valid && reservationModel.getStatus()!=null){
            valid = validateStatus(reservationModel.getStatus());
        }
        return valid;
    }


}
