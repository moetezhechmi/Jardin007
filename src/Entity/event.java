/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author AmiR
 */
public class event {
    private int id;
    private String nom;
    private String emplacement;
    private String date;
    private int nbParticipe ; 
    private int maxparticipant ;
    private String nomJ;

    public event(int id, String nom, String emplacement, String date, int maxparticipant, String nomJ) {
        this.id = id;
        this.nom = nom;
        this.emplacement = emplacement;
        this.date = date;
        this.maxparticipant = maxparticipant;
        this.nomJ = nomJ;
    }
    
    
    
     public event() {
    }

    public event(int id, String nom, String emplacement, String date, int nbParticipe, int maxparticipant) {
        this.id = id;
        this.nom = nom;
        this.emplacement = emplacement;
        this.date = date;
        this.nbParticipe = nbParticipe;
        this.maxparticipant = maxparticipant;
    }

    public event(String nom, String emplacement, String date, int  maxparticipant) {
        this.nom = nom;
        this.emplacement = emplacement;
        this.date = date;
        this.maxparticipant = maxparticipant;
    }

    public event(int id, String nom, String emplacement, String date, int maxparticipant) {
        this.id = id;
        this.nom = nom;
        this.emplacement = emplacement;
        this.date = date;
        this.maxparticipant = maxparticipant;
    }

  

   

    public event(String nom,  String emplacement) {
        this.nom = nom;
        this.emplacement = emplacement;
    }

    public event(String nom, String emplacement, String date) {
        this.nom = nom;
        this.emplacement = emplacement;
        this.date = date;
    }

    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
    
    public String getNomJ() {
        return nomJ;
    }

    public void setNomJ(String nom) {
        this.nomJ = nom;
    }

  

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getNbParticipe() {
        return nbParticipe;
    }

    public void setNbParticipe(int nbParticipe) {
        this.nbParticipe = nbParticipe;
    }

    public int getMaxparticipant() {
        return maxparticipant;
    }

    public void setMaxparticipant(int maxparticipant) {
        this.maxparticipant = maxparticipant;
    }

  

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", nom=" + nom + ", emplacement=" + emplacement + ", date=" + date + ", nbParticipe=" + nbParticipe + ", maxparticipant=" + maxparticipant + '}';
    }

 
    

 
    

   
    

    
    
    
     
}
