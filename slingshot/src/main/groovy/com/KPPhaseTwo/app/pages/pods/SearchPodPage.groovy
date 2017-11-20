package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Samir  */

final class SearchPodPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("primaryDetails")){
			new SearchPodForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new FilterPodForm().populateFields(browser, formData);
		}

	}

	//Override
	def submit = {browser, formKey, formData ->
		println ("Submit method in SearchPodPage")
		if(formKey.equals("primaryDetails")){
			new SearchPodForm().submit(browser, formData)
		}

	}

	//To verify that the pod list is displayed on clicking search button
	def static podsDisplayed = { browser, formData ->
		new SearchPodForm().podsDisplayed browser, formData
	}

	//To verify that the primary searched level and industry are displaying in the filters too
	def static filtersWithSameSearch = { browser, formData ->
		new SearchPodForm().filtersWithSameSearch browser, formData
	}

	//To verify that the result displayed for the serached pods are shown correctly
	def static correctPodsDisplayed = { browser, formData ->
		new SearchPodForm().correctPodsDisplayed browser, formData
	}

	//To verify that the pod name displayed in the pod details is same as the pod clicked
	def static podName = { browser, formData ->
		new SearchPodForm().podName browser, formData
	}

	//To add the first pod to wishlist
	def static addToWishlist = { browser, formData ->
		new SearchPodForm().addToWishlist browser, formData
	}

	//To verify that the wishlisted pod is displayed in wishlist page
	def static wishlistPodDsplyd = { browser, formData ->
		new SearchPodForm().wishlistPodDsplyd browser, formData
	}

	//To remove the first pod to wishlist
	def static removeFrmWshList = { browser, formData ->
		new SearchPodForm().removeFrmWshList browser, formData
	}

	//To verify that the wishlisted pod is removed from the pod wishlist page
	def static wishListedPodRemoved = { browser, formData ->
		new SearchPodForm().wishListedPodRemoved browser, formData
	}

	//To verify result shown per page is the same number pods displayed per page
	def static resultDisplayedPerPage = { browser, formData ->
		new SearchPodForm().resultDisplayedPerPage browser, formData
	}

	/*//To Verify the result of pod using skill filter
	def static displayedSkillFilter = {browser, formData ->
		println "......Inside displayed skill ...."
		new FilterPodForm().displayedSkillFilter browser, formData
	}*/

	/*//To Verify the result of pod using Industry filter
	def static displayedIndustryFilter = {browser, formData ->
		new FilterPodForm().displayedIndustryFilter browser,  formData
	}*/


	/*//To Verify the result of pod using Level filter
	def static displayedLevelFilter = {browser, formData ->
		new FilterPodForm().displayedLevelFilter browser,  formData
	}*/

	/*//To Verify the result of pod using Min and Max filter
	def static displayedMinAndMaxDurationFilter = {browser, formData ->
		new FilterPodForm().displayedMinAndMaxDurationFilter browser, formData
	}*/

	/*//To Verify the result of pod using Sort By filter
	def static displayedSortByFilter = {browser, formData ->
		new FilterPodForm().displayedSortByFilter browser, formData
	}*/
	
	//To fetch the pod names if they are displaying
	def static getPodTextList = { browser, element, podList, fiftyRes ->
		new FilterPodForm().getPodTextList browser, element, podList, fiftyRes
	}
    
	/*//To remove selected skills from skill filter
	def static removeSelectedSkills = {browser, formData ->
		new FilterPodForm().removeSelectedSkills browser, formData
	}*/
	
	
	//To get total number of viewing pod's count.
	def static getTotalViewingPodsCount = {browser, formData ->
		new FilterPodForm().getTotalViewingPodsCount browser, formData
		
	}

	static final class SearchPodForm extends WebForm {

		//SearchPod form elements
		private static final def SEARCH_TEXT = ".//*[@id='courseSearch']"

		private static final def LEVEL = ".//md-select[@placeholder='Select Level']"

		private static final def INDUSTRY = ".//md-select[@placeholder='Select Industry']"

		private static final def SEARCH = "//button[@class='full-width button-primary']"

		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def LEVEL_LIST = ".//*[@ng-value='level']"

		private static final def INDUSTRY_LIST = ".//md-option[@ng-value='ind.industryId']"

		private static final def FILTER_INDUSTRY = ".//md-select[@ng-model='filter.industry']"

		private static final def FILTER_LEVEL = ".//md-select[@ng-model='filter.level']"

		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='opt.industryId']"

		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='opt']"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"        

		private static final def OVERVIEW = ".//a[@class='select individual-tab']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"    

		private static final def POD_NAME = "html/body/div[1]/div[2]/div[2]/div[1]/div/h3"

		private static final def ADD_TO_WISHLIST = "//div[@ng-hide='isListEmpty']/div[3]/div/span"     

		private static final def FIRST_POD_NAME = ".//*[@id='main_page']/div[2]/div/div[3]/div[2]/div[2]/div[1]/div[1]/div/div/h3/a"

		private static final def WISHLISTED_POD = "//div[2]/div[1]/div/div/h3/a"

		private static final def ALL_WISHLISTED_POD = "//a[@class='content-name job-title pointer title-name blue ng-binding']"

		private static final def FIELDS = [LEVEL, INDUSTRY, SEARCH_TEXT]// the error fields.
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("SearchPodForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						browser.populateField(FIELDS[i],formData[i])
					}
					if(FIELDS[i].equals(LEVEL)){
						browser.click LEVEL
						browser.delay(1000)
						def levelList = []
						if(formData[i].contains(",")){
							def data = formData[i].split(",")
							for(int j = 0; j < data.length; j++){
								levelList.add(data[j])
								KPCommonPage.selectAutoComplete(browser, LEVEL_LIST, data[j])
							}
						}else{
							levelList.add(formData[i])
							KPCommonPage.selectAutoComplete(browser, LEVEL_LIST , formData[i])
						}
						browser.click(ELEMENT)
						KPCommonPage.levelLists = levelList
					}
					if(FIELDS[i].equals(INDUSTRY)){
						browser.click INDUSTRY
						browser.delay(1000)
						def industryList = []
						if(formData[i].contains(",")){
							def data = formData[i].split(",")
							for(int k = 0; k < data.length; k++){
								industryList.add(data[k])
								KPCommonPage.selectAutoComplete(browser, INDUSTRY_LIST , data[k])
							}
						}else{
							industryList.add(formData[i])
							KPCommonPage.selectAutoComplete(browser, INDUSTRY_LIST , formData[i])
						}
						KPCommonPage.industryLists = industryList
						browser.click(ELEMENT)
					}
					browser.delay(1000)
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
			def actualValidationMsg = submitForm browser, FIELDS, SEARCH, data, ERROR_MESSAGE_FIELDS
			def actualValidationMsgKeys = getActualErrorMessageKeys(actualValidationMsg, searchPodPageErrorMessageMap)
			def outcome = new SuccessOutcome();
			outcome.setResultData(actualValidationMsgKeys)
			return outcome
		}

		//override submitForm
		def static submitForm = {browser, formFields, submitButton, data, errFields ->
			browser.click submitButton // submit the form.
			browser.delay(3500)
			browser.getValidationMessages errFields // get the validation messages from the current page.
		}

		//To verify that the pod list is displayed on clicking search button
		def static final podsDisplayed = { browser, formData ->
			if(browser.isDisplayed(POD_LIST)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsNotDisplayedIssue", "Pods are not displayed on search")
			}
		}

		//To verify that the primary searched level and industry are displaying in the filters too
		def static final filtersWithSameSearch = { browser, formData ->
			if(browser.isDisplayed(FILTER_INDUSTRY) && browser.isDisplayed(FILTER_LEVEL)){
				browser.click FILTER_INDUSTRY
				browser.delay(1000)
				def allIndustry = browser.getLists(FILTER_INDUSTRY_LIST)
				browser.click(ELEMENT)
				browser.delay(1000)
				browser.click FILTER_LEVEL
				browser.delay(1000)
				def allLevel = browser.getLists(FILTER_LEVEL_LIST)
				if(allIndustry?.sort(false) == KPCommonPage.industryLists?.sort(false) && allLevel?.sort(false) == KPCommonPage.levelLists?.sort(false)){
					KPCommonPage.industryLists = []
					KPCommonPage.levelLists = []
					return new SuccessOutcome()
				}else{
					KPCommonPage.industryLists = []
					KPCommonPage.levelLists = []
					return KPCommonPage.returnFailureOutcome(browser, "Industry&LevelIssue", "Industry and level searched does not match with the displayed option in Filters.")
				}
			}
		}

		//To verify that the result displayed for the searched pods are shown correctly
		def static final correctPodsDisplayed = { browser, formData ->
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
					browser.delay(3000)
					def allResult = browser.getLists(POD_LIST)
					def result = false
					outerloop:for(int i = 0; i < allResult.size(); i++){
						def allResultElement = browser.getListElements(POD_LIST)
						//browser.scrollToElement(allResultElement[i])
						browser.delay(3000)
						browser.clickElement allResultElement[i]
						browser.delay(4000)
						if(browser.isDisplayed(OVERVIEW)){
							browser.click OVERVIEW
							browser.delay(3500)
							def levelAndIndustry = browser.getLists("//div[@class='row']/div/h5")
							def value = browser.getLists("//div[@class='row']/div[2]/span")
							for(int j = 0;j < levelAndIndustry.size();j++){
								if(levelAndIndustry[j].equals("Level") || levelAndIndustry[j].equals("Industry")){
									if(KPCommonPage.industryLists.contains(value[j]) || KPCommonPage.levelLists.contains(value[j])){
										println "KPCommonPage.industryLists "+KPCommonPage.industryLists+"\nvalue[j] "+value[j]+"\nKPCommonPage.levelLists  "+KPCommonPage.levelLists
										result = true
									}else{
										result = false
										break outerloop
									}
								}
							}
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
						}
						browser.click CLICK_PODS_BACK
						browser.delay(2500)
					}
					if(result){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "IndustryLevelNotMatchIssue", "Industry and Level does not match.")
					}
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
		}

		//To verify that the pod name displayed in the pod details is same as the pod clicked
		def static final podName = { browser, formData ->
			browser.scrollToElement2(ELEMENT)
			browser.delay(5000)
			if(browser.isDisplayed(POD_NAME)){
				def podName = browser.gettext(POD_NAME)
				println "podName "+podName+ "\nKPCommonPage.podName "+KPCommonPage.podName
				if(podName.equalsIgnoreCase(KPCommonPage.podName)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "PodNameMatchIssue", "Pod name does not match the selected pod")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodNameDisplayIssue", "Pod name is not displayed.")
			}
		}

		//To add the first pod to wishlist
		def static final addToWishlist = { browser, formData ->
			println "In add to wishlist"
			browser.delay(3500)
			if(browser.isDisplayed(ADD_TO_WISHLIST)){
				KPCommonPage.firstPodName = browser.gettext(FIRST_POD_NAME)
				println KPCommonPage.firstPodName 
				if(browser.gettext(ADD_TO_WISHLIST).contains("Add to wishlist")){
					browser.click ADD_TO_WISHLIST
					println "Clicked"
					return new SuccessOutcome()
				}else{
					println "Already added"
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Add to wishlist is not displayed.")
			}
		}

		//To verify that the wishlisted pod is displayed in wishlist page
		def static final wishlistPodDsplyd = { browser, formData ->
			browser.delay(3500)
			if(browser.isDisplayed(WISHLISTED_POD)){
				def name = browser.gettext(WISHLISTED_POD)
				//println "name : "+name+"\nKPCommonPage.firstPodName : "+KPCommonPage.firstPodName
				if(KPCommonPage.firstPodName.equalsIgnoreCase(name)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "WishlistedPodMatchIssue", "Wishlisted pod is not same in the wishlist page.")
				}

			}else{
				return KPCommonPage.returnFailureOutcome(browser, "WishlistDisplayIssue", "Wishlisted pod is not displayed in the wishlist page.")
			}
		}

		//To remove the first pod to wishlist
		def static final removeFrmWshList = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(ADD_TO_WISHLIST)){
				//KPCommonPage.firstPodName = browser.gettext(FIRST_POD_NAME)
				if(browser.gettext(ADD_TO_WISHLIST).contains("Remove from wishlist")){
					browser.click ADD_TO_WISHLIST
					return new SuccessOutcome()
				}else{
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
			}
		}

		//To verify that the wishlisted pod is removed from the pod wishlist page
		def static final wishListedPodRemoved = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(WISHLISTED_POD)){
				def name = browser.getLists(ALL_WISHLISTED_POD)
				def result = false
				for(int i = 0; i < name.size(); i++){
					if(KPCommonPage.firstPodName.equalsIgnoreCase(name[i])){
						result = false
						break
					}else{
						result = true
					}
				}
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
				}
			}else{
				return new SuccessOutcome()
			}
		}

		//To verify result shown per page is the same number pods displayed per page
		def static final resultDisplayedPerPage = { browser, formData ->
			browser.delay(2000)
			if(browser.isDisplayed(POD_LIST)){
				def totalPodPerPage = browser.getLists(POD_LIST)
				println "totalPodPerPage : "+totalPodPerPage.size()
				def totalResult = browser.gettext("//p[@class='clearfix content output-count-header ng-binding']").split("-")[1].subSequence(0, 2).trim()
				println "total Result"+totalResult
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "ResultPerPageDisplayIssue", "Result for per page does not match the displayed result.")
			}
		}
	}

	static final class FilterPodForm extends WebForm {

		//SearchPod form elements with filters
		private static final def SEARCH = "//button[@class='full-width button-primary']"

		private static final def POD_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def FILTER_SKILL = "//input[@placeholder='Skills']"

		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"

		private static final def FILTER_INDUSTRY = ".//md-select[@ng-model='functionCall.filter.industry']"

		private static final def FILTER_LEVEL = ".//md-select[@ng-model='functionCall.filter.level']"

		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='opt.industryId']"

		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='opt']"

		private static final def FILTER_ORGANIZATION = "//input[@placeholder='Organisation']"

		private static final def FILTER_ORGANIZATION_LIST = "//div[@class='autocomplete-list']/ul/li"

		private static final def POD_CREATED_BY = "//span[i[@class='kp-pod blue mr_5']]"

		private static final def DURATION_LIST = "//span[i[@class='kp-timer blue mr_5']]"

		private static final def FILTER_MIN_DURATION = "//md-select[@ng-model='functionCall.filter.minDuration']"

		private static final def FILTER_MIN_DURATION_LIST = "//md-option[@ng-value='minduration']"

		private static final def FILTER_MAX_DURATION = "//md-select[@ng-model='functionCall.filter.maxDuration']"

		private static final def FILTER_MAX_DURATION_LIST = "//md-option[@ng-value='maxDuration']"

		private static final def FILTER_SORT_BY = "//select[@ng-model='functionCall.sortBy']"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav//img"

		private static final def ELEMENT2 = "html/body/md-backdrop"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def OVERVIEW = ".//a[@class='select individual-tab']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"
		
		private static final def SKILL_REMOVE_MARK = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//i"

		private static final def SELCTED_SKILLLIST = "//multiple-autocomplete[@ng-model='functionCall.filter.skills']/div/ul//p"
		
		private static final def SELECTED_ORGLIST = "//multiple-autocomplete[@ng-model='filter.company']/div/ul//p"
		
		private static final def ORG_REMOVE_MARK = "//multiple-autocomplete[@ng-model='filter.company']/div/ul//i"
				
		private static final def POD_NAME = "html/body/div[1]/div[2]/div[2]/div[1]/h3"
		
		private static final def NO_OF_PEOPLE = "//h5[contains(text(),'No of People in the Batch')]"
		
		private static final def PAGINATION_NEXT = "//li[@class='pagination-last ng-scope disabled']"

		private static final def FIELDS = [FILTER_SKILL, FILTER_INDUSTRY, FILTER_LEVEL, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]// the error fields.
		
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = []

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("SearchPodForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_SKILL)){
						if(!formData[i].equals("")){
							def viewingCount = SearchPodPage.getTotalViewingPodsCount(browser,formData)
							KPCommonPage.viewCount = viewingCount
								browser.delay(1000)
							def skillsList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int j = 0; j < data.length; j++){								
									skillsList.add(data[j])
									browser.populateField(FIELDS[i], data[j])	
									browser.delay(4000)
									KPCommonPage.selectAutoComplete(browser, FILTER_SKILLS_LIST, data[j])
								}
							}else{
								skillsList.add(formData[i])
								browser.populateField(FIELDS[i],formData[i])
								browser.delay(4000)
								KPCommonPage.selectAutoComplete(browser, FILTER_SKILLS_LIST , formData[i])
							}
							KPCommonPage.skillListsFilter = skillsList
						}
					}
					if(FIELDS[i].equals(FILTER_INDUSTRY)){
						if(!formData[i].equals("")){
							browser.click FILTER_INDUSTRY
							browser.delay(2000)
							def industryList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int k = 0; k < data.length; k++){
									industryList.add(data[k])
									KPCommonPage.selectAutoComplete(browser, FILTER_INDUSTRY_LIST , data[k])
								}
							}else{
								industryList.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, FILTER_INDUSTRY_LIST , formData[i])
							}
							KPCommonPage.industryListsFilter = industryList
							browser.click(ELEMENT)
						}
					}


					if(FIELDS[i].equals(FILTER_LEVEL)){
						if(!formData[i].equals("")){
							browser.click FILTER_LEVEL
							browser.delay(1000)
							def levelList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int j = 0; j < data.length; j++){
									levelList.add(data[j])
									KPCommonPage.selectAutoComplete(browser, FILTER_LEVEL_LIST, data[j])
								}
							}else{
								levelList.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, FILTER_LEVEL_LIST , formData[i])
							}
							browser.click(ELEMENT)
							KPCommonPage.levelListsFilter = levelList
						}
					}


					if(FIELDS[i].equals(FILTER_MIN_DURATION) && FIELDS[i+1].equals(FILTER_MAX_DURATION)){
                        def minHrsData,maxHrsData
						browser.scrollToElement2(FILTER_MIN_DURATION)
						if(!formData[i].equals("") && !formData[i+1].equals("") ){
							
							browser.click FILTER_MIN_DURATION
							browser.delay(1000)
							minHrsData = formData[i]
							KPCommonPage.selectAutoComplete(browser, FILTER_MIN_DURATION_LIST , formData[i])
							browser.delay(1000)
							browser.click(ELEMENT2)
							browser.scrollToElement2(FILTER_MAX_DURATION)
							browser.delay(1000)
							browser.click FILTER_MAX_DURATION
							browser.delay(1000)
							maxHrsData = formData[i+1]
							KPCommonPage.selectAutoComplete(browser, FILTER_MAX_DURATION_LIST , formData[i+1])
							browser.delay(2000)
							KPCommonPage.minTimeData = minHrsData
							KPCommonPage.maxTimeData = maxHrsData
						}
					}

					if(FIELDS[i].equals(FILTER_SORT_BY)){
						if(!formData[i].equals("")){
							def sortData
							browser.click FILTER_SORT_BY
							browser.delay(1000)
							sortData = formData[i]
							browser.selectDropdownValue(FILTER_SORT_BY,formData[i])
							browser.delay(2000)
							KPCommonPage.sortByData = sortData
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}

		//to get all the pod names from Search pod list page in a list
		public static def getPodTextList(def browser,def element, def podList,def fiftyRes){
			def allResult
			browser.scrollToElement2(element)
			browser.delay(3000)
			if(browser.isDisplayed(podList)){
				if(browser.isDisplayed(fiftyRes)){
					//browser.click fiftyRes
					browser.delay(3000)
					allResult = browser.getLists(podList)
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
			return allResult
		}

		/*//To verify that the result displayed for the entered skills through filter are shown correctly
		def static displayedSkillFilter = { browser, formData ->
					def allResult = SearchPodPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
					println ".......After getting pod text list........."
					def matchingResult = false
					outerloop:for(int i = 0; i < allResult.size(); i++){
						def allResultElement = browser.getListElements(POD_LIST)
						browser.scrollToElement(allResultElement[i])
						browser.delay(1000)
						browser.clickElement allResultElement[i]
						browser.delay(2000)
						if(browser.isDisplayed(OVERVIEW)){
							browser.click OVERVIEW
							browser.delay(2000)
							def label = browser.getLists("//div[@class='row']/div/h5")
							def value = browser.getLists("//div[@class='row']/div[2]/span")
							for(int j = 0;j < label.size();j++){
								if(label[j].equalsIgnoreCase("Skills")){
									def inputSkillList = KPCommonPage.skillListsFilter
									def actualSkillList = []
									if(value[j].contains(",")){
										def data = value[j].split(",")
										for(int m = 0; m < data.length; m++){
											actualSkillList.add(data[m].trim())
										}
									}else{
									      actualSkillList.add(value[j])
									}
									if(inputSkillList != null || !inputSkillList.isEmpty()){
										for(int k = 0; k < inputSkillList.size(); k++){
											for(int r = 0; r < actualSkillList.size(); r++){
												if(inputSkillList[k].equalsIgnoreCase(actualSkillList[r])){
													matchingResult = true
													
												}
											}
										}
									}else{
										return KPCommonPage.returnFailureOutcome(browser, "SkillListIssue", "Actual Skill List is Either Null or Empty ")
									}
								}
							}
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
						}
						browser.click CLICK_PODS_BACK
						browser.delay(2000)
					}
					if(matchingResult){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "SkillsDisplayIssue", "Skills does not match.")
					}
		}*/

		//To verify that the result displayed for the entered Industry through filter are shown correctly
		/*def static displayedIndustryFilter = { browser, formData ->
			println "........INSIDE DISPLAYED INDUSTRY METHOD.........."
					def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
					def matchingResult = false
					outerloop:for(int i = 0; i < allResult.size(); i++){
						def allResultElement = browser.getListElements(POD_LIST)
						browser.scrollToElement(allResultElement[i])
						browser.delay(1000)
						browser.clickElement allResultElement[i]
						browser.delay(2000)
						if(browser.isDisplayed(OVERVIEW)){
							browser.click OVERVIEW
							browser.delay(2000)
							def label = browser.getLists("//div[@class='row']/div/h5")
							def value = browser.getLists("//div[@class='row']/div[2]/span")
							for(int j = 0;j < label.size();j++){
								if(label[j].equalsIgnoreCase("Industry")){
									def inputIndustryList = KPCommonPage.industryListsFilter
									def actualIndustryList = []
									if(value[j].contains(",")){
										def data = value[j].split(",")
										for(int m = 0; m < data.length; m++){
											actualIndustryList.add(data[m].trim())
										}
									}else{
									      actualIndustryList.add(value[j])
									}
									if(inputIndustryList != null || !inputIndustryList.isEmpty()){
										
										for(int k = 0; k < inputIndustryList.size(); k++){
										
												for(int r = 0; r < actualIndustryList.size(); r++){
										
													if(inputIndustryList[k].equalsIgnoreCase(actualIndustryList[r])){
															matchingResult = true
													}
												}
											}
									}else{
											return KPCommonPage.returnFailureOutcome(browser, "IndustryListIssue", "Actual Industry List is Either Null or Empty ")
									}
								}
							}
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
						}	
						browser.click CLICK_PODS_BACK
						browser.delay(2000)
					}
					if(matchingResult){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "IndustryNotMatchIssue", "Industry  does not match.")
					}
				
		}*/

		

		//To verify that the result displayed for the entered Level through filter are shown correctly
		/*def static displayedLevelFilter = { browser, formData ->
					def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
					def matchingResult = false
					outerloop:for(int i = 0; i < allResult.size()-1; i++){
						def allResultElement = browser.getListElements(POD_LIST)
						browser.scrollToElement(allResultElement[i])
						browser.delay(2000)
						browser.clickElement allResultElement[i]
						browser.delay(3000)
						if(browser.isDisplayed(OVERVIEW)){
							browser.click OVERVIEW
							browser.delay(2000)
							def label = browser.getLists("//div[@class='row']/div/h5")
							def value = browser.getLists("//div[@class='row']/div[2]/span")
							for(int j = 0;j < label.size();j++){
								if(label[j].equalsIgnoreCase("Level")){
									def inputLevelList = KPCommonPage.levelListsFilter
									def actualLevelList = []
									if(value[j].contains(",")){
										def data = value[j].split(",")
										for(int m = 0; m < data.length; m++){
											actualLevelList.add(data[m].trim())
										}
									}else{
									      actualLevelList.add(value[j])
									}
									if(inputLevelList != null || !inputLevelList.isEmpty()){
										for(int k = 0; k < inputLevelList.size(); k++){
											for(int r = 0; r < actualLevelList.size(); r++){
												if(inputLevelList[k].equalsIgnoreCase(actualLevelList[r])){
													matchingResult = true
													
												}
											}
										}
									}else{
										return KPCommonPage.returnFailureOutcome(browser, "LevelListIssue", "Actual Level List is Either Null or Empty ")
									}
								}
							}
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
						}
						browser.click CLICK_PODS_BACK
						browser.delay(2000)
					}
					if(matchingResult){
						return new SuccessOutcome()
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "LevelNotMatchIssue", "Level  does not match.")
					}
		}*/

		//To verify that the result displayed for the selected Min and Max Duration through filter are shown correctly
		/*def static displayedMinAndMaxDurationFilter = { browser, formData ->
					def minHrs = KPCommonPage.minTimeData
					def maxHrs = KPCommonPage.maxTimeData
					def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
					def result = false			
						outerloop:for(int i = 0; i < allResult.size(); i++){
							def allResultElement = browser.getListElements(POD_LIST)
							browser.scrollToElement(allResultElement[i])
							browser.delay(1000)
							browser.clickElement allResultElement[i]
							browser.delay(3000)
							  if(browser.isDisplayed(OVERVIEW)){	
								   browser.click OVERVIEW
								   browser.delay(2000)
								   String[] duration = browser.gettext("//div[h5[text()='Duration']]/following-sibling::div//span").split(" ")
								   println "....Duration on web is ......."+duration[0]
									def hours = Integer.parseInt(duration[0])
									def minHours = Integer.parseInt(minHrs)
									def maxHours = Integer.parseInt(maxHrs)
									if(hours>=minHours && hours<=maxHours){
										result = true
									}else{
										result = false
										break outerloop
									}   
							  }else{
							       return KPCommonPage.returnFailureOutcome(browser, "OverviewDisplayedIssue", "Overiview is not displayed.")
							  }
							  browser.click CLICK_PODS_BACK
							  browser.delay(2000)
						}
						if(result){
							return new SuccessOutcome()
						}else{
							return KPCommonPage.returnFailureOutcome(browser, "HoursDisplayIssue", "Hours does not match with the input filters.")
						}
				
		}*/

		//To verify that the result displayed for the selected Sort By through filter are shown correctly
		/*def static final displayedSortByFilter = { browser, formData ->
			def sortBy = KPCommonPage.sortByData
					def allResult = KPCommonPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
					def noOfPeopleList = browser.getLists("//h5[contains(text(),'No of People in the Batch')]")
					browser.delay(1000)
						if(sortBy.equalsIgnoreCase("Alphabetical Z-A")){
							def result = KPCommonPage.isSorted(allResult)
							if(!result){
								return new SuccessOutcome()
							}else{
								return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical Z-A is not Working.")
							}
						}else if(sortBy.equalsIgnoreCase("Alphabetical A-Z") || sortBy.equalsIgnoreCase("Sort by Name")){
							def result = KPCommonPage.isSorted(allResult)
							if(result){
								return new SuccessOutcome()
							}else{
								return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical Z-A is not Working.")
							}
					   }else if(sortBy.equalsIgnoreCase("Sort by No. of People")){
							 def result = KPCommonPage.isSortedByNoOfPeople(browser,NO_OF_PEOPLE)
							 if(result){
								 return new SuccessOutcome()
							 }else{
								 return KPCommonPage.returnFailureOutcome(browser, "SortByNoOfPeopleIssue", "Sort By Number of people is not Working.")
							 }
					   }
		}*/
		
		//To get the total viewing Pod's count in search pod page.
		def static final getTotalViewingPodsCount = {browser, formData ->
			
			def totalResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(POD_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					//browser.click FIFTY_RESULT
					browser.delay(3000)
					totalResult = browser.gettext("//p[@class='clearfix content output-count-header ng-binding']").split(" ")[3].trim()					
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "PodsDisplayedIssue", "Pod list is not displayed.")
			}
			
			return totalResult
			
		}
		
		//to remove the selected skill from the autosuggest in search pod page
		/*def static final removeSelectedSkills = { browser, formData ->
			
			def dataToRemoveList = KPCommonPage.skillListsFilter
			SearchPodPage.getPodTextList(browser,ELEMENT,POD_LIST,FIFTY_RESULT)
			def beforeSelectCount = KPCommonPage.viewCount
			def beforeAdding =  Integer.parseInt(beforeSelectCount)
			if(dataToRemoveList != null || !dataToRemoveList.isEmpty()){
				for(int i=0;i<dataToRemoveList.size();i++){
				    browser.delay(4500)
					KPCommonPage.removeSelectedAutosuggest( browser, SELCTED_SKILLLIST,SKILL_REMOVE_MARK, dataToRemoveList[i])
				}
				def viewingCount = SearchPodPage.getTotalViewingPodsCount(browser,formData)
				browser.delay(4500)
				def afterAdding =  Integer.parseInt(viewingCount)
				if(beforeAdding==afterAdding){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "RemoveSelectedAutosuggestIssue", "After Removing autosuggest,the count is not changing.")
			    }
			}else{
			        return KPCommonPage.returnFailureOutcome(browser, "FetchingSkillListIssue", "Unable to fetch Skill List from KPCommon Page. ")
			}	
				
		}
		*/
		
		
		
	}
}