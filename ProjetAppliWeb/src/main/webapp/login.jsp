<%-- 
    Document   : login
    Created on : 20 avr. 2015, 15:56:43
    Author     : kammounh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>

  <meta charset="UTF-8">

  <title>Théâtro - Identification</title>

  <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>

    <link rel="stylesheet" href="style.css">

</head>

<body>

  <div class="login-card">
    <h1><a href="index.jsp">Théatr<font color='red'>O</font></h1></a><br>
    <form action="ControleurUser" method="GET"> 
    <input name="action" value="getVerification" type="hidden"/>
    <input type="text" name="login" placeholder="Identifiant">
    <input type="password" name="password" placeholder="Mot de passe">
    <input type="submit" value="Se connecter"/>
    </form>
  
  <div class="login-help">
    <p><a href="#"> S'enregistrer </a></p>
    <p><a href="#">Mot de passe oublié ?</a></p>
  </div>
</div>

<!-- <div id="error"><img src="https://dl.dropboxusercontent.com/u/23299152/Delete-icon.png" /> Your caps-lock is on.</div> -->

  <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>

</body>
</html>
