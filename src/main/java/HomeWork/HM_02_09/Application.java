package HomeWork.HM_02_09;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClients httpClient = new HttpClients();
        httpClient.get("https://jsonplaceholder.typicode.com/","posts?id=22");
     //   httpClient.post("https://gorest.co.in/","public/v1/users","{\"name\":\"Teeenali Dre\", \"gender\":\"male\", \"email\":\"tenali.frakmakrishna22@mail.com\", \"status\":\"active\"}");
        httpClient.head("https://google.com");
        httpClient.put("http://dummy.restapiexample.com/api/v1/update/4710");


    }
}
