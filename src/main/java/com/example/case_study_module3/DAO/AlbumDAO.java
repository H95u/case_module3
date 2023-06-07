package com.example.case_study_module3.DAO;

import com.example.case_study_module3.DAO.connection.MyConnection;
import com.example.case_study_module3.model.Album;
import com.example.case_study_module3.model.Options;
import com.example.case_study_module3.model.Partner;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    private Connection connection = MyConnection.getConnection();
    private static AlbumDAO albumDAO;

    public static AlbumDAO getInstance() {
        if (albumDAO == null) {
            albumDAO = new AlbumDAO();
        }
        return albumDAO;
    }

    public List<Album> findAll() {
        List<Album> albumList = new ArrayList<>();
        String querry = "select * from album;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Blob imageBlob = resultSet.getBlob("image");
                int pId = resultSet.getInt("partner_id");
                Partner partner = PartnerDAO.getInstance().findById(pId);
                InputStream imageStream;
                if (imageBlob != null) {
                    imageStream = imageBlob.getBinaryStream();
                    byte[] imageBytes = imageStream.readAllBytes();
                    albumList.add(new Album(id, imageBytes, partner));
                } else {
                    albumList.add(new Album(id, null, partner));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return albumList;
    }

    public List<Album> findAllByPartnerId(int pId) {
        List<Album> albumList = new ArrayList<>();
        String querry = "select * from album where partner_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
            preparedStatement.setInt(1, pId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Blob imageBlob = resultSet.getBlob("image");
                Partner partner = PartnerDAO.getInstance().findById(pId);
                InputStream imageStream;
                if (imageBlob != null) {
                    imageStream = imageBlob.getBinaryStream();
                    byte[] imageBytes = imageStream.readAllBytes();
                    albumList.add(new Album(id, imageBytes, partner));
                } else {
                    albumList.add(new Album(id, null, partner));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return albumList;
    }

    public void uploadNewImage(InputStream fileContent, int id) {
        String query = "insert into album(image,partner_id) values(?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBinaryStream(1, fileContent);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
