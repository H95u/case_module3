package com.example.case_study_module3.controller;

import com.example.case_study_module3.DAO.BookingDAO;
import com.example.case_study_module3.DAO.OptionsDAO;
import com.example.case_study_module3.DAO.PartnerDAO;
import com.example.case_study_module3.DAO.UserDAO;
import com.example.case_study_module3.model.Booking;
import com.example.case_study_module3.model.Options;
import com.example.case_study_module3.model.Partner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@MultipartConfig
@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "searchByName":

                break;
            case "update":
                updatePartnerGet(request, response);
                break;
            case "partnerInfo":
                showPartnerInfo(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            case "search":
                searchByOption(request, response);
                break;
            case "searchByGender":
                searchByGender(request, response);
                break;
            default:
                findAll(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "searchByName":
                searchByName(request, response);
                break;
            case "update":
                updatePartnerPost(request, response);
                break;
            case "upload":
                uploadImage(request, response);
                break;
            default:

                break;
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Partner> partnerList = PartnerDAO.getInstance().findAll();
        List<Options> optionsList = OptionsDAO.getInstance().findAll();
        request.setAttribute("partnerList", partnerList);
        request.setAttribute("optionList", optionsList);
        List<Booking> bookingList = BookingDAO.getInstance().findAll();
        for (Partner partner : partnerList) {
            for (Booking booking : bookingList) {
                if (partner.getId() == booking.getPartner().getId()) {
                    partner.setAvailability(0);
                    PartnerDAO.getInstance().setAvailability(partner);
                }
            }
        }
        request.getRequestDispatcher("/home.jsp").forward(request, response);

    }

    private void showPartnerInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Partner partner = PartnerDAO.getInstance().findById(id);
        List<Options> optionsList = partner.getOptionsList();
        request.setAttribute("user", partner);
        request.setAttribute("optionList", optionsList);
        request.getRequestDispatcher("/partner/partner-info-admin.jsp").forward(request, response);
    }

    private static void uploadImage(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        int id = Integer.parseInt(request.getParameter("id"));
        PartnerDAO.getInstance().uploadImage(fileContent, id);
        response.sendRedirect("/home?action=partnerInfo&id=" + id);
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/home");
    }

    private void updatePartnerGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Partner partner = PartnerDAO.getInstance().findById(id);
        request.setAttribute("partner", partner);
        request.getRequestDispatcher("/partner/update-partner.jsp").forward(request, response);
    }

    private void updatePartnerPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nickname = request.getParameter("name");
        double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        int availability = Integer.parseInt(request.getParameter("availability"));
        String date = request.getParameter("DOB");
        LocalDate DOB = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        String address = request.getParameter("address");
        int gender = Integer.parseInt(request.getParameter("gender"));
        Partner partner = new Partner(id, nickname,
                hourlyRate, availability, DOB, address, gender);
        PartnerDAO.getInstance().updateInfo(partner);
        response.sendRedirect("/home?action=partnerInfo&id=" + id);
    }

    private void searchByOption(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int optionId = Integer.parseInt(request.getParameter("oid"));
        List<Partner> partnerList = PartnerDAO.getInstance().searchByOption(optionId);
        List<Options> optionsList = OptionsDAO.getInstance().findAll();
        request.setAttribute("partnerList", partnerList);
        request.setAttribute("optionList", optionsList);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void searchByGender(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int gender = Integer.parseInt(request.getParameter("gender"));
        List<Partner> partnerList = PartnerDAO.getInstance().searchByGender(gender);
        List<Options> optionsList = OptionsDAO.getInstance().findAll();
        request.setAttribute("partnerList", partnerList);
        request.setAttribute("optionList", optionsList);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Partner> partnerList = PartnerDAO.getInstance().searchByName(name);
        List<Options> optionsList = OptionsDAO.getInstance().findAll();
        request.setAttribute("partnerList", partnerList);
        request.setAttribute("optionList", optionsList);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }

}