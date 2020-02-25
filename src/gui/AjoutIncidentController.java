/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entitie.Incident;
import Service.ServiceIncident;
import static Service.ServiceIncident.getDateOnCour;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class AjoutIncidentController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextArea tfDes;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnquitter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        ServiceIncident sp = new ServiceIncident();
        if ((tfNom.getText().equals("")) || (tfDes.getText().equals(""))) {

            if (tfNom.getText().equals("")) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Saisie");
                alert.setHeaderText("il faut saisir le nom de l'incident !");
                alert.showAndWait();
                Toolkit.getDefaultToolkit().beep();

            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Saisie");
                alert.setHeaderText("il faut saisir le corp de l'incident !");
                alert.showAndWait();
               Toolkit.getDefaultToolkit().beep();
            }
        } else {
            sp.insert(new Incident(tfNom.getText(), tfDes.getText(), getDateOnCour()));

            JOptionPane.showMessageDialog(null, "incident ajouté");
            tfNom.clear();
            tfDes.clear();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AcceuilIncident.fxml"));

                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Acceuil incident");
                stage.setScene(new Scene(root1));
                stage.show();

            } catch (IOException ex) {
                ex.getMessage();

            }

        }
    }

    @FXML
    private void quitter(ActionEvent event) {
        Stage S = (Stage) btnquitter.getScene().getWindow();
        S.close();

    }

}
