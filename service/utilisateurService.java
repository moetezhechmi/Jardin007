/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.utilisateur;
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

/**
 *
 * @author Khaled
 */
public class utilisateurService  implements IService<utilisateur>{
    
    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    
    public utilisateurService(){
        cnx=DataSource.getInstance().getcnx();
    }
   public void insert(utilisateur p){
        String req=" insert into utilisateur (Nom,Prenom,Email,Login,Password,Role) values('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getEmail()+"','"+p.getLogin()+"','"+p.getPassword()+"','"+p.getRole()+"')";
        try {
            st=cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   

    @Override
    public List<utilisateur> displayAll() {
        List<utilisateur> list =new ArrayList<>();
        String req="select * from utilisateur";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
                list.add(new utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;
    } 
        
    


    @Override
    public void delete(int id) {
 String sql = "DELETE FROM utilisateur WHERE id = ?";
        try {
                    ps=cnx.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(utilisateur t) {
String sql = "UPDATE utilisateur SET Prenom='moetez'"
                + "WHERE id = 21";    
       try {   
                    ps=cnx.prepareStatement(sql);
          
            ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 }

