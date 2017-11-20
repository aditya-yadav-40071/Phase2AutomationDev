Feature: Successful and Unsuccessful registration for Organization to the application
As an user of the application
I want to test registration feature 

Scenario: To Verify unsuccessful registration to application with DATA Organisation_Reg_Failure
		  Given I am ON home page
		  When I CLICK registerFrmPage
		  Then I am ON bifurcateUser page
		  When I CLICK organizationLnk
		  And I CLICK nextBtn
		  Then I am ON companyRegistration page
		  And I ENTER invalid details with DATA Organisation_Reg_Failure
		  And I SUBMIT the form
		  Then error messages are displayed
		  
Scenario: To verify successful OTP message and verify data on Edit Organization profile page the first time with DATA Organization_Failure
		  Given I am ON home page
		  When I CLICK registerFrmPage
		  Then I am ON bifurcateUser page
		  When I CLICK organizationLnk
		  And I CLICK nextBtn
		  Then I am ON companyRegistration page
		  And I ENTER invalid details of OTP with DATA Organization_Failure
		  And I SUBMIT the form
		  Then I CLICK podWorksLogo
		  And I am ON home page
		  When I CLICK login link
	      Then I am ON login page
	      And I ENTER valid login details with DATA Login_Success
	      And I SUBMIT the form
	      Then I am ON dashboard page
    	  And I CLICK editOrgOnDashboard 
		  Then I am ON editOrganizationProfile page
	      And I VERIFY profileDataDisplayMatch
	      And the data displayed initially is a match