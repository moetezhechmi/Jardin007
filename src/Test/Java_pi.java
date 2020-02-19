/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import Entitie.Incident;
import Service.ServiceIncident;

/**
 *
 * @author AmiR
 */
public class Java_pi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        Incident p1 = new Incident ("probleme  ", "ce probleme est tres grave","amir","vendredi");
        Service.ServiceIncident ps =new ServiceIncident();
        ps.delete(2);
      //  ps.update(p1);
        //ps.insert(p1);
        ps.displayAll().forEach(System.out::println);
       // System.out.println("******************************");
        // ps.rechercher().forEach(System.out::println);
       // ps.triParNom().forEach(System.out::println);

    }
    
}
