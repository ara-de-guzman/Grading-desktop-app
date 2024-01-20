package com.example.myjavaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.EventObject;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.sql.DriverManager.getConnection;


public class frontSign implements Initializable {


    @FXML
    private Button cancelButton;

    @FXML
    private Button closeBtn;

    @FXML
    private Button loginButton;

    @FXML
    private Text loginMessageLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpBtn;

    @FXML
    private Button studentLogInButton;

    @FXML
    private Text signMessageLabel;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfFirst_name;
    @FXML
    private TextField tfLast_name;

    @FXML
    private TextField tfPassword;

    @FXML
    private TextField tfRePassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField usernameField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void loginButtonOnAction(ActionEvent event) throws IOException {
        if (usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false) {

            Connect();
            String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + usernameField.getText() +
                    "' AND PASSWORD =  '" + passwordField.getText() + "'";

            try {

                Connection connectDb = Connect();
                Statement statement = connectDb.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {

                        Stage stage;
                        Scene scene;
                        Parent root;
                        root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();




                    } else {
                        loginMessageLabel.setText("Username or password does not match");
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            loginMessageLabel.setText("Please enter your username and password");
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (tfPassword.getText().equals(tfRePassword.getText())) {
            addUser();
            clearText();



        } else {
            signMessageLabel.setText("PASSWORD DOES NOT MATCH");

        }

    }

    public Connection Connect() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        return connectDB;
    }

    public void cancelButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    void studentLogInWindow(ActionEvent event) throws IOException {
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logInWindow.fxml"));
        Parent my_root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(my_root));
        stage.show();

    }

    public void clearText(){

        tfUsername.clear();
        tfEmail.clear();
        tfFirst_name.clear();
        tfLast_name.clear();
        tfUsername.clear();
        tfPassword.clear();
        tfRePassword.clear();
    }

    public void addUser() {

        Connect();

        String first_name = tfFirst_name.getText();
        String last_name = tfLast_name.getText();
        String email = tfEmail.getText();
        String username =tfUsername.getText();
        String password = tfPassword.getText();
        String retypePass = tfPassword.getText();




        String insertData = "INSERT INTO user_account(first_name,last_name,email,username,password) VALUES ('"+first_name+"','" +last_name + "','"+email + "','"+username + "','"+ password+"')";


        try {
            if(password != retypePass){
                signMessageLabel.setText("PASSWORD DOES NOT MATCH");
            }
                Connection connectDb = Connect();
                Statement st = connectDb.createStatement();
                st.executeUpdate(insertData);
                signMessageLabel.setText("REGISTERED SUCCESSFULLY");

        } catch (SQLException e) {
                e.printStackTrace();
                e.getCause();
            }
        }
}


