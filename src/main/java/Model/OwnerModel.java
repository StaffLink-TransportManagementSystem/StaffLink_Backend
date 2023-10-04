package Model;

import DAO.OwnerDAO;

public class OwnerModel {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private String address;
    private String contactNo;
    private float income;
    private float balance;
    private int vehicleCount;

    public OwnerModel(String name, String email, String NIC, String address, String contactNo, float income, float balance) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.address = address;
        this.contactNo = contactNo;
        this.income = income;
        this.balance = balance;
//        this.vehicleCount = getVehicleCount(NIC);
    }

    public int getVehicleCount() {
        return vehicleCount;
    }
    
}
