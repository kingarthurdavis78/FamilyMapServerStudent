package handlers;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;
import request.LoginRequest;
import result.LoginResponse;
import service.LoginService;
import com.google.gson.*;

/*
    The ListGamesHandler is the HTTP handler that processes
    incoming HTTP requests that contain the "/games/list" URL path.

    Notice that ListGamesHandler implements the HttpHandler interface,
    which is define by Java.  This interface contains only one method
    named "handle".  When the HttpServer object (declared in the Server class)
    receives a request containing the "/games/list" URL path, it calls
    ListGamesHandler.handle() which actually processes the request.
*/
public class LoginHandler implements HttpHandler {
    // Handles HTTP requests containing the "/routes/claim" URL path.
    // The "exchange" parameter is an HttpExchange object, which is
    // defined by Java.
    // In this context, an "exchange" is an HTTP request/response pair
    // (i.e., the client and server exchange a request and response).
    // The HttpExchange object gives the handler access to all of the
    // details of the HTTP request (Request type [GET or POST],
    // request headers, request body, etc.).
    // The HttpExchange object also gives the handler the ability
    // to construct an HTTP response and send it back to the client
    // (Status code, headers, response body, etc.).
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // This handler allows a "Ticket to Ride" player to claim ability
        // route between two cities (part of the Ticket to Ride game).
        // The HTTP request body contains a JSON object indicating which
        // route the caller wants to claim (a route is defined by two cities).
        // This implementation is clearly unrealistic, because it
        // doesn't actually do anything other than print out the received JSON string.
        // It is also unrealistic in that it accepts only one specific
        // hard-coded auth token.
        // However, it does demonstrate the following:
        // 1. How to get the HTTP request type (or, "method")
        // 2. How to access HTTP request headers
        // 3. How to read JSON data from the HTTP request body
        // 4. How to return the desired status code (200, 404, etc.)
        //		in an HTTP response
        // 5. How to check an incoming HTTP request for an auth token


        try {
            // Determine the HTTP request type (GET, POST, etc.).
            // Only allow POST requests for this operation.
            // This operation requires a POST request, because the
            // client is "posting" information to the server for processing.
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                // Extract the JSON string from the HTTP request body

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();

                // Read JSON string from the input stream
                String reqData = readString(reqBody);

                // Display/log the request JSON data

                Gson gson = new Gson();
                LoginRequest request = gson.fromJson(reqData, LoginRequest.class);

                LoginService service = new LoginService();
                LoginResponse result = service.login(request);

                String jsonStr = gson.toJson(result);
                System.out.println(jsonStr);
                if (result.getSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }
                writeString(jsonStr, exchange.getResponseBody());
                exchange.getResponseBody().close();

            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

            exchange.getResponseBody().close();

            e.printStackTrace();
        }
    }

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

}
