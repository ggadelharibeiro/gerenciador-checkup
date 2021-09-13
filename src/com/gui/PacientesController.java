package com.gui;

import com.utils.Archive;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PacientesController implements Initializable {

    ObservableList lista = FXCollections.observableArrayList();
    String conteudo = "";
    String listagem ="";
    String ID = "", IDTarget = "";
    String Nome = "", TargetName = "";
    String primeiroNome = "";
    int contador = 0;
    int i = 1;

    @FXML
    private ListView lista_pacientes;

    @FXML
    private TextField tfCampoPesquisa;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private ChoiceBox cbTipoBusca;

    @FXML
    private Label lblTotalPacientes;


    //FUNCOES

    //PESQUISAR
    public void btPesquisarClick() {
        lblTotalPacientes.setText("0");
        contador = 0;
        limparLista();

        //BUSCA POR ID
        if(cbTipoBusca.getValue().equals("ID")) {

            IDTarget = tfCampoPesquisa.getText();

            while (!ID.equals(IDTarget)) {
                conteudo = Archive.Read("paciente-" + IDTarget + ".txt");
                //ID e NOME recolhido na leitura do arquivo
                ID = conteudo.split(";")[0];
                listagem = "#"+conteudo.split(";")[0] + " - " +conteudo.split(";")[1] + " - " + conteudo.split(";")[2];
            }
            contador++;
            lblTotalPacientes.setText(""+contador);


            lista.removeAll(lista);
            lista.add(listagem);
            lista_pacientes.setItems(lista);
        }

        //BUSCA POR NOME
        if(cbTipoBusca.getValue().equals("Nome")) {
            TargetName = tfCampoPesquisa.getText();
            int j = 1;
            ObservableList list1 = FXCollections.observableArrayList();

            while(Archive.Read("paciente-"+ j + ".txt") != "no"){
                j++;
            }

            while(i < j){
                conteudo = Archive.Read("paciente-"+ i + ".txt");
                i++;
                Nome = conteudo.split(";")[1];
                primeiroNome = Nome.split(" ")[0];
                listagem = "#"+conteudo.split(";")[0] + " - " +conteudo.split(";")[1] + " - " + conteudo.split(";")[2];
                if(Nome.equals(TargetName) || primeiroNome.equals(TargetName.split(" ")[0])) {
                    list1.add(listagem);
                    contador++;
                }

            }

            lista.removeAll(lista);
            lista_pacientes.setItems(list1);
            lblTotalPacientes.setText(""+contador);

        }
        i = 1;
        }


    //CADASTRAR
    public void btCadastrarClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("sceneNotAvailable.fxml"));



            Parent parent = FXMLLoader.load(getClass().getResource("/com/gui/viewCadastro.fxml"));
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

    //INICIALIZACAO DA PAGINA
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbTipoBusca.setValue("ID");
        cbTipoBusca.getItems().addAll("ID", "Nome");


        int j = 1;
        ObservableList list1 = FXCollections.observableArrayList();

        while(Archive.Read("paciente-"+ j + ".txt") != "no"){
            j++;
        }

        while(i < j) {
            conteudo = Archive.Read("paciente-" + i + ".txt");
            i++;
            Nome = conteudo.split(";")[1];
            primeiroNome = Nome.split(" ")[0];
            listagem = "#" + conteudo.split(";")[0] + " - " + conteudo.split(";")[1] + " - " + conteudo.split(";")[2];
            list1.add(listagem);
            contador++;
        }

        lista.removeAll(lista);
        lista_pacientes.setItems(list1);
        lblTotalPacientes.setText(""+contador);

        i = 1;
        contador = 0;
    }

    public void carregarLista(){
        lista.removeAll(lista);
        lista.addAll("Teste", "testando");
        lista_pacientes.getItems().addAll(lista);

    }

    public void limparLista(){
        lista_pacientes.setItems(null);
    }
}
