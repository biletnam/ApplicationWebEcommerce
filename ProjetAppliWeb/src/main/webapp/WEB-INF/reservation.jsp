<%-- 
    Document   : reservation
    Created on : 24 avr. 2015, 18:48:00
    Author     : berrazar
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>

  <meta charset="UTF-8">

  <title>Théâtro - S'enregistrer </title>

  <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>

    <link rel="stylesheet" href="style.css">

</head>

<body>

  <div class="login-card">
    <h1>Théatr<font color='red'>O</font></h1><br>
    <p>Remplissez les champs ci-dessous: </p>
          
    <form method="GET" action="controleurreservation">
        <input name="action" value="reserver" type="hidden"/>
	<input type="text" name="quantite" placeholder="Quantité">
	Catégorie: <input name="categorie" value="orchestre" type=radio> orchestre
        <input name="categorie" value="poulailler" type=radio CHECKED> poulailler 
        <input name="categorie" value="balcon" type=radio> balcon <br/>
        <input type="submit" value="reserver"/>
    </form>

  
</div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>

</body>
</html>
