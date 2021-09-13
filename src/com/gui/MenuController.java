package com.gui;

import com.connection.DBConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController {

    //CONTROLLERS
    @FXML
    private Button btnAgenda;

    @FXML
    private Button btnPacientes;

    @FXML
    private Button btnTarefas;

    @FXML
    private Button btnCid;



    //BOTÃO AGENDA
    public void onBtAgendaClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("sceneNotAvailable.fxml"));



            Parent parent = FXMLLoader.load(getClass().getResource("/com/gui/viewAgenda.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Agenda");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    //BOTAO PACIENTES
    public void onBtPacientesClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("sceneNotAvailable.fxml"));



            Parent parent = FXMLLoader.load(getClass().getResource("/com/gui/viewPacientes.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Pacientes");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void onBtCidClick() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnect.getInstance().getConnection();

        System.out.println(DBConnect.getInstance().getConnection());

        connection.close();


    }
}
