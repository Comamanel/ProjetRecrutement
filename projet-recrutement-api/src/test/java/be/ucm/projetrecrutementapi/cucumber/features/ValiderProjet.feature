Feature: Can I create this project ?

Scenario: the project is properly set up (Creator involved in 0 projects)
	Given a new project is initiated
	And said project is given the name "Sushi Shop"
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2020-12-07"
	And said project ends on "2021-03-24"
	Then this project can be created

Scenario: the project is properly set up (Creator involved in 3 projects)
	Given a new project is initiated
	And the creator is currently working on 3 projects
	And said project is given the name "Sushi Shop"
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2020-12-07"
	And said project ends on "2021-03-24"
	Then this project can be created

Scenario: the project is properly set up (Creator created 2 projects)
	Given a new project is initiated
	And the creator is currently administrating 2 projects
	And said project is given the name "Sushi Shop"
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2020-12-07"
	And said project ends on "2021-03-24"
	Then this project can be created

Scenario: the user already is administrating 3 projects
	Given a new project is initiated
	And the creator is currently working on 4 projects
	And the creator is currently administrating 3 projects
	And said project is given the name "Sushi Shop"
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2020-12-07"
	And said project ends on "2021-03-24"
	Then this project cannot be created

Scenario: the user already is involved in 5 projects
	Given a new project is initiated
	And the creator is currently working on 5 projects
	And the creator is currently administrating 2 projects
	And said project is given the name "Sushi Shop"
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2020-12-07"
	And said project ends on "2021-03-24"
	Then this project cannot be created

Scenario: the project's name is null
    Given a new project is initiated
    And said project is described as "Une appli pour commander des bons sushis"
    And said project begins on "2020-03-07"
    And said project ends on "2020-06-24"
    Then this project cannot be created

Scenario: the project's name is empty
	Given a new project is initiated
	And said project is given the name "   "
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2020-03-07"
    And said project ends on "2020-06-24"
	Then this project cannot be created

Scenario: the project ends before it begins
	Given a new project is initiated
	And said project is given the name "Sushi Shop"
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2020-06-24"
	And said project ends on "2020-03-07"
	Then this project cannot be created

Scenario: the project begins before it is created
	Given a new project is initiated
	And said project is given the name "Sushi Shop"
	And said project is described as "Une appli pour commander des bons sushis"
	And said project begins on "2018-06-24"
	And said project ends on "2020-03-07"
	Then this project cannot be created

Scenario: the project description is excessively long
	Given a new project is initiated
	And said project is given the name "Sushi Shop"
	And said project is described as "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec venenatis consectetur gravida. Cras nec convallis nunc. Proin dignissim augue nulla, id efficitur odio volutpat nec. Donec malesuada nibh quis erat consequat, eu semper sem aliquet. Aenean interdum eros ut scelerisque sollicitudin. Integer bibendum sollicitudin lectus, eu euismod augue viverra quis. Sed eu orci augue. Ut ac mattis eros, at luctus odio. Nulla tincidunt porttitor leo, vitae aliquam eros. In euismod, arcu at tristique viverra, augue turpis vehicula ante, sit amet ullamcorper augue dui sit amet purus. Praesent faucibus sapien in feugiat ullamcorper. Aenean ac tellus nec ante mattis rutrum. Vestibulum ut malesuada felis, ut mattis felis. Nulla vitae ullamcorper risus. Aliquam magna lacus, commodo vel venenatis non, dignissim tempor nunc. Proin vitae mi elit. Curabitur dapibus, nunc ut aliquet posuere, sapien elit lacinia justo, id mattis metus nibh vel mi. Etiam vel tellus eu ex facilisis sollicitudin 	ut ut nulla. Etiam vitae mauris malesuada libero elementum semper sit amet egestas lorem. Suspendisse volutpat."
	And said project begins on "2020-12-07"
    And said project ends on "2021-03-24"
	Then this project cannot be created

Scenario: the user is already working on a project with the same name
    Given a new project is initiated
    And said project is given the name "Sushi Shop"
    And said project is described as "Une appli pour commander des bons sushis"
    And said project begins on "2020-12-07"
    And said project ends on "2021-03-24"
    And the user is currently working on a project named "Sushi Shop"
    Then this project cannot be created

Scenario: the user is already working on a project with a different name
    Given a new project is initiated
    And said project is given the name "Sushi Shop"
    And said project is described as "Une appli pour commander des bons sushis"
    And said project begins on "2020-12-07"
    And said project ends on "2021-03-24"
    And the user is currently working on a project named "Lasagne Shop"
    Then this project can be created

Scenario: the user is already working on a project with a the same name (Caseproof)
    Given a new project is initiated
    And said project is given the name "Sushi Shop"
    And said project is described as "Une appli pour commander des bons sushis"
    And said project begins on "2020-12-07"
    And said project ends on "2021-03-24"
    And the user is currently working on a project named "SUSHI SHOP"
    Then this project cannot be created

Scenario: the user has previously worked on a project with the same name
    Given a new project is initiated
    And said project is given the name "Sushi Shop"
    And said project is described as "Une appli pour commander des bons sushis"
    And said project begins on "2020-12-07"
    And said project ends on "2021-03-24"
    And the user has previously worked on a project named "Sushi Shop"
    Then this project can be created

Scenario: the user has previously worked on a project with the same name (Caseproof)
    Given a new project is initiated
    And said project is given the name "Sushi Shop"
    And said project is described as "Une appli pour commander des bons sushis"
    And said project begins on "2020-12-07"
    And said project ends on "2021-03-24"
    And the user has previously worked on a project named "SUSHI SHOP"
    Then this project can be created