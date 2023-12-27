package Controller;

import DAO.VehicleImageDAO;
import Model.VehicleModel;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

//@WebServlet("/testSendImages")
//public class testSendImages extends HttpServlet {
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.setContentType("application/json");
//        PrintWriter out = response.getWriter();
//        System.out.println("Image Sending servelet");
//
//        try {
//            Gson gson = new Gson();
//            BufferedReader bufferedReader = request.getReader();
//            VehicleModel vehicle = gson.fromJson(bufferedReader, VehicleModel.class);
//
////            VehicleImageDAO vehicleImageDAO = new VehicleImageDAO();
////            List<VehicleModel> vehicleImages = vehicleImageDAO.getVehicleImages(vehicle.getVehicleNo());
//
////            byte[] image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images"+vehicleImages.get(0).getFrontImage()+"."+vehicleImages.get(0).getFrontImageType());
//            byte[] image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/Capture.png");
//
//            if (image.length != 0) {
//                response.setStatus(HttpServletResponse.SC_OK);
//                out.write("{\"size\": " + image.length + ",\"list\":" + image + "}");
//                System.out.println("View all images");
//            } else if (image.length == 0) {
//                response.setStatus(HttpServletResponse.SC_ACCEPTED);
//                out.write("{\"size\": \"0\"}");
//                System.out.println("No images");
//            }
//
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        } finally {
//            out.close();
//        }
//    }
//
//    private byte[] convertImageToByteArray(String imagePath) throws IOException {
//        Path path = Paths.get(imagePath);
//        return Files.readAllBytes(path);
//    }
//}


@WebServlet("/testSendImages")
public class testSendImages extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Image Sending servlet");

        try {
            Gson gson = new Gson();
            BufferedReader bufferedReader = request.getReader();
            VehicleModel vehicle = gson.fromJson(bufferedReader, VehicleModel.class);

            byte[] image = convertImageToByteArray("C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images/Capture.png");

            if (image.length != 0) {
                // Convert the byte array to a Base64-encoded string
                String base64Image = Base64.getEncoder().encodeToString(image);

                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"size\": " + image.length + ", \"base64Image\": \"" + base64Image + "\"}");
                System.out.println("View all images");
            } else {
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                out.write("{\"size\": \"0\"}");
                System.out.println("No images");
            }

        } catch (Exception e) {
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