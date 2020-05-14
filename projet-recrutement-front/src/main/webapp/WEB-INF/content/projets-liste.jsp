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


            <div id="container-projets">
                <s:iterator value="projets">
                    <div class="boite-projet">
                        <div class="titre-projet">
                            <h3><s:property value="nom"/></h3>
                        </div>
                        <table class="infos-projet">
                            <tr>
                                 <td>
                                     <span><s:property value="typeProjet"/></span>
                                     <span><s:property value="nbParticipants"/>/<s:property value="maxParticipants"/></span>
                                  </td>
                            </tr>
                        </table>
                        <div class="specs-projet">
                            <p><s:property value="description"/></p>
                        </div>
                        <div class="explorer-projet">
                            <a href="<s:url value='projet-page'><s:param name='id' value='%{id}' /></s:url>">
                                Explorer
                            </a>
                        </div>
                    </div>
                </s:iterator>
            </div>

    </body>
</html>