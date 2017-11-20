Feature: Successful and Unsuccessful scenarios of buying a pod as an admin/user in the application  
  As an admin/user of the application
  I want to test the Buy a Pod feature

Background: Login:LoginAsAdmin

Scenario: To verify the amount of the pod with DATA PodsToBuy_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommended
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON podDetails page
	And I PERFORM getCostOfPod from details page
	Then I CLICK buyThePod button
	Then I am ON paymentRequest page
	And I VERIFY equalAmountStatus
	And the same amount used for calculation
	
Scenario: To verify request of payment for zero users with DATA PodsToBuy_Success
	Given I am ON dashboard page
	And I CLICK viewAllRecommended
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON podDetails page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER the number of users with DATA NoOfUsers_Failure
	And I SUBMIT the form
	Then I am ON paymentRequest page	 

Scenario: To verify request of payment to the Sales Admin with DATA PodsToBuy_Success
	Given I am ON dashboard page 
	And I CLICK viewAllRecommended
	Then I am ON recommendedTraining page
	And I PERFORM clickPodToBuy for the pods
	Then I am ON podDetails page
	And I CLICK buyThePod button
	Then I am ON paymentRequest page
	Then I ENTER the number of users with DATA NoOfUsers_Success
	And I VERIFY noOfUsersEntered for calculation
	And I SUBMIT the form
	Then I am ON podDetails page
	Then I VERIFY podBuyRequest action
	And the pod purchase request is sent