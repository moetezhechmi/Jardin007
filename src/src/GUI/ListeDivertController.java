/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Divertissement;
import Entity.Jardin;
import Service.jardinService;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author moetez
 */
public class ListeDivertController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Divertissement> tableview;
    @FXML
    private TableColumn<Divertissement, Integer> id;

    @FXML
    private TableColumn<Divertissement, String> nomD;

    @FXML
    private TableColumn<Jardin, String> nomJ;

    @FXML
    private TextField filterField;

    private List<Divertissement> listDivertissement;
    private ObservableList<Divertissement> observableList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jardinService us = new jardinService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomD.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));
        
        System.out.println("avant get");
        List<Divertissement> list = us.getAllD(2);
        
        ObservableList<Divertissement> observablelist = FXCollections.observableArrayList(list);
        
        // tableview.setItems(observablelist);
        
        FilteredList<Divertissement> filtredData= new FilteredList<>(observablelist, b ->true);
        filterField.textProperty().addListener((observable,oldValue,newValue) -> {
            Predicate<? super Divertissement> Divertissement;
            filtredData.setPredicate((Divertissement d) -> {
                if (newValue == null || newValue.isEmpty()){
                return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                if(d.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if (d.getNomJ().indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else  
                         return false;
            } );
        });
        	
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Divertissement> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);
        

        
    }
    @FXML
    void delete(ActionEvent event) {

         jardinService sa = new jardinService();
         sa.deleteD(id.getCellData(tableview.getSelectionModel().getSelectedIndex()));
         Divertissement selectedItem=tableview.getSelectionModel().getSelectedItem();
         Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
         dialogC.setTitle("A confirmation dialog-box");
         dialogC.setHeaderText(null);
         dialogC.setContentText("Voulez vous supprimer ce Divertissement?");
         Optional<ButtonType> answer = dialogC.showAndWait();
         if (answer.get() == ButtonType.OK) {
         System.out.println("User chose OK");
         tableview.getItems().remove(selectedItem);
         this.refresh(event);
    }
         else {
         System.out.println("User chose Cancel or closed the dialog-box");
         }
    }
     @FXML
    void refresh(ActionEvent event) {
        jardinService us = new jardinService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomD.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));
        
        System.out.println("avant get");
        List<Divertissement> list = us.getAllD(2);
        
        ObservableList<Divertissement> observablelist = FXCollections.observableArrayList(list);
        
        // tableview.setItems(observablelist);
        
        FilteredList<Divertissement> filtredData= new FilteredList<>(observablelist, b ->true);
        filterField.textProperty().addListener((observable,oldValue,newValue) -> {
            Predicate<? super Divertissement> Divertissement;
            filtredData.setPredicate((Divertissement d) -> {
                if (newValue == null || newValue.isEmpty()){
                return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                if(d.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if (d.getNomJ().indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else  
                         return false;
            } );
        });
        	
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Divertissement> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);
        

    }

} 

