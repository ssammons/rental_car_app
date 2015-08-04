package rentalcar.system;

/*
  Database.java
  --
  Kendal Harland <kendaljharland@gmail.com>
  --
  Database API Client library for the rental car application.
*/

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import rentalcar.web.Request;
import rentalcar.web.JSONRequest;
import rentalcar.data.FormObject;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import org.json.JSONException;

public class Database {

  public static final Map<String, Integer> Locations;

  /** Maps location names to database constants */
  static {
    HashMap<String, Integer> nonConstLocations = new HashMap<String, Integer>();
    nonConstLocations.put("Atchison", 1);
    nonConstLocations.put("Belton", 11);
    nonConstLocations.put("Emporia", 21);
    nonConstLocations.put("Hiawatha", 31);
    nonConstLocations.put("Kansas City", 41);
    nonConstLocations.put("Lawrence", 51);
    nonConstLocations.put("Leavenworth", 61);
    nonConstLocations.put("Manhattan", 71);
    nonConstLocations.put("Platte City", 81);
    nonConstLocations.put("St Joseph", 91);
    nonConstLocations.put("Topeka", 101);
    nonConstLocations.put("Warrensburg", 111);
    Locations = Collections.unmodifiableMap(nonConstLocations);
  }
  
  public String[] GetLocations() {
    return Locations.keySet().toArray(new String[Locations.size()]);
  }

  public Integer GetLocationId(String locationName) {
    return Locations.get(locationName);
  }

  public String CreateUser(FormObject user) {
    Request request = new Request();
    return request.POST("/user", user.ToQueryString());
  }

  public String CreateReservation(FormObject reservation) {
    Request request = new Request();
    return request.POST("/reservation", reservation.ToQueryString());
  }

  public List<HashMap<String, String>> GetAllReservations() {
    Request request = new Request();
    String url = "/reservations";
    JSONArray objects = new JSONArray(new JSONTokener(request.GET(url)));
    List<HashMap<String, String>> reservations = new ArrayList<HashMap<String, String>>();
    for (int i=0; i<objects.length(); i++) {
      JSONObject obj = objects.getJSONObject(i);
      HashMap<String, String> map = new HashMap<String, String>();
      Iterator<String> it = obj.keys();
      while (it.hasNext()) {
        String key = it.next();
        map.put(key, obj.get(key).toString());
      }
      reservations.add(map);
    }
    return reservations;
  }

  public Double GetTaxRate(int locationId) throws JSONException {
    JSONRequest request = new JSONRequest();
    JSONObject response = request.GET("/location/taxRate/" + Integer.toString(locationId));
    return response.getDouble("rate");
  }

  public HashMap<String, Double> GetVehicleClassRates(String vehicleClass) throws JSONException {
    JSONRequest request = new JSONRequest();
    JSONObject response = request.GET("/vehicle/class/rates/" + vehicleClass);
    Iterator<String> it = response.keys();
    HashMap<String, Double> vehicleClassRates = new HashMap<String, Double>();
    while (it.hasNext()) {
      String key = it.next();
      if (!key.equals("name"))
        vehicleClassRates.put(key, response.getDouble(key));
    }
    return vehicleClassRates;
  }

  public List<HashMap<String, String>> GetVehicles(int locationId) throws JSONException {
    Request request = new Request();
    String response = request.GET("location/vehicles/"+Integer.toString(locationId));
    JSONArray vehicleStrings = new JSONArray(response);
    List<HashMap<String, String>> vehicles = new ArrayList<HashMap<String, String>>();
    for (int i=0; i<vehicleStrings.length(); i++) {
      JSONObject obj = vehicleStrings.getJSONObject(i);
      HashMap<String, String> map = new HashMap<String, String>();
      Iterator<String> it = obj.keys();
      while (it.hasNext()) {
        String key = it.next();
        map.put(key, obj.get(key).toString());
      }
      vehicles.add(map);
    }
    return vehicles;
  }

