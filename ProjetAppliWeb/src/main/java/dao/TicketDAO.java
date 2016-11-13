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
import java.sql.*;
import java.util.List;
import javax.sql.DataSource;
import modele.Spectacle;
import modele.Ticket;


/**
 *
 * @author kithr
 */
public class TicketDAO extends AbstractDataBaseDAO {
    
    public TicketDAO(DataSource ds) {
        super(ds);
    }

    /**
     * Renvoie la liste des tickets disponibles
     */
    public List<Ticket> getListeTicket() throws DAOException {
        List<Ticket> result = new ArrayList<Ticket>();
        ResultSet rs = null;
        String requeteSQL = "";
        Connection conn = null;
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from ticket";
            rs = st.executeQuery(requeteSQL);
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getInt("idticket"), rs.getInt("idspec"),
                        rs.getTimestamp("dateheure"), rs.getString("login"), rs.getInt("idsalle"),
                        rs.getInt("idrang"), rs.getInt("idplace"));
                System.err.println(ticket);
                result.add(ticket);
            }
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
        return result;
    }

    /**
     * Ajoute le ticket dans les réservés et le retire des tickets possibles
     */
    public void ajouterTicket(int idticket, int idspec, Timestamp dateheure, String login, 
            int idsalle, int idrang, int idplace) throws DAOException {
        Connection conn = null;
        ResultSet rs = null;
        String requeteSQL = "";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "insert into ticket values (" + idticket + "," + idspec
                     + "," + dateheure + ",'" + login + "'," + idsalle + ","
                    + idrang + "," + idplace + ")";
            rs = st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD " + e.getMessage(), e);
        } finally {
        closeConnection(conn);
    }
}

    /**
     * Récupère le ticket reservé de la base de donnée.
     */
    public Ticket getTicket(int idticket) throws DAOException {
        Connection conn = null;
        ResultSet rs = null;
        Ticket ticket = null;
        String requeteSQL = "";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "select * from ticket where idticket =" + idticket;
            rs = st.executeQuery(requeteSQL);
            rs.next();
            ticket = new Ticket(rs.getInt("idticket"), rs.getInt("idspec"), 
                    rs.getTimestamp("dateheure"), rs.getString("login"),
                    rs.getInt("idsalle"), rs.getInt("idrang"), rs.getInt("idplace"));
            return ticket;
        } catch (SQLException e) {
            throw new DAOException("Erreur BD" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * Supprime le ticket d'identifiant id dans la table ticket.
     */
    public void supprimerTicket(int idticket) throws DAOException {
        Connection conn = null;
        ResultSet rs = null;
        String requeteSQL = "";
        try {
            conn = getConnection();
            Statement st = conn.createStatement();
            requeteSQL = "delete * from ticket where idticket = " + idticket;
            st.executeQuery(requeteSQL);
        } catch (SQLException e) {
            throw new DAOException("Erreur BD" + e.getMessage(), e);
        } finally {
            closeConnection(conn);
        } 
    }
}
