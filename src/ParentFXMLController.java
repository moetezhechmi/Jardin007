/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Session;
import Entity.enfant;
import Entity.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.enfantService;
import service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class ParentFXMLController implements Initializable {

    private Button btnprofil;
    @FXML
    private TableView<enfant> Table;
    @FXML
    private TableColumn<enfant, Integer> idenf;
    @FXML
    private TableColumn<enfant, String> nomenf;
    @FXML
    private TableColumn<enfant, String> prenomenf;
    @FXML
    private TableColumn<enfant, String> sexe;
    @FXML
    private TableColumn<enfant, Date> date_de_naissance;
    @FXML
    private TableColumn<enfant, String> photoenf;
    @FXML
    private TableColumn<enfant, String> nomjardin;
    @FXML
    private TableColumn<enfant, String> etat;
private List<enfant> listenf;
    private ObservableList<enfant> observablelist;
    @FXML
    private Button btnconfirmer;
    @FXML
    private Button btnrefuser;

    public TableColumn<enfant, Integer> getIdenf() {
        return idenf;
    }

    public void setIdenf(TableColumn<enfant, Integer> idenf) {
        this.idenf = idenf;
    }

    public TableColumn<enfant, String> getNomenf() {
        return nomenf;
    }

    public void setNomenf(TableColumn<enfant, String> nomenf) {
        this.nomenf = nomenf;
    }

    public List<enfant> getListenf() {
        return listenf;
    }

    public void setListenf(List<enfant> listenf) {
        this.listenf = listenf;
    }

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        enfantService us = new enfantService();
        idenf.setCellValueFactory(new PropertyValueFactory<>("idenf"));
        nomenf.setCellValueFactory(new PropertyValueFactory<>("nomenf"));
        prenomenf.setCellValueFactory(new PropertyValueFactory<>("prenomenf"));
         sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
          date_de_naissance.setCellValueFactory(new PropertyValueFactory<>("date_de_naissance"));
           photoenf.setCellValueFactory(new PropertyValueFactory<>("photoenf"));
            nomjardin.setCellValueFactory(new PropertyValueFactory<>("jardin"));
            etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        listenf = us.displayAll();
        observablelist=FXCollections.observableArrayList(listenf);
        Table.setItems(observablelist);
        
      
    }    

  

   
@FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLajoutEnf.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Ajout utilisateur");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLajoutEnfController controller = fxmlLoader.getController();
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();     
    }
  @FXML

    private void supprimer(ActionEvent event) {
        enfantService sa = new enfantService();
        sa.delete(idenf.getCellData(Table.getSelectionModel().getSelectedIndex()));
        Table.refresh();
        enfant selectedItem=Table.getSelectionModel().getSelectedItem();
        Table.getItems().remove(selectedItem);
        JOptionPane.showMessageDialog(null, "enfant Supprimé avec succès");
    }
@FXML
    private void afficher(ActionEvent event) {
        enfantService us = new enfantService();
        idenf.setCellValueFactory(new PropertyValueFactory<>("idenf"));
        nomenf.setCellValueFactory(new PropertyValueFactory<>("nomenf"));
        prenomenf.setCellValueFactory(new PropertyValueFactory<>("prenomenf"));
        sexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        date_de_naissance.setCellValueFactory(new PropertyValueFactory<>("date_de_naissance"));
        photoenf.setCellValueFactory(new PropertyValueFactory<>("photoenf"));
        nomjardin.setCellValueFactory(new PropertyValueFactory<>("jardin"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        listenf = us.displayAll();
        observablelist=FXCollections.observableArrayList(listenf);
        Table.setItems(observablelist);
    }

   @FXML
    private void modifier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLupdateEnf.fxml"));
        AnchorPane page = (AnchorPane) fxmlLoader.load();

        // Criando um Estágio de Diálogo (Stage Dialog)
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier Enfant");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o cliente no Controller.
        FXMLupdateEnfController controller = fxmlLoader.getController();
        
        // Mostra o Dialog e espera até que o usuário o feche
        dialogStage.showAndWait();     
    }
    
    
}
