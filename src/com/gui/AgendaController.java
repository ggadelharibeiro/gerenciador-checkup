package com.gui;

import com.utils.Archive;
import com.utils.Cids;
import com.utils.DateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgendaController implements Initializable {

    Archive arq = new Archive();
    ObservableList horarios_comparar = FXCollections.observableArrayList("09h00","09h30","10h00", "10h30", "11h00", "11h30", "12h00", "12h30", "13h00", "13h30",
                                                                    "14h00", "14h30", "15h00", "15h30", "16h00", "16h30", "17h00", "17h30", "18h00", "18h30",
                                                                    "19h00", "19h30", "20h00", "20h30", "21h00", "21h30", "22h00", "22h30", "23h00");

    ObservableList horario_fim = FXCollections.observableArrayList();
    ObservableList id_pacientes = FXCollections.observableArrayList();


    //CONTROLLERS
    @FXML
    private DatePicker data;

    @FXML
    private ChoiceBox horario;

    @FXML
    private Label nome;

    @FXML
    private Label patologia;

    @FXML
    private Label cid;

    @FXML
    private Label data_nasc;

    @FXML
    private Label idade;

    @FXML
    private Label ultima_prescricao;

    @FXML
    private Label posologia;

    @FXML
    private CheckBox sim;

    @FXML
    private CheckBox nao;

    @FXML
    private Button agendar_consulta;

    @FXML
    private Button btCarregar;

    public void onDateSelection(){
        horario.getItems().remove(0, horario.getItems().size());
        String hour = "";
        DateFormat df = new DateFormat();
        String dia = df.formatDate(data.getValue().toString());


        for(int i = 0; i < horarios_comparar.size(); i++){
            hour = horarios_comparar.get(i).toString();
            String path = dia+"--"+ hour +".txt";

            if(Archive.ReadSingleLine(path) != "no"){
                horario_fim.add(hour);
            }

            horario.getItems().addAll(horario_fim);
            horario_fim.removeAll(horario_fim);

        }

    }

    public void btCarregarClick(){
        DateFormat df = new DateFormat();
        String dia = df.formatDate(data.getValue().toString());
        String hour = horario.getValue().toString();

        String path = dia+"--"+ hour +".txt";

        String id = Archive.ReadSingleLine(path);

        String path2 = "paciente-"+id+".txt";
        String conteudo_pct = Archive.ReadSingleLine(path2);

        String nome_pct = "", patologia_pct = "", data_nasc_pct = "", prescricao_pct = "", posologia_pct = "";

        nome_pct = conteudo_pct.split(";")[1];
        patologia_pct = conteudo_pct.split(";")[2];
        data_nasc_pct = conteudo_pct.split(";")[3];

        if(conteudo_pct.split(";").length > 4) {
            prescricao_pct = conteudo_pct.split(";")[4];
            posologia_pct = conteudo_pct.split(";")[5];
        }


        Date data_de_hoje = new Date();



        String ano_atual = data_de_hoje.toString().split(" ")[5];
        String mes_atual = data_de_hoje.toString().split(" ")[2];

        int idade_pct = Integer.parseInt(ano_atual) - Integer.parseInt(data_nasc_pct.split("/")[2]);


        nome.setText(nome_pct);
        patologia.setText(patologia_pct);
        data_nasc.setText(data_nasc_pct);
        ultima_prescricao.setText(prescricao_pct);
        posologia.setText(posologia_pct);
        idade.setText(String.valueOf(idade_pct));


    }

    public void btAgendarClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("sceneNotAvailable.fxml"));



            Parent parent = FXMLLoader.load(getClass().getResource("/com/gui/viewAgendarConsulta.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Agendar nova consulta");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
