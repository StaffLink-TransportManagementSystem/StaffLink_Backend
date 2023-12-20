package Controller;

import Model.DriverModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;

@WebServlet("/viewAllDriver")
public class allDriver extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();


        DriverModel driverModel = new DriverModel();
        List<DriverModel> drivers = driverModel.viewAllDrivers();

        Gson gson = new Gson();
        String centerJson = gson.toJson(drivers);

        if(drivers.size() != 0){
            resp.setStatus(HttpServletResponse.SC_OK);
            out.write("{\"size\": "+ drivers.size() +",\"list\":"+ centerJson+"}");
            System.out.println("View all Accounts");
        }else if(drivers.size() == 0){
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            out.write("{\"size\": \"0\"}");
            System.out.println("No Accounts");
        }else{
            // TODO handle
        }
    }




}
//package org.ucsc.ecoswapperbackend.controllers;
//
//        import com.google.gson.Gson;
//        import org.ucsc.ecoswapperbackend.dto.CenterDTO;
//        import org.ucsc.ecoswapperbackend.services.CenterService;
//
//        import javax.servlet.ServletException;
//        import javax.servlet.http.HttpServlet;
//        import javax.servlet.http.HttpServletRequest;
//        import javax.servlet.http.HttpServletResponse;
//        import java.io.IOException;
//        import java.io.PrintWriter;
//        import java.sql.Connection;
//        import java.sql.SQLException;
//        import java.util.List;
//
//public class AdminCenterController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("application/json");
//        resp.setCharacterEncoding("UTF-8");
//
//        Connection connection = null;
//
//        CenterService centerService = new CenterService();
//
//        List<CenterDTO> centers = centerService.getAllCenters();
//
//        Gson gson = new Gson();
//        String centerJson = gson.toJson(centers);
//
//        PrintWriter out = resp.getWriter();
//        out.print(centerJson);
//        out.flush();
//    }
//
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CenterService centerService = new CenterService();
//
//        try {
//            /*String businessName = req.getParameter("business_name");
//            String businessRegNo = req.getParameter("business_reg_no");
//            String businessType = req.getParameter("business_type");
//            String centerBio = req.getParameter("center_bio");
//            String centerLocationLink = req.getParameter("center_location_link");*/
//
//            resp.setContentType("application/json");
//
//            // Get the JSON data from the request body
//            String jsonData = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
//
//            // Now you have the JSON data as a string in jsonData
//            System.out.println("Received JSON data: " + jsonData);
//
//            // You can parse the JSON data using a JSON library, such as Gson
//            // Example using Gson:
//            Gson gson = new Gson();
//
//
//            CenterDTO newCenter = gson.fromJson(jsonData,CenterDTO.class);
//            /*newCenter.setBusiness_name(businessName);
//            newCenter.setBusiness_reg_no(businessRegNo);
//            newCenter.setBusiness_type(businessType);
//            newCenter.setCenter_bio(centerBio);
//            newCenter.setCenter_location_link(centerLocationLink);*/
//
//            centerService.addCenter(newCenter);
//
//            resp.getWriter().println("Success!");
//        } catch (Exception e) {
//            resp.getWriter().println("Failed!");
//        }
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int centerId = Integer.parseInt(req.getParameter("center_id"));
//        String newBusinessName = req.getParameter("newBusinessName");
//        String newBusinessRegNo = req.getParameter("newBusinessRegNo");
//        String newBusinessType = req.getParameter("newBusinessType");
//        String newCenterBio = req.getParameter("newCenterBio");
//        String newCenterLocationLink = req.getParameter("newCenterLocationLink");
//
//        CenterService centerService = new CenterService();
//
//        boolean updated = false;
//        try {
//            updated = centerService.updateCenter(centerId, newBusinessName, newBusinessRegNo, newBusinessType, newCenterBio, newCenterLocationLink);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        if (updated) {
//            resp.setStatus(HttpServletResponse.SC_OK);
//            resp.getWriter().write("Center updated successfully");
//        } else {
//            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            resp.getWriter().write("Failed to update center");
//        }
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String centerIdStr = req.getParameter("center_id");
//
//        if (centerIdStr != null) {
//            try {
//                int centerId = Integer.parseInt(centerIdStr);
//
//                CenterService centerService = new CenterService();
//
//                boolean deleted = centerService.deleteCenter(centerId);
//
//                if (deleted) {
//                    resp.setStatus(HttpServletResponse.SC_OK);
//                } else {
//                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//                }
//            } catch (NumberFormatException e) {
//                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            }
//        } else {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//        }
//    }
//}