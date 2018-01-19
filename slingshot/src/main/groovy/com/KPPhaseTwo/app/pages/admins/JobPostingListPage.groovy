
package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 03-01-2018
 */

final class JobPostingListPage extends WebPage {

	def static correctJobDisplayed = { browser, formData ->
		new JobPostingListForm().correctJobDisplayed browser ,formData
	}

	def static clickOnFirstJob = { browser,formData->
		new JobPostingListForm().clickOnFirstJob browser ,formData
	}


	static final class JobPostingListForm extends WebForm {

		private static final def SORT_BY_FILTER          = ".//select[@ng-model='functionCall.sortBy']"

		private static final def JOBPOSTING_LIST         = ".//h3[@class='content-name job-title blue pointer title-name blue capitalize mb10 ng-binding']"

		private static final def JOBSEARCH_TEXTBOX       = ".//input[@class='form-control pl_30 pr_30 ng-pristine ng-untouched ng-valid'][@placeholder='Search']"

		def static correctJobDisplayed = { browser, formData ->
			def result = new JobPostingListForm().getFirstJobName(browser)
			if(result){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobPostIssue", "Job posted is not at the first position")
			}
		}

		def static clickOnFirstJob = { browser,formData ->
			browser.delay(2000)
			def result = false
			def jobList = browser.getLists(JOBPOSTING_LIST)
			def jobListElements = getListElements(JOBPOSTING_LIST)
			for(int i=0;i<jobList.size();i++){
				if(jobList.equalsIgnoreCase(KPCommonPage.jobTitle)){
					browser.clickElement(jobListElements[i])
					result =true
					break
				}
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "JobClickIssue", "Unable to click on the job link")
				}
			}
		}

		def static getFirstJobName(def browser){
			browser.delay(2000)
			def jobList = browser.getLists(JOBPOSTING_LIST)
			browser.delay(2000)
			browser.selectDropdownValue(SORT_BY_FILTER,"Latest")
			browser.delay(4000)
			for(int i=0;i<jobList.size();i++){
				if(browser.isDisplayed(JOBPOSTING_LIST)){
					if(jobList[i].trim().equalsIgnoreCase(KPCommonPage.jobTitle)){
						return true
					}else{
						return false
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "JonPostListIssue", "Job list was not displayed")
				}
			}
		}
	}
}


