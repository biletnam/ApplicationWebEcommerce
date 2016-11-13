/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.text.ParseException;
import java.sql.Timestamp;

/**
 *
 * @author kithr
 */
public class Ticket {
    private int idticket;
    private int idspec;
    private Timestamp dateheure;
    private String login;
    private int idsalle;
    private int idrang;
    private int idplace;
    
    public Ticket(int idticket, int idspec, Timestamp dateheure, String login,
            int idsalle, int idrang, int idplace) {
        this.idticket = idticket;
        this.idspec = idspec;
        this.dateheure = dateheure;
        this.login = login;
        this.idsalle = idsalle;
        this.idrang = idrang;
        this.idplace = idplace;
    }
    
    public int getIdTicket() {
        return this.idticket;
    }
    
    public int getIdSpectacle() {
        return this.idspec;
    }
    
    public Timestamp getTimestamp() {
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
        return "Ticket{" + "idticket=" + idticket + ", idspec=" + idspec + 
                ", dateheure=" + dateheure.toString() + ", login=" + login +", idsalle=" + idsalle + 
                ", idrang=" + idrang + ", idplace=" + idplace +'}';
    }   
}
