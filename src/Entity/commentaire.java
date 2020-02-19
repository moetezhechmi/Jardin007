/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import javafx.scene.control.TableColumn;

/**
 *
 * @author akrem
 */
public class commentaire {
    private int id;
    
    private String texte;
    
    
     public commentaire() {
    }

    public commentaire(int id,String texte) {
        this.id = id;
        this.texte = texte;
    }

   

    public commentaire(String text) {
        this.texte=text;
    }

 


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    


  

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id + ", texte=" + texte + '}';
    }

   
    

    
    
    
     
}
