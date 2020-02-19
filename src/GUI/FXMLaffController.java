/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.commentaire;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.CommentaireService;

/**
 * FXML Controller class
 *
 * @author akrem
 */
public class FXMLaffController implements Initializable {

    @FXML
    private TableView<commentaire> Table;
    @FXML
    private TableColumn<commentaire, Integer> id;
    @FXML
    private TableColumn<commentaire, String> texte;
    
    private List<commentaire> listCom;
    private ObservableList<commentaire> observablelist;
    
    
    
     public TableColumn<commentaire, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<commentaire, Integer> id) {
        this.id = id;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             CommentaireService cs=new CommentaireService();
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
              texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
              listCom=cs.displayAll();
              observablelist=FXCollections.observableArrayList(listCom);
              Table.setItems(observablelist);
    }    

    @FXML
    private void supprimer(ActionEvent event) {
                CommentaireService sa = new CommentaireService();
        sa.delete(id.getCellData(Table.getSelectionModel().getSelectedIndex()));
        Table.refresh();
        commentaire selectedItem=Table.getSelectionModel().getSelectedItem();
        Alert dialogC = new Alert(AlertType.CONFIRMATION);
        dialogC.setTitle("A confirmation dialog-box");
        dialogC.setHeaderText(null);
dialogC.setContentText("Voulez vous supprimer ce commentaire?");
Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
System.out.println("User chose OK");
        Table.getItems().remove(selectedItem);
    }
    
}
}