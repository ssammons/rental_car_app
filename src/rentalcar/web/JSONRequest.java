package rentalcar.web;

/*
  JSONRequest.java
  --
  Kendal Harland <kendaljharland@gmail.com>
  --
  Wrapper for rentalcar.web.Request that parses a response into a JSON object.
*/
import org.json.JSONException;
import rentalcar.web.Request;
import org.json.JSONTokener;
import org.json.JSONObject;

public class JSONRequest {

  /*
    JSONObject GET
    GET a url and return the response as a JSON object 
    @param url  The url to GET
  */
  public static JSONObject GET (String url) throws JSONException {
    Request request = new Request();
    String response = request.GET(url);
    return new JSONObject(new JSONTokener(response));
  }

  /*
    JSONObject PUT
    PUT a url and return the response as a JSON object 
    @param url  The url to PUT
  */
  public static JSONObject PUT (String url) throws JSONException {
    Request request = new Request();
    String response = request.PUT(url);
    return new JSONObject(new JSONTokener(response));
  }
  
  /*
    JSONObject DELETE
    DELETE a url and return the response as a JSON object 
    @param url  The url to DELETE
  */
  public static JSONObject DELETE (String url) throws JSONException {
    Request request = new Request();
    String response = request.DELETE(url);
    return new JSONObject(new JSONTokener(response));
  }

  /*
    JSONObject POST
    POST a url and return the response as a JSON object 
    @param url  The url to POST
    @param params  The key-value pairs of data in url format
  */
  public static JSONObject POST (String url, String params) throws JSONException {
    Request request = new Request();
    String response = request.POST(url, params);
    return new JSONObject(new JSONTokener(response));
  }
};