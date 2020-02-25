package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author moetez
 */
  

public class ProfilResponsableController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    private Button lancerAJ;
       @FXML
    private Button divertissement;
       @FXML
    private Button lancerMod;
       @FXML
    private AnchorPane rootPane;
       @FXML
    private Button aff;
       @FXML
    private Button lancerAf;
   
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    } 
    
   /* @FXML
    void lancerAjoutJ(ActionEvent event) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("AjoutJardin.fxml")); 
        rootPane.getChildren().setAll(pane);
    }
    */
     @FXML
    void lancerModJ(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EditProfil.fxml"));
        rootPane.getChildren().setAll(pane); 

    }

    
     @FXML
    void listerD(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ListeDivert.fxml"));
        rootPane.getChildren().setAll(pane); 
    }

    
    @FXML
    void LancerAffichageJ(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("MesJardins.fxml"));
        rootPane.getChildren().setAll(pane);
        System.out.println("AFF");
    }
    
    @FXML
    void lancerAffich(ActionEvent event) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("FXMLaffichage.fxml")); 
        rootPane.getChildren().setAll(pane);

    }
    
    
    

}