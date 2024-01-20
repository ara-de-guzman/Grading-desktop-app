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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddStudent implements Initializable {


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToHomePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private TableView<Students> tvStudent;
    @FXML
    private TableColumn<Students, Integer> col_Id;

    @FXML
    private TableColumn<Students, String> col_fname;

    @FXML
    private TableColumn<Students, String> col_lname;

    @FXML
    private TableColumn<Students, Integer> col_age;

    @FXML
    private TableColumn<Students, String> col_addy;

    @FXML
    private TableColumn<Students, String> col_dob;

    @FXML
    private TableColumn<Students, String> col_gender;


    @FXML
    private TableColumn<Students, String> col_passKey;

    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfFname;
    @FXML
    private TextField tfLname;

    @FXML
    private TextField tfPassKey;

    @FXML
    private TextField tfId;

    @FXML
    private Text textStatus;


    @FXML
    private Button homeBtn;

    @FXML
    private RadioButton maleRadio;


    @FXML
    private RadioButton femaleRadio;

    @FXML
    private DatePicker dobPicker;
    @FXML
    private Button upload;

    @FXML
    private ImageView imageview;

    @FXML
    private Button load;


    @FXML
    private TextField textnum;



    @FXML
    private void handleMouseAction(MouseEvent event){
        Students student = tvStudent.getSelectionModel().getSelectedItem();
        tfId.setText(""+ student.getId());
        tfFname.setText(student.getFname());
        tfLname.setText(student.getLname());
        tfAge.setText(""+ student.getAge());
        tfAddress.setText(student.getAddress());
        dobPicker.getValue();
        tfPassKey.setText(student.getPassKey());


    }


    @FXML
    void handleButtonOnAction(ActionEvent event) {
        if(event.getSource() == addBtn){
            addStudent();
            addOnAttendance();
            showStudents();
        }else if(event.getSource() == deleteBtn){
            deleteStudent();
        }else if(event.getSource() == editBtn) {
            updateStudent();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showStudents();
    }
    private ObservableList<Students> getStudentsList() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<Students> studentList = FXCollections.observableArrayList();

        String query = "SELECT * FROM student_infos";
        Statement st;
        ResultSet rs;

        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Students students;

            while(rs.next()){
                students = new Students( rs.getInt("id"),rs.getString("fname"),rs.getString("lname"), rs.getInt("age"),rs.getString("address"),rs.getString("gender"),rs.getDate("dob"),rs.getDouble("math1"),rs.getDouble("math2"),
                        rs.getDouble("sci1"),rs.getDouble("sci2"),rs.getDouble("eng1"),rs.getDouble("eng2"),rs.getDouble("ss1"),rs.getDouble("ss2"), rs.getDouble("ar1"),rs.getDouble("ar2"),rs.getDouble("hc1"),rs.getDouble("hc2"),rs.getDouble("finalGrade"),rs.getString("letterGrade"),rs.getString("passKey"));
                studentList.add(students);
            }


        } catch (SQLException e) {
        e.printStackTrace();
        }
        return studentList;
    }
    private void showStudents() {
        ObservableList<Students> list = getStudentsList();

        col_Id.setCellValueFactory(new PropertyValueFactory<Students, Integer>("Id"));
        col_fname.setCellValueFactory(new PropertyValueFactory<Students, String>("fname"));
        col_lname.setCellValueFactory(new PropertyValueFactory<Students, String>("lname"));
        col_age.setCellValueFactory(new PropertyValueFactory<Students, Integer>("Age"));
        col_addy.setCellValueFactory(new PropertyValueFactory<Students, String>("Address"));
        col_gender.setCellValueFactory(new PropertyValueFactory<Students,String>("gender"));
        col_dob.setCellValueFactory(new PropertyValueFactory<Students,String>("dob"));
        col_passKey.setCellValueFactory(new PropertyValueFactory<Students,String>("passKey"));

        tvStudent.setItems(list);
    }

    public void addStudent(){
        showStudents();
        textStatus.setText("Student Successfully Added");

    }
    public void addOnAttendance(){

        String query = "INSERT INTO stdattendance(stdId,fname,lname) VALUES ("+tfId.getText()+",'"+tfFname.getText()+"','"+tfLname.getText()+"')";
        executeQuery(query);
    }

    public void updateStudent(){
        String query = "UPDATE student_infos SET fname = '" + tfFname.getText() + "', lname = '" + tfLname.getText() +
                        "',age = " + tfAge.getText() + ", address = '" + tfAddress.getText() + "',gender = '"+chosenGender()+"',dob='"+getDOB()+"',passkey = '"+tfPassKey.getText()+"' WHERE id = " +tfId.getText() + "";
        executeQuery(query);
        showStudents();
        textStatus.setText("Student Information Updated");

    }

    public void deleteStudent(){
        String query =  "DELETE FROM student_infos WHERE id = " + tfId.getText() + "";
        executeQuery(query);
        showStudents();
        textStatus.setText("Student Successfully Deleted");

    }


    @FXML
    public void addImage(ActionEvent event) {


        Connection conn ;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "Newlife12!");
            String storeStatement = "INSERT INTO student_infos(id,fname,lname,age,gender,dob,address,passkey,img)  VALUES("+tfId.getText()+",'"+tfFname.getText()+"','"+tfLname.getText()+"',"+tfAge.getText()+",'"+chosenGender()+"','"+getDOB()+"','"+tfAddress.getText()+"','"+tfPassKey.getText()+"',?)";

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(upload.getScene().getWindow());

            PreparedStatement store = conn.prepareStatement(storeStatement);
            FileInputStream fileInputStream = new FileInputStream(file);
            store.setBinaryStream(1, fileInputStream, fileInputStream.available());
            store.execute();
            Image image = new Image(fileInputStream);
            textStatus.setText("Image Successfully Uploaded");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    void loadImg(ActionEvent event) {
        Connection conn ;


        try {

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "Newlife12!");
            String loadStatement = "SELECT img from student_infos WHERE id = ?";

            PreparedStatement retrieve = conn.prepareStatement(loadStatement);
            retrieve.setInt(1,Integer.parseInt(textnum.getText()));
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





    public String chosenGender(){

        if(femaleRadio.isSelected()){
            return  "F";
        }else{
            return "M";
        }
    }

    public LocalDate getDOB(){
        LocalDate dob = dobPicker.getValue();
        return dob;
    }

    public void executeQuery(String query){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement st;

        try{
            st = connectDB.createStatement();
            st.executeUpdate(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
