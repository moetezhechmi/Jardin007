/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.commentaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import service.CommentaireService;
import javax.mail.PasswordAuthentication;
/**
 * FXML Controller class
 *
 * @author akrem
 */
public class CommentaireController implements Initializable {

    @FXML
    private TextArea txt;
    @FXML
    private TableView<commentaire> Table;
    @FXML
    private TableColumn<commentaire, Integer> id;
    @FXML
    private TableColumn<commentaire, String> texte;
    @FXML
    private TableColumn<commentaire, Integer> nbr_etoile;
    @FXML
    private TextField txt1;
    private int nbMax=5;
    
    
    
     private List<commentaire> comm;
    private ObservableList<commentaire> observablelist;
    @FXML
    private ComboBox<String> idJ;

    public TableColumn<commentaire, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<commentaire, Integer> id) {
        this.id = id;
    }
    
        public TextArea getTxt() {
        return txt;
    }

    public void setTxt(TextArea txt) {
        this.txt = txt;
    }

    public TextField getTxt1() {
        return txt1;
    }

    public void setTxt1(TextField txt1) {
        this.txt1 = txt1;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CommentaireService us = new CommentaireService();

        ArrayList<String> ListeJ = new ArrayList<>();
        ListeJ=(ArrayList<String>) us.getIdj();
        ObservableList<String> ComboData = FXCollections.observableArrayList(ListeJ);
        for(String s : ComboData){
        idJ.getItems().add(s);
        }
        // TODO
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
        nbr_etoile.setCellValueFactory(new PropertyValueFactory<>("nbr_etoile"));
        comm = us.displayAll();
        observablelist=FXCollections.observableArrayList(comm);
        Table.setItems(observablelist); 
    }    

   
    @FXML
    private void Ajouter(ActionEvent event) throws Exception {
        
       CommentaireService us = new CommentaireService();
       if(txt1.getText().length()>5){
       Alert alert = new Alert(AlertType.ERROR);
       alert.setTitle("Erreur!!!");
       alert.setHeaderText("Nombre des étoiles supérieur à 5");
       alert.setContentText("Veuillez introduire de nouveau");

       alert.showAndWait(); }
        else{
        us.insert(new commentaire(txt.getText(),txt1.getText().length()),idJ.getValue());
        JOptionPane.showMessageDialog(null, "commentaire ajouté");
        //sendMail("akrembenothmen1996@gmail.com");
        
    }
 
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        texte.setCellValueFactory(new PropertyValueFactory<>("texte"));
        nbr_etoile.setCellValueFactory(new PropertyValueFactory<>("nbr_etoile"));
        comm = us.displayAll();
        observablelist=FXCollections.observableArrayList(comm);
        Table.setItems(observablelist);   
        
        
        txt.setText("");
        idJ.setValue("");
        txt1.setText("");

    }
   

public static void sendMail(String recepient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "chaimahichri80@gmail.com";
        //Your gmail password
        String password = "Chaima@50";
        Session session = Session.getDefaultInstance(properties, new Authenticator(){
            
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
});
        
        //Create a session with account credentials
        /*Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });*/

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("My First Email from Java App");
            String htmlCode = "<h1> un membre est inscrit au club </h1> <br/> <h2><b>vérifiez votre liste </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            System.out.println("no");
        }
        return null;
    }


    
}

