package com.google.sps.servlets;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/message")
public class MyServletClass extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String[] bucketListTrips = {"India", "Italy", "Thailand"};

    // Convert the server stats to JSON
    String json = convertToJsonUsingGson(bucketListTrips);

    // Send the JSON as the response
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  private String convertToJsonUsingGson(String[] bucketListTrips) {
    Gson gson = new Gson();
    String json = gson.toJson(bucketListTrips);
    return json;
  }
}