/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class RegistrationController implements Initializable {

    @FXML
    private Button btnresponsable;
    @FXML
    private Button btnparent;
    @FXML
    private Button btnretour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscriptionresponsable(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnresponsable.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("RegistrationResponsable.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void InscriptionParent(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnparent.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("RegistrationParent.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
            window.setScene(scene);  
             window.show();
    }
    
}
