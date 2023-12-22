package Controller;

import Model.PassengerModel;
import Model.VehicleModel;
import com.google.gson.Gson;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
@MultipartConfig(
        location = "C:/Users/User/Documents/Group Project/Implementation/try2/src/main/java/Images",
        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize = 5 * 1024 * 1024, // 5MB
        maxRequestSize = 11 * 1024 * 1024 //10MB
)
@WebServlet("/imageUpload")
public class testImageUpload extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        System.out.println("Image uploading servelet");

        try{
            Gson gson = new Gson();
//            BufferedReader bufferedReader = request.getReader();
//            VehicleModel vehicle = gson.fromJson(bufferedReader,VehicleModel.class);

            Part part = request.getPart("image");
            System.out.println("Part: " + part);
            Part part1 = request.getPart("extension");
            System.out.println("Part1: " + part1);
            String filename = getFileExtension(String.valueOf(part));
            System.out.println(filename);
            if(filename != null){
                String fullFileName = "image" + filename;
                part.write(fullFileName);
//                vehicle.setImage(fullFileName);
                response.setStatus(HttpServletResponse.SC_OK);
                out.write("{\"message\": \"Image upload successfully\"}");
                System.out.println("Image upload successful");
            }
            else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                out.write("{\"message\": \"Image upload unsuccessfully\"}");
                System.out.println("Image upload incorrect");
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

//    public static String getFileExtension(String fileName) {
//        if (fileName == null || fileName.isEmpty()) {
//            return "";
//        }
//
//        int lastDotIndex = fileName.lastIndexOf(".");
//        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
//            return fileName.substring(lastDotIndex + 1);
//        }
//
//        return "";
//    }

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










//package com.fit.student.controller;
//
//import com.fit.student.dao.RegFormDao;
//import com.fit.student.models.Announcement;
//import com.fit.student.models.RegForm;
//import com.fit.student.models.ResponseMessage;
//import com.fit.student.models.User;
//import com.fit.student.services.InputValidator;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//import java.io.IOException;
//import java.sql.Date;
//import java.sql.SQLException;
//
//import static com.fit.student.security.CookieAuthenticator.validateCookie;
//import static com.fit.student.utils.FileUtils.getFileExtension;
//
//
////@WebServlet("/student/register/*")
//@MultipartConfig(
//        location = "G://Programs/Inteli J Projects/Group Project/src/main/webapp/images/public",
//        fileSizeThreshold = 2 * 1024 * 1024, // 2MB
//        maxFileSize = 5 * 1024 * 1024, // 5MB
//        maxRequestSize = 11 * 1024 * 1024 //10MB
//)
//@WebServlet(name = "StudentServlet", urlPatterns = {"/student/register", "/student/register/*"})
//public class StudentRegisterServlet extends HttpServlet {
//
//    private RegFormDao regFormDao;
//
//    public void init() {
//        regFormDao = new RegFormDao();
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        User visitor = validateCookie(request);
//        if (visitor == null) {
//            response.sendRedirect(request.getContextPath() + "/login");
//        } else {
//            request.setAttribute("user", visitor);
//            String action = (request.getPathInfo() != null) ? request.getPathInfo() : "";
//            System.out.println(action);
//
//            try {
//                switch (action) {
//
//                    case "", "/" -> showRegForm(request, response, visitor);
//                    case "/1", "/review" -> showRegForm1(request, response, visitor);
//                    case "/2" -> showRegForm2(request, response, visitor);
//                    case "/3" -> showRegForm3(request, response, visitor);
//                    case "/1/insert" -> insertRegForm1(request, response, visitor);
//                    case "/2/insert" -> insertRegForm2(request, response, visitor);
//                    case "/3/insert" -> insertRegForm3(request, response, visitor);
////
////                    default:
////                        System.out.println("default");
////                        listFeedback(request, response);
////                        break;
//                }
//            } catch (SQLException ex) {
//                throw new ServletException(ex);
//            }
//        }
//    }
//
//    private void showRegForm(HttpServletRequest request, HttpServletResponse response, User visitor)
//            throws SQLException, ServletException, IOException {
//
//        String email = visitor.getEmail();
//
//        int regFormStatus = regFormDao.getRegFormStatus(email);
//        System.out.println(regFormStatus);
//
//        switch (regFormStatus) {
//            case 1 -> showRegForm1(request, response, visitor);
//            case 2 -> showRegForm2(request, response, visitor);
//            case 3 -> showRegForm3(request, response, visitor);
//            case 4 -> request.getRequestDispatcher("/payment/register").forward(request, response);
//            case 5 -> request.getRequestDispatcher("/views/page/underAdminReview.jsp").forward(request, response);
//            case 6 -> fixRegForm(request, response, visitor);
//        }
//
//
//    }
//
//
//    private void showRegForm1(HttpServletRequest request, HttpServletResponse response, User visitor)
//            throws SQLException, ServletException, IOException {
//
//        String email = visitor.getEmail();
//
//        RegForm regform = regFormDao.selectRegForm(email);
//        if (regform == null) {
//            regform = new RegForm(email);
//        }
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/student/RegForm1.jsp");
//        request.setAttribute("regform", regform);
//        dispatcher.forward(request, response);
//
//    }
//
//    private void showRegForm2(HttpServletRequest request, HttpServletResponse response, User visitor)
//            throws SQLException, ServletException, IOException {
//
//        String email = visitor.getEmail();
//        RegForm regform = regFormDao.selectRegForm(email);
//        System.out.println(visitor.getRole());
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/student/RegForm2.jsp");
//        request.setAttribute("regform", regform);
//        dispatcher.forward(request, response);
//
//    }
//
//    private void showRegForm3(HttpServletRequest request, HttpServletResponse response, User visitor)
//            throws SQLException, ServletException, IOException {
//
//        String email = visitor.getEmail();
//        RegForm regform = regFormDao.selectRegForm(email);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/student/RegForm3.jsp");
//        request.setAttribute("regform", regform);
//        dispatcher.forward(request, response);
//
//    }
//
//    private void insertRegForm1(HttpServletRequest request, HttpServletResponse response, User visitor)
//
//            throws SQLException, IOException {
//
//        response.setContentType("application/json");
//        ResponseMessage responseMessage = new ResponseMessage();
//
//
//        String email = visitor.getEmail();
//        int regformstatus = regFormDao.getRegFormStatus(email);
//
//
//        // Check if the user has already submitted the form or Accepted the form
//        if (regformstatus != 5 && regformstatus != 7) {
//            try {
//                RegForm regform = regFormDao.selectRegForm(email);
//                if (regform == null) {
//                    regform = new RegForm(email);
//                }
//
//
//                regform.setTitle(request.getParameter("title"));
//                regform.setFullname(request.getParameter("fullname"));
//                regform.setInitials(request.getParameter("initials"));
//                regform.setLastname(request.getParameter("lastname"));
//                regform.setBirthday(java.sql.Date.valueOf(request.getParameter("birthday")));
//                regform.setSex(request.getParameter("sex"));
//                regform.setCitizenship(request.getParameter("citizenship"));
//
//
//                // File Upload
//                Part part = request.getPart("profile_pic");
//                String filename = getFileExtension(part);
//
//                if (filename != null) {
//                    String fullFileName = email.hashCode() + "profile_pic" + filename;
//                    part.write(fullFileName);
//
//                    // Save File name in Database
//                    regform.setProfile_pic(fullFileName);
//                }
//
//
//                if (responseMessage.hasError()) {
//                    responseMessage.setStatus("error");
//                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//
//                } else {
//                    regFormDao.insertRegForm1(regform);
//
//                    responseMessage.setStatus("ok");
//                    response.setStatus(HttpServletResponse.SC_OK);
//                    responseMessage.setUrl(request.getContextPath() + "/student/register/2");
//                }
//
//            } catch (ServletException e) {
//                responseMessage.setStatus("error");
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                throw new RuntimeException(e);
//            }
//        } else {
//            responseMessage.setUrl(request.getContextPath() + "/student/register/2");
//        }
//        response.getWriter().write(responseMessage.toJson());
//    }
//
//    private void insertRegForm2(HttpServletRequest request, HttpServletResponse response, User visitor)
//            throws SQLException, IOException {
//        response.setContentType("application/json");
//        ResponseMessage responseMessage = new ResponseMessage();
//
//        String email = visitor.getEmail();
//        int regformstatus = regFormDao.getRegFormStatus(email);
//
//        // Check if the user has already submitted the form or Accepted the form
//        if (regformstatus != 5 && regformstatus != 7) {
//
//            RegForm regform = regFormDao.selectRegForm(email);
//            regform.setTel_no(request.getParameter("tel_no"));
//            regform.setpAddressLine1(request.getParameter("pAddressLine1"));
//            regform.setpAddressLine2(request.getParameter("pAddressLine2"));
//            regform.setpAddressLine3(request.getParameter("pAddressLine3"));
//            regform.setpAddressCity(request.getParameter("pAddressCity"));
//            regform.setpAddressCountry(request.getParameter("pAddressCountry"));
//            regform.setpAddressPostalCode(request.getParameter("pAddressPostalCode"));
//
//            regform.setcAddressLine1(request.getParameter("cAddressLine1"));
//            regform.setcAddressLine2(request.getParameter("cAddressLine2"));
//            regform.setcAddressLine3(request.getParameter("cAddressLine3"));
//            regform.setcAddressCity(request.getParameter("cAddressCity"));
//            regform.setcAddressCountry(request.getParameter("cAddressCountry"));
//            regform.setcAddressPostalCode(request.getParameter("cAddressPostalCode"));
//
//            if (responseMessage.hasError()) {
//                responseMessage.setStatus("error");
//                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//
//            } else {
//                regFormDao.insertRegForm2(regform);
//
//                responseMessage.setStatus("ok");
//                response.setStatus(HttpServletResponse.SC_OK);
//                responseMessage.setUrl(request.getContextPath() + "/student/register/3");
//            }
//        } else {
//            responseMessage.setUrl(request.getContextPath() + "/student/register/3");
//        }
//        response.getWriter().write(responseMessage.toJson());
//    }
//
//    private void insertRegForm3(HttpServletRequest request, HttpServletResponse response, User visitor)
//            throws SQLException, IOException {
//
//        response.setContentType("application/json");
//        ResponseMessage responseMessage = new ResponseMessage();
//
//        String email = visitor.getEmail();
//        int regformstatus = regFormDao.getRegFormStatus(email);
//
//        // Check if the user has already submitted the form or Accepted the form
//        if (regformstatus != 5 && regformstatus != 7) {
//            try {
//                RegForm regform = regFormDao.selectRegForm(email);
//
//                String birthCertificateNumber = request.getParameter("birthCertificateNumber");
//                Date birthCertificateRegDate = java.sql.Date.valueOf(request.getParameter("birthCertificateRegDate"));
//
//                // Birth Certificate Upload ------------------------------------------------------------------------------
//
//                Part birthCertificateImgPart = request.getPart("birthCertificateImg");
//                String birthCertificateImgFilename = getFileExtension(birthCertificateImgPart);
//
//                if (birthCertificateImgFilename != null) {
//                    String fullBirthCertificateImgFilename = email.hashCode() + "birthCertificateImg" + birthCertificateImgFilename;
//                    birthCertificateImgPart.write(fullBirthCertificateImgFilename);
//
//                    // Save File name in Database
//                    regform.setBirthCertificateImg(fullBirthCertificateImgFilename);
//                }
//                //----------------------------------------------------------------------------------------------------------
//
//                String docType = request.getParameter("docType");
//                String docNumber = request.getParameter("docNumber");
//
//
//                // Document PDF Upload ------------------------------------------------------------------------------
//
//                Part docImgPart = request.getPart("docImg");
//                String docImgFilename = getFileExtension(docImgPart);
//
//                if (docImgFilename != null) {
//
//                    String fullDocImgFilename = email.hashCode() + "docImg" + docImgFilename;
//                    docImgPart.write(fullDocImgFilename);
//
//                    // Save File name in Database
//                    regform.setDocImg(fullDocImgFilename);
//                }
//
//                //---------------------------------- VALIDATE ---------------------------------------------
//                InputValidator.isBirthCertificateNumber(birthCertificateNumber, responseMessage, "birthCertificateNumber");
//                InputValidator.isPreviousDate(birthCertificateRegDate, responseMessage, "birthCertificateRegDate");
//                InputValidator.isEmpty(birthCertificateImgFilename, responseMessage, "birthCertificateImg");
//                InputValidator.isInTheList(docType, responseMessage, "docType", new String[]{"nic", "passport", "postalId"});
//                InputValidator.isDocNumber(docNumber, responseMessage, "docNumber", docType);
//                InputValidator.isEmpty(docImgFilename, responseMessage, "docImg");
//                //----------------------------------------------------------------------------------------
//
//
//                if (responseMessage.hasError()) {
//                    responseMessage.setStatus("error");
//                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//
//                } else {
//                    //inserting data to database
//                    regform.setBirthCertificateNumber(birthCertificateNumber);
//                    regform.setBirthCertificateRegDate(birthCertificateRegDate);
//                    regform.setDocType(docType);
//                    regform.setDocNumber(docNumber);
//
//                    regFormDao.insertRegForm3(regform);
//
//                    responseMessage.setStatus("ok");
//                    response.setStatus(HttpServletResponse.SC_OK);
//                    responseMessage.setUrl(request.getContextPath() + "/payment/register");
//                }
//
//            } catch (ServletException e) {
//                throw new RuntimeException(e);
//            }
//        } else {
//            responseMessage.setUrl(request.getContextPath() + "/dashboard");
//        }
//        response.getWriter().write(responseMessage.toJson());
//    }
//
//    private void fixRegForm(HttpServletRequest request, HttpServletResponse response, User visitor)
//            throws SQLException, IOException, ServletException {
//
//        String email = visitor.getEmail();
//        RegForm regform = regFormDao.getRegFormStatusForDashboard(email);
//
//        if (regform.getBirthCertificateStatus().equals("Declined")) {
//            showRegForm1(request, response, visitor);
//        } else if (regform.getDocStatus().equals("Declined")) {
//            showRegForm2(request, response, visitor);
//        } else if (regform.getPaymentStatus().equals("Declined")) {
//            showRegForm2(request, response, visitor);
//        } else {
//            regform.setRegFormStatus(5);
//            regFormDao.updateRegFormStatus(regform);
//        }
//
//    }
//
//
////    private void setDocstatus(HttpServletRequest request, HttpServletResponse response, User visitor)
////            throws SQLException, IOException, ServletException {
////
////        String email = visitor.getEmail();
////        RegForm regform = regFormDao.selectRegForm(email);
////
////        regform.setDocStatus(request.getParameter("docStatus"));
////
////        regFormDao.setDocStatus(regform);
////        response.sendRedirect("../../validate");
////    }
//
//
//    //    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////
////        String name = request.getParameter("name");
////        String username = request.getParameter("username");
////        String email = request.getParameter("email");
////        String password = request.getParameter("password");
////        String telno = request.getParameter("telno");
////
////
////        User user = new User(name, username, email, password, telno);
////        System.out.println(user.getRole());
////
////
////        try {
////            userDao.insertUser(user);
////
////
////        } catch (Exception e) {
////            e.printStackTrace();
////
////
////        }
////
////        response.sendRedirect("login");
////    }
//
//
//}