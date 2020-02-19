

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.event;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
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
import package_Database.DataSource;

/**
 *
 * @author AmiR
 */
public class EventService  implements IService<event>{
    
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
        
    


    @Override
    public void delete(int id) {
        String req="delete from tableevent where id =?";
        try{
            ps=cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(event t) {
        String sq1="UPDATE tableevent set nom='disney',emplacement='mahdia'" 
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
    public List<event> displayAll() {
     List<event> list =new ArrayList<>();
        String req="select * from tableevent order by nom asc";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new event (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }
    
  
    public List<event> rechercher() {
     List<event> list =new ArrayList<>();
        String req="select * from tableevent where nom='disney'";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new event (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }

       public void insert(event p){
        String req=" insert into tableevent(nom,emplacement,date) values('"+p.getNom()+"','"+p.getEmplacement()+"','"+p.getDate()+"')";
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
       
    }
}
