package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import result.ClearResponse;
import service.ClearService;

import java.io.*;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {
        // Determine the HTTP request type (GET, POST, etc.).
        // Only allow POST requests for this operation.
        // This operation requires a POST request, because the
        // client is "posting" information to the server for processing.
        if (exchange.getRequestMethod().toLowerCase().equals("post")) {

            ClearService service = new ClearService();
            ClearResponse result = service.clear();

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            Gson gson = new Gson();
            String jsonStr = gson.toJson(result);
            writeString(jsonStr, exchange.getResponseBody());
            exchange.getResponseBody().close();

            success = true;

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                exchange.getResponseBody().close();
            }

        }
    }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

            // We are not sending a response body, so close the response body
            // output stream, indicating that the response is complete.
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
