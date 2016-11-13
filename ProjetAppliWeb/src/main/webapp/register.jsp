<%-- 
    Document   : register
    Created on : 21 avr. 2015, 17:52:44
    Author     : kammounh
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
    <h1><a href="index.jsp">Théatr<font color='red'>O</font></h1></a><br>
    <p>Remplissez les champs ci-dessous: </p>
    
  <form action="ControleurUser" method="GET">
    <input name="action" value="ajouter" type="hidden"/>
    <input type="text" name="nom" placeholder="Nom">
    <input type="text" name="prenom" placeholder="Prenom">
    <input type="text" name="login" placeholder="Identifiant-Pseudo">
    <input type="text" name="email" placeholder="Votre adresse Mail">
    <input type="password" name="password" placeholder="Mot de passe">
    <input type="password" name="password2" placeholder="Confirmez votre mot de passe">
    <input type="submit" value="S'enregistrer"/>
  </form>
</div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>

</body>
</html>
