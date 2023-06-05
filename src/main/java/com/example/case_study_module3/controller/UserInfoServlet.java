package com.example.case_study_module3.controller;

import com.example.case_study_module3.DAO.UserDAO;
import com.example.case_study_module3.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;


@MultipartConfig
@WebServlet(name = "UserInfoServlet", value = "/userInfo")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "update":
                updateGet(request, response);
                break;
            case "showInfo":
                showInfo(request, response);
                break;
            default:

                break;
        }
    }

    private static void showInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = UserDAO.getInstance().findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/user_info/user-info.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "upload":
                uploadImage(request, response);
                break;
            case "update":
                updatePost(request, response);
                break;
            case "login":
                break;
            default:

                break;
        }
    }

    private static void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        int id = Integer.parseInt(request.getParameter("id"));
        UserDAO.getInstance().uploadImage(fileContent, id);
        response.sendRedirect("/userInfo?action=showInfo&id=" + id);
    }

    private void updateGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = UserDAO.getInstance().findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/user_info/update-info.jsp").forward(request, response);
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String email = request.getParameter("regEmail");
        String fullName = request.getParameter("regName");
        String phoneNumber = request.getParameter("regPhoneNumber");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("regAddress");
        LocalDate DOB = LocalDate.parse(request.getParameter("regDOB"));
        User user = new User(id, email, fullName, phoneNumber, gender, address, DOB);
        UserDAO.getInstance().updateInfo(user);
        response.sendRedirect("/userInfo?action=showInfo&id=" + id);
    }

    private static int getAge(User user) {
        LocalDate dob = user.getDob();
        LocalDate now = LocalDate.now();
        Period age = dob.until(now);
        return age.getYears();
    }
}