Feature: Puis-je créer une candidature

    Scenario: Une candidature à un projet sérieux par un utilisateur ayant toutes les maitrises nécessaires et les cochant toutes
      Given la technologie "Java", créée par "Sun Microsystem"
      And la technologie "CSharp", créée par "Microsoft"
      And la maîtrise contenant la techno "CSharp", de niveau "AVA"
      And la maîtrise contenant la techno "Java", de niveau "DEB"
      And un utilisateur avec un email "test@candidature.ok", "onverra" comme mdp, un pseudo "test1" la maîtrise en "DEB" pour la technologie "Java" et la maîtrise en "AVA" pour la technologie "CSharp"
      And un projet avec un nom "ProjetTestCandidature", la description "Ceci est un projet de test afin de tester si la méthode de vérification d'une candidature est correcte", une date de début fixée au "2020-04-06", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT", une maîtrise en "Java" demandée au niveau "DEB", et une en "CSharp" demandée au niveau "AVA"
      And une candidature contenant le projet, l'utilisateur, avec, en guise de technologies cochées, celles avec le nom "Java" et le nom "CSharp", le statut à "ATT", le nombre d'heures par semaines fixées à 10
      When je vérifie si la candidature est bonne
      Then la vérification de la véracité de la candidature est à true

  Scenario: Une candidature à un projet sérieux par un utilisateur qui ne maîtrise pas toutes les technologies qu'il coche
    Given la technologie "Java", créée par "Sun Microsystem"
    And la technologie "CSharp", créée par "Microsoft"
    And la maîtrise contenant la techno "CSharp", de niveau "AVA"
    And la maîtrise contenant la techno "Java", de niveau "DEB"
    And un utilisateur avec un email "test@candidature.ok", "onverra" comme mdp, un pseudo "test1" et la maîtrise en "DEB" pour la technologie "Java"
    And un projet avec un nom "ProjetTestCandidature", la description "Ceci est un projet de test afin de tester si la méthode de vérification d'une candidature est correcte", une date de début fixée au "2020-04-06", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT", une maîtrise en "Java" demandée au niveau "DEB", et une en "CSharp" demandée au niveau "AVA"
    And une candidature contenant le projet, l'utilisateur, avec, en guise de technologies cochées, celles avec le nom "Java" et le nom "CSharp", le statut à "ATT", le nombre d'heures par semaines fixées à 10
    When je vérifie si la candidature est bonne
    Then la vérification de la véracité de la candidature est à false

  Scenario: Une candidature à un projet sérieux par un utilisateur qui ne maîtrise pas toutes les technologies, où il ne sélectionne que celles qu'il connaît
    Given la technologie "Java", créée par "Sun Microsystem"
    And la technologie "CSharp", créée par "Microsoft"
    And la maîtrise contenant la techno "CSharp", de niveau "AVA"
    And la maîtrise contenant la techno "Java", de niveau "DEB"
    And un utilisateur avec un email "test@candidature.ok", "onverra" comme mdp, un pseudo "test1" et la maîtrise en "DEB" pour la technologie "Java"
    And un projet avec un nom "ProjetTestCandidature", la description "Ceci est un projet de test afin de tester si la méthode de vérification d'une candidature est correcte", une date de début fixée au "2020-04-06", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT", une maîtrise en "Java" demandée au niveau "DEB", et une en "CSharp" demandée au niveau "AVA"
    And une candidature contenant le projet, l'utilisateur, avec, en guise de technologies cochées, celle avec le nom "Java", le statut à "ATT", le nombre d'heures par semaines fixées à 10
    When je vérifie si la candidature est bonne
    Then la vérification de la véracité de la candidature est à true

  Scenario: Une candidature à un projet sérieux par un utilisateur qui ne maîtrise aucune technologie et n'en sélectionne pas
    Given la technologie "Java", créée par "Sun Microsystem"
    And la technologie "CSharp", créée par "Microsoft"
    And la maîtrise contenant la techno "CSharp", de niveau "AVA"
    And la maîtrise contenant la techno "Java", de niveau "DEB"
    And un utilisateur avec un email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec un nom "ProjetTestCandidature", la description "Ceci est un projet de test afin de tester si la méthode de vérification d'une candidature est correcte", une date de début fixée au "2020-04-06", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT", une maîtrise en "Java" demandée au niveau "DEB", et une en "CSharp" demandée au niveau "AVA"
    And une candidature contenant le projet, l'utilisateur, avec aucune technologie cochée, le statut à "ATT", le nombre d'heures par semaines fixées à 10
    When je vérifie si la candidature est bonne
    Then la vérification de la véracité de la candidature est à true

  Scenario: Une candidature à un projet d'apprentissage par un utilisateur qui ne maîtrise pas toutes les technologies qu'il coche
    Given la technologie "Java", créée par "Sun Microsystem"
    And la technologie "CSharp", créée par "Microsoft"
    And la maîtrise contenant la techno "CSharp", de niveau "AVA"
    And la maîtrise contenant la techno "Java", de niveau "DEB"
    And un utilisateur avec un email "test@candidature.ok", "onverra" comme mdp, un pseudo "test1" et la maîtrise en "DEB" pour la technologie "Java"
    And un projet avec un nom "ProjetTestCandidature", la description "Ceci est un projet de test afin de tester si la méthode de vérification d'une candidature est correcte", une date de début fixée au "2020-04-06", un type de projet fixé à "APP" avec 10 participants maximum et un statut fixé à "ACT", une maîtrise en "Java" demandée au niveau "DEB", et une en "CSharp" demandée au niveau "AVA"
    And une candidature contenant le projet, l'utilisateur, avec, en guise de technologies cochées, celles avec le nom "Java" et le nom "CSharp", le statut à "ATT", le nombre d'heures par semaines fixées à 10
    When je vérifie si la candidature est bonne
    Then la vérification de la véracité de la candidature est à true

  Scenario: Une candidature à un projet d'apprentissage par un utilisateur qui maîtrise toutes les technologies qu'il coche
    Given la technologie "Java", créée par "Sun Microsystem"
    And la technologie "CSharp", créée par "Microsoft"
    And la maîtrise contenant la techno "CSharp", de niveau "AVA"
    And la maîtrise contenant la techno "Java", de niveau "DEB"
    And un utilisateur avec un email "test@candidature.ok", "onverra" comme mdp, un pseudo "test1" et la maîtrise en "DEB" pour la technologie "Java"
    And un projet avec un nom "ProjetTestCandidature", la description "Ceci est un projet de test afin de tester si la méthode de vérification d'une candidature est correcte", une date de début fixée au "2020-04-06", un type de projet fixé à "APP" avec 10 participants maximum et un statut fixé à "ACT", une maîtrise en "Java" demandée au niveau "DEB", et une en "CSharp" demandée au niveau "AVA"
    And une candidature contenant le projet, l'utilisateur, avec, en guise de technologies cochées, celle avec le nom "Java", le statut à "ATT", le nombre d'heures par semaines fixées à 10
    When je vérifie si la candidature est bonne
    Then la vérification de la véracité de la candidature est à true