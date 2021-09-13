package com.gui;

import com.utils.Alerts;
import com.utils.Archive;
import com.utils.DateFormat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AgendarConsultaController implements Initializable {

    @FXML
    private DatePicker data;

    @FXML
    private Button btnAgendar;

    @FXML
    private Button btnCarregar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField tfHorario;

    @FXML
    private TextField tfID;

    @FXML
    private Label lbNome_do_paciente;

    //BOTAO CARREGAR
    public void btCarregarAgendamentoClick(){
        String id_Paciente = "";
        DateFormat df = new DateFormat();
        String horario = tfHorario.getText();
        horario = Archive.FormatarHorario(horario);
        String dia = df.formatDate(data.getValue().toString());
        String path = dia+"--"+horario+".txt";

        id_Paciente = Archive.ReadSingleLine(path);
        String conteudo = Archive.ReadSingleLine("paciente-"+id_Paciente+".txt");
        lbNome_do_paciente.setText(conteudo.split(";")[1]);
        tfID.setText(id_Paciente);
    }

    //BOTAO AGENDAR
    public void btAgendarClick(){
        DateFormat df = new DateFormat();

        String conteudo = Archive.Read("paciente-"+tfID.getText()+".txt");
        String horario = tfHorario.getText();
        horario = Archive.FormatarHorario(horario);
        String dia = df.formatDate(data.getValue().toString());
        String path = dia+"--"+horario+".txt";

        if(Archive.Read(path) == "no"){
            Archive.WriteAgenda(path, tfID.getText());
            lbNome_do_paciente.setText(conteudo.split(";")[1]);
            Alerts.showAlert("!", "Consulta agendada com sucesso para dia "+dia, null, Alert.AlertType.CONFIRMATION);
        }else
            {
            Alerts.showAlert("Falha ao agendar", "Já existe um agendamento nesse dia e horário", null, Alert.AlertType.ERROR);
        }
    }

    //BOTAO EXCLUIR
    public void btExcluirClick(){
        DateFormat df = new DateFormat();

        String conteudo = Archive.Read("paciente-"+tfID.getText()+".txt");
        String horario = tfHorario.getText();
        horario = Archive.FormatarHorario(horario);
        String dia = df.formatDate(data.getValue().toString());
        String path = dia+"--"+horario+".txt";

        if(Archive.Read(path) != "no"){
            Archive.DeletarArquivo(path);
            Alerts.showAlert("!", "Agendamento excluído com sucesso", null, Alert.AlertType.CONFIRMATION);
            tfID.setText(null);
            tfHorario.setText(null);
            data.setValue(null);
            lbNome_do_paciente.setText(null);
        }else{
            Alerts.showAlert("Falha ao excluir agendamento", "O agendamento não existe, verifique os dados e tente novamente.", null, Alert.AlertType.ERROR);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
