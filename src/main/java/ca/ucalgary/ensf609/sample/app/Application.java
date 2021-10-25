package ca.ucalgary.ensf609.sample.app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import ca.ucalgary.ensf609.sample.app.api.ApiUtils;
import ca.ucalgary.ensf609.sample.app.api.user.UserRegistrationHandler;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

class Application {

    public static void main(String[] args) throws IOException {
        int serverPort = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

        UserRegistrationHandler userRegistrationHandler = new UserRegistrationHandler(Configuration.getUserService(), Configuration.getObjectMapper(),
            Configuration.getErrorHandler());
        server.createContext("/api/users/register", userRegistrationHandler::handle);

        HttpContext context =server.createContext("/api/hello", (exchange -> {

            if ("GET".equals(exchange.getRequestMethod())) {
                Map<String, List<String>> params = ApiUtils.splitQuery(exchange.getRequestURI().getRawQuery());
                String noNameText = "Anonymous";
                String name = params.getOrDefault("name", List.of(noNameText)).stream().findFirst().orElse(noNameText);
                String respText = String.format("Hello %s!", name);
                exchange.sendResponseHeaders(200, respText.getBytes().length);
                OutputStream output = exchange.getResponseBody();
                output.write(respText.getBytes());
                output.flush();
            } else {
                exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
            }
            exchange.close();
        }));
        // In order add authentication to your app remove this line
       /* context.setAuthenticator(new BasicAuthenticator("myrealm") {
            @Override
            public boolean checkCredentials(String user, String pwd) {
                return user.equals("admin") && pwd.equals("admin");
            }
        });*/

        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Http server started in port "+serverPort);
    }
}
