package HomeWork.HM_02_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpClients implements  Request{

    @Override
    public void get(String Url, String params) {
        try {
            URL url = new URL(Url+params);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
               System.out.println(content);
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void post(String Url, String params,String jsonInputString) {
        try {
            URL postUrl = new URL(Url+params);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            String token = "0d1987a4886e094386b826a24da2967fa90cd88c6356515efec116e64ab3dcea";
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setRequestProperty("Authorization", "Bearer " + token);
            postConnection.setDoOutput(true);

            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void head(String url) throws IOException, InterruptedException {
        HttpClient newHttpClient = HttpClient.newHttpClient();

        var newRequest = HttpRequest.newBuilder(URI.create(url))
                .method("HEAD", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<Void> response = newHttpClient.send(newRequest,
                HttpResponse.BodyHandlers.discarding());

        HttpHeaders keyValue = response.headers();
        keyValue.map().forEach((key, values) ->  System.out.printf("%s = %s%n", key, values));


    }

    @Override
    public void put(String url) throws InterruptedException {

        try {
            var httpClient = HttpClient.newHttpClient();
            String inputJson = "{\n" +"  \"lastname\": \"testput\",\n" + " \"type\": \"2\",\n" +"  \"age\": \"3\"\n" + "}";

            var newRequestPut = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(inputJson))
                    .build();

            var responsePut = httpClient.send(newRequestPut, HttpResponse.BodyHandlers.ofString());
            System.out.println(responsePut.body());

      }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String Url) {

    }

}
