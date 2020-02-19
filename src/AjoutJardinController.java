/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entity.Adresse;
import Entity.Jardin;
import Service.jardinService;
import Test.DataSource;
import Utils.GoogleMapsAPI;
import Utils.UploadImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
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
public class AjoutJardinController implements Initializable {
    
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
    private Button enreJ;
    
    @FXML
    private Button btnEdit;
    
    @FXML
    private Button btnImage;
    
    @FXML
    private Text imagePath;
    @FXML
    private Button remove;
    
    @FXML
    private TableView<Jardin> Table;

    @FXML
    private TableColumn<Jardin, Integer> id;

    @FXML
    private TableColumn<Jardin, String> nom;

    @FXML
    private TableColumn<Jardin, Adresse> adresse;

    @FXML
    private TableColumn<Jardin, String> numTel;

    @FXML
    private TableColumn<Jardin, String> mail;
    
    @FXML
    private ImageView imageJardin;
    
     private List<Jardin> listJardin;
    private ObservableList<Jardin> observablelist;
    

    //@FXML
    //private WebView mapView;
    
    // Init controller class
    //GoogleMapsAPI gma;
    
    
    public TableColumn<Jardin, Integer> getId() {
        return id;
    }

    public void setId(TableColumn<Jardin, Integer> id) {
        this.id = id;
    }
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        jardinService ss = new jardinService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
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
    private void AjouterJ(ActionEvent event) {
       
        
        jardinService p = new jardinService();
        String nom = "";
        Adresse adresse = new Adresse(rueJ.getText(),  villeJ.getText(),  gouverneratJ.getText(), 0, 0);
        try {
            p.Insert(new Jardin(nomJ.getText(),adresse,numJ.getText(),emailJ.getText(), uploadImageToServer(imagePath.getText())));
        } catch (IOException ex) {
            Logger.getLogger(AjoutJardinController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Jardin ajouté");
        
    }   
      

    @FXML
    private void EditPro(ActionEvent event) throws IOException{
      Parent formulaireParent = FXMLLoader.load(getClass().getResource("EditProfil.fxml"));
      Scene  formulaireScene = new Scene(formulaireParent);
      Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
      window.setScene(formulaireScene);
      window.show();
      
    }
    @FXML
    private void afficher(ActionEvent event) {
        jardinService us = new jardinService();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        numTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
        mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        List<Jardin> list = us.getAll("12345678");
        ObservableList<Jardin> observablelist = FXCollections.observableArrayList(list);
        Table.setItems(observablelist);
    }
    
    @FXML
    void coordonneesSelected(MouseEvent event) {

    }
    private Stage stage;
    
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
    void clickId(MouseEvent event) { 
         
    }
    @FXML
    private void delete(ActionEvent event) {
    jardinService sa = new jardinService();
        sa.delete(id.getCellData(Table.getSelectionModel().getSelectedIndex()));
        Table.refresh();
        Jardin selectedItem=Table.getSelectionModel().getSelectedItem();
        Table.getItems().remove(selectedItem);
        JOptionPane.showMessageDialog(null, "Club Supprimé avec succès");
    }
}
