package com.KPPhaseTwo.app.pages.pods

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage

import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage

/**
 * Created by Krithika Bafna on 12/04/2017
 */

final class RecommendedTrainingPage extends WebPage {

	//Override
	def populateData = { browser, formKey, formData ->
		new RecommendedTrainingForm().populateFields(browser, formData);
	}
	//To get the number of pods in Recommended training page
	def static verifyPodsInRecommended = { browser, formData ->
		new RecommendedTrainingForm().verifyPodsInRecommended browser, formData
	}
	
	//To verify if the skills are entered.
	def static skillsEntered = {browser, formData ->
		new RecommendedTrainingForm().skillsEntered browser, formData
	}
	
	//To verify number of pods displayed for every click of number in ruler
	def static podsAndRulerNoMatch = {browser, formData ->
		new RecommendedTrainingForm().podsAndRulerNoMatch browser, formData
	}
	
	//To verify wishlisted pod displayed in the wishlist page
	def static wishPodDisplayed = {browser, formData ->
		new RecommendedTrainingForm().wishPodDisplayed(browser,  formData)
	}
	
	//To click on a particular pod in the list page
	def static clickPodToBuy = {browser, formData ->
		new RecommendedTrainingForm().clickPodToBuy browser, formData
	}
	
	static final class RecommendedTrainingForm extends WebForm {

		//Recommended Training Form elements
		private static final def POD_NAMES = "//div[@class='clearfix content individual-pod ng-scope']/div/div/div/h3/a"
		
		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"
		
		private static final def FILTER_SKILL = "//input[@placeholder='Skills']"
		
		private static final def FILTER_INDUSTRY = ".//md-select[@placeholder='Industry']"
		
		private static final def FILTER_LEVEL = ".//md-select[@placeholder='Level']"
		
		private static final def FILTER_MIN_DURATION = "//md-select[@placeholder='Min Duration']"
		
		private static final def FILTER_MAX_DURATION = "//md-select[@placeholder='Max Duration']"
		
		private static final def FILTER_SORT_BY = "//select[@ng-model='sortBy']"
		
		private static final def FILTER_SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"
		
		private static final def FILTER_INDUSTRY_LIST = ".//*[@ng-value='industry']"
		
		private static final def FILTER_LEVEL_LIST = ".//*[@ng-value='level']"
		
		private static final def CLICK_ELEMENT = "html/body/md-backdrop"
		
		private static final def LIST_OF_SKILLS = "//span[@class='ng-scope']/p"
		
		private static final def FILTER_DURATION_LIST = "//md-option[@ng-value='duration']"
		
		//pagination elements
		private static def LASTBUTTON = "//li[@class='pagination-last ng-scope']/a"
	
		private static def FIRSTBUTTON = "//li[@class='pagination-first ng-scope']/a"
	
		private static def NEXTBUTTON = "//li[@class='pagination-next ng-scope']/a"
	
		private static final def LASTPAGENO = "//li[@class='pagination-page ng-scope active']/a"
		
		//Items per page Ruler elements
		private static final def ITEMS_IN_RULER = "//div[@ng-repeat='item in itemsPerPageList']/label"
		
		private static final def FIELDS = [FILTER_SKILL, FILTER_INDUSTRY, FILTER_LEVEL, FILTER_MIN_DURATION, FILTER_MAX_DURATION, FILTER_SORT_BY]
		
