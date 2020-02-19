/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entitie.Incident;
import IService.IServiceIncident;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AmiR
 */
public class ServiceIncident implements IServiceIncident {

    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;

    public ServiceIncident() {
        cnx = DataSource.getInstance().getcnx();
    }

    public void insert(Incident t) {
        String req = " insert into incident( nom,description,createdby,date_inc) values('" + t.getNom() + "','" + t.getDescription() + "','" + t.getCreatedby() + "','" + t.getDate_inc() + "')";
        try {
            st = cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List displayAll() {
    List<Incident> list =new ArrayList<>();
        String req="select * from incident";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new Incident (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;      }

    @Override
    public void delete(int id) {
        String req = "delete from incident where id_inc =?";
        try {
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void update(Object t) {
      String sq1="UPDATE incident set nom=?,description=? ,createdby=?,date_inc=? where id_inc= ?";
         
        try{
        ps=cnx.prepareStatement(sq1);
        ps.executeUpdate();
        }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List triParNom() {
List<Incident> list =new ArrayList<>();
        String req="select * from incident order by nom asc";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new Incident (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;     
    }

    @Override
    public List rechercher() {
     List<Incident> list =new ArrayList<>();
        String req="select * from tableclub where nom=?";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
          list.add(new Incident (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));     
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;     
        }

}
