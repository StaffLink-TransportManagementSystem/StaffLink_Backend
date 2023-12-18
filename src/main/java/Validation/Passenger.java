package Validation;

import DAO.PassengerDAO;
import Model.PassengerModel;

import java.util.regex.Pattern;

public class Passenger {
    private static boolean validateName(String name) {
        if (name.length() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean validateEmail(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if ((email.length() > 0) && (pattern.matcher(email).matches()) && (!emailExists(email))) {
            return true;
        }
        else {
            return false;
        }
    }
    private static boolean emailExists(String email) {
        PassengerDAO passengerDAO = new PassengerDAO();
        if(passengerDAO.checkPassengerEmail(email)) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean validateNIC(String NIC) {
        String NIC_REGEX = "^(\\d{9}[vVxX]|\\d{12})$";
        Pattern pattern = Pattern.compile(NIC_REGEX);
        if ((NIC.length() > 0) && pattern.matcher(NIC).matches() && (!NICExists(NIC))) {
            return true;
        }
        else {
            return false;
        }
    }
    private static boolean NICExists(String NIC) {
        PassengerDAO passengerDAO = new PassengerDAO();
        if(passengerDAO.checkPassengerNIC(NIC)) {
            return true;
        } else {
            return false;
        }
    }
    private static boolean validateContact(String contact) {
        String CONTACT_NUMBER_REGEX = "^(\\+\\d{2})?\\d{10}$";
        Pattern pattern = Pattern.compile(CONTACT_NUMBER_REGEX);
        if ((contact.length() == 10 || contact.length() == 12) && pattern.matcher(contact).matches()){
            return true;
        }
        else {
            return false;
        }
    }
    private static boolean validatePassword(String password) {
        String PASSWORD_REGEX ="^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);

        if ((password.length() > 0) && (pattern.matcher(password).matches())) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean validation(PassengerModel passenger) {
        if (validateName(passenger.getName()) && validateEmail(passenger.getEmail()) && validateNIC(passenger.getNIC()) && validateContact(passenger.getContactNo()) && validatePassword(passenger.getPassword())) {
            return true;
        } else {
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
        }
        valid = validation(passenger);
        if(valid) {
            return true;
        } else {
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
            return passenger;
        } else {
            return null;
        }
    }

    public Passenger() {}

}
