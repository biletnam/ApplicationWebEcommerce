/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;


import dao.DAOException;
import dao.ReservationDAO;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.Place;
import modele.Reservation;
import modele.ReservationAffichage;

/**
 *
 * @author berrazar
 */


/**
 * Le contrôleur de l'application
 * reservation.jsp est la page de la gestion des spectacles
 */
@WebServlet(name = "Controleureservation", urlPatterns = {"/controleurreservation"})
public class ControleurReservation extends HttpServlet {

    @Resource(name = "jdbc/projet_site_web")
    private DataSource ds;



    /**
     * La méthode principale d'aiguillage.
     */
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        ReservationDAO reservationDAO = new ReservationDAO(ds);

        try {

            if (action.equals("afficher")) {
                actionAfficher(request, response, reservationDAO);
            } else if (action.equals("reserver")) {
                actionAjouter(request, response, reservationDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, reservationDAO);
            } else if (action.equals("getReservation")){
                actionGetReservation(request, response, reservationDAO);
            } else if (action.equals("getTitre")) {
                actionGetTitre(request, response, reservationDAO);
            } else if (action.equals("donneesrepresentation")){
                actionDonneesRepresentation(request, response, reservationDAO);
            } else if (action.equals("lienPayer")) {
                actionLienPayer(request, response);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleurSpectacle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Ajout d'une reservation
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            ReservationDAO reservationDAO)
            throws IOException, ServletException, DAOException, SQLException {
        int i;
        HttpSession session = request.getSession(false);
        String idspec = (String)session.getAttribute("idspec");
        String dateheure = (String)session.getAttribute("dateheure");
        String login = (String)session.getAttribute("utilisateur");
        String idsalle = (String)session.getAttribute("idsalle");
        String categorie = request.getParameter("categorie");
        String quantite = request.getParameter("quantite");
        Timestamp date = createDate(dateheure);
        
        for(i=0;i<Integer.parseInt(quantite);i++){
            reservationDAO.ajouterReservation(login, Integer.parseInt(idspec),date,Integer.parseInt(idsalle),categorie);
        }
         getServletContext().getRequestDispatcher("/WEB-INF/spectacleclient.jsp").forward(request, response);

    }

    
    
        private void actionDonneesRepresentation(HttpServletRequest request,
            HttpServletResponse response,
            ReservationDAO reservationDAO)
            throws IOException, ServletException, DAOException {
        String idsalle = request.getParameter("idsalle");
        String dateheure = request.getParameter("dateheure");
        HttpSession session = request.getSession(false);
        session.setAttribute("idsalle", idsalle);
        session.setAttribute("dateheure",dateheure);
        getServletContext().getRequestDispatcher("/WEB-INF/reservation.jsp").forward(request, response);
        }

    /**
     * Suppression d'une réservation.
     */
    
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            ReservationDAO reservationDAO)
            throws IOException, ServletException, DAOException {
        String idspec = request.getParameter("idspec");
        String dateheure = request.getParameter("dateheure");
        String login = request.getParameter("login");
        String idsalle = request.getParameter("idsalle");
        String idrang = request.getParameter("idrang");
        String idplace = request.getParameter("idplace");
        Timestamp date = createDate(dateheure);
        reservationDAO.supprimerReservation(login, Integer.parseInt(idspec), date,
            Integer.parseInt(idsalle), Integer.parseInt(idrang), Integer.parseInt(idplace));
        HttpSession session = request.getSession(false);
        session.removeAttribute("titreReserv");
        getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        }

    private void actionAfficher(HttpServletRequest request, 
            HttpServletResponse response, 
            ReservationDAO reservationDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("reservations", reservationDAO.AfficherListeReservationAffichage());
        getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
        
    }

    private void actionGetReservation(HttpServletRequest request, 
            HttpServletResponse response, 
            ReservationDAO reservationDAO) throws DAOException, ServletException, IOException {
        String idspec = request.getParameter("idspec");
        String dateheure = request.getParameter("dateheure");
        String login = request.getParameter("login");
        String idsalle = request.getParameter("idsalle");
        String idrang = request.getParameter("idrang");
        String idplace = request.getParameter("idplace");
        Timestamp date = createDate(dateheure);
        reservationDAO.getReservation(login, Integer.parseInt(idspec), date,
            Integer.parseInt(idsalle), Integer.parseInt(idrang), Integer.parseInt(idplace));
        getServletContext().getRequestDispatcher("/WEB-INF/panier.jsp").forward(request, response);
    }
    
    public void actionGetTitre(HttpServletRequest request, 
            HttpServletResponse response, 
            ReservationDAO reservationDAO) throws DAOException, ServletException, IOException {

    }
    
   /*
    public Timestamp createDate(String dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
                System.out.println(dateString);
                Timestamp dateheure =new Timestamp(simpleDateFormat.parse(dateString).getTime());
                return dateheure;
        } catch (ParseException e) {
            	e.printStackTrace();
        }
        return null;
    } */
    
    
    
    
    public final static java.sql.Timestamp string2Time(String dateString)  
        throws java.text.ParseException {  
        DateFormat dateFormat;  
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");  
        dateFormat.setLenient(false);  
        java.util.Date timeDate = dateFormat.parse(dateString);//util  
        java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp,timeDate.getTime()long  
        return dateTime;  
    }
    
    public Timestamp createDate(String dateString) {
        try {  
             
            Timestamp date2 = string2Time(dateString);  
            System.out.println("Timestamp:"+date2.toString()); 
            return date2;
        }catch(Exception e) {  
            e.printStackTrace();  
        }  
        return null;
    }   

    public void actionLienPayer(HttpServletRequest request, 
            HttpServletResponse response) throws DAOException, ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/paiment.jsp").forward(request, response);
    }

}
