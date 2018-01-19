package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created By Bishnu Das
 */


final class OngoingTrainingPage extends WebPage {
	//Override
	def populateData = { browser, formKey, formData ->
		if(formKey.equals("searchInputs")){
			new OngoingPodsSearchForm().populateFields(browser, formData);
		}
	}


	def static getFirstPodName = {browser, formData ->
		new OngoingPodsSearchForm().getFirstPodName browser, formData
	}

	def static correctPodProgressPage = {browser, formData ->
		new OngoingPodsSearchForm().correctPodProgressPage browser, formData
	}

	static final class OngoingPodsSearchForm extends WebForm {

		//SearchPod form elements
		private static final def SEARCH_TEXT = "//input[@ng-model='searchValue.skills']"

		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def PODNAME_PODPROGRESS = "//div[@class='col-sm-6 col-md-6 mb_10']/p"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def OVERVIEW = "//a[text()='Overview']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"

		private static final def POD_NAME = "//div[@class='col-md-12 col-xs-12']/h3"

		private static final def ADD_TO_WISHLIST = "//div[@ng-hide='isListEmpty']/div[3]/div/span"

		private static final def FIRST_POD_NAME = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"

		private static final def WISHLISTED_POD = ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a"

		private static final def ALL_WISHLISTED_POD = "//a[@class='content-name job-title pointer title-name blue ng-binding']"

		private static final def FIELDS = [SEARCH_TEXT]// the error fields.
		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("OngoingPodsSearchForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						def searchInput = formData[i].trim()
						browser.populateField(FIELDS[i],formData[i].trim())
						KPCommonPage.podName = searchInput
					}

					browser.delay(1000)
				}
			}
			return outcome
		}


		//to get the first pod names from Ongoing pod list page in a list
		public static def getFirstPodName = {browser, formData ->
			def allResult
			def firstPodName
			browser.scrollToElement2(ELEMENT)
			browser.delay(3000)
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.delay(3000)
					allResult = browser.getLists(POD_LIST)
					firstPodName = allResult[0].trim()
					KPCommonPage.getFirstPodName = firstPodName

				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}

			return firstPodName
		}



		def static final correctPodProgressPage = {browser, formData ->
			if(browser.isDisplayed(PODNAME_PODPROGRESS) && KPCommonPage.getFirstPodName != null){
				def nameInPodProgressPage = browser.gettext(PODNAME_PODPROGRESS)
				if(KPCommonPage.getFirstPodName.equalsIgnoreCase(nameInPodProgressPage.trim())){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodNameMismatchIssue", "Pod name does not match in Pod progress details page.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodNameInPodprogressPageDisplayedIssue", "Pod name is not appearing in Pod progress details page")
			}


		}

	}
}