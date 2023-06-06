package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.DataAccessException;
import passoffrequest.FillRequest;
import result.FillResponse;
import service.FillService;

import java.io.*;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                String uri = exchange.getRequestURI().getPath();
                String[] uriParts = uri.split("/");
                System.out.println(uriParts[0] + uriParts[1] + uriParts[2]);

                if (uriParts.length != 3 && uriParts.length != 4) {
                    throw new Exception("Error: Invalid URI");
                }

                FillService service = new FillService();
                FillResponse res;

                if (uriParts.length == 3) {
                    res = service.fill(uriParts[2], 4);
                }
                else {
                    res = service.fill(uriParts[2], Integer.parseInt(uriParts[3]));
                }


                if (res.getSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                Gson gson = new Gson();
                String response = gson.toJson(res);
                writeString(response, exchange.getResponseBody());
                exchange.getResponseBody().close();
            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            FillResponse res = new FillResponse(e.getMessage(), false);
            Gson gson = new Gson();
            String response = gson.toJson(res);
            writeString(response, exchange.getResponseBody());
            exchange.getResponseBody().close();
        }

    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
