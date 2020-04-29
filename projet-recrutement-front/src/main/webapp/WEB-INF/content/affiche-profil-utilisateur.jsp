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
</head>
<body>
<h1>Voici la page de profil de l'utilisateur <s:property value="%{utilisateur.id} />}</h1>
    <p><a href="<s:url action='index'  />">Revenir à la page d'accueil</a>.</p>

</body>
</html>