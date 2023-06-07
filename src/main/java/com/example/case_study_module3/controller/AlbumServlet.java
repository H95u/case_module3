package com.example.case_study_module3.controller;

import com.example.case_study_module3.DAO.AlbumDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
@MultipartConfig
@WebServlet(name = "AlbumServlet", value = "/album")
public class AlbumServlet extends HttpServlet {
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
                break;
            case "confirm":
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
            case "upload":
                upload(request, response);
                break;
            case "update":
                break;
            case "confirm":
                break;
            default:

                break;
        }
    }

    private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        int id = Integer.parseInt(request.getParameter("id"));
        AlbumDAO.getInstance().uploadNewImage(fileContent, id);
        response.sendRedirect("/partners?action=showAlbum&id=" + id);
    }
}