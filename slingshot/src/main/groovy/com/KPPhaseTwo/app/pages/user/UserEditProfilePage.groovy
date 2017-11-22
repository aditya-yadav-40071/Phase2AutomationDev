package com.KPPhaseTwo.app.pages.user

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Aditya on 06/04/2017
 */


final class UserEditProfilePage extends WebPage {

	//To populate the datas
	def populateData = { browser, formKey, formData ->
		println "===========Inside Populate Data==========="
		if(formKey.equalsIgnoreCase("basicInformation")) {
			new BasicInformationForm().populateFields(browser, formData)
		} else if(formKey.equalsIgnoreCase("eductionQualification")) {
			println ":::::::::::::Inside form key populate fields::::::>>>>>>>>>>>>>>>"
			new EductionQualificationForm().populateFields(browser, formData)
		}else if(formKey.equalsIgnoreCase("workExperience")){
			println "***********Entered in Work Experience***************"
			new WorkExperienceForm().populateFields(browser, formData)
		}else if(formKey.equalsIgnoreCase("skills")){
			println "***********Entered in SKILLS***************"
			new SkillsForm().populateFields(browser, formData)
		}else if(formKey.equalsIgnoreCase("certificate")){
			println "***********Entered in CERTIFICATES***************"
			new CertificateForm().populateFields(browser, formData)
		}
	}

	//Override
	def submit = { browser, formKey, formData ->
		if(formKey.equalsIgnoreCase("basicInformation")) {
			new BasicInformationForm().submit(browser, formData)
		} else if(formKey.equalsIgnoreCase("eductionQualification")) {
			new EductionQualificationForm().submit(browser, formData)
		} else if(formKey.equalsIgnoreCase("workExperience")){
			new WorkExperienceForm().submit(browser, formData)
		} else if(formKey.equalsIgnoreCase("skills")){
			new SkillsForm().submit(browser, formData)
		}else if(formKey.equalsIgnoreCase("certificate")){
			new CertificateForm().submit(browser, formData)
		}
	}

	def static uploadProfilepic = { browser,formData ->
		new BasicInformationForm().uploadProfilepic(browser,formData)
	}

	def static skillsDisplayed = { browser,formData ->
		new SkillsForm().skillsDisplayed(browser,formData)
	}

	def static deleteExistingSkills = { browser,formData ->
		new SkillsForm().deleteExistingSkills(browser,formData)
	}

	def static skillsRetained = { browser,formData ->
		new SkillsForm().skillsRetained(browser,formData)
	}

	def static removeASkill = { browser, formData ->
		new SkillsForm().removeASkill(browser,formData)
	}

	def static skillsRemoved = { browser, formData ->
		new SkillsForm().skillsRemoved(browser,formData)
	}



	static final class BasicInformationForm extends WebForm {

		//CompanyRegistration form elements

		private static final def FIRST_NAME         = ".//div[@class='basic-info-container ']//input[@id='fname']"

		private static final def MIDDLE_NAME        = ".//div[@class='basic-info-container ']//input[@id='mname']"

		private static final def LAST_NAME          = ".//div[@class='basic-info-container ']//input[@id='lname']"

		private static final def GENDER             = ".//div[@class='basic-info-container ']//select[@id='gender_new']"

		private static final def CAL_BTN            =  ".//div[@class='basic-info-container ']//button[@ng-click='opendobcalendar()']"

		private static final def SELECT_DOB         = ".//*[@id='ui-datepicker-div']/table/tbody/tr/td/a"

		private static final def MARITAL_STATUS     = ".//div[@class='basic-info-container ']//select[@name='marital_new']"

		private static final def ADD_LINE_1         = ".//div[@class='basic-info-container ']//input[@id='address1']"

		private static final def ADD_LINE_2         = ".//div[@class='basic-info-container ']//input[@id='address2']"

		private static final def ADD_LINE_3         = ".//div[@class='basic-info-container ']//input[@id='address3']"

		private static final def CITY               = ".//div[@class='basic-info-container ']//input[@id='city']"

		private static final def CITY_AUTOSELCT     = ".//div[@class='pac-container pac-logo'][last()]/descendant::span[@class='pac-matched']"

		private static final def PIN_CODE           = ".//div[@class='basic-info-container ']//input[@name='pincode']"

		private static final def EMAIL_ID           = ".//div[@class='basic-info-container ']//input[@id='emailId']"

		private static final def MOBILE_NUM         = ".//div[@class='basic-info-container ']//input[@id='mobileno']"

