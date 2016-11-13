/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author berrazar
 */
public class Salle {
    
    private int idSalle;
    private int nbPlaces; 
    
    public Salle(int idsalle, int nbplaces) {
        this.idSalle = idsalle;
        this.nbPlaces = nbplaces ;
    }

    public int getIdSalle() {
        return this.idSalle;
    }
   
    public int getNbPlaces(){
        return this.nbPlaces;
    }
   
    @Override
    public String toString() {
        return "salle{" + "idsalle=" + idSalle + ", nombre de places=" + nbPlaces + '}';
    }
}
    

