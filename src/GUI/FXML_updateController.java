/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.event;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Service.EventService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXML_updateController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField emplacement;
    @FXML
    private TextField date;
     @FXML
     private TextField maxparticipant;
    @FXML
    private Button enregistrer;

  private Stage stage;
    private Stage dialogStage;
     private event evenement;
      private boolean buttonConfirmerClicked = false;

    public TextField getMaxparticipant() {
        return maxparticipant;
    }

    public void setMaxparticipant(TextField maxparticipant) {
        this.maxparticipant = maxparticipant;
    }

    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(TextField emplacement) {
        this.emplacement = emplacement;
    }

    public TextField getDate() {
        return date;
    }

    public void setDate(TextField date) {
        this.date = date;
    }

    public Button getEnregistrer() {
        return enregistrer;
    }

    public void setEnregistrer(Button enregistrer) {
        this.enregistrer = enregistrer;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public event getEvenement() {
        return evenement;
    }

    public void setEvenement(event evenement) {
        this.evenement = evenement;
       
        this.nom.setText(evenement.getNom());
        this.emplacement.setText(evenement.getEmplacement());
        this.date.setText(evenement.getDate());
    }

    public boolean isButtonConfirmerClicked() {
        return buttonConfirmerClicked;
    }

    public void setButtonConfirmerClicked(boolean buttonConfirmerClicked) {
        this.buttonConfirmerClicked = buttonConfirmerClicked;
    }
      
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }  
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    private void enregistrer(ActionEvent event) {
        
         EventService es = new EventService();

    
          evenement.setNom(nom.getText());
           evenement.setEmplacement(emplacement.getText());
           evenement.setDate(date.getText());
           evenement.setMaxparticipant(Integer.parseInt(maxparticipant.getText()));
           if ((nom.getText().equals(""))||(emplacement.getText().equals(""))||(maxparticipant.getText().equals("")))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur!!!");
alert.setHeaderText("Champs manquants!!");
alert.setContentText("Veuillez remplir tous les champs!");

alert.showAndWait();
                }

        else{
        es.update(evenement);
        
           buttonConfirmerClicked = true;
            dialogStage.close();
    }
    }
}
