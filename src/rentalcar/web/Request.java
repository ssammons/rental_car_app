package rentalcar.web;

/*
  Request.java
  --
  Kendal Harland <kendaljharland@gmail.com>
  --
  Wrapper class that handles sending GET, POST, PUT and DELETE requests over
  http.
*/

import java.net.URL;
import java.net.URI;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Request {

  final static String EMPTY_RESPONSE = "{}";

  /** LocalHost configuration */
  //final static String PROTOCOL = "http";
  //final static String HOST = "localhost";
  //final static int PORT = 3000;

  /** Heroku configuration */
  final static String PROTOCOL = "https";
  final static String HOST = "rentalcar.herokuapp.com";
  final static int PORT = 443;

  private static void _handleHTTPError(int code) {
    System.out.println("x A web request failed with code " + code + ". Implement _handleHTTPError");
  }

  private String _request(String path, String _method) {
    try {
      URL url = new URI(PROTOCOL, null, HOST, PORT, path, null, null).toURL();
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setRequestMethod(_method);
      int status = conn.getResponseCode();
      if (status != 200) // and exception was not thrown
        _handleHTTPError(status);
      InputStreamReader reader = new InputStreamReader(conn.getInputStream());
      int data;
      String response = "";
      while ((data = reader.read()) > 0)
        response += (char)data;
      conn.disconnect();
      return response;
    } catch (URISyntaxException e) {
      System.out.println("x URISyntaxException: " + e.getMessage());
      return EMPTY_RESPONSE;
    } catch (MalformedURLException e) {
      System.out.println("x MalformedURLException: " + e.getMessage());
      return EMPTY_RESPONSE;
    } catch (IOException e) {
      System.out.println("x IOException: " + e.getMessage());
      return EMPTY_RESPONSE;
    }
  }

  public Request() {}

  public String GET (String path)  {
    String response = _request(path, "GET");
    return response;
  }

  public String PUT (String path) {
    String response = _request(path, "PUT");
    return response;
  } 

  public String DELETE (String path) {
    String response = _request(path, "DELETE");
    return response;
  }
  
  public static String POST (String path, String params) {
    try {
      URL url = new URI(PROTOCOL, null, HOST, PORT, path, null, null).toURL();
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
      DataOutputStream ostream = new DataOutputStream(conn.getOutputStream());
      String reqstr = params;
      ostream.writeBytes(reqstr);
      ostream.flush();
      ostream.close();
      InputStreamReader reader = new InputStreamReader(conn.getInputStream());
      int data;
      String response = "";
      while ((data = reader.read()) > 0)
        response += (char)data;
      reader.close();
      return response;
    } catch (MalformedURLException e) {
      System.out.println("x MalformedURLException: " + e.getMessage());
      return EMPTY_RESPONSE;
    } catch (IOException e) {
      System.out.println("x IOException: " + e.getMessage());
      return EMPTY_RESPONSE;
    } catch (URISyntaxException e) {
      System.out.println("x URISyntaxException: " + e.getMessage());
      return EMPTY_RESPONSE;
    }
  }
};
