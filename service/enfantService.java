/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.enfant;
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
public class enfantService implements IServiceenfant<enfant> {
private Connection cnx;
    public Statement st;
    public PreparedStatement ps;
    private ResultSet rs;
    
    public enfantService(){
        cnx=DataSource.getInstance().getcnx();
   
    }

    @Override
    public void insert(enfant e) {
         String req=" insert into enfant (nomenf,prenomenf,age) values('"+e.getNomenf()+"','"+e.getPrenomenf()+"','"+e.getAge()+"')";
        try {
            st=cnx.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(enfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(enfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<enfant> displayAll() {
List<enfant> list =new ArrayList<>();
        String req="select * from enfant";
          try {
            st=cnx.createStatement();
            rs=st.executeQuery(req);
            while (rs.next())
            {
                list.add(new enfant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(enfantService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return list;   
    }

    @Override
    public void delete(int id) {
String sql = "DELETE FROM enfant WHERE id = ?";
        try {
                    ps=cnx.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ps.executeUpdate();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }

    @Override
    public void update(enfant e) {
String req="UPDATE `enfant` SET `nomenf`=?,`prenomenf`=?,`age`=? WHERE Id=?";

 PreparedStatement ste;
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1,e.getNomenf());
            ste.setString(2,e.getPrenomenf());
            ste.setInt(3,e.getAge());
            ste.setInt(4,e.getIdenf());

             ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(utilisateurService.class.getName()).log(Level.SEVERE, null, ex);
        }    }
   
   
    
    
}
