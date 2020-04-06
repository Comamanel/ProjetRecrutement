Feature: Puis-je créer une candidature

  Scenario: Une candidature à un projet sérieux par un utilisateur ayant toutes les maitrises nécessaires et les cochant toutes
    Given la technologie "Java", créée par "Sun Microsystem"
    And la technologie "CSharp", créée par "Microsoft"
    And la maîtrise contenant la techno "CSharp", de niveau "Avancé"
    And la maîtrise contenant la techno "Java", de niveau "Débutant"
    And un utilisateur avec un email "test@candidature.ok", "onverra" comme mdp, un pseudo "test1" la maîtrise en "Débutant" pour la technologie "Java" et la maîtrise en "Avancé" pour la technologie "CSharp"
    And un projet avec un nom "ProjetTestCandidature", la description "Ceci est un projet de test afin de tester si la méthode de vérification d'une candidature est correcte", une date de début fixée au "2020-04-06", un type de projet fixé à "Sérieux" avec 10 participants maximum et un statut fixé à "Actif", une maîtrise en "Java" demandée en "Débutant", et une en "CSharp" demandée en "Avancé"
    And une candidature contenant le projet, l'utilisateur, avec toutes les maîtrises de l'utilisateur cochées, le statut à "En attente", le nombre d'heures par semaines fixées à 10
    When je vérifie si la candidature est bonne
    Then la vérification de la véracité de la candidature est à true