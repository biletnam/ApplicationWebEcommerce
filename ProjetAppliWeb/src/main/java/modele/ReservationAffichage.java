/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.sql.Timestamp;

/**
 *
 * @author kithr
 */
public class ReservationAffichage {
    
    private String titre;
    private int idspec;
    private Timestamp dateheure;
    private int idsalle;
    private int idrang;
    private int idplace;
    private int prix;
    
    
    public ReservationAffichage(String titre, int idspec, Timestamp dateheure,
            int idsalle, int idrang, int idplace, int prix) {
        this.titre = titre;
        this.dateheure = dateheure;
        this.idspec = idspec;
        this.idsalle = idsalle;
        this.idrang = idrang;
        this.idplace = idplace;
        this.prix = prix;
    }
    
    public String getTitre() {
        return this.titre;
    }
   
    public int getIdspec() {
        return this.idsalle;
    }
    public Timestamp getDateHeure() {
        return this.dateheure;
    }    
    
    public int getIdSalle() {
        return this.idsalle;
    }
    
    public int getIdRang() {
        return this.idrang;
    }
    
    public int getIdPlace() {
        return this.idplace;
    }
    
    public int getPrix() {
        return this.prix;
    }
    
    @Override
    public String toString() {
        return "Ticket{" + "titre=" + titre + 
                ", dateheure=" + dateheure.toString() +", idsalle=" + idsalle + 
                ", idrang=" + idrang + ", idplace=" + idplace + " }";
    }
}
