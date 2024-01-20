package com.example.myjavaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LogInWindow implements Initializable {


    @FXML
    private Button homeBtn;


    @FXML
    private TextField tf_getId;
    @FXML
    private TextField tf_passkey;

    @FXML
    private Text textForInt;

    @FXML
    private Button logInBtn;

    @FXML
    private TextField tf_stdAttendance;


    @FXML
    private Button presentBtn;


    @FXML
    private Text textStatus;

    private Stage stage;
    private Scene scene;
    private Parent root;



    public int getId() {

        TextField text = new TextField();
        text.setText(tf_getId.getText());
        return getId();
    }


    @FXML
    void signInAction(ActionEvent event) {

        if(event.getSource() == presentBtn){
            stdAttendance();
            forQueryAttendance();

        }

    }
    @FXML
    void logInAction(ActionEvent event) {
        if (event.getSource() == logInBtn) {
            loginScore();
            showStudentGrade();
        }
    }

    @FXML
    void getHome(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("FrontSign.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showStudentGrade() {

        Students student = new Students();
        String query = "SELECT count(1) FROM student_infos WHERE passkey = '" + tf_passkey.getText() + "' AND id = " + tf_getId.getText() + "";


        try {

            Connection connectDb = Connect();
            Statement statement = connectDb.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("studentLogin.fxml"));
                    Parent my_root = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(my_root));
                    stage.show();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public Connection Connect() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        return connectDB;
    }

    public String getQueryId() {

        Connection connectDb = Connect();


        String query = "SELECT * from student_infos,idToGetIn\n" +
                "WHERE student_infos.id = idToGetIn.keyId";
        Statement st;
        ResultSet rs;
        try {
            st = connectDb.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return query;
    }

    public void loginScore() {

        Connection connectDb = Connect();

        forStudentId id = new forStudentId();
        String query = "UPDATE idToGetIn SET keyid  = " + tf_getId.getText() + "";

        Statement st;

        int rs;
        try {
            st = connectDb.createStatement();
            rs = st.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void stdAttendance() {

        Connection connectDb = Connect();

        String query = "UPDATE stdattendance SET date = current_timestamp() , present = 'YES' where stdid =  "+tf_stdAttendance.getText()+"";

        Statement st;
        int rs;

        try {

            st = connectDb.createStatement();
            rs =st.executeUpdate(query);
            textStatus.setText("Logged In Successfully ");


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void forQueryAttendance(){

        Connection connectDb = Connect();

        String query = "INSERT INTO attendanceQuery(stdnt_id,date) VALUES("+tf_stdAttendance.getText()+",CURRENT_DATE)";

        Statement st;
        int rs;

        try {

            st = connectDb.createStatement();
            rs =st.executeUpdate(query);


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    }

