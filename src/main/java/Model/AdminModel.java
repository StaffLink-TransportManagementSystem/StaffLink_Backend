package Model;

public class AdminModel {
    private int id;
    private String name;
    private String email;
    private String NIC;
    private String contactNo;
    private String password;
    private int deleteState;

    public AdminModel(String name, String email, String NIC, String contactNo, String password) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.password = password;
    }

    public AdminModel(String email) {
        this.email = email;
    }

    public AdminModel() {

    }

    public AdminModel(int id, String name, String email, String NIC, String contactNo, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.password = password;
    }

    public AdminModel(int id, String name, String email, String NIC, String contactNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
    }

    public AdminModel(String name, String email, String NIC, String contactNo) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
    }

    public AdminModel(String name, String email, String NIC, String contactNo, String password, int deleteState) {
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.password = password;
        this.deleteState = deleteState;
    }

    public AdminModel(int id, String name, String email, String NIC, String contactNo, String password, int deleteState) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.password = password;
        this.deleteState = deleteState;
    }

    public AdminModel(int id, String name, String email, String NIC, String contactNo, int deleteState) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.NIC = NIC;
        this.contactNo = contactNo;
        this.deleteState = deleteState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public boolean createAdmin(){
        return true;
    }



}
