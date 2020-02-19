

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import Entity.commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import package_Database.DataSource;

/**
 *
 * @author akrem
 */
public class CommentaireService  implements IService<commentaire>{
    
    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    
    public CommentaireService(){
        cnx=DataSource.getInstance().getcnx();
    }
    public void insert(commentaire p){
        String req=" insert into avis(texte) values('"+p.getTexte()+"')";
        try {
            st=cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
  /*  public void insertPST (commentaire p)
    {
        String req="insert into personne (nom, texte) values (?,?)";
       try {
           ps=cnx.prepareStatement(req);
           ps.setString(1, p.getNom());
           ps.setString(2, p.getTexte());
           ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(personneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}*/

   /* @Override
    public List<commentaire> displayAll() {
        List<event> list =new ArrayList<>();
        String req="select * from personne";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
                list.add(new event (rs.getInt(1),rs.getString(2),rs.getString("texte")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(personneService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;
    } */
        
    


    @Override
    public void delete(int id) {
        String req="delete from avis where id =?";
        try{
            ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(commentaire t) {
        String sq1="UPDATE avis set texte='uuuuuuuu'" 
                +"WHERE id=12";
        try{
        ps=cnx.prepareStatement(sq1);
        ps.executeUpdate();
        }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<commentaire> displayAll() {
     List<commentaire> list =new ArrayList<>();
        String req="select * from avis ";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
list.add(new commentaire (rs.getInt(1),rs.getString(2)));            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }

    public void insert(commentaire commentaire, String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
   
}
