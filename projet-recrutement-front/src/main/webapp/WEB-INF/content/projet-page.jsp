<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="fr">
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projet Recrutement - Projets</title>
        <link href="styles/main.css" rel="stylesheet">
    </head>
    <body>

        <div id="site-titre">
              <h1>Overskill</h1>
              <h3>Plateforme de partage de compétences et de projets</h3>
        </div>
        <img id="top-bg" src="img/bg_board.jpg" alt="background tableau">

           <div id="container-standard">
                <div class="titre-projet projet-single">
                    <h2><s:property value="projet.nom" /></h2>
                    <div class="titre-infos">
                        Projet démarré le <s:property value="projet.dateDebut" />
                        <s:if test="projet.dateFin != null">
                            Clotûre prévue pour le <s:property value="projet.dateFin" />
                        </s:if>
                    </div>
                </div>
                <div class="projet-description">
                    <h3>Description</h3>
                    <s:property value="projet.description" />
                </div>
                <h3>Équipe du projet</h3>
                <div class="projet-infos-tech">
                    <table class="maitrises-projet">
                            <th colspan="2">Compétences requises</th>
                        <s:iterator value="projet.maitrises">
                            <tr>
                                <td>
                                    <s:property value="technologie.nom" />
                                </td>
                                <td>
                                    <s:if test="niveauMaitrise=='DEB'">
                                        Débutant
                                    </s:if>
                                    <s:elseif test="niveauMaitrise=='INT'">
                                        Intermédiaire
                                    </s:elseif>
                                    <s:elseif test="niveauMaitrise=='AVA'">
                                        Avancé
                                    </s:elseif>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                    <table class="équipe-projet">
                        <th colspan="2">Participants</th>
                        <s:iterator value="projet.utilActifs">
                            <tr>
                                <td>
                                    <a href="<s:url value='affiche-profil-utilisateur'><s:param name='id' value='%{id}' /></s:url>">
                                        <s:property value="pseudo" />
                                    </a>
                                </td>
                                <td>
                                    <s:if test="id==projet.adminId">
                                        Administrateur
                                    </s:if>
                                    <s:else>
                                        Contributeur
                                    </s:else>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
    </body>