/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Entitie.Incident;
import Service.ServiceIncident;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class AfficherIncidentController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfDate;
    @FXML
    private TextArea tfDes;
    @FXML
    private TextField tfCreated;
    @FXML
    private Button btnAffiche;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private TableView<Incident> table;
    @FXML
    private TableColumn<Incident, Integer> colid;
    @FXML
    private TableColumn<Incident, String> colNom;
    @FXML
    private TableColumn<Incident, String> colDes;
    @FXML
    private TableColumn<Incident, String> colCreated;
    @FXML
    private TableColumn<Incident, String> colDate;

    private List<Incident> listIncident;
    private ObservableList<Incident> observablelist;
    @FXML
    private Button btnRechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      /*  Service.ServiceIncident us = new ServiceIncident();
        colid.setCellValueFactory(new PropertyValueFactory<>("id_inc"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("createdby"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_inc"));

        listIncident = us.displayAll();
        observablelist = FXCollections.observableArrayList(listIncident);
        table.setItems(observablelist);*/
    }

    @FXML
    private void Afficher(ActionEvent event) {
        ServiceIncident us = new ServiceIncident();
        colid.setCellValueFactory(new PropertyValueFactory<>("id_inc"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("createdby"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_inc"));
        listIncident = us.displayAll();
        observablelist = FXCollections.observableArrayList(listIncident);
        table.setItems(observablelist);

    }

    @FXML
    private void Ajouter(ActionEvent event) {
        ServiceIncident sp = new ServiceIncident();
        if ((tfNom.getText().equals("")) || (tfDate.getText().equals("") || (tfDes.getText().equals(""))) | (tfCreated.getText().equals(""))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur!!!");
            alert.setHeaderText("Champs manquants!!");
            alert.setContentText("Veuillez remplir tous les champs!");

            alert.showAndWait();
        } else {
            sp.insert(new Incident(tfNom.getText(), tfDes.getText(), tfDate.getText(), tfCreated.getText()));

            JOptionPane.showMessageDialog(null, "incident ajouté");
            tfNom.clear();
            tfDes.clear();
            tfDate.clear();
            tfCreated.clear();
            ServiceIncident us = new ServiceIncident();
            colid.setCellValueFactory(new PropertyValueFactory<>("id_inc"));
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
            colCreated.setCellValueFactory(new PropertyValueFactory<>("createdby"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date_inc"));
            listIncident = us.displayAll();
            observablelist = FXCollections.observableArrayList(listIncident);
            table.setItems(observablelist);

        }
    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException {
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifierIncident.fxml"));

        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update incident");
        stage.setScene(new Scene(root1));
        stage.show();

     /*   ModifierController uac = fxmlLoader.getController();
        uac.settfNom(colNom.getCellData(table.getSelectionModel().getSelectedIndex()));
        uac.setTa_des(colDes.getCellData(table.getSelectionModel().getSelectedIndex()));
        uac.setTa_des(colCreated.getCellData(table.getSelectionModel().getSelectedIndex()));
        uac.setTa_des(colDate.getCellData(table.getSelectionModel().getSelectedIndex()));
*/
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        ServiceIncident sa = new ServiceIncident();
        sa.delete(colid.getCellData(table.getSelectionModel().getSelectedIndex()));
        table.refresh();
        Incident selectedItem = table.getSelectionModel().getSelectedItem();
        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("A confirmation dialog-box");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Voulez vous supprimer l'incident?");
        Optional<ButtonType> answer = dialogC.showAndWait();
        if (answer.get() == ButtonType.OK) {
            System.out.println("User chose OK");
            table.getItems().remove(selectedItem);

        } else {
            System.out.println("User chose Cancel or closed the dialog-box");
        }
        // J
    }

    @FXML
    private void Rechercher(ActionEvent event) throws IOException {
           ServiceIncident us = new ServiceIncident();
        colid.setCellValueFactory(new PropertyValueFactory<>("id_inc"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colDes.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCreated.setCellValueFactory(new PropertyValueFactory<>("createdby"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_inc"));
        listIncident = us.triParNom();
        observablelist = FXCollections.observableArrayList(listIncident);
        table.setItems(observablelist);
     

    }

}
