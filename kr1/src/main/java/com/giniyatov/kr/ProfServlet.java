package com.giniyatov.kr;

import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONException;
import org.cloudinary.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.giniyatov.kr.PostgresConnectionUtil.*;

@WebServlet(name = "ProfServlet", value = "/ProfServlet")
public class ProfServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("ajax.checkUsername(username)");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = String.valueOf(request.getSession(false).getAttribute("username"));
        String city = request.getParameter("ci");
        Date date = new Date();   // given date
        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date
        calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
        calendar.get(Calendar.HOUR);        // gets hour in 12h format
        calendar.get(Calendar.MONTH);
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        SimpleDateFormat sdf=new SimpleDateFormat("hh:mm:ss");
        String dateString=sdf.format(date);
        System.out.println(dateString);
        loadDriver(DRIVER);
        String result = "successfully";
        String sql = "insert into pr1 values(?,?,?)";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,user);
            preparedStatement.setString(2,city);
            preparedStatement.setString(3, sdf.format(date));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            result = "data failed";
            throw new RuntimeException(e);
        }
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=9bf73362c913f887d7d8ff91db6ff1ba");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

//            System.out.println(connection.getResponseCode());
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader =
                         new BufferedReader(
                                 new InputStreamReader(connection.getInputStream())
                         )) {

                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                System.out.println(content.toString());
            }

            connection.disconnect();
            String str = content.toString();
            String[] line = str.split(",\"feels_like\"");
            line = line[0].split("\"temp\":");
            String temperature = line[line.length - 1];
            double celciy = Double.parseDouble(temperature) - 273.15;
            System.out.println("Weather in " + city + " = " + celciy);
            PrintWriter out = response.getWriter();
            out.println("Weather in " + city + " = " + celciy);

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}
