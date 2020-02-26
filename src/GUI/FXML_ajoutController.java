/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Service.EventService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXML_ajoutController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_emp;
    @FXML
    private TextField txt_date;
    @FXML
    private TextField maxparticip;
    @FXML
    private ComboBox<String> idJ;
    @FXML
    private TableView<event> tab_event;
     @FXML
    private TableColumn<event, Integer> id;
    @FXML
    private TableColumn<event, String> tab_nom;
    @FXML
    private TableColumn<event, String> tab_emp;
    @FXML
    private TableColumn<event, String> tab_date;
    @FXML
    private TableColumn<event, Integer> tab_max;

    @FXML
    private TableColumn<event,String> NomJ;
    
    private List<event> listevent;
    private ObservableList<event> observablelist;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventService us = new EventService();
        idJ.setValue("selectioner jardin");
        ArrayList<String> ListeJ = new ArrayList<>();
        ListeJ=(ArrayList<String>) us.getIdj();
        ObservableList<String> ComboData = FXCollections.observableArrayList(ListeJ);
        for(String s : ComboData){
        idJ.getItems().add(s);
        // TODO
    }
    }
      
      

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
    EventService es = new EventService();
        if ((txt_nom.getText().equals(""))||(txt_emp.getText().equals(""))||(maxparticip.getText().equals("")))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur!!!");
                alert.setHeaderText("Champs manquants!!");
                alert.setContentText("Veuillez remplir tous les champs!");
                alert.showAndWait();
            }
        else{
        es.insert(new event(txt_nom.getText(),txt_emp.getText(),txt_date.getText() ,Integer.parseInt(maxparticip.getText())),idJ.getValue());
       //JOptionPane.showMessageDialog(null, "EVENT ajouté");
        txt_nom.clear();
        txt_emp.clear();
         maxparticip.clear();
         

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_event.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Liste des club");
        stage.setScene(new Scene(root1));
        stage.show();
        }
    }
}
    

