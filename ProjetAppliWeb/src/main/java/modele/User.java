/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author kithr
 */
public class User {
    private String login;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    
    public User(String login, String nom, String prenom, String password, String email) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public String getNom() {
        return this.nom;
    }
    
    public String getPrenom() {
        return this.prenom;
    }    
    
    public String getPassword() {
        return this.password;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    @Override
    public String toString() {
        return "User{" + "login=" + login + ", nom=" + nom + 
                ", prenom=" + prenom +", password=" + password + ", email=" + email +'}';
    }
}
