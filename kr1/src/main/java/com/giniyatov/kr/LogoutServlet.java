package com.giniyatov.kr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet(name = "logoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie c: cookies) {
                c.setMaxAge(0);
                resp.addCookie(c);
            }
        }

        HttpSession httpSession = req.getSession(false);

        if (httpSession != null) {
            httpSession.invalidate();
        }

        resp.sendRedirect("home.jsp");

    }


}
