/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiReclamation;

import Entitie.Incident;
import Entitie.Reclamation;
import Service.ServiceIncident;
import Service.ServiceReclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Iterator;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class AcceuilReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> table;
    @FXML
    private TextField chercher;
    @FXML
    private TableColumn<Reclamation, String> colNomInc;
    @FXML
    private TableColumn<Reclamation, String> colNomRec;
    @FXML
    private TableColumn<Reclamation, Date> colDate;
    @FXML
    private TableColumn<Reclamation, String> ColUrgence;
    
    private List<Reclamation> listReclamation;
    private ObservableList<Reclamation> observablelist;
    @FXML
    private TableColumn<Reclamation, String> colDesc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           Service.ServiceReclamation us = new ServiceReclamation();

        colNomInc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNomRec.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("createDat"));
        ColUrgence.setCellValueFactory(new PropertyValueFactory<>("urgence"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("descrition"));

        listReclamation = us.displayAll();
        observablelist = FXCollections.observableArrayList(listReclamation);
        table.setItems(observablelist);
        table.setEditable(true);

        colNomRec.setCellFactory(TextFieldTableCell.forTableColumn());
        ColUrgence.setCellFactory(TextFieldTableCell.forTableColumn());
        colDesc.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }    

    @FXML
    private void Supprimer(ActionEvent event) {
           ObservableList<Reclamation> listSelected = (ObservableList<Reclamation>) table.getSelectionModel().getSelectedItems();
        ServiceReclamation cs = new ServiceReclamation();
        for (Iterator<Reclamation> it = listSelected.iterator(); it.hasNext();) {
            Reclamation next = it.next();

            cs.delete(next);
        }
        table.getItems().removeAll(listSelected);
    }

    @FXML
    private void GenerateActivitePdf(ActionEvent event) {
    }


    @FXML
    private void search(KeyEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutReclamation.fxml"));

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajout incident");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
