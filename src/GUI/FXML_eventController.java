/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Jardin;
import Entity.club;
import Entity.event;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Service.EventService;

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
   private TextField maxparticip;
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
    
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button afficher;

    @FXML
    private ComboBox<String> idJ;


    @FXML
    private TextField recherche;
    @FXML
    private Button chercher;
    private List<event> listevent;
    private ObservableList<event> observablelist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        
        EventService us = new EventService();
        String listG[] = {"Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Le Kef", "Mahdia", "Manouba",
            "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
        
        
       
        
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tab_emp.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        tab_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tab_max.setCellValueFactory(new PropertyValueFactory<>("maxparticipant"));
        NomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));
        List <event> list = us.displayAllC(1);
       
        observablelist=FXCollections.observableArrayList(list);
        tab_event.setItems(observablelist);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_ajout.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXML_ajoutController controller = fxmlLoader.getController();

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
    }
    public boolean showGestionEvenementManipulation(event evenement) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Update.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXML_updateController controller = fxmlLoader.getController();
        controller.setDialogStage(dialogStage);
        controller.setEvenement(evenement);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmerClicked();

    }
    public void carregarTableViewEvenement() throws SQLException {
            
        
        EventService us = new EventService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tab_emp.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        tab_date.setCellValueFactory(new PropertyValueFactory<>("date"));
                NomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));

        tab_max.setCellValueFactory(new PropertyValueFactory<>("maxparticipant"));
           List <event> list = us.displayAllC(1);
       
        observablelist=FXCollections.observableArrayList(list);
        tab_event.setItems(observablelist);
        
    }
 

    @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
                 EventService es = new EventService();

        event evenement = (event) tab_event.getSelectionModel().getSelectedItem();
        
         
        //  JOptionPane.showMessageDialog(null, evenement);
         
        if (tab_event.getSelectionModel().getSelectedItem()!=null) {
            boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
            //JOptionPane.showMessageDialog(null, evenement);
            if (buttonConfirmerClicked) {
              
                carregarTableViewEvenement();
   
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selectionner un evenement");
            alert.show();

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        EventService sa = new EventService();
        sa.delete( id.getCellData(tab_event.getSelectionModel().getSelectedIndex()));
        tab_event.refresh();
        event selectedItem=tab_event.getSelectionModel().getSelectedItem();
        tab_event.getItems().remove(selectedItem);
        //JOptionPane.showMessageDialog(null, "Club Supprimé avec succès");
    }

    @FXML
    private void afficher(ActionEvent event) {
        EventService us = new EventService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tab_emp.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        tab_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tab_max.setCellValueFactory(new PropertyValueFactory<>("maxparticipant"));
        NomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));
           List <event> list = us.displayAllC(1);
       
        observablelist=FXCollections.observableArrayList(list);
        tab_event.setItems(observablelist);
    }

    @FXML
    private void chercher(ActionEvent event) {
       EventService sa = new EventService();
       //observablelist.clear();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tab_emp.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        tab_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tab_max.setCellValueFactory(new PropertyValueFactory<>("maxparticipant"));
        NomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));
        List<event> list=sa.displayAllC(1);
        observablelist=FXCollections.observableArrayList(list);
        tab_event.setItems(observablelist);

        FilteredList<event> filteredData = new FilteredList<>(observablelist, lu -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((event evenement) -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(evenement.getId()).contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (evenement.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (evenement.getEmplacement().toLowerCase().contains(lowerCaseFilter)) {
                    return true;}
                    else if (evenement.getDate().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // // Filter matches last name.
                }  else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tab_event.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tab_event.setItems(sortedData);
    }
    }
    

