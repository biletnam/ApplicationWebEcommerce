<%-- 
    Document   : panier
    Created on : 21 avr. 2015, 10
    Author     : kithr
--%>

<%@page import="controleur.ControleurReservation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    	<head> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" /> 	
		<!-- lien vers la feuille de style CSS, si les deux fichiers ne sont pas dans le meme repertoire il faut changer le href -->
		<link rel="stylesheet" type="text/css" href="style_index.css" />	
		<title> TheatrO - Bienvenue dans notre site de réservation </title> 			
	</head> 
    <body>
        <div id="fixed_header">
		<!-- En xhtml, les balises sont toutes écrites en minuscules et toutes les valeurs des attributs doivent être écrits entre "" -->
                <h1><a href="index.jsp">TheatrO</a></h1> 		
        <c:choose>
            <c:when test="${utilisateur != null}">
                <h2>Bienvenue ${prenom} ${nom}</h2>
                <p><a href="/controleurreservation?action=afficher">Votre panier</a></p>
                <p><a href="/ControleurUser?action=deconnexion">Déconnexion</a></p>
            </c:when>
            <c:otherwise>
                <ul>
                    <jsp:forward page="login.jsp"></jsp:forward>
                </ul>
            </c:otherwise>
        </c:choose>
        </div>

        <div id="main"> 
            <h3>Votre panier</h3>
            <p>Gérer votre panier</p>
            <c:forEach items="${reservations}" var="reservation">
                <tr>
                    <td>
                        ${reservation.titre} à ${reservation.dateheure} dans la 
                        salle ${reservation.idsalle}, rang ${reservation.idrang},
                         place ${reservation.idplace}, prix ${reservation.prix} : <a href="controleurreservation?action=supprimer"> supprimer</a>
                    </td>
                </tr>
            </c:forEach>
                    <% if (session.getAttribute("reservations") != null) { %>
                        <input type="button" value="Valider les réservations"
                               onclick="self.location.href='/controleurreservation?action=lienPayer'" style="background-color:#9C0303"
                               style="color:black; font-weight:bold padding 5px 10px;"onclick> 
                    <% } %>
            
        </div>
                
        <div id="fixed_bottom">
		<p>Contac t</p>			
                <ul>
                    <li><p>Adresse : 85 Rue Theatro, 99099 Theatro City </p></li>
                    <li><p>Téléphone : 06 11 22 33 44</p></li>
                    <li><p>Mail : contact@theatro.com </p></li>
		</ul>                
        </div>
    </body>
</html>
