package ClassWork.CW_09_09.Server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "helloServlets", urlPatterns = "/hi")

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        check
        http://localhost:8080/hello?name=2&lastname=3
         */
        PrintWriter writer = resp.getWriter();



        writer.println(req+" "+resp);
    }


}
