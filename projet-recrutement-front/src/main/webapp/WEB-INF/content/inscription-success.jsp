<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="fr">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Projet Recrutement - Liste des utilisateurs</title>
    <link href="styles/main.css" rel="stylesheet">
</head>
<body>
    <div id="site-titre">
        <h1>Overskill</h1>
        <h3>Plateforme de partage de compétences et de projets</h3>

        <h2>Enregistrement réussi</h2>
    </div>
    <img id="top-bg" src="img/bg_board.jpg" alt="background tableau">

    <div id="container-projets">
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Id</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateurFinal.id" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Adresse e-mail</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateurFinal.email" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Pseudonyme</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateurFinal.pseudo" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Date de Naissance</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateurFinal.datedeNaissanceFormattee" /></div>
            </div>
        </table>
    </div>
    <p><a href="/projet-recrutement-front/">Revenir à la page d'accueil</a>.</p>
</body>
</html>