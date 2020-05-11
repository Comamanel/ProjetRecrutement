<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html lang="fr">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Projet Recrutement - Inscription</title>
    <link href="styles/main.css" rel="stylesheet">
    <sx:head />
</head>
<body>
    <div id="site-titre">
        <h1>Overskill</h1>
        <h3>Plateforme de partage de compétences et de projets</h3>

        <h2>Profil de l'utilisateur</h2>
    </div>
    <img id="top-bg" src="img/bg_board.jpg" alt="background tableau">

    <div id="container-projets">
        <div class="boite-projets">
            <s:form action="inscription">
                <s:textfield name="utilisateur.email" label="Adresse email " />
                <s:textfield name="utilisateur.pseudo" label="Pseudonyme " />
                <s:textfield name="utilisateur.motDePasse" label="Mot de Passe " />
                <sx:datetimepicker name="utilisateur.dateDeNaissance" label="Date de Naissance "
                displayFormat="dd/MM/yyyy" value="%{'2000-01-01'}"/>
                <s:submit value="S'enregistrer" />
            </s:form>
        </div>
    </div>
    <p><a href="/projet-recrutement-front/">Revenir à la page d'accueil</a>.</p>
</body>
</html>