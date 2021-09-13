package com.application;

import com.connection.DBConnect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {


    public Main() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void start(Stage stage) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/com/gui/viewMenu.fxml"));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Check-UP | Clínica Médica");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
    }
