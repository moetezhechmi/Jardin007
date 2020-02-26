/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author moetez
 */
public class Jardin {
        private int id;
        private String nom;
        Adresse adresse;
        private String numTel;
        private String mail;
        private String imageUrl;

    public Jardin() {
    }

    public Jardin(int id, String nom, String numTel, String mail) {
        this.id = id;
        this.nom = nom;
        this.numTel = numTel;
        this.mail = mail;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Jardin(String nom, Adresse adresse, String numTel, String mail, String imageUrl) {
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.mail = mail;
        this.imageUrl = imageUrl;
    }

    public Jardin(int id, String nom, Adresse adresse, String numTel, String mail) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.mail = mail;
    }

    public Jardin(String nom, Adresse adresse, String numTel, String mail) {
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.mail = mail;
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

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Jardin{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", numTel=" + numTel + ", mail=" + mail + '}';
    }
    

    
    
    
    
}
