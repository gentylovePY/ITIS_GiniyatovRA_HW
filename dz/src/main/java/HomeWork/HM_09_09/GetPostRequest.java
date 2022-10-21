package HomeWork.HM_09_09;

import java.util.Map;

public interface GetPostRequest {
    // https://postman-echo.com/get
    String get(String url, Map<String, String> headers, Map<String, String> params);

    //    https://postman-echo.com/post
    String post(String url, Map<String, String> headers, Map<String, String> params);

}
