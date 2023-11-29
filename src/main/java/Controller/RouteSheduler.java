package Controller;

import DAO.WaypointsDAO;
import Model.Waypoints;

import java.sql.Connection;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class RouteSheduler {
    public static void setArrivalTimes(int routeNo) {
        System.out.println("Hello setArrivalTimes");
        try {
            WaypointsDAO waypointsDAO = new WaypointsDAO();
            List<Waypoints> waypointsList = waypointsDAO.getwaypoints(routeNo);

            int i = waypointsList.size() - 1;
            System.out.println(waypointsList.size());
            String finalPoint = waypointsList.get(i).getDeadlineTime();
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            Date prev = dateFormat.parse(finalPoint);
            long differenceInMilliSeconds = 0;
            long differenceInHours = 0;
            long differenceInMinutes = 0;
            long differenceInSeconds = 0;
            String difference = "";
            long differenceInMilliSeconds1 =  0;
            long differenceInHours1 = 0;
            long differenceInMinutes1 = 0;
            long differenceInSeconds1 = 0;
            String difference1 = "";
            Date temp = dateFormat.parse("00:00:00");
            Date differenceDate = dateFormat.parse("00:00:00");

            while (i >= 0) {
                Waypoints waypoint = waypointsList.get(i);
                System.out.println("check 01");
                if (i == waypointsList.size()-1) {
//                    waypoint.setArrivalTime(String.valueOf(prev));
                    differenceInMilliSeconds = Math.abs(prev.getTime() - temp.getTime());
                    differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
                    differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;
                    differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;
                    difference = differenceInHours + ":" + differenceInMinutes + ":" + differenceInSeconds;
//                    differenceDate = dateFormat.parse(difference);
                    waypoint.setArrivalTime(difference);
                    waypointsDAO.updateWaypoint(waypoint);
                    System.out.println(i+"--------"+waypoint.getWaypointId()+"----------"+waypoint.getArrivalTime());
                } else {
                    System.out.println("check 02");
                    Date date = dateFormat.parse(waypoint.getDeadlineTime());
                    differenceInMilliSeconds = Math.abs(date.getTime() - prev.getTime());
                    differenceInHours = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;
                    differenceInMinutes = (differenceInMilliSeconds / (60 * 1000)) % 60;
                    differenceInSeconds = (differenceInMilliSeconds / 1000) % 60;
                    difference = differenceInHours + ":" + differenceInMinutes + ":" + differenceInSeconds;
                    differenceDate = dateFormat.parse(difference);
                    Date travellingTime = dateFormat.parse("00:30:00");

                    if(differenceDate.compareTo(travellingTime) > 0) {
                        // differenceDate > travellingTime
                        System.out.println("check 03");
                        waypoint.setArrivalTime(waypoint.getDeadlineTime());
                        waypointsDAO.updateWaypoint(waypoint);

                        differenceInMilliSeconds1 = Math.abs(differenceDate.getTime() - travellingTime.getTime());
                        differenceInHours1 = (differenceInMilliSeconds1 / (60 * 60 * 1000)) % 24;
                        differenceInMinutes1 = (differenceInMilliSeconds1 / (60 * 1000)) % 60;
                        differenceInSeconds1 = (differenceInMilliSeconds1 / 1000) % 60;
                        difference1 = differenceInHours1 + ":" + differenceInMinutes1 + ":" + differenceInSeconds1;
                        Date changingTime = dateFormat.parse(difference1);



                        Date nextWaypointDate = dateFormat.parse("00:00:00");
                        Date newTime = dateFormat.parse("00:00:00");
                        int j = i+1;
                        Waypoints nextWaypoint = waypointsList.get(j);
                        while (j<waypointsList.size()) {
                            nextWaypoint = waypointsList.get(j);
                            nextWaypointDate = dateFormat.parse(nextWaypoint.getArrivalTime());
                            newTime = dateFormat.parse(String.valueOf(nextWaypointDate.getTime() - changingTime.getTime()));
                            differenceInMilliSeconds1 = Math.abs(newTime.getTime() - temp.getTime());
                            differenceInHours1 = (differenceInMilliSeconds1 / (60 * 60 * 1000)) % 24;
                            differenceInMinutes1 = (differenceInMilliSeconds1 / (60 * 1000)) % 60;
                            differenceInSeconds1 = (differenceInMilliSeconds1 / 1000) % 60;
                            difference1 = differenceInHours1 + ":" + differenceInMinutes1 + ":" + differenceInSeconds1;
                            nextWaypoint.setArrivalTime(difference1);
                            waypointsDAO.updateWaypoint(nextWaypoint);
                            System.out.println(i+"--------"+waypoint.getWaypointId()+"----------"+waypoint.getArrivalTime());
                            j++;
                        }
                    } else {
                        System.out.println("check 04 -- prev"+prev.getTime()+"--travellingTime"+travellingTime.getTime());
                        differenceInMilliSeconds1 = Math.abs(prev.getTime() - travellingTime.getTime());
                        differenceInHours1 = (differenceInMilliSeconds1 / (60 * 60 * 1000)) % 24;
                        differenceInMinutes1 = (differenceInMilliSeconds1 / (60 * 1000)) % 60;
                        differenceInSeconds1 = (differenceInMilliSeconds1 / 1000) % 60;
                            difference1 = differenceInHours1 + ":" + differenceInMinutes1 + ":" + differenceInSeconds1;
                        System.out.println(difference1);
                        waypoint.setArrivalTime(difference1);
                        waypointsDAO.updateWaypoint(waypoint);
                        System.out.println(i+"--------"+waypoint.getWaypointId()+"----------"+waypoint.getArrivalTime());
                    }


                }
                i--;

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        System.out.println("Done");


//        int i = waypointsList.size()-1;
//        LocalTime prev = waypointsList.get(i).getDeadlineTime();
//        while (i>=0) {
//            Waypoints waypoints = waypointsList.get(i);
//            if (i == waypointsList.size()-1) {
//                waypoints.setArrivalTime(prev);
//            } else {
//
//                if(waypoints.getDeadlineTime()> Duration.between(prev,calculateTravelTime(waypoints.getLocation(),waypointsList.get(i+1).getLocation()))) {
//                    waypoints.setArrivalTime(prev-calculateTravelTime(waypoints.getLocation(),waypointsList.get(i+1).getLocation()));
//                } else {
//                    waypoints.setArrivalTime(waypoints.getDeadlineTime());
//                }
//                ------------------- Have to resolve this -------------------
        //                Waypoints nextWaypoint = waypointsList.get(i+1);
        //                waypoints.setArrivalTime(nextWaypoint.getDeadlineTime());
        //            }
        //            waypointsDAO.updateWaypoint(waypoints);
        //            i--;
        //        }
    }

    private static LocalTime calculateTravelTime(String location, String location1) {
        LocalTime time = LocalTime.of(0,30);
        return time;
    }
}
