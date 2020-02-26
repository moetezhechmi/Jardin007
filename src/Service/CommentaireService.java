

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import Entity.commentaire;
import IIService.IServiceAvis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Test.DataSource;

/**
 *
 * @author akrem
 */
public class CommentaireService  implements IServiceAvis<commentaire>{
    
    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    private ResultSet rss;
    
    public CommentaireService(){
        cnx=DataSource.getInstance().getcnx();
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
        String req="delete from comment where id =?";
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
        String sq1="UPDATE comment set texte='uuuuuuuu'" 
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
        String req="select * from comment";
          try {
            st=cnx.createStatement();
            rss=st.executeQuery(req);
            while (rss.next())
            {  
                int idc=rss.getInt(1);
                String com=rss.getString(2);
                int nbe=rss.getInt(3);
                int idp=rss.getInt(4);  
                int idj=rss.getInt(5);  
        
                String rq = "select Nom from utilisateur where id="+idp;
                String rqq = "select nom from jardin where id="+idj;
                st=cnx.createStatement();
                rs=st.executeQuery(rq);
                rs.next();
                String parent=rs.getString(1);  
                st=cnx.createStatement();
                rs=st.executeQuery(rqq);
                rs.next();
                String jardin=rs.getString(1);
                list.add(new commentaire (idc,com,nbe,parent,jardin));            
}
        } catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }

    public void insert(commentaire commentaire, String idJ) {
        String r="select id from jardin where nom='"+idJ+"'";
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(r);
            rs.next();
            int id = rs.getInt(1);
            String req=" insert into comment(texte,nbr_etoile,idP,idJ) values('"+commentaire.getTexte()+"','"+commentaire.getNbr_etoile()+"',1,"+id+")";
            ps=cnx.prepareStatement(req);
            ps.executeUpdate();
            
    }   catch (SQLException ex) {
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            Logger.getLogger(CommentaireService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    
        }

    @Override
    public void insert(commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
