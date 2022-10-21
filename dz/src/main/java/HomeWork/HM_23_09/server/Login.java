package HomeWork.HM_23_09.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.*;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("loginDate.ftl");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String dateBrt = req.getParameter("dateBrt");
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("name", name);
        httpSession.setAttribute("dateBrt", verificationDate(dateBrt));
        httpSession.setMaxInactiveInterval(60 * 60);
        req.getRequestDispatcher("usersInfo.ftl").forward(req, resp);

    }

    public static String verificationDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date.replace("-", ""), dateTimeFormatter);
        LocalDate localDate1 = LocalDate.now();

        long days = DAYS.between(localDate, localDate1);
        long months =  MONTHS.between(localDate, localDate1);
        long years =  YEARS.between(localDate, localDate1);

        return "дней =" + days + " месяцев =  " + months + " лет = " + years;
    }
}

