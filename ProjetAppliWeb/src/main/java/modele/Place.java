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
public class Place {
    private int idSalle;
    private int idRang;
    private int idPlace; 
    
    public Place(int idsalle,int idrang,int idplace) {
        this.idSalle = idsalle;
        this.idRang = idrang;
        this.idPlace = idplace;
    }

    public int getIdSalle() {
        return this.idSalle ;
    }
   
    public int getIdRang(){
        return this.idRang;
    }
    
    public int getIdPlace(){
        return this.idPlace;
    }
   
    @Override
    public String toString() {
        return "Place{" + "idrang=" + idRang +", idsalle=" + idSalle + ", idplace=" + idPlace + '}';
    }
}
    

