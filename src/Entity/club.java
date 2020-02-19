/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author AmiR
 */
public class club {
    private int id;
    private String nom;
    private String descr;
    
    
     public club() {
    }

    public club(String nom, String descr) {
        this.nom = nom;
        this.descr = descr;
    }

    public club(int id, String nom, String descr) {
        this.id = id;
        this.nom = nom;
        this.descr = descr;
    }

    public club(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public String toString() {
        return "club{" + "id=" + id + ", nom=" + nom + ", descr=" + descr + '}';
    }
    

   
     
}
