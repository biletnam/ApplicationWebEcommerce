/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
      
package modele; 

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author berrazar
 */
public class Spectacle {

    private int idSpec;
    private String titre; 
    private List<Representation> representations;
    
    public Spectacle(int idspec, String titre) {
        this.titre = titre;
        this.idSpec = idspec;
        //this.representations = new ArrayList<Representation>();
    }

    public String getTitre() {
        return this.titre;
    }
   
    public int getIdSpec(){
        return this.idSpec;
    }
   
    @Override
    public String toString() {
        return "spectacle{" + "idspectacle=" + idSpec + ", titre=" + titre + '}';
    }
}
