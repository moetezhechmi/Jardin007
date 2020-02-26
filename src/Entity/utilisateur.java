/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Khaled
 */
public class utilisateur {
    private int Id;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Login;
    private String Password;
    private String Role;
   

    public utilisateur() {
    }

    public utilisateur(int Id, String Nom, String Prenom, String Email, String Login, String Password, String Role) {
        this.Id = Id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Login = Login;
        this.Password = Password;
        this.Role = Role;
        
    }

    public utilisateur(String Nom, String Prenom, String Email, String Login, String Password, String Role) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Login = Login;
        this.Password = Password;
        this.Role = Role;
       
    }

    public utilisateur(int Id, String Login, String Password, String Role) {
        this.Id = Id;
        this.Login = Login;
        this.Password = Password;
        this.Role = Role;
    }

    public utilisateur(String Login, String Password, String Role) {
        this.Login = Login;
        this.Password = Password;
        this.Role = Role;
    }

    public utilisateur(String Login, String Password) {
        this.Login = Login;
        this.Password = Password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String Login) {
        this.Login = Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "utilisateur{" + "Id=" + Id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + ", Login=" + Login + ", Password=" + Password + ", Role=" + Role + '}';
    }

    
   

   
   
    
    
    
   
}
