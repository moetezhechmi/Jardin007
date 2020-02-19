/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.club;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author Daytech
 */
public class FXMLaffichageController implements Initializable {

@FXML
    private TableView<club> Table;
    @FXML
    private TableColumn<club, Integer> id;
    @FXML
    private TableColumn<club, String> nomclub;
    @FXML
    private TableColumn<club, String> description;
    
    private List<club> listClub;
    private ObservableList<club> observablelist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ClubService us = new ClubService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));
        listClub = us.displayAll();
        observablelist=FXCollections.observableArrayList(listClub);
        Table.setItems(observablelist);
    }    

    @FXML
    private void delete(ActionEvent event) {
                ClubService sa = new ClubService();
        sa.delete(id.getCellData(Table.getSelectionModel().getSelectedIndex()));
        Table.refresh();
        club selectedItem=Table.getSelectionModel().getSelectedItem();
        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("A confirmation dialog-box");
        dialogC.setHeaderText(null);
dialogC.setContentText("Voulez vous supprimer ce club?");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
System.out.println("User chose OK");
        Table.getItems().remove(selectedItem);

}
else {
System.out.println("User chose Cancel or closed the dialog-box");
}
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("crud.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update Club");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
     @FXML
    private void modifier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateFXML.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update Club");
        stage.setScene(new Scene(root1));
        stage.show();
        
        UpdateFXMLController uac=fxmlLoader.getController();
        uac.setTf_nom(nomclub.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTa_des(description.getCellData(Table.getSelectionModel().getSelectedIndex()));
      
        
    }
      @FXML
    private void tri(ActionEvent event) throws IOException {
           ClubService us = new ClubService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));
        listClub = us.triParNom();
        observablelist=FXCollections.observableArrayList(listClub);
        Table.setItems(observablelist);
    }
    
}
