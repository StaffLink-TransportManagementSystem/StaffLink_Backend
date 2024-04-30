package Controller;

import Auth.JwtUtils;
import Model.VehicleModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@MultipartConfig(
        location = "C:/Users/User/Downloads/StaffLInk/StaffLink_Web_Frontend/Public/Images",
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 11 * 1024 * 1024 //10MB
)
@WebServlet("/vehicleRegister1")
public class createVehicleServelet1 extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("Inside vehicle register servlet with images 11111");

        try {
//            Gson gson = new Gson();
//
//            // Extract JSON data for vehicle model
//            String vehicleJson = req.getParameter("vehicle");
//            VehicleModel vehicle = gson.fromJson(vehicleJson, VehicleModel.class);
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setVehicleNo(req.getParameter("vehicleNo"));
            vehicleModel.setOwnerEmail(req.getParameter("ownerEmail"));
            vehicleModel.setType(req.getParameter("vehicleType"));
            String brand = req.getParameter("vehicleBrand");
            System.out.println("Brand: " + brand);
            if(brand == null || brand.isEmpty()){
                brand = "Toyota";
            }
            vehicleModel.setBrand(brand);
            vehicleModel.setModel(req.getParameter("model"));
            vehicleModel.setDriverEmail(req.getParameter("driverEmail"));
            String seats = req.getParameter("availableSeats");
            System.out.println("Seats: " + seats);
            if(seats == null || seats.isEmpty()){
                seats = "4";
            }
            vehicleModel.setSeatsCount(Integer.parseInt(seats));

            vehicleModel.setStartingLatitude(req.getParameter("startingLatitude"));
            vehicleModel.setStartingLongitude(req.getParameter("startingLongitude"));
            vehicleModel.setEndingLatitude(req.getParameter("endingLatitude"));
            vehicleModel.setEndingLongitude(req.getParameter("endingLongitude"));
            vehicleModel.setType(req.getParameter("trips"));

            // Handle image parts
            Part insideImagePart = req.getPart("insideImage");
            String insideImageName = getSubmittedFileName(insideImagePart);
            String insideImageType = getFileTypeFromFileName(insideImageName);

            Part outsideImagePart = req.getPart("outsideImage");
            String outsideImageName = getSubmittedFileName(insideImagePart);
            String outsideImageType = getFileTypeFromFileName(insideImageName);

            Part insuranceImagePart = req.getPart("insuranceImage");
            String insuranceImageName = getSubmittedFileName(insideImagePart);
            String insuranceImageType = getFileTypeFromFileName(insideImageName);

            Part revenueLicenseImagePart = req.getPart("revenueLicenseImage");
            String revenueLicenseImageName = getSubmittedFileName(insideImagePart);
            String revenueLicenseImageType = getFileTypeFromFileName(insideImageName);

            Part vehicleRegistrationImagePart = req.getPart("vehicleRegistrationImage");
            String vehicleRegistrationImageName = getSubmittedFileName(insideImagePart);
            String vehicleRegistrationImageType = getFileTypeFromFileName(insideImageName);

            System.out.println("Check 1");
            System.out.println("Vehicle No: " + vehicleModel.getVehicleNo());
            System.out.println("Owner Email: " + vehicleModel.getOwnerEmail());
            System.out.println("Vehicle Type: " + vehicleModel.getType());
            System.out.println("Brand: " + vehicleModel.getBrand());
            System.out.println("Model: " + vehicleModel.getModel());
            System.out.println("Driver Email: " + vehicleModel.getDriverEmail());
            System.out.println("Seats: " + vehicleModel.getSeatsCount());
            System.out.println("Starting Latitude: " + vehicleModel.getStartingLatitude());
            System.out.println("Starting Longitude: " + vehicleModel.getStartingLongitude());
            System.out.println("Ending Latitude: " + vehicleModel.getEndingLatitude());
            System.out.println("Ending Longitude: " + vehicleModel.getEndingLongitude());
            System.out.println("Trips: " + vehicleModel.getTrips());




            // Save the image if it's provided
            if (insideImageName != null && insideImagePart.getSize() > 0) {
                String fullFileName = "insideImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                insideImagePart.write(fullFileName);
                vehicleModel.setInsideImage(fullFileName);
                System.out.println("Inside Image upload successful");
            }
            if (outsideImageName != null && outsideImagePart.getSize() > 0) {
                String fullFileName = "outsideImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                outsideImagePart.write(fullFileName);
                vehicleModel.setOutsideImage(fullFileName);
                System.out.println("Outside Image upload successful");
            }
            if(insuranceImageName != null && insuranceImagePart.getSize() > 0) {
                String fullFileName = "insuranceImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                insuranceImagePart.write(fullFileName);
                vehicleModel.setInsuranceImage(fullFileName);
                System.out.println("Insurance Image upload successful");
            }
            if(revenueLicenseImageName != null && revenueLicenseImagePart.getSize() > 0) {
                String fullFileName = "revenueLicenseImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                revenueLicenseImagePart.write(fullFileName);
                vehicleModel.setRevenueLicenseImage(fullFileName);
                System.out.println("Revenue License Image upload successful");
            }
            if(vehicleRegistrationImageName != null && vehicleRegistrationImagePart.getSize() > 0) {
                String fullFileName = "vehicleRegistrationImage_"+ vehicleModel.getVehicleNo() + System.currentTimeMillis() + "." + insideImageType;
                System.out.println("Full file name: " + fullFileName);
                vehicleRegistrationImagePart.write(fullFileName);
                vehicleModel.setVehicleRegistrationImage(fullFileName);
                System.out.println("Vehicle Registration Image upload successful");
            }


            if(vehicleModel.createVerifyVehicle()){
                // Send response (status 200
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Registration successfully\"}");
                System.out.println("Registration successful");
            }else{
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Registration unsuccessfully\"}");
                System.out.println("Registration incorrect");
            }
            // All validations are passed then register
            // Add your validation logic here

            // Send response
//            if(vehicleModel.createVehicle()){
                // Send response (status 200
//            res.setStatus(HttpServletResponse.SC_OK);
//            out.write("{\"message\": \"Registration successfully\"}");
//            System.out.println("Registration successful");
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.write("{\"message\": \"Registration failed\"}");
        } finally {
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
}
