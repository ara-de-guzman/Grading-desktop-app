package com.example.myjavaproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class StudentAttendance implements Initializable {

    @FXML
    private TableColumn<StdAttendanceImpl, Date> col_date;

    @FXML
    private TableColumn<StdAttendanceImpl, String> col_late;

    @FXML
    private TableColumn<StdAttendanceImpl, String> col_stdFname;

    @FXML
    private TableColumn<StdAttendanceImpl, Integer> col_stdId;

    @FXML
    private TableColumn<StdAttendanceImpl, String> col_stdLname;
    @FXML
    private TableColumn<StdAttendanceImpl, String> col_present;

    @FXML
    private TableView<StdAttendanceImpl> tv_attendance;

    @FXML
    private TextField fromdate;

    @FXML
    private ImageView homepage;

    @FXML
    private Button searchResultBtn;

    @FXML
    private TextField todate;




    @FXML
    private TableColumn<AttendanceQuery, Integer> col_stdIdRes;

    @FXML
    private TableColumn<AttendanceQuery, Date> col_stdDateAndTImeRes;

    @FXML
    private TableView<AttendanceQuery> tv_queryAttendance;








    DatabaseConnection connectNow = new DatabaseConnection();

    Connection connectDb = connectNow.getConnection();






    public void showStudentAttendance(){
        ObservableList<StdAttendanceImpl> list = getStudentAttendance();

        col_stdId.setCellValueFactory(new PropertyValueFactory<StdAttendanceImpl,Integer>("stdid"));
        col_stdFname.setCellValueFactory(new PropertyValueFactory<StdAttendanceImpl,String>("fname"));
        col_stdLname.setCellValueFactory(new PropertyValueFactory<StdAttendanceImpl,String>("lname"));
        col_date.setCellValueFactory(new PropertyValueFactory<StdAttendanceImpl, Date>("date" ));
        col_present.setCellValueFactory(new PropertyValueFactory<StdAttendanceImpl, String>("present" ));


        tv_attendance.setItems(list);

    }



    private ObservableList<StdAttendanceImpl> getStudentAttendance() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();
        ObservableList<StdAttendanceImpl> studentsAttende = FXCollections.observableArrayList();

        String query = "SELECT * FROM stdattendance";
        Statement st;
        ResultSet rs;

        try {
            st = connectDb.createStatement();
            rs = st.executeQuery(query);
                StdAttendanceImpl studentAttendance;

            while (rs.next()) {
                studentAttendance = new StdAttendanceImpl(rs.getInt("stdId"), rs.getString("fname"), rs.getString("lname"), rs.getDate("date"),rs.getString("present"));
                studentsAttende.add(studentAttendance);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentsAttende;
    }

    //Query
    private ObservableList<AttendanceQuery> getQueryAttendance() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();
        ObservableList<AttendanceQuery> queryAttendance = FXCollections.observableArrayList();

        String query = "SELECT DISTINCT * FROM attendanceQuery WHERE date between '"+fromdate.getText()+"' AND '"+todate.getText()+"'";
        Statement st;
        ResultSet rs;

        try {
            st = connectDb.createStatement();
            rs = st.executeQuery(query);
            AttendanceQuery attendanceQuery;

            while (rs.next()) {

                attendanceQuery = new AttendanceQuery(rs.getInt("stdnt_id"),rs.getDate("date"));
                queryAttendance.add(attendanceQuery);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       return queryAttendance;
    }

    public void showQueryAttendance(){

        ObservableList<AttendanceQuery> list = getQueryAttendance();

        col_stdIdRes.setCellValueFactory(new PropertyValueFactory<AttendanceQuery,Integer>("stdnt_id"));
        col_stdDateAndTImeRes.setCellValueFactory(new PropertyValueFactory<AttendanceQuery,Date>("date"));


        tv_queryAttendance.setItems(list);
    }



    @FXML
    void gotohomepage(MouseEvent event) throws IOException {

        Stage stage;
        Scene scene;
        Parent root;

        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    showStudentAttendance();

    }
}
