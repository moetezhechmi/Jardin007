/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Session;
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
import java.sql.SQLException;


/**
 *
 * @author Khaled
 */
public class utilisateurService  implements IServiceutilisateur<utilisateur>{
    
    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    
     public utilisateurService() {
        cnx = DataSource.getInstance().getcnx();
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
        try {
        String req="select * from utilisateur";
          
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
/*String sql = "UPDATE utilisateur SET Prenom='moetez'"
                + "WHERE id = 5";    
       try {   
                    ps=cnx.prepareStatement(sql);
          
            ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
       }*/
try { 
String req="UPDATE utilisateur set Nom=?,Prenom=?,Email=?,Login=?,Password=?,Role=? WHERE id=?";

 PreparedStatement ste=cnx.prepareStatement(req);
        
            ste.setString(1,t.getNom());
            ste.setString(2,t.getPrenom());
            ste.setString(3,t.getEmail());
            ste.setString(4,t.getLogin());
            ste.setString(5,t.getPassword());
            ste.setString(6,t.getRole());
            ste.setInt(7,t.getId());

            

             ste.executeUpdate();
                  

         } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    
    public String authentification(utilisateur u){
        
       String login = u.getLogin();
       String password = u.getPassword(); 
       String role=u.getRole();
       String userNameDB = "";
       String passwordDB = "";
       String roleDB = "";


 String req= ("SELECT `Login`,`Password`,`Role` ,`Id` FROM `utilisateur`");
   try
 { 
 Statement ste=cnx.createStatement();
            rs=ste.executeQuery(req);

            while(rs.next())
 {
 userNameDB = rs.getString("Login");
 passwordDB = rs.getString("Password");
 roleDB = rs.getString("Role");
     Session.setId_User(rs.getInt("Id"));
 if(login.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Administrateur"))
 return "Administrateur";
 else if(login.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Responsable"))
 return "Responsable";
 else if(login.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Parent"))
 return "Parent";
 }
 }
 catch(SQLException e)
 {
 e.printStackTrace();
 }
 return "Invalid user credentials";
}

     public  utilisateur recupererUtilisateur (String nom) throws SQLException{
        utilisateur us = new utilisateur();
        Statement ste = cnx.createStatement();

        String req="SELECT * FROM utilisateur Where Login= '"+nom+ "'";
        rs = ste.executeQuery(req);
         
		
        while(rs.next()){
             us= new utilisateur();
             us.setId(rs.getInt("id"));
             us.setNom(rs.getString("nom"));
             us.setPrenom(rs.getString("prenom"));
             us.setEmail(rs.getString("email"));
             us.setLogin(rs.getString("login"));
             us.setRole(rs.getString("role"));
              
        
        }
        return us ;
    }
     
     public utilisateur findById(Integer id) {
        utilisateur event = null;
        try {
            String req = "select * from utilisateur where id =?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 event = new utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return event;  
    }
   public utilisateur findByName(String id) {
        utilisateur event = null;
        try {
            String req = "select * from utilisateur where nom =?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 event = new utilisateur(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return event;  
    }

     public boolean finduser(String user) throws SQLException
          {
               String req="Select Login from utilisateur where Nom = ?";
                try {
             
            PreparedStatement ste= cnx.prepareStatement(req);
            ste.setString(1,user);
            ResultSet res= ste.executeQuery();
           
              
        while(res.next()){
        if ( res.getString("Login").equals(user)){
            return true;
            
        } else {
            return false;
        }
        }
          }catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
}
          return false;
          }
 }

