/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.utilisateur;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class FXMLAjoutController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfrole;
    @FXML
    private Button btnajouter;
private List<utilisateur> listUser;
    private ObservableList<utilisateur> observablelist;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
        utilisateurService us = new utilisateurService();
         
        if ((tfnom.getText().equals(""))||(tfprenom.getText().equals(""))||(tfemail.getText().equals(""))||(tflogin.getText().equals(""))||(tfpassword.getText().equals(""))||(tfrole.getText().equals("")))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur!!!");
alert.setHeaderText("Champs manquants!!");
alert.setContentText("Veuillez remplir tous les champs!");

alert.showAndWait();
                }
        else{
        us.insert(new utilisateur(tfnom.getText(),tfprenom.getText(),tfemail.getText(),tflogin.getText(),tfpassword.getText(),tfrole.getText()));
        JOptionPane.showMessageDialog(null, "Utilisateur ajouté");
        tfnom.clear();
        tfprenom.clear();
        tfemail.clear();
        tflogin.clear();
        tfpassword.clear();
        tfrole.clear();
        
    }
        
    }
    
}
