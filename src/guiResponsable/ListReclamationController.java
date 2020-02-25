/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiResponsable;

import Entitie.ListeRecl;
import Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class ListReclamationController implements Initializable {

    @FXML
    private TableView<ListeRecl> listerecl;
    @FXML
    private TableColumn<ListeRecl, String> a;
    @FXML
    private TableColumn<ListeRecl, String> b;
    @FXML
    private TableColumn<ListeRecl, String> c;
    @FXML
    private TableColumn<ListeRecl, String> d;
    @FXML
    private TableColumn<ListeRecl, Date> e;
    @FXML
    private TableColumn<ListeRecl, String> f;
    @FXML
    private TableColumn<ListeRecl, String> g;
    @FXML
    private TableColumn<ListeRecl, String> h;
    
    @FXML
    private TextField chercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        affiche();
        // TODO
    }    
    
    Connection con = DataSource.getInstance().getcnx();
    
    public ObservableList<ListeRecl> show1()
    { 

           try {
               ObservableList<ListeRecl> obl =FXCollections.observableArrayList();
                  //exécution de la réquette et enregistrer le resultat dans le resultset
                  PreparedStatement pt= con.prepareStatement("SELECT name,reclamation.nom,urgence,description,createdat,user.username,user.username_canonical,user.email from reclamation, user where reclamation.id_user=user.id");
                  ResultSet rs = pt.executeQuery();
                  while(rs.next()){
                  
                 ListeRecl ls = new ListeRecl();

                 ls.setNom(rs.getString("name")); 
                 ls.setName(rs.getString("nom"));
                 ls.setDesc(rs.getString("description"));
                 ls.setUrg(rs.getString("urgence"));
                 ls.setDate(rs.getDate("createdat"));
                 ls.setNomuser(rs.getString("username"));
                 ls.setPrenom(rs.getString("username_canonical"));
                 ls.setEmailuser(rs.getString("email"));
             

                  
                  System.out.println("ok");
         obl.add(ls);
                  }
                  return obl;
                  
              } catch (SQLException ex) {
                System.out.println("Erreur"+ex);
              }
           return null;
    } 

    private void affiche() {
   
         //ajouter les valeurs au tableview
      a.setCellValueFactory(new PropertyValueFactory<>("nom"));
      b.setCellValueFactory(new PropertyValueFactory<>("name"));
      c.setCellValueFactory(new PropertyValueFactory<>("desc"));
      d.setCellValueFactory(new PropertyValueFactory<>("urg"));
      e.setCellValueFactory(new PropertyValueFactory<>("date"));
      f.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
      g.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      h.setCellValueFactory(new PropertyValueFactory<>("emailuser"));
      
      ObservableList<ListeRecl> obl =FXCollections.observableArrayList();

     // tableview.setItems(null);
     obl=show1(); 
      listerecl.setItems(obl);
      System.out.println("ok");
        
    }

    @FXML
    private void search(KeyEvent event) {
    }
 
    
    
    
}
