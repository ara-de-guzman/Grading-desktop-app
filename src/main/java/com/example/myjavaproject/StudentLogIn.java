package com.example.myjavaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StudentLogIn implements  Initializable{


    @FXML
    private Button lgoutBtn;

    @FXML
    private Text txtAddress;

    @FXML
    private Text txtAge;

    @FXML
    private Text txtAr1;

    @FXML
    private Text txtAr2;

    @FXML
    private Text txtDob;

    @FXML
    private Text txtEng1;

    @FXML
    private Text txtEng2;

    @FXML
    private Text txtFinalGrade;

    @FXML
    private Text txtFname;

    @FXML
    private Text txtGender;

    @FXML
    private Text txtHc1;

    @FXML
    private Text txtHc2;

    @FXML
    private Text txtLetterGrade;

    @FXML
    private Text txtLname;

    @FXML
    private Text txtMath1;

    @FXML
    private Text txtMath2;

    @FXML
    private Text txtSci1;

    @FXML
    private Text txtSci2;

    @FXML
    private Text txtSs1;

    @FXML
    private Text txtSs2;

    @FXML
    private Text txtStudentId;
    @FXML
    private ImageView imageview;



    public ObservableList<Students> showStudentInfo() {

        LogInWindow in = new LogInWindow();
        String query = in.getQueryId();

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Students> studentList = FXCollections.observableArrayList();


        Statement st;
        ResultSet rs;
        try {
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Students students;

            while (rs.next()) {
                         students = new Students(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getInt("age"), rs.getString("address"), rs.getString("gender"), rs.getDate("dob"), rs.getDouble("math1"), rs.getDouble("math2"),
                         rs.getDouble("sci1"), rs.getDouble("sci2"), rs.getDouble("eng1"), rs.getDouble("eng2"), rs.getDouble("ss1"), rs.getDouble("ss2"), rs.getDouble("ar1"), rs.getDouble("ar2"), rs.getDouble("hc1"), rs.getDouble("hc2"), rs.getDouble("finalGrade"), rs.getString("letterGrade"),rs.getString("passKey"));
                         studentList.add(students);

                txtStudentId.setText(rs.getString("id"));
                txtFname.setText(rs.getString("fname"));
                txtLname.setText(rs.getString("lname"));
                txtAge.setText(rs.getString("age"));
                txtGender.setText(rs.getString("gender"));
                txtDob.setText(rs.getString("dob"));
                txtAddress.setText(rs.getString("address"));

                txtMath1.setText(rs.getString("math1"));
                txtMath2.setText(rs.getString("math2"));
                txtSci1.setText(rs.getString("sci1"));
                txtSci2.setText(rs.getString("sci2"));
                txtEng1.setText(rs.getString("eng1"));
                txtEng2.setText(rs.getString("eng2"));
                txtSs1.setText(rs.getString("ss1"));
                txtSs2.setText(rs.getString("ss2"));
                txtAr1.setText(rs.getString("ar1"));
                txtAr2.setText(rs.getString("ar2"));
                txtHc1.setText(rs.getString("hc1"));
                txtHc2.setText(rs.getString("hc2"));

                txtFinalGrade.setText(rs.getString("finalGrade"));
                txtLetterGrade.setText(rs.getString("letterGrade"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    public void loadImg() {
        Connection conn ;

        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "Newlife12!");
            String loadStatement = "SELECT img from student_infos,idToGetIn WHERE student_infos.id = idToGetIn.keyId";
            PreparedStatement retrieve = conn.prepareStatement(loadStatement);
            ResultSet resultSet = retrieve.executeQuery();

            if(resultSet.next()) {
                Blob blob = resultSet.getBlob(1);
                InputStream inputStream = blob.getBinaryStream();
                Image image = new Image(inputStream);
                imageview.setImage(image);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStudentInfo();
    }
}
