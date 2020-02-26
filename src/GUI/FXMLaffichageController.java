/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Jardin;
import Entity.club;
import com.qoppa.pdfViewerFX.PDFViewer;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
      @FXML
    private TableColumn<club, Integer> nbparticipant;
      
            @FXML
    private TableColumn<club, String> NomJ;
            
            
            
            private Stage primaryStage;
    
    private List<club> listClub;
    private ObservableList<club> observablelist;
     @FXML
    private TextField tfsearch;
     private Stage stage;

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
        nbparticipant.setCellValueFactory(new PropertyValueFactory<>("nbparticipant"));
        NomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));
        List<club> list=us.displayAllC(1);
        observablelist=FXCollections.observableArrayList(list);
        Table.setItems(observablelist);
        
        
        
        /*            */
    /*    ClubService sa = new ClubService();
       observablelist.clear();
       observablelist.addAll(sa.displayAll());

       
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));
       
        Table.setItems(observablelist);

        FilteredList<club> filteredData = new FilteredList<>(observablelist, lu -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((club animal) -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(animal.getId()).contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (animal.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (animal.getDescr().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }  else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<club> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Table.setItems(sortedData);*/
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
                Table.refresh();

}
else {
System.out.println("User chose Cancel or closed the dialog-box");
}
       // JOptionPane.showMessageDialog(null, "Club Supprimé avec succès");

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
    
    
    
    public boolean showGestionEvenementManipulation(club evenement) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("updateFXML.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        UpdateFXMLController controller = fxmlLoader.getController();
        controller.setDialogStage(dialogStage);
        controller.setUtilisateur(evenement);

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();

        return controller.isButtonConfirmerClicked();

    }
    public void carregarTableViewEvenement() throws SQLException {
                     ClubService se = new ClubService();

            listClub = se.displayAllC(1);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

       nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));
       


        observablelist = FXCollections.observableArrayList(listClub);
        Table.setItems(observablelist);
        
    }
    
     @FXML
    private void modifier(ActionEvent event) throws IOException, SQLException {
       /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateFXML.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update Club");
        stage.setScene(new Scene(root1));
        stage.show();
        
        UpdateFXMLController uac=fxmlLoader.getController();
        uac.setId(id.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTf_nom(nomclub.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTa_des(description.getCellData(Table.getSelectionModel().getSelectedIndex()));*/
         ClubService us = new ClubService();

        club evenement = Table.getSelectionModel().getSelectedItem();
        if (Table.getSelectionModel().getSelectedItem()!=null) {
            boolean buttonConfirmerClicked = showGestionEvenementManipulation(evenement);
            if (buttonConfirmerClicked) {
              
    us.update(evenement);
                carregarTableViewEvenement();
   
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Selectionner un evenement");
            alert.show();

        }
      
        
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
    
    
    
    
      @FXML
    void actualiser(){
       
        ClubService us = new ClubService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));
        nbparticipant.setCellValueFactory(new PropertyValueFactory<>("nbparticipant"));
        NomJ.setCellValueFactory(new PropertyValueFactory<>("nomJ"));
        List<club> list=us.displayAllC(1);
        observablelist=FXCollections.observableArrayList(list);
        Table.setItems(observablelist);
        tfsearch.setText("");

        FilteredList<club> filteredData = new FilteredList<>(observablelist, lu -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((club animal) -> {
                // If filter text is empty, display all persons.

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(animal.getId()).contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (animal.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if (animal.getDescr().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }  else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<club> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Table.setItems(sortedData);
    }
    /* @FXML
    void pdf(){
        m_PDFViewer = new PDFViewer();
		BorderPane borderPane = new BorderPane(m_PDFViewer);
		Scene scene = new Scene(borderPane);
                Stage stage = new Stage();
		stage.setTitle("JavaFX PDFViewer - Qoppa Software");
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
    }*/
    
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("crud.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Gestion Evenement");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        CrudController controller = fxmlLoader.getController();
  

        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();
        
    }
    
}
