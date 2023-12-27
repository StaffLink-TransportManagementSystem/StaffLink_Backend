package Controller;

import Model.VehicleModel;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet("/getVehicleImages")
public class GetVehicleImages extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        System.out.println("get vehicle Images");
        try {
//            String email = req.getParameter("email");
            String vehicleNumber = req.getParameter("vehicleNo");
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.setVehicleNo(vehicleNumber);
            vehicleModel.getVehicleImages(vehicleNumber);
            String path = "C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/";

            byte[] frontImage = convertImageToByteArray(path+vehicleModel.getFrontImage()+"."+vehicleModel.getFrontImageType());
            byte[] backImage = convertImageToByteArray(path+vehicleModel.getBackImage()+"."+vehicleModel.getBackImageType());
            byte[] sideImage = convertImageToByteArray(path+vehicleModel.getSideImage()+"."+vehicleModel.getSideImageType());
            byte[] insideImage = convertImageToByteArray(path+vehicleModel.getInsideImage()+"."+vehicleModel.getInsideImageType());
            byte[] certificateImage = convertImageToByteArray(path+vehicleModel.getCertificate()+"."+vehicleModel.getCertificateType());
            byte[] insuranceImage = convertImageToByteArray(path+vehicleModel.getInsurance()+"."+vehicleModel.getInsuranceType());

            if (frontImage.length != 0 || backImage.length != 0 || sideImage.length != 0 || insideImage.length != 0 || certificateImage.length != 0 || insuranceImage.length != 0) {
                res.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\":\"success\",\"frontImage\": " + frontImage + ",\"backImage\":" + backImage + ",\"sideImage\":" + sideImage + ",\"insideImage\":" + insideImage + ",\"certificateImage\":" + certificateImage + ",\"insuranceImage\":" + insuranceImage + "}");
                System.out.println("View all images");
            } else if (frontImage.length == 0 && backImage.length == 0 && sideImage.length == 0 && insideImage.length == 0 && certificateImage.length == 0 && insuranceImage.length == 0) {
                res.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"message\":\"No images\"}");
                System.out.println("No images");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            out.close();
        }
    }

    private byte[] convertImageToByteArray(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
}