  public List<HashMap<String, String>> GetAllVehicles() {
    Request request = new Request();
    String response = request.GET("/vehicles");
    JSONArray vehicleStrings = new JSONArray(response);
    List<HashMap<String, String>> vehicles = new ArrayList<HashMap<String, String>>();
    for (int i=0; i<vehicleStrings.length(); i++) {
      JSONObject obj = vehicleStrings.getJSONObject(i);
      HashMap<String, String> map = new HashMap<String, String>();
      Iterator<String> it = obj.keySet().iterator();
      while (it.hasNext()) {
        String key = it.next();
        map.put(key, obj.get(key).toString());
      }
      vehicles.add(map);
    }
    return vehicles;
  }

  public HashMap<String, Integer> GetReservedVehicleClassCount(int locationId, String startDate, String endDate) throws JSONException {
    Request request = new Request();
    String url = "/location/reservations/class/count/" + Integer.toString(locationId) + "/" + 
      startDate + "/" + endDate;
    JSONArray objects = new JSONArray(new JSONTokener(request.GET(url)));
    HashMap<String, Integer> classCounts = new HashMap<String, Integer>();
    for (int i=0; i<objects.length(); i++) {
      JSONObject object = objects.getJSONObject(i);
      classCounts.put(
        object.getString("class"),
        object.getInt("count"));
    }
    return classCounts;
  }


  public HashMap<String, Integer> GetVehicleClassCount(int locationId) throws JSONException {
    Request request = new Request();
    String url = "/location/vehicles/class/count/" + Integer.toString(locationId);
    JSONArray objects = new JSONArray(new JSONTokener(request.GET(url)));
    HashMap<String, Integer> classCounts = new HashMap<String, Integer>();
    for (int i=0; i<objects.length(); i++) {
      JSONObject object = objects.getJSONObject(i);
      classCounts.put(
        object.getString("class"),
        object.getInt("count"));
    }
    return classCounts;
  }  

  public List<HashMap<String, String>> GetReservations(int userId) {
    Request request = new Request();
    String url = "/user/reservations/" + Integer.toString(userId);
    JSONArray objects = new JSONArray(new JSONTokener(request.GET(url)));
    List<HashMap<String, String>> reservations = new ArrayList<HashMap<String, String>>();
    for (int i=0; i<objects.length(); i++) {
      HashMap<String, String> map = new HashMap<String, String>();
      JSONObject object = objects.getJSONObject(i);
      Iterator<String> it = object.keys();
      while (it.hasNext()) {
        String key = it.next();
        map.put(key, object.getString(key));
      }
      reservations.add(map);
    }
    return reservations;
  }

  public List<HashMap<String, String>> GetAllEquipment() {
    Request request = new Request();
    String response = request.GET("/equipment");
    JSONArray equipmentStrings = new JSONArray(response);
    List<HashMap<String, String>> equipment = new ArrayList<HashMap<String, String>>();
    for (int i=0; i<equipmentStrings.length(); i++) {
      JSONObject obj = equipmentStrings.getJSONObject(i);
      HashMap<String, String> map = new HashMap<String, String>();
      Iterator<String> it = obj.keySet().iterator();
      while (it.hasNext()) {
        String key = it.next();
        map.put(key, obj.get(key).toString());
      }
      equipment.add(map);
    }
    return equipment;
  }

  public List<HashMap<String, String>> GetEquipmentCounts() {
    Request request = new Request();
    String response = request.GET("/equipment/counts");
    JSONArray equipmentStrings = new JSONArray(response);
    List<HashMap<String, String>> equipment = new ArrayList<HashMap<String, String>>();
    for (int i=0; i<equipmentStrings.length(); i++) {
      JSONObject obj = equipmentStrings.getJSONObject(i);
      HashMap<String, String> map = new HashMap<String, String>();
      Iterator<String> it = obj.keySet().iterator();
      while (it.hasNext()) {
        String key = it.next();
        map.put(key, obj.get(key).toString());
      }
      equipment.add(map);
    }
    return equipment;
  }
}
