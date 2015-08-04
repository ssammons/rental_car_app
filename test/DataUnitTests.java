
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Iterator;
import java.util.HashMap;

import rentalcar.web.JSONRequest;
import rentalcar.system.Database;
import rentalcar.data.FormObject;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class DataUnitTests {
  public static void main(String[] args) throws JSONException {

    // Instantiate a database client
    Database db = new Database();

    /// Get a list of available locations (unstable)
    String[] locations = db.GetLocations();
    
    /** 
     * Compare the number of vehicles at a location with a specific class to
     * the number of reserved vehicles in the same class at the same location
     * (location with location id of 1)
     */
    HashMap<String, Integer> countsV = db.GetVehicleClassCount(41);
    HashMap<String, Integer> countsR = db.GetReservedVehicleClassCount(41, "2016-01-01", "2015-01-01");
    Iterator<String> it = countsV.keySet().iterator();
      while (it.hasNext()) {
        String nextKey = it.next();
        System.out.print(nextKey+": Total ");
        System.out.print(countsV.get(nextKey));
        System.out.print(",  Reserved ");
      System.out.println(countsR.get(nextKey));
    }
    
    System.out.println("Tax rate: " + db.GetTaxRate(1));

    HashMap<String, Double> classRates = db.GetVehicleClassRates("Standard SUV");
    System.out.println("vehicle class rates: ");
    System.out.println("weekly: " + classRates.get("weeklyprice").toString());
    System.out.println("daily: " + classRates.get("dailyprice").toString());

    System.out.println("Fetching all reservations...");
    List<HashMap<String, String>> resis = db.GetReservations(1);
    System.out.println("Got " + resis.size() + "reservations");


    System.out.println("Fetching all vehicles...");
    List<HashMap<String, String>> vehicles = db.GetAllVehicles();
    System.out.println("Got "+ vehicles.size() +"vehicles");

    System.out.println(db.GetAllReservations());

    FormObject reservation = new FormObject();
    reservation.Set("startDate",    "2015-01-12");
    reservation.Set("endDate",      "2015-08-04");
    reservation.Set("vehicleClass", "Standard SUV");
    reservation.Set("locationId",   "11");
    reservation.Set("salesrepId",   "12");
    reservation.Set("customerId",   "2");
    reservation.Set("ccn",          "1111777712122323");
    reservation.Set("gps",          "1");
    reservation.Set("childSeat",    "0");
    reservation.Set("ktag",         "0");
    reservation.Set("roadside",     "1");
    reservation.Set("waiver",       "1");
    reservation.Set("accident",     "1");
    reservation.Set("totalCost",    "1350.44");
    String affectedRowsResis = db.CreateReservation(reservation);
    System.out.println("created "+ affectedRowsResis + " reservation(s)");

    FormObject newUser = new FormObject();
    newUser.Set("username",     "troll3");
    newUser.Set("password",     "yunoencrypt??");
    newUser.Set("address",      "frickin' space. awwwww yeahhhhhhh.");
    newUser.Set("licenseNumber","HAWTDGZ");
    newUser.Set("userType",         "1");
    newUser.Set("firstname",    "John");
    newUser.Set("lastname",     "Oliver");
    newUser.Set("email",        "john.oliver@lwt.tv");
    newUser.Set("phoneNumber",  "139-319-3913"); 
    newUser.Set("birthdate",    "7-4-1776");
    String affectedRowsUsers = db.CreateUser(newUser);
    System.out.println("created "+ affectedRowsUsers + " user(s)");

    System.out.println("fetching equipment...");
    List<HashMap<String, String>> equipment = db.GetAllEquipment();
    System.out.println("got " + equipment.size() + " instances of equipment...");

    System.out.println("fetching equipment counts...");
    List<HashMap<String, String>> equipmentCounts = db.GetEquipmentCounts();
    System.out.println("got " + equipmentCounts.size() + " instances of equipment...");
    System.out.println(equipmentCounts);
  }
}