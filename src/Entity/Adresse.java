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
public class Adresse {
    
    private String id;
    private String rue;
    private String ville;
    private String governerat;
    private double latitude;
    private double longitude;

    public Adresse() {
    }

    public Adresse(String id, String rue, String ville, String governerat, double latitude, double longitude) {
        this.id = id;
        this.rue = rue;
        this.ville = ville;
        this.governerat = governerat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Adresse(String rue, String ville, String governerat, double latitude, double longitude) {
        this.rue = rue;
        this.ville = ville;
        this.governerat = governerat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getGovernerat() {
        return governerat;
    }

    public void setGovernerat(String governerat) {
        this.governerat = governerat;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
        
    
    
}
