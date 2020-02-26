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
    int id_rec;
    String nom;
    String name;
    String desc;
    String urg;
    Date date;
    String nomuser;
    String  prenom;
    String email;
    String jardin;
    String etat;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJardin() {
        return jardin;
    }

    public void setJardin(String jardin) {
        this.jardin = jardin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public ListeRecl(String nom, String name, String desc, String urg, Date date, String nomuser, String prenom, String email,String nomj,String etat) {
        this.nom = nom;
        this.name = name;
        this.desc = desc;
        this.urg = urg;
        this.date = date;
        this.nomuser = nomuser;
        this.prenom = prenom;
        this.email = email;
        this.jardin = nomj;
        this.etat=etat;
    }

    public ListeRecl(int id_rec, String nom, String name, String desc, String urg, Date date, String nomuser, String prenom, String email, String jardin, String etat) {
        this.id_rec = id_rec;
        this.nom = nom;
        this.name = name;
        this.desc = desc;
        this.urg = urg;
        this.date = date;
        this.nomuser = nomuser;
        this.prenom = prenom;
        this.email = email;
        this.jardin = jardin;
        this.etat = etat;
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
        return email;
    }

    public void setEmailuser(String emailuser) {
        this.email = emailuser;
    }

    @Override
    public String toString() {
        return "ListeRecl{" + "nom=" + nom + ", name=" + name + ", desc=" + desc + ", urg=" + urg + ", date=" + date + ", nomuser=" + nomuser + ", prenom=" + prenom + ", emailuser=" + email + '}';
    }
    
    
    
}


