/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entitie.Incident;
import Entitie.Reclamation;
import IService.IServiceRec;
import static Service.ServiceIncident.getDateOnCour;
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
public class ServiceReclamation implements IServiceRec {

    private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;

    public ServiceReclamation() {
        cnx = DataSource.getInstance().getcnx();
    }

    @Override
    public void insert(Reclamation t) {
        Reclamation ren = new Reclamation();
        String req = "insert into reclamation (nom,id_user,name,createdat,user_mail,urgence,description)"
                + " values" + "(?,?,?,now(),?,?,?)";
        //        String req="insert into reclamation (id,sujet,description,date_ajout,user_mail) values"+ "(?,?,?,now(),?)";

        try {

            ps = cnx.prepareStatement(req);

            ps.setString(1, t.getNom());
            ps.setInt(2, t.getId_user());
            ps.setString(3, t.getName());
            ps.setString(4, t.getUser_mail());
            ps.setString(5, t.getUrgence());
            ps.setString(6, t.getDescrition());

            // Executer 
            System.out.println(ps);
            ps.executeUpdate();

            //incrNbr(R.getId_user());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List displayAll() {
        List<Reclamation> list = new ArrayList<>();
        String req = "select * from reclamation";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public void delete(Reclamation t) {
        String req = "delete from reclamation where id_rec=" + t.getId_rec();

        try {
            st = cnx.createStatement();
            int r = st.executeUpdate(req);
            System.out.println(r);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void update(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List triParNom() {
        List<Reclamation> list = new ArrayList<>();
        String req = "select * from reclamation order by name asc";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List rechercher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
