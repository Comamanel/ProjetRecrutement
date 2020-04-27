Feature: what's my matching score ?

Scenario: I have the skill demanded at the right level for the project
	Given an user with the username "Hector"
	And said user is "INT" with the technology "Java"
	When said user consults a project that demands "INT" users in "Java"
	Then the skill matching score is above 99%

Scenario: I don't have the skill demanded for this project
    Given an user with the username "Hector"
    And said user is "INT" with the technology "Java"
    When said user consults a project that demands "INT" users in "C#"
    Then the skill matching score is below 1%

Scenario: I have the skill demanded at the wrong level for the project
    Given an user with the username "Hector"
    And said user is "INT" with the technology "Java"
    When said user consults a project that demands "AVA" users in "Java"
    Then the skill matching score is below 1%

Scenario: I am above the right level for the project
    Given an user with the username "Hector"
    And said user is "AVA" with the technology "Java"
    When said user consults a project that demands "AVA" users in "Java"
    Then the skill matching score is above 99%

Scenario: I have more skills than those demanded by the project
    Given an user with the username "Hector"
    And said user is "AVA" with the technology "Java"
    And "INT" with the technology "C#"
    When said user consults a project that demands "AVA" users in "Java"
    Then the skill matching score is below 99%
    And above 49%

Scenario: I dont' have all the skills demanded by the project
    Given an user with the username "Hector"
    And said user is "AVA" with the technology "Java"
    When said user consults a project that demands "AVA" users in "Java"
    And "INT" users in "C#"
    Then the skill matching score is above 99%

Scenario: My profile completely matches what's demanded by the admin
	Given an user with the username "Hector"
	And said user is "AVA" with the technology "Java"
	And "INT" with the technology "Angular"
	And "DEB" with the technology "C#"
	And a project administrator who wants people that are "AVA" in "Java"
	And "INT" in "Angular"
	When the user is consulting this project
	Then the profile matching score is above 99%

Scenario: My profile completely matches what's demanded by the admin and I am overqualified
	Given an user with the username "Hector"
	And said user is "AVA" with the technology "Java"
	And "AVA" with the technology "Angular"
	And "DEB" with the technology "C#"
	And a project administrator who wants people that are "AVA" in "Java"
	And "INT" in "Angular"
	When the user is consulting this project
	Then the profile matching score is above 99%

Scenario: My profile completely matches what's demanded by the admin but I am underqualified
	Given an user with the username "Hector"
	And said user is "AVA" with the technology "Java"
	And "INT" with the technology "Angular"
	And "DEB" with the technology "C#"
	And a project administrator who wants people that are "AVA" in "Java"
	And "AVA" in "Angular"
	When the user is consulting this project
	Then the profile matching score is below 1%


