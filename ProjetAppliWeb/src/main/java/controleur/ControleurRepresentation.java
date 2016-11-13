/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.RepresentationDAO;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.Representation;

/**
 *
 * @author berrazar
 */

/**
 * Le contrôleur de l'application
 * representation.jsp est la page de la gestion des spectacles
 */
@WebServlet(name = "Controleurrepresentation", urlPatterns = {"/controleurrepresentation"})
public class ControleurRepresentation extends HttpServlet {

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
        RepresentationDAO representationDAO = new RepresentationDAO(ds);

        try {
                
            if (action.equals("ajouter")) {
                actionAjouter(request, response, representationDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, representationDAO);
            } else if (action.equals("modifier la salle")) {
                actionModifierSalle(request, response, representationDAO);
            } else if (action.equals("modifier la date et l'heure")){
                actionModifierDate(request, response, representationDAO);
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
     * Ajout d'une representation.
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            RepresentationDAO representationDAO)
            throws IOException, ServletException, DAOException, SQLException {
        String idspec = request.getParameter("idspec");
        String idsalle = request.getParameter("idsalle");
	String dateheure = request.getParameter("dateheure");
        Timestamp date = createDate(dateheure);
        representationDAO.ajouterRepresentation(Integer.parseInt(idspec),Integer.parseInt(idsalle),date);
        getServletContext().getRequestDispatcher("/WEB-INF/gestion_spectacle.jsp").forward(request, response);

    }

    /**
     * Suppression d'une representation.
     */
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            RepresentationDAO representationDAO)
            throws IOException, ServletException, DAOException {
        String idspec = request.getParameter("idspec");
        String idsalle = request.getParameter("idsalle");
	String dateheure = request.getParameter("dateheure");
        Timestamp date = createDate(dateheure);
        representationDAO.supprimerRepresentation(Integer.parseInt(idspec),Integer.parseInt(idsalle),date);
        getServletContext().getRequestDispatcher("/WEB-INF/representation.jsp").forward(request, response);
        

        }

    /**
     * Modification de la salle d'une representation
     */
    private void actionModifierSalle(HttpServletRequest request,
            HttpServletResponse response,
            RepresentationDAO representationDAO)
            throws IOException, ServletException, DAOException {
            String idspec = request.getParameter("idspec");
            String idsalle = request.getParameter("idsalle");
	    String dateheure = request.getParameter("dateheure");
	    String idnouvellesalle= request.getParameter("idnouvellesalle");
            Timestamp date = createDate(dateheure);
            representationDAO.modifierSalle(Integer.parseInt(idspec),Integer.parseInt(idsalle),date,Integer.parseInt(idnouvellesalle));
            getServletContext().getRequestDispatcher("/WEB-INF/representation.jsp").forward(request, response);

    }

    /**
     * Modification de la date ou l'heure d'une representation
     */
    private void actionModifierDate(HttpServletRequest request,
            HttpServletResponse response,
            RepresentationDAO representationDAO)
            throws IOException, ServletException, DAOException {
            String idspec = request.getParameter("idspec");
            String idsalle = request.getParameter("idsalle");
	    String dateheure = request.getParameter("dateheure");
	    String nouvelledateheure= request.getParameter("nouvelledateheure");
            Timestamp date = createDate(dateheure);
            Timestamp nouvelledate = createDate(nouvelledateheure);
            representationDAO.modifierDate(Integer.parseInt(idspec),Integer.parseInt(idsalle),date,nouvelledate);
            getServletContext().getRequestDispatcher("/WEB-INF/representation.jsp").forward(request, response);

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
}
