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
    private int nbparticipant;
    private String nomJ;

    public club(int id, String nom, String descr, int nbparticipant, String nomJ) {
        this.id = id;
        this.nom = nom;
        this.descr = descr;
        this.nbparticipant = nbparticipant;
        this.nomJ = nomJ;
    }

    public club(String nom, String descr, int nbparticipant, String nomJ) {
        this.nom = nom;
        this.descr = descr;
        this.nbparticipant = nbparticipant;
        this.nomJ = nomJ;
    }

    public int getNbparticipant() {
        return nbparticipant;
    }

    public void setNbparticipant(int nbparticipant) {
        this.nbparticipant = nbparticipant;
    }

    public club(int id, String nom, String descr, int nbparticipant) {
        this.id = id;
        this.nom = nom;
        this.descr = descr;
        this.nbparticipant = nbparticipant;
    }
    
    
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
        return "club{" + "id=" + id + ", nom=" + nom + ", descr=" + descr + ", nbparticipant=" + nbparticipant + '}';
    }

   

   
     
}
