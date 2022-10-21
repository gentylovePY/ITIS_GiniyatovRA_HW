package HomeWork.HM_09_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClients implements GetPostRequest {

    @Override
    public String get(String url, Map<String, String> params, Map<String, String> headers) {
        StringBuilder content = new StringBuilder();
        try {

            for (String paramss : params.keySet()) {
                URL urls = new URL(url + "?" + paramss + "=" + params.get(paramss));
                HttpURLConnection connection = (HttpURLConnection) urls.openConnection();
                connection.setRequestMethod("GET");

                for (String headerKey : headers.keySet()) {
                    connection.setRequestProperty(headerKey, headers.get(headerKey));
                }
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

                    String input;
                    while ((input = reader.readLine()) != null) {
                        content.append(input);
                    }
                    System.out.println(content);
                }

                connection.disconnect();

            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return String.valueOf(content);

    }


    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        StringBuilder content = new StringBuilder();
        try {
            URL postUrl = new URL(url);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();

            postConnection.setRequestMethod("POST");
            for (String headerKey : headers.keySet()) {
               // System.out.println(headerKey+ headers.get(headerKey));
                postConnection.setRequestProperty(headerKey, headers.get(headerKey));

            }

            postConnection.setDoOutput(true);

            try (OutputStream outputStream = postConnection.getOutputStream()) {
                byte[] input = params.toString().getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            System.out.println(postConnection.getResponseCode());

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(postConnection.getInputStream()))) {

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
       return content.toString();
    }

}

