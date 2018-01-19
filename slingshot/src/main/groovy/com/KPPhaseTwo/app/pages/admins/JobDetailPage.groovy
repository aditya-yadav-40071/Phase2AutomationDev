package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage


final class JobDetailPage extends WebPage {

	def static jobDetails = { browser, formData ->
		new JobDetailForm().jobDetails browser ,formData
	}

	static final class JobDetailForm extends WebForm {
		
		private static final def JOB_LOCATION          = ".//div[@class='col-xs-12 col-md-12 smallText created-by-label']/span[@class='pull-left ng-binding']"

		private static final def JOB_OPENINGS          = ".//div[@class='col-xs-12 col-md-4 smallText created-by-label ng-binding']"
		
		private static final def JOB_DESCRIPTION       = ".//span[@class='job-description smallText ng-binding ng-scope']"
		
		private static final def EXP_AND_COMP_LIST     = ".//div[@class='col-xs-12 col-md-4 smallText created-by-label']/span"
		
		private static final def ALL_JOBDATES          = ".//div[@class='col-xs-12 col-sm-4 col-md-4 certified-by smallText ng-binding']"
		
		private static final def OTHER_JOB_DETAILS     = ".//div[@class='col-xs-12 col-md-9 text-justify content-description smallText']"
		
		def static jobDetails = { browser, formData ->
			browser.delay(1000)
			def tempList = []
			def actualJobDetail = []
			browser.scrollToElement2("//a[text()='Overview']")
			browser.delay(1000)
			tempList.add(browser.gettext(JOB_LOCATION))
			tempList.add(browser.gettext(JOB_OPENINGS))
			tempList.add(browser.gettext(JOB_DESCRIPTION))
			tempList.addAll(browser.getLists(EXP_AND_COMP_LIST))
			tempList.addAll(browser.getLists(ALL_JOBDATES))
			tempList.addAll(browser.getLists(OTHER_JOB_DETAILS))
			for(int i=0;i<tempList.size();i++){
				if(tempList[i].contains(":") && tempList[i].contains("-")){
					def temp = tempList[i].split(":")[1].trim()
					temp = temp.split("-")
					for(int j=0;j<temp.size();j++){
						actualJobDetail.add(temp[j].trim())
					}
				}else if(tempList[i].contains(":") && !tempList[i].contains("-")){
					actualJobDetail.add(tempList[i].split(":")[1].trim())
				}else if(tempList[i].contains(",")){
					def data = tempList[i].split(",")
					for(int k=0;k<data.size();k++){
						actualJobDetail.add(data[k].trim())
					}
				}else{
					actualJobDetail.add(tempList[i].trim())
				}
			}
			if(actualJobDetail?.sort(false) == KPCommonPage.jobDetailList?.sort(false)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "JobDetailsMismatchIssue", "The job details do not match")
			}
		}
	}
}

