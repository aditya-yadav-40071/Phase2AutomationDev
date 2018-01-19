package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage



final class DashboardPage extends WebPage {

	//To logout from the application
	def static logOut = { browser, formData ->
		new DashboardForm().logOut browser, formData
	}

	//To verify the Organization name
	def static getOrgName = {browser, formData ->
		new DashboardForm().getOrgName browser, formData
	}
	
	def static orgNameChangeOnDashboard = {browser, formData ->
		new DashboardForm().orgNameChangeOnDashboard browser, formData
	}

	//To get the number of pods in Recommended from dashboard
	def static getPodNumber = { browser, formData ->
		new DashboardForm().getPodNumber browser, formData
	}

	def static loggedInAsTrainer= { browser, formData ->
		new DashboardForm().loggedInAsTrainer browser, formData
	}

	def static loggedInAsJobAdmin= { browser, formData ->
		new DashboardForm().loggedInAsJobAdmin browser, formData
	}

	def static loggedInAsSubAdmin= { browser, formData ->
		new DashboardForm().loggedInAsSubAdmin browser, formData
	}

	static final class DashboardForm extends WebForm {

		//Manage Admins Page elements
		private static final def LEFT_MENU = "//button[@aria-expanded='false'][@role='button']"

		private static final def LOG_OUT = ".//a[@ng-click='logOut()']"
		
		private static final def ADMIN_TRAIN = ".//ul[@class='pull-left menu mt_40 no-padding ng-scope']/li[@ng-show='getOrgPodDashboard']"
		
		private static final def ADMIN_HIRE = ".//ul[@class='pull-left menu mt_40 no-padding ng-scope']/li[@ng-show='getOrgJobDashboard']"
		
		private static final def EDIT_TEXT = ".//a[@class='profile-edit-link display-block']/h5"
		
		private static final def MENU_ITEMS = ".//li[@class='menu-name-default mb30']/a"

		private static final def DASH_REC_NO = "//span[contains(text(), 'Recommended Trainings')]"

		private static final def DASH_ORG_NAME = "//h3/span[@class='capitalize ng-binding']"

		//Verify that added admin is displayed in the list
		def static final logOut = { browser, formData ->
			browser.scrollToElement(browser.getElement(Browser.XPATH, LEFT_MENU))
			browser.click LEFT_MENU
			browser.delay(1500)
			if(LOG_OUT !=null){
				browser.scrollToElement(browser.getElement(Browser.XPATH, LOG_OUT))
				browser.click LOG_OUT
				browser.delay(2000)
				return new SuccessOutcome()
			} else {
				return KPCommonPage.returnFailureOutcome(browser, "LogOutIssue", "Not able to logout from the application")
			}
		}

		//To verify Organization name on the dashboard
		def static final orgNameChangeOnDashboard = {browser, formData ->
			browser.delay(2000)
			def OrganizationName = new DashboardForm().getOrgName(browser)
			
			if(OrganizationName.equalsIgnoreCase(KPCommonPage.ViewOrgDataVerify[0])){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "orgNameIssue", "Changed Organization name not reflecting on dashboard.")
		}

		def static getOrgName = { def browser, def formData ->
			def orgName
			browser.delay(3000)
			if(browser.isDisplayed(DASH_ORG_NAME)){
				orgName = browser.gettext(DASH_ORG_NAME)
			}
			return orgName
		}
		
		//To get the number of pods from dashboard page
		def static final getPodNumber = {browser, formData ->
			def recString = browser.gettext(DASH_REC_NO)
			def numberOnDash = (recString.substring(recString.indexOf('(')+1, recString.indexOf(')'))).toInteger()
			println numberOnDash
			KPCommonPage.noOnDashboard = numberOnDash
			println KPCommonPage.noOnDashboard
			if(numberOnDash!=null){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "nullNumber", "Cannot find the number on dashboard for Recommended training.")
		}

		def static final loggedInAsTrainer = { browser, formData ->

			if(browser.isDisplayed(ADMIN_TRAIN) && browser.gettext(ADMIN_TRAIN).equalsIgnoreCase("Train") && !browser.isDisplayed(ADMIN_HIRE)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "TrainerTabIssue", "The Trainer tab is not displayed")
			}
		}

		def static final loggedInAsJobAdmin = { browser, formData ->
			if(browser.isDisplayed(ADMIN_HIRE) && browser.gettext(ADMIN_HIRE).equalsIgnoreCase("Hire") && !browser.isDisplayed(ADMIN_TRAIN)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobAdminTabIssue", "The Job Admin tab is not displayed")
			}
		}

		def static final loggedInAsSubAdmin = { browser, formData ->

			def hireText=browser.gettext(ADMIN_TRAIN)
			def trainText=browser.gettext(ADMIN_HIRE)
			def profileText=browser.gettext(EDIT_TEXT)

			if(hireText.isDisplayed(ADMIN_TRAIN) && trainText.isDisplayed(ADMIN_HIRE) && !profileText.equalsIgnoreCase("Edit Organization Profile")){
				if(hireText.equalsIgnoreCase("Hire") && trainText.equalsIgnoreCase("Train")){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SubAdminTabIssue", "The Sub Admin tab is not displayed")
				}
			}
		}
	}
}
