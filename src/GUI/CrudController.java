/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.club;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Service.ClubService;

/**
 * FXML Controller class
 *
 * @author Daytech
 */
public class CrudController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextArea ta_des;
    @FXML
    private TableView<club> Table;
    @FXML
    private TableColumn<club, Integer> id;
    @FXML
    private TableColumn<club, String> nomclub;
    @FXML
    private TableColumn<club, String> description;
    
    @FXML
    private ComboBox<String> idJ;

    
    private List<club> listClub;
    private ObservableList<club> observablelist;
/*public void setTf_nom(String tf_nom) {
        this.tf_nom.setText(tf_nom); 
    }
 public void setTa_des(String ta_des) {
         this.ta_des.setText(ta_des); 
    }*/
    public TableColumn<club, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<club, Integer> id) {
        this.id = id;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClubService us = new ClubService();

        ArrayList<String> ListeJ = new ArrayList<>();
        ListeJ=(ArrayList<String>) us.getIdj();
        ObservableList<String> ComboData = FXCollections.observableArrayList(ListeJ);
        for(String s : ComboData){
        idJ.getItems().add(s);
        }
       
    }    

   
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        ClubService sp=new ClubService();
        if ((tf_nom.getText().equals(""))||(ta_des.getText().equals("")))
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Erreur!!!");
                    alert.setHeaderText("Champs manquants!!");
                    alert.setContentText("Veuillez remplir tous les champs!");

                    alert.showAndWait();
                }
        else{
        sp.insert(new club (tf_nom.getText(),ta_des.getText()),idJ.getValue());
        
        //JOptionPane.showMessageDialog(null, "club ajouté");
        tf_nom.clear();
        ta_des.clear();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLaffichage.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Liste des club");
        stage.setScene(new Scene(root1));
        stage.show();
        /* ClubService us = new ClubService();
        nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));
        listClub = us.displayAll();
        observablelist=FXCollections.observableArrayList(listClub);
        Table.setItems(observablelist);*/
        
        }
    }
     @FXML
    private void afficher(ActionEvent event) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLaffichage.fxml"));
 
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Liste des Club");
        stage.setScene(new Scene(root1));
        stage.show();
        
        /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLaffichage.fxml"));
 
        Parent root1 =  fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Affichage Club");
        stage.setScene(new Scene(root1));
        stage.show();*/
       /* ClubService us = new ClubService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));
        listClub = us.displayAll();
        observablelist=FXCollections.observableArrayList(listClub);
        Table.setItems(observablelist);*/
       
       
    }
        @FXML
    private void delete(ActionEvent event) {
        ClubService sa = new ClubService();
        sa.delete(id.getCellData(Table.getSelectionModel().getSelectedIndex()));
        Table.refresh();
        club selectedItem=Table.getSelectionModel().getSelectedItem();
        Alert dialogC = new Alert(AlertType.CONFIRMATION);
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
       // JOptionPane.showMessageDialog(null, "Club Supprimé avec succès");

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
     
    }
    
    



        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
   
                
                      
                
                
                
                
                
                
                      
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                