Feature: Changement de propriétaire d'un projet

  Scenario: Requête sans body
    Given une requête en "JSON" sans body
    When je contacte l'adresse "http://localhost:8080/api/projet/changer-proprietaire"
    Then je reçois une réponse avec un code 400

  Scenario: Requête avec un body contenant un string vide
    Given une requête en "JSON" avec body contenant un string vide
    When je contacte l'adresse "http://localhost:8080/api/projet/changer-proprietaire"
    Then je reçois une réponse avec un code 400

  Scenario: Requête avec un body contenant l'id d'un utilisateur
    Given un formulaire de changement de propriétaire envoyant comme id de l'ancien propriétaire 1, comme id du nouveau propriétaire -1, comme id du projet -1
    And une requête en "JSON" avec body contenant le formulaire de changement de propriétaire
    When je contacte l'adresse "http://localhost:8080/api/projet/changer-proprietaire"
    Then je reçois une réponse avec un code 200
    And un body avec false

  Scenario: Requête avec un body contenant l'id de l'ancien et du nouveau propriétaire, mais pas de projet
    Given un formulaire de changement de propriétaire envoyant comme id de l'ancien propriétaire 1, comme id du nouveau propriétaire 2, comme id du projet -1
    And une requête en "JSON" avec body contenant le formulaire de changement de propriétaire
    When je contacte l'adresse "http://localhost:8080/api/projet/changer-proprietaire"
    Then je reçois une réponse avec un code 200
    And un body avec false

  Scenario: Requête avec un body contenant l'id du nouveau propriétaire et du projet
    Given un formulaire de changement de propriétaire envoyant comme id de l'ancien propriétaire -1, comme id du nouveau propriétaire 2, comme id du projet 1
    And une requête en "JSON" avec body contenant le formulaire de changement de propriétaire
    When je contacte l'adresse "http://localhost:8080/api/projet/changer-proprietaire"
    Then je reçois une réponse avec un code 200
    And un body avec false

  Scenario: Requête avec un body contenant l'id de l'ancien propriétaire, du nouveau et du projet.
    Given un formulaire de changement de propriétaire envoyant comme id de l'ancien propriétaire 1, comme id du nouveau propriétaire 2, comme id du projet 1
    And une requête en "JSON" avec body contenant le formulaire de changement de propriétaire
    When je contacte l'adresse "http://localhost:8080/api/projet/changer-proprietaire"
    Then je reçois une réponse avec un code 200
    And un body avec true