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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class FXMLUpdateController implements Initializable {

    @FXML
    private TextField tfnom1;
    @FXML
    private TextField tfprenom1;
    @FXML
    private TextField tfemail1;
    @FXML
    private TextField tflogin1;
    @FXML
    private TextField tfpassword1;
    @FXML
    private TextField tfrole1;
    @FXML
    private Button btnvalider;

    public TextField getTfnom1() {
        return tfnom1;
    }

    public void setTfnom1(String tfnom1) {
        this.tfnom1.setText(tfnom1); 
    }

    public TextField getTfprenom1() {
        return tfprenom1;
    }

    public void setTfprenom1(String tfprenom1) {
        this.tfprenom1.setText(tfprenom1);
    }

    public TextField getTfemail1() {
        return tfemail1;
    }

    public void setTfemail1(String tfemail1) {
        this.tfemail1.setText(tfemail1);
    }

    public TextField getTflogin1() {
        return tflogin1;
    }

    public void setTflogin1(String tflogin1) {
        this.tflogin1.setText(tflogin1);
    }

    public TextField getTfpassword1() {
        return tfpassword1;
    }

    public void setTfpassword1(String tfpassword1) {
        this.tfpassword1.setText(tfpassword1);
    }

    public TextField getTfrole1() {
        return tfrole1;
    }

    public void setTfrole1(String tfrole1) {
        this.tfrole1.setText(tfrole1);
    }

    public Button getBtnvalider() {
        return btnvalider;
    }

    public void setBtnvalider(Button btnvalider) {
        this.btnvalider = btnvalider;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void valider(ActionEvent event) throws IOException {
         utilisateurService cs=new utilisateurService();
 cs.update(new utilisateur (tfnom1.getText(),tfprenom1.getText(),tfemail1.getText(),tflogin1.getText(),tfpassword1.getText(),tfrole1.getText()));        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLutilisateur.fxml"));
        Parent root=loader.load();
        
        
        JOptionPane.showMessageDialog(null, "club Bien modifié");
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
   
    }
    
}
