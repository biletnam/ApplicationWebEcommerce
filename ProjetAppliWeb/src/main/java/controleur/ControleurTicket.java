/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import modele.ReservationAffichage;

/**
 *
 * @author kithr
 */
@WebServlet(name = "ControleurTicket", urlPatterns = {"/ControleurTicket"})
public class ControleurTicket extends HttpServlet {

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
        TicketDAO ticketDAO = new TicketDAO(ds);

        try {
            if (action == null) {
                actionAfficher(request, response, ticketDAO);
            } else if (action.equals("ajouter")) {
                actionAjouter(request, response, ticketDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, ticketDAO);
            } else if (action.equals("getIdTicket")){
                actionGetTicket(request, response, ticketDAO);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleurTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ajout d'un ticket dans un dossier.
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            TicketDAO ticketDAO)
            throws IOException, ServletException, DAOException, SQLException {
        HttpSession session = request.getSession(false);
        List<ReservationAffichage> liste = (List<ReservationAffichage>) session.getAttribute("reservations");
        String dateheure = request.getParameter("dateheure");

        Timestamp date = createDate(dateheure);
        String login= (String) session.getAttribute("utilisateur");
        //parcourir les reservations
        for(int i=0; i<liste.size(); i++) {
            int idspec = liste.get(i).getIdspec();
            int idsalle=  liste.get(i).getIdSalle();
            int idrang= liste.get(i).getIdRang();
            int idplace=  liste.get(i).getIdPlace();
            ajouterUnTicket(idspec,idrang,idplace,idsalle,date,login,ticketDAO);
            
        
        
        }
        
        

        getServletContext().getRequestDispatcher("/paiement_valide.jsp").forward(request, response);

    }

    /**
     * Suppression d'un ticket.
     */
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            TicketDAO ticketDAO)
            throws IOException, ServletException, DAOException {
        String idticket = request.getParameter("idticket");
        ticketDAO.supprimerTicket(Integer.parseInt(idticket));
        getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
        

        }

    private void actionAfficher(HttpServletRequest request, 
            HttpServletResponse response, 
            TicketDAO ticketDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("idticket", ticketDAO.getListeTicket());                
        getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);  
    }

    private void actionGetTicket(HttpServletRequest request, 
            HttpServletResponse response, 
            TicketDAO ticketDAO) throws DAOException, ServletException, IOException {
        String idticket = request.getParameter("idticket");
        ticketDAO.getTicket(Integer.parseInt(idticket));
        getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
    }
    
   
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
    
    public void ajouterUnTicket(int idspec,int idrang,int idplace,int idsalle,Timestamp date,String login,TicketDAO ticketDAO) throws DAOException{
        
        
        int idticket = idspec*10^13 + (date.getYear()-2000)*10^11 +
                date.getMonth()*10^9 + date.getDay()*10^7 + date.getHours()*10^5 +
                date.getMinutes()*10^3 + idsalle*10^2 +
                idrang*10 + idplace;
        ticketDAO.ajouterTicket(idticket, idspec,
               date, login, idsalle, idrang,
               idplace);
    }
    } 


