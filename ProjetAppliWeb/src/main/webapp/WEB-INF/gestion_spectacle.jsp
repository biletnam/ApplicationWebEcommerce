<%@ page contentType="text/html; charset=utf-8"%>
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
                <h1><a href="index.jsp">TheatrO</a></h1> 		
        <c:choose>
            <c:when test="${utilisateur != null}">
                <h2>Bienvenue ${prenom} ${nom}</h2>
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
        
        </div>
            
            <div id="main">   
        <table>
            <tr>
                <th><font color="white">Spectacles</font></th>
                
                
                
            </tr>
            <c:forEach items="${spectacles}" var="spectacle">
                <tr> 
                    <td><font color="white">${spectacle.titre}</font></td>
                    <td><font color="white"><a href="controleurspectacle?action=memoriser&idspec=${spectacle.idSpec}">
                            modifier&nbsp;&nbsp;&nbsp;</a></font></td>
                    <td><font color="white"><a href="controleurspectacle?action=supprimer&idspec=${spectacle.idSpec}">
                            &nbsp;&nbsp;&nbsp;&nbsp;supprimer</a></font></td>

                    
                </tr>  
            </c:forEach>
        </table>
                <p><a href="controleurspectacle?action=lienAjouter">Ajouter un spectacle</a></p>
                </div>
              <div id="fixed_bottom">

		<p>Contact</p>			
		
                <ul>
                    <li><p>Adresse : 85 Rue TheatrO, 99099 TheatrO City NoWhere </p> 
				
                                </li>
                    <li><p>Téléphone : 06 11 22 33 44</p></li>
                    <li><p>Mail : contact@theatro.com </p></li>
		</ul>
        </div>
    </body>
</html>
