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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import javax.sql.DataSource;
import modele.Place;
import modele.Spectacle;
import modele.Reservation;
import modele.ReservationAffichage;
import modele.Ticket;

/**
 *
 * @author berrazar
 */
public class ReservationDAO extends AbstractDataBaseDAO {
    
    
        public ReservationDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des reservations
     */
    public List<Reservation> AfficherListeReservation() throws DAOException {
        List<Reservation> result = new ArrayList<Reservation>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from reservation";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getString("login"), rs.getInt("idspec"),
                        rs.getTimestamp("dateheure"), rs.getInt("idsalle"), rs.getInt("idrang"),
                        rs.getInt("idplace"));
                System.err.println(reservation);
                result.add(reservation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     * Ajoute 
     */
    public void ajouterReservation(String login, int idspec, Timestamp dateheure,
            int idsalle,String categorie) throws DAOException {
        ResultSet rs = null;
        Connection conn = null;
        String requeteSQL = "";
        Place place;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL ="select distinct p.idplace,p.idrang,p.idsalle from place p,rang r where (r.idplace=p.idrang and p.idsalle=r.idsalle and r.categorie=  ' " + categorie + "' and (p.idplace,p.idrang,p.idsalle) not in (select (re.idplace,re.idrang,re.idsalle)from reservation re where re.idspec = " +idspec+ "and re.idsalle="+idsalle+" and re.dateheure= "+dateheure+"re.idspec="+idspec+")"   ;
	    rs = st.executeQuery(requeteSQL);
	    rs.next();
	    place = new Place(rs.getInt("idplace"),rs.getInt("idrang"), rs.getInt("idsalle"));
            int idrang=place.getIdRang();
            int idplace=place.getIdPlace();
	    requeteSQL = "insert into  values ('" + login + "','" + idspec
                     + "','" + dateheure + "','"+ idsalle + "','"
                    + idrang + "','" + idplace + "')";
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
        closeConnection(conn);
    }
   }
	
    /**
     * .
     */
    public void supprimerReservation(String login, int idspec, Timestamp dateheure,
            int idsalle, int idrang, int idplace) throws DAOException {
        Connection conn = null;
        ResultSet rs = null;
        String requeteSQL = "";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "delete * from reservation where login = '" + login +
                    "'  and idpsec = " + idspec +" and dateheure = " + dateheure 
                    + " and idsalle = " + idsalle +" and idrang = " + idrang + 
                    " and idplace = "+ idplace;
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        } 
    }

    public Reservation getReservation(String login, int idspec, Timestamp date,
            int idsalle, int idrang, int idplace)
            throws DAOException {
        Connection conn = null;
        ResultSet rs = null;
        Reservation reservation = null;
        String requeteSQL = "";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from reservation where login = '" + login +
                    "'  and idspec = " + idspec +" and dateheure = " + date 
                    + " and idsalle = " + idsalle +" and idrang = " + idrang + 
                    " and idplace = "+ idplace;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            reservation = new Reservation(rs.getString("login"), rs.getInt("idspec"),
            rs.getTimestamp("date"), rs.getInt("idsalle"), rs.getInt("idrang"), rs.getInt("idplace"));
            return reservation;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }    
    
    public List<ReservationAffichage> AfficherListeReservationAffichage() 
            throws DAOException {
        List<ReservationAffichage> result = new ArrayList<ReservationAffichage>();
        ResultSet rs = null;
        String requeteSQL = "";
        String requeteSQL2 = "";
        String requeteSQL3 = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            Statement st2 = conn.createStatement();
            Statement st3 = conn.createStatement();
            requeteSQL = "select * from reservation";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                requeteSQL2 = "select s.titre from reservation r, spectacle s where r.login = '" + rs.getString("login") +
                    "'  and r.idspec = " + rs.getInt("idspec") +" and r.dateheure = " + rs.getTimestamp("date") 
                    + " and r.idsalle = " + rs.getInt("idsalle") +" and r.idrang = " + rs.getInt("idrang") + 
                    " and r.idplace = "+ rs.getInt("idplace") + "and s.idspec = r.idspec";
                requeteSQL3 = "select r.prix from rang r where r.idsalle =" + rs.getInt("idsalle") +
                        " and r.idrang = " + rs.getInt("idrang");
                ReservationAffichage reservation = new ReservationAffichage
                    (st.executeQuery(requeteSQL2).getString("s.titre"), rs.getInt("idspec"),
                    rs.getTimestamp("dateheure"), rs.getInt("idsalle"), 
                    rs.getInt("idrang"), rs.getInt("idplace"), st.executeQuery(requeteSQL3).getInt("r.prix"));
                System.err.println(reservation);
                result.add(reservation);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }    
    
}
