package Validation;

import DAO.PassengerDAO;
import Model.PassengerModel;

import java.util.regex.Pattern;

public class Passenger {
    private static boolean validateName(String name) {
        if (name != null && name.length() > 0) {
            System.out.println("Name validation success");
            return true;
        } else {
            System.out.println("Name validation error");
            return false;
        }
    }
    public static boolean validateEmail(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if ((email != null) && (pattern.matcher(email).matches())) {
            System.out.println("Email validation success");
            return true;
        }
        else {
            System.out.println("Email validation error");
            return false;
        }
    }
    private static boolean emailExists(String email) {
        PassengerDAO passengerDAO = new PassengerDAO();
        if(passengerDAO.checkPassengerEmail(email)) {
            System.out.println("Email exists");
            return true;
        } else {
            System.out.println("Email not exists");
            return false;
        }
    }
    private static boolean validateNIC(String nic) {
        nic = nic.replaceAll("[^a-zA-Z0-9]", "");

        String NEW_NIC_REGEX = "^\\d{12}$";
        String OLD_NIC_REGEX = "^[0-9]{9}[Vv]$";

        if (nic.matches(NEW_NIC_REGEX) || nic.matches(OLD_NIC_REGEX)) {
            // NIC format is valid, now check if it doesn't exist
            System.out.println("NIC validation success");
            return true;
        } else {
            System.out.println("NIC validation error");
            return false;
        }
    }
    private static boolean NICExists(String NIC) {
        PassengerDAO passengerDAO = new PassengerDAO();
        if(NIC == null) {
            return false;
        }
        if(passengerDAO.checkPassengerNIC(NIC)) {
            System.out.println("NIC exists");
            return true;
        } else {
            System.out.println("NIC not exists");
            return false;
        }
    }
    private static boolean validateContact(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9+]", "");

        // Check if the phone number is either 10 digits or starts with '+' and has 12 digits
        String PHONE_NUMBER_REGEX = "^(\\+\\d{12}|\\d{10})$";

        if (phoneNumber.matches(PHONE_NUMBER_REGEX)) {
            System.out.println("Phone number validation success");
            return true;
        } else {
            System.out.println("Invalid phone number format");
            return false;
        }
    }
    private static boolean validatePassword(String password) {
//        String PASSWORD_REGEX ="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
//        Pattern pattern = Pattern.compile(PASSWORD_REGEX);

//        if ((password != null) && (pattern.matcher(password).matches())) {
        if ((password != null) && (password.length() >= 6)) {
            System.out.println("Password validation success");
            return true;
        }
        else {
            System.out.println("Password validation error");
            return false;
        }
    }

    public static boolean validation(PassengerModel passenger) {
        if (validateName(passenger.getName()) && validateEmail(passenger.getEmail()) && validateNIC(passenger.getNIC()) && validateContact(passenger.getContactNo()) && validatePassword(passenger.getPassword()) && !emailExists(passenger.getEmail()) && !NICExists(passenger.getNIC())) {
            System.out.println("Validation success");
            return true;
        } else {
            System.out.println("Validation error");
            return false;
        }
    }

    public static boolean validationOnUpdate(PassengerModel passenger) {
        PassengerModel passengerModel = new PassengerModel();
        PassengerDAO passengerDAO = new PassengerDAO();
        passengerModel = passengerDAO.getPassenger(passenger.getEmail());
        boolean valid = true;
        if(passenger.getId() == 0) {
            passenger.setId(passengerModel.getId());
        }
        if(passenger.getName() == null) {
            passenger.setName(passengerModel.getName());
        }
        if(passenger.getNIC() == null) {
            passenger.setNIC(passengerModel.getNIC());
        }
        if(passenger.getPassword()== null) {
            passenger.setPassword(passengerModel.getPassword());
        }
        if(passenger.getContactNo() == null) {
            passenger.setContactNo(passengerModel.getContactNo());
            System.out.println(passenger.getContactNo());
        }
        valid = validation(passenger);
        if(valid) {
            System.out.println("Validation success");
            return true;
        } else {
            System.out.println("Validation error");
            return false;
        }
    }
    public static PassengerModel setPassenger(PassengerModel passenger) {
        PassengerModel passengerModel = new PassengerModel();
        PassengerDAO passengerDAO = new PassengerDAO();
        passengerModel = passengerDAO.getPassenger(passenger.getEmail());
        boolean valid = true;
        if(passenger.getId() == 0) {
            passenger.setId(passengerModel.getId());
        }
        if(passenger.getName() == null) {
            passenger.setName(passengerModel.getName());
        }
        if(passenger.getNIC() == null) {
            passenger.setNIC(passengerModel.getNIC());
        }
        if(passenger.getPassword()== null) {
            passenger.setPassword(passengerModel.getPassword());
        }
        if(passenger.getContactNo() == null) {
            passenger.setContactNo(passengerModel.getContactNo());
        }
        valid = validationOnUpdate(passenger);
        if(valid) {
            System.out.println("Validation success");
            return passenger;
        } else {
            System.out.println("Validation error");
            return null;
        }
    }

    public Passenger() {}

}
