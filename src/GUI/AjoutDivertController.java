/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Adresse;
import Entity.Divertissement;
import Entity.Jardin;
import Service.jardinService;
import Test.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

public class AjoutDivertController implements Initializable {

       
    private Stage stage;
    private Jardin j;
    private Stage dialogStage;
    private boolean buttonConfirmerClicked = false;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField tf_nom;
    
    @FXML
    private Text imagePath;

    @FXML
    private Button btnImage;

    @FXML
    private Button enreD;
    
    @FXML
    private ImageView imageD;
 
        
    public Stage getDialogStage() {
    return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
     
    public Jardin getUtilisateur() {
        return j;
    }

    public void setUtilisateur(Jardin j) {
        this.j = j;
    }
    
        public boolean isButtonConfirmerClicked() {
        return buttonConfirmerClicked;
    }

    public void setButtonConfirmerClicked(boolean buttonConfirmerClicked) {
        this.buttonConfirmerClicked = buttonConfirmerClicked;
    }
     
    public void initialize(URL url, ResourceBundle rb) {
        jardinService ss = new jardinService();

        //List<Jardin> l = ss.getById();
        
        
        //Table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //Table.getSelectionModel().setCellSelectionEnabled(true);
        //gma = new GoogleMapsAPI(mapView);
        //gma.initMap();
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
    
    @FXML
    private void AjouterD(ActionEvent event) {
        System.out.println(this.getUtilisateur().getId());
        int idJ=this.getUtilisateur().getId();
        jardinService p = new jardinService();
        if((imagePath.getText().equals("Sélectionner une image"))||(tf_nom.getText().equals("")))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("champs manquants");
                alert.setContentText("veuillez remplir tous les champs");
                alert.showAndWait();
            }
        else{
                try {
                    p.AjoutD(new Divertissement(tf_nom.getText(),uploadImageToServer(imagePath.getText()),idJ),idJ);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutJardinController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //JOptionPane.showMessageDialog(null, "Jardin ajouté");    
                tf_nom.setText("");
                imagePath.setText("Sélectionner une image");
                imageD.setImage(null);
                }   
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
            imageD.setImage(new Image(f.toURI().toString()));
            //imagePath.setVisible(false);
        }
    
    }

    
}