		private static final def SEND_OTP_BTN       = ".//div[@class='col-md-3']/button[@class='full-width button-secondary btn-small']"

		private static final def RESEND_OTP         = ".//div[@class='col-md-3']/h4[@class='col-md-12 link-secondary pt_13']"

		private static final def OTP_TEXTBOX        = ".//div[@class='basic-info-container ']//input[@id='otp']"

		private static final def UPDATE_BUTTON      = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def UPLOAD_PROFILE_IMAGE_LINK  = ".//*[@id='edit_profile']/div[2]/label/span[1]"

		private static final def CHANGE_PROFILE_IMAGE_LINK = ".//span[contains(text(), 'Change Image')]"

		private static final def UPLOAD_PROFILE_IMAGE_BUTTON = ".//button[@id='upload_image']"

		private static final def UPLOAD_IMAGE_SUBMIT = ".//*[@id='btnCrop']"

		private static final def SUCCESS_MESSAGE  = ".//*[@id='main_page']/div[1]/div/span"

		private static final def FIELDS = [FIRST_NAME, MIDDLE_NAME, LAST_NAME, GENDER, CAL_BTN, MARITAL_STATUS, ADD_LINE_1, ADD_LINE_2, ADD_LINE_3, CITY, PIN_CODE, EMAIL_ID, MOBILE_NUM]

		// the error fields.
		private static final def FORM_ERROR = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def FIELD_ERROR_1 = ".//span[@class='error_message']"

		private static final def FIELD_ERROR_2 = ".//span[@class='error_message ng-hide']/span"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final BasicInformationErrorMessageMap = [
			fname_req            :  'First name is required.',
			age_req              :  'Age is required.',
			invalid_age          :  'Enter a valid age',
			gender_req           :  'Select Gender.',
			dob_req              :  'Date Of Birth is required.',
			maritalstatus_req    :  'Select Marital Status.',
			addline1_req         :  'Address Line 1 is required.',
			city_req             :  'City Name is required.',
			pin_req              :  'Pin code is required',
			invalid_pin          :  'Pin code should be of 6 digits',
			emailid_req          :  'Email ID is required.',
			invalid_emailid      :  'Not a valid Email ID',
			emailid_exists       :  'Email ID already exists',
			mobile_req           :  'Mobile Number is required',
			mobile_exist         :  'Mobile Number already exists',
			invalid_mobile       :  'Enter a valid Mobile Number',
			startgreater_endtime :  'Start Time Period cannot be greater than End Time Period',
			startend_periodsame  :  'Start Time Period cannot be same as End Time Period',
			invalidimg_file	     :  'Not a valid file please upload supported file ex: doc, docx, pdf, rtf, jpeg, png ,jpg',
			invalid_size         :  'File size should less then 2 MB.',
			invalid_DOB 		 :  'Enter a valid Date Of Birth.',
			update_success 		 :  'User data updated successfully',
			profileUploadSuccess :  'Profile Pic uploaded successfully']

