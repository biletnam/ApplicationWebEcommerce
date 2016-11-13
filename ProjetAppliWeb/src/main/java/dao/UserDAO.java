/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modele.User;

/**
 *
 * @author kithr
 */
public class UserDAO extends AbstractDataBaseDAO {
    
    public UserDAO(DataSource ds) {
        super(ds);
    }
    
    /**
     * Renvoie la liste des utilisateurs
     */
    public List<User> getListeUser() throws DAOException, SQLException {
        List<User> result = new ArrayList<User>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from users";
            rs = st.executeQuery(requeteSQL);

            while (rs.next()) {
                User user = new User (rs.getString("login"), rs.getString("nom"),
                rs.getString("prenom"), rs.getString("password"), rs.getString("email"));
                System.err.println(user);
                result.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     * Ajoute l'utilisateur dans la table des utilisateurs
     */
    public void ajouterUtilisateur(String login, String nom, String prenom,
            String password,String password2, String email) throws DAOException, SQLException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "insert into users values ('" + login + "','" + nom + "','"
                    + prenom + "','" + password + "','"+ email + "')";
            st.executeUpdate(requeteSQL);
	       }
         catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
    
            closeConnection(conn);
        }
    }

    /**
     * Récupère l'utilisateur de login login dans la table users.
     */
    public User getUser(String login, HttpServletResponse response) throws DAOException, IOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
	User user = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from users where login = '" + login + "'";
            rs = st.executeQuery(requeteSQL);
            if (rs.isBeforeFirst()) {
                rs.next();
                user = new User(rs.getString("login"), rs.getString("nom"),
                rs.getString("prenom"), rs.getString("password"),
                rs.getString("email"));
            } return user;
        }
        catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

      /**
     * Modifie le mot de passe de l'utilisateur d'identifiant login
     */
     public void modifierUserPassword(String login, String password) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL=" update * from users where login= '" + login + 
                    "' set password='" + password + "'";
            rs = st.executeQuery(requeteSQL);
            
       } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }   
    

    /**
     * Modifie l'email de l'utilisateur d'identifiant login
     */
     public void modifierUserEmail(String login, String email) throws DAOException {
	ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL=" update * from users where login= '" + login + 
                    "' set email='" + email + "'";
            rs = st.executeQuery(requeteSQL);
            
       } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    } 

    /**
     * Supprime l'utilisateur d'identifiant login dans la table users.
     */
    public void supprimerUser(String login) throws DAOException {
        String requeteSQL = "delete * from users where login ='" + login + "'";
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
