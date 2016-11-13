/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;


import dao.DAOException;
import dao.DossierDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import modele.ReservationAffichage;
/**
 *
 * @author kithr
 */
@WebServlet(name = "ControleurDossier", urlPatterns = {"/ControleurDossier"})
public class ControleurDossier extends HttpServlet {
    
    @Resource(name = "jdbc/projet_site_web")
    private DataSource ds;
    
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        DossierDAO dossierDAO = new DossierDAO(ds);

        try {
            if (action == null) {
                actionAfficher(request, response, dossierDAO);
            } else if (action.equals("ajouter")) {
                actionAjouter(request, response, dossierDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, dossierDAO);         
            } else if (action.equals("getDossier")){
                actionGetDossier(request, response, dossierDAO);
            } else {
                getServletContext().getRequestDispatcher("/WEB-INF/controleurErreur.jsp").forward(request, response);
            }
        } catch (DAOException e) {
            request.setAttribute("erreurMessage", e.getMessage());
            getServletContext().getRequestDispatcher("/WEB-INF/bdErreur.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ControleurUser.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    /**
     * Ajout d'un dossier.
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            DossierDAO dossierDAO)
            throws IOException, ServletException, DAOException, SQLException {
        HttpSession session = request.getSession(false);
        String login = (String)session.getAttribute("utilisateur");
        List<ReservationAffichage> liste = (List<ReservationAffichage>) session.getAttribute("reservations");
        int nbPlacesAchetees= liste.size();
        String dateheure = request.getParameter("dateheure");
        Timestamp date = createDate(dateheure);
        int idDossier = (date.getYear()-2000)*10^11 +
                date.getMonth()*10^9 + date.getDay()*10^7 + date.getHours()*10^5 +
                date.getMinutes()*10^3 ;
        int h= (int) (Math.random()*100);
        idDossier= idDossier+h;
        dossierDAO.ajouterDossier(idDossier, nbPlacesAchetees,login);
        getServletContext().getRequestDispatcher("/WEB-INF/ticket.jsp").forward(request, response);

    }

    /**
     * Suppression d'un dossier.
     */
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            DossierDAO dossierDAO)
            throws IOException, ServletException, DAOException {
        String idDossier = request.getParameter("idDossier");
        dossierDAO.supprimerDossier(Integer.parseInt(idDossier));
        getServletContext().getRequestDispatcher("/WEB-INF/dossier.jsp").forward(request, response);
        

        } 
    
    private void actionAfficher(HttpServletRequest request, 
            HttpServletResponse response, 
            DossierDAO dossierDAO) throws DAOException, ServletException, IOException, SQLException {
        request.setAttribute("dossier", dossierDAO.getListeDossier());                
        getServletContext().getRequestDispatcher("/WEB-INF/dossier.jsp").forward(request, response);
        
    }

    private void actionGetDossier(HttpServletRequest request, 
            HttpServletResponse response, 
            DossierDAO dossierDAO) throws DAOException, ServletException, IOException {
        String idDossier = request.getParameter("idDossier");
        dossierDAO.getDossier(Integer.parseInt(idDossier));
        getServletContext().getRequestDispatcher("/WEB-INF/dossier.jsp").forward(request, response);
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
