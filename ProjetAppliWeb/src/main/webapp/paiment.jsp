<%-- 
    Document   : paiment
    Created on : 24 avr. 2015, 18:20:57
    Author     : kammounh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                
            </c:otherwise>
        </c:choose>
        </div>
          
                
        <div id="main">
            <p Entrer les coordonnées de la carte bancaire ></p>
        <form action="ControleurDossier" method="GET">
             <input name="action" value="ajouter" type="hidden"/>
             
            <input type="text" name="nom" placeholder="Nom">
             <input type="text" name="prenom" placeholder="Prenom">
            <input type="number" min="1000000000000000" max="9999999999999999"  name="numero" placeholder="Numéro de carte bancaire">
            <input type="number" name="code" min="100" max="999" placeholder="Cryptoramme (en dos de la carte)">
             <SELECT name="mois" size="1">
	    <OPTION>01
	    <OPTION>02
	    <OPTION>03
	    <OPTION>04
            <OPTION>05
            <OPTION>06
            <OPTION>07
            <OPTION>08
            <OPTION>09
            <OPTION>10
            <OPTION>11
            <OPTION>12
	  </SELECT>
            
            <SELECT name="annee" size="1">
	    <OPTION>2015
	    <OPTION>2016
	    <OPTION>2017
	    <OPTION>2018
            </SELECT>
            
            <input type="submit" value="payer"/>
            
            
         </form>
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
