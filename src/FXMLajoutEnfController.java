/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.enfant;
import Entity.utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;
import service.enfantService;


/**
 * FXML Controller class
 *
 * @author Khaled
 */
public class FXMLajoutEnfController implements Initializable {

    @FXML
    private TextField tfnomenf;
    @FXML
    private TextField tfprenomenf;
    @FXML
    private DatePicker datepicker;
    //private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @FXML
    private Button btnvalider;
    @FXML
    private Button btnretour;
    @FXML
    private Button btnphoto;
    @FXML
    private ComboBox<String> combo;
private int year;
private int month;
private int day;
    @FXML
    private ComboBox<String> idJ;
    
   
    @FXML
    private TextField tfetat;

    public DatePicker getDatepicker() {
        return datepicker;
    }

    public void setDatepicker(DatePicker datepicker) {
        this.datepicker = datepicker;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public TextField getTfnomenf() {
        return tfnomenf;
    }

    public void setTfnomenf(TextField tfnomenf) {
        this.tfnomenf = tfnomenf;
    }

    public TextField getTfprenomenf() {
        return tfprenomenf;
    }

    public void setTfprenomenf(TextField tfprenomenf) {
        this.tfprenomenf = tfprenomenf;
    }
    

    public Button getBtnvalider() {
        return btnvalider;
    }

    public void setBtnvalider(Button btnvalider) {
        this.btnvalider = btnvalider;
    }

    public Button getBtnretour() {
        return btnretour;
    }

    public void setBtnretour(Button btnretour) {
        this.btnretour = btnretour;
    }

    public Button getBtnphoto() {
        return btnphoto;
    }

    public void setBtnphoto(Button btnphoto) {
        this.btnphoto = btnphoto;
    }

    public ComboBox<String> getCombo() {
        return combo;
    }

    public void setCombo(ComboBox<String> combo) {
        this.combo = combo;
    }

    public ComboBox<String> getIdJ() {
        return idJ;
    }

    public void setIdJ(ComboBox<String> idJ) {
        this.idJ = idJ;
    }

    public TextField getTfetat() {
        return tfetat;
    }

    public void setTfetat(TextField tfetat) {
        this.tfetat = tfetat;
    }

   
    
 private String images;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
      
          tfetat.setText(String.valueOf("En Cours"));
        tfetat.setDisable(true);
  
         enfantService es = new enfantService();
         enfant us = new enfant();
        combo.setValue(us.getSexe());
        ArrayList<String> ListeSexe = new ArrayList<>();
     
        ListeSexe.add("Garçon");
        ListeSexe.add("Fille");
        ObservableList<String> ComboData = FXCollections.observableArrayList(ListeSexe);
        for(String s : ComboData){
            combo.getItems().add(s);}
            
        idJ.setValue("Jardin d'enfant");
        ArrayList<String> ListeJ = new ArrayList<>();
        ListeJ=(ArrayList<String>) es.getIdj();
        ObservableList<String> ComboData1 = FXCollections.observableArrayList(ListeJ);
        for(String ss : ComboData1){
        idJ.getItems().add(ss);}
        
       /*etat.setValue(us.getEtat());
        ArrayList<String> ListeE = new ArrayList<>();
        ListeE.add("En Cours");
        ListeE.add("Refuser");
        ListeE.add("Accepter");
        ObservableList<String> ComboData2 = FXCollections.observableArrayList(ListeE);
        for(String s2 : ComboData2){
        etat.getItems().add(s2);
        }*/
        /*datepicker.setConverter(new StringConverter<LocalDate>(){
             @Override
             public String toString(LocalDate t) {
                if (t !=null){
                   return formatter.format(t);
                }
                return null;
             }

             @Override
             public LocalDate fromString(String string) {
               if (string !=null && !string.trim().isEmpty() ){
                  return LocalDate.parse(string, formatter);
               }
                  return null;
             }
         });
        
        
        
        
        
        
        datepicker.setOnAction((ActionEvent event ) ->{
        System.out.println(formatter.format(datepicker.getValue()));
        
        });*/
        
        
    } 
   
    @FXML
    private void ajouter(ActionEvent event) {
        
         enfant enf = new enfant();
         enf.setNomenf(tfnomenf.getText());
          enf.setPrenomenf(tfprenomenf.getText());
           enf.setSexe(combo.getValue());
           // enf.setDate_de_naissance(datepicker.get);
            enf.setPhotoenf(btnphoto.getText());
             enf.setEtat(tfetat.getText());
        enfantService es = new enfantService();
        es.insert(enf,idJ.getValue());
        JOptionPane.showMessageDialog(null, "Enfant ajouté");
        
        tfnomenf.clear();
        tfprenomenf.clear();
        combo.setValue(null);
        datepicker.setValue(null);
        idJ.setValue(null);
       
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Stage stage = (Stage) btnretour.getScene().getWindow();
             stage.close();
            Stage window = new Stage();
            window.centerOnScreen();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ParentFXML.fxml")));
            window.setScene(scene);  
             window.show();
    }

    @FXML
    private void upload(ActionEvent event) throws IOException {
        
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null){
            File source = new File(selectedFile.getAbsolutePath());
         images = selectedFile.getName();
        File dest = new File("C:\\Users\\Khaled\\Documents\\NetBeansProjects\\Utilisateur_1\\src\\images"+selectedFile.getName());
        long start = System.nanoTime();
        start = System.nanoTime();
        copyFileUsingJava7Files(source, dest);
        }else{
            System.out.println("File not valid");
        }
    }
    private static void copyFileUsingJava7Files(File source, File dest) throws IOException {
	    Files.copy(source.toPath(), dest.toPath());
	}

    

    

   
}
