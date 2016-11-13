/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import modele.Dossier;

/**
 *
 * @author kithr
 */
public class DossierDAO extends AbstractDataBaseDAO {
    
    public DossierDAO(DataSource ds) {
        super(ds);
    }
    
    /**
     * Renvoie la liste des dossiers
     */
    public List<Dossier> getListeDossier() throws DAOException, SQLException {
        List<Dossier> result = new ArrayList<Dossier>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from dossier";
            rs = st.executeQuery(requeteSQL);

            while (rs.next()) {
                Dossier dossier = new Dossier (rs.getInt("idDossier"), rs.getInt("nbPlacesAchetees"),
                rs.getString("login"));
                System.err.println(dossier);
                result.add(dossier);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }
    
    /**
     * Ajoute le dossier dans la table des dossiers
     */
    public void ajouterDossier(int idDossier, int nbPlacesAchetees, String login)
            throws DAOException, SQLException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "insert into dossier values (" + idDossier + "," + nbPlacesAchetees + ",'"
                    + login + "')";
            st.executeUpdate(requeteSQL);
	       }
         catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
    
            closeConnection(conn);
        }
    }
    
    /**
     * Récupère le dossier de numéro idDossier dans la table dossier.
     */
    public Dossier getDossier(int idDossier) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	Dossier dossier = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from dossier where idDossier = " + idDossier;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            dossier = new Dossier(rs.getInt("idDossier"), rs.getInt("nbPlacesAchetees"),
                    rs.getString("login"));
            return dossier;
            }
        catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }
    
    /**
     * Supprime le dossier d'identifiant idDossier dans la table dossier.
     */
    public void supprimerDossier(int idDossier) throws DAOException {
        String requeteSQL = "delete * from dossier where idDossier =" + idDossier;
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            st.executeUpdate(requeteSQL);
	
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
	  
    }    
    
}
