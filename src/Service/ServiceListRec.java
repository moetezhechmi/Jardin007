/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entitie.Incident;
import Entitie.ListeRecl;
import Entitie.Reclamation;
import static Service.ServiceIncident.getDateOnCour;
import Test.DataSource;
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
public class ServiceListRec {
    
      private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
public ServiceListRec() {
        cnx = DataSource.getInstance().getcnx();
    }

    
    
    
     public List displayAllRec() {
        List<ListeRecl> list = new ArrayList<>();
        String req = "SELECT reclamation.id_rec,reclamation.nom,reclamation.name,reclamation.description,reclamation.urgence,reclamation.createdat,utilisateur.Nom,utilisateur.Prenom,utilisateur.Email,jardin.nom,reclamation.etat FROM jardin , utilisateur ,reclamation WHERE reclamation.id_user=utilisateur.Id and reclamation.idJ=jardin.id";
        try {
            st = cnx.createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new ListeRecl(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceIncident.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
    
}
