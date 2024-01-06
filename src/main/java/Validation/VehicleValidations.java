package Validation;

import Model.VehicleModel;

import java.util.regex.Pattern;

public class VehicleValidations {
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
    public boolean validateVehicleType(String vehicleType) {
        if(vehicleType == null) {
            System.out.println("VehicleType validation error");
            return false;
        }
        if(vehicleType.equals("Car") || vehicleType.equals("Van") || vehicleType.equals("Bus")) {
            System.out.println("VehicleType validation success");
            return true;
        } else {
            System.out.println("VehicleType validation error");
            return false;
        }
    }
    public boolean validateVehicleOwner(String vehicleOwner) {
        if(vehicleOwner == null) {
            System.out.println("VehicleOwner validation error");
            return false;
        }
        return true;
    }
    public boolean validateTrips(String trips) {
        trips = trips.toLowerCase();
        if(trips == null) {
            System.out.println("Trips validation error");
            return false;
        }
        if(trips.equals("morning") || trips.equals("evening") || trips.equals("both")) {
            System.out.println("Trips validation success");
            return true;
        } else {
            System.out.println("Trips validation error");
            return false;
        }
    }
    public boolean validateVehicleBrand(String vehicleBrand) {
        if(vehicleBrand == null) {
            System.out.println("VehicleBrand validation error");
            return false;
        }
        return true;
    }
    public boolean validateVehicleModel(String vehicleModel) {
        if(vehicleModel == null) {
            System.out.println("VehicleModel validation error");
            return false;
        }
        return true;
    }
    public boolean validateRegNo(String regNo) {
        if(regNo == null) {
            System.out.println("Registration no validation error");
            return false;
        }
        return true;
    }
    public boolean validateDriverEmail(String driverEmail) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@gmail.com$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if ((driverEmail != null) && (pattern.matcher(driverEmail).matches())) {
            System.out.println("DriverEmail validation success");
            return true;
        }
        else {
            System.out.println("DriverEmail validation error");
            return false;
        }
    }

    public boolean validateNoOfSeats(int noOfSeats) {
        if(noOfSeats == 0) {
            System.out.println("NoOfSeats validation error");
            return false;
        }
        return true;
    }
    public boolean validateStartingLocation(String startingLocation) {
        if(startingLocation == null) {
            System.out.println("StartingLocation validation error");
            return false;
        }
        return true;
    }
    public boolean validateDestination(String destination) {
        if(destination == null) {
            System.out.println("Destination validation error");
            return false;
        }
        return true;
    }
public boolean validatePrice(double price) {
        if(price == 0) {
            System.out.println("Price validation error");
            return false;
        }
        return true;
    }
    public boolean validateVehicleOnInsert(VehicleModel vehicleModel) {
        if(validateVehicleNo(vehicleModel.getVehicleNo()) && validateVehicleType(vehicleModel.getType()) && validateVehicleOwner(vehicleModel.getOwnerEmail()) && validateTrips(vehicleModel.getTrips()) && validateVehicleBrand(vehicleModel.getVehicleBrand()) && validateVehicleModel(vehicleModel.getModel()) && validateRegNo(vehicleModel.getRegNo()) && validateDriverEmail(vehicleModel.getDriverEmail()) && validateNoOfSeats(vehicleModel.getSeatsCount()) && validateStartingLocation(vehicleModel.getStartingPoint()) && validateDestination(vehicleModel.getEndingPoint())) {
            System.out.println("Vehicle validation success");
            return true;
        } else {
            System.out.println("Vehicle validation error");
            return false;
        }
    }
    public boolean validateVehicleOnUpdate(VehicleModel vehicleModel) {
        boolean valid = true;
        if (vehicleModel.getVehicleNo() != null) {
            valid = validateVehicleNo(vehicleModel.getVehicleNo());
        }
        if (vehicleModel.getType() != null) {
            valid = validateVehicleType(vehicleModel.getType());
        }
        if (vehicleModel.getOwnerEmail() != null) {
            valid = validateVehicleOwner(vehicleModel.getOwnerEmail());
        }
        if (vehicleModel.getTrips() != null) {
            valid = validateTrips(vehicleModel.getTrips());
        }
        if (vehicleModel.getVehicleBrand() != null) {
            valid = validateVehicleBrand(vehicleModel.getVehicleBrand());
        }
        if (vehicleModel.getModel() != null) {
            valid = validateVehicleModel(vehicleModel.getModel());
        }
        if (vehicleModel.getRegNo() != null) {
            valid = validateRegNo(vehicleModel.getRegNo());
        }
        if (vehicleModel.getDriverEmail() != null) {
            valid = validateDriverEmail(vehicleModel.getDriverEmail());
        }
        if (vehicleModel.getSeatsCount() != 0) {
            valid = validateNoOfSeats(vehicleModel.getSeatsCount());
        }
        if (vehicleModel.getStartingPoint() != null) {
            valid = validateStartingLocation(vehicleModel.getStartingPoint());
        }
        if (vehicleModel.getEndingPoint() != null) {
            valid = validateDestination(vehicleModel.getEndingPoint());
        }
        if (vehicleModel.getOwnerEmail() != null) {
            valid = validateVehicleOwner(vehicleModel.getOwnerEmail());
        }
        return valid;
    }
}
