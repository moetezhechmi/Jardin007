/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Daytech
 */
public class SampleController implements Initializable {
 @FXML
    private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 @FXML
    private void statistiques(ActionEvent event) throws IOException {
        
              AnchorPane pane= FXMLLoader.load(getClass().getResource("stat.fxml")); 
        rootPane.getChildren().setAll(pane);
    }
    @FXML
    private void lancerAjout(ActionEvent event) throws IOException {
        
              AnchorPane pane= FXMLLoader.load(getClass().getResource("crud.fxml")); 
        rootPane.getChildren().setAll(pane);
    }
      @FXML
    private void lancerAffich(ActionEvent event) throws IOException {
        
              AnchorPane pane= FXMLLoader.load(getClass().getResource("FXMLaffichage.fxml")); 
        rootPane.getChildren().setAll(pane);
    }  @FXML
    private void lancerModif(ActionEvent event) throws IOException {
        
              AnchorPane pane= FXMLLoader.load(getClass().getResource("updateFXML.fxml")); 
        rootPane.getChildren().setAll(pane);
    }
    
}
