<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="fr">
<head>
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

            <tr>
                <td><s:property value="id" /> | </td>
                <td><s:property value="email" /> | </td>
                <td><s:property value="pseudo" /> | </td>
                <td>
                    <s:url action="liste-utilisateurs" var="details">
                        <s:param name="id">51</s:param>
                    </s:url>
                    <p><a href="${details}">Détails</a></p>
                </td>
            </tr>
        </s:iterator>
    </table>
    <p><a href="<s:url action='index'  />">Revenir à la page d'accueil</a>.</p>

</body>
</html>