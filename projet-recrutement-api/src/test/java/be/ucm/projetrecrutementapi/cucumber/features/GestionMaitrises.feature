Feature: How does this action affect my skills list ? 

Scenario: I add a new skill to my list
	Given an user with the nickname "Hector"
	And said user has informed he is "DEB" in the technology "Angular"
	When he updates his list to inform that he is "INT" in the technology "Java"
	Then his skills list contains 2 items

Scenario: I add two new skills to my list
	Given an user with the nickname "Hector"
	And said user has informed he is "DEB" in the technology "Angular"
	When he updates his list to inform that he is "INT" in the technology "Java"
	When he updates his list to inform that he is "DEB" in the technology "PhP"
	Then his skills list contains 3 items

Scenario: I update a skill in my list
	Given an user with the nickname "Hector"
	And said user has informed he is "DEB" in the technology "Angular"
	When he updates his list to inform that he is "INT" in the technology "Angular"
	Then his skills list contains 1 items

Scenario: I update two skills in my list
	Given an user with the nickname "Hector"
	And said user has informed he is "DEB" in the technology "Angular"
	And said user has informed he is "DEB" in the technology "Java"
	When he updates his list to inform that he is "INT" in the technology "Angular"
	When he updates his list to inform that he is "INT" in the technology "Java"
	Then his skills list contains 2 items

Scenario: I update a skill in my list (Level Check)
	Given an user with the nickname "Hector"
	And said user has informed he is "DEB" in the technology "Angular"
	When he updates his list to inform that he is "INT" in the technology "Angular"
	Then his level in the technology "Angular" is now "INT"

Scenario: I remove a skill from my list
    Given an user with the nickname "Hector"
	And said user has informed he is "DEB" in the technology "Angular"
	And said user has informed he is "DEB" in the technology "Java"
	When he updates his list by removing the info about his skills in "Angular"
	Then his skills list contains 1 items

Scenario: I remove everything from my list
    Given an user with the nickname "Hector"
	And said user has informed he is "DEB" in the technology "Angular"
	And said user has informed he is "DEB" in the technology "Java"
    When he updates his list by removing the info about his skills in "Java"
	When he updates his list by removing the info about his skills in "Angular"
	Then his skills list contains 0 items

Scenario: Adding required skills to the project
	Given a project with the name "Sushi Shop"
	And said project requires people being "INT" in the technology "Java"
	When the project is updated to demand people who are "INT" in "Angular"
	Then the list of demanded skills contains 2 items

Scenario: Adding more flexibility in required skills to the project
	Given a project with the name "Sushi Shop"
	And said project requires people being "INT" in the technology "Java"
	When the project is updated to demand people who are "DEB" in "Java"
	Then the list of demanded skills contains 2 items

Scenario: Adding more required skills to the project
	Given a project with the name "Sushi Shop"
	And said project requires people being "INT" in the technology "Java"
	When the project is updated to demand people who are "INT" in "Angular"
	When the project is updated to demand people who are "INT" in "C++"
	Then the list of demanded skills contains 3 items