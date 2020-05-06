Feature: Login to GS1

@current
Scenario Outline: 01_GS one Login Scenario
Given user is already on Login Page
Then user enters "<username>" and "<password>"
#Then user clicks on login button
#Then user is on home page


Examples:
	| username | password |
	| testuser009  | Password@123 |
	
@current
Scenario Outline: 02_GS one Login Scenario
Given user is already on Login Page
Then user enters "<username>" and "<password>"
#Then user clicks on login button
#Then user is on home page


Examples:
	| username | password |
	| testuser009  | Password@123 |	