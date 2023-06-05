package com.example.case_study_module3.controller;

import com.example.case_study_module3.DAO.PartnerDAO;
import com.example.case_study_module3.DAO.UserDAO;
import com.example.case_study_module3.model.Partner;
import com.example.case_study_module3.model.User;
import com.example.case_study_module3.service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createGet(request, response);
                break;
            case "update":
                updateGet(request, response);
                break;
            case "login":
                login(request, response);
                break;
            default:
                findAll(request, response);
                break;
        }
    }


    private void updateGet(HttpServletRequest request, HttpServletResponse response) {
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createPost(request, response);
                break;
            case "update":

                break;
            case "login":
                login(request, response);
                break;
            default:

                break;
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = UserDAO.getInstance().findAllLoginInfo();
        request.getRequestDispatcher("/product/product.jsp").forward(request, response);
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int checkLogin = LoginService.getInstance().login(request);
        User user = UserDAO.getInstance().findById(checkLogin);
        if (checkLogin != -1) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/login/login.jsp?loginCode=1");
        }
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean checkRegister = LoginService.getInstance().createNewAccount(request);
        if (checkRegister) {
            response.sendRedirect("/login/login.jsp?regCode=1");
        } else {
            response.sendRedirect("/login/login.jsp?regCode=2");
        }
    }

}