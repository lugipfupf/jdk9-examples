package ch.puzzle.javakafi.jdk9;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.time.Duration;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

public class Http2 {
    public static void main(String[] args) throws Exception {
        System.out.println("HTTP/2 Client interface with JDK 9-181");

        new Http2();
    }

    private Http2() throws Exception {
        basicHtttRequest();
        basicHttp2Request();
    }

    private void basicHtttRequest() {

    }

    private void basicHttp2Request() throws URISyntaxException, java.io.IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://transport.opendata.ch/v1/connections?from=Bern&to=z%C3%BCrich&datetime=2017-09-21T17%3A00"))
            .timeout(Duration.ofSeconds(5))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

        // behold: lots of unformatted JSON will be thrown at you
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
