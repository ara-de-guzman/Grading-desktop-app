package com.example.myjavaproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class Main extends Application {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private EventObject event;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("frontSign.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 780, 680);
        stage.setTitle("Grading System");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }
}