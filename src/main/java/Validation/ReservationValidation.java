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
    public boolean validateStartingDate(LocalDate date) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        if(date == null) {
            System.out.println("Date validation error");
            return false;
        }
        if(date.toString().matches(dateRegex)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate inputDate = date;

            // Get the current date
            LocalDate currentDate = LocalDate.now();
            if (inputDate.isAfter(currentDate)) {
                System.out.println("Date validation success");
                return true;
            }
        }
        return false;
    }
    public boolean validateEndingDate(LocalDate startingDate, LocalDate endingDate) {
        String dateRegex = "^(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-]\\d{4}$";
        if(endingDate == null) {
            System.out.println("Date validation error");
            return false;
        }
        if(endingDate.toString().matches(dateRegex)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate endDate = endingDate;
            LocalDate startDate = startingDate;


            if (endDate.isAfter(startDate) || endDate.isEqual(startDate)) {
                System.out.println("Date validation success");
                return true;
            }
        }
        return false;
    }
    public boolean validateStartingWaypoint(String startingWaypoint) {
        if(startingWaypoint == null) {
            System.out.println("StartingWaypoint validation error");
            return false;
        }
        return true;
    }
    public boolean validateEndingWaypoint(String endingWaypoint) {
        if(endingWaypoint == null) {
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
        if(validatePassengerEmail(reservationModel.getPassengerEmail()) && validateVehicleNo(reservationModel.getVehicleNo()) && validateStartingDate(reservationModel.getStartingDate()) && validateEndingDate(reservationModel.getStartingDate(), reservationModel.getEndingDate()) && validateStartingWaypoint(reservationModel.getStartingLongitude()) && validateStartingWaypoint(reservationModel.getStartingLatitude()) && validateEndingWaypoint(reservationModel.getEndingLatitude()) && validateEndingWaypoint(reservationModel.getEndingLongitude()) && validateStatus(reservationModel.getStatus())){
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
        if(valid && reservationModel.getStartingLatitude()!=null){
            valid = validateStartingWaypoint(reservationModel.getStartingLatitude());
        }
        if(valid && reservationModel.getEndingLatitude()!=null){
            valid = validateEndingWaypoint(reservationModel.getEndingLatitude());
        }
        if(valid && reservationModel.getStartingLongitude()!=null){
            valid = validateStartingWaypoint(reservationModel.getStartingLongitude());
        }
        if(valid && reservationModel.getEndingLongitude()!=null){
            valid = validateEndingWaypoint(reservationModel.getEndingLongitude());
        }
        if(valid && reservationModel.getStatus()!=null){
            valid = validateStatus(reservationModel.getStatus());
        }
        return valid;
    }


}
