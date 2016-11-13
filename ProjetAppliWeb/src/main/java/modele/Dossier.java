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
public class Dossier {
    private int idDossier;
    private int nbPlacesAchetees;
    private String login;
    
    public Dossier(int idDossier, int nbPlacesAchetees, String login) {
        this.idDossier = idDossier;
        this.nbPlacesAchetees = nbPlacesAchetees;
        this.login = login;
    }
    
    public int getIdDossier() {
        return this.idDossier;
    }
    
    public int getNbPlacesAchetees() {
        return this.nbPlacesAchetees;
    }
    
    @Override
    public String toString() {
        return "Dossier{" + "idDossier=" + idDossier + ", nbPlacesAchetees=" + nbPlacesAchetees
                 + ", login=" + login + '}';
    }
}
