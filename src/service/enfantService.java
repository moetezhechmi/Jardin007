/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.enfant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pack_database.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Khaled
 */
public class enfantService implements IServiceenfant<enfant> {
private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    
    public enfantService(){
        cnx=DataSource.getInstance().getcnx();
   
    }

    @Override
    public void insert(enfant e,String idJ) {
        
        try {    
        String r="select id from jardin where nom='"+idJ+"'";
        
            st=cnx.createStatement();
            rs=st.executeQuery(r);
            rs.next();
            int id = rs.getInt(1);
            
            
         String req="INSERT INTO"
                + " `enfant`(`nomenf`,`prenomenf`,`sexe`,`date_de_naissance`,`photoenf`,`idJ`,`etat`) "
                + "VALUES (?,?,?,?,?,?,?)";
      
        
            PreparedStatement ste;

            ste = cnx.prepareStatement(req);
            ste.setString(1,  e.getNomenf());
            ste.setString(2,  e.getPrenomenf());
            ste.setString(3, e.getSexe());
            DateFormat df= new SimpleDateFormat("yyyy-MM-dd") ;
            java.util.Date date =new java.util.Date();
            ste.setString(4,df.format(date)); 
            ste.setString(5, e.getPhotoenf());
            ste.setInt(6, id);
            ste.setString(7, e.getEtat());
            
             
            ste.executeUpdate();
            
             System.out.println(" Enfant ajouté "); 
    } catch (SQLException ex) {
        Logger.getLogger(enfantService.class.getName()).log(Level.SEVERE, null, ex);
    }
      
           
   
    }

    @Override
    public List<enfant> displayAll() {
List<enfant> list =new ArrayList<>();
        String req="select enfant.idenf ,enfant.nomenf,enfant.prenomenf,enfant.sexe,enfant.date_de_naissance,enfant.photoenf,enfant.etat , jardin.nom From jardin , enfant Where enfant.idJ=jardin.id";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
                list.add(new enfant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8)));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(enfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;   
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
            Logger.getLogger(enfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    
        }

    @Override
    public void delete(int id) {
String sql = "DELETE FROM enfant WHERE idenf = ?";
        try {
                    ps=cnx.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }

    @Override
    public void update(enfant e) {
String req="UPDATE enfant set nomenf=?,prenomenf=?,sexe=?,date_de_naissance=?,photoenf=?,etat=? WHERE idenf=?";

 PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(req);
   
        
            ste.setString(1,e.getNomenf());
            ste.setString(2,e.getPrenomenf());
            ste.setString(3,e.getSexe());
             DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
            java.util.Date date =new java.util.Date();
            ste.setString(4,df.format(date));
            
            ste.setString(5,e.getPhotoenf());
            ste.setInt(6,e.getIdenf());
            ste.setString(7,e.getEtat());
            

             ste.executeUpdate();
                  

         } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public enfant findById(Integer idenf) throws SQLException {
       enfant us = new enfant();
        Statement ste = cnx.createStatement();

        String req="SELECT * FROM enfant Where idenf= '"+idenf+ "'";
        rs = ste.executeQuery(req);
         
		
        while(rs.next()){
             us= new enfant();
             us.setIdenf(rs.getInt("idenf"));
             us.setNomenf(rs.getString("nomenf"));
             us.setPrenomenf(rs.getString("prenomenf"));
             us.setSexe(rs.getString("sexe"));
             //us.setDate_de_naissance(rs.getString("date_de_naissance"));
             us.setPhotoenf(rs.getString("photoenf"));
             us.setEtat(rs.getString("etat")); 
        
        }
        return us ;
    }
    public void updateAdmin(enfant e) {
String req="UPDATE enfant set etat='Confirmer' WHERE idenf=?";

 PreparedStatement ste;
    try {
        ste = cnx.prepareStatement(req);
   
        
            ste.setString(8,e.getEtat());
            

             ste.executeUpdate();
                  

         } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
