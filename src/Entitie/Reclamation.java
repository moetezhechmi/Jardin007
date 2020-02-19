/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitie;

/**
 *
 * @author AmiR
 */
public class Reclamation {

    private int id_rec;
    private String name;
    private String type;
    private String etat;
    private String createdby;
    private String urgence;
    private String descrition;

    public Reclamation() {
    }

    public Reclamation(int id_rec, String name, String type, String etat, String createdby, String urgence, String descrition) {
        this.id_rec = id_rec;
        this.name = name;
        this.type = type;
        this.etat = etat;
        this.createdby = createdby;
        this.urgence = urgence;
        this.descrition = descrition;
    }

    public Reclamation(String name, String type, String etat, String createdby, String urgence, String descrition) {
        this.name = name;
        this.type = type;
        this.etat = etat;
        this.createdby = createdby;
        this.urgence = urgence;
        this.descrition = descrition;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getUrgence() {
        return urgence;
    }

    public void setUrgence(String urgence) {
        this.urgence = urgence;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", name=" + name + ", type=" + type + ", etat=" + etat + ", createdby=" + createdby + ", urgence=" + urgence + ", descrition=" + descrition + '}';
    }
    
    

}
