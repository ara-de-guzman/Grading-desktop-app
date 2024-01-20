package com.example.myjavaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;


public class homePage implements Initializable {


    @FXML
    private Button logOutBtn;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private EventObject event;
    @FXML
    private Text textShowAStudent;
    @FXML
    private Text showNumberOfStudents;

    @FXML
    private Text textshowsFailedStudent;


    @FXML
    private Text textOnlyMale;

    @FXML
    private Text textOnlyFemale;

    @FXML
    private Text userNameText;

    @FXML
    private Text topstudnt;

    @FXML
    private Text topStdFName;
    @FXML
    private Text topStdLName;

    @FXML
    private Hyperlink link;


    @FXML
    private Text textDay;
    @FXML
    private Text textDate;

    @FXML
    private Text textDateNow;

    @FXML
    private Text birthDay;

    @FXML
    private Text showsBirthday;

    @FXML
    private Text userText;

    public void switchToAddStudent(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AddStudent.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1340, 680);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("FrontSign.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddGrades(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("addGrades.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1139, 680);
        stage.setScene(scene);
        stage.show();
    }

    public void searchStudent(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchStudent.fxml"));
        Parent my_root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(my_root));
        stage.show();
    }

    public void showsAttendance (ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("studentAttendance.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1250, 680);
        stage.setScene(scene);
        stage.show();
    }

    public Connection connect() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        return connectDB;
    }

    public void showNumberOfTotalStudent() {
        Connection connectDB = connect();

        String query = "select count(*) from student_infos;";
        Statement st;
        ResultSet rs;

        try {
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                showNumberOfStudents.setText(String.valueOf(count));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showUserAccount(){
        Connection connectDB = connect();
        String query = "select first_name from user_account";
        Statement st;
        ResultSet rs;

        try {
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {

                String fname = rs.getString("first_name");
                userText.setText(fname);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void showsAStudent() {
        Connection connectDB = connect();

        String query = "select count(finalGrade) from student_infos WHERE finalGrade > 90";
        Statement st;
        ResultSet rs;

        try {

            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                textShowAStudent.setText(String.valueOf(count));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showsFailedStudent() {

        Connection connectDB = connect();

        String query = "select count(finalGrade) from student_infos WHERE finalGrade < 74 ";
        Statement st;
        ResultSet rs;

        try {

            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                textshowsFailedStudent.setText(String.valueOf(count));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showsOnlyMale() {

        Connection connectDB = connect();

        String query = "select count(gender) from student_infos WHERE gender = 'M' ";
        Statement st;
        ResultSet rs;

        try {

            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                textOnlyMale.setText(String.valueOf(count));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showsOnlyFemale() {

        Connection connectDB = connect();

        String query = "select count(gender) from student_infos WHERE gender = 'F' ";
        Statement st;
        ResultSet rs;

        try {

            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                int count = rs.getInt(1);
                textOnlyFemale.setText(String.valueOf(count));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showsBirthday() {
        Connection connectDB = connect();
        String query = "SELECT fname,lname  FROM student_infos WHERE format(dob,'MM,dd') = format (current_date(),'MM,dd')";
        Statement st;
        ResultSet rs;

        try {

            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {

                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                showsBirthday.setText(fname + " " + lname);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showsTopStudent() {

        Connection connectDB = connect();

        String query = "select fname,lname,finalgrade from student_infos WHERE finalgrade = (SELECT MAX(finalgrade) from student_infos)";
        Statement st;
        ResultSet rs;

        try {

            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                String score = rs.getString("finalgrade");
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                topstudnt.setText("with the score of " + score);
                topStdFName.setText(String.valueOf(fname));
                topStdLName.setText(String.valueOf(lname));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dateNow() {
        LocalDate day = LocalDate.now();
        DateTimeFormatter formatDay = DateTimeFormatter.ofPattern("EEEE", Locale.getDefault());
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MMMM", Locale.forLanguageTag("DD"));
        DateTimeFormatter formatDateNow = DateTimeFormatter.ofPattern("d");
        DateTimeFormatter formatYear = DateTimeFormatter.ofPattern("YYYY");


        textDay.setText((day.format(formatDay)));
        textDate.setText((day.format(formatDate)) + " / "+ day.format(formatYear));
        textDateNow.setText(day.format(formatDateNow));



    }

    public void linkEmail(){
        Hyperlink emailLink = new Hyperlink("wwww.yahoo.com");
        emailLink.setVisited(true);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showNumberOfTotalStudent();
        showsAStudent();
        showsFailedStudent();
        showsOnlyMale();
        showsOnlyFemale();
        showsTopStudent();
        linkEmail();
        dateNow();
        showsBirthday();
        showUserAccount();
    }
}
