<%-- 
    Document   : ajouter_spectacle
    Created on : 22 avr. 2015, 15:32:48
    Author     : tilsaghm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

  <meta charset="UTF-8">

  <title>Théâtro - Ajouter un spectacle </title>

  <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>

    <link rel="stylesheet" href="style.css">

</head>

<body>

  <div class="login-card">
    <h1>Théatr<font color='red'>O</font></h1><br>
    <p>Remplissez les champs ci-dessous pour rajouter un spectacle: </p>
    
  <form action="controleurspectacle" method="GET">
    <input name="action" value="ajouter" type="hidden"/>
    <input type="text" name="idspec" placeholder="Identifiant du spectacle">
    <input type="text" name="titre" placeholder="Titre du spectacle">
    
    <input type="submit" value="Ajouter"/>
  </form>

  
</div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>

</body>
</html>
