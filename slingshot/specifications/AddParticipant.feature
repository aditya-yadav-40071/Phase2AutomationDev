Feature: To check successful and unsuccessful Add Participant functionality of the application  
  As an user of the application
  I want to test Add Participant feature

Background: Login:LoginAsSubAdmin,SubAdmin:PodSearch

@Group(PageTitle)
Scenario: To Verify the page-title of 'Participant List' page
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 

@Group(PageTitle1)
Scenario: To Verify the page-title of 'Add User' page
Given I am ON overview page of the pod
When I CLICK addParticipant button
And I CLICK inviteLearner button 
Then I am ON inviteUser page

@Group(PageTitle3)
Scenario: To Verify the page-title after navigating to previous page from 'Participant List' page
Given I am ON overview page of the pod
When I CLICK addParticipant button
And I CLICK inviteLearnerBack button to go back to the previous page
Then I am ON overview page of the pod

@Group(AddPartSuccess)
Scenario: To verify successful scenario for adding and enrolling a user to a pod with DATA InviteUser_Success
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 
And I CLICK inviteLearner button 
Then I am ON inviteUser page
And I ENTER valid user details with DATA InviteUser_Success
Then I SUBMIT the form
And I am ON participantList page
Then I VERIFY ifUserIsEnrolled to the pod
And I PERFORM searchUserByFilter based upon site name with DATA FilterSearch_Success
Then correct user is displayed on the page
And I am ON participantList page

@Group(AddPartSuccess11111111111111)
Scenario: To verify successful scenario for adding, enrolling and login with the created user with DATA InviteUser_Success
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 
And I CLICK inviteLearner button 
Then I am ON inviteUser page
And I ENTER valid user details with DATA InviteUser_Success
Then I SUBMIT the form
And I am ON participantList page
Then I CLICK leftMenuButton icon
And I CLICK logoutLink from menu options
Then I am ON home page
When I CLICK login link
Then I am ON login page
And I PERFORM loginWithInvitedUser
Then I am ON changePassword page
And I ENTER valid password details with DATA InvitedUserChangePass_Success
Then I SUBMIT the form
Then I am ON dashboard page
And I VERIFY correctUserLoggedIn 
Then it is verified that user is logged in with correct credentials

@Group(AddPartSuccessFilter)
Scenario: To verify successful scenario for searching the enrolled user using filters with DATA FilterEnrollSearch_Success
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 
And I PERFORM searchUserByFilter based upon site name
Then correct user is displayed on the page
And I am ON participantList page

Scenario: To verify successful scenario for searching the enrolled user using filters with DATA FilterUnEnrollSearch_Success
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 
And I PERFORM searchUserByFilter based upon site name
Then correct user is displayed on the page
And I am ON participantList page

@Group(isUserSortedAphabetically)
Scenario: To verify sorting of participant names in A to Z order in 'Participant List' page with DATA LearnerListSorted_AtoZ
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 
And I VERIFY learnerListSorted alphabatically

@Group(isUsernotSortedAphabetically)
Scenario: To verify sorting of participant names in Z to A order in 'Participant List' page with DATA LearnerListSorted_ZtoA
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 
And I VERIFY learnerListSorted in decending order

@Group(EnrollUserbuttonSuccess3434)
Scenario: To verify enroling of student by using Enroll button in 'Participant List' page with DATA EnrollUserToOtherPod_Success
Given I am ON overview page of the pod
When I CLICK addParticipant button
Then I am ON participantList page 
And I CLICK inviteLearner button 
Then I am ON inviteUser page
And I ENTER valid user details with DATA InviteUser_Success
Then I SUBMIT the form
And I am ON participantList page
Then I VERIFY ifUserIsEnrolled to the pod
Then I CLICK ongoingBreadcrumb 
And I am ON ongoingTraining page
And I ENTER searchInputs for search with DATA EnrollUserToOtherPod_Success
Then I CLICK searchPodArrow 
And I VERIFY correctPodsDisplayed
And I CLICK firstPod
Then I am ON overview page
And I VERIFY podName
When I CLICK addParticipant button
Then I am ON participantList page 
And I PERFORM enrollUserToAnotherPod on participant list page
Then I am ON overview page of the pod
And I CLICK addParticipant button
Then I am ON participantList page 
And I VERIFY ifUserIsEnrolled to the pod
Then user is enrolled to the pod

