package HomeWork.HM_02_09;

import java.io.IOException;

public interface Request {
    public void get(String Url, String params);
    public  void post(String Url,String params,String jsonInputString);

    public void head(String Url) throws IOException, InterruptedException;

    public void put(String url) throws InterruptedException;

    public void delete(String url);


}
