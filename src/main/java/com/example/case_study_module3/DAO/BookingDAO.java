package com.example.case_study_module3.DAO;

import com.example.case_study_module3.DAO.connection.MyConnection;
import com.example.case_study_module3.model.Booking;
import com.example.case_study_module3.model.Options;
import com.example.case_study_module3.model.Partner;
import com.example.case_study_module3.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private Connection connection = MyConnection.getConnection();
    private static BookingDAO bookingDAO;

    public static BookingDAO getInstance() {
        if (bookingDAO == null) {
            bookingDAO = new BookingDAO();
        }
        return bookingDAO;
    }

    public List<Booking> findAll() {
        List<Booking> bookingList = new ArrayList<>();
        String query = "select * from booking;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("user_id");
                int partnerId = resultSet.getInt("partner_id");
                int optionId = resultSet.getInt("option_id");
                User user = UserDAO.getInstance().findById(userId);
                Partner partner = PartnerDAO.getInstance().findById(partnerId);
                Options option = OptionsDAO.getInstance().finById(optionId);
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                bookingList.add(new Booking(id, user, partner, startTime, endTime, option));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookingList;
    }


    public Booking findById(int id) {
        Booking booking = null;
        String query = "SELECT * FROM booking WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                int partnerId = resultSet.getInt("partner_id");
                int optionId = resultSet.getInt("option_id");
                User user = UserDAO.getInstance().findById(userId);
                Partner partner = PartnerDAO.getInstance().findById(partnerId);
                Options option = OptionsDAO.getInstance().finById(optionId);
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                booking = new Booking(id, user, partner, startTime, endTime, option);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }

    public Booking findByUserId(int uId) {
        Booking booking = null;
        String query = "SELECT * FROM booking WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, uId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                booking = getBookingInfo(booking, resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }

    private static Booking getBookingInfo(Booking booking, ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int partnerId = resultSet.getInt("partner_id");
        int optionId = resultSet.getInt("option_id");
        User user = UserDAO.getInstance().findById(userId);
        Partner partner = PartnerDAO.getInstance().findById(partnerId);
        Options option = OptionsDAO.getInstance().finById(optionId);
        LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
        LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
        booking = new Booking(id, user, partner, startTime, endTime, option);
        return booking;
    }


    public void deleteById(int id) {
        String query = "DELETE FROM booking WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByUserId(int id) {
        String query = "DELETE FROM booking WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createNewBooking(Booking booking) {
        String query = "insert into booking (user_id,partner_id,startTime,endTime,option_id)" +
                " values(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, booking.getUser().getId());
            preparedStatement.setInt(2, booking.getPartner().getId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(booking.getStartTime()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getEndTime()));
            preparedStatement.setInt(5, booking.getOption().getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}