package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import modele.Spectacle;
import modele.Representation;
        
        

/**
 *
 * @author berrazar
 */
public class SpectacleDAO extends AbstractDataBaseDAO {

    public SpectacleDAO(DataSource ds) {
        super(ds);
    }
    /**
     * Renvoie la liste des spectacles  de la table spectacles sous la forme
     * d'un ResultSet
     */
    public List<Spectacle> getListeSpectacles() throws DAOException {
        List<Spectacle> result = new ArrayList<Spectacle>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from spectacles";
            rs = st.executeQuery(requeteSQL);

            while (rs.next()) {
                Spectacle spectacle = new Spectacle (rs.getInt("idspec"),rs.getString("titre"));
                System.err.println(spectacle);
                result.add(spectacle);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

        /**
     * Ajoute le spectacle de titre spécifié dans la table
     * spectacles.
     */
    public void ajouterSpectacle(String idspectacle,String titre) throws DAOException, SQLException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "insert into spectacles values (" + idspectacle+ ",'" + titre + "')";
            st.executeUpdate(requeteSQL);
	 
	       }
         catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
    
            closeConnection(conn);
        }
    }

        /**
     * Récupère le spectacle d'identifiant id dans la table spectacles.
     */
    public Spectacle getSpectacle(int idspec) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	Spectacle spectacle = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from spectacles where idspec = " + idspec;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            spectacle = new Spectacle(rs.getInt("id"),rs.getString("titre"));
            return spectacle;
               
           
            }
        catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

        public String getTitreSpectacle(int idspec) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	Spectacle spectacle = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from spectacles where idspec = " + idspec;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            spectacle = new Spectacle(rs.getInt("id"),rs.getString("titre"));
            String titre = spectacle.getTitre();
            return titre;
            }
        catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * Modifie le spectacle d'identifiant id avec le nouvel titre
     * dans la table spectacles
     */
     public void modifierSpectacle(int idspec,String titre) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();

            requeteSQL=" update spectacles set titre='" + titre + "' where idspec= "+idspec;
            rs = st.executeQuery(requeteSQL);
       } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    } 

    /**
     * Supprime le spectacle d'identifiant id dans la table spectacles.
     */
    public void supprimeSpectacle(int idspec) throws DAOException {

        String requeteSQL = "delete from spectacles where idspec = " + idspec ;

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
    
    /**
     * Renvoie la liste des representations d'un spectacle donné
     * sous forme d'un ResultSet
     */
    public List<Representation> getListeRepresentations(int idspec) throws DAOException {
        List<Representation> result = new ArrayList<Representation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from representation where idspec = " + idspec;
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Representation representation = new Representation (rs.getInt("idspec"),rs.getInt("idsalle"),rs.getTimestamp("dateheure"));
         
                        
                System.err.println(representation);
                result.add(representation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

 
  
    
 }
    
    
