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
    private String password;

    public OwnerModel(String name, String email, String NIC, String address, String contactNo, float income, float balance, String password) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.address = address;
        this.contactNo = contactNo;
        this.income = income;
        this.balance = balance;
        this.password = password;
        this.vehicleCount = getVehicleCount(NIC);
    }

    public int getVehicleCount(String NIC) {

        return vehicleCount;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getNIC() {
        return NIC;
    }
    public String getAddress() {
        return address;
    }
    public String getContactNo() {
        return contactNo;
    }
    public float getIncome() {
        return income;
    }
    public float getBalance() {
        return balance;
    }
    public String getPassword() {
        return password;
    }

    public void setVehicleCount(int vehicleCount) {
        this.vehicleCount = vehicleCount;
    }
    public void setName(String name) {
        this.name= name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNIC(String NIC) {
        this.NIC = NIC;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
    public void setIncome(float income) {
        this.income = income;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int anInt) {
        this.id = anInt;
    }
}
