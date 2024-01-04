package Validation;

import Model.OwnerModel;

import java.util.regex.Pattern;

public class OwnerValidations {
    public boolean validateOwnerName(String name) {
        if(name == null) {
            System.out.println("Name validation error");
            return false;
        }
        return true;
    }
    public boolean validateOwnerEmail(String email) {
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
    public boolean validateOwnerNIC(String nic) {
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
    public boolean validateOwnerContactNo(String contactNo) {
        String CONTACT_NO_REGEX = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(CONTACT_NO_REGEX);
        if ((contactNo != null) && (pattern.matcher(contactNo).matches())) {
            System.out.println("ContactNo validation success");
            return true;
        }
        else {
            System.out.println("ContactNo validation error");
            return false;
        }
    }
    public boolean validateOwnerPassword(String password) {
        if(password == null ) {
            System.out.println("Password validation error");
            return false;
        } else if (password.length() < 6) {
            System.out.println("Password validation error");
            return false;
        }
        return true;
    }
    public boolean validateOwnerOnInsert(OwnerModel ownerModel) {
        if(validateOwnerName(ownerModel.getName()) && validateOwnerEmail(ownerModel.getEmail()) && validateOwnerNIC(ownerModel.getNIC()) && validateOwnerContactNo(ownerModel.getContactNo()) && validateOwnerPassword(ownerModel.getPassword())) {
            System.out.println("Owner validation success");
            return true;
        } else {
            System.out.println("Owner validation error");
            return false;
        }
    }
    public boolean validateOwnerOnUpdate(OwnerModel ownerModel) {
        if(ownerModel.getName() != null) {
            if(!validateOwnerName(ownerModel.getName())) {
                System.out.println("Owner validation error");
                return false;
            }
        }
        if(ownerModel.getEmail() != null) {
            if(!validateOwnerEmail(ownerModel.getEmail())) {
                System.out.println("Owner validation error");
                return false;
            }
        }
        if(ownerModel.getNIC() != null) {
            if(!validateOwnerNIC(ownerModel.getNIC())) {
                System.out.println("Owner validation error");
                return false;
            }
        }
        if(ownerModel.getContactNo() != null) {
            if(!validateOwnerContactNo(ownerModel.getContactNo())) {
                System.out.println("Owner validation error");
                return false;
            }
        }
        if(ownerModel.getPassword() != null) {
            if(!validateOwnerPassword(ownerModel.getPassword())) {
                System.out.println("Owner validation error");
                return false;
            }
        }
        System.out.println("Owner validation success");
        return true;
    }
}
