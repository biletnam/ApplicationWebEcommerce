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
public class Horaire {
 
    private Timestamp dateHeure;
    
    public Horaire(Timestamp dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Timestamp getTimestampHeure() {
        return this.dateHeure;
    }
   
   @Override
    public String toString() {
        return "la date et l'heure{"  + dateHeure + "}";
    }
}


