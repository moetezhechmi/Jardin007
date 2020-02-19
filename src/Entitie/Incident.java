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
public class Incident {
    private int id_inc;
    private String nom;
    private String description;
    private String createdby;
    private String date_inc;

    public Incident() {
    }

    public Incident(int id_inc, String nom, String description, String createdby, String date_inc) {
        this.id_inc = id_inc;
        this.nom = nom;
        this.description = description;
        this.createdby = createdby;
        this.date_inc = date_inc;
    }

    public Incident(String nom, String description, String createdby, String date_inc) {
        this.nom = nom;
        this.description = description;
        this.createdby = createdby;
        this.date_inc = date_inc;
    }

    public int getId_inc() {
        return id_inc;
    }

    public void setId_inc(int id_inc) {
        this.id_inc = id_inc;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getDate_inc() {
        return date_inc;
    }

    public void setDate_inc(String date_inc) {
        this.date_inc = date_inc;
    }

    @Override
    public String toString() {
        return "Incident{" + "id_inc=" + id_inc + ", nom=" + nom + ", description=" + description + ", createdby=" + createdby + ", date_inc=" + date_inc + '}';
    }

     
    
}
