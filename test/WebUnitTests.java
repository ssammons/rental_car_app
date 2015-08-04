

import rentalcar.web.Request;
import rentalcar.web.JSONRequest;

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
  
public class WebUnitTests {
  public static void main(String[] args) {
    JSONRequest jreq = new JSONRequest();
    Request req = new Request();
    System.out.println("checking server->db connection: " + req.GET("mysql/connected/"));
    System.out.println("req.GET:    " + req.GET("json/test/"));
    System.out.println("req.PUT:    " + req.PUT("json/test/"));
    System.out.println("req.DELETE: " + req.DELETE("json/test/"));
    System.out.println("req.POST:   " + req.POST("json/test", ""));
    System.out.println("jreq.GET:   " + jreq.GET("json/test/"));
    System.out.println("jreq.PUT:   " + jreq.PUT("json/test/"));
    System.out.println("jreq.DELETE:" + jreq.DELETE("json/test/"));
    System.out.println("jreq.POST:  " + jreq.POST("json/test", ""));
  }
}