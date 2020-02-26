/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.event;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import package_Database.JavaMailUtil;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXML_frontController implements Initializable {

    @FXML
    private TableView<event> tableEvent;
    @FXML
    private TableColumn<event, String> nom;
    @FXML
    private TableColumn<event, String> emplacement;
    @FXML
    private TableColumn<event, String> date;
 
    @FXML
    private Button participer;
    private List<event> listevent;
    private ObservableList<event> observablelist;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventService us = new EventService();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        emplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
 
        
        listevent = us.displayAll();
        observablelist=FXCollections.observableArrayList(listevent);
     
        tableEvent.setItems(observablelist);
    }   

    @FXML
    private void participer(ActionEvent event) throws SQLException, Exception {
                        EventService es = new EventService();

        event evenement = (event) tableEvent.getSelectionModel().getSelectedItem();
        if (tableEvent.getSelectionModel().getSelectedItem()!=null) {
            int max = evenement.getMaxparticipant();
          if(evenement.getNbParticipe()< max ){
              es.participer(evenement);
              JavaMailUtil email = new JavaMailUtil();
                 email.sendMail("sanaferjardin@gmail.com");
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("sucess");
            alert.show();
               tableEvent.refresh();
               
          }else{
              Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("evenement complet");
            alert.show();
          }
              
 
   
          
        } 
    }
    
}
