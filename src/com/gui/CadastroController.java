package com.gui;

import com.connection.DBConnect;
import com.utils.Archive;
import com.utils.DateFormat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class CadastroController implements Initializable {

    int id = 1;
    Boolean IdExists = true;
    Archive arq = new Archive();

    @FXML
    private Label lblId;

    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfPatologia;

    @FXML
    private TextField tfDataNasc;

    @FXML
    private TextArea tfUltimapresc;

    @FXML
    private TextArea tfUltimaposologia;

    @FXML
    private Button salvar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        while(Archive.Read("paciente-"+id+".txt") != "no"){
            id++;
        }

        lblId.setText(""+id);

    }

    public void saveBtn(){

        Archive.WriteProntuario(id, "paciente-"+id+".txt", tfNome.getText(), tfPatologia.getText(), tfDataNasc.getText(), tfUltimapresc.getText(), tfUltimaposologia.getText());
    }
}
