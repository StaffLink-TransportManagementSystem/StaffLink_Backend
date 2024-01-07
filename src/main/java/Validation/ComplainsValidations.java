package Validation;

import java.util.regex.Pattern;

public class ComplainsValidations {
    public static boolean validateComplainerEmail(String complainerEmail){
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if((complainerEmail!=null) && (pattern.matcher(complainerEmail).matches())){
            //Have to check complainerEmail is in the database
            return true;
        }
        else{
            return false;
        }
    }
    public static boolean validateComplainerType(String complainerType){
        complainerType = complainerType.toLowerCase();
        if(complainerType == null) {
            System.out.println("ComplainerType validation error");
            return false;
        }
        if(complainerType.equals("passenger") || complainerType.equals("driver") || complainerType.equals("owner")) {
            System.out.println("ComplainerType validation success");
            return true;
        } else {
            System.out.println("ComplainerType validation error");
            return false;
        }
    }
    public static boolean validateComplaineeEmail(String complaineeEmail){
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@gmail.com$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if ((complaineeEmail != null) && (pattern.matcher(complaineeEmail).matches())) {
            System.out.println("ComplaineeEmail validation success");
            return true;
        }
        else {
            System.out.println("ComplaineeEmail validation error");
            return false;
        }
    }
    public static boolean validateComplaineeType(String complaineeType){
        complaineeType = complaineeType.toLowerCase();
        if(complaineeType == null) {
            System.out.println("ComplaineeType validation error");
            return false;
        }
        if(complaineeType.equals("passenger") || complaineeType.equals("driver") || complaineeType.equals("owner") || complaineeType.equals("stafflink")) {
            System.out.println("ComplaineeType validation success");
            return true;
        } else {
            System.out.println("ComplaineeType validation error");
            return false;
        }
    }
    public boolean validateComplain(String complain){
        if(complain == null) {
            System.out.println("Complain validation error");
            return false;
        }
        return true;
    }
    public boolean validateStatus(String status){
        status = status.toLowerCase();
        if(status == null) {
            System.out.println("Status validation error");
            return false;
        }
        if(status.equals("pending") || status.equals("accepted") || status.equals("rejected")) {
            System.out.println("Status validation success");
            return true;
        } else {
            System.out.println("Status validation error");
            return false;
        }
    }
}
