/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_Database;

import Entity.event;
import java.sql.SQLException;
import service.EventService;

/**
 *
 * @author AmiR
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws SQLException {
        
     /*   DataSource ds1=DataSource.getInstance();
        System.out.println(ds1);
        DataSource ds2=DataSource.getInstance();
        DataSource ds3=DataSource.getInstance();
        System.out.println(ds2);
        System.out.println(ds3);*/

     
        event p1 = new event ("chaima", "YYY","hhghgh");
        EventService ps =new EventService();
       //ps.delete(10);
      //  ps.update(p1);
         ps.insert(p1);
          ps.displayAll().forEach(System.out::println);
     ps.rechercher().forEach(System.out::println);

    }
    
}
