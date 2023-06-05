package com.example.case_study_module3.service;

import com.example.case_study_module3.DAO.UserDAO;
import com.example.case_study_module3.model.User;
import com.example.case_study_module3.model.ValidateData;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class LoginService {
    private static LoginService loginService;

    public static LoginService getInstance() {
        if (loginService == null) {
            loginService = new LoginService();
        }
        return loginService;
    }

    public int login(HttpServletRequest request) {
        String userName = request.getParameter("userNameLogin");
        String passWord = request.getParameter("passWordLogin");
        List<User> userList = UserDAO.getInstance().findAllLoginInfo();
        for (User user : userList) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(passWord)) {
                return user.getId();
            }
        }
        return -1;
    }

    public boolean createNewAccount(HttpServletRequest request) {
        String username = request.getParameter("regUsername");
        String password = request.getParameter("regPassword");
        String email = request.getParameter("regEmail");
        String fullName = request.getParameter("regName");
        String phoneNumber = request.getParameter("regPhoneNumber");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("regAddress");
        String date = request.getParameter("regDOB");
        if (!date.equals("")) {
            LocalDate DOB = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
            return checkAllInfo(username, password, email, fullName, phoneNumber, gender, address, DOB);
        } else return false;
    }

    private static boolean checkAllInfo(String username, String password, String email, String fullName, String phoneNumber, int gender, String address, LocalDate DOB) {
        if (ValidateData.validateUserName(username) &&
                ValidateData.validatePassWord(password) &&
                ValidateData.validateEmail(email) &&
                ValidateData.validatePhone(phoneNumber)) {
            User user = new User(username, password, email, fullName, phoneNumber, gender, address, DOB);
            UserDAO.getInstance().createNewUser(user);
            return true;
        }
        return false;
    }
}
