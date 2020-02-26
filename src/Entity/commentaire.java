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
    private Integer nbr_etoile;
    private String parent;
    private String jardin;
    
    
     public commentaire() {
    }
     

    public commentaire(int id, String texte, int nbr_etoile) {
        this.id = id;
        this.texte = texte;
        this.nbr_etoile=nbr_etoile;
    }

   

    public commentaire(String texte,int nbr_etoile) {
        this.texte=texte;
        this.nbr_etoile=nbr_etoile;
        
    }

    public commentaire(int id, String texte, Integer nbr_etoile, String parent, String jardin) {
        this.id = id;
        this.texte = texte;
        this.nbr_etoile = nbr_etoile;
        this.parent = parent;
        this.jardin = jardin;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getJardin() {
        return jardin;
    }

    public void setJardin(String jardin) {
        this.jardin = jardin;
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
        public int getNbr_etoile() {
        return nbr_etoile;
    }

    public void setNote(int nbr_etoile) {
        this.nbr_etoile = nbr_etoile;
    }

    @Override
    public String toString() {
        return "commentaire{" + "id=" + id +", nbr_etoile=" + nbr_etoile + ", texte=" + texte + '}';
    }

   
    

    
    
    
     
}
