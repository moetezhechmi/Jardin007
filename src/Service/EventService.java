

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.event;
import IIService.IServiceEvent;
//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Test.DataSource;

/**
 *
 * @author AmiR
 */
public class EventService  implements IServiceEvent<event>{
    
    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    
    public EventService(){
        cnx=DataSource.getInstance().getcnx();
    }
    /*public void insert(event p){
        String req=" insert into tableevent(nom,emplacement,date) values('"+p.getNom()+"','"+p.getEmplacement()+"',,'"+p.getDate()+"')";
        try {
            st=cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }*/
  /*  public void insertPST (event p)
    {
        String req="insert into tableevent (nom, emplacement,date) values (?,?,?)";
       try {
           ps=cnx.prepareStatement(req);
           ps.setString(1, p.getNom());
           ps.setString(2, p.getEmplacement());
                      ps.setString(3, p.getDate());

           ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}*/

   /* @Override
    public List<event> displayAll() {
        List<event> list =new ArrayList<>();
        String req="select * from personne";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
                list.add(new event (rs.getInt(1),rs.getString(2),rs.getString("prenom")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(personneService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;
    } */
        
    


    
    public void delete(int id) {
        String req="delete from tableevent where id =?";
        try{
            ps=cnx.prepareStatement(req);
             ps.setInt(1, id);
             System.out.println(id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(event t) {
       try { 
String req="UPDATE tableevent set Nom=?,emplacement=?,date=?,maxparticipant=? WHERE id=?";

 PreparedStatement ste=cnx.prepareStatement(req);
        
            ste.setString(1,t.getNom());
            ste.setString(2,t.getEmplacement());
            ste.setString(3,t.getDate());
            ste.setInt(4,t.getMaxparticipant());
            ste.setInt(5 , t.getId()) ;
            

            

             ste.executeUpdate();
                  

         } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
 
    
    

    @Override
    public List<event> displayAll() {
     List<event> list =new ArrayList<>();
        String req="select * from tableevent order by nom asc";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new event (rs.getInt(1) , rs.getString(2),rs.getString(3),rs.getString(4) ,rs.getInt(5) ,rs.getString(6) ));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }
    
    /**
     *
     * @return
     */
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
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    
        }
    
  
    public List<event> rechercher() {
     List<event> list =new ArrayList<>();
        String req="select * from tableevent where nom='disney'";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new event (rs.getString(2),rs.getString(3),rs.getString(4)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }

       public void insert(event p,String idJ){
           
        String r="select id from jardin where nom='"+idJ+"'";
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(r);
            rs.next();
            int id = rs.getInt(1);
            String req=" insert into tableevent(nom,emplacement,date,maxparticipant,idJ,idUser) values('"+p.getNom()+"','"+p.getEmplacement()+"','"+p.getDate()+"',"+p.getMaxparticipant()+","+id+",1)";
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
         public void participer(event evenement) throws SQLException
    {
         String r="UPDATE  tableevent SET nbParticipe=nbParticipe+1 WHERE id=?";
              Connection conn = DataSource.getInstance().getcnx();
              PreparedStatement st = conn.prepareStatement(r);
              st.setInt(1, evenement.getId());
              st.executeUpdate();
    }

    
       
      public List<event> displayAllC(int id) {
                String req="select tableevent.id , tableevent.nom , tableevent.emplacement , tableevent.date ,tableevent.maxparticipant , jardin.nom as nomJ from jardin , tableevent  where  jardin.id=tableevent.idJ and jardin.idC="+id+""; 

     List<event> list =new ArrayList<>();
        
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {   
                list.add(new event (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }

    @Override
    public void insert(event t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
