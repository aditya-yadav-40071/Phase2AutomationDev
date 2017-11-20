package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Krithika on 05/06/2017
 */

final class BuyAPodPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new BuyAPodForm().populateFields(browser, formData);
	}
	
	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in Buy a pod page")
		new BuyAPodForm().submit(browser, formData)
	}
	
	//To verify the amount of the pod from pod details page
	def static equalAmountStatus = {browser, formData ->
		new BuyAPodForm().equalAmountStatus browser, formData
	}
	
	//To verify the number of users
	def static noOfUsersEntered = {browser, formData ->
		new BuyAPodForm().noOfUsersEntered browser, formData
	}
	
	static final class BuyAPodForm extends WebForm {

		//Buy a pod page form elements
		private static final def POD_LIST_NAMES = "//div[@class='clearfix content individual-pod ng-scope']/div/div/div/h3/a"
		
		private static final def NO_OF_USERS = "//input[@type='number']"
		
		private static final def PURCHASE_REQUEST = "//input[@value='Purchase Request']"
		
		private static final def SINGLE_AMOUNT = "//div[@class='row']/div/div[3]/div[2]/span"
		
		private static final def USER_TOTAL_EXP = "//div[@class='row']/div[2]/span"
		
		private static final def FIELDS = [NO_OF_USERS]
		
		private static final def SUCCESS_MESSAGE = ".//span[@class='ng-binding ng-scope']"
		
		private static final def ERROR_MESSAGE_FIELDS = [SUCCESS_MESSAGE]
		
		private static final def buyAPodErrorMessageMap = [buyPodRequest_Success:'Successfully submitted the request, Soon you will get purchase detials to your mail',
														   invalidNo_Failure: 'Enter Valid Number of Learner']
		

		//To enter data
		def static final populateFields = { browser, formData ->
		println ("BuyAPodForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				outcome = WebForm.enterData(browser, formData, FIELDS, PURCHASE_REQUEST, WAIT_REQ_FIELDS)
				println "Entered"
			} 
			return outcome
		}
		
		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, PURCHASE_REQUEST, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, buyAPodErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}
		
		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(5000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
		
		//To verify the amount of the pod from pod details page
		def static final equalAmountStatus = {browser, formData ->
			def amountOnBuyPage
			amountOnBuyPage = (browser.gettext(SINGLE_AMOUNT).substring(1)).toFloat()
			if(amountOnBuyPage == KPCommonPage.pod_amount){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "amountNotEqual", "Amount displayed not equal.")
		}
		
		//To verify the number of users entered for a pod
		def static final noOfUsersEntered = {browser, formData ->
			def amountCalc = browser.gettext(USER_TOTAL_EXP)
			println amountCalc
			def numberOfUsers = (amountCalc.split("\\s+"))[2].toInteger()
			println "numberOfUsers:"+numberOfUsers
			def numberEntered = Integer.parseInt(formData[0])
			if(numberOfUsers == numberEntered){
				println "Same"
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "mismatchInNo", "No of users entered not similar to the calculation.")
		}
		
	}
}
