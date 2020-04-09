Feature: Can I join this project ?

Scenario: User works on less than 5 projects and the project doesn'thave a maximum number of participants
	Given A user currently working on 4 projects
	When said user candidates to a project with currently 1 participants and a maximum of 0 participants
	Then he can join the project

Scenario: User works on less than 5 projects and the project didn't reach full capacity
	Given A user currently working on 2 projects
	When said user candidates to a project with currently 4 participants and a maximum of 5 participants
	Then he can join the project

Scenario: User works on less than 5 projects and the project reached full capacity
	Given A user currently working on 3 projects
	When said user candidates to a project with currently 3 participants and a maximum of 3 participants
	Then he cannot join the project

Scenario: User works on 5 projects and the project doesn't have a maximum
	Given A user currently working on 5 projects
	When said user candidates to a project with currently 1 participants and a maximum of 0 participants
	Then he cannot join the project

Scenario: User works on 5 projects and the project reached full capacity
	Given A user currently working on 5 projects
	When said user candidates to a project with currently 4 participants and a maximum of 4 participants
	Then he cannot join the project


