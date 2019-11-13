package com.company.servlets;

import com.company.servlets.models.User;
import com.company.servlets.repositories.UsersRepoIMLP;
import com.company.servlets.repositories.UsersRepository;
import com.company.servlets.services.AuthCheck;
import com.company.servlets.services.RegistrateService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class RegistationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("Email");
        String login = req.getParameter("Login");
        String password = req.getParameter("Password");
        String country = req.getParameter("Country");
        String userInfo = req.getParameter("Info");
        User newuser = new User(email, login, password, country, userInfo);
        AuthCheck authCheck = new AuthCheck();
        RegistrateService registrateService = new RegistrateService();
        if (authCheck.checkEmail(newuser)) {
            if (registrateService.registrateUser(newuser)) {
                req.getRequestDispatcher("hello.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("hide", "hide");
                req.getRequestDispatcher("registrationpage.jsp").forward(req,resp);
            }
        } else {
            req.getSession().setAttribute("hide","hide");
            resp.sendRedirect("/registration");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("hide","");
        req.getRequestDispatcher("registrationpage.jsp").forward(req, resp);
    }
}

