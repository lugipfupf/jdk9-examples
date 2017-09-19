package ch.puzzle.javakafi.jdk9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

public class Http2 {
    private static final String URL = "http://transport.opendata.ch/v1/connections?from=Bern&to=z%C3%BCrich&datetime=2017-09-21T17%3A00";

    public static void main(String[] args) throws Exception {
        System.out.println("HTTP/2 Client interface with JDK 9-181");

        new Http2();
    }

    private Http2() throws Exception {
        basicHtttRequest();
        basicHttp2Request();
    }

    private void basicHtttRequest() throws Exception {
        System.out.println("Do request the old way:");

        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(responseCode);
        System.out.println(response.toString());
        System.out.println();
    }

    private void basicHttp2Request() throws Exception {
        System.out.println("Do request with new http2 client:");

        // build request
        HttpRequest request = HttpRequest
            .newBuilder()
            .uri(new URI(URL))
            .timeout(Duration.ofSeconds(5))
            .GET()
            .build();

        // build client and feed it the request we just created
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

        // behold: lots of unformatted JSON will be thrown at you
        System.out.println(response.statusCode());
        System.out.println(response.body());
        System.out.println();
    }
}
