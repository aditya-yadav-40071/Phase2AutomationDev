package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Krithika on 22/06/2017
 */

final class PodDetailsPage extends WebPage {
	
	//To verify the logged in user is displayed
	def static getCostOfPod = {browser, formData ->
		new PodDetailsForm().getCostOfPod browser, formData
	}
	
	//To verify buying a pod
	def static podBuyRequest = {browser, formData ->
		new PodDetailsForm().podBuyRequest browser, formData
	}
	
	static final class PodDetailsForm extends WebForm {

		//Pod details form elements
		private static final def OVERVIEW_TAB = "//a[contains(text(), 'Overview')]"
		
		private static final def POD_AMOUNT = "//div/span[@ng-if='courseDetail.amount']"
		
		private static final def BUY_BUTTON = "//div[@ng-if='buyCourse']/button"
		
		//To verify the logged in user is displayed
		def static final getCostOfPod = {browser, formData ->
			if(browser.isDisplayed(OVERVIEW_TAB)){
				browser.click OVERVIEW_TAB
			}
			if(browser.isDisplayed(POD_AMOUNT)){
				browser.scrollToElement2(POD_AMOUNT)
				def amount_text = browser.gettext(POD_AMOUNT)
				KPCommonPage.pod_amount = ((amount_text.split("\\s+"))[0]).toFloat()
				println "KPCommonPage.pod_amount:"+KPCommonPage.pod_amount
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "noAmountDisplayed", "Amount not displayed.")
		}
		
		//To verify if the request is sent for buying a pod
		def static final podBuyRequest = {browser, formData ->
			if(!browser.checkEnabled(BUY_BUTTON)){
				println "Bought"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "podNotBought", "Request to buy not sent.")
		}
	}
}
