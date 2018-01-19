Feature: Successful and Unsuccessful Ongoing Training functionality to the application  
  As an user of the application
  I want to test Ongoing Training feature

Background: Login:LoginAsSubAdmin

@Name(PodSearch)
Scenario: To verify the name of pod after navigating to pod details page with DATA PodSearch_Success
Given I am ON dashboard page
And I CLICK viewAllOngoingTrainings
Then I am ON ongoingTraining page
And I ENTER searchInputs for search with DATA PodSearch_Success
Then I CLICK searchPodArrow 
And I VERIFY correctPodsDisplayed
And I CLICK firstPod
Then I am ON overview page
When I VERIFY podName
Then the pod name is same as the first pod clicked




