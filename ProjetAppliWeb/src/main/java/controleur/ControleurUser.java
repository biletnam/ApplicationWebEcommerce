/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import dao.DAOException;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import modele.User;

/**
 *
 * @author kithr
 */
@WebServlet(name = "ControleurUser", urlPatterns = {"/ControleurUser"})
public class ControleurUser extends HttpServlet {
  
    @Resource(name = "jdbc/projet_site_web")
    private DataSource ds;
    public String message= "";
    
    public String getMessage(){
        return message;
    }
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
        UserDAO userDAO = new UserDAO(ds);
        
        try {
            if (action == null) {
                actionAfficher(request, response, userDAO);
            } else if (action.equals("ajouter")) {
                actionAjouter(request, response, userDAO);
            } else if (action.equals("supprimer")) {
                actionSupprimer(request, response, userDAO);
            } else if (action.equals("modifierPassword")) {
                actionModifierPassword(request, response, userDAO);
            } else if (action.equals("modifierEmail")) {
                actionModifierEmail(request, response, userDAO);                
            } else if (action.equals("getUser")){
                actionGetUser(request, response, userDAO);
            } else if (action.equals("getVerification")) {
                actionVerification(request, response, userDAO);
            } else if (action.equals("deconnexion")) {
                actionDeconnexion(request, response);
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
     * Ajout d'un utilisateur.
     */
    private void actionAjouter(HttpServletRequest request,
            HttpServletResponse response,
            UserDAO userDAO)
            throws IOException, ServletException, DAOException, SQLException {
        String login = request.getParameter("login");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String password = request.getParameter("password");
        String password2= request.getParameter("password2");
        String email = request.getParameter("email");
        
        //verification de la disponibilité du login
        User user = userDAO.getUser(login,response);
        if (user != null) {
            //request.setAttribute("message", "login");
            //message="login";
            
            getServletContext().getRequestDispatcher("/WEB-INF/register_login.jsp").forward(request, response);
            
        }
        //verification du mot de passe
        if ((!password.equals(password2)) || (password.length() <5)) {
           //request.setAttribute("message","password");
            getServletContext().getRequestDispatcher("/WEB-INF/register_password.jsp").forward(request, response);
        }
        //sinon:tout va bien
        userDAO.ajouterUtilisateur(login, nom, prenom, password,password2, email);
        HttpSession session = request.getSession(true);
        session.setAttribute("utilisateur",login);
        
        getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);

    }

    /**
     * Suppression d'un utilisateur.
     */
    private void actionSupprimer(HttpServletRequest request,
            HttpServletResponse response,
            UserDAO userDAO)
            throws IOException, ServletException, DAOException {
        String login = request.getParameter("login");
        userDAO.supprimerUser(login);
        getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
        

        }

    /**
     * Modification d'un mot de passe
     */
    private void actionModifierPassword(HttpServletRequest request,
            HttpServletResponse response,
            UserDAO userDAO)
            throws IOException, ServletException, DAOException {
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            userDAO.modifierUserPassword(login, password);
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);

    }
    
    /**
     * Modification d'un email
     */
    private void actionModifierEmail(HttpServletRequest request,
            HttpServletResponse response,
            UserDAO userDAO)
            throws IOException, ServletException, DAOException {
            String login = request.getParameter("login");
            String email = request.getParameter("email");
            userDAO.modifierUserEmail(login, email);
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);

    }
    
    private void actionAfficher(HttpServletRequest request, 
            HttpServletResponse response, 
            UserDAO userDAO) throws DAOException, ServletException, IOException, SQLException {
        request.setAttribute("users", userDAO.getListeUser());                
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        
    }

    private void actionGetUser(HttpServletRequest request, 
            HttpServletResponse response, 
            UserDAO userDAO) throws DAOException, ServletException, IOException {
        String login = request.getParameter("login");
        userDAO.getUser(login, response);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }    
    
    private void actionVerification(HttpServletRequest request, 
            HttpServletResponse response, 
            UserDAO userDAO) throws DAOException, ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userDAO.getUser(login,response);
        if (user == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/invalidlogin.html").forward(request, response);
        }
        if (user.getPassword().equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("utilisateur",user.getLogin());
            session.setAttribute("nom", user.getNom());
            session.setAttribute("prenom", user.getPrenom());
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/invalidlogin.html").forward(request, response);
        }
    }
    
    private void actionDeconnexion(HttpServletRequest request, HttpServletResponse response)
            throws DAOException, ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
