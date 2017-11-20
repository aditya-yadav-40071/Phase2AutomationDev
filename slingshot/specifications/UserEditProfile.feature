Feature: To test all the feature of Individual User Profile page
As an user of the application
I want to test various functionalities of Individual User Profile page

Background: Login:loginSuccess

Scenario: To Verify the page-title of 'User Edit Profile' page
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	
Scenario: To Verify the navigation from edit profile page to Dashboard using Cancel link 
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page			 
	And I CLICK cancelLink to cancel the form submission
	Then I am ON dashboard page

Scenario: To Verify the page-title of 'User View Profile' page
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	When I CLICK viewPublicProfile link
	Then I am ON userViewProfile page

Scenario: To verify 'User Edit Profile' link from breadcrumb menu
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK dashbreadcrumb breadcrumb
	Then I am ON dashboard page
	
Scenario: To Verify the navigation from View Public Profile page to Edit Profile page using bredcrumb link
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK viewPublicProfile link
	Then I am ON userViewProfile page
	Then I CLICK editProfilelink breadcrumb
	Then I am ON editProfile page

@Group(00)
Scenario: To Verify the navigation from View Public Profile page to Dashboard page using bredcrumb link
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	And I CLICK viewPublicProfile link
	Then I am ON userViewProfile page
	Then I CLICK dashBoardLink breadcrumb
	Then I am ON dashboard page

@Group(UserBaisicInfoDetails)
Scenario: To verify all the failure scenario for 'User Edit Profile Basic Information' page with DATA UserBaisicInfo_Failure 
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And  I ENTER basicInformation invalid details with DATA UserBaisicInfo_Failure
	When I SUBMIT basicInformation form 
	Then I am ON editProfile page

@Group(EducatonFailure)
Scenario: To verify all failure scenario for 'User Edit Profile Education Qualification' page with DATA UserEduQualification_Failure
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I CLICK educationDetailsArrowDown
	Then I am ON editProfile page
	And  I ENTER eductionQualification invalid details with DATA UserEduQualification_Failure
	When I SUBMIT eductionQualification form 
	Then I am ON editProfile page
	
@Group(EducatonSuccess)
Scenario: To verify success scenario for 'User Edit Profile Education Qualification' page with DATA UserEduQualification_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I CLICK educationDetailsArrowDown
	Then I am ON editProfile page
	And  I ENTER eductionQualification valid details
	When I SUBMIT eductionQualification form 
	Then I am ON userViewProfile page

@Group(VerifyEducation)
Scenario: To verify the entered Education details in View Public Profile page with DATA UserEduQualification_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I CLICK educationDetailsArrowDown
	Then I am ON editProfile page
	And  I ENTER eductionQualification valid details UserEduQualification_Success
	When I SUBMIT eductionQualification form 
	Then I am ON userViewProfile page
	And I VERIFY educationDetails on View Profile page
	Then I am ON userViewProfile page 
	
@Group(WorkExperienceFailure)
Scenario: To verify success scenario for 'User Edit Profile Work Experience' page with DATA WorkExperience_Failure
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK workExperienceArrowDown
	Then I CLICK workExperienceAddMoreButton
	And  I ENTER workExperience valid details with DATA WorkExperience_Failure
	When I SUBMIT workExperience form 
	Then I am ON userViewProfile page 

@Group(WorkExperienceSuccess)
Scenario: To verify success scenario for 'User Edit Profile Work Experience' page with DATA WorkExperience_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK workExperienceArrowDown
	Then I CLICK workExperienceAddMoreButton
	And  I ENTER workExperience valid details with DATA WorkExperience_Success
	When I SUBMIT workExperience form 
	Then I am ON userViewProfile page 
	
@Group(SkillsSuccess)
Scenario: To verify success scenario for 'User Edit Profile Skills' page with DATA Skills_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	And  I ENTER skills valid details with DATA Skills_Success
	And I SUBMIT skills form
	Then I am ON userViewProfile page
	
@Group(VerifySkillsSuccess)
Scenario: To verify success scenario for 'User Edit Profile Skills' page with DATA Skills_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	Then I PERFORM deleteExistingSkills to delete existing user skills
	And  I ENTER skills valid details with DATA Skills_Success
	And I SUBMIT skills form 
	Then I am ON userViewProfile page
	Then I VERIFY skillsDisplayed on 'View Public Profile' page
	And I CLICK editProfilelink breadcrumb
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	Then I VERIFY skillsRetained on edit profile pages
	And I am ON editProfile page

@Group(CertificateSuccess)	
Scenario: To verify success scenario for 'User Edit Profile Certificate' page with DATA Certificate_Success
  Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK certificateArrowDown
	And  I ENTER certificate valid details with DATA Certificate_Success
	And I SUBMIT certificate form 
	Then I am ON userViewProfile page
	
@Group(UploadProfilePic)
Scenario: To verify profile pic upload success scenario with DATA UserProfilePicUpload_Success 
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I PERFORM uploadProfilepic to upload user profile picture with DATA UserProfilePicUpload_Success
	And I CLICK viewPublicProfile link
	Then I am ON userViewProfile page
	And I VERIFY uploadUserImgDisplay 
	Then I am ON userViewProfile page
	
@Group(removePecificSkill)
Scenario: To remove a user skill and verify if skill is removed with DATA RemoveSkill_Success
	Given I am ON dashboard page
	When I CLICK userEditProfile link
	Then I am ON editProfile page
	And I CLICK skillsArrowDown
	And I PERFORM removeASkill to remove a specific skill from user profile with DATA RemoveSkill_Success
	And I SUBMIT skills form 
	Then I am ON userViewProfile page
	Then I VERIFY skillsRemoved after update on 'View Public Profile' page	
	Then I am ON userViewProfile page
	