		//To enter data
		def static final populateFields = { browser, formData ->

			println ("BasicInformationForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i <= FIELDS.size()-1; i++){
					def tagName = browser.getTagName(FIELDS[i])
					println "The tagname is for field "+FIELDS[i]+ "and "+tagName
					if(FIELDS[i].equals(CAL_BTN) ){
						if(formData[i] !=""){
							//							WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
							browser.click(CAL_BTN)
							KPCommonPage.datePicker(browser,formData[i])
						} else {
							browser.click FIELDS[i]
						}
					} else if(FIELDS[i].equals(CITY) ){
						browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS[i]))
						browser.delay(2000)
						if(formData[i] != ""){
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(3000)
							KPCommonPage.selectAutoComplete(browser, CITY_AUTOSELCT, formData[i].trim())
						} else{
							browser.populateField(FIELDS[i], formData[i])
							browser.delay(2000)
						}
					} else if(FIELDS[i].equals(EMAIL_ID)){
						println " Inside email field"
						if(formData[i].contains("@")){
							KPCommonPage.userName=formData[i]
							println " 1::: "+KPCommonPage.userName+" 2::: "+formData[i]
							browser.populateField(FIELDS[i], KPCommonPage.userName)
							//WebForm.inputData(browser,FIELDS[i],tagname,formData[i])
						}else{
							browser.populateField(FIELDS[i],formData[i])
						}
					} else if(FIELDS[i].equals(MOBILE_NUM)){
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
						if(formData[i]==""){
							browser.pressTab(MOBILE_NUM)
						}
					}else{
						/*if(FIELDS[i].equals(FIRST_NAME)){
						 KPCommonPage.f_name=formData[i]
						 }*/
						WebForm.inputData(browser, FIELDS[i], tagName,formData[i])
					}
				}
				outcome = new SuccessOutcome()
			}
			return outcome
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {

			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, BasicInformationErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.scrollToElement(browser.getElement(Browser.XPATH, submitButton))
			browser.delay(1000)
			//browser.scrollToElement(browser.getElement(Browser.XPATH, FIRST_NAME))
			browser.delay(1000)
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(1000)
			}
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		def static uploadProfilepic = { browser,formData ->
			def dataToEnter=""
			//def splittedimageSource
			browser.click(CHANGE_PROFILE_IMAGE_LINK)
			browser.delay(1500)
			browser.click(UPLOAD_PROFILE_IMAGE_BUTTON)
			browser.delay(1000)
			dataToEnter = browser.getCurrentDirectory()+formData[0]
			println "dataToEnter ::::: "+dataToEnter
			browser.uploadFile(dataToEnter)
			browser.delay(2000)
			browser.click(UPLOAD_IMAGE_SUBMIT)
			browser.delay(3000)
			def userUploadText = browser.gettext(SUCCESS_MESSAGE)
			def actualUploadMsg = BasicInformationErrorMessageMap.get('profileUploadSuccess')
			def imageSource = browser.gettext(KPCommonPage.USER_PROFILE_PIC, "src")
			println "Image Source ::::::::::::: "+imageSource
			def splittedimageSource = imageSource.split("\\?")
			println "imageSource----------> "+splittedimageSource
			KPCommonPage.srcUserProfilePic = splittedimageSource[0]
			println "KPCommonPage.srcUserProfilePic  --------------------------> "+KPCommonPage.srcUserProfilePic

			if(userUploadText.equals(actualUploadMsg)){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "uploadError", "User profile upload error")
			}
		}
	}

	static final class EductionQualificationForm extends WebForm {

		//Education qualification form Elements

		private static final def X_PERCENTAGE       = ".//div[@class='edu-qual-container ']//input[@id='xPercentage']"

		private static final def X_SCHOOL_NAME      = ".//div[@class='edu-qual-container ']//input[@id='xSchoolName']"

		private static final def X_YEAR_PASS        = ".//div[@class='edu-qual-container ']//select[@name='xYearOfPass']"

		private static final def XII_PERCENTAGE     = ".//div[@class='edu-qual-container ']//input[@id='xiiPercentage']"

		private static final def XII_SCHOOL_NAME    = ".//div[@class='edu-qual-container ']//input[@id='xiiSchoolName']"

		private static final def XII_YEAR_PASS      = ".//div[@class='edu-qual-container ']//select[@name='xiiYearOfPass']"

		private static final def DIPLOMA_PERCENTAGE = ".//div[@class='edu-qual-container ']//input[@id='diplomaPercentage']"

		private static final def DIPLOMA_COLLEGE    = ".//div[@class='edu-qual-container ']//input[@id='diplomaCollegeName']"

		private static final def DIPLOMA_COURSE     = ".//div[@class='edu-qual-container ']//input[@ placeholder='Select Course Name']"

		private static final def DIPLOMA_START      = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='diplomaStartYear']"

		private static final def DIPLOMA_END        = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='diplomaEndYear']"

		private static final def UG_PERCENTGE       = ".//div[@class='edu-qual-container ']//input[@id='undergraduatePercentage']"

		private static final def UG_COLLEGE         = ".//div[@class='edu-qual-container ']//input[@id='undergraduateSchoolName']"

		private static final def UG_FIELD           = ".//div[@class='edu-qual-container ']//input[@ placeholder='Select Field Of Study']"

		private static final def UG_START           = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='undergraduateStartYear']"

		private static final def UG_END             = ".//div[@class='edu-qual-container ']//div[@ng-init='getYears(-46,46)']/select[@name='undergraduateEndYear']"

		private static final def PG_PERCENTGE       = ".//div[@class='edu-qual-container ']//input[@id='postgraduatePercentage']"

		private static final def PG_COLLEGE         = ".//div[@class='edu-qual-container ']//input[@id='postgraduateSchoolName']"

		private static final def PG_SPECIALIZATION   = ".//div[@class='edu-qual-container ']//input[@ placeholder='Select Specialization']"

		private static final def PG_START           = ".//div[@class='edu-qual-container ']//select[@name='postgraduateStartYear']	"

		private static final def PG_END             = ".//div[@class='edu-qual-container ']//select[@name='postgraduateEndYear']"

		private static final def SCROLL                 = ".//*[@id='main_page']/div[2]/div/div[2]/div[3]/div/h4"

		private static final def ADDNEWCOURSELINK   	= ".//a[@ng-click='addQualification(eduText)']"

		private static final def ADDNEWFIELDOFSTUDYLINK = ".//a[@ng-click='addQualification(fieldOfStudyText)']"

		private static final def ADDNEWSPECIALIZATIONLINK = ".//a[@ng-click='addQualification(specializationText)']"

		private static final def CREATENEWCOURSE   = ".//li[@ng-click=\'\$mdAutocompleteCtrl.select(\$index)\'][@role='button']"

		private static final def CREATENEWFIELDOFSTUDY = ".//span [@md-highlight-text='fieldOfStudyText']"

		private static final def CREATENEWSPECIALIZATION  = ".//span [@md-highlight-text='specializationText']"

		private static final def EXISTINGCOURSENAME = ".//span[@md-highlight-text='eduText']/span[@class='highlight']"

		private static final def EXISTINGFIELDOFSTUDY = ".//span[@md-highlight-text='fieldOfStudyText']"

		private static final def EXISTINGSPECIALIZATION = ".//span[@md-highlight-text='specializationText']"

		private static final def UPDATE_BUTTON      = ".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[6]/div/button"

		private static final def FIELDS= [X_PERCENTAGE, X_SCHOOL_NAME, X_YEAR_PASS, XII_PERCENTAGE, XII_SCHOOL_NAME, XII_YEAR_PASS, DIPLOMA_PERCENTAGE, DIPLOMA_COLLEGE, DIPLOMA_COURSE, DIPLOMA_START, DIPLOMA_END, UG_PERCENTGE, UG_COLLEGE, UG_FIELD, UG_START, UG_END, PG_PERCENTGE, PG_COLLEGE, PG_SPECIALIZATION, PG_START, PG_END]

		private static final def FIELD_ERROR = ".//*[@class='edu-qual-container ']//span[@class='error_message']/span[@aria-hidden='false']"

		private static final def FORM_ERROR    = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final EducationQualificationErrorMessageMap = [
			percent_req      		     :  'Percentage is required',
			invalid_percent 		     :  'Percentage is invalid.Use ex: 40, 56.23',
			schoolname_req  		     :  'School Name is required',
			year_req          			 :  'Year is required',
			collegename_req   			 :  'College Name is required',
			coursename_req  			 :  'Course Name is required.',
			startyear_req  				 :  'Start Year is required',
			endyear_req   				 :  'End Year is required',
			endyear_beforestart			 :  'End Year can\'t be Before Start Year',
			startendyear_same            :  'Start Year and End Year can\'t be Same',
			yearPassGreaterThanBornYear	 :	'Year of Pass should be greater than Born year',
			yearLessThanXYear			 :  'Year should be greater than the SSLC Passing Year',
			yearStartLessThanXYear		 :  'Year of Start should not be less than X Standard Passing year',
			nameofcoll_req     			 :  'Name of the College is required',
			fieldname_req     		     :  'Field Of Study is required.',
			specilization_req  			 :  'Specialization is required.',
			update_success    			 :  'User data updated successfully',
			startYearLessThanXiiDiploma  :  'Start Year shoud not be less than XII Standard/Diploma Pass Year',
			startYearLessThanUg          :  'start year should not be less than Undergraduate End year',
			update_success               :  'User data updated successfully']


		def static final populateFields = { browser, formData ->
			println "----------------------------------------------------------------------------------------------------------------------------------------------------------------"
			def outcome= WebForm.checkFormFieldsData(formData,FIELDS)
			if(outcome.isSuccess()){
				for(int i=0;i<FIELDS.size();i++){
					def tagName= browser.getTagName(FIELDS[i])
					if(FIELDS[i].equals(DIPLOMA_PERCENTAGE) || FIELDS[i].equals(UG_COLLEGE)){
						browser.scrollToElement(browser.getElement(Browser.XPATH,FIELDS[i]))
					}
					WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
					if(FIELDS[i].equals(DIPLOMA_COURSE) && browser.isDisplayed(ADDNEWCOURSELINK)){
						browser.click(ADDNEWCOURSELINK)
						browser.delay(1000)
						browser.click(CREATENEWCOURSE)
					}else if(browser.isDisplayed(EXISTINGCOURSENAME)){
						browser.click(EXISTINGCOURSENAME)
					}
					if(FIELDS[i].equals(UG_FIELD) && browser.isDisplayed(ADDNEWFIELDOFSTUDYLINK)){
						browser.click(ADDNEWFIELDOFSTUDYLINK)
						browser.delay(1000)
						browser.click(CREATENEWFIELDOFSTUDY)
					}else if(browser.isDisplayed(EXISTINGFIELDOFSTUDY)){
						browser.click(EXISTINGFIELDOFSTUDY)
					}
					if(FIELDS[i].equals(PG_SPECIALIZATION) && browser.isDisplayed(ADDNEWSPECIALIZATIONLINK)){
						browser.click(ADDNEWSPECIALIZATIONLINK)
						browser.delay(1000)
						browser.click(CREATENEWSPECIALIZATION)
					}else if(browser.isDisplayed(EXISTINGSPECIALIZATION)){
						browser.click(EXISTINGSPECIALIZATION)
					}

					KPCommonPage.allEducationDetails.add(formData[i])
					println "Hello::::::----------------------> "+KPCommonPage.allEducationDetails
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
			println "Inside Education Qualification Submit :::::::::::::"
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, EducationQualificationErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.scrollToElement(browser.getElement(Browser.XPATH, submitButton))
			browser.delay(1000)
			//browser.scrollToElement(browser.getElement(Browser.XPATH, X_PERCENTAGE))
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(1000)
			}
			println "Going to get validation messages and error fields are " +  errFields
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}
	}


	static final class WorkExperienceForm extends WebForm {

		//CompanyRegistration form elements

		private static def INDUSTRY     		                 = ".//div[@class='work-exp-container']//md-autocomplete[@aria-required='false']/md-autocomplete-wrap/input"

		private static final def CREATENEW_INDUSTRY_LINK         = ".//a[@ng-click='addIndustry(searchText)']"

		private static final def CREATENEW_INDUSTRY 			 = ".//span[@md-highlight-text='searchText']"

		private static final def INDUSTRY_AUTOCOMPLETE           = ".//md-autocomplete-parent-scope[@class='ng-scope']"

		private static final def JOB_ROLE     				     = ".//div[@class='work-exp-container']//input[@ng-model='userProfiles.jobRole'][@aria-invalid='true']"

		private static final def COMPANY_NAME  					 = ".//div[@class='work-exp-container']//input[@ng-model='userProfiles.companyName'][@aria-invalid='true']"

		private static final def TIMEPERIOD_STARTMONTH 		     = ".//div[@class='work-exp-container']//select[@class='form-control margin-month ng-pristine ng-untouched ng-invalid ng-invalid-required'][@id='category']"

		private static final def TIMEPERIOD_STARTYEAR	         = ".//div[@class='work-exp-container']//select[@class='form-control margin-year ng-pristine ng-untouched ng-invalid ng-invalid-required'][@ng-model='userProfiles.startYear']"

		private static final def TIMEPERIOD_ENDMONTH             = ".//div[@class='work-exp-container']//select[@class='form-control margin-month ng-pristine ng-untouched ng-invalid ng-invalid-required'][@id='categoryEndMonth']"

		private static final def TIMEPERIOD_ENDYEAR   	         = ".//div[@class='work-exp-container']//select[@class='form-control margin-year ng-pristine ng-untouched ng-invalid ng-invalid-required'][@ng-model='userProfiles.endYear']"

		private static final def CURRENTLY_WORKING_HERE 		 = ".//div[@class='work-exp-container']//md-checkbox[@type='checkbox']"

		private static final def WORK_EXPERIENCE_ADD_BUTTON      = "//div[@class='work-exp-container']//button[@class='btn button-secondary']"

		private static final def DELETE_WORK_EXPERIENCE          = "//div[@class='work-exp-container']//a[contains(text(),'DELETE')]"

		private static final def DELETE_OPTION_NO 				 = ".//button[@class='md-primary md-cancel-button md-button ng-scope md-default-theme md-ink-ripple']"

		private static final def DELETE_OPTION_YES               =  ".//button[@ng-click='dialog.hide()']/span[@class='ng-binding ng-scope']"

		private static final def UPDATE_BUTTON                   = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def FIELDS 						 = [INDUSTRY, JOB_ROLE, COMPANY_NAME, TIMEPERIOD_STARTMONTH, TIMEPERIOD_STARTYEAR, TIMEPERIOD_ENDMONTH, TIMEPERIOD_ENDYEAR, CURRENTLY_WORKING_HERE]

		private static final def FIELDSAFTERUNCHECK              = [TIMEPERIOD_ENDMONTH, TIMEPERIOD_ENDYEAR]
		// the error fields.
		private static final def FORM_ERROR                      = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def FIELD_ERROR                     = "//div[@class='work-exp-container']//span[@class='error_message']/span[@aria-hidden='false']"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final workExperienceErrorMessageMap = [
			industry_req                :  'Select Industry.',
			job_role_req		        :  'Job Role is required ',
			org_name_req  			    :  'Organization Name is required. ',
			start_month_req 			:  'Month is required',
			start_year_req  			:  'Year is required',
			end_month_req 			    :  'Month is required ',
			end_year_req    		    :  'Year is required',
			start_end_time_same			: 'Start Time Period cannot be same as End Time Period',
			start_time_greater_end_time : 'Start Time Period cannot be greater than End Time Period',
			update_success 				: 'User data updated successfully']

		//To enter data
		def static final populateFields = { browser, formData ->

			println ("WorkExperience.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)

			if(outcome.isSuccess()){
				for(int j = 0; j < FIELDS.size(); j++){
					def tagName= browser.getTagName(FIELDS[j])
					if(FIELDS[j].equals(INDUSTRY)){
						browser.scrollToElement(browser.getElement(Browser.XPATH, FIELDS[j]))
						browser.delay(500)
						if(formData[j] != ""){
							browser.populateField(FIELDS[j], formData[j])
							browser.delay(1000)
							if(browser.isDisplayed(CREATENEW_INDUSTRY_LINK)){
								browser.click(CREATENEW_INDUSTRY_LINK)
								browser.delay(500)
								browser.click(CREATENEW_INDUSTRY)
							}else{
								KPCommonPage.selectAutoComplete(browser, INDUSTRY_AUTOCOMPLETE, formData[j].trim())
							}
						} else{
							browser.populateField(FIELDS[j], formData[j])
						}
					}else if(FIELDS[j].equals(CURRENTLY_WORKING_HERE)){

						if(formData[j].equals("1")){
							println "1"
							WebForm.inputData(browser,FIELDS[j],tagName,formData[j])
							println "2"
							println "CHECKED"
							KPCommonPage.WorkExperienceDetails.add("Till Date .")
							println "3"
						}else if(formData[j].equals("0")){
							for(int k=FIELDSAFTERUNCHECK.size();k<=FIELDSAFTERUNCHECK.size();k--){
								WebForm.inputData(browser,FIELDS[j],tagName,formData[j])
								println "4"
								WebForm.inputData(browser,FIELDS[j-k],tagName,formData[j-k])
								println "5"
								KPCommonPage.WorkExperienceDetails.add(formData[j-k].trim())
								println"6"
							}
						}
					}else if(FIELDS[j].equals(TIMEPERIOD_ENDYEAR) || FIELDS[j].equals(TIMEPERIOD_ENDMONTH)){
					println "7"
					println  "formData[j]::::::::::::::::::::::::: "+formData[j]
						if(formData[j]!=""){
							println "8"
							WebForm.inputData(browser,FIELDS[j],tagName,formData[j])
							println "9"
							KPCommonPage.WorkExperienceDetails.add(formData[j].trim())
							println "10"
						}
					}else{
						WebForm.inputData(browser,FIELDS[j],tagName,formData[j])
						println "Adding------------>"
						KPCommonPage.WorkExperienceDetails.add(formData[j].trim())
						println "Added------------>"
						/*if(FIELDS[j].equals(TIMEPERIOD_ENDYEAR)){
						 browser.pressTab(TIMEPERIOD_ENDYEAR)
						 }*/
					}
				}
			}
			println "121212121"
			println "KPCommonPage.WorkExperienceDetails----------> "+KPCommonPage.WorkExperienceDetails
			println "767676767676"
			return outcome
		}

		/**
		 * To submit the form
		 * @param browser browser instance
		 * @param data  array containing test data
		 */
		def final submit(browser, data) {
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, workExperienceErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.scrollToElement(browser.getElement(Browser.XPATH, submitButton))
			browser.delay(1000)
			if(browser.checkEnabled(submitButton)){
				browser.click submitButton // submit the form.
				browser.delay(1000)
			}
			browser.getValidationMessages errFields
		}
	}

	static final class SkillsForm extends WebForm {

		private static final def SKILLS				       = ".//div[@class='skills-container']//input[@name='multipleSelectSkills']"

		private def static final SKILLS_ADD_NEW       	   = ".//*[@id='edit_profile_container']//form//multiple-autocomplete/div[2][@class='autocomplete-list']"

		private def static final SKILLS_AUTOSUGGEST        = "//div[@class='skills-container']//ul/li[@class='ng-binding ng-scope autocomplete-active']"

		private static final def UPDATE_BUTTON		       = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def SKILLS_TEXT			   = ".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[4]/div/div/div/div/div/multiple-autocomplete/div/ul/li/span/p"

		private static final def SKILLS_REMOVE_BUTTON      = ".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[4]/div/div/div/div/div/multiple-autocomplete/div/ul/li/span/span/i"

		private static final def SKILLS_ON_VIEW_PROFILE    = ".//li[@class='tagSkills prfleDetails capitalize ng-binding']"

		private static final def SCROLL_TO_VIEW_SKILLS     = ".//ul[@class='footer-secondary-list']/li/h4[@class='heading']"

		private static final def FIELDS = [SKILLS]

		// the error fields.
		private static final def FORM_ERROR = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR]

		//error message map (Key-Value Pair)
		def static final skillsErrorMessageMap = [update_success :"User data updated successfully"]

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("LoginForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					def tagName = browser.getTagName(FIELDS[i])
					if(formData[i].contains(",")){
						def splittedValue = formData[i].split(",")
						for(int j=0;j<splittedValue.size();j++){
							WebForm.inputData(browser,FIELDS[i],tagName,splittedValue[j].trim())
							if(browser.isDisplayed(SKILLS_AUTOSUGGEST)){
								browser.delay(500)
								browser.click(SKILLS_AUTOSUGGEST)
							}else if(browser.isDisplayed(SKILLS_ADD_NEW)){
								browser.delay(500)
								browser.click(SKILLS_ADD_NEW)
							}
							KPCommonPage.skills.add(splittedValue[j])
						}
					}else{
						WebForm.inputData(browser,FIELDS[i],tagName,formData[i])
						if(browser.isDisplayed(SKILLS_AUTOSUGGEST)){
							browser.delay(500)
							browser.click(SKILLS_AUTOSUGGEST)
						}else if(browser.isDisplayed(SKILLS_ADD_NEW)){
							browser.delay(500)
							browser.click(SKILLS_ADD_NEW)
						}
						KPCommonPage.skills.add(formData[i].trim())
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
			def actualValidationMsg = submitForm browser, FIELDS, UPDATE_BUTTON, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, skillsErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = { browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(3000)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		def static skillsDisplayed = { browser, formData ->
			def formDataSkills = []
			browser.delay(1000)
			browser.scrollToElement2(SCROLL_TO_VIEW_SKILLS)
			def skillsOnViewProfile = browser.getLists(SKILLS_ON_VIEW_PROFILE)
			def commonSkills = KPCommonPage.skills
			println "commonSkills:::: "+commonSkills
			println "skillsOnViewProfile:::::::: "+skillsOnViewProfile
			for(int k=0;k<commonSkills.size();k++){
				formDataSkills.add(commonSkills[k].trim())
			}
			skillsOnViewProfile = skillsOnViewProfile.sort()
			formDataSkills = formDataSkills.sort()
			for(int l=0;l<skillsOnViewProfile.size();l++)
				if(skillsOnViewProfile[l].equalsIgnoreCase(formDataSkills[l])){
					println "SUCCESS---> 1"
					return new SuccessOutcome()
				}else {
					println "FAILURE----> 1"
					return KPCommonPage.returnFailureOutcome(browser, "SkillsMismatchIssue", "Skills on View profile page do not match")
				}

		}

		def static removeASkill = { browser, formData ->
			def result = false
			def allSkills = browser.getLists(SKILLS_TEXT)
			def allSkillEle = browser.getListElements(SKILLS_REMOVE_BUTTON)
			if(formData[0].equals("")){
				result = true
			}else if(formData[0].contains(",")){
				def splitFormData = formData[0].split(",")
				for(int i=0;i<allSkills.size();i++){
					for(int j=0;j<splitFormData.size();j++){
						if(splitFormData[j].equalsIgnoreCase(allSkills[i])){
							browser.clickElement(allSkillEle[i])
							result = true
						}
					}
				}
			}else {
				for(int i=0;i<allSkills.size();i++){
					for(int j=0;j<formData.size();j++){
						if(formData[j].equalsIgnoreCase(allSkills[i])){
							browser.clickElement(allSkillEle[i])
							result = true
						}
					}
				}
			}

			if(result){
				return new SuccessOutcome()
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "noSkillsDisplayed", "Skill to remove is not displayed.")
			}
		}


		def static final skillsRemoved = { browser, formData ->
			def skillsNamesOnPage = []
			def skillValues
			browser.scrollToElement2(SCROLL_TO_VIEW_SKILLS)
			if(browser.isDisplayed(SKILLS_ON_VIEW_PROFILE)){
				skillsNamesOnPage = browser.getLists(SKILLS_ON_VIEW_PROFILE)
			}else
				return new SuccessOutcome()
			if(formData[0].contains(",")){
				skillValues = formData[0].split(",")
				if(!skillsNamesOnPage.containsAll(skillValues)){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "SkillDisplayed", "Skill not removed after update.")
			}else if(!skillsNamesOnPage.contains(formData[0])){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "SkillDisplayed", "Skill not removed after update.")
		}

		def static deleteExistingSkills = { browser, formData ->
			if(browser.isDisplayed(SKILLS_TEXT)){
				def skillsCountOnPage = browser.getListElements(SKILLS_REMOVE_BUTTON)
				for(int j=0;j<skillsCountOnPage.size();j++){
					browser.clickElement(skillsCountOnPage[j])
					browser.delay(1000)
				}
			}
		}

		def static skillsRetained = { browser, formData ->
			def formDataSkillss = []
			def skillsFromCommon = KPCommonPage.skills
			def skillsOnEditPage = browser.getLists(SKILLS_TEXT)
			for(int k=0;k<skillsOnEditPage.size();k++){
				formDataSkillss.add(skillsOnEditPage[k].trim())
			}
			if(skillsOnEditPage.containsAll(formDataSkillss)){
				println "SUCCESS---> 2"
				return new SuccessOutcome()
			}else {
				println "FAILURE----> 2"
				return KPCommonPage.returnFailureOutcome(browser, "SkillsMismatchIssue", "Skills on View profile page do not match")
			}
		}
	}

	static final class CertificateForm extends WebForm {

		private static final def CHOOSE_FILE_BUTTON     	= ".//div[@class='mb_10 ng-binding']/label[@class='button-pri'][@aria-hidden='false']"

		private static final def CERTIFICATE_NAME      		= ".//div[@class='certificates-container']//md-autocomplete[@aria-required='false']/md-autocomplete-wrap/input"

		private static final def CREATENEW_CERTIFICATE_LINK = ".//a[@ng-click='addCertificate(searchText)']"

		private static final def CREATENEW_CERTIFICATE	    = ".//span[@md-highlight-text='searchText']"

		private static final def CERTIFICATE_AUTOCOMPLETE   = ".//md-autocomplete-parent-scope[@class='ng-scope']"

		private static final def FORM_ERROR                 = ".//div[@class='success-message alert ng-isolate-scope alert-success alert-dismissible']//div[@ng-transclude='']/span"

		private static final def FIELD_ERROR                = "//div[@class='work-exp-container']//span[@class='error_message']/span"

		private static final def UPDATE_BUTTON              = ".//*[@id='edit_profile_container']//form//div[@class='col-md-3']//button[@class='ptb_6 btn button-primary']"

		private static final def FIELDS 			        = [CHOOSE_FILE_BUTTON, CERTIFICATE_NAME]

		// the error fields.
		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final certificateErrorMessageMap = [update_success :"User data updated successfully"]

		//To enter data
		def static final populateFields = { browser, formData ->
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					def tagName = browser.getTagName(FIELDS[i])
					println "tagname for ::::::::::::::: "+i+":::::::::::: "+tagName

					if(FIELDS[i].equalsIgnoreCase(CHOOSE_FILE_BUTTON)){
						if(formData[i]!=null && !formData[i].equals("")){
							browser.click(CHOOSE_FILE_BUTTON)
							browser.uploadFile(formData[i])
							browser.delay(500)
						}
					}

					if(FIELDS[i].equalsIgnoreCase(CERTIFICATE_NAME)){
						println "Inside Input Data"
						browser.populateField(FIELDS[i], formData[i])
						println "Outside input data"
						if(browser.isDisplayed(CREATENEW_CERTIFICATE_LINK)){
							println "6"
							browser.click(CREATENEW_CERTIFICATE_LINK)
							println "7"
							browser.delay(500)
							println "8"
							browser.click(CREATENEW_CERTIFICATE)
							println "9"
						}else{
							println "10"
							KPCommonPage.selectAutoComplete(browser, CERTIFICATE_AUTOCOMPLETE, formData[i].trim())
							println "11"
						}
					}
				}
			}
			return outcome
		}
	}
}


