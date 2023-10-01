package Model;

import java.sql.Time;
import java.util.Date;

public class PassengerModel {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private String address;
    private String contactNo;
    private String homeLocation;
    private String workLocation;
    private String type; // daily or monthly
    private Time onTime;
    private Time offTime;
    private Boolean upAndDown; //up and down passenger or not

}
