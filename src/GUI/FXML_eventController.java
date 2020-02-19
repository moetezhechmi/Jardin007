/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.event;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class FXML_eventController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_emp;
    @FXML
    private TextField txt_date;
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
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficher;
    private List<event> listClub;
    private ObservableList<event> observablelist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         EventService es = new EventService();
        es.insert(new event(txt_nom.getText(),txt_emp.getText(),txt_date.getText()));
        JOptionPane.showMessageDialog(null, "Evenement ajouté");
        txt_nom.clear();
        txt_date.clear();
        txt_emp.clear();
    }
    

    @FXML
    private void modifier(ActionEvent event) {
    }

    @FXML
    private void supprimer(ActionEvent event) {
         EventService sa = new EventService();
        sa.delete(id.getCellData(tab_event.getSelectionModel().getSelectedIndex()));
        tab_event.refresh();
        event selectedItem=tab_event.getSelectionModel().getSelectedItem();
        tab_event.getItems().remove(selectedItem);
        JOptionPane.showMessageDialog(null, "Club Supprimé avec succès");
    }

    @FXML
    private void afficher(ActionEvent event) {
         EventService us = new EventService();
                 id.setCellValueFactory(new PropertyValueFactory<>("id"));

        tab_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tab_emp.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        tab_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        listClub = us.displayAll();
        observablelist=FXCollections.observableArrayList(listClub);
        tab_event.setItems(observablelist);
    }
    
    
}
