package com.giniyatov.kr;

import com.giniyatov.kr.Member;
import com.giniyatov.kr.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(value = "/reg")
public class Reg extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println(password+ email);

        Member member = new Member(email,password);
        UserDaoImpl registerDao = new UserDaoImpl();
        String result = registerDao.insert(member);

        if (result.equals("successfully")){
            resp.sendRedirect("/login");
        }
    }
}
