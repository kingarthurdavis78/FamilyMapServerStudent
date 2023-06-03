package handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.AuthTokenAccess;
import dao.DataAccessException;
import dao.DatabaseAccess;
import dao.UserAccess;
import model.Authtoken;
import model.User;
import result.GetFamilyResponse;
import result.GetPersonResponse;
import service.PersonService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;

public class PersonHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
                try {
                        if (!exchange.getRequestMethod().toLowerCase().equals("get")) {
                                throw new Exception("Error: Invalid request type");
                        }

                        Headers reqHeaders = exchange.getRequestHeaders();
                        if (!reqHeaders.containsKey("Authorization")) {
                                throw new Exception("Error: Please provide an authorization token");
                        }
                        String authToken = reqHeaders.getFirst("Authorization");
                        DatabaseAccess db = new DatabaseAccess();
                        db.openConnection();
                        AuthTokenAccess aDao = new AuthTokenAccess(db.getConn());
                        Authtoken token = aDao.getAuthToken(authToken);
                        if (token == null) {
                                db.closeConnection(false);
                                throw new Exception("Error: Invalid authorization token");
                        }
                        UserAccess uDao = new UserAccess(db.getConn());
                        User user = uDao.getUser(token.getUsername());
                        db.closeConnection(true);
                        if (user == null) {
                                throw new Exception("Error: Invalid authorization token");
                        }

                        String uri = exchange.getRequestURI().getPath();
                        String[] uriParts = uri.split("/");

                        if (uriParts.length != 2 && uriParts.length != 3) {
                                throw new Exception("Error: Invalid URI");
                        }

                        if (uriParts.length == 2) {
                                PersonService service = new PersonService();
                                GetFamilyResponse result = service.getFamily(user.getUsername());

                                if (!result.getSuccess()) {
                                        throw new Exception(result.getMessage());
                                }

                                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                                Gson gson = new Gson();
                                String jsonStr = gson.toJson(result);
                                writeString(jsonStr, exchange.getResponseBody());
                                exchange.getResponseBody().close();
                        }

                        if (uriParts.length == 3) {
                                String personID = uriParts[2];
                                PersonService service = new PersonService();
                                GetPersonResponse result = service.getPerson(user, personID);
                                if (!result.getSuccess()) {
                                        throw new Exception(result.getMessage());
                                }
                                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                                Gson gson = new Gson();
                                String jsonStr = gson.toJson(result);
                                writeString(jsonStr, exchange.getResponseBody());
                                exchange.getResponseBody().close();
                        }

                } catch (Exception e) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        Gson gson = new Gson();
                        GetPersonResponse result = new GetPersonResponse(e.getMessage(), false);
                        writeString(gson.toJson(result), exchange.getResponseBody());
                        exchange.getResponseBody().close();
                        e.printStackTrace();
                }
        }

        private void writeString (String str, OutputStream os) throws IOException {
                OutputStreamWriter sw = new OutputStreamWriter(os);
                sw.write(str);
                sw.flush();
        }
}
