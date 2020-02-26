/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Khaled
 */
public class Session {
    private static utilisateur utilisateur;

    public static utilisateur getUtilisateur() {
        return utilisateur;
    }

    public static void setUtilisateur(utilisateur utilisateur) {
        Session.utilisateur = utilisateur;
    }

    
   
    

 public static void disconnect()  {
     Session.utilisateur=null;
 } 
   
}