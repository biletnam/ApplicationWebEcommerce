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
public class Rang {
    private int idSalle;
    private int idRang;
    private String categorie; 
    
    public Rang(int idsalle,int idrang,String cath ) {
        this.idSalle = idsalle;
        this.idRang = idrang;
        this.categorie = cath;
    }

    public int getIdSalle() {
        return this.idSalle ;
    }
   
    public int getIdRang(){
        return this.idRang;
    }
    
    public String getCategorie(){
        return this.categorie;
    }
   
    @Override
    public String toString() {
        return "Rang{" + "idrang=" + idRang +", idsalle=" + idSalle + ", categorie=" + categorie + '}';
    }
}


