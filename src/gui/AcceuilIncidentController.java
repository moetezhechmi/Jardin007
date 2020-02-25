/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entitie.Incident;
import Service.ServiceIncident;
import Utils.PDFutil;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class AcceuilIncidentController implements Initializable {

    @FXML
    private TableView<Incident> table;
    @FXML
    private TableColumn<Incident, String> colNom;
    @FXML
    private TableColumn<Incident, String> colDes;
    @FXML
    private TableColumn<Incident, Date> colDate;

    private List<Incident> listIncident;
    private ObservableList<Incident> observablelist;
    @FXML
    private TextField chercher;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Service.ServiceIncident us = new ServiceIncident();

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_inc"));

        listIncident = us.displayAll();
        observablelist = FXCollections.observableArrayList(listIncident);
        table.setItems(observablelist);
        table.setEditable(true);

        colNom.setCellFactory(TextFieldTableCell.forTableColumn());
        colDes.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    @FXML
    public void Modifier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierIncident.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update incident");
        stage.setScene(new Scene(root1));
        stage.show();
       
        
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        ObservableList<Incident> listSelected = (ObservableList<Incident>) table.getSelectionModel().getSelectedItems();
        ServiceIncident cs = new ServiceIncident();
        for (Iterator<Incident> it = listSelected.iterator(); it.hasNext();) {
            Incident next = it.next();

            cs.delete(next);
        }
        table.getItems().removeAll(listSelected);
        Notifications.create().title("Notification").text("Vous aver supprimer un incident attention !!!")
                .darkStyle().position(Pos.BOTTOM_CENTER).showWarning();
    }

    @FXML
    private void search(KeyEvent event) {
        Incident inc = new Incident();
        ServiceIncident sv = new ServiceIncident();
        List<Incident> list1 = sv.displayAll();
        observablelist = FXCollections.observableArrayList(list1);

        FilteredList<Incident> filteredData = new FilteredList<>(observablelist, e -> true);

        chercher.setOnKeyReleased(e
                -> {
            chercher.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Incident>) new Predicate<Incident>() {

                    @Override
                    public boolean test(Incident Incident) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (Incident.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        } else if (Incident.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        if (Incident.getCreatedby().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        if (String.valueOf(Incident.getDate_inc()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        return false;

                    }
                });

            });

            SortedList<Incident> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            table.setItems(sortedData);
        });

    }

    public static java.sql.Date getDateOnCour() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutIncident.fxml"));

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Ajout incident");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    public void GenerateActivitePdf() throws SQLException, IOException, FileNotFoundException {
        PDFutil pdf = new PDFutil();

        try {
            pdf.listActivite();
        } catch (DocumentException ex) {
            Logger.getLogger(AcceuilIncidentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
@FXML
    private void onEditCellNom(TableColumn.CellEditEvent<Incident, String> event) {
        Incident r = (Incident) table.getSelectionModel().getSelectedItem();
        String cellColName = event.getTableColumn().getId();
        System.out.println("You edited a cell: id = " + r.getId_inc()+ " col = "+ event.getTableColumn().getId());//test affichage colonne
        r.setNom(event.getNewValue());
        ServiceIncident cs = new ServiceIncident();
        cs.modifierIncident(r, cellColName); 
    }
@FXML
    private void onEditCellDescription(TableColumn.CellEditEvent<Incident, String> event) {
        Incident r = (Incident) table.getSelectionModel().getSelectedItem();
        String cellColName = event.getTableColumn().getId();
        System.out.println("You edited a cell: id = " + r.getId_inc()+ " col = "+ event.getTableColumn().getId());//test affichage colonne
        r.setDescription(event.getNewValue());
        ServiceIncident cs = new ServiceIncident();
        cs.modifierIncident(r, cellColName); 
        
    }

 
  

}
