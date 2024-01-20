package com.example.myjavaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SearchStudent implements Initializable {

    @FXML
    private Button btnSearch;

    @FXML
    private Label stdAddress;

    @FXML
    private Label stdAge;

    @FXML
    private Label stdDOB;

    @FXML
    private Label stdFName;

    @FXML
    private Label stdGender;

    @FXML
    private Label stdId;

    @FXML
    private ImageView stdImage;

    @FXML
    private Label stdLName;

    @FXML
    private TextField tfSearchStd;

    @FXML
    void searchAction(ActionEvent event) {

        if(event.getSource() == btnSearch){
            searchStudent();
            loadImg();
        }
    }

    public void searchStudent()  {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String query = "SELECT id,fname,lname,age,address,gender,dob,img FROM student_infos WHERE fname = '" + tfSearchStd.getText() + "' OR lname = '"+ tfSearchStd.getText() + "' OR id = '"+tfSearchStd.getText()+"'";

        Statement st;
        ResultSet rs;

        try {


            st = connectDB.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {

                stdId.setText(rs.getString("id"));
                stdFName.setText(rs.getString("fname"));
                stdLName.setText(rs.getString("lname"));
                stdAge.setText(rs.getString("age"));
                stdAddress.setText(rs.getString("address"));
                stdGender.setText(rs.getString("gender"));
                stdDOB.setText(rs.getString("dob"));

                Blob blob = rs.getBlob(1);
                InputStream inputStream = blob.getBinaryStream();
                Image image = new Image(inputStream);
                stdImage.setImage(image);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void loadImg() {
        Connection conn;


        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "Newlife12!");
            String loadStatement = "SELECT img from student_infos WHERE id = '"+tfSearchStd.getText()+"' OR fname = '"+tfSearchStd.getText()+"' OR lname = '"+tfSearchStd.getText()+"'";

            PreparedStatement retrieve = conn.prepareStatement(loadStatement);
            ResultSet resultSet = retrieve.executeQuery();

            if (resultSet.next()) {
                Blob blob = resultSet.getBlob(1);
                InputStream inputStream = blob.getBinaryStream();
                Image image = new Image(inputStream);
                stdImage.setImage(image);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}