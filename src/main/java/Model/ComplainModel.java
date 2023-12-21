package Model;

import DAO.ComplainDAO;

import java.util.List;

public class ComplainModel {
    private int id;
    private String complainerEmail; //person who is complaining
    private String complainerType;
    private String complaineeEmail; //person who is targeted
    private String complaineeType;
    private String complain;
    private String date;
    private String time;
    private String status;
    private int deleteState;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComplainerEmail() {
        return complainerEmail;
    }

    public void setComplainerEmail(String complainerEmail) {
        this.complainerEmail = complainerEmail;
    }

    public String getComplainerType() {
        return complainerType;
    }

    public void setComplainerType(String complainerType) {
        this.complainerType = complainerType;
    }

    public String getComplaineeEmail() {
        return complaineeEmail;
    }

    public void setComplaineeEmail(String complaineeEmail) {
        this.complaineeEmail = complaineeEmail;
    }

    public String getComplaineeType() {
        return complaineeType;
    }

    public void setComplaineeType(String complaineeType) {
        this.complaineeType = complaineeType;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }

    public boolean createComplain(){
        ComplainDAO complainDAO = new ComplainDAO();
        boolean status = complainDAO.addComplain(this);
        return  status;
    }

    public boolean editComplain(){
        ComplainDAO complainDAO = new ComplainDAO();
        boolean status = complainDAO.updateComplain(this);
        return  status;
    }

    public boolean deleteComplain(){
        ComplainDAO complainDAO = new ComplainDAO();
        boolean status = complainDAO.deleteComplain(id);
        return  status;
    }

    public List<ComplainModel> viewAllOwners(){
        ComplainDAO complainDAO = new ComplainDAO();
        List<ComplainModel> complains = complainDAO.getAllComplains();
        return complains;
    }

    public List<ComplainModel> getComplainByPassenger(String email){
        ComplainDAO complainDAO = new ComplainDAO();
        List<ComplainModel> complains = complainDAO.getComplainsByPassenger(email);
        return complains;
    }
    public List<ComplainModel> getComplainByOwner(String email){
        ComplainDAO complainDAO = new ComplainDAO();
        List<ComplainModel> complains = complainDAO.getComplainsByOwner(email);
        return complains;
    }
}
