
Feature: To test all the feature of Post Job page
As an user of the application
I want to test various functionalities of Post Job page

Background: Login:LoginAsOrgAdmin

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
   When I CLICK dashboardBreadcrum 
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
   When I ENTER invalid details with DATA PostJob_Failure 
   Then I am ON postJob page error messages are displayed
   
@Group(inProgress78788)    
Scenario: To verify success scenarios for Post Job page and verify job is displayed on post job list page with DATA PostJob_Success
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   When I ENTER valid details with DATA PostJob_Success
   Then I SUBMIT the form
   Then I am ON jobPostList page
   Then I VERIFY correctJobDisplayed on job list page
   And it is verfied that job was displayed on post job list
   
@Group(PostJobSuccess)    
Scenario: To verify posted job description success scenario on job description page with DATA PostJob_Success
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   When I ENTER valid details with DATA PostJob_Success
   Then I SUBMIT the form
   Then I am ON jobPostList page
   Then I VERIFY correctJobDisplayed on job list page  
   And I am ON jobDetail page
   Then I PERFORM clickOnFirstJob
   And I VERIFY jobDetails match on job detail page
   Then I am ON jobDetail page and job details are verified
      
@Group(PostJobVerifyDescription)    
Scenario: To verify posted job description success scenario on job description page with DATA PostJob_Success
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK postJob link
   Then I am ON postJob page
   When I ENTER valid details with DATA PostJob_Success
   Then I SUBMIT the form
   Then I am ON jobPostList page
   Then I VERIFY correctJobDisplayed on job list page  
   And I am ON jobDetail page
   And I CLICK leftMenuButton 
   And I CLICK logoutLink to log out from current user
   Then I am ON login page
   And I ENTER valid login details with DATA Login_Success
   Then I am ON dashboard page
   And I CLICK hire tab 
   Then I CLICK recommendedJobViewAll link
   