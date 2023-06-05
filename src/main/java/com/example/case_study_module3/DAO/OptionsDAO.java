package com.example.case_study_module3.DAO;

import com.example.case_study_module3.DAO.connection.MyConnection;
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

public class OptionsDAO {
    private Connection connection = MyConnection.getConnection();
    private static OptionsDAO optionsDAO;

    public static OptionsDAO getInstance() {
        if (optionsDAO == null) {
            optionsDAO = new OptionsDAO();
        }
        return optionsDAO;
    }

    public List<Options> findAll() {
        List<Options> optionsList = new ArrayList<>();
        String querry = "select * from options";
        try (PreparedStatement preparedStatement = connection.prepareStatement(querry)) {
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
}
