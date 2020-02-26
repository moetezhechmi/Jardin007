/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitie;

import java.sql.Date;



/**
 *
 * @author AmiR
 */
public class Reclamation {

    private int id_rec;
    private String nom_inc;
    private int id_user;
    private String name;
    private Date createDat;
    private String user_mail;
    private String urgence;
    private String descrition;
    private String etat;

    public Reclamation(int id_rec) {
        this.id_rec = id_rec;
    }

    public Reclamation() {
    }

    public Reclamation(int id_rec, String nom, int id_user, String name, Date createDat, String user_mail, String urgence, String descrition) {
        this.id_rec = id_rec;
        this.nom_inc = nom;
        this.id_user = id_user;
        this.name = name;
        this.createDat = createDat;
        this.user_mail = user_mail;
        this.urgence = urgence;
        this.descrition = descrition;
    }

    public Reclamation(String nom, int id_user, String name, Date createDat, String user_mail, String urgence, String descrition) {
        this.nom_inc = nom;
        this.id_user = id_user;
        this.name = name;
        this.createDat = createDat;
        this.user_mail = user_mail;
        this.urgence = urgence;
        this.descrition = descrition;
    }

    public Reclamation(String nom_inc, int id_user, String name, Date createDat, String user_mail, String urgence, String descrition, String etat) {
        this.nom_inc = nom_inc;
        this.id_user = id_user;
        this.name = name;
        this.createDat = createDat;
        this.user_mail = user_mail;
        this.urgence = urgence;
        this.descrition = descrition;
        this.etat = etat;
    }

    public Reclamation(int id_rec, String nom_inc, int id_user, String name, Date createDat, String user_mail, String urgence, String descrition, String etat) {
        this.id_rec = id_rec;
        this.nom_inc = nom_inc;
        this.id_user = id_user;
        this.name = name;
        this.createDat = createDat;
        this.user_mail = user_mail;
        this.urgence = urgence;
        this.descrition = descrition;
        this.etat = etat;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getNom() {
        return nom_inc;
    }

    public void setNom(String nom) {
        this.nom_inc = nom;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDat() {
        return createDat;
    }

    public void setCreateDat(Date createDat) {
        this.createDat = createDat;
    }

    public String getUser_mail() {
        return user_mail;
    }

    public void setUser_mail(String user_mail) {
        this.user_mail = user_mail;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_rec=" + id_rec + ", nom=" + nom_inc + ", id_user=" + id_user + ", name=" + name + ", createDat=" + createDat + ", user_mail=" + user_mail + ", urgence=" + urgence + ", descrition=" + descrition + '}';
    }

   
    

}