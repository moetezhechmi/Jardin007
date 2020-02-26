/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack_database;

import Entity.enfant;
import Entity.utilisateur;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import service.enfantService;
import service.utilisateurService;

/**
 *
 * @author Khaled
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) throws ParseException {
        
     DataSource ds1=DataSource.getInstance();
       /*    System.out.println(ds1);
        DataSource ds2=DataSource.getInstance();
        DataSource ds3=DataSource.getInstance();
        System.out.println(ds2);
        System.out.println(ds3);*/

         
 
        utilisateurService ps =new utilisateurService();
        enfantService es = new enfantService();
    String sDate1="2020-01-14";  
    Date date1=new SimpleDateFormat("yyyy-dd-MM").parse(sDate1);  
     
           
    
        utilisateur p1= new utilisateur ("ben othmene","akrem","akrem.benothmene@esprit.tn","akrembenothmene","azerty123","Parent");
    
        //es.insert(e1);
        //ps.delete(21);
        //ps.update(5,p1);
      // ps.insertPST(p1);
      es.displayAll().forEach(System.out::println);
      //es.delete(3);

    }
    
}
