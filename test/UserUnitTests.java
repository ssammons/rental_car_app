

import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import rentalcar.data.FormObject;
import rentalcar.system.User;

/*
  WebUnitTests.java
  --
  Kendal Harland <kendaljharland@gmail.com>
  Siddhant Sethi <siddhant.sethi91@gmail.com>
  Stephanie Sammons <chirox90@gmail.com>
  Grant Hays <granthays@gmail.com>
  Jackson Montgomery <j573m923@ku.edu>
  Lei Wang <l290w868@gmail.com>
  Greg Pregulman <gpregulman@gmail.com>
  --
  Unit tests for the rentalcar.web package
 */

public class UserUnitTests {
  public static void main(String[] args) {
    User user = new User();
    FormObject creds = new FormObject();
    
    // Attempt to log in to the server with bad creds
    creds.Set("username", "kjhd");
    creds.Set("password", "kendalharland");
    System.out.println("Logging in as kjhd...");
/*
   try {
      user.LogIn(creds);
      if(user.IsLoggedIn())
        System.out.println("Client logged in as " + user.FirstName() + " " + user.LastName());
      else 
        System.out.println("User credentials are invalid");
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }
*/
    // Log in with good creds
    System.out.println("Logging in as kjh...");
    creds.Set("username", "kjh");
    try {
      user.LogIn(creds);
      if(user.IsLoggedIn())
        System.out.println("Client logged in as " + user.FirstName() + " " + user.LastName());
      else 
        System.out.println("User credentials are invalid");
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }

    // Test create fake reservation
    FormObject reservation = new FormObject();
    reservation.Set("vehicleid",  "777777");
    reservation.Set("locationid", "1");
    reservation.Set("customerid", "1");
    reservation.Set("salesid",    "1");
    reservation.Set("totalcost",  "4999.99");
    reservation.Set("startmiles", "1000");
    reservation.Set("endmiles",   "2000");
    user.CreateReservation(reservation);

    // Test retrieve list of vehicles
    List<JSONObject> vehicles = user.GetVehiclesByLocation(51);
    System.out.println("VEHICLES: ");
    for (int i=0; i<vehicles.size(); i++) {
      System.out.println(vehicles.get(i)); // JSON Object prints as unparsed string
    }
  }
}
