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
		new ViewUserPublicProfilePageForm().uploadUserImgDisplay(browser,  formData)
	}

	def static educationDetailsMatch = { browser,formData ->
		new ViewUserPublicProfilePageForm().educationDetailsMatch(browser,formData)
	}

	def static workExperienceDetailsMatch = { browser,formData ->
		new ViewUserPublicProfilePageForm().workExperienceDetailsMatch(browser,formData)
	}

	def static ifWorkExperienceDisplayed = { browser, formData ->
		println "Inside User Manager::::::::::::::>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
		new ViewUserPublicProfilePageForm().ifWorkExperienceDisplayed browser,  formData
	}
	
	
	def static certificateMatch = { browser,formData ->
		new ViewUserPublicProfilePageForm().certificateMatch(browser,formData)
	}


	static final class ViewUserPublicProfilePageForm extends WebForm {

		private static final def IMAGE_SOURCE            = ".//img[@class='profile-pic']"

		private static final def EDU_DETAILS             = ".//div[@aria-hidden='false']//p"

		private static final def WORKEXPERIENCE_DETAILS  = ".//div[@ng-repeat='userProfiles in userProfile.kpUserExperienceJSON ']//p"

		private static final def DOCNAME_VIEWPROFILE     = ".//label[@name='docName']"
		
		private static final def SCROLL_TO_VIEW_DOC      = ".//ul[@class='footer-secondary-list']/li/h4[@class='heading']"
		
		
		//Verify image displayed in View Organization Profile page
		def static final uploadUserImgDisplay = { browser, formData ->
			def srcImagePath = []
			def srcViewUserProfilePic = browser.gettext(IMAGE_SOURCE, "src")
			srcImagePath = srcViewUserProfilePic.split("\\?")
			if((KPCommonPage.srcUserProfilePic!=null) && (srcImagePath[0]!=null)){
				if(srcImagePath[0].equals(KPCommonPage.srcUserProfilePic)){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "srcUrlMismatch", "Image was not uploaded properly.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "nullSrcUrl", "Image has null src attribute.")
		}

		def static educationDetailsMatch = { browser, formData ->
			def result = false
			def eduDetailList = []
			def sortedFormData = []
			def sortededuDetailList = []
			def splittedValue
			browser.scrollToElement(browser.getElement(Browser.XPATH, ".//a[@href='#/dashboard']"))
			browser.delay(1500)
			def userEduDetails= browser.getLists(EDU_DETAILS)
			browser.delay(2000)
			for(int i=0;i<userEduDetails.size();i++){
				if(userEduDetails[i] != null && userEduDetails[i].size() > 0 && userEduDetails[i].charAt(userEduDetails[i].size()-1)=='.') {
					userEduDetails[i] = userEduDetails[i].substring(0, userEduDetails[i].size()-1).trim()
				}
				userEduDetails[i] = userEduDetails[i].trim();
				if((userEduDetails[i]).contains("-")){
					splittedValue = userEduDetails[i].split("-")
					eduDetailList.add(splittedValue[0].trim())
					eduDetailList.add(splittedValue[1].trim())
				}else{
					eduDetailList.add(userEduDetails[i].trim())
				}
			}
			if(formData.size()==(eduDetailList.size())){
				sortedFormData = formData.sort()
				sortededuDetailList = eduDetailList.sort()
				for(int k=0;k<sortededuDetailList.size();k++){
					if(sortedFormData[k].equalsIgnoreCase(sortededuDetailList[k])){
						result = true
					}
				}
				if(result){
					println "matched"
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "EducationDetailsMismatchIssue", "Education details on View profile page do not matchwith the data provided")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "EducationDetailsSizeMismatchIssue", "Education details count on View profile page do not matchwith the data provided")
			}
		}

		def static final workExperienceDetailsMatch = { browser, formData ->
			println "Inside WORKEXPERIENCE"
			def expectedWorkExprience = []
			def actualWorkExprience = []
			println "0"
			if(browser.isDisplayed(WORKEXPERIENCE_DETAILS)){
				def workExperienceOnViewProfile = browser.getLists(WORKEXPERIENCE_DETAILS)
				println "workExperienceOnViewProfile------->"+workExperienceOnViewProfile
				def workExperienceOnViewProfileList = []
				def splittedValue
				for(int i=0;i<workExperienceOnViewProfile.size();i++){
					println "1"
					if(workExperienceOnViewProfile[i] != null && workExperienceOnViewProfile[i].size() > 0 && workExperienceOnViewProfile[i].charAt(workExperienceOnViewProfile[i].size()-1)=='.'){
						println "2"
						workExperienceOnViewProfile[i] = workExperienceOnViewProfile[i].substring(0, workExperienceOnViewProfile[i].size()-1).trim()
						println "3"
					}
					println "4"
					workExperienceOnViewProfile[i] = workExperienceOnViewProfile[i].trim();
					println "5"
					if((workExperienceOnViewProfile[i]).contains("-")){
						println "6"
						splittedValue = workExperienceOnViewProfile[i].split("-")
						println "splittedValue:::::::::::::::::::::------> "+splittedValue
						println "7"
						println "splittedValue:::::::::::::::::::::------> "+splittedValue.size()
						for(int j=0;j<splittedValue.size();j++){
							println "34343"
							if(splittedValue[j].matches(".*\\d+.*")){
								println "HAS DIGITS"
								println "splittedValue[j]::::::::: "+splittedValue[j]
								def value = splittedValue[j].split(" ")
								println "value.size()::::::::::: "+value.size()
								for(int k=0;k<value.size();k++){
									println "VALUE OF K is "+k+" and"+" value[k] is "+value[k]
									if(value[k]!=""){
										workExperienceOnViewProfileList.add(value[k].trim())
									}
								}
							}else {
								println "DOES NOT HAVE DIGIT"
								workExperienceOnViewProfileList.add(splittedValue[j].trim())
							}
						}
					}else{
						println "10"
						workExperienceOnViewProfileList.add(workExperienceOnViewProfile[i].trim())
						println "11"
					}
				}
				println "workExperienceOnViewProfileList :::::------> "+workExperienceOnViewProfileList
				println "KPCommonPage.WorkExperienceDetails.size() :: "+KPCommonPage.WorkExperienceDetails
				println "KPCommonPage.WorkExperienceDetails.size() :: "+KPCommonPage.WorkExperienceDetails.size()
				println "workExperienceOnViewProfileList.size()    :: "+workExperienceOnViewProfileList.size()
				if(KPCommonPage.WorkExperienceDetails.size()== workExperienceOnViewProfileList.size()){
					println "12"
					expectedWorkExprience = KPCommonPage.WorkExperienceDetails.sort()
					println "13"
					actualWorkExprience = workExperienceOnViewProfileList.sort()
					println "14"
					for(int k=0;k<actualWorkExprience.size();k++){
						println "15"
						if(expectedWorkExprience[k].equalsIgnoreCase(actualWorkExprience[k])){
							println "16"
							println "The Work Experience matched"
						}
					}
				}
			}else {
				println "The Work Experience details were not added"
			}
		}

		def static final ifWorkExperienceDisplayed = { browser, formData ->
			println "HELLLLLLLLLOOOOOOOOOOOOOOOOO"
			println "browser.isDisplayed(WORKEXPERIENCE_DETAILS):::::::: "+browser.isDisplayed(WORKEXPERIENCE_DETAILS)
			if(browser.isDisplayed(WORKEXPERIENCE_DETAILS) == null){
				println"UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU"
				return new SuccessOutcome()
			}else{

			}
		}

		def static certificateMatch = { browser,formData ->
			browser.scrollToElement2(SCROLL_TO_VIEW_DOC)
			browser.delay(200)
			def docName = browser.getLists(DOCNAME_VIEWPROFILE)
			for(int i=0;i<docName.size();i++){
				if(docName[i].equalsIgnoreCase(KPCommonPage.docName[i])){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "DocumentNameMismatchIssue", "The document name does not match on View Public profile page")
				}
			}
		}

	}
}

