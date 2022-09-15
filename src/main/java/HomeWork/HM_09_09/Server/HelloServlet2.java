package HomeWork.HM_09_09.Server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "helloServlet", urlPatterns = "/hello")

public class HelloServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        check
        http://localhost:8080/hello?name=2&lastname=3
         */
        PrintWriter writer = resp.getWriter();
        int firstname = Integer.parseInt(req.getParameter("name"));
        int lastname = Integer.parseInt(req.getParameter("lastname"));

        writer.println("Hello GET! Your name = " + firstname + "lastname = "+ lastname);
        writer.println(req+" "+resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        log in to http://localhost:8080/form.html . Fill in the data and the post request will work
        */
        int firstname = Integer.parseInt(req.getParameter("name"));
        int lastname = Integer.parseInt(req.getParameter("lastname"));
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("Hello POST! Your name = " + firstname + "lastname = "+ lastname);
        writer.println(req+" "+resp);
    }
}
