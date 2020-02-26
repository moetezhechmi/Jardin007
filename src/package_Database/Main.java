/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package_Database;


import Entity.commentaire;
import service.CommentaireService;

/**
 *
 * @author akrem
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        
  

     
        commentaire p1 = new commentaire ("akrem",5);
        CommentaireService ps =new CommentaireService();
       //ps.delete(10);
        //ps.update(p1);
        ps.insert(p1);
        //ps.displayAll().forEach(System.out::println);

    }
    
}
