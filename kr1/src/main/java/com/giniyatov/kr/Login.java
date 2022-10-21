package com.giniyatov.kr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(value = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("email");
        String password = req.getParameter("password");



        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        UserDaoImpl loginDao = new UserDaoImpl();
        try {
            if (loginDao.validate(userDto)){
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("username",username);

                resp.sendRedirect("home.jsp");

            }else {
                resp.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    }

