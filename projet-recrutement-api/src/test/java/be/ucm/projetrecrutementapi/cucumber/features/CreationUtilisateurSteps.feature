Feature: Can I create this account ?

Scenario: submitted data is correct and unique
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b.com"
	And the password he chose is "M0stertruck!"
	And his birth date is "1995-08-07"
	Then this account can be created

Scenario: submitted nickname is not unique
    Given someone wants to create an account
    And the nickname he chose is "SpaceBichon"
    And the email he chose is "b@b.com"
    And the password he chose is "M0stertruck!"
    And his birth date is "1995-08-07"
    Then this account cannot be created

Scenario: submitted email adress is not unique
    Given someone wants to create an account
    And the nickname he chose is "Walter"
    And the email he chose is "a@a.com"
    And the password he chose is "M0stertruck!"
    And his birth date is "1995-08-07"
    Then this account cannot be created

Scenario: there is no submitted username
    Given someone wants to create an account
    And the email he chose is "b@b.com"
    And the password he chose is "M0stertruck!"
    And his birth date is "1995-08-07"
    Then this account cannot be created

Scenario: there is no submitted email
    Given someone wants to create an account
    And the nickname he chose is "Walter"
    And the password he chose is "M0stertruck!"
    And his birth date is "1995-08-07"
    Then this account cannot be created

Scenario: there is no submitted password
    Given someone wants to create an account
    And the nickname he chose is "Walter"
    And the email he chose is "b@b.com"
    And his birth date is "1995-08-07"
    Then this account cannot be created

Scenario: there is no submitted birth date
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b.com"
	And the password he chose is "M0stertruck!"
	Then this account cannot be created

Scenario: birth date is located in the future
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b.com"
	And the password he chose is "M0stertruck!"
	And his birth date is "2025-08-31"
	Then this account cannot be created

Scenario: username contains forbidden characters
    Given someone wants to create an account
    And the nickname he chose is "1 OR #"
    And the email he chose is "b@b.com"
    And the password he chose is "M0stertruck!"
    And his birth date is "1995-08-07"
    Then this account cannot be created

Scenario: email adress format is invalid
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b"
	And the password he chose is "M0stertruck!"
	And his birth date is "1995-08-07"
	Then this account cannot be created

Scenario: email adress is not valid
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b"
	And the password he chose is "M0stertruck!"
	And his birth date is "1995-08-07"
	Then this account cannot be created

Scenario: password is too short
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b.com"
	And the password he chose is "M0ose!"
	And his birth date is "1995-08-07"
	Then this account cannot be created

Scenario: password doesn't contain an uppercase character
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b.com"
	And the password he chose is "m0stertruck!"
	And his birth date is "1995-08-07"
	Then this account cannot be created

Scenario: password doesn't contain a lowercase character
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b.com"
	And the password he chose is "M0STERTRUCK!"
	And his birth date is "1995-08-07"
	Then this account cannot be created

Scenario: password doesn't contain a special character
	Given someone wants to create an account
	And the nickname he chose is "Walter"
	And the email he chose is "b@b.com"
	And the password he chose is "M0stertruck"
	And his birth date is "1995-08-07"
	Then this account cannot be created

