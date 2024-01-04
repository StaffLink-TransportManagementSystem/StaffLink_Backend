package Validation;

import Model.DriverModel;
import com.sun.org.apache.bcel.internal.generic.PUSH;

import java.security.PublicKey;
import java.util.regex.Pattern;

public class DriverValidations {
    public boolean validateDriverName(String name) {
        if(name == null) {
            System.out.println("Name validation error");
            return false;
        }
        return true;
    }
    public boolean validateDriverEmail(String email) {
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

    public boolean validateDriverNIC(String nic) {
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
    public boolean validateDriverContactNo(String contactNo) {
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
    public boolean validateDriverPassword(String password) {
        if(password == null ) {
            System.out.println("Password validation error");
            return false;
        } else if (password.length() < 6) {
            System.out.println("Password validation error");
            return false;
        }
        return true;
    }

    public boolean validateDriverAge(int age) {
        if(age == 0) {
            System.out.println("Age validation error");
            return false;
        } else if(age < 18) {
            System.out.println("Age validation error");
            return false;
        }
        return true;
    }
    public boolean validateOwnerEmail(String ownerEmail) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@gmail.com$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if ((ownerEmail != null) && (pattern.matcher(ownerEmail).matches())) {
            System.out.println("OwnerEmail validation success");
            return true;
        }
        else {
            System.out.println("OwnerEmail validation error");
            return false;
        }
    }

    public boolean validateDriverOnInsert(DriverModel driverModel) {
        if(validateDriverName(driverModel.getName()) && validateDriverEmail(driverModel.getEmail()) && validateDriverNIC(driverModel.getNIC()) && validateDriverAge(driverModel.getAge()) && validateDriverContactNo(driverModel.getContact()) && validateDriverPassword(driverModel.getPassword()) && validateOwnerEmail(driverModel.getOwnerEmail())) {
            System.out.println("Driver validation success");
            return true;
        } else {
            System.out.println("Driver validation error");
            return false;
        }
    }
    public boolean validateDriverOnUpdate(DriverModel driverModel) {
        boolean valid = true;
        if (driverModel.getName() != null) {
            valid = validateDriverName(driverModel.getName());
        }
        if (driverModel.getEmail() != null) {
            valid = validateDriverEmail(driverModel.getEmail());
        }
        if (driverModel.getNIC() != null) {
            valid = validateDriverNIC(driverModel.getNIC());
        }
        if (driverModel.getAge() != 0) {
            valid = validateDriverAge(driverModel.getAge());
        }
        if (driverModel.getContact() != null) {
            valid = validateDriverContactNo(driverModel.getContact());
        }
        if (driverModel.getPassword() != null) {
            valid = validateDriverPassword(driverModel.getPassword());
        }
        if (driverModel.getOwnerEmail() != null) {
            valid = validateOwnerEmail(driverModel.getOwnerEmail());
        }
        if (valid) {
            System.out.println("Driver validation success");
            return true;
        } else {
            System.out.println("Driver validation error");
            return false;
        }
    }
}
