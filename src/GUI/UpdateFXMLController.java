/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.club;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author Daytech
 */
public class UpdateFXMLController implements Initializable {

   
    @FXML
    private TextField tf_nom;
    @FXML
    private TextArea ta_des;

 @FXML
    private TextField id;
  private Stage stage;
  private club club;
 private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;
    
    
    
        public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    
    public boolean isButtonConfirmerClicked() {
        return buttonConfirmerClicked;
    }

    public void setButtonConfirmerClicked(boolean buttonConfirmerClicked) {
        this.buttonConfirmerClicked = buttonConfirmerClicked;
    }
    
    
    public club getUtilisateur() {
        return club;
    }

    public void setUtilisateur(club club) {
        this.club = club;
        this.tf_nom.setText(club.getNom());
        this.ta_des.setText(club.getDescr());
       

    }

    public void setId(int id) {
        this.id.setText(String.valueOf(id));
    }

    public TextField getId() {
        return id;
    }
    
   
    public TextField getTf_nom() {
        return tf_nom;
    }

    public void setTf_nom(String tf_nom) {
        this.tf_nom.setText(tf_nom); 
    }

    public TextArea getTa_des() {
        return ta_des;
    }

    public void setTa_des(String ta_des) {
         this.ta_des.setText(ta_des); 
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
       @FXML
 private void save(ActionEvent event) throws IOException, SQLException {
     /* ClubService cs=new ClubService();
        cs.update(new club(Integer.parseInt(id.getText()),tf_nom.getText(),ta_des.getText()),Integer.parseInt(id.getText()));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("crud.fxml"));
        
        Parent root=loader.load();
        
        
        JOptionPane.showMessageDialog(null, "club Bien modifié");
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();*/
   
           club.setNom(tf_nom.getText());
           club.setDescr(ta_des.getText());

           buttonConfirmerClicked = true;
            dialogStage.close();
    
   
 }  
    }