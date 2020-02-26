/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Daytech
 */
public class session {
    public static int id_user;

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        session.id_user = id_user;
    }
    public static void disconnect()
    {
        setId_user(-1);
    }
    
}
