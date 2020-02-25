/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entitie.Incident;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import IService.IService;

/**
 *
 * @author AmiR
 */
public class ServiceIncident implements IService {

    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;

    public ServiceIncident() {
        cnx = DataSource.getInstance().getcnx();
    }

    public void insert(Incident t) {
        String req = " insert into incident( nom,description,createdby,date_inc) values('" + t.getNom()
                + "','" + t.getDescription() + "','" + t.getCreatedby() + "','" + getDateOnCour() + "')";
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

    public static java.sql.Date getDateOnCour() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    @Override
    public List displayAll() {
        List<Incident> list = new ArrayList<>();
        String req = "select * from incident";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Incident(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), getDateOnCour()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void delete(Incident t) {
        String req = "delete from incident where id_inc=" + t.getId_inc();

        try {
            st = cnx.createStatement();
            int r = st.executeUpdate(req);
            System.out.println(r);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(Incident t) {
        String sq1 = "UPDATE incident set nom=?,description=? ,createdby=?,date_inc=? where id_inc=" + t.getId_inc();

        try {
            ps = cnx.prepareStatement(sq1);
            if (t.getDescription() != null) {
                ps.setString(1, t.getNom());
                ps.setString(2, t.getDescription());
                ps.setString(3, t.getCreatedby());
                ps.setDate(4, getDateOnCour());

            } else {
                ps.setNull(1, Types.VARCHAR);
            }

            System.out.println(ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List triParNom() {
        List<Incident> list = new ArrayList<>();
        String req = "select * from incident order by nom asc";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Incident(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), getDateOnCour()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List rechercher() {
        List<Incident> list = new ArrayList<>();
        String req = "select * from tableclub where nom=?";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Incident(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), getDateOnCour()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void modifierIncident(Incident r, String colName) {

        try {

            String req = "UPDATE incident SET nom= ?, description=? WHERE id_inc=?  ";

            ps = cnx.prepareStatement(req);

            ps.setString(1, r.getNom());
            System.out.println("SQL Nom");

            ps.setString(2, r.getNom());
            System.out.println("SQL description");

            ps.setInt(3, r.getId_inc());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