		//To enter data
		def static final populateFields = { browser, formData ->
			println ("RecommendedTrainingForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(FILTER_SKILL)){
						if(!formData[i].equals("")){
							def skillsList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int j = 0; j < data.length; j++){
									skillsList.add(data[j])
									browser.populateField(FIELDS[i], data[j])
									println ".....After populate........"
									browser.delay(4000)
									KPCommonPage.selectAutoComplete(browser, FILTER_SKILLS_LIST, data[j])
									println ".....After Selecting....."
								}
							}else{
								skillsList.add(formData[i])
								browser.populateField(FIELDS[i],formData[i])
								println "......After Else part........"
								browser.delay(4000)
								KPCommonPage.selectAutoComplete(browser, FILTER_SKILLS_LIST , formData[i])
							}
							println ".....BeforeAdding to KP Page........"
							KPCommonPage.skillListsFilter = skillsList
							println "...Populate...Skill list....."+KPCommonPage.skillListsFilter
						}
					}
					if(FIELDS[i].equals(FILTER_INDUSTRY)){
						if(!formData[i].equals("")){
							browser.click FILTER_INDUSTRY
							browser.delay(1000)
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
							browser.click(CLICK_ELEMENT)
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
							browser.click(CLICK_ELEMENT)
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
							KPCommonPage.selectAutoComplete(browser, FILTER_DURATION_LIST , formData[i])
							browser.delay(1000)
							browser.click(CLICK_ELEMENT)
							browser.scrollToElement2(FILTER_MAX_DURATION)
							browser.delay(1000)
							browser.click FILTER_MAX_DURATION
							browser.delay(1000)
							maxHrsData = formData[i+1]
							KPCommonPage.selectAutoComplete(browser, FILTER_DURATION_LIST , formData[i+1])
							browser.delay(2000)
							KPCommonPage.minTimeData = minHrsData
							KPCommonPage.maxTimeData = maxHrsData
							println ".........After min and max input..........."
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

		//To verify the number of pods in Recommended Training page
		def static final verifyPodsInRecommended = {browser, formData ->
			def podList = []
			def listOfPods = browser.getLists(POD_NAMES)
			podList.addAll(listOfPods)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				def podsInNextPage = browser.getLists(POD_NAMES)
				podList.addAll(podsInNextPage)
			}
			if(podList.size().equals(KPCommonPage.noOnDashboard)){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "mismatchInNumber", "No of pods not the same.")
		}
		
		//To verify number of pods displayed for every click of number in ruler
		def static final podsAndRulerNoMatch = {browser, formData ->
			def itemsRulerElements = []
			def flag
			itemsRulerElements = browser.getListElements(ITEMS_IN_RULER)
			def rulerOptions = browser.getLists(ITEMS_IN_RULER)
			for(int i=0;i<itemsRulerElements.size();i++){
				def noOfPodsPerPage = []
				browser.scrollToElement(browser.getElement(Browser.XPATH, ITEMS_IN_RULER))
				if(browser.isDisplayed(ITEMS_IN_RULER)){
					browser.clickElement(itemsRulerElements[i])
				}else
			        return KPCommonPage.returnFailureOutcome(browser, "rulerNotDisplayed", "Ruler not displayed")
				browser.delay(3000)
				def listOfPods = browser.getLists(POD_NAMES)
				noOfPodsPerPage.add(listOfPods.size())
				while(browser.checkEnabled(PAGINATION_NEXT)){
					browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
					browser.click PAGINATION_NEXT
					browser.delay(1500)
					def podsInNextPage = browser.getLists(POD_NAMES)
					noOfPodsPerPage.add(podsInNextPage.size())
				}
				browser.delay(1500)
				if(Collections.max(noOfPodsPerPage)<= (rulerOptions[i].toInteger())){
					flag = true
				}else{
					flag=false
					return KPCommonPage.returnFailureOutcome(browser, "itemPodMismatch", "No of items and pods number have a mismatch.")
				}
			}
			if(flag){
				return new SuccessOutcome()
			}
		}
		
		//To verify if pod displayed in wshlist page after adding to wishlist from Recommended Trainings page
		def static final wishPodDisplayed = {browser, formDate ->
			if(browser.isDisplayed(POD_NAMES)){
				def listOfwishPods = KPCommonPage.paginationData(browser, POD_NAMES, "textData")
				println KPCommonPage.firstPodName
				if(listOfwishPods.contains(KPCommonPage.firstPodName)){
					println KPCommonPage.firstPodName
					println "Added to wishlist list"
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "podMissingInWishlist", "Pod not reflecting in the wishlist.")
			}
		}
		
		//To verify if skills are entered after selecting.
		def static final skillsEntered = {browser, formData ->
			if(browser.isDisplayed(LIST_OF_SKILLS)){
				def listOfSkills = browser.getLists(LIST_OF_SKILLS)
				if(KPCommonPage.skillListsFilter.containsAll(listOfSkills)){
					return new SuccessOutcome()
				}else
					return KPCommonPage.returnFailureOutcome(browser, "missingSkill", "Few skills missing in list.")
			}else
				return KPCommonPage.returnFailureOutcome(browser, "noElementFound", "Element Skill list not found.")
		}
		
		def static final clickPodToBuy  = {browser, formData ->
			println "Clicking pod-pagination"
			def flag
			KPCommonPage.podToBuy_Entry = formData[0]
			if(browser.checkEnabled(LASTBUTTON)){
				browser.click LASTBUTTON
				browser.delay(500)
			}
			browser.delay(500)
			int lastPage = (browser.gettext(LASTPAGENO)).toInteger()
			browser.delay(500)
			if(browser.checkEnabled(FIRSTBUTTON)){
				browser.click FIRSTBUTTON
				browser.delay(500)
			}
			for(int i=0;i<lastPage;i++){
			def listDataPerPage = browser.getLists(POD_NAMES)
			def listElementPerPage = browser.getListElements(POD_NAMES)
				for(int j=0;j<listDataPerPage.size();j++){
					if(listDataPerPage[j].equalsIgnoreCase(formData[0])){
						flag = true
						browser.clickElement(listElementPerPage[j])
						browser.delay(2000)
						break
					}else
						flag = false
				}
				if(flag==true){
					break
				}
				if(browser.checkEnabled(NEXTBUTTON)){
					browser.click NEXTBUTTON
					browser.delay(500)
				}
			}
			if(flag){
				return new SuccessOutcome()
			}else
				return KPCommonPage.returnFailureOutcome(browser, "podNotFound", "Pod not found in list.")
		}
	}
}
