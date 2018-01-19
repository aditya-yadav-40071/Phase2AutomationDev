package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

final class ParticipantListPage extends WebPage {

	def static ifUserIsEnrolled = { browser,formData ->
		new ParticipantListPageForm().ifUserIsEnrolled(browser, formData);
	}

	def static learnerListSorted = { browser,formData ->
		new ParticipantListPageForm().learnerListSorted(browser, formData);
	}

	def static searchUserByFilter = { browser, formData ->
		new ParticipantListPageForm().searchUserByFilter browser, formData
	}

	def static enrollUserToAnotherPod = { browser, formData->
		new ParticipantListPageForm().enrollUserToAnotherPod browser, formData
	}

	static final class ParticipantListPageForm extends WebForm {

		//Login form elements
		private static final def  PARTICIPANT_SEARCH        = ".//div[@ng-hide='isListEmpty && hideSearch']//input[@ng-model='searchValue']"

		private static final def  SEARCH_ARROW              = ".//button[@ng-click='searchAddParticipantsList(searchValue) ']/i"

		private static final def  USER_EMAIL_ID             = ".//h4[@class='ng-binding']"

		private static final def  LEARNER_NAMES             = ".//span[@class='capitalize ng-binding']"

		private static final def  USER_ENROLLED_STATUS      = ".//md-checkbox[@role='checkbox'][@aria-disabled='true']"

		private static final def  SORT_BY_RELEVENCE         = ".//*[@id='main_page']/div[2]/div/div/div[3]/div/div[1]/div/select"

		private static final def  FILTER_BY_ENROLLED_STATUS = ".//select[@placeholder='Select Enrolled/Un Enrolled'][@ng-change='changeFilter(filterBy,siteid)']"

		private static final def  FILTER_BY_SITES           = ".//select[@placeholder='Select Site'][@ng-change='changeFilter(filterBy,siteid)']"

		private static final def  ENROLL_BUTTON             = ".//*[@id='main_page']//button[@ng-click='enrollUserToCourse()']"

		private static final def FIFTY_RESULTS              = ".//label[@for='item5']"

		private static final def ALL_CHECKBOXES             = ".//md-checkbox[@aria-label='Checkbox No Ink']/div[@class='md-container']"

		private static final def NEXT_BTN                   = ".//li[@ng-class='{disabled: noNext()||ngDisabled}']"

		private static final def SEARCH_PART_BTN            = "//button[@ng-click='searchAddParticipantsList(searchValue) ']/i[@class='fa fa-arrow-right blue']"

		// the error fields.
		private static final def FORM_ERROR                 = ".//div[@ng-transclude='']/span"

		private static final def ERROR_MESSAGE_FIELDS       = [FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final participantListPageErrorMessageMap = [
			enrollFail             :'Failed to enroll new Learners, You have already Enrolled num Learners',
			enrollment_success     :'Learner Enrolled to Pod Successfully',
			enrollToPod_success    :'User Enrolled Successfully',
			learnerAdd_success     :'User added Successfully'
		]

		//To enter data
		def static ifUserIsEnrolled = { browser, formData ->
			browser.populateField(PARTICIPANT_SEARCH,KPCommonPage.invitedUserEmailId)
			browser.click(SEARCH_ARROW)
			browser.delay(1000)
			def allUserEmailId = browser.gettext(USER_EMAIL_ID)
			if(KPCommonPage.invitedUserEmailId.equalsIgnoreCase(allUserEmailId.split(" ")[1])){
				def disabledStatus = browser.gettext(USER_ENROLLED_STATUS,"aria-disabled")
				def checkedStatus = browser.gettext(USER_ENROLLED_STATUS,"aria-checked")
				if(disabledStatus && checkedStatus){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "UserEnrollmentIssue", "User was not enrolled to the pod")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserCreationIssue", "The user was not created")
			}
		}

		def static learnerListSorted = { browser, formData ->
			def allLearnerName
			browser.delay(500)
			browser.selectDropdownValue(SORT_BY_RELEVENCE,formData[0])
			browser.delay(1000)
			allLearnerName = browser.getLists(LEARNER_NAMES)
			def sortResult = KPCommonPage.isSorted(allLearnerName)
			if(sortResult){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "SortingFilterIssue", "Sorting filter is not working properly")
			}
		}

