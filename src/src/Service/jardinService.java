/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Adresse;
import Entity.Divertissement;
import Entity.Jardin;
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
import Test.DataSource;

/**
 *
 * @author AmiR
 */
public class jardinService  implements IServiceJardin<Jardin>{
    
    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    
    public jardinService(){
        try {
            cnx=DataSource.getInstance().getcnx();
            st = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
   public void Insert(Jardin p){
       
      String req = "INSERT INTO adresse(rue, ville, gouvernorat, latitude, longitude) VALUES ('" + p.getAdresse().getRue() + "','" + p.getAdresse().getVille() + "','" + p.getAdresse().getGovernerat() + "'," + p.getAdresse().getLatitude() + "," + p.getAdresse().getLongitude() + ")";
        int id=0;
       
        
        try {
            st.executeUpdate(req);
            req = "select * from adresse";
            rs = st.executeQuery(req);
            while (rs.next()) {

                id= rs.getInt(1);
                
            }
             req=" insert into jardin (nom,adresse,numTel,mail,idC, imageUrl) values('"+p.getNom()+"',"+id+",'"+p.getNumTel()+"','"+p.getMail()+"',2,'"+p.getImageUrl()+"')";
             st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }

       
       
    }
   
    


    @Override
    public void delete(int id) {
     String sql = "select adresse FROM jardin WHERE id="+id;
        try {
               rs = st.executeQuery(sql);
               rs.next();
               int add=rs.getInt(1);
               System.out.println("id="+id);
               System.out.println("id add="+add);
               String sql2 = "DELETE FROM jardin WHERE id="+id;
               st.executeUpdate(sql2);
               String sql1 = "DELETE FROM adresse WHERE id="+add;
               st.executeUpdate(sql1);
               
            
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
        public void deleteD(int id) {
        try {
               String sql2 = "DELETE FROM divertissement WHERE id="+id;
               st.executeUpdate(sql2);
               
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
  
        public void InsertAdresse(Adresse adresse){
        String req=" insert into adresse (rue,ville,governat,pays,latitude,longitude) values('"+adresse.getRue()+"','"+adresse.getVille()+"','"+adresse.getGovernerat()+"',"+0+","+0+")";
         try {
            st=cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
        
         
        
        public  List<Jardin>  getById(int id) {
        String req = "select * from `jardin`,`adresse` where `jardin`.id="+id+" and `jardin`.adresse=`adresse`.id";
        List<Jardin> list = new ArrayList<>();
        try {
            rs = st.executeQuery(req);
            while (rs.next()){
                Adresse a1 = new Adresse(rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
                Jardin j = new Jardin(rs.getInt(1), rs.getString(2), a1, rs.getString(4), rs.getString(5));
                list.add(j); 
            
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
                
        public  List<Jardin>  getAll(int id) {
        String req = "select * from `jardin`,`adresse` where `jardin`.idC="+id+" and `jardin`.adresse=`adresse`.id";
        List<Jardin> list = new ArrayList<>();
        try {
            rs = st.executeQuery(req);
            while (rs.next()){
                Adresse a1 = new Adresse(rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getInt(13));
                Jardin j = new Jardin(rs.getInt(1), rs.getString(2), a1, rs.getString(4), rs.getString(5));
                list.add(j); 
            
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
        public  List<Divertissement>  getAllD(int id) {
        String req = "SELECT divertissement.id , divertissement.nom,jardin.nom FROM jardin , divertissement WHERE jardin.id=divertissement.idJ and jardin.idC="+id+"";
        List<Divertissement> list = new ArrayList<>();
        try {
            rs = st.executeQuery(req);
            while (rs.next()){
                Divertissement d = new Divertissement(rs.getInt(1), rs.getString(2), rs.getString(3));
                list.add(d); 
                
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        
    public void update(Jardin j) {
    
    String rue =j.getAdresse().getRue();
    String ville=j.getAdresse().getVille();
    String gouv=j.getAdresse().getGovernerat();
    
    String sql = "UPDATE `jardin` SET `nom`='"+j.getNom()+"',`numTel`='"+j.getNumTel()+"',`mail`='"+j.getMail()+"',`imageUrl`='"+j.getImageUrl()+"' WHERE `jardin`.id='"+j.getId()+"'";    
    String sql2 = "UPDATE adresse SET `rue`='"+rue+"', `ville`='"+ville+"', `gouvernorat`='"+gouv+"' WHERE  adresse.id ='"+j.getAdresse().getId()+"'";
    
    try {
            ps=cnx.prepareStatement(sql);
            ps.executeUpdate();
            
            ps=cnx.prepareStatement(sql2);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void AjoutD(Divertissement divertissement,int idJ){
        try {
            System.out.println(divertissement.getNom());
            System.out.println(divertissement.getImageUrl());
            System.out.println(divertissement.getIdJ());
            System.out.println(idJ);
            String req="insert into divertissement (nom,imageUrl,idJ) values('"+divertissement.getNom()+"','"+divertissement.getImageUrl()+"',"+idJ+")";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(jardinService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Jardin> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Jardin t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        

    
      
   

    

   
 }

