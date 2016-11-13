package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Spectacle;
import java.sql.*;
import modele.Representation;
        
        

/**
 *
 * @author berrazar
 */
public class RepresentationDAO extends AbstractDataBaseDAO {

    public RepresentationDAO(DataSource ds) {
        super(ds);
    }

     /**
     * Ajoute la representation dans la table
     * represenation.
     */
    public void ajouterRepresentation(int idspec,int idsalle,Timestamp dateheure) 
            throws DAOException, SQLException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "insert into representation values (" + idspec + "," 
                    + idsalle +" , " + dateheure + ")" ;
            st.executeUpdate(requeteSQL);
	       }
         catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
    
            closeConnection(conn);
        }
    }

    /**
     * Modifie la salle de la representation
     * dans la table representation
     */
     public void modifierSalle(int idspec,int idsalle,Timestamp dateheure,int idnouvellesalle) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL=" update * from representation where idspec =" + idspec +
                    " and idsalle =" + idsalle + " and dateheure= " + dateheure 
                    + " set idsalle=" + idnouvellesalle;
            rs = st.executeQuery(requeteSQL);
            
       } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    } 

    /**
     * Modifie la date ou l'heure du spectacle
     * dans la table representation
     */
     public void modifierDate(int idspec,int idsalle,Timestamp dateheure,Timestamp nouvelledateheure) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL=" update * from representation where idspec =" + idspec 
                    + " and idsalle =" + idsalle + " and dateheure= " + 
                    dateheure +" set dateheure=" + nouvelledateheure;
            rs = st.executeQuery(requeteSQL);
            
       } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    } 

    /**
     * Supprime la representation dans la table representation.
     */
    public void supprimerRepresentation(int idspec,int idsalle, Timestamp dateheure) throws DAOException {
        String requeteSQL = "delete * from representation where idspec =" + 
                idspec + " and idsalle =" + idsalle + " and dateheure= " + dateheure;
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
    
    
