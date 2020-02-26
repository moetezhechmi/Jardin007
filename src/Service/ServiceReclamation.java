/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entitie.Incident;
import Entitie.Reclamation;
import IService.IServiceRec;
import static Service.ServiceIncident.getDateOnCour;
import Test.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author AmiR
 */
public class ServiceReclamation implements IServiceRec {

    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    private Object PieChart;

    public ServiceReclamation() {
        cnx = DataSource.getInstance().getcnx();
    }
    
    
    
    public ObservableList<PieChart.Data> StatNbrParticipant() {
         
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
           
            PreparedStatement st = cnx.prepareStatement("select count(id_rec),nom from reclamation  group by nom ");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(2), rs.getInt(1)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
            //System.out.println("ici" + observableList.size());
            return observableList;

        } catch (SQLException ex) {
             Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Reclamation t,String idJ) {
        
        String r="select id from jardin where nom='"+idJ+"'";
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(r);
            rs.next();
            int id = rs.getInt(1);
            
        Reclamation ren = new Reclamation();
        String req = "insert into reclamation (nom,id_user,name,createdat,user_mail,urgence,description,idJ,etat)"
                + " values" + "(?,?,?,now(),?,?,?,?,'En Cours')";

            ps = cnx.prepareStatement(req);

            ps.setString(1, t.getNom());
            ps.setInt(2, t.getId_user());
            ps.setString(3, t.getName());
            ps.setString(4, t.getUser_mail());
            ps.setString(5, t.getUrgence());
            ps.setString(6, t.getDescrition());
            ps.setInt(7, id);

            // Executer 
            ps.executeUpdate();

            //incrNbr(R.getId_user());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List displayAll() {
        List<Reclamation> list = new ArrayList<>();
        String req = "select * from reclamation";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void delete(Reclamation t) {
        String req = "delete from reclamation where id_rec=" + t.getId_rec();

        try {
            st = cnx.createStatement();
            int r = st.executeUpdate(req);
            System.out.println(r);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List triParNom() {
        List<Reclamation> list = new ArrayList<>();
        String req = "select * from reclamation order by name asc";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List rechercher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List< String > getIdj() {
         List<String> list =new ArrayList<>();
         
         
         String req="select nom from jardin ";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {   
                list.add(rs.getString(1));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    
        }
    
    
  

}
