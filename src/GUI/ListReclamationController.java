/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entitie.Incident;
import Entitie.ListeRecl;
import Entitie.Reclamation;
import Service.ServiceIncident;
import Service.ServiceListRec;
import Service.ServiceReclamation;
import Test.DataSource;
import Utils.PDFutil;
import com.lowagie.text.DocumentException;
//import gui.AcceuilIncidentController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author AmiR
 */
public class ListReclamationController implements Initializable {

    @FXML
    private TableView<ListeRecl> listerecl;
    @FXML
    private TableColumn<ListeRecl, String> a;
    @FXML
    private TableColumn<ListeRecl, String> b;
    @FXML
    private TableColumn<ListeRecl, String> c;
    @FXML
    private TableColumn<ListeRecl, String> d;
    @FXML
    private TableColumn<ListeRecl, Date> e;
    @FXML
    private TableColumn<ListeRecl, String> f;
    @FXML
    private TableColumn<ListeRecl, String> g;
    @FXML
    private TableColumn<ListeRecl, String> h;
    @FXML
    private TableColumn<ListeRecl, String> i;
    @FXML
    private TableColumn<ListeRecl, String> k;

    @FXML
    private TextField chercher;

    private ObservableList<ListeRecl> observablelist;

    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;

    public ListReclamationController() {
        cnx = DataSource.getInstance().getcnx();
    }
    /**
     * Initializes the controller class.
     */
    List<ListeRecl> listR = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String res = "SELECT reclamation.id_rec,reclamation.nom,reclamation.name,reclamation.description,reclamation.urgence,reclamation.createdat,utilisateur.Nom,utilisateur.Prenom,utilisateur.Email,jardin.nom,reclamation.etat FROM jardin , utilisateur ,reclamation WHERE reclamation.id_user=utilisateur.Id and reclamation.idJ=jardin.id";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(res);

            while (rs.next()) {

                ListeRecl ls = new ListeRecl(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
                listR.add(ls);
            }

            a.setCellValueFactory(new PropertyValueFactory<>("nom"));
            b.setCellValueFactory(new PropertyValueFactory<>("name"));
            c.setCellValueFactory(new PropertyValueFactory<>("desc"));
            d.setCellValueFactory(new PropertyValueFactory<>("urg"));
            e.setCellValueFactory(new PropertyValueFactory<>("date"));
            f.setCellValueFactory(new PropertyValueFactory<>("nomuser"));
            g.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            h.setCellValueFactory(new PropertyValueFactory<>("emailuser"));
            i.setCellValueFactory(new PropertyValueFactory<>("jardin"));
            k.setCellValueFactory(new PropertyValueFactory<>("etat"));

            ObservableList<ListeRecl> obl = FXCollections.observableArrayList(listR);
            listerecl.setItems(obl);
            listerecl.setEditable(true);

            k.setCellFactory(TextFieldTableCell.forTableColumn());
        } catch (SQLException ex) {
            Logger.getLogger(ListReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Connection con = DataSource.getInstance().getcnx();

    @FXML
    private void search(KeyEvent event) {

        ListeRecl inc = new ListeRecl();
        ServiceListRec sv = new ServiceListRec();
        List<ListeRecl> list1 = sv.displayAllRec();
        observablelist = FXCollections.observableArrayList(list1);

        FilteredList<ListeRecl> filteredData = new FilteredList<>(observablelist, e -> true);

        chercher.setOnKeyReleased(e
                -> {
            chercher.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super ListeRecl>) new Predicate<ListeRecl>() {

                    @Override
                    public boolean test(ListeRecl ListeRecl) {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();

                        if (ListeRecl.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;

                        } else if (ListeRecl.getName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }

                        if (String.valueOf(ListeRecl.getDesc()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        if (String.valueOf(ListeRecl.getUrg()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        if (String.valueOf(ListeRecl.getDate()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        if (String.valueOf(ListeRecl.getJardin()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        if (String.valueOf(ListeRecl.getEmailuser()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                            return true;
                        }
                        return false;

                    }
                });

            });

            SortedList<ListeRecl> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(listerecl.comparatorProperty());
            listerecl.setItems(sortedData);
        });

    }

    @FXML
    private void GenerateActivitePdf(ActionEvent event) throws SQLException, IOException {
        PDFutil pdf = new PDFutil();

        try {
            pdf.listActivite();
        } catch (DocumentException ex) {
            Logger.getLogger(ListReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
