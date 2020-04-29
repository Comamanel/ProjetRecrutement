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
<h1>Voici la liste des utilisateurs</h1>

    <table>
        <tr>
            <td>Id</td>
            <td>Email</td>
            <td>Pseudo </td>
        </tr>
        <s:iterator value="utilisateurs">
            <s:url action="affiche-profil-utilisateur" var="details" />
                <s:param name="id">id</s:param>
            </s:url>
            <tr>
                <td><s:property value="id" /> | </td>
                <td><s:property value="email" /> | </td>
                <td><s:property value="pseudo" /> | </td>
                <td><a href="${details}">Détails</a>
            </tr>
        </s:iterator>
    </table>
    <p><a href="<s:url action='index'  />">Revenir à la page d'accueil</a>.</p>

</body>
</html>