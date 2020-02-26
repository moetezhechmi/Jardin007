/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class RegistrationParentController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tflogin;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private TextField tfrole;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnretour;

    public TextField getTfrole() {
        return tfrole;
    }

    public void setTfrole(TextField tfrole) {
        this.tfrole=tfrole;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfrole.setText(String.valueOf("Parent"));
        tfrole.setDisable(true);
    }    
        

    @FXML
    private void ajouterresponsable(ActionEvent event) {
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
        JOptionPane.showMessageDialog(null, "Parent ajouté");
        tfnom.clear();
        tfprenom.clear();
        tfemail.clear();
        tflogin.clear();
        tfpassword.clear();
        //tfrole.clear();
        
          }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Registration.fxml")));
            window.setScene(scene);  
             window.show();
    }
    
    
}
