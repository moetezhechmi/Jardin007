/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3a2jdbc;

import Entity.club;
import service.ClubService;

/**
 *
 * @author AmiR
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        


     
        club p1 = new club ("ADance  ", "club5");
        ClubService ps =new ClubService();
       //ps.delete(10);
      //  ps.update(p1);
       // ps.insert(p1);
       ps.displayAll().forEach(System.out::println);
       // System.out.println("******************************");
        // ps.rechercher().forEach(System.out::println);
       // ps.triParNom().forEach(System.out::println);

    }
    
}
