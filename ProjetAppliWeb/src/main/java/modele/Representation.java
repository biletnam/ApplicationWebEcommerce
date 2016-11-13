/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;


import java.sql.Timestamp;

/**
 *
 * @author berrazar
 */
public class Representation {
    
    private int idSpec;
    private int idSalle;
    private Timestamp dateHeure;
    
    public Representation(int idspec,int idSalle,Timestamp dateHeure) {
        this.idSpec=idspec;
        this.idSalle=idSalle;
        this.dateHeure=dateHeure;
       
    }

    public int getIdSalle() {
        return this.idSalle;
    }
   
    public int getIdSpec(){
        return this.idSpec;
    }
   
    public Timestamp getDateHeure(){
       
        return this.dateHeure;
        
    }
      
    @Override
    public String toString() {
        return "representation{" + "idspectacle=" + idSpec + ", idsalle=" + idSalle + "date et heure "+ dateHeure + "}";
    }
}

    