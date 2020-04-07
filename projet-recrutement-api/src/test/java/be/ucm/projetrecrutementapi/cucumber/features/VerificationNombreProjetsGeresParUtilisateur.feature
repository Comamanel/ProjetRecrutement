Feature: Vérification du nombre de projets actifs qu'un utilisateur gère

  Scenario: Un utilisateur sans le moindre projet
      Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
      When on teste si on peut le mettre propriétaire d'un autre projet
      Then la réponse est true

  Scenario: Un utilisateur qui gère un seul projet, actif
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est true

  Scenario: Un utilisateur qui gère deux projets, actifs
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 2, comme nom "testProjet2", la description "Ceci est un deuxième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est true

  Scenario: Un utilisateur qui gère trois projets, actifs
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 2, comme nom "testProjet2", la description "Ceci est un deuxième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 3, comme nom "testProjet3", la description "Ceci est un troisième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est false

  Scenario: Un utilisateur qui gère trois projets dont 2 actifs
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 2, comme nom "testProjet2", la description "Ceci est un deuxième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 3, comme nom "testProjet3", la description "Ceci est un troisième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets inactifs", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ARC"
    And une participation projet active à true et proprio à true
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est true

  Scenario: Un utilisateur qui gère cinq projets dont 3 actifs
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 2, comme nom "testProjet2", la description "Ceci est un deuxième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 3, comme nom "testProjet3", la description "Ceci est un troisième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets inactifs", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ARC"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 4, comme nom "testProjet4", la description "Ceci est un quatrième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets inactifs", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ARC"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 5, comme nom "testProjet5", la description "Ceci est un cinquième projet, actif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets inactifs", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est false

  Scenario: Un utilisateur qui gère cinq projets dont 2 actifs
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 2, comme nom "testProjet2", la description "Ceci est un deuxième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 3, comme nom "testProjet3", la description "Ceci est un troisième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets inactifs", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ARC"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 4, comme nom "testProjet4", la description "Ceci est un quatrième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets inactifs", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ARC"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 5, comme nom "testProjet5", la description "Ceci est un cinquième projet, actif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets inactifs", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ARC"
    And une participation projet active à true et proprio à true
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est true

  Scenario: Un utilisateur qui gère deux projets et participe à trois autres
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 2, comme nom "testProjet2", la description "Ceci est un deuxième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à true
    And un projet avec comme id 3, comme nom "testProjet3", la description "Ceci est un troisième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets dont l'user n'est pas le proprio", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à false
    And un projet avec comme id 4, comme nom "testProjet4", la description "Ceci est un quatrième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets dont l'user n'est pas le proprio", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à false
    And un projet avec comme id 5, comme nom "testProjet5", la description "Ceci est un cinquième projet, actif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets dont l'user n'est pas le proprio", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à false
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est true

  Scenario: Un utilisateur qui participe à trois projets qu'il ne gère pas
    Given un utilisateur avec comme email "test@candidature.ok", "onverra" comme mdp et un pseudo "test1"
    And un projet avec comme id 1, comme nom "testProjet", la description "Ceci est un projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à false
    And un projet avec comme id 2, comme nom "testProjet2", la description "Ceci est un deuxième projet de test afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à false
    And un projet avec comme id 3, comme nom "testProjet3", la description "Ceci est un troisième projet, inactif celui-ci, afin de tester si la méthode de vérification du nombre de projets gérés par un utilisateur est correcte avec les projets dont l'user n'est pas le proprio", une date de début fixée au "2020-04-07", un type de projet fixé à "SER" avec 10 participants maximum et un statut fixé à "ACT"
    And une participation projet active à true et proprio à false
    When on teste si on peut le mettre propriétaire d'un autre projet
    Then la réponse est true