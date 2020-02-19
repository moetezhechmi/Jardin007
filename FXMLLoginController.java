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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label password;
    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button btnlogin;
private utilisateur u;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
       utilisateurService us = new utilisateurService();
       us.authentification(u);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLParent.fxml"));
        Parent root1 =  fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update User");
        stage.setScene(new Scene(root1));
        stage.show();
        
    }
    
}
