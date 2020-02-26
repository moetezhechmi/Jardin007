/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
public class FXMLUpdateController implements Initializable {

    @FXML
    private TextField tfnom1;
    @FXML
    private TextField tfprenom1;
    @FXML
    private TextField tfemail1;
    @FXML
    private TextField tflogin1;
    @FXML
    private TextField tfpassword1;
    @FXML
    private TextField tfrole1;
    @FXML
    private Button btnvalider;
    private Stage stage;
    
    private List<utilisateur> listEvenement1;
    private ObservableList<utilisateur> observableListEvenement;
    private utilisateur utilisateur;
 private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    
    public boolean isButtonConfirmerClicked() {
        return buttonConfirmerClicked;
    }

    public void setButtonConfirmerClicked(boolean buttonConfirmerClicked) {
        this.buttonConfirmerClicked = buttonConfirmerClicked;
    }
    
    
    public utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(utilisateur utilisateur) {
        this.utilisateur = utilisateur;
        this.tfnom1.setText(utilisateur.getNom());
        this.tfprenom1.setText(utilisateur.getPrenom());
        this.tfemail1.setText(utilisateur.getEmail());
        this.tflogin1.setText(utilisateur.getLogin());
        this.tfpassword1.setText(utilisateur.getPassword());
        this.tfrole1.setText(utilisateur.getRole());

    }

    public TextField getTfnom1() {
        return tfnom1;
    }

    public void setTfnom1(TextField tfnom1) {
        this.tfnom1 = tfnom1;
    }

    public TextField getTfprenom1() {
        return tfprenom1;
    }

    public void setTfprenom1(TextField tfprenom1) {
        this.tfprenom1 = tfprenom1;
    }

    public TextField getTfemail1() {
        return tfemail1;
    }

    public void setTfemail1(TextField tfemail1) {
        this.tfemail1 = tfemail1;
    }

    public TextField getTflogin1() {
        return tflogin1;
    }

    public void setTflogin1(TextField tflogin1) {
        this.tflogin1 = tflogin1;
    }

    public TextField getTfpassword1() {
        return tfpassword1;
    }

    public void setTfpassword1(TextField tfpassword1) {
        this.tfpassword1 = tfpassword1;
    }

    public TextField getTfrole1() {
        return tfrole1;
    }

    public void setTfrole1(TextField tfrole1) {
        this.tfrole1 = tfrole1;
    }
    
    

    

   

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*public void LoadData(int id){
   
        utilisateurService cs=new utilisateurService();
          utilisateur e=cs.findById(id);
          tfnom1.setText(e.getNom());
          tfprenom1.setText(e.getPrenom());
          tfemail1.setText(e.getEmail()); 
          tflogin1.setText(e.getLogin());
          tfpassword1.setText(e.getPassword());
          tfrole1.setText(e.getRole());
          setId(id);
}
    @FXML
    private void valider(ActionEvent event) throws IOException {
        utilisateurService cs=new utilisateurService();
         
 utilisateur a =new utilisateur (tfnom1.getText(),tfprenom1.getText(),tfemail1.getText(),tflogin1.getText(),tfpassword1.getText(),tfrole1.getText());  
 cs.update(id, a);
 utilisateur Event=cs.findByName(a.getNom());
                    int Id = Event.getId();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLutilisateur.fxml"));
        Parent root=loader.load();
        FXMLutilisateurController EVC = loader.getController();
                    EVC.loadData(Id);
        
        
        JOptionPane.showMessageDialog(null, "utilisateur modifié");
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
       
        /*utilisateur.setNom(tfnom1.getText());
           utilisateur.setPrenom(tfprenom1.getText());
           utilisateur.setEmail(tfemail1.getText());
           utilisateur.setLogin(tflogin1.getText());
           utilisateur.setPassword(tfpassword1.getText());
           utilisateur.setRole(tfrole1.getText());

           utilisateurService cs=new utilisateurService();
           cs.update(utilisateur);
            

            btnvalider = true;
            stage.close();*/
           utilisateurService cs=new utilisateurService();

     @FXML
    private void valider(ActionEvent event)  {
           utilisateur.setNom(tfnom1.getText());
           utilisateur.setPrenom(tfprenom1.getText());
           utilisateur.setEmail(tfemail1.getText());
           utilisateur.setLogin(tflogin1.getText());
           utilisateur.setPassword(tfpassword1.getText());
           utilisateur.setRole(tfrole1.getText());
           buttonConfirmerClicked = true;
            dialogStage.close();
    }
}
