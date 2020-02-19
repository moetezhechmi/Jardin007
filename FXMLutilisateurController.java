/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.utilisateur;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class FXMLutilisateurController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tflogin;
    @FXML
    private TextField tfpassword;
    @FXML
    private TextField tfrole;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<utilisateur> Table;
    @FXML
    private TableColumn<utilisateur, Integer> id;
    @FXML
    private TableColumn<utilisateur, String> nom;
    @FXML
    private TableColumn<utilisateur, String> prenom;
    @FXML
    private TableColumn<utilisateur, String> email;
    @FXML
    private TableColumn<utilisateur, String> login;
    @FXML
    private TableColumn<utilisateur, String> password;
    @FXML
    private TableColumn<utilisateur, String> role;
    @FXML
    private Button btnafficher;
    private List<utilisateur> listUser;
    private ObservableList<utilisateur> observablelist;

    public TableColumn<utilisateur, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<utilisateur, Integer> id) {
        this.id = id;
    }

    public TableColumn<utilisateur, String> getNom() {
        return nom;
    }

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilisateurService us = new utilisateurService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
         email.setCellValueFactory(new PropertyValueFactory<>("email"));
          login.setCellValueFactory(new PropertyValueFactory<>("login"));
           password.setCellValueFactory(new PropertyValueFactory<>("password"));
            role.setCellValueFactory(new PropertyValueFactory<>("role"));
        listUser = us.displayAll();
        observablelist=FXCollections.observableArrayList(listUser);
        Table.setItems(observablelist);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
         utilisateurService us = new utilisateurService();
         
        if ((tfnom.getText().equals(""))||(tfprenom.getText().equals(""))||(tfemail.getText().equals(""))||(tflogin.getText().equals(""))||(tfpassword.getText().equals(""))||(tfrole.getText().equals("")))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur!!!");
alert.setHeaderText("Champs manquants!!");
alert.setContentText("Veuillez remplir tous les champs!");

alert.showAndWait();
                }
        else{
        us.insert(new utilisateur(tfnom.getText(),tfprenom.getText(),tfemail.getText(),tflogin.getText(),tfpassword.getText(),tfrole.getText()));
        JOptionPane.showMessageDialog(null, "Utilisateur ajouté");
        tfnom.clear();
        tfprenom.clear();
        tfemail.clear();
        tflogin.clear();
        tfpassword.clear();
        tfrole.clear();
         utilisateurService sp = new utilisateurService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        listUser = us.displayAll();
        observablelist=FXCollections.observableArrayList(listUser);
        Table.setItems(observablelist);
        
    }
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLUpdate.fxml"));
        Parent root1 =  fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Update User");
        stage.setScene(new Scene(root1));
        stage.show();
        
        FXMLUpdateController uac=fxmlLoader.getController();
         
        uac.setTfnom1(nom.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTfprenom1(prenom.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTfemail1(email.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTflogin1(login.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTfpassword1(password.getCellData(Table.getSelectionModel().getSelectedIndex()));
        uac.setTfrole1(role.getCellData(Table.getSelectionModel().getSelectedIndex()));
      
    }

    @FXML
    private void supprimer(ActionEvent event) {
         utilisateurService sa = new utilisateurService();
        sa.delete(id.getCellData(Table.getSelectionModel().getSelectedIndex()));
        Table.refresh();
        utilisateur selectedItem=Table.getSelectionModel().getSelectedItem();
        Table.getItems().remove(selectedItem);
        JOptionPane.showMessageDialog(null, "utilisateur Supprimé avec succès");
    }

    @FXML
    private void afficher(ActionEvent event) {
        utilisateurService us = new utilisateurService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        listUser = us.displayAll();
        observablelist=FXCollections.observableArrayList(listUser);
        Table.setItems(observablelist);
    }
    
}
