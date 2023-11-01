package Model;

import DAO.PassengerDAO;

public class loginModel {
    private String email;
    private String password;

    public loginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PassengerModel getPassenger(){
        PassengerDAO passengerDAO = new PassengerDAO();
        PassengerModel passenger = passengerDAO.getPassenger(this.email);
        return  passenger;
    }
}
