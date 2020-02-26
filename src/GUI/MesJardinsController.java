/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Adresse;
import Entity.Jardin;
import Service.jardinService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author moetez
 */


public class MesJardinsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Jardin> tableview;
    
     @FXML
    private TableColumn<Jardin, Integer> id;

    @FXML
    private TableColumn<Jardin, String> nom;

    @FXML
    private TableColumn<Jardin, String> mail;

    @FXML
    private TableColumn<Jardin, String> num;
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField filterField;
    
        private List<Jardin> listJardin;
        private ObservableList<Jardin> observableList;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jardinService us = new jardinService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        num.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        
        
        List<Jardin> list = us.getAll(2);
        ObservableList<Jardin> observablelist = FXCollections.observableArrayList(list);
        
        //tableview.setItems(observablelist);
        
        FilteredList<Jardin> filtredData= new FilteredList<>(observablelist, b ->true);
        filterField.textProperty().addListener((observable,oldValue,newValue) -> {
            Predicate<? super Jardin> Jardin;
            filtredData.setPredicate((Jardin jardin) -> {
                if (newValue == null || newValue.isEmpty()){
                return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                if(jardin.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if (jardin.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                }
                else if (jardin.getNumTel().indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else  
                         return false;
            } );
        });
        	
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Jardin> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);


        
    }
    
    public boolean showGestionEvenementManipulation(Jardin evenement) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditProfil.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        EditProfilController controller = fxmlLoader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUtilisateur(evenement);
        dialogStage.showAndWait();
        return controller.isButtonConfirmerClicked();
    }    
    public boolean showGestionEvenementManipulation2(Jardin evenement) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutDivert.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        AjoutDivertController controller = fxmlLoader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUtilisateur(evenement);
        dialogStage.showAndWait();
        return controller.isButtonConfirmerClicked();
    }
    
    
    public void carregarTableViewEvenement() throws SQLException {
       jardinService se = new jardinService();

        listJardin = se.displayAll();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        num.setCellValueFactory(new PropertyValueFactory<>("numTel"));
       


        ObservableList<Jardin> observablelist = FXCollections.observableArrayList(listJardin);
        tableview.setItems(observablelist);
        tableview.refresh();
        
    }
   

      @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
        jardinService us = new jardinService();
        
        Jardin evenement = tableview.getSelectionModel().getSelectedItem();
        if (tableview.getSelectionModel().getSelectedItem()!=null) {
            boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
            if (buttonConfirmerClicked) {
                
                carregarTableViewEvenement();
   
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selectionner une Jardin");
            alert.show();
        }
     
    }  
    
    @FXML
    void delete(ActionEvent event) {
         jardinService sa = new jardinService();
         sa.delete(id.getCellData(tableview.getSelectionModel().getSelectedIndex()));
         Jardin selectedItem=tableview.getSelectionModel().getSelectedItem();
         Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
         dialogC.setTitle("A confirmation dialog-box");
         dialogC.setHeaderText(null);
         dialogC.setContentText("Voulez vous supprimer cette Jardin?");
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
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        num.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        
        
        List<Jardin> list = us.getAll(2);
        ObservableList<Jardin> observablelist = FXCollections.observableArrayList(list);
        
        //tableview.setItems(observablelist);
        
        FilteredList<Jardin> filtredData= new FilteredList<>(observablelist, b ->true);
        filterField.textProperty().addListener((observable,oldValue,newValue) -> {
            Predicate<? super Jardin> Jardin;
            filtredData.setPredicate((Jardin jardin) -> {
                if (newValue == null || newValue.isEmpty()){
                return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                if(jardin.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                    return true;
                }
                else if (jardin.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                }
                else if (jardin.getNumTel().indexOf(lowerCaseFilter)!=-1)
                     return true;
                     else  
                         return false;
            } );
        });
        	
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Jardin> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableview.setItems(sortedData);

    }
    
    
    @FXML
    void divert(ActionEvent event) throws IOException {
        jardinService us = new jardinService();
        
        Jardin j = tableview.getSelectionModel().getSelectedItem();
        if (tableview.getSelectionModel().getSelectedItem()!=null) {
            boolean buttonConfirmerClicked = showGestionEvenementManipulation2(j);
            if (buttonConfirmerClicked) {
                
   
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selectionner une Jardin");
            alert.show();
        }
    }
    
    @FXML
    void ajout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjoutJardin.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        AjoutJardinController controller = fxmlLoader.getController();
        dialogStage.showAndWait();
        
    }

    private void setDialogStage(Stage dialogStage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setUtilisateur(Jardin evenement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean isButtonConfirmerClicked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void refresh(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     
}
