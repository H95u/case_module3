package com.example.case_study_module3.DAO;

import com.example.case_study_module3.DAO.connection.MyConnection;
import com.example.case_study_module3.model.User;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection = MyConnection.getConnection();
    private static UserDAO userDAO;

    public static UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    public List<User> findAllNoImage() {
        List<User> userList = new ArrayList<>();
        String querry = "SELECT * FROM user where image is null;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_name");
                int gender = resultSet.getInt("gender");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                int userRole = resultSet.getInt("user_role");
                userList.add(new User(id, username, password, email,
                        fullName, gender, dob, address, phoneNumber, userRole));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return userList;
    }

    public List<User> findAllHaveImage() {
        List<User> userList = new ArrayList<>();
        String querry = "SELECT * FROM user where image is not null;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_name");
                int gender = resultSet.getInt("gender");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                int userRole = resultSet.getInt("user_role");
                InputStream image = resultSet.getBlob("image").getBinaryStream();
                byte[] imageBytes = image.readAllBytes();
                userList.add(new User(id, username, password, email,
                        fullName, gender, dob, address, phoneNumber, userRole, imageBytes));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return userList;
    }

    public List<User> findAllLoginInfo() {
        List<User> userList = new ArrayList<>();
        String querry = "SELECT * FROM user;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                userList.add(new User(id, username, password));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return userList;
    }

    public void createNewUser(User user) {
        String querry = "insert into user(username,password,email,full_name,gender," +
                "dob,address,phone_number) values (?,?,?,?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getFullName());
            preparedStatement.setInt(5, user.getGender());
            LocalDate dob = user.getDob();
            java.sql.Date sqlDate = java.sql.Date.valueOf(dob);
            preparedStatement.setDate(6, sqlDate);
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public User findById(int id) {
        User user = null;
        String querry = "select * from user where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String fullName = resultSet.getString("full_name");
                int gender = resultSet.getInt("gender");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phone_number");
                int userRole = resultSet.getInt("user_role");
                Blob imageBlob = resultSet.getBlob("image");
                InputStream imageStream;
                if (imageBlob != null) {
                    imageStream = imageBlob.getBinaryStream();
                    byte[] imageBytes = imageStream.readAllBytes();
                    user = new User(id, username, password, email,
                            fullName, gender, dob, address, phoneNumber, userRole, imageBytes);
                } else {
                    user = new User(id, username, password, email,
                            fullName, gender, dob, address, phoneNumber, userRole, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void uploadImage(InputStream fileContent, int id) {
        String querry = "update user set image = ?  where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setBinaryStream(1, fileContent);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateInfo(User user) {
        String querry = "update user set email = ?, full_name = ? , gender = ? ,dob = ? ," +
                " address = ?, phone_number = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setInt(3, user.getGender());
            preparedStatement.setDate(4, Date.valueOf(user.getDob()));
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getPhoneNumber());
            preparedStatement.setInt(7, user.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