		def static searchUserByFilter = { browser, formData ->
			def result
			result = ParticipantListPageForm.selectFilter(browser,formData[0])
			if(formData[0].equalsIgnoreCase("Enrolled Students") && result == true){
				return new SuccessOutcome()
			} else if(formData[0].equalsIgnoreCase("UnEnrolled Students")  && result == false){
				return new SuccessOutcome()
			} else{
				return KPCommonPage.returnFailureOutcome(browser, "EnrollingFilterSearchIssue", "Enrolled/UnEnrolled filter is not working properly")
			}
		}

		def static selectFilter(def browser, def itemToSelect){
			def result = false
			browser.selectDropdownValue(FILTER_BY_ENROLLED_STATUS,itemToSelect)
			browser.delay(600)
			browser.selectDropdownValue(FILTER_BY_SITES,KPCommonPage.invitedUserSiteName)
			def learnerName = browser.getLists(LEARNER_NAMES)
			def allLearnerEmailId = browser.getLists(USER_EMAIL_ID)
			for(int i=0;i<learnerName.size();i++){
				allLearnerEmailId[i] = allLearnerEmailId[i].split(" ")[1]
				if(learnerName[i].contains(KPCommonPage.invitedUserFirstName) && allLearnerEmailId[i].equalsIgnoreCase(KPCommonPage.invitedUserEmailId)){
					result = true
					break;
				}
			}
			return result
		}

		def static enrollUserToAnotherPod = { browser, formData ->
			browser.delay(500)
			def userEmailList = []
			def actMessage
			def result
			browser.populateField(PARTICIPANT_SEARCH,KPCommonPage.invitedUserFirstName)
			browser.click(SEARCH_PART_BTN)
			browser.delay(4000)
			browser.click(FIFTY_RESULTS)
			browser.delay(3000)
			def checkBoxToBeChecked = browser.getListElements(ALL_CHECKBOXES)
			def allUsersNameOnPage = browser.getLists(LEARNER_NAMES)
			def allUsersEmailOnPage = browser.getLists(USER_EMAIL_ID)
			for(int j=0;j<allUsersEmailOnPage.size();j++){
				userEmailList.add(allUsersEmailOnPage[j].split(" ")[1].trim())
			}
			result = new ParticipantListPageForm().enrollToPod(browser,allUsersNameOnPage,userEmailList,checkBoxToBeChecked)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "UserMismatchIssue", "Username or Email ID did not match")
			}
		}
	}

	def static enrollToPod(def browser,def allUsersNameOnList,def userEmailList,def checkBoxList){
		def outcome = false
		def actMessage
		def userToEnroll = KPCommonPage.invitedUserEmailId
		for(int i=0;i<allUsersNameOnList.size();i++){
			if(allUsersNameOnList[i].contains(KPCommonPage.invitedUserFirstName) && userEmailList[i].equalsIgnoreCase(userToEnroll)){
				browser.scrollToElement(checkBoxList[i])
				browser.clickElement(checkBoxList[i])
				browser.delay(500)
				if(browser.checkEnabled(ParticipantListPageForm.ENROLL_BUTTON)){
					browser.click(ParticipantListPageForm.ENROLL_BUTTON)
					browser.delay(1000)
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EnrollButtonIssue", "Enrolled button is not enabled")
				}
				actMessage = browser.gettext(ParticipantListPageForm.FORM_ERROR)
				if(actMessage!=null && actMessage.equalsIgnoreCase(ParticipantListPageForm.participantListPageErrorMessageMap.get('enrollToPod_success'))){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SuccessMessageIssue", "Success message was not displayed")
				}
				outcome = true
				return outcome
			}
		}
		return outcome
	}
}

