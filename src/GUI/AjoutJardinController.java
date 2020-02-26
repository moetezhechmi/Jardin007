/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package GUI;

import Entity.Adresse;
import Entity.Jardin;
import Service.jardinService;
import Test.DataSource;
import Utils.ControleSaisie;
import Utils.GoogleMapsAPI;
import Utils.JavaMailUtil;
import Utils.UploadImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private Button btnImage;
    
    @FXML
    private Text imagePath;
    
    @FXML
    private ComboBox<String> gouvernorat;

    @FXML
    private Label errorNom;

    @FXML
    private Label errorRue;

    @FXML
    private Label errorVille;

    @FXML
    private Label errorGouvernorat;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorNumero;
    
    @FXML
    private Label errorImage;


    @FXML
    private ImageView imageJardin;
    
     private List<Jardin> listJardin;
    private ObservableList<Jardin> observablelist;
    

    //@FXML
    //private WebView mapView;
    
    // Init controller class
    //GoogleMapsAPI gma;
    boolean nomOK = true;
    boolean rueOK = true;
    boolean villeOK = true;
    boolean gouvernoratOK = true;
    boolean mailOK1 = true;
    boolean mailOK2 = true;
    boolean numOK = true;
    boolean numOK2 = true;
    boolean imageOK = true;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        jardinService ss = new jardinService();
        
        remplirGouvernorat();
        //List<Jardin> l = ss.getById();
        
        //Table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //Table.getSelectionModel().setCellSelectionEnabled(true);
        //gma = new GoogleMapsAPI(mapView);
        //gma.initMap();
    }
   
    private void remplirGouvernorat() {
        
        String list[] = {"Ariana", "Beja", "Ben Arous", "Bizerte", "Gabes", "Gafsa", "Jendouba", "Kairouan", "Kasserine", "Kebili", "Le Kef", "Mahdia", "Manouba",
            "Medenine", "Monastir", "Nabeul", "Sfax", "Sidi Bouzid", "Siliana", "Sousse", "Tataouine", "Tozeur", "Tunis", "Zaghouan"};
        
        for (int i = 0; i < list.length; i++) {
            gouvernorat.getItems().add(list[i]);
        }
        
        /*
        ObservableList<String> ComboData = FXCollections.observableArrayList(list);
        for(String s : ComboData){
        gouvernorat.getItems().add(s);
        }
        */
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
    private void AjouterJ(ActionEvent event) throws Exception {
        jardinService p = new jardinService();
        ControleSaisie.effacerControleSaisie(errorNom);
        ControleSaisie.effacerControleSaisie(errorEmail);
        ControleSaisie.effacerControleSaisie(errorGouvernorat);
        ControleSaisie.effacerControleSaisie(errorImage);
        ControleSaisie.effacerControleSaisie(errorNumero);
        ControleSaisie.effacerControleSaisie(errorRue);
        ControleSaisie.effacerControleSaisie(errorVille);
        
        nomOK = ControleSaisie.controleTextFieldVide(nomJ, "Veuillez saisir le nom du jardin", errorNom);
        villeOK = ControleSaisie.controleTextFieldVide(villeJ, "Veuillez saisir la ville", errorVille);
        rueOK = ControleSaisie.controleTextFieldVide(rueJ, "Veuillez saisir le rue", errorRue);
        gouvernoratOK = ControleSaisie.controleComboBox(gouvernorat, "Veuillez sélectionner le gouvernorat", errorGouvernorat);
        mailOK2 = ControleSaisie.controleMailFormat(emailJ, "Veuillez saisir un e-mail valide", errorEmail);
        mailOK1 = ControleSaisie.controleTextFieldVide(emailJ, "Veuillez saisir le e-mail", errorEmail);
        numOK = ControleSaisie.controleNumTelLongueur(numJ, "Le Num. Tél doit être composé de 8 chiffres ", errorNumero);
        imageOK = ControleSaisie.controleLabelImage(imagePath, "Veuillez sélectionner une image", errorImage);
        
        
        if(!(numOK || villeOK || rueOK || gouvernoratOK || mailOK1 || mailOK2 || numOK || imageOK)){
                Adresse adresse = new Adresse(rueJ.getText(),  villeJ.getText(), gouvernorat.getValue(), 10.9, 12.9);
                try {
                    p.Insert(new Jardin(nomJ.getText(),adresse,numJ.getText(),emailJ.getText() ,uploadImageToServer(imagePath.getText())));
                    JavaMailUtil maill = new JavaMailUtil();
                    maill.sendMail("moetezhechmi.groun@esprit.tn");
                } catch (IOException ex) {
                    Logger.getLogger(AjoutJardinController.class.getName()).log(Level.SEVERE, null, ex);
                }
                //JOptionPane.showMessageDialog(null, "Jardin ajouté");    
                nomJ.setText("");
                rueJ.setText("");
                villeJ.setText("");
                gouverneratJ.setText("");
                emailJ.setText("");
                numJ.setText("");
                imageJardin.setImage(null);

                }   
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
            //imagePath.setVisible(false);
        }
    
    }

}
