/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiReclamation;

import Entitie.Reclamation;
import Entitie.User;
import Service.ServiceReclamation;
import Utils.DataSource;
import Utils.JavaMailUtil;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private Button btnquitter;
    @FXML
    private TextField tfName;
    @FXML
    private TextArea tfDescription;
    @FXML
    private Button btnEnvoyer;
    @FXML
    private ComboBox<String> comboBox;
   
    @FXML
    private ChoiceBox  choiceBoxUrgent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            fillcomboname();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        User user = new User(1,"Amir","parent","amir.yazidi1@esprit.tn",false);
        choiceBoxUrgent.getItems().addAll("Haut","Moyenne","Faible");
       
        
        btnEnvoyer.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                try {
                    Reclamation rec = new Reclamation();
                    rec.setNom(comboBox.getValue());
                    rec.setId_user(user.getIdUser());
                    rec.setName(tfName.getText());
                    rec.setUrgence(choiceBoxUrgent.getValue().toString());
                    rec.setDescrition(tfDescription.getText());
                    rec.setUser_mail(user.getEmail());
                    
                    ServiceReclamation sr;
                    sr=new ServiceReclamation();
                    sr.insert(rec);
                    //JavaMailUtil email = new JavaMailUtil();
                    //email.sendMail("sanaferjardin@gmail.com");
                   
                } catch (Exception ex) {
                    Logger.getLogger(AjoutReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            
        });
        
        
       
    }    


    @FXML
    private void quitter(ActionEvent event) {
         Stage S = (Stage) btnquitter.getScene().getWindow();
        S.close();
    }
    
    ObservableList dd = FXCollections.observableArrayList();
  
   Connection con =DataSource.getInstance().getcnx();
   
   
    private void fillcomboname() throws SQLException{
    
        PreparedStatement stm;
    String Sql="Select nom from incident ";
    stm = con.prepareStatement(Sql);
        ResultSet rs = stm.executeQuery();
         
        while (rs.next()){
            
            dd.add(rs.getString("nom"));
            
            comboBox.setItems(dd);
        
        
        }
    
    }

    
}
