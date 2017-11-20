Feature: To test all the feature of Post Job page
As an user of the application
I want to test various functionalities of Post Job page

Background: Login:loginSuccess

Scenario: To Verify pageTitle of Post Job page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   
Scenario: To verify the navigation from Post Job page to dashboard page using breadcrumb
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   When I CLICK postJobBreadcrumb 
   Then I am ON dashboard page

 
Scenario: To verify the navigation from Post Job page to view all Job Postings page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   And I CLICK viewAllPostings link
   Then I am ON jobPostList page 
  
Scenario: To verify the navigation from Post Job page to view all Job Postings page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   And I CLICK viewAllPostings link
   Then I am ON jobPostList page 
   When I CLICK postJobBreadcrumb
   Then I am ON postJob page

   
Scenario: To verify all the failure scenarios for Post Job page with DATA PostJob_Failure
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   When I ENTER invalid details with DATA PostJob_Failure error messages are displayed
   Then I am ON postJob page
   
@Group(inProgress)    
Scenario: To verify success scenarios for Post Job page with DATA PostJob_Success
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   When I ENTER valid details with DATA PostJob_Success
   Then I SUBMIT the form
   Then I am ON jobPostList page
   When I VERIFY isCorrectJob displayed
   Then the job is verified successfully
   
   
   
   
   
   
   
   
   