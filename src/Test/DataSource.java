
package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataSource {
    public static String serverUrl = "http://192.168.43.147/";
    private String username="root";
    private String password="root";
    private String url="jdbc:mysql://localhost:3306/pidev";
    private Connection cnx;
    private static DataSource instance;
     public Connection getcnx() {
        return cnx;
    }
    
    
    private DataSource(){
        try {
            cnx=DriverManager.getConnection(url, username, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
public static DataSource getInstance(){
    if(instance==null)
        instance=new DataSource();
    return instance;
    
}

}
