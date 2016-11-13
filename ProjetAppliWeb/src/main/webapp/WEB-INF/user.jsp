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
	 
		<!-- En xhtml, les balises sont toutes écrites en minuscules et toutes les valeurs des attributs doivent être écrits entre "" -->
                
                    
                    <h1><a href="index.jsp" />TheatrO</h1> 
		
                
                <ul>
                    <li><a href="login.jsp">S'identifier</a></li>
                    <li><a href="register.jsp">S'inscrire</a></li>
                    <li><a href="contact.html">Contact</a></li>
                    
                </ul>
                    
                <!--<a href= "mon_compte.html"> <input type="submit" value="Mon compte" /> </a>
		<a href= "mon_panier.html"> <input type="submit" value="Mon panier" /> </a>
         -->
        
        </div>
            
            <div id="main">
                 
                 
                       <table>
            <tr>
                <th>Spectacles</th>

                
            </tr>
            <c:forEach items="${spectacles}" var="spectacle">
                <tr> 
                    <td>${spectacle.titre}</td>
                        <td><a href="controleurspectacle?action=representations&titre=${spectacle.titre}&idspec=${spectacle.idSpec}">representations</a></td>

                </tr>
            </c:forEach>
        </table>
                
                

                
               
                
                
                
                
               
                </div>
                
              <div id="fixed_bottom">

		<p>Contact</p>			
		
                <ul>
                    <li><p>Adresse : 85 Rue Theatro, 99099 Theatro City </p> 
				
                                </li>
                    <li><p>Téléphone : 06 11 22 33 44</p> 
				
                                </li>
                                <li><p>Mail : contact@theatro.com </p></li>
				
			
		</ul>

		
                
        </div>

        


    </body>
</html>
