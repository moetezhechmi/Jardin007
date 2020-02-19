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
/*String sql = "UPDATE utilisateur SET Prenom='moetez'"
                + "WHERE id = 21";    
       try {   
                    ps=cnx.prepareStatement(sql);
          
            ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
String req="UPDATE `utilisateur` SET `Nom`=?,`Prenom`=?,`Email`=?,`Login`=?,`Password`=?,`Role`=? WHERE Id=?";

 PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1,t.getNom());
            ste.setString(2,t.getPrenom());
            ste.setString(3,t.getEmail());
            ste.setString(4,t.getLogin());
            ste.setString(5,t.getPassword());
            ste.setString(6,t.getRole());
            ste.setInt(7,t.getId());

            
            

             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
}
 
    
    public String authentification(utilisateur u){
        
       String login = u.getLogin();
       String password = u.getPassword(); 
       String role=u.getRole();
       String userNameDB = "";
       String passwordDB = "";
       String roleDB = "";


 String req= ("SELECT `login`, `password`, `role` FROM `utilisateur`");
   try
 { 
 st=cnx.createStatement();
            rs=st.executeQuery(req);

            while(rs.next())
 {
 userNameDB = rs.getString("login");
 passwordDB = rs.getString("password");
 roleDB = rs.getString("role");
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
 }

