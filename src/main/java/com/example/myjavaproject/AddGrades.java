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
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class AddGrades implements Initializable {

    DatabaseConnection connectNow = new DatabaseConnection();
    Students grades = new Students();


    Double math1 = grades.getMath1();
    Double math2 = grades.getMath2();

    Double sc1 = grades.getSci1();
    Double sc2 = grades.getSci2();

    Double eng1 = grades.getEng1();
    Double eng2 = grades.getEng2();

    Double ss1 = grades.getSs1();
    Double ss2 = grades.getSs2();

    Double ar1 = grades.getAr1();
    Double ar2 = grades.getAr2();

    Double hc1 = grades.getHc1();
    Double hc2 = grades.getHc2();


    @FXML
    private Button calculateBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private TableColumn<Students, Integer> col_finalGrade;

    @FXML
    private TableColumn<Students, String> col_firstName;

    @FXML
    private TableColumn<Students, String> col_lastName;

    @FXML
    private TableColumn<Students, Character> col_letterGrade;

    @FXML
    private TableColumn<Students, Integer> col_studentId;

    @FXML
    private Button homeBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField tf_ar1;

    @FXML
    private TextField tf_ar2;

    @FXML
    private TextField tf_eng1;

    @FXML
    private TextField tf_eng2;

    @FXML
    private TextField tf_hc1;

    @FXML
    private TextField tf_hc2;

    @FXML
    private TextField tf_math1;

    @FXML
    private TextField tf_math2;

    @FXML
    private TextField tf_sci1;

    @FXML
    private TextField tf_sci2;

    @FXML
    private TextField tf_ss1;

    @FXML
    private TextField tf_ss2;

    @FXML
    private TableView<Students> tv_studentGrade;

    @FXML
    private Text textResult;
    @FXML
    private Text textLetterGrade;


    @FXML
    private TextField tfshowId;




    @FXML
    private void handleMouseAction(MouseEvent event) {
        Students student = tv_studentGrade.getSelectionModel().getSelectedItem();

        tfshowId.setText("" + student.getId());
        tf_math1.setText(""+student.getMath1());
        tf_math2.setText(""+student.getMath2());
        tf_sci1.setText(""+student.getSci1());
        tf_sci2.setText(""+student.getSci2());
        tf_eng1.setText(""+student.getEng1());
        tf_eng2.setText(""+student.getEng2());
        tf_ss1.setText(""+student.getSs1());
        tf_ss2.setText(""+student.getSs2());
        tf_ar1.setText(""+student.getAr1());
        tf_ar2.setText(""+student.getAr2());
        tf_hc1.setText(""+student.getHc1());
        tf_hc2.setText(""+student.getHc2());


    }

    @FXML
    void handleButtonAction(ActionEvent event) {

        if (event.getSource() == calculateBtn ) {
            finalGrade();
            showLetter();
        } else if (event.getSource() == saveBtn) {
            savingGradesForStudents();
        } else if(event.getSource() == clearBtn){
            clearGrades();
        }

    }


    public Double firstSemScore() {
        math1 = Double.valueOf(tf_math1.getText());
        sc1 = Double.valueOf(tf_sci1.getText());
        eng1 = Double.valueOf(tf_eng1.getText());
        ss1 = Double.valueOf(tf_ss1.getText());
        ar1 = Double.valueOf(tf_ar1.getText());
        hc1 = Double.valueOf(tf_hc1.getText());

        Double firstScore = (math1 + sc1 + eng1 + ss1 + ar1 + hc1) / 6;

        return firstScore;
    }

    public Double secondSemScore() {
        math2 = Double.valueOf(tf_math2.getText());
        sc2 = Double.valueOf(tf_sci2.getText());
        eng2 = Double.valueOf(tf_eng2.getText());
        ss2 = Double.valueOf(tf_ss2.getText());
        ar2 = Double.valueOf(tf_ar2.getText());
        hc2 = Double.valueOf(tf_hc2.getText());

        Double secondScore = (math2 + sc2 + eng2 + ss2 + ar2 + hc2) / 6;

        return secondScore;
    }

    public Double finalGrade() {
        Double x = firstSemScore();
        Double y = secondSemScore();
        Double gradeResult = (x + y) / 2;
        DecimalFormat format = new DecimalFormat("0.#");

        if(gradeResult < 100) {
            textResult.setText(format.format(gradeResult));
        }else {
            textResult.setText("Invalid");
        }
        return gradeResult;

    }

    public String showLetterGrade(){
        Double gradeLetter = finalGrade();

        if (gradeLetter > 100){
            return "";
        }else if (gradeLetter >= 90) {
            return "A";
        } else if (gradeLetter >= 85) {
            return "B";
        } else if (gradeLetter >= 75) {
            return "C";
        } else if (gradeLetter >= 70) {
            return "D";
        } else {
           return "E";
        }
    }

    private void showLetter(){
        if(showLetterGrade().equals("")){
         textLetterGrade.setText("Invalid");
        } else if(showLetterGrade().equals("A")){
            textLetterGrade.setText("A");
        } else if (showLetterGrade().equals("B")){
            textLetterGrade.setText("B");
        } else if(showLetterGrade().equals("C")){
            textLetterGrade.setText("C");
        } else if (showLetterGrade().equals("D")) {
            textLetterGrade.setText("D");
        }else if(showLetterGrade().equals("E")) {
            textLetterGrade.setText("E");
        }else {
            textLetterGrade.setText("");
        }
    }
    private ObservableList<Students> getStudentGradeinfo() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDb = connectNow.getConnection();
        ObservableList<Students> studentsInfo = FXCollections.observableArrayList();

        String query = "SELECT * FROM student_infos";
        Statement st;
        ResultSet rs;

        try {
            st = connectDb.createStatement();
            rs = st.executeQuery(query);
            Students studentGrade;

            while (rs.next()) {
                studentGrade = new Students(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"), rs.getInt("age"), rs.getString("address"), rs.getString("gender"),rs.getDate("dob"),rs.getDouble("math1"), rs.getDouble("math2"),
                        rs.getDouble("sci1"), rs.getDouble("sci2"), rs.getDouble("eng1"), rs.getDouble("eng2"), rs.getDouble("ss1"), rs.getDouble("ss2"), rs.getDouble("ar1"), rs.getDouble("ar2"), rs.getDouble("hc1"), rs.getDouble("hc2"),rs.getDouble("finalGrade"),rs.getString("letterGrade"),rs.getString("passKey"));
                studentsInfo.add(studentGrade);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentsInfo;
    }

    private void showStudentsGrade() {
        ObservableList<Students> list = getStudentGradeinfo();
        col_studentId.setCellValueFactory(new PropertyValueFactory<Students, Integer>("Id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<Students, String>("fname"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<Students, String>("lname"));
        col_finalGrade.setCellValueFactory(new PropertyValueFactory<Students,Integer>("finalGrade"));
        col_letterGrade.setCellValueFactory(new PropertyValueFactory<Students,Character>("letterGrade"));

        tv_studentGrade.setItems(list);
    }

    private void savingGradesForStudents() {

        String query = "UPDATE student_infos SET math1 = " + tf_math1.getText() +",math2="+tf_math2.getText()+",sci1 = "+tf_sci1.getText()+",sci2 ="+tf_sci2.getText()+",eng1 = "+tf_eng1.getText()+",eng2 = "+tf_eng2.getText()+",ss1="+tf_ss1.getText()+",ss2="+tf_ss2.getText()+",ar1="+tf_ar1.getText()+",ar2 ="+tf_ar2.getText()+",hc1="+tf_hc1.getText()+",hc2 ="+tf_hc1.getText()+",finalGrade ="+finalGrade()+",letterGrade ='"+showLetterGrade()+"' WHERE id = " + tfshowId.getText()+ " ";
        AddStudent getQuery = new AddStudent();
        getQuery.executeQuery(query);
        showStudentsGrade();


    }

    private void clearGrades(){
        String query = "UPDATE student_infos SET math1 = null WHERE id = " + tfshowId.getText()+ " ";
        AddStudent getQuery = new AddStudent();
        getQuery.executeQuery(query);
        showStudentsGrade();

    }



    public void switchToHomePage(ActionEvent event) throws IOException {
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
        showStudentsGrade();
    }


}