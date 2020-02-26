package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.Adresse;
import Entity.Jardin;
import Service.jardinService;
import Test.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * FXML Controller class
 *
 * @author moetez
 */
public class EditProfilController implements Initializable {
    @FXML
    private TextField nomJ;

    @FXML
    private TextField paysJ;

    @FXML
    private TextField villeJ;

    @FXML
    private TextField adresseJ;

    @FXML
    private TextField emailJ;

    @FXML
    private TextField numJ;
    
    @FXML
    private TextField rueJ;
    
    @FXML
    private TextField gouverneratJ;

    
    @FXML
    private Button modJ;
    @FXML
    private Button btnAff;
    @FXML
    private Button btnImage;
    @FXML
    private Text imagePath;
    @FXML
    private TextField id;

    @FXML
    private ImageView imageJardin;
      @FXML
    private Button annuler;
    
    //private Window stage;
   
    private Stage stage;
    private Jardin jardin;
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
    
    
    public Jardin getUtilisateur() {
        return jardin;
    }

    public void setUtilisateur(Jardin jardin) {
        this.jardin = jardin;
        this.nomJ.setText(jardin.getNom());
        this.emailJ.setText(jardin.getMail());
        this.numJ.setText(jardin.getNumTel());
        this.rueJ.setText(jardin.getAdresse().getRue());
        this.villeJ.setText(jardin.getAdresse().getVille());
        this.gouverneratJ.setText(jardin.getAdresse().getGovernerat());
    }
    
     public void setId(int id) {
        this.id.setText(String.valueOf(id));
    }

    public TextField getId() {
        return id;
    }
    
   
    public TextField getNomJ() {
        return nomJ;
    }

    public void setNomJ(String nomJ) {
        this.nomJ.setText(nomJ); 
    }
        
   
    public TextField getNumJ() {
        return numJ;
    }

    public void setNumJ(String numJ) {
        this.numJ.setText(numJ); 
    }

    public TextField getEmailJ() {
        return emailJ;
    }

    public void setEmailJ(String emailJ) {
         this.emailJ.setText(emailJ); 
    }



    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                jardinService ss = new jardinService();

        // TODO
    } 
    
        private static final String UPLOAD_URL = DataSource.serverUrl + "uploadJardinImage.php";

    public String uploadImageToServer(String fileLocation) throws IOException {

        // the URL where the file will be posted
        String postReceiverUrl = UPLOAD_URL;

        // new HttpClient
        HttpClient httpClient = new DefaultHttpClient();

        // post header
        HttpPost httpPost = new HttpPost(postReceiverUrl);

        //Create File
        File file = new File(fileLocation);
        FileBody fileBody = new FileBody(file);

        //Set up HTTP post
        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        reqEntity.addPart("fileToUpload", fileBody);

        httpPost.setEntity(reqEntity);

        // execute HTTP post request
        HttpResponse response = httpClient.execute(httpPost);
        HttpEntity resEntity = response.getEntity();
        String responseStr ="";
        if (resEntity != null) {

            responseStr = EntityUtils.toString(resEntity).trim();


            // you can add an if statement here and do other actions based on the response
            System.out.println(responseStr);
            System.out.println(response.getStatusLine());
        }
        return responseStr;

    }
    
    
    public void init(Stage stage) {
        this.stage = stage;
    }
    
        
    @FXML
    void openfile() {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            imagePath.setText(file.getPath());
            File f = new File(file.getPath());
            imageJardin.setImage(new Image(f.toURI().toString()));
            //imagePath.setVisible(false);
        }
    }
    @FXML
    private void aff(ActionEvent event) {
      jardinService us = new jardinService();
      List<Jardin> list = us.getById(34);
      nomJ.setText(list.get(0).getNom());
      rueJ.setText(list.get(0).getAdresse().getRue());
      villeJ.setText(list.get(0).getAdresse().getVille());
      gouverneratJ.setText(list.get(0).getAdresse().getGovernerat());
      emailJ.setText(list.get(0).getMail());
      numJ.setText(list.get(0).getNumTel());
    }    
    
    @FXML
    private void updat(ActionEvent event) throws IOException {
        
     jardinService us = new jardinService();
     jardin.setNom(nomJ.getText());
     jardin.setMail(emailJ.getText());
     jardin.setNumTel(numJ.getText());
     Adresse a = new Adresse(jardin.getAdresse().getId(),rueJ.getText(),villeJ.getText(),gouverneratJ.getText(),10.9,10.9);
     jardin.setAdresse(a);
     jardin.setImageUrl(uploadImageToServer(imagePath.getText()));
     us.update(jardin);
     buttonConfirmerClicked = true;
     dialogStage.close();
 }
    
}
