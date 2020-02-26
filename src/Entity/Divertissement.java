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
public class Divertissement {
    int id;
    String nom;
    String imageUrl;
    int idJ;
    String nomJ;
    public Divertissement() {
    }

    public Divertissement(int id, String nom, String imageUrl,int idJ) {
        this.id = id;
        this.nom = nom;
        this.imageUrl = imageUrl;
        this.idJ=idJ;

    }

    public Divertissement(String nom, String imageUrl,int idJ) {
        this.nom = nom;
        this.imageUrl = imageUrl;
        this.idJ=idJ;
    }
    
    public Divertissement(int id, String nom, String nomJ) {
        this.nom = nom;
        this.nomJ = nomJ;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getIdJ() {
        return id;
    }

    public void setIdJ(int idJ) {
        this.idJ = idJ;
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

    public void setNomJ(String nomJ) {
        this.nomJ = nomJ;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Divertissement{" + "id=" + id + ", nom=" + nom + ", imageUrl=" + imageUrl + '}';
    }

    
}
