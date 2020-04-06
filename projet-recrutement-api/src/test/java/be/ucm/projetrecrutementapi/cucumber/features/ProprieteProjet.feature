Feature: Am I owner of the project ?

Scenario: User created the project and didn't leave
Given a user with the id 2
And a project with the id 1
And said project is created by the user with the id 2
When the user 2 wants to put an end to the project
Then the user can put the project to an end

Scenario: User didn't create the project and didn't leave
Given a user with the id 1
And a project with the id 1
And said project is created by the user with the id 2
When the user 1 wants to put an end to the project
Then the user cannot put the project to an end