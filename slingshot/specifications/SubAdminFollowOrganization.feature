Feature: Successful and Unsuccessful Follow Organization functionality of the application  
  As an Sub Admin of the application
  I want to test Follow Organization feature
  
Background: Login:LoginAsSubAdmin

@Group(CheckPageTitle)
Scenario: To Verify the page-title of 'Follow Organization' page
Given I am ON dashboard page of the pod
When I CLICK followOrg link on the dashboard
Then I am ON followedOrgList page