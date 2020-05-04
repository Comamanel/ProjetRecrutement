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
<h1>Voici la page de profil de l'utilisateur</h1>
    <table>
        <tr>
            <td>Prénom</td>
            <td><s:property value="utilisateur.prenom" /></td>
        </tr>
        <tr>
            <td>Nom</td>
            <td><s:property value="utilisateur.nom" /></td>
        </tr>
        <tr>
            <td>Id</td>
            <td><s:property value="utilisateur.id" /></td>
        </tr>
        <tr>
            <td>Adresse e-mail</td>
            <td><s:property value="utilisateur.email" /></td>
        </tr>
        <tr>
            <td>Pseudonyme</td>
            <td><s:property value="utilisateur.pseudo" /></td>
        </tr>
        <tr>
            <td>Date de Naissance</td>
            <td><s:property value="utilisateur.datedeNaissanceFormattee" /></td>
        </tr>
        <tr>
            <td>Infos supplémentaires de l'utilisateur</td>
            <td><s:property value="utilisateur.infosSupp" /></td>
        </tr>
        <tr>
            <td>Numéro de téléphone</td>
            <td><s:property value="utilisateur.numTel" /></td>
        </tr>
        <tr>
            <td>Pays</td>
            <td><s:property value="utilisateur.pays" /></td>
        </tr>
        <tr>
            <td>Lien du compte Git</td>
            <td><s:property value="utilisateur.lienGit" /></td>
        </tr>
        <tr>
            <td>Photo de profil</td>
            <td><img src="<s:property value="utilisateur.photoProfil" />" width="200px"/></td>
        </tr>
        <tr>
            <td>Lien du CV</td>
            <td><s:property value="utilisateur.cvDoc" /></td>
        </tr>
        <tr>
            <td>Maitrises</td>
            <td>
                <table>
                    <tr>
                        <td>Nom de la technologie</td>
                        <td>Créateur de la technologie</td>
                        <td>Niveau de maîtrise</td>
                    </tr>
                    <s:iterator value="utilisateur.maitrises">
                        <tr>
                            <td><s:property value="technologie.nom" /></td>
                            <td><s:property value="technologie.createur" /></td>
                            <td><s:property value="niveauMaitrise" /></td>
                        </tr>
                    </s:iterator>
                </table>
            </td>
        </tr>
    </table>



    <p><a href="/projet-recrutement-front">Revenir à la page d'accueil</a>.</p>
</body>
</html>