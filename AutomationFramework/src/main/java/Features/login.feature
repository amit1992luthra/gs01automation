Feature: Login to GS1



Scenario Outline: 01_GS one Login Scenario
Given user is already on Login Page
Then user enters "<username>" and "<password>"
#Then user clicks on login button
#Then user is on home page


Examples:
	| username | password |
	| testuser009  | Password@123 |
	

Scenario Outline: 02_GS one Login Scenario
Given user is already on Login Page
Then user enters "<username>" and "<password>"
#Then user clicks on login button
#Then user is on home page


Examples:
	| username | password |
	| testuser009  | Password@123 |	
	
	
	
	@current
Scenario Outline: 03_GS one Login Scenario
Given user is already on Login Page
When user enters "<username>" and "<password>"
And user click on Add user Link
When user enters "<firstname>" , "<lastname>" and "<email>" 



Examples:
	| username | password |
	| testuser008  | Password@123 |	