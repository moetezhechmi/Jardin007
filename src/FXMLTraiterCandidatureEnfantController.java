/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.enfant;
import Entity.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.enfantService;
import service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class FXMLTraiterCandidatureEnfantController implements Initializable {

    @FXML
    private TableView<enfant> Table;
    @FXML
    private TableColumn<enfant, Integer> idenf;
    @FXML
    private TableColumn<enfant, String> nomenf;
    @FXML
    private TableColumn<enfant, String> prenomenf;
    @FXML
    private TableColumn<enfant, String> sexe;
    @FXML
    private TableColumn<enfant, Date> date_de_naissance;
    @FXML
    private TableColumn<enfant, String> photoenf;
    @FXML
    private TableColumn<enfant, String> nomjardin;
    @FXML
    private TableColumn<enfant, String> etat;
    @FXML
    private Button btnconfirmer;
    @FXML
    private Button btnrefuser;
private List<enfant> listenf;
    private ObservableList<enfant> observablelist;
    private enfant enfant;
    public enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(enfant enfant) {
        
                    etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
                    
        etat.setText(String.valueOf("Confimer"));
 }
    private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    
    public boolean isButtonConfirmerClicked() {
        return buttonConfirmerClicked;
    }

    public void setButtonConfirmerClicked(boolean buttonConfirmerClicked) {
        this.buttonConfirmerClicked = buttonConfirmerClicked;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         enfantService us = new enfantService();
        idenf.setCellValueFactory(new PropertyValueFactory<>("idenf"));
        nomenf.setCellValueFactory(new PropertyValueFactory<>("nomenf"));
        prenomenf.setCellValueFactory(new PropertyValueFactory<>("prenomenf"));
         sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
          date_de_naissance.setCellValueFactory(new PropertyValueFactory<>("date_de_naissance"));
           photoenf.setCellValueFactory(new PropertyValueFactory<>("photoenf"));
            nomjardin.setCellValueFactory(new PropertyValueFactory<>("jardin"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        listenf = us.displayAll();
        observablelist=FXCollections.observableArrayList(listenf);
        Table.setItems(observablelist);
    }    


    
        public boolean showGestionEvenementManipulation(enfant enfant) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLTraiterCandidatureEnfant.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion enf");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLTraiterCandidatureEnfantController controller = fxmlLoader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEnfant(enfant);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmerClicked();

    }

@FXML
    private void confirmer(ActionEvent event) throws IOException, SQLException {
         enfantService us = new enfantService();
 enfant Event=us.findById(enfant.getIdenf());

        us.updateAdmin(Event);
        System.out.println(Event); 
    }
    

    @FXML
    private void refuser(ActionEvent event) {
    }

    
}
