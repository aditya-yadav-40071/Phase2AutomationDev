package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage




final class ManageUserPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("manageUser")){
			new ManageUserForm().populateFields(browser, formData)
		}
		/*else if(formKey.equals("userDetails")){
		 new inviteUserForm().populateFields(browser, formData);
		 }*/



	}








	def static getTotalViewingPodsCount = {browser, formData ->
		new ManageUserForm().getTotalViewingPodsCount browser, formData
	}

	def static getPageCount = {browser ->
		new ManageUserForm().getPageCount browser
	}

	def static isManageUsrErrorMessageCorrect = {browser, formData ->
		new ManageUserForm().isManageUsrErrorMessageCorrect browser,formData
	}

	def static usersDisplayed = {browser, formData ->
		new ManageUserForm().usersDisplayed browser,formData
	}

	def static clickOperation = {browser, formData ->
		new ManageUserForm().clickOperation browser,formData
	}

	def static correctUserProfile = {browser, formData ->
		new ManageUserForm().correctUserProfile browser, formData
	}

	def static removeUser = {browser,formData ->
		new ManageUserForm().removeUser browser, formData
	}

	def static loginAsRemovedUser = {browser, formData ->
		new ManageUserForm().loginAsRemovedUser browser, formData
	}
	
	def static manageUserSortByFilter = {browser, formData ->
		new ManageUserForm().manageUserSortByFilter browser, formData 
	}


	static final class ManageUserForm extends WebForm {

		//Manage Users form elements

		private static final def NO_USER_ERROR = "//h4[@ng-show='isListEmpty']"

		private static final def USER_NAMES = "//a[@ui-sref='kp.viewUserProfile']"

		private static final def ONGOING_1st_POD = "//dashboard-list-widget[@category-type='ongoingTrainings']//div[@ng-repeat='pod in podList'][1]//a"

		private static final def FIRST_USER = "//div[@class='clearfix content individual-job']/div/div[1]//h3/a"

		private static final def FIRST_USER_REMOVE  = "//div[@class='clearfix content individual-job']/div/div[1]/div[2]/a"

		private static final def CONFIRM_POPUP_YES = "//button[contains(@class,'md-primary md-confirm-button')]"

		private static final def CONFIRM_POPUP_NO = "//button[contains(@class,'md-primary md-cancel-button')]"

		private static final def PROFILE_PAGE_NAME = "//h3[@class='profile-name text-center profile-text']"

		private static final def PROFILE_EMAIL = "//h3[@class='profile-name text-center profile-text']/following-sibling::h5[1]"

		private static final def PROFILE_PSWD = "//div[contains(@ng-if,'userProfile.cloudUserJson.passwordText')]//span"

		private static final def LOGIN_USERNAME = "//input[@name='username']"

		private static final def LOGIN_PSWD = "//input[@name='password']"

		private static final def LOGIN_SUBMIT = "//input[@value='SIGN IN']"

		private static final def SORTBY_FILTER = "//select[contains(@class,'sort-select selectBox')]"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav/div[2]/img"


		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-page ng-scope active']/a"

		private static final def FIRST_BUTTON = "//li[@class='pagination-first ng-scope']/a"

		private static final def LAST_BUTTON = "//li[@class='pagination-last ng-scope']/a"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"

		//private static final def FIELDS = [SEARCH_TEXT, INDUSTRY, LOCATION]// the error fields.
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final manageUserPageErrorMessageMap = []

		private static final def FIELDS = [SORTBY_FILTER]// the error fields.
		//To enter data
		def static final populateFields = { browser, formData ->
			println ("ManageUsersForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)

					if(FIELDS[i].equals(SORTBY_FILTER)){
						if(!formData[i].equals("")){
							def sortData
							browser.click SORTBY_FILTER
							browser.delay(1000)
							sortData = formData[i]
							browser.selectDropdownValue(SORTBY_FILTER,formData[i])
							browser.delay(2000)
							KPCommonPage.manageUsersSortByData = sortData
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}


		//to get all the pod names from Search pod list page in a list
		public static def getOrgTextList(def browser,def element, def orgList,def fiftyRes){
			println "........INSIDE getorgtextlist method in filter page............"
			def allResult
			browser.scrollToElement2(element)
			browser.delay(3000)
			if(browser.isDisplayed(orgList)){
				println ".....ORG LIST is Displayed......"
				if(browser.isDisplayed(fiftyRes)){
					browser.click fiftyRes
					browser.delay(3000)
					allResult = browser.getLists(orgList)
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationDisplayedIssue", "Org list is not displayed.")
			}

			return allResult
		}

		def static getPageCount(def browser){
			println "Inside KPCommon pagecount"
			int lastPage=0
			if(browser.checkEnabled(LAST_BUTTON)){
				browser.click LAST_BUTTON
				browser.delay(1000)
			}
			browser.delay(1000)
			def pageText = browser.gettext(LAST_PAGE_NO)
			browser.delay(1000)
			lastPage = pageText.toInteger()
			println "...lastPage......"+lastPage
			browser.click FIRST_BUTTON
			browser.delay(1500)
			lastPage
		}





		//To get the total viewing User's count in Manage User page.
		def static final getTotalViewingPodsCount = {browser, formData ->

			def totalResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(USER_NAMES)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(3000)
					totalResult = browser.gettext("//p[@class='clearfix content output-count-header ng-binding']").split(" ")[3].trim()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}

			return totalResult

		}


		//To verify the error message being displayed in Manage Users list page
		def static final isManageUsrErrorMessageCorrect = {browser, formData ->

			def expMsg = "No User Found In Organization"
			def actMsg = browser.gettext(NO_USER_ERROR)
			if(browser.isDisplayed(NO_USER_ERROR)){
				if(expMsg.equalsIgnoreCase(actMsg.trim())){
					println ".....PASS....."
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ErrorMessageComparisionIssue", "When No Users are available Error message is not displaying correctly")
				}
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "ErrorMessageDisplayIssue", "Form Error Message is not Appearing.")
			}

		}




		//To verify that the users are displayed in manage users page
		def static final usersDisplayed = { browser, formData ->
			browser.delay(1500)
			if(browser.isDisplayed(USER_NAMES)){
				def userNameList = []
				def listOfUserNames = browser.getLists(USER_NAMES,"title")
				userNameList.addAll(listOfUserNames);
				println "usersIn1stPage ::: "+userNameList
				while(browser.checkEnabled(PAGINATION_NEXT)){
					browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
					browser.click PAGINATION_NEXT
					browser.delay(1500)
					def usersInNextPage = browser.getLists(USER_NAMES,"title")
					println "usersInNextPage:::"+usersInNextPage
					userNameList.addAll(usersInNextPage);
				}

				KPCommonPage.usersList = userNameList
				println "..Totlal Users.."+KPCommonPage.usersList
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UsersDisplayedIssue", "Users are not displayed on Manage User page.")
			}
		}

		def static final clickOperation = {browser, formData ->
			browser.delay(1500)
			if(browser.isDisplayed(USER_NAMES)){
				KPCommonPage.firstUserInList = browser.gettext(FIRST_USER)
				println "....First User in list is ....."+KPCommonPage.firstUserInList
				browser.click FIRST_USER

			}
		}


		def static final correctUserProfile = {browser, formData ->
			browser.delay(1500)
			def actName = browser.gettext(PROFILE_PAGE_NAME)
			KPCommonPage.profileEmailId = browser.gettext(PROFILE_EMAIL).trim()
			KPCommonPage.profilePassword = browser.gettext(PROFILE_PSWD).trim()
			if(actName.trim().equalsIgnoreCase(KPCommonPage.firstUserInList.trim())){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ProfileNameMismatchIssue", "Appearing some other's profile name")
			}
		}


		def static final removeUser = {browser, formData ->
			browser.delay(1500)
			//KPCommonPage.profileEmailId = browser.gettext(PROFILE_EMAIL)
			if(browser.isDisplayed(FIRST_USER_REMOVE)){
				browser.click FIRST_USER_REMOVE
				browser.delay(2000)
				browser.click CONFIRM_POPUP_YES
				browser.delay(1000)

			}

		}

		def static final loginAsRemovedUser = {browser, formData ->
			browser.delay(1500)
			if(browser.isDisplayed(LOGIN_USERNAME) && browser.isDisplayed(LOGIN_PSWD)){
				browser.populateField(LOGIN_USERNAME,KPCommonPage.profileEmailId)
				browser.populateField(LOGIN_PSWD,KPCommonPage.profilePassword)
				browser.click LOGIN_SUBMIT
				browser.delay(3000)
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserNameAndPasswordDisplayIssue", "User name and Password field is not appearing.")
			}


		}


		//To verify that the result displayed for the selected Sort By through filter are shown correctly
		def static final manageUserSortByFilter = { browser, formData ->
			def sortBy = KPCommonPage.manageUsersSortByData

			def allResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(USER_NAMES)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(3000)

					allResult = browser.getLists(USER_NAMES)
					if(sortBy.equalsIgnoreCase("Alphabetical Z-A")){
						def result = KPCommonPage.isSorted(allResult)
						if(!result){
							println "..PASS..Alphabetical Z-A "
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical Z-A is not Working.")
						}
					}else if(sortBy.equalsIgnoreCase("Alphabetical A-Z")){
						def result = KPCommonPage.isSorted(allResult)
						if(result){
							println "..PASS...Alphabetical A-Z."
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical Z-A is not Working.")
						}
					}

				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
		}





	}

}






