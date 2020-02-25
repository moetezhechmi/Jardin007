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
public class ListeRecl {
    String nom;
    String name;
    String desc;
    String urg;
    Date date;
    String nomuser;
    String  prenom;
    String emailuser;

    public ListeRecl(String nom, String name, String desc, String urg, Date date, String nomuser, String prenom, String emailuser) {
        this.nom = nom;
        this.name = name;
        this.desc = desc;
        this.urg = urg;
        this.date = date;
        this.nomuser = nomuser;
        this.prenom = prenom;
        this.emailuser = emailuser;
    }

    public ListeRecl() {
    }

    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrg() {
        return urg;
    }

    public void setUrg(String urg) {
        this.urg = urg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmailuser() {
        return emailuser;
    }

    public void setEmailuser(String emailuser) {
        this.emailuser = emailuser;
    }

    @Override
    public String toString() {
        return "ListeRecl{" + "nom=" + nom + ", name=" + name + ", desc=" + desc + ", urg=" + urg + ", date=" + date + ", nomuser=" + nomuser + ", prenom=" + prenom + ", emailuser=" + emailuser + '}';
    }
    
    
    
}


