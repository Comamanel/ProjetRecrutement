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

        <h2>Profil de l'utilisateur</h2>
    </div>
    <img id="top-bg" src="img/bg_board.jpg" alt="background tableau">

    <div id="container-projets">

            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Prénom</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.prenom" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Nom</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.nom" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Id</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.id" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Adresse e-mail</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.email" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Pseudonyme</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.pseudo" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Date de Naissance</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.datedeNaissanceFormattee" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Infos supplémentaires de l'utilisateur</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.infosSupp" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Numéro de téléphone</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.numTel" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Pays</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.pays" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Lien du compte Git</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.lienGit" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Photo de profil</h3>
                 </div>
                <div class="specs-projet"><img src="<s:property value="utilisateur.photoProfil" />" width="200px"/></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Lien du CV</h3>
                </div>
                <div class="specs-projet"><s:property value="utilisateur.cvDoc" /></div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Maitrises</h3>
                </div>
                <div class="specs-projet">
                    <s:if test="utilisateur.maitrises.size() > 0">
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
                        </s:if>
                        <s:else>
                            Pas de maitrises
                         </s:else>
                </div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Projets </h3>
                </div>
                <div class="specs-projet">
                    <s:if test="utilisateur.projetsParticipes.size() > 0">
                        <table>
                            <tr>
                                <td>Nom du projet</td>
                                <td>Propriétaire ?</td>
                                <td>Toujours actif sur le projet ? </td>
                            </tr>
                            <s:iterator value="utilisateur.projetsParticipes">
                                <tr>
                                    <td><s:property value="projet.nom" /></td>
                                    <td>
                                        <s:if test="proprio">Oui</s:if>
                                        <s:else>Non</s:else>
                                    </td>
                                    <td>
                                        <s:if test="actif">Oui</s:if>
                                        <s:else>Non</s:else>
                                    </td>
                                    <td class="explorer-projet">
                                        <a href="<s:url value='projet-page'><s:param name='id' value='%{id}' /></s:url>">Explorer</a>
                                    </td>
                                </tr>
                            </s:iterator>
                        </table>
                    </s:if>
                    <s:else>
                        L'utilisateur n'a pas encore participé au moindre projet
                    </s:else>
                </div>
            </div>
            <div class="boite-projet">
                <div class="titre-projet">
                    <h3>Projets créés</h3>
                </div>
                <div class="specs-projet">
                    <s:if test="utilisateur.projetsCrees.size() > 0">
                        <s:iterator value="utilisateur.projetsCrees">
                            <div class="infos-projet">
                                <s:property value="nom" />
                                <div class="explorer-projet">
                                    <a href="<s:url value='projet-page'><s:param name='id' value='%{id}' /></s:url>">Explorer</a>
                                </div>
                             </div>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        Aucun projet Créé
                    </s:else>
                </div>
            </div>
        </table>
    </div>
    <p><a href="/projet-recrutement-front/">Revenir à la page d'accueil</a>.</p>
</body>
</html>