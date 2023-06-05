package com.example.case_study_module3.DAO;

import com.example.case_study_module3.DAO.connection.MyConnection;
import com.example.case_study_module3.model.Options;
import com.example.case_study_module3.model.Partner;
import com.example.case_study_module3.model.User;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PartnerDAO {
    private Connection connection = MyConnection.getConnection();
    private static PartnerDAO partnerDAO;

    public static PartnerDAO getInstance() {
        if (partnerDAO == null) {
            partnerDAO = new PartnerDAO();
        }
        return partnerDAO;
    }

    public List<Partner> findAll() {
        List<Partner> partnerList = new ArrayList<>();
        String querry = "select * from partner;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nickname = resultSet.getString("nickname");
                double hourlyRate = resultSet.getDouble("hourly_rate");
                int availability = resultSet.getInt("availability");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String address = resultSet.getString("address");
                int gender = resultSet.getInt("gender");
                Blob imageBlob = resultSet.getBlob("image");
                InputStream imageStream;
                List<Options> optionsList = findOption(id);
                if (imageBlob != null) {
                    imageStream = imageBlob.getBinaryStream();
                    byte[] imageBytes = imageStream.readAllBytes();
                    partnerList.add(new Partner(id, nickname,
                            hourlyRate, availability, imageBytes, dob, address, gender, optionsList));
                } else {
                    partnerList.add(new Partner(id, nickname,
                            hourlyRate, availability, null, dob, address, gender, optionsList));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partnerList;
    }

    public void updateInfo(Partner partner) {
        String querry = "update partner set nickname = ?, hourly_rate = ? , availability = ? ,dob = ? ," +
                " address = ?, gender = ? where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setString(1, partner.getNickname());
            preparedStatement.setDouble(2, partner.getHourlyRate());
            preparedStatement.setInt(3, partner.getAvailability());
            preparedStatement.setDate(4, Date.valueOf(partner.getDob()));
            preparedStatement.setString(5, partner.getAddress());
            preparedStatement.setInt(6, partner.getGender());
            preparedStatement.setInt(7, partner.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Partner findById(int id) {
        Partner partner = null;
        String querry = "select * from partner where id = ?; ";
        List<Options> optionsList = findOption(id);
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nickname = resultSet.getString("nickname");
                double hourlyRate = resultSet.getDouble("hourly_rate");
                int availability = resultSet.getInt("availability");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String address = resultSet.getString("address");
                int gender = resultSet.getInt("gender");
                Blob imageBlob = resultSet.getBlob("image");
                InputStream imageStream;
                if (imageBlob != null) {
                    imageStream = imageBlob.getBinaryStream();
                    byte[] imageBytes = imageStream.readAllBytes();
                    partner = new Partner(id, nickname,
                            hourlyRate, availability, imageBytes, dob, address, gender, optionsList);
                } else {
                    partner = new Partner(id, nickname,
                            hourlyRate, availability, null, dob, address, gender, optionsList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return partner;
    }

    public void uploadImage(InputStream fileContent, int id) {
        String querry = "update partner set image = ?  where id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setBinaryStream(1, fileContent);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createNewPartner(Partner partner) {
        String querry = "insert into partner(nickname,hourly_rate,availability,gender,address,dob) values(?,?,?,?,?,?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setString(1, partner.getNickname());
            preparedStatement.setDouble(2, partner.getHourlyRate());
            preparedStatement.setInt(3, partner.getAvailability());
            preparedStatement.setInt(4, partner.getGender());
            preparedStatement.setString(5, partner.getAddress());
            preparedStatement.setDate(6, Date.valueOf(partner.getDob()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Options> findOption(int partnerId) {
        List<Options> optionsList = new ArrayList<>();
        String querry = "select options.id,options.name,options.price from options " +
                "join partner_options on options.id = partner_options.options_id\n" +
                "    join partner on partner.id = options.id where partner_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setInt(1, partnerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                optionsList.add(new Options(id, name, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return optionsList;
    }
    public void deleteById (int id) {
        String query = "DELETE FROM partner WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
