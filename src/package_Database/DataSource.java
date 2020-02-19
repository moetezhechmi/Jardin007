
package package_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataSource {
    private String username="root";
    private String password="";
    private String url="jdbc:mysql://127.0.0.1/esprit";
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
