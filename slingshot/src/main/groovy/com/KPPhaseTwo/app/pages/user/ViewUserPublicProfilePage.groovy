package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Aditya on 14/11/2017
 */

final class ViewUserPublicProfilePage extends WebPage {

	//To verify image displayed in View Profile page
	def static uploadUserImgDisplay = { browser, formData ->
		println "------------------------------------------------------->>>>>>>>>>>"
		new ViewUserPublicProfilePageForm().uploadUserImgDisplay(browser,  formData)
	}

	static final class ViewUserPublicProfilePageForm extends WebForm {
		
		private static final def IMAGE_SOURCE = ".//img[@class='profile-pic']"

		//Verify image displayed in View Organization Profile page
		def static final uploadUserImgDisplay = { browser, formData ->
			println "------------------------------------------------------->>>>>>>>>>>"
			println "11"
			def srcViewUserProfilePic = browser.gettext(IMAGE_SOURCE, "src")
			println "srcViewUserProfilePic:::::::::::: "+srcViewUserProfilePic
			println "KPCommonPage.srcUserProfilePic::::::::::: "+KPCommonPage.srcUserProfilePic
			println "12"
			if((KPCommonPage.srcUserProfilePic!=null) && (srcViewUserProfilePic!=null)){
				println "13"
				if(srcViewUserProfilePic.equals(KPCommonPage.srcUserProfilePic)){
					println "14"
					return new SuccessOutcome()
				}else
					println "15"
				return KPCommonPage.returnFailureOutcome(browser, "srcUrlMismatch", "Image was not uploaded properly.")
			}else
				println "16"
			return KPCommonPage.returnFailureOutcome(browser, "nullSrcUrl", "Image has null src attribute.")
		}
	}
}

