package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Bishnu on 19/07/2017
 */

final class PostJobPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		new PostJobForm().populateFields(browser, formData);
	}
	
	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in PostJobPage")
		new PostJobForm().submit(browser, formData)
	}
	
	
	def isCorrectJob = {browser, formData ->
		new PostJobForm().isCorrectJob browser ,formData
	}
	
	static final class PostJobForm extends WebForm {

		//Job post form elements
		private static final def JOB_TITLE = "//input[@aria-label='Enter Job Title']"
		
		private static final def JOB_TITLE_LIST = "//md-virtual-repeat-container[contains(@class,'autocomplete')][1]//ul//span"

		private static final def NO_OF_POSITIONS = ".//input[@id='noOfPositions']"

		private static final def MIN_AGE = ".//input[@id='minAge']"
		
		private static final def MAX_AGE = ".//input[@id='maxAge']"
		
		private static final def MIN_EXPERIENCE = ".//*[@id='minExperience']"
		
		private static final def MAX_EXPERIENCE = ".//*[@id='maxExperience']"
		
		private static final def MIN_SALARY = ".//*[@id='minSalary']"
		
		private static final def MAX_SALARY = ".//*[@id='maxSalary']"
		
		private static final def INDUSTRY = "//input[@aria-label='Select Industry']"
		
		private static final def LOCATION = ".//*[@id='jobLocation']"
		
		private static final def LOCATION_LIST = ".//html/body/div[5][last()]/div[@class='pac-item']"
		
		//private static final def CITY_AUTOSELECT = "//div[@class='pac-container pac-logo'][2]/div"
		
		private static final def INDUSTRY_AUTOSELECT = "html/body/md-virtual-repeat-container[2]/div//li"
		
		private static final def JOB_TYPE = ".//select[@name='jobType']"
		
		private static final def JOB_LAST_DATE = ".//*[@id='jobLastDate']"
		
		private static final def JOB_POSTING_DATE = ".//*[@id='jobPostingDate']"
		
		private static final def JOB_EXPIRY_DATE = ".//*[@id='jobExpiryDate']"
		
		private static final def JOB_DATES= "//p[@class='input-group mb_0']/input"
		
		private static final def EDU_QUALIFICATION = ".//input[@name='multipleSelectEducation']"
		
		private static final def EDU_QUALIFICATION_AUTOSELECT = "//div[@class='autocomplete-list']/ul/li"
		
		private static final def SKILLS = ".//input[@name='multipleSelectSkills']"
		
		private static final def SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"
		
		private static final def PRE_CERTIFICATE = ".//input[@name='multipleSelectCertificates']"
		
		private static final def PRE_CERTIFICATE_LIST = "//div[@class='autocomplete-list']/ul/li"
		
		private static final def JOB_DESCRIPTION = ".//*[@id='jobDescription']"
		
		private static final def ADD_BUTTON = ".//button[text()='Add']"
		
		private static final def FIELDS = [JOB_TITLE, NO_OF_POSITIONS, MIN_AGE, MAX_AGE, MIN_EXPERIENCE,MAX_EXPERIENCE,MIN_SALARY,MAX_SALARY, INDUSTRY, LOCATION, JOB_TYPE, JOB_LAST_DATE, JOB_POSTING_DATE, JOB_EXPIRY_DATE, EDU_QUALIFICATION, SKILLS, PRE_CERTIFICATE, JOB_DESCRIPTION]
		
		// the error fields.
		private static final def FORM_ERROR = ".//span[@class='ng-binding ng-scope']"
		
		private static final def FIELD_ERROR_1 = ".//span[@class='error_message']"
		
		private static final def FIELD_ERROR_2 = ".//p[@class='error_message']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR_1,FIELD_ERROR_2]
		
		//error message map (Key-Value Pair)
		def static final PostJobPageErrorMessageMap = [
			jobtitle_req              :  'Select Job Title.',
			noofpositions_req         :  'No of positions is required.',
			minage_req                :  'Min age is required.',
			maxage_req                :  'Max age is required.',
			minexperience_req         :  'Min experience is required.',
			maxexperience_req         :  'Max experience is required.',
			minsalary_req             :  'Min salary is required.',
			maxsalary_req             :  'Max salary is required.',
			industry_req              :  'Industry is required',
			location_req              :  'Location is required.',
			jobtype_req               :  'Job Type is required',
			joblastdate_req           :  'Job last date is required.',
			jobpostdate_req           :  'Date of Job posting is required.',
			jobexpirydate_req         :  'Expiry date of Job posting required.',
			eduqualification_req      :  'Education Qualification is required', 
			skill_req                 :  'Skills is required',
			jobdesc_req               :  'Job Description is required.',
			lastAfterPosting_date     :  'Last date of application should be after Date of Job Posting',
			lastAfterExpiry_date      :  'Last date of application can\'t be after Expiry Date of Job Posting',
			postingBeforeExpiry_date  :  'Date of Job Posting should be before Expiry of Job Posting',
			jobpostingBeforeLast_date :  'Date of Job Posting should be before Last Date of Job Application',
			expiryAfterPosting_date   :  'Expiry of Job Posting should be after Date of Job Posting',
			expiryAfterLast_date      :  'Expiry of Job Posting should be after Last Date of job Application',
			minAgeGreaterMax          :  'Min age can\'t be greater than max age',
			minExpGreaterMax          :  'Min experience can\'t be greater than max experience',
			minSalGreaterMax          :  'Min salary can\'t be greater than max salary',
			success_postJob           :   'Job added successfully']  //can'+"'"+'t

		//To enter data
		def static final populateFields = { browser, formData ->
			
			println ("PostJobForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i <= FIELDS.size(); i++){
					if(FIELDS[i].equals(JOB_TITLE) && formData[i]!= ""){
						KPCommonPage.jobTitle = formData[i]
						browser.delay(2000)
						browser.populateField(JOB_TITLE,formData[i])
						browser.delay(2500)
						KPCommonPage.selectAutoComplete(browser, JOB_TITLE_LIST, formData[i].trim())
						browser.delay(2500)
						
					}
					/*if(FIELDS[i].equals(EDU_QUALIFICATION) && formData[i]!= ""){
						KPCommonPage.eduQualification = formData[i]
						browser.delay(2000)
						browser.populateField(EDU_QUALIFICATION,formData[i])
						browser.delay(2500)
						KPCommonPage.selectAutoComplete(browser, EDU_QUALIFICATION_AUTOSELECT, formData[i].trim())
						browser.delay(2500)
						
					}
					
					if(FIELDS[i].equals(SKILLS) && formData[i]!= ""){
						KPCommonPage.jobSkills = formData[i]
						browser.delay(2000)
						browser.populateField(SKILLS,formData[i])
						browser.delay(2500)
						KPCommonPage.selectAutoComplete(browser, SKILLS_LIST, formData[i].trim())
						browser.delay(2500)
						
					}
					
					if(FIELDS[i].equals(PRE_CERTIFICATE) && formData[i]!= ""){
						KPCommonPage.prefferedCertificates = formData[i]
						browser.delay(2000)
						browser.populateField(PRE_CERTIFICATE,formData[i])
						browser.delay(2500)
						KPCommonPage.selectAutoComplete(browser, PRE_CERTIFICATE_LIST, formData[i].trim())
						browser.delay(2500)
						
					}*/
					
					if(FIELDS[i].equals(LOCATION) && formData[i]!= ""){
						KPCommonPage.city = formData[i]
						browser.scrollToElement(browser.getElement(Browser.XPATH, LOCATION))
						browser.delay(2000)
						browser.populateField(LOCATION,formData[i])
						browser.delay(5500)
						browser.pressDownArrow(LOCATION)
						browser.delay(2000)
						browser.pressEnter(LOCATION)
						//KPCommonPage.selectAutoComplete(browser, LOCATION_LIST, formData[i].trim())
						browser.delay(2000)
						
					}else if(FIELDS[i].equals(INDUSTRY)){
						if(formData[i].equals("")){
							browser.pressTab(FIELDS[i])
						}else{
							KPCommonPage.jobIndustry = formData[i]
							browser.delay(2000)
							browser.populateField(INDUSTRY,formData[i])
							browser.delay(2000)
							KPCommonPage.selectAutoComplete(browser, INDUSTRY_AUTOSELECT, formData[i].trim())
							browser.delay(2000)
						}
					}else{
						def tagName = browser.getTagName(FIELDS[i])
						browser.scrollToElement2(FIELDS[i])
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
					}
				}
			}
			return outcome
		}
		
		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			
			def actualValidationMsg = submitForm browser, FIELDS, ADD_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, PostJobPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}
		
		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			
			browser.click submitButton // submit the form.
			browser.scrollToElement2(JOB_TITLE)
			browser.delay(1000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
		
		
		
		
		
		
		
		def static isCorrectJob = {browser, formData ->
			browser.delay(3000)
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}
}
