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
public class Reservation {

    private String login;
    private int idspec;
    private Timestamp dateheure;
    private int idsalle;
    private int idrang;
    private int idplace;
    
    
    public Reservation(String login, int idspec, Timestamp dateheure,
            int idsalle, int idrang, int idplace) {
        this.login = login;
        this.idspec = idspec;
        this.dateheure = dateheure;
        this.idsalle = idsalle;
        this.idrang = idrang;
        this.idplace = idplace;
        
    }
    
    public int getIdSpectacle() {
        return this.idspec;
    }
    
    public Timestamp getTimestampHeure() {
        return this.dateheure;
    }    
    
    public String getLogin() {
        return this.login;
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
    @Override
    public String toString() {
        return "Ticket{" + "login=" + login + ", idspec=" + idspec + 
                ", dateheure=" + dateheure.toString() +", idsalle=" + idsalle + 
                ", idrang=" + idrang + ", idplace=" + idplace + " }";
    } 

}