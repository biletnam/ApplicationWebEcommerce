/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.SpectacleDAO;
import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import modele.Spectacle;
/**
 *
 * @author berrazar
 */

/**
 * Le contrôleur de l'application
 * gestion_spectacle.jsp est la page de la gestion des spectacles
 * spectacleclient.jsp est la page dédié au client
 */
@WebServlet(name = "controleurspectacle", urlPatterns = {"/controleurspectacle"})
public class ControleurSpectacle extends HttpServlet {

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
        SpectacleDAO spectacleDAO = new SpectacleDAO(ds);

        try {
            if (action == null) {
                actionAfficher(request, response, spectacleDAO);
            } else if (action.equals("ajouter")) {
                actionAjouter(request, response, spectacleDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, spectacleDAO);
            } else if (action.equals("modifier")) {
                actionModifier(request, response, spectacleDAO);
            } else if (action.equals("getSpectacle")){
                actionGetSpectacle(request, response, spectacleDAO);
            } else if (action.equals("lien")) {
                actionLien(request, response);
            } else if (action.equals("liengestionspectacle")) {
                actionLienGestionSpectacle(request, response);
            } else if (action.equals("memoriser")) {
                actionMemoriserIdspec(request, response, spectacleDAO);
	    } else if (action.equals("representations")){
                actionRepresentations(request, response, spectacleDAO);
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
     * Ajout d'un spectacle.
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            SpectacleDAO spectacleDAO)
            throws IOException, ServletException, DAOException, SQLException {
        String titre = request.getParameter("titre");
        String idspectacle=request.getParameter("idspec");
        spectacleDAO.ajouterSpectacle(idspectacle,titre);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Suppression d'un spectacle.
     */
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            SpectacleDAO spectacleDAO)
            throws IOException, ServletException, DAOException {
        String idspec = request.getParameter("idspec");
        spectacleDAO.supprimeSpectacle(Integer.parseInt(idspec));
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        

        }

    private void actionMemoriserIdspec(HttpServletRequest request,
            HttpServletResponse response, SpectacleDAO spectacleDAO) throws IOException, ServletException,
            DAOException {
        String idspec = request.getParameter("idspec");
        HttpSession session = request.getSession(false);
        session.setAttribute("idspec", idspec);
        getServletContext().getRequestDispatcher("/WEB-INF/modifier_spectacle.jsp").forward(request, response);
    }
    
    /**
     * Modification d'un spectacle
     */
    private void actionModifier(HttpServletRequest request,
            HttpServletResponse response,
            SpectacleDAO spectacleDAO)
            throws IOException, ServletException, DAOException {
            String titre = request.getParameter("titre");
            HttpSession session = request.getSession(false);
            int id = Integer.parseInt((String)session.getAttribute("idspec"));
            spectacleDAO.modifierSpectacle(id, titre);
            actionAfficher(request,response,spectacleDAO);
            session.removeAttribute("idspec");
            getServletContext().getRequestDispatcher("/WEB-INF/gestion_spectacle.jsp").forward(request, response);

    }


    
    
    private void actionAfficher(HttpServletRequest request, 
            HttpServletResponse response, 
            SpectacleDAO spectacleDAO) throws DAOException, ServletException, IOException {
        request.setAttribute("spectacles", spectacleDAO.getListeSpectacles()); 
        HttpSession session = request.getSession(false);
        String login = (String)session.getAttribute("utilisateur");
        if(!(login == null)){
                if (login.equals("admin")){
                    getServletContext().getRequestDispatcher("/WEB-INF/gestion_spectacle.jsp").forward(request, response);
                }
                else {
                    getServletContext().getRequestDispatcher("/WEB-INF/spectacleclient.jsp").forward(request, response);
                }
        }else {
            getServletContext().getRequestDispatcher("/WEB-INF/spectacleclient.jsp").forward(request, response);
        }
    }
    
    private void actionGetSpectacle(HttpServletRequest request, 
            HttpServletResponse response, 
            SpectacleDAO spectacleDAO) throws DAOException, ServletException, IOException {
        String idspec = request.getParameter("idspec");
        spectacleDAO.getSpectacle(Integer.parseInt(idspec));
        getServletContext().getRequestDispatcher("/WEB-INF/gestion_spectacle.jsp").forward(request, response);
    }
    

    private void actionLien(HttpServletRequest request, HttpServletResponse response) 
            throws DAOException, ServletException, IOException{
        getServletContext().getRequestDispatcher("/WEB-INF/gestion_spectacle.jsp").forward(request, response);
    }
     /**
    
        /**
     * page des representations d'un spectacle
     */


    private void actionRepresentations(HttpServletRequest request, 
            HttpServletResponse response, 
            SpectacleDAO spectacleDAO) throws DAOException, ServletException, IOException {
        String idspec = request.getParameter("idspec");
        String titre = request.getParameter("titre");
        HttpSession session = request.getSession(false);
        session.setAttribute("idspec", idspec);
        session.setAttribute("titre", titre);
        
        request.setAttribute("representations", spectacleDAO.getListeRepresentations(Integer.parseInt(idspec)));


        String login = (String)session.getAttribute("utilisateur");
        if(!(login == null)){
                if (login.equals("admin")){
                    getServletContext().getRequestDispatcher("/WEB-INF/gestion_representation.jsp").forward(request, response);
                }
                else {
                    getServletContext().getRequestDispatcher("/WEB-INF/representationclient.jsp").forward(request, response);
                }
        }else{
            getServletContext().getRequestDispatcher("/WEB-INF/representationclient.jsp").forward(request, response);
        }
        
    }

    private void actionLienGestionSpectacle(HttpServletRequest request, HttpServletResponse response) 
            throws DAOException, ServletException, IOException {
        getServletContext().getRequestDispatcher("/controleurspectacle").forward(request, response);
    }
}
