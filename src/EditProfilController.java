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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ImageView imageJardin;
    
    private Window stage;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
            imagePath.setVisible(false);
    
    }
    }
    @FXML
    private void aff(ActionEvent event) {
      jardinService us = new jardinService();
      List<Jardin> list = us.getById(22);
      nomJ.setText(list.get(0).getNom());
      rueJ.setText(list.get(0).getAdresse().getRue());
      villeJ.setText(list.get(0).getAdresse().getVille());
      gouverneratJ.setText(list.get(0).getAdresse().getGovernerat());
      paysJ.setText("tunis");
      emailJ.setText(list.get(0).getMail());
      numJ.setText(list.get(0).getNumTel());
    }    
    
    @FXML
    private void updat(ActionEvent event) {
      jardinService us = new jardinService();
      List<Jardin> list = us.getById(22);
      Adresse a= new Adresse(list.get(0).getAdresse().getId(),rueJ.getText(),villeJ.getText(),gouverneratJ.getText(),list.get(0).getAdresse().getLatitude(),list.get(0).getAdresse().getLongitude());
      Jardin J= new Jardin(list.get(0).getId(),nomJ.getText(),a,emailJ.getText(),numJ.getText());
      us.update(J);
    }
    
    
}
