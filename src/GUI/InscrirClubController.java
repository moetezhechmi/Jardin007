/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.club;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author Daytech
 */
public class InscrirClubController implements Initializable {
    private String user = "chaimahichri80@gmail.com ";
    private String password = "Chaima@50";
@FXML
    private TableView<club> Table;
    @FXML
    private TableColumn<club, Integer> id;
    @FXML
    private TableColumn<club, String> nomclub;
    @FXML
    private TableColumn<club, String> description;
    
    private List<club> listClub;
    private ObservableList<club> observablelist;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ClubService us = new ClubService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomclub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("descr"));

        listClub = us.displayAll();
        observablelist=FXCollections.observableArrayList(listClub);
        Table.setItems(observablelist);
        // TODO
    }    

    @FXML
    private void participer(ActionEvent event) throws SQLException, Exception {
        ClubService sa = new ClubService();
        club selectedItem=Table.getSelectionModel().getSelectedItem();
        Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
        dialogC.setTitle("A confirmation dialog-box");
        dialogC.setHeaderText(null);
        dialogC.setContentText("Voulez vous participer a ce club?");
        Optional<ButtonType> answer = dialogC.showAndWait();
if (answer.get() == ButtonType.OK) {
            sa.modifier(selectedItem);
         sendMail("saneferjardin@gmail.com");
      

System.out.println("User chose OK");
}
else {
System.out.println("User chose Cancel or closed the dialog-box");
        
    }
    
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
