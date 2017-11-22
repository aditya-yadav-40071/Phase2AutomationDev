package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.app.pages.user.LoginPage
import com.KPPhaseTwo.app.pages.admins.ManageAdminsPage
import com.KPPhaseTwo.app.pages.user.DashboardPage
import com.KPPhaseTwo.app.pages.admins.EditOrganizationProfilePage
import com.KPPhaseTwo.app.pages.admins.ViewOrganizationProfilePage
import com.KPPhaseTwo.app.pages.admins.ChangePrivilagesPage
import com.KPPhaseTwo.app.pages.user.UserEditProfilePage
import com.KPPhaseTwo.app.pages.admins.AddAdminPage
import com.KPPhaseTwo.app.pages.admins.PostJobPage
import com.KPPhaseTwo.app.pages.admins.SalesAdminLicenseDetailsPage
import com.KPPhaseTwo.app.pages.user.ViewUserPublicProfilePage

/**
 * Created by Sandhya on 27/9/2015
 */
class UserManager {

//LOGIN PAGE
	
	//To verify that the logged in user is displayed in the edit profile page
	def correctUserLoggedIn = { browser, formData ->
		LoginPage.correctUserLoggedIn browser, formData
	}
	
	//Login to the application with removed admin's credential
	def loginAsAnAdmin = { browser, formData ->
		LoginPage.loginAsAnAdmin browser,formData
	}
	
//EDIT PROFILE
	def educationDetailsMatch = { browser, formData ->
		ViewUserPublicProfilePage.educationDetailsMatch browser, formData
	}
	
	def workExperienceDetailsMatch = { browser, formData ->
		println "-------------------------------------------------------------------------...-----------------------"
		ViewUserPublicProfilePage.workExperienceDetailsMatch browser, formData
	}
	def skillsDisplayed = { browser, formData ->
		println "Inside User Manager::::::::::::::"
		UserEditProfilePage.skillsDisplayed browser, formData
	}
	
	def deleteExistingSkills = { browser, formData ->
		println "Inside User Manager::::::::::::::"
		UserEditProfilePage.deleteExistingSkills browser, formData
	}
	
	def skillsRetained = { browser, formData ->
		println "Inside User Manager::::::::::::::"
		UserEditProfilePage.skillsRetained browser, formData
	}
	
	def removeASkill = { browser, formData ->
		println "Inside User Manager::::::::::::::"
		UserEditProfilePage.removeASkill browser, formData
	}
	
	def skillsRemoved = { browser, formData ->
		println "Inside User Manager::::::::::::::"
		UserEditProfilePage.skillsRemoved browser, formData
	}
	
	def uploadProfilepic = { browser, formData ->
		println "Inside User Manager::::::::::::::"
		UserEditProfilePage.uploadProfilepic browser, formData
	}
	
	def uploadUserImgDisplay = { browser, formData ->
		ViewUserPublicProfilePage.uploadUserImgDisplay browser,  formData
	}

//DASHBOARD PAGE
	
	//To logout from the application
	def logOut = { browser, formData ->
		DashboardPage.logOut browser,formData
	}

	//Organization name verification
	def orgNameChangeOnDashboard = {browser, formData ->
		DashboardPage.orgNameChangeOnDashboard browser, formData
	}

	//To get the number of pods in Recommended from dashboard
	def getPodNumber = { browser,formData ->
		DashboardPage.getPodNumber browser,formData
	}

	def loggedInAsTrainer = { browser, formData ->
		DashboardPage.loggedInAsTrainer browser, formData
	}

	def loggedInAsJobAdmin = { browser, formData ->
		DashboardPage.loggedInAsJobAdmin browser, formData
	}

	def loggedInAsSubAdmin = { browser, formData ->
		DashboardPage.loggedInAsSubAdmin browser, formData
	}

	
//EDIT ORGANIZATION PROFILE
	
	//To verify that the necessary fields are entered in Edit profile page with correct data.
	def profileDataDisplayMatch = { browser, formData ->
		EditOrganizationProfilePage.profileDataDisplayMatch browser, formData
	}

	//To remove all industries selected.
	def removeAllIndustry= { browser, formData ->
		EditOrganizationProfilePage.removeAllIndustry browser, formData
	}

	//To verify the error message after removing industries.
	def indErrorMessageMap = { browser, formData ->
		EditOrganizationProfilePage.indErrorMessageMap browser, formData
	}

	//To verify the removal of industries
	def removeIndustries = { browser, formData ->
		EditOrganizationProfilePage.removeIndustries browser, formData
	}

	//To verify removal of particular industries
	def industryRemoved = { browser, formData ->
		EditOrganizationProfilePage.industryRemoved browser, formData
	}

	//To perform upload of logo of Organization
	def orgProfileImgUpload = {browser, formData ->
		EditOrganizationProfilePage.orgProfileImgUpload browser, formData
	}
	
	//To get First name of the Org Admin
	def getOrgFirstName = {browser, formData ->
		EditOrganizationProfilePage.getOrgFirstName browser, formData
	}

//VIEW ORGANIZATION PROFILE
	
	//To verify data after editing data
	def editDataMatch = { browser, formData ->
		ViewOrganizationProfilePage.editDataMatch browser, formData
	}

	//To verify About Organization data display.
	def aboutOrgDataDisplay = { browser, formData ->
		ViewOrganizationProfilePage.aboutOrgDataDisplay browser, formData
	}

	//To verify image displayed in View Profile page
	def uploadImgDisplay = { browser, formData ->
		ViewOrganizationProfilePage.uploadImgDisplay browser,  formData
	}

	//To verify Latest News data display.
	def aboutLatestNewsDisplay = { browser, formData ->
		ViewOrganizationProfilePage.aboutLatestNewsDisplay browser, formData
	}

//MANAGE ADMINS PAGE
	
	def clickOnAdminName = { browser, formData ->
		ManageAdminsPage.clickOnAdminName browser,formData
	}

	def adminDetails = { browser, formData ->
		ManageAdminsPage.adminDetails browser,formData
	}
	
	//to remove an admon from the admins list
	def removeAnAdmin = { browser, formData ->
		ManageAdminsPage.removeAnAdmin browser,formData
	}

	//Verify that admin has been removed from the admins list
	def isAdminRemoved = { browser, formData ->
		ManageAdminsPage.isAdminRemoved browser,formData
	}

	def clickOnChangePrivilages = { browser, formData ->
		ManageAdminsPage.clickOnChangePrivilages browser, formData
	}

	def clickOnNewAdminChangePrivilage = { browser, formData ->
		ManageAdminsPage.clickOnNewAdminChangePrivilage browser, formData
	}
	
	//To verify job title after posting a job 
	def isCorrectJob = {browser, formData ->
		PostJobPage.isCorrectJob browser ,formData
	}

	//Verify that added admin is displayed in the list
	def addedAdminDisplayed = { browser, formData ->
		ManageAdminsPage.addedAdminDisplayed browser,formData
	}
	
//CHANGE PRIIVILEGES PAGE

	def adminPrivilageChanged = { browser, formData ->
		ChangePrivilagesPage.adminPrivilageChanged browser, formData
	}
	
//ADD ADMIN PAGE

	def addAnotherAdminErrorMessage = { browser, formData ->
		AddAdminPage.addAnotherAdminErrorMessage browser, formData
	}

//SALES ADMIN

	def clickPodReview = {browser, formData ->
		SalesAdminLicenseDetailsPage.clickPodReview browser, formData
	}
	
}
