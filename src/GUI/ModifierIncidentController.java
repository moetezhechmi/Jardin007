/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entitie.Incident;
import Service.ServiceIncident;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class ModifierIncidentController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextArea tfDes;
    @FXML
    private TextField tfCreated;
    @FXML
    private TextField tfDate;
    @FXML
    private Button btnValider;
    @FXML
    private TextField tfNom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Valider(ActionEvent event) throws IOException {
        Service.ServiceIncident cs = new ServiceIncident();
        cs.update(new Incident(tfNom.getText(), tfDes.getText(), tfCreated.getText(), tfDate.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherIncident.fxml"));
        Parent root = loader.load();

        JOptionPane.showMessageDialog(null, "incident Bien modifié");
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
