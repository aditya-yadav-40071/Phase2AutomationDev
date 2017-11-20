Feature: Successful and Unsuccessful scenarios in License details page of the application  
  As a user of the application
  I want to test License details page features
  
Background: Login:LoginAsAdmin

@Group(aaaa)
Scenario: To verify clicking user review to accept pod purchase with DATA PodsToBuy_Success
	Given I am ON dashboard page
	Then I CLICK editOrganizationProfile
	And I PERFORM getOrgFirstName
	Then I CLICK dashboardBreadcrum
	And I am ON dashboard page
	And I CLICK viewAllRecommended
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON podDetails page
	And I VERIFY podBuyRequest for the pods
	Then I am ON podDetails page
	And I CLICK leftMenuButton
	And I CLICK logoutLink
	And I CLICK login link
	Then I am ON login page
	Then I ENTER valid Sales Admin Login credentials with DATA LoginAsSalesAdmin_Success
	And I SUBMIT the form
	And I am ON dashboard page
	And I CLICK leftMenuButton
    And I CLICK licenseDetails link
    Then I am ON LicenseDetails page
    And I CLICK pendingButton button
    Then I PERFORM clickPodReview action
    