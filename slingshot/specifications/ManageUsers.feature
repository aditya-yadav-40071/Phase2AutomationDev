Feature: Successful and Unsuccessful Scenarios of Manage User functionality of the application  
  As an user of the application
  I want to test Manage User feature
  
Background: Login:loginSuccess


Scenario: To verify the page title of the Manage User List page
	Given I am ON dashboard page 
	When I CLICK leftMenuButton
	And I CLICK manageUsers link
	Then I am ON manageUser page
	
	
Scenario: To verify the Error message being displayed when the Manage Users list page does not contain any Users
    Given I am ON dashboard page 
	When I CLICK leftMenuButton
	And I CLICK manageUsers link
	Then I am ON manageUser page
	And I VERIFY isManageUsrErrorMessageCorrect in Manage User page is correct
	Then it is verified successfully
	
	
Scenario: To verify that invited users are displayed in manage user page
	Given I am ON dashboard page 
	When I CLICK leftMenuButton
	And I CLICK manageUsers link
	Then I am ON manageUser page
	And I VERIFY usersDisplayed in manage user page
	Then users are displayed successfully verified
	
		
	
Scenario: To verify that onclick of user name it should navigate to view public profile page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
    And I CLICK manageUsers link
	Then I am ON manageUser page
	And I VERIFY usersDisplayed in manage user page
	Then users are displayed successfully verified
	And I CLICK firstUser name
	Then I am ON userViewProfile page
	Then it is successfully verified
	


Scenario: To verify that onclick of user name it should navigate to same user profile page which it was clicked
	Given I am ON dashboard page
	When I CLICK leftMenuButton
    And I CLICK manageUsers link
	Then I am ON manageUser page
	And I VERIFY usersDisplayed in manage user page
	Then users are displayed successfully verified
	And I PERFORM clickOperation on first username
	Then I am ON userViewProfile page
	When I VERIFY correctUserProfile is displayed or not
	Then it is successfully verified the correct user
	

Scenario: To verify the remove user feature in manage users page
    Given I am ON dashboard page
	When I CLICK leftMenuButton
    And I CLICK manageUsers link
	Then I am ON manageUser page
	And I VERIFY usersDisplayed in manage user page
	Then users are displayed and verified successfully 
	And I CLICK firstUser from the list
	Then I am ON userViewProfile page
	When I VERIFY correctUserProfile is displayed or not and fetch the profile email id 
	Then I CLICK back link via bredcrum
	When I PERFORM removeUser operation
	Then I CLICK leftMenuButton
	And I CLICK logoutLink 
	Then I am ON home page
	And I CLICK login button
	Then I am ON login page
	When I VERIFY loginAsRemovedUser credentials
	Then I am ON login page
	And Successfully Application does not allow to login with the same credentials
	


Scenario: To verify the sorted results displayed for sortby filter is correct with DATA ManageUsersSortBy_Success
    Given I am ON dashboard page
	When I CLICK leftMenuButton
    And I CLICK manageUsers link
	Then I am ON manageUser page
	When I ENTER manageUser sortby filter details 
	And I VERIFY manageUserSortByFilter for sorting the results
	Then the list is sorted successfully
	
	

	
		