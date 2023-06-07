package com.example.case_study_module3.controller;

import com.example.case_study_module3.DAO.BookingDAO;
import com.example.case_study_module3.DAO.OptionsDAO;
import com.example.case_study_module3.DAO.PartnerDAO;
import com.example.case_study_module3.DAO.UserDAO;
import com.example.case_study_module3.model.Booking;
import com.example.case_study_module3.model.Options;
import com.example.case_study_module3.model.Partner;
import com.example.case_study_module3.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "BookingServlet", value = "/booking")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "payment":
                payment(request, response);
                break;
            case "confirm":
                confirmBookingGet(request, response);
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
            case "create":
                createPost(request, response);
                break;
            case "update":
                break;
            case "confirm":
                confirmBookingPost(request, response);
                break;
            default:

                break;
        }
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean checkBooking = true;
        List<Booking> bookingList = BookingDAO.getInstance().findAll();
        int userId = Integer.parseInt(request.getParameter("uid"));
        for (Booking booking : bookingList) {
            if (booking.getUser().getId() == userId) {
                checkBooking = false;
            }
        }
        if (checkBooking) {
            int partnerId = Integer.parseInt(request.getParameter("pid"));
            int optionId = Integer.parseInt(request.getParameter("oid"));
            User user = UserDAO.getInstance().findById(userId);
            Partner partner = PartnerDAO.getInstance().findById(partnerId);
            Options option = OptionsDAO.getInstance().finById(optionId);
            String startTimeStr = request.getParameter("startTime");
            String endTimeStr = request.getParameter("endTime");
            if (startTimeStr.isEmpty() || endTimeStr.isEmpty()) {
                response.sendRedirect("/booking/cant-booking.jsp?bookingCode=3");
            } else {
                LocalDateTime startTime = LocalDateTime.parse(startTimeStr);
                LocalDateTime endTime = LocalDateTime.parse((endTimeStr));
                validateBookingTime(response, user, partner, option, startTime, endTime);
            }
        } else {
            response.sendRedirect("/booking/cant-booking.jsp?bookingCode=0");
        }
    }

    private static void validateBookingTime(HttpServletResponse response, User user, Partner partner, Options option, LocalDateTime startTime, LocalDateTime endTime) throws IOException {
        if (endTime.isAfter(startTime)) {
            BookingDAO.getInstance().createNewBooking(new Booking(user, partner, startTime, endTime, option));
            response.sendRedirect("/booking?action=confirm");
        } else if (endTime.isBefore(startTime)) {
            response.sendRedirect("/booking/cant-booking.jsp?bookingCode=1");
        } else {
            response.sendRedirect("/booking/cant-booking.jsp?bookingCode=2");
        }
    }

    private void confirmBookingGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Booking booking = BookingDAO.getInstance().findByUserId(user.getId());
        double optionPrice = booking.getOption().getPrice();
        double hourlyRate = booking.getPartner().getHourlyRate();
        Duration duration = Duration.between(booking.getStartTime(), booking.getEndTime());
        double totalPrice = (duration.toHours() * hourlyRate) + optionPrice;
        request.setAttribute("booking", booking);
        request.setAttribute("time", duration.toHours());
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("/booking/booking-info.jsp").forward(request, response);
    }

    private void confirmBookingPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BookingDAO.getInstance().deleteById(id);
        response.sendRedirect("/home");
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Booking booking = BookingDAO.getInstance().findByUserId(id);
        if (booking != null) {
            request.setAttribute("booking", booking);
            Duration duration = Duration.between(booking.getStartTime(), booking.getEndTime());
            request.setAttribute("time", duration.toHours());
            request.getRequestDispatcher("/payment/payment.jsp").forward(request, response);
        } else {
            response.sendRedirect("/booking/no-booking.jsp");
        }
    }

    private void payment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        BookingDAO.getInstance().deleteById(id);
        response.sendRedirect("/home");
    }

}