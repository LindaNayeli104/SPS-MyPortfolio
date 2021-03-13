package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    long timestamp = System.currentTimeMillis();
    String nameTextValue = Jsoup.clean(request.getParameter("nameText-input"), Whitelist.none());
    String emailTextValue = Jsoup.clean(request.getParameter("emailText-input"), Whitelist.none());
    String messageTextValue = Jsoup.clean(request.getParameter("messageText-input"), Whitelist.none());

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + nameTextValue);
    System.out.println("You submitted: " + emailTextValue);
    System.out.println("You submitted: " + messageTextValue);

    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted: " + nameTextValue);
    response.getWriter().println("You submitted: " + emailTextValue);
    response.getWriter().println("You submitted: " + messageTextValue);

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory keyFactory = datastore.newKeyFactory().setKind("User");
    FullEntity contactInfoEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", nameTextValue)
            .set("email", emailTextValue)
            .set("message", messageTextValue)
            .set("timestamp", timestamp)
            .build();
    datastore.put(contactInfoEntity);
    response.sendRedirect("/index.html");
  }
}