/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.commentaire;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.CommentaireService;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class CommentaireController implements Initializable {

    @FXML
    private TextArea txt;
    @FXML
    private Button bt1;
    
    
        public TextArea getTxt() {
        return txt;
    }

    public void setTxt(TextArea txt) {
        this.txt = txt;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        CommentaireService us = new CommentaireService();
        us.insert(new commentaire(txt.getText()));
        JOptionPane.showMessageDialog(null, "commentaire ajouté");
    }

    @FXML
    private void afficher(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("FXMLaff.fxml"));
        Parent root1= (Parent)fxmlloader.load();
        Stage stage = new Stage();
        stage.setTitle("up");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
