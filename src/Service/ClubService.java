


package service;

import Entity.club;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import static jdk.nashorn.internal.runtime.Debug.id;
import pkg3a2jdbc.DataSource;

/**
 *
 * @author AmiR
 */
public class ClubService  implements IService<club>{
    
    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;

    
    public ClubService(){
        cnx=DataSource.getInstance().getcnx();
    }
    public void insert(club p ,String idJ){
        
        
        String r="select id from jardin where nom='"+idJ+"'";
        try {
            st=cnx.createStatement();
            rs=st.executeQuery(r);
            rs.next();
            int id = rs.getInt(1);
             
            String req=" insert into tableclub( nom,descr,nbparticipant,idJ,idUser) values('"+p.getNom()+"','"+p.getDescr()+"',"+p.getNbparticipant()+","+id+",1)";
        
            st=cnx.createStatement();
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
 

    @Override
    public void delete(int id) {
        String req="delete from tableclub where id =?";
        try{
            ps=cnx.prepareStatement(req);
          ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(club t)throws SQLException {
   
       try { 
String req="UPDATE tableclub set nom=?,descr=? WHERE id=?";

 PreparedStatement ste=cnx.prepareStatement(req);
        
            ste.setString(1,t.getNom());
            ste.setString(2,t.getDescr());
            
            ste.setInt(3,t.getId());

            

             ste.executeUpdate();
                  

         } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
   
        

        
    

    @Override
    public List<club> displayAll() {
     List<club> list =new ArrayList<>();
        String req="select * from tableclub";
        
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new club (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }
    
        
   public ObservableList<PieChart.Data> StatNbrParticipant() {
        ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
        try {
            PreparedStatement st = cnx.prepareStatement("select nbparticipant,nom from tableclub group BY nom");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new PieChart.Data(rs.getString(2), rs.getInt(1)));
            }
            ObservableList<PieChart.Data> observableList;
            observableList = FXCollections.observableList(list);
            //System.out.println("ici" + observableList.size());
            return observableList;

        } catch (SQLException ex) {
             Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
       
    
        
    
       
    
    
    public List<club> triParNom() {
     List<club> list =new ArrayList<>();
        String req="select * from tableclub order by nom asc";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new club (rs.getInt(1),rs.getString(2),rs.getString(3)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }
    
    
    
    
  
    public List<club> rechercher() {
     List<club> list =new ArrayList<>();
        String req="select * from tableclub where nom=?";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new club (rs.getInt(1),rs.getString(2),rs.getString(3)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }
    
    
      public void modifier(club t)throws SQLException {
   
       try { 
String req="UPDATE tableclub set nbparticipant=nbparticipant+1 WHERE id=?";

 PreparedStatement ste=cnx.prepareStatement(req);
        
            
            ste.setInt(1,t.getId());

            

             ste.executeUpdate();
                  

         } catch (SQLException ex) {
            ex.printStackTrace();
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
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    
        }

    @Override
    public void insert(club t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public List<club> displayAllC(int id) {
                String req="select tableclub.id , tableclub.nom , tableclub.descr ,tableclub.nbparticipant, jardin.nom  as nomJ from jardin , tableclub  where  jardin.id=tableclub.idJ and jardin.idC="+id+""; 

     List<club> list =new ArrayList<>();
        
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new club (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;    }
    
     

   
    
}
