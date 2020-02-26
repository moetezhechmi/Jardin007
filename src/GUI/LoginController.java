/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Session;
import Entity.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Service.utilisateurService;

/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button btnlog;
    @FXML
    private Button btnregister;
    @FXML
    private Hyperlink linkpwd;
    private utilisateur Us;

    public utilisateur getUs() {
        return Us;
    }

    public void setUs(utilisateur Us) {
        this.Us = Us;
    }
 private int iduser;

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void connecter(ActionEvent event) throws SQLException, IOException {
         
        String  username=tfusername.getText();
       String password=tfpassword.getText();
     utilisateur User = new utilisateur();
     
     User.setPassword(password);
       User.setLogin(username);
       utilisateurService su = new utilisateurService();
        NewMain main= new NewMain();
        try
 {
         String userValide=su.authentification(User);
            Us=su.recupererUtilisateur(username);
            main.setU(su.recupererUtilisateur(username));
            if(userValide.equals("Administrateur"))
 {        

 System.out.println("Admin's Home");
 
   
    iduser=Us.getId();
    System.out.println(Us.getId());
     System.out.println(main.u.getLogin());
 
     System.out.println(iduser);

     


 Stage stage = (Stage) btnlog.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLTraiterCandidatureEnfant.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
           
       
            stage2.setTitle("Souk El Madina ");
            stage2.setScene(new Scene(root2));

            stage2.show();

        }catch(IOException e) {
            e.printStackTrace();
        }

 }
     else if(userValide.equals("Parent"))
 {  

     System.out.println("Parent Home");
      System.out.println(Us.getId());
      
      
       
Stage stage = (Stage) btnlog.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParentFXML.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
           
       
            stage2.setTitle("Espace Parent");
            stage2.setScene(new Scene(root2));

            stage2.show();

        }catch(IOException e) {
            e.printStackTrace();
        }
     
 
 }
     else if(userValide.equals("Responsable"))
 { 
     System.out.println("Responsable Jardin Home");
   
   int a= Us.getId();
   

 Stage stage = (Stage) btnlog.getScene().getWindow();
        // do what you have to do
        stage.close();

        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ProfilResponsable.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage2 = new Stage();
           
       
            stage2.setTitle("Souk El Madina ");
            stage2.setScene(new Scene(root2));

     
            stage2.show();

        }catch(IOException e) {
            e.printStackTrace();
        }
 }
     else
 {
 System.out.println("Error message = "+userValide);
 
   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Login");
            alert.setContentText(" Invalid user");

            alert.showAndWait();
 }
     
 }
 catch (Exception e1)
 {
 e1.printStackTrace();
 }
} 
        /*if(main.u.getLogin().equals(tfusername.getText())&&us.finduser(tfusername.getText()))
           
            
                {  
                   
                    if (us.recupererUtilisateur(tfusername.getText()).getPassword().equals(tfpassword.getText()))
                    {     
                        if (main.u.getRole().equals("Parent")||us.recupererUtilisateur(tfusername.getText()).getRole().equals("Parent"))
                                {        
                                        Stage stage = (Stage) btnlog.getScene().getWindow();
                                        stage.close();
                                        Stage window = new Stage();
                                        window.centerOnScreen();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Registration.fxml")));
                                        window.setScene(scene);  
                                        window.show();
                                      
                                        
                                }
                   
                       else if (main.u.getRole().equals("Responsable")||us.recupererUtilisateur(tfusername.getText()).getRole().equals("Responsable"))
                                {
                                        
                                  
                                        Stage stage = (Stage) btnlog.getScene().getWindow();
                                        stage.close();
                                        Stage window = new Stage();
                                        window.centerOnScreen();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Registration.fxml")));
                                        window.setScene(scene);  
                                        window.show();
                                   
                    }
                         else if (main.u.getRole().equals("Administrateur")&us.recupererUtilisateur(tfusername.getText()).getRole().equals("Administrateur"))
                                {
                                        
                                  
                                        Stage stage = (Stage) btnlog.getScene().getWindow();
                                        stage.close();
                                        Stage window = new Stage();
                                        window.centerOnScreen();
                                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Registration.fxml")));
                                        window.setScene(scene);  
                                        window.show();
                                   
                    }
                
                }
                else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Password incorrecte");
            alert.show();
                    }
   
    
    
}
        else{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Username invalide");
        alert.show();
        }
}*/
    

   
    @FXML
    private void register(ActionEvent event) throws IOException {
         Stage stage = (Stage) btnregister.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Registration.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void changermdp(ActionEvent event) {
    }
    
    
    
}
