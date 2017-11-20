package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Krithika Bafna on 19/07/2017
 */

final class SalesAdminLicenseDetailsPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new SalesAdminLicenseDetailsForm().populateFields(browser, formData);
	}
	
	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in LoginPage")
		new SalesAdminLicenseDetailsForm().submit(browser, formData)
	}
	
	//To verify the amount of the pod from pod details page
	def static clickPodReview = {browser, formData ->
		new SalesAdminLicenseDetailsForm().clickPodReview browser, formData
	}
	static final class SalesAdminLicenseDetailsForm extends WebForm {

		//Sales Admin License Details Form Elements
		private static final def POD_REVIEW_TO_CLICK = "//tr[td[1][text()='Krithika'] and td[2][text()='ADMIN'] and td[3][text()='Assistant Elevator Installer']]/td/a"

		//To perform action of clicking on the pod for which the pending request is accepted/rejected
		def static final clickPodReview = {browser, formData ->
			
			/*println KPCommonPage.podToBuy_Entry
			println KPCommonPage.typeOfUser
			println KPCommonPage.firstNameAdmin*/
		}
	}
}
