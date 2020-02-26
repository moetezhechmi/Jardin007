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
     private static int id_User;

    public static int getId_User() {
        return id_User;
    }

    public static void setId_User(int id_User) {
        Session.id_User = id_User;
    }
   
public static void disconnect(){
    setId_User(-1);
}
    
}