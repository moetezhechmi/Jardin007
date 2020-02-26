/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
/**
 *
 * @author Khaled
 */
public class enfant {
     private int idenf;
    private String nomenf;
    private String prenomenf;
    private String sexe ;
    private Date date_de_naissance ;
    private String photoenf ;
    private String etat;
    private String jardin;

    public enfant() {
    }

    public enfant(int idenf, String nomenf, String prenomenf, String sexe, Date date_de_naissance, String photoenf, String etat, String jardin) {
        this.idenf = idenf;
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.photoenf = photoenf;
        this.etat = etat;
        this.jardin = jardin;
    }

    public enfant(String nomenf, String prenomenf, String sexe, Date date_de_naissance, String photoenf, String etat, String jardin) {
        this.nomenf = nomenf;
        this.prenomenf = prenomenf;
        this.sexe = sexe;
        this.date_de_naissance = date_de_naissance;
        this.photoenf = photoenf;
        this.etat = etat;
        this.jardin = jardin;
    }

   
   

    public String getJardin() {
        return jardin;
    }

    public void setJardin(String jardin) {
        this.jardin = jardin;
    }

   
    

   
    
   
    public int getIdenf() {
        return idenf;
    }

    public void setIdenf(int idenf) {
        this.idenf = idenf;
    }

    public String getNomenf() {
        return nomenf;
    }

    public void setNomenf(String nomenf) {
        this.nomenf = nomenf;
    }

    public String getPrenomenf() {
        return prenomenf;
    }

    public void setPrenomenf(String prenomenf) {
        this.prenomenf = prenomenf;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDate_de_naissance() {
        return date_de_naissance;
    }

    public void setDate_de_naissance(Date date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getPhotoenf() {
        return photoenf;
    }

    public void setPhotoenf(String photoenf) {
        this.photoenf = photoenf;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    @Override
    public String toString() {
        return "enfant{" + "idenf=" + idenf + ", nomenf=" + nomenf + ", prenomenf=" + prenomenf + ", sexe=" + sexe + ", date_de_naissance=" + date_de_naissance + ", photoenf=" + photoenf + '}';
    }

   
}
