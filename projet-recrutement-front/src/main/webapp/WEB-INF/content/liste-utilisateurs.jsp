<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="fr">
<head>
    <meta charset="utf-8">
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
                    <s:url action="affiche-profil-utilisateur" var="details">
                        <s:param name="id">51</s:param>
                    </s:url>
                    <p>
                        <a href="<s:url value='affiche-profil-utilisateur'><s:param name='id' value='%{id}' /></s:url>">
                            Détails
                        </a>
                    </p>
                </td>
            </tr>
        </s:iterator>
    </table>
    <p><a href="<s:url action='index'  />">Revenir à la page d'accueil</a>.</p>

</body>
</html>