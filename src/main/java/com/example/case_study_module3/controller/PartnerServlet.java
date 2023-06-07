package com.example.case_study_module3.controller;

import com.example.case_study_module3.DAO.AlbumDAO;
import com.example.case_study_module3.DAO.OptionsDAO;
import com.example.case_study_module3.DAO.PartnerDAO;
import com.example.case_study_module3.model.Album;
import com.example.case_study_module3.model.Options;
import com.example.case_study_module3.model.Partner;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PartnerServlet", value = "/partners")
public class PartnerServlet extends HttpServlet {
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
                updateOptionGet(request, response);
                break;
            case "partnerInfo":
                showPartnerInfo(request, response);
                break;
            case "delete":
                deletePartner(request, response);
                break;
            case "showAlbum":
                showAlbum(request, response);
                break;
            case "updateOption":
                updateOptionGet(request, response);
                break;
            default:
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
            case "create":
                createPost(request, response);
                break;
            case "delete":
                deletePartner(request, response);
                break;
            case "updateOption":
                updateOptionPost(request, response);
                break;
            default:
                break;
        }
    }

    private void showPartnerInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Partner partner = PartnerDAO.getInstance().findById(id);
        request.setAttribute("user", partner);
        request.getRequestDispatcher("/partner/partner-info-admin.jsp").forward(request, response);
    }

    private void createGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/partner/new-partner.jsp");
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("name");
        double hourlyRate = Double.parseDouble(request.getParameter("hourlyRate"));
        int availability = Integer.parseInt(request.getParameter("availability"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("address");
        String dateStr = request.getParameter("DOB");
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        Partner partner = new Partner(nickname, hourlyRate, availability, gender, address, date);
        PartnerDAO.getInstance().createNewPartner(partner);
        response.sendRedirect("/home");
    }

    private void updateOptionGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Partner partner = PartnerDAO.getInstance().findById(id);
        List<Options> optionOfPartner = PartnerDAO.getInstance().findOption(id);
        List<Options> optionList = OptionsDAO.getInstance().findAll();
        request.setAttribute("partner", partner);
        request.setAttribute("optionList", optionList);
        request.setAttribute("optionOfPartner", optionOfPartner);
        request.getRequestDispatcher("/partner/update-options.jsp").forward(request, response);
    }

    private void deletePartner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PartnerDAO.getInstance().deleteById(id);
        response.sendRedirect("/home");
    }

    private void showAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pId = Integer.parseInt(request.getParameter("id"));
        Partner partner = PartnerDAO.getInstance().findById(pId);
        List<Album> albumList = AlbumDAO.getInstance().findAllByPartnerId(pId);
        request.setAttribute("partner", partner);
        request.setAttribute("albumList", albumList);
        request.getRequestDispatcher("/album/album-info.jsp").forward(request, response);
    }

    private void updateOptionPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Options> optionsList = new ArrayList<>();
        String[] selectedOptionIds = request.getParameterValues("option");
        int pId = Integer.parseInt(request.getParameter("id"));
        PartnerDAO.getInstance().findById(pId);
        for (int i = 0; i < selectedOptionIds.length; i++) {
            int oId = Integer.parseInt(selectedOptionIds[i]);
            optionsList.add(OptionsDAO.getInstance().finById(oId));
        }
        OptionsDAO.getInstance().deletePartnerOption(pId);
        for (Options options : optionsList) {
            OptionsDAO.getInstance().newPartnerOption(pId, options.getId());
        }
        response.sendRedirect("/home");
    }

}