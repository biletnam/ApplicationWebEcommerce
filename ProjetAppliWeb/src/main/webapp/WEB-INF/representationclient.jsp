<%-- 
    Document   : representation
    Created on : Apr 22, 2015, 8:13:22 AM
    Author     : berrazar
--%>

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
                <p><a href="controleurreservation?action=afficher">Votre panier</a></p>
                <p><a href="ControleurUser?action=deconnexion">Déconnexion</a></p>
            </c:when>
            <c:otherwise>
                <ul>
                    <li><a href="login.jsp">S'identifier</a></li>
                    <li><a href="register.jsp">S'inscrire</a></li>
                    <li><a href="contact.html">Contact</a></li>
                </ul>
            </c:otherwise>
        </c:choose>
        </div>
            
        <div id="main"> 
            <p>Bienvenue sur le site de réservation de théâtre de ThéatrO.</p>
            <p>Vous trouvez sur ce site toutes sortes de spectacles. Réservez dès maintenant.</p>
          
            

        <table>
            <tr>
                <th>Representations</th>
            </tr>
            <c:forEach items="${representations}" var="representation">
                <tr> 

                   <td> Le Spectacle:</td><td>${titre}</td><td> à la salle:</td> <td>${representation.idSalle}</td> <td>se déroule le:</td> <td>${representation.dateHeure}</td>
                    <c:choose>
                        <c:when test="${utilisateur != null}">
                            <td><a href="controleurreservation?action=donneesrepresentation&idsalle=${representation.idSalle}&dateheure=${representation.dateHeure}">reserver</a></td>
                        </c:when>
                        <c:otherwise>
                            <td>Inscrivez vous pour réserver</td>
                        </c:otherwise>
                    </c:choose>
                </tr>

            </c:forEach>
        </table>
                    
        </div>
                
        <div id="fixed_bottom">
		<p>Contact</p>			
                <ul>
                    <li><p>Adresse : 85 Rue Theatro, 99099 Theatro City </p></li>
                    <li><p>Téléphone : 06 11 22 33 44</p> </li>
                    <li><p>Mail : contact@theatro.com </p></li>
		</ul>                
        </div>
    </body>
</html>



