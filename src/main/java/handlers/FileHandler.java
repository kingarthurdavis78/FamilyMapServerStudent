package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileHandler  implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String url = exchange.getRequestURI().toString();

        if (url.equals("/")) {
            url = "/index.html";
        }

        String filePath = "web" + url;

        File file = new File(filePath);
        if (file.exists()) {
            exchange.sendResponseHeaders(200, 0);
            OutputStream respBody = exchange.getResponseBody();
            Files.copy(file.toPath(), respBody);
            exchange.getResponseBody().close();
        }
        else {
            exchange.sendResponseHeaders(404, 0);
            exchange.getResponseBody().close();
        }
    }

}