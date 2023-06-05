package com.example.case_study_module3.DAO;

import com.example.case_study_module3.DAO.connection.MyConnection;
import com.example.case_study_module3.model.Booking;

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
        String query = "SELECT booking.id, booking.startTime, booking.endTime, partner.nickname, user.fullname"
                + "FROM booking INNER JOIN partner ON booking.partner_id = partner.id "
                + "INNER JOIN user ON booking.user_id = user.id;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                bookingList.add(new Booking(id, startTime, endTime));
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
                int user_id = resultSet.getInt("user_id");
                int partner_id = resultSet.getInt("partner_id");
                LocalDateTime startTime = resultSet.getTimestamp("startTime").toLocalDateTime();
                LocalDateTime endTime = resultSet.getTimestamp("endTime").toLocalDateTime();
                booking = new Booking(id, user_id, partner_id, startTime, endTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return booking;
    }
    public void deleteById (int id) {
        String query = "DELETE FROM booking WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
