package HomeWork.HM_09_09;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClients httpClients = new HttpClients();
        Map<String,String> params = new HashMap<>();
        Map<String,String> paramsPost = new HashMap<>();
        Map<String,String> headers = new HashMap<>();
        Map<String,String> headersPost = new HashMap<>();
        paramsPost.put("map","elef");
        params.put("foo1","bar1");
        headers.put("Content-Type","application/json");
        headersPost.put("Content-Type", "application/x-www-form-urlencoded");
        headersPost.put("User-Agent", "Mozilla/5.0");
        httpClients.get("https://postman-echo.com/get", params,headers);
        httpClients.post("https://postman-echo.com/post",headersPost,paramsPost);


    }
}
