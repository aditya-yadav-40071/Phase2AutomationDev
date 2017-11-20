Feature: To test all the feature of Pods Customization page
As an user of the application
I want to test various functionalities of Pods Customization page

Background: Login:loginSuccess


Scenario: To Verify pageTitle of Pod Customization page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK podCustomization link
   Then I am ON podCustomization page
   
  
Scenario: To verify the navigation from Pod Customization page to dashboard page using breadcrumb
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK podCustomization link
   Then I am ON podCustomization page
   When I CLICK podsCustomizationBack bredcrum url 
   Then I am ON dashboard page
   
@Group(aaa)   
Scenario: To verify the upload pod icon in Pod Customization page
   Given I am ON dashboard page 
   Then I CLICK leftMenuButton
   When I CLICK podCustomization link
   Then I am ON podCustomization page
   When I VERIFY podsAvailability 
   Then successfully pods are available
   When I PERFORM podIconUpload operation
   Then it is successfully uploaded.

 

   
   
   
   
   
   
   
   
   