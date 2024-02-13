package Controller;

import Auth.JwtUtils;
import Model.VehicleModel;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
@MultipartConfig(
        location = "C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images",
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 11 * 1024 * 1024 //10MB
)
@WebServlet("/vehicleRegisterWithImages")
public class vehicleRegisterWithImages extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Image uploading servelet");

        // Get all cookies from the request
        Cookie[] cookies = request.getCookies();
        JSONObject jsonObject = new JSONObject();
        int user_id = 0;
        boolean jwtCookieFound = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt".equals(cookie.getName())) {
                    JwtUtils jwtUtils = new JwtUtils(cookie.getValue());
                    if (!jwtUtils.verifyJwtAuthentication()) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.write("{\"message\": \"UnAuthorized\"}");
                        System.out.println("UnAuthorized1");
                        return;
                    }
                    jsonObject = jwtUtils.getAuthPayload();
                    jwtCookieFound = true;
                    break;  // No need to continue checking if "jwt" cookie is found
                }
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized\"}");
            System.out.println("No cookies found in the request.");
            return;
        }

        // If "jwt" cookie is not found, respond with unauthorized status
        if (!jwtCookieFound) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.write("{\"message\": \"UnAuthorized - JWT cookie not found\"}");
            System.out.println("UnAuthorized - JWT cookie not found");
            return;
        }


        try{
            Gson gson = new Gson();

            String ownerEmail = request.getParameter("ownerEmail");
            System.out.println("ownerEmail: " + ownerEmail);

            String vehicleNo = request.getParameter("vehicleNo");
            System.out.println("vehicleNo: " + vehicleNo);

            String type = request.getParameter("type");
            System.out.println("type: " + type);

            String brand = request.getParameter("brand");
            System.out.println("brand: " + brand);

            String model = request.getParameter("model");
            System.out.println("model: " + model);

            String registrationNo = request.getParameter("regNo");
            System.out.println("registrationNo: " + registrationNo);

            String driverEmail = request.getParameter("driverEmail");
            System.out.println("driverEmail: " + driverEmail);

            int noOfSeats = Integer.parseInt(request.getParameter("seatsCount"));
            System.out.println("noOfSeats: " + noOfSeats);

            String startingLocation = request.getParameter("startingPoint");
            System.out.println("startingLocation: " + startingLocation);

            String endingLocation = request.getParameter("endingPoint");
            System.out.println("endingLocation: " + endingLocation);

            String trips = request.getParameter("trips");
            System.out.println("Trips: " + trips);

            Part frontImage = request.getPart("frontImage");
            String frontImageName = getSubmittedFileName(frontImage);
            String frontImageType = getFileTypeFromFileName(frontImageName);

            Part backImage = request.getPart("backImage");
            String backImageName = getSubmittedFileName(backImage);
            String backImageType = getFileTypeFromFileName(backImageName);

            Part sideImage = request.getPart("sideImage");
            String sideImageName = getSubmittedFileName(sideImage);
            String sideImageType = getFileTypeFromFileName(sideImageName);

            Part insideImage = request.getPart("insideImage");
            String insideImageName = getSubmittedFileName(insideImage);
            String insideImageType = getFileTypeFromFileName(insideImageName);

            Part certificateImage = request.getPart("certificate");
            String certificateImageName = getSubmittedFileName(certificateImage);
            String certificateImageType = getFileTypeFromFileName(certificateImageName);

            Part insuranceImage = request.getPart("insurance");
            String insuranceImageName = getSubmittedFileName(insuranceImage);
            String insuranceImageType = getFileTypeFromFileName(insuranceImageName);

            Date date = new Date();
            long timeMilli = date.getTime();
            System.out.println("Time: " + timeMilli);

            VehicleModel vehicle = new VehicleModel();
            vehicle.setOwnerEmail(ownerEmail);
            vehicle.setVehicleNo(vehicleNo);
            vehicle.setType(type);
            vehicle.setVehicleBrand(brand);
            vehicle.setModel(model);
            vehicle.setRegNo(registrationNo);
            vehicle.setDriverEmail(driverEmail);
            vehicle.setSeatsCount(noOfSeats);
            vehicle.setStartingPoint(startingLocation);
            vehicle.setEndingPoint(endingLocation);
            vehicle.setTrips(trips);
            vehicle.setFrontImage("frontImage_"+ vehicleNo+ "_" + timeMilli + "." + frontImageType);
            vehicle.setBackImage("backImage_"+ vehicleNo+ "_" + timeMilli + "." + backImageType);
            vehicle.setSideImage("sideImage_"+ vehicleNo+ "_" + timeMilli + "." + sideImageType);
            vehicle.setInsideImage("insideImage_"+ vehicleNo+ "_" + timeMilli + "." + insideImageType);
            vehicle.setCertificate("certificate_"+ vehicleNo+ "_" + timeMilli + "." + certificateImageType);
            vehicle.setInsurance("insurance_"+ vehicleNo+ "_" + timeMilli + "." + insuranceImageType);
            vehicle.setFrontImageType(frontImageType);
            vehicle.setBackImageType(backImageType);
            vehicle.setSideImageType(sideImageType);
            vehicle.setInsideImageType(insideImageType);
            vehicle.setCertificateType(certificateImageType);
            vehicle.setInsuranceType(insuranceImageType);
            vehicle.setVarifiedState("Not Verified");

            Boolean success = false;
            success = vehicle.createVehicle();

            if(success) {

                Boolean imageUpload = true;

                if (frontImageName != null) {
                    String fullFileName = "frontImage_" + vehicleNo + "_" + timeMilli + "." + frontImageType;
                    frontImage.write(fullFileName);
                    vehicle.setFrontImage(fullFileName);
                    System.out.println("Front Image upload successful");
                } else {
                    imageUpload = false;
                    System.out.println("Front Image upload incorrect");
                }
                if (backImageName != null) {
                    String fullFileName = "backImage_" + vehicleNo + "_" + timeMilli + "." + backImageType;
                    backImage.write(fullFileName);
                    vehicle.setBackImage(fullFileName);
                    System.out.println("Back Image upload successful");
                } else {
                    imageUpload = false;
                    System.out.println("Back Image upload incorrect");
                }
                if (sideImageName != null) {
                    String fullFileName = "sideImage_" + vehicleNo + "_" + timeMilli + "." + sideImageType;
                    sideImage.write(fullFileName);
                    vehicle.setSideImage(fullFileName);
                    System.out.println("Side Image upload successful");
                } else {
                    imageUpload = false;
                    System.out.println("Side Image upload incorrect");
                }
                if (insideImageName != null) {
                    String fullFileName = "insideImage_" + vehicleNo + "_" + timeMilli + "." + insideImageType;
                    insideImage.write(fullFileName);
                    vehicle.setInsideImage(fullFileName);
                    System.out.println("Inside Image upload successful");
                } else {
                    imageUpload = false;
                    System.out.println("Inside Image upload incorrect");
                }
                if (certificateImageName != null) {
                    String fullFileName = "certificate_" + vehicleNo + "_" + timeMilli + "." + certificateImageType;
                    certificateImage.write(fullFileName);
                    vehicle.setCertificate(fullFileName);
                    System.out.println("Certificate Image upload successful");
                } else {
                    imageUpload = false;
                    System.out.println("Certificate Image upload incorrect");
                }
                if (insuranceImageName != null) {
                    String fullFileName = "insurance_" + vehicleNo + "_" + timeMilli + "." + insuranceImageType;
                    insuranceImage.write(fullFileName);
                    vehicle.setInsurance(fullFileName);
                    System.out.println("Insurance Image upload successful");
                } else {
                    imageUpload = false;
                    System.out.println("Insurance Image upload incorrect");
                }
                if (imageUpload) {
                    if(vehicle.insertVehicleImages()) {
                        response.setStatus(HttpServletResponse.SC_OK);
                        out.write("{\"message\": \"Images registration successfully\"}");
                        System.out.println("Vehicle images register successful");
                    }
                    else {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        out.write("{\"message\": \"Something Went Wrong\"}");
                        System.out.println("Vehicle images register incorrect");
                        vehicle.deleteVehicle();
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    out.write("{\"message\": \"Something Went Wrong\"}");
                    System.out.println("Image upload incorrect");
                    vehicle.deleteVehicle();
                }
            }
            else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Something Went Wrong\"}");
                System.out.println("Vehicle register incorrect");
            }

        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        finally {
            out.close();
        }

    }


    private String getSubmittedFileName(Part part) {
        // Check if the part has a "Content-Disposition" header with a "filename" attribute
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            if (contentDisposition.trim().startsWith("filename")) {
                return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private String getFileTypeFromFileName(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            // Extract the file type from the file name
            return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        }
        return null;
    }

    public static String getFileExtension(String filename) {
        if (filename == null) {
            return null; // No file extension found
        }

        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return null; // No valid file extension found
        }

        return filename.substring(lastDotIndex + 1).toLowerCase();
    }

}
