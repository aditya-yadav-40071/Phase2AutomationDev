package com.KPPhaseTwo.app.pages.admins

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.web.WebForm
import com.KPPhaseTwo.web.WebPage
import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage


final class FollowedOrganizationsListPage extends WebPage {

	//Override
	def populateData = {browser, formKey, formData ->
		if(formKey.equals("primarySearchDetails")){
			new FollowedOrganizationslistForm().populateFields(browser, formData);
		}
		else if(formKey.equals("filterDetails")){
			new FollowedOrganizationsFilterForm().populateFields(browser, formData);
		}

	}

	//Override
	/*def submit = {browser, formKey, formData ->
	 println ("Submit method in Followed Organization Page")
	 if(formKey.equals("primarySearchDetails")){
	 new FollowedOrganizationslistForm().submit(browser, formData)
	 }
	 }*/






	def static getTotalViewingPodsCount = {browser, formData ->
		new FollowedOrganizationslistForm().getTotalViewingPodsCount browser, formData
	}

	def static isErrorMessageCorrect = {browser, formData ->
		new FollowedOrganizationslistForm().isErrorMessageCorrect browser, formData
	}

	def static sameFilterValuesAsSearch = {browser, formData ->
		new FollowedOrganizationslistForm().sameFilterValuesAsSearch browser, formData
	}

	def static organizationsDisplayed = {browser, formData ->
		new FollowedOrganizationslistForm().organizationsDisplayed browser, formData
	}

	def static correctOrgDisplayed = {browser, formData ->
		new FollowedOrganizationslistForm().correctOrgDisplayed browser,formData
	}

	def static correctOrgProfile = {browser, formData ->
		new FollowedOrganizationslistForm().correctOrgProfile browser, formData
	}

	def static getOrgTextList = {browser, element, podList, fiftyRes->
		println "......................"
		new FollowedOrganizationslistForm().getOrgTextList browser, element, podList, fiftyRes
	}

	def static getPageCount = {browser ->
		new FollowedOrganizationslistForm().getPageCount browser
	}

	def static isCorrectLocationList = {browser, formData ->
		new FollowedOrganizationsFilterForm().isCorrectLocationList browser, formData
	}

	def static isCorrectIndustryList = {browser, formData ->
		new FollowedOrganizationsFilterForm().isCorrectIndustryList browser, formData
	}

	def static isCorrectSortingList = {browser, formData ->
		new FollowedOrganizationsFilterForm().isCorrectSortingList browser, formData
	}
	
	def static addToFollowedOrgPage ={browser, formData ->
		new FollowedOrganizationsFilterForm().addToFollowedOrgPage browser, formData
	}
	
	def static followedOrgDsplyd ={browser, formData ->
		new FollowedOrganizationsFilterForm().followedOrgDsplyd browser, formData
	}
	
	def static removeFrmFollowedList ={browser, formData ->
		new FollowedOrganizationsFilterForm().removeFrmFollowedList browser, formData
	}
	
	def static isFollowedOrgRemoved ={browser, formData ->
		new FollowedOrganizationsFilterForm().isFollowedOrgRemoved browser, formData
	}
	
	def static followOrg ={browser, formData ->
		new FollowedOrganizationsFilterForm().followOrg browser, formData
		
	}



	static final class FollowedOrganizationslistForm extends WebForm {

		//Followed Organizations form elements

		private static final def SEARCH_TEXT = ".//input[@placeholder='Search'][@style='']"

		private static final def LOCATION = ".//md-select[@placeholder='Select Location']"

		private static final def LOCATION_LIST = "//md-option[@ng-value='location.placeId']/div[@class='md-text ng-binding']"

		private static final def INDUSTRY = ".//md-select[@placeholder='Select Industry']"

		private static final def INDUSTRY_LIST = "//md-option[@ng-value='industry.industryId']"

		private static final def SEARCH = "//button[@class='full-width button-primary']"

		private static final def ORG_LIST = "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def LEVEL_LIST = ".//*[@ng-value='level']"

		private static final def FILTER_INDUSTRY = "//md-select[@ng-model='functionCall.filter.industry']"

		private static final def FILTER_INDUSTRY_LIST = "//md-option[@ng-value='opt.industryId']"

		private static final def FILTER_LOCATION = "//md-select[@ng-model='functionCall.filter.location']"

		private static final def FILTER_LOCATION_LIST = "//md-option[@ng-value='opt.placeId']"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav/div[2]/img"

		private static final def ERROR_MSG = "//div[@class='list-section col-sm-12 col-md-12 visible-lg']//div[@ng-show='isListEmpty']/span"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def OVERVIEW = ".//a[@class='select individual-tab']"

		private static final def CLICK_PODS_BACK = ".//*[@id='breadcrumbox']/ol/li[2]/a"

		private static final def FIRST_ORG = "//a[@class='content-name job-title pointer title-name blue ng-binding']"

		private static final def ORG_INDUSTRIES = "//span[@ng-if='industry']"

		private static final def ORG_LOCATIONS = ".//span[@class='pull-left content-name smallText mt_10 mr30 ng-binding']"   //"//span[@class='pull-left content-name smallText mt_10 ng-binding']"

		private static final def ORG_PROFILE_NAME = "//h4[@class='primary_heading mb50 capitalize ng-binding']"

		private static final def LAST_PAGE_NO = "//li[@class='pagination-page ng-scope active']/a"

		private static final def FIRST_BUTTON = "//li[@class='pagination-first ng-scope']/a"

		private static final def LAST_BUTTON = "//li[@class='pagination-last ng-scope']/a"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"

		private static final def FIELDS = [SEARCH_TEXT, INDUSTRY, LOCATION]// the error fields.
		
		private static final def FORM_ERROR = ""

		private static final def FIELD_ERROR = ""

		private static final def ERROR_MESSAGE_FIELDS = [FORM_ERROR, FIELD_ERROR]

		//error message map (Key-Value Pair)
		def static final searchPodPageErrorMessageMap = [followListEmpty : 'You are not following any organizations yet!!']

		//To enter data
		def static final populateFields = { browser, formData ->
			println ("FollowedOrganizationForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(SEARCH_TEXT)){
						KPCommonPage.firstOrgName = formData[i]
						browser.populateField(FIELDS[i],formData[i])
					}
			/*		if(FIELDS[i].equals(LOCATION)){
						browser.click LOCATION
						browser.delay(1000)
						def locationList = []
						if(formData[i].contains(",")){
							def data = formData[i].split(",")
							for(int j = 0; j < data.length; j++){
								locationList.add(data[j])
								KPCommonPage.selectAutoComplete(browser, LOCATION_LIST, data[j])
							}
						}else{
							locationList.add(formData[i])
							KPCommonPage.selectAutoComplete(browser, LOCATION_LIST , formData[i])
						}
						browser.click(ELEMENT)
						KPCommonPage.locationList = locationList
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
					}*/
					browser.delay(1000)
				}
			}
			return outcome
		}

		


		//to get all the Organization names from Search pod list page in a list
		public static def getOrgTextList(def browser,def element, def orgList,def fiftyRes){
			println "........INSIDE getorgtextlist method in filter page............"
			def allResult
			browser.scrollToElement2(element)
			browser.delay(3000)
			if(browser.isDisplayed(orgList)){
				println ".....ORG LIST is Displayed......"
				if(browser.isDisplayed(fiftyRes)){
					browser.click fiftyRes
					browser.delay(3000)
					allResult = browser.getLists(orgList)
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FiftyResultDisplayedIssue", "Option for fifty result display in a page is not displaying.")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationDisplayedIssue", "Org list is not displayed.")
			}
			return allResult
		}

		def static getPageCount(def browser){
			println "Inside KPCommon pagecount"
			int lastPage=0
			if(browser.checkEnabled(LAST_BUTTON)){
				browser.click LAST_BUTTON
				browser.delay(1000)
			}
			browser.delay(1000)
			def pageText = browser.gettext(LAST_PAGE_NO)
			browser.delay(1000)
			lastPage = pageText.toInteger()
			println "...lastPage......"+lastPage
			browser.click FIRST_BUTTON
			browser.delay(1500)
			lastPage
		}





		//To get the total viewing Pod's count in search pod page.
		def static final getTotalViewingPodsCount = {browser, formData ->

			def totalResult
			browser.scrollToElement2(ELEMENT)
			browser.delay(2000)
			if(browser.isDisplayed(ORG_LIST)){
				if(browser.isDisplayed(FIFTY_RESULT)){
					browser.click FIFTY_RESULT
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


		//To verify the error message being displayed in followed organization list page
		def static final isErrorMessageCorrect = {browser, formData ->
			def actMsg = browser.gettext(ERROR_MSG)
			if(browser.isDisplayed(ERROR_MSG)){
				if(searchPodPageErrorMessageMap.followListEmpty.equalsIgnoreCase(actMsg.trim())){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "ErrorMessageComparisionIssue", "After Comparision Error message is not displaying correctly")
				}
			}else {
				return KPCommonPage.returnFailureOutcome(browser, "ErrorMessageDisplayIssue", "Form Error Message is not Appearing.")
			}

		}


		//To verify that the Organization list is displayed on clicking search button
		def static final organizationsDisplayed = { browser, formData ->
			if(browser.isDisplayed(ORG_LIST)){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationNotDisplayedIssue", "Organizations are not displayed on search")
			}
		}



		//To verify that the primary searched location and industry are displaying in the filters too
		def static final sameFilterValuesAsSearch = { browser, formData ->
			if(browser.isDisplayed(FILTER_INDUSTRY) && browser.isDisplayed(FILTER_LOCATION)){
				browser.click FILTER_INDUSTRY
				browser.delay(1000)
				def allIndustry = browser.getLists(FILTER_INDUSTRY_LIST)
				browser.click(ELEMENT)
				browser.delay(1000)
				browser.click FILTER_LOCATION
				browser.delay(1000)
				def allLocation = browser.getLists(FILTER_LOCATION_LIST)
				if(allIndustry?.sort(false) == KPCommonPage.industryLists?.sort(false) && allLocation?.sort(false) == KPCommonPage.locationList?.sort(false)){
					KPCommonPage.industryLists = []
					KPCommonPage.locationList = []
					return new SuccessOutcome()
				}else{
					KPCommonPage.industryLists = []
					KPCommonPage.locationLists = []
					return KPCommonPage.returnFailureOutcome(browser, "Industry&LocationIssue", "Industry and location searched does not match with the displayed option in Filters.")
				}
			}
		}

		def static final correctOrgDisplayed = { browser, formData ->

			def allResult = FollowedOrganizationsListPage.getOrgTextList(browser,ELEMENT,ORG_LIST,FIFTY_RESULT)
			println "....allResult....."+allResult
			def matchingResult = false
			int lastPage = FollowedOrganizationsListPage.getPageCount(browser)
			def isLocationPresent
			for(int w=0;w<lastPage;w++){
				outerloop:for(int i = 0; i < allResult.size(); i++){
					def allResultElement = browser.getListElements(ORG_LIST)
					browser.scrollToElement(allResultElement[i])
					browser.delay(2000)
					if(browser.isDisplayed(ORG_INDUSTRIES)){
						browser.delay(2000)
						def orgList = browser.getLists(ORG_INDUSTRIES)
						def listOfactIndustries = browser.getLists(ORG_INDUSTRIES)
						println "......listOfIndustries......."+listOfactIndustries
						def listOfactLocations = browser.getLists(ORG_LOCATIONS)
						println "......listOfactLocations......."+listOfactLocations
						def industries = KPCommonPage.industryLists
						def locations = KPCommonPage.locationList
						isLocationPresent =  locations.containsAll(listOfactLocations)
						for(int j = 0;j < industries.size();j++){
							if(listOfactIndustries.contains(industries[j])){
								matchingResult = true
								println "....PASS...."
							}else{
								return KPCommonPage.returnFailureOutcome(browser, "IndustriesAndLocationIssues", "Industries and Locations does not match.")
							}
						}
					}else{
						return KPCommonPage.returnFailureOutcome(browser, "OrganizationListDisplayedIssue", "Organizations List does not appear.")
					}
				}
				if(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
				}
			}
			if(matchingResult && isLocationPresent){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "IndustryandLocationMismatchIssue", "IndustryAndLocation does not match.")
			}

		}



		def static final correctOrgProfile = {browser, formData ->
			if(browser.isDisplayed(ORG_PROFILE_NAME)){
				def actText = browser.gettext(ORG_PROFILE_NAME)
				if(actText.contains(KPCommonPage.firstOrgName.trim())){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "OrganizationProfileNameDisplayIssue", "The Name on Organization Profile page is displaying incorrect")
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationsProfileNameNotDisplayIssue", "Organizations Profile Name is not appearing in profile page")
			}

		}

	}



	static final class FollowedOrganizationsFilterForm extends WebForm {

		private static final def LOCATION_FILTER = "//md-select[@ng-model='functionCall.filter.location']"

		private static final def LOCATION_FILTER_LIST = "//md-option[@ng-value='opt.placeId']"

		private static final def LOCATION_RESULT_LIST = "//span[@class='pull-left content-name smallText mt_10 ng-binding']"

		private static final def INDUSTRY_RESULT_LIST = "//div[@class='list-section col-sm-9 col-md-9']/div[2]//span[@ng-if='industry']"

		private static final def INDUSTRY_FILTER = "//md-select[@ng-model='functionCall.filter.industry']"

		private static final def INDUSTRY_FILTER_LIST = "//md-option[@ng-value='opt.industryId']"

		private static final def SORTBY_FILTER = "//select[@ng-model='functionCall.sortBy']"

		private static final def ORG_LIST =  "//a[contains(@class,'content-name job-title pointer title-name blue')]"

		private static final def ELEMENT = "html/body/div[1]/div[1]//nav/div[2]/img"

		private static final def FILTER = "//div[@class='filter-container']"
		
		private static final def FOLLOW = "//a[@class='pull-left link-primary smallText display-block orange pointer mr30 ng-binding']"
		
		private static final def FIRST_ORG_NAME = "//a[@class='content-name job-title pointer title-name blue ng-binding']"
		
		private static final def FOLLOWED_ORG = "//a[@class='content-name job-title pointer title-name blue capitalize ng-binding']"
		
		private static final def UNFOLLOW = "//a[@class='pull-left link-primary smallText display-block orange pointer mr30 ng-binding']"

		private static final def FIFTY_RESULT = "//input[@id='item5']/following-sibling::label"

		private static final def FIRST_BUTTON = "//li[@class='pagination-first ng-scope']/a"

		private static final def LAST_BUTTON = "//li[@class='pagination-last ng-scope']/a"

		private static final def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"



		private static final def FIELDS = [LOCATION_FILTER, INDUSTRY_FILTER, SORTBY_FILTER]// the error fields.
		//To enter data
		def static final populateFields = { browser, formData ->
			println ("FollowedOrganizationsFilterForm.populateFields - data: " + formData)
			def outcome = WebForm.checkFormFieldsData(formData, FIELDS)
			if(outcome.isSuccess()){
				for(int i = 0; i < FIELDS.size(); i++){
					browser.delay(1000)
					if(FIELDS[i].equals(LOCATION_FILTER)){
						if(!formData[i].equals("")){
							browser.click LOCATION_FILTER
							browser.delay(2000)
							def followedLocationList = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int k = 0; k < data.length; k++){
									followedLocationList.add(data[k])
									KPCommonPage.selectAutoComplete(browser, LOCATION_FILTER_LIST , data[k])
								}
							}else{
								followedLocationList.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, LOCATION_FILTER_LIST , formData[i])
							}
							KPCommonPage.followedLocationListsFilter = followedLocationList
							browser.click(ELEMENT)
						}
					}

					if(FIELDS[i].equals(INDUSTRY_FILTER)){
						if(!formData[i].equals("")){
							browser.click INDUSTRY_FILTER
							browser.delay(2000)
							def followedIndustryLists = []
							if(formData[i].contains(",")){
								def data = formData[i].split(",")
								for(int k = 0; k < data.length; k++){
									followedIndustryLists.add(data[k])
									KPCommonPage.selectAutoComplete(browser, INDUSTRY_FILTER_LIST , data[k])
								}
							}else{
								followedIndustryLists.add(formData[i])
								KPCommonPage.selectAutoComplete(browser, INDUSTRY_FILTER_LIST , formData[i])
							}
							KPCommonPage.followedIndustryListsFilter = followedIndustryLists
							browser.click(ELEMENT)
						}
					}

					if(FIELDS[i].equals(SORTBY_FILTER)){
						if(!formData[i].equals("")){
							def sortData
							browser.click SORTBY_FILTER
							browser.delay(1000)
							sortData = formData[i]
							browser.selectDropdownValue(SORTBY_FILTER,formData[i])
							browser.delay(2000)
							KPCommonPage.searchOrgSortByData = sortData
						}
					}
					browser.delay(1500)
				}
			}
			return outcome
		}



		def static final isCorrectLocationList = {browser, formData ->
			println "...INSIDE Location METHOD..."
			def matchingResult
			browser.delay(2000)
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(3000)
				int lastPage = FollowedOrganizationsListPage.getPageCount(browser)
				println "....lastPage....."+lastPage
				for(int w=0;w<lastPage;w++){
					if(browser.isDisplayed(LOCATION_RESULT_LIST)){

						def actualLocList = browser.getLists(LOCATION_RESULT_LIST)
						def expectedLocList = KPCommonPage.followedLocationListsFilter
						if(expectedLocList != null || !expectedLocList.isEmpty()){
							outerloop:for(int k = 0; k < expectedLocList.size(); k++){
								for(int r = 0; r < actualLocList.size(); r++){
									if(expectedLocList[k].equalsIgnoreCase(actualLocList[r]) || actualLocList[r].equalsIgnoreCase("Bengaluru")){
										matchingResult = true
										println ".......Locations are Matched......."

									}else{

										matchingResult = false
										break outerloop
									}

								}
							}
						}
					}
				}
				if(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
				}
			}
			if(matchingResult){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "IncorrectLocationDisplayIssue", "Location does not match with the input filter Location.")
			}



		}
		
		
		
		
		
		
		def static final isCorrectIndustryList = {browser, formData ->
			println "...INSIDE INDUSTRY METHOD..."
			def matchingResult
			browser.delay(2000)
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(3000)
				int lastPage = FollowedOrganizationsListPage.getPageCount(browser)
				println "....lastPage....."+lastPage
				for(int w=0;w<lastPage;w++){
					if(browser.isDisplayed(INDUSTRY_RESULT_LIST)){
						def totalOrg = browser.getLists(ORG_LIST)
						def industriesPerOrg = browser.getLists("//div[@class='list-section col-sm-9 col-md-9']/div[2]//span[@ng-if='industry']")
						def actualIndustryList = browser.getLists(INDUSTRY_RESULT_LIST)
						def expectedIndustryList = KPCommonPage.followedIndustryListsFilter
						if(expectedIndustryList != null || !expectedIndustryList.isEmpty()){
							println ".... Industries .. list..."+browser.getLists("//div[@class='mt_5 ng-scope']")
							def indLst=[]
							def lst
							outerloop:for(int k = 2; k < totalOrg.size()+2; k++){

									industriesPerOrg = browser.getLists(INDUSTRY_RESULT_LIST.replace("div[2]","div["+k+"]"))
										for(int r=0;r<industriesPerOrg.size();r++){
											lst = browser.getTexts(industriesPerOrg[r]).replaceAll(" ,", "")
											indLst.add(lst)
											
										}
									if(indLst.containsAll(expectedIndustryList)){
										matchingResult = true
									}else{
									    matchingResult = false
										break outerloop
									}	
						
							}
						}
					}
				}
				if(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
				}
			}
			if(matchingResult){
				return new SuccessOutcome()
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "IncorrectIndustryDisplayIssue", "Industry does not match with the input filter Location.")
			}



		}

				def static final isCorrectSortingList = {browser, formData ->
			def sortBy = KPCommonPage.searchOrgSortByData
			def matchingResult
			browser.delay(2000)
			if(browser.isDisplayed(FIFTY_RESULT)){
				browser.click FIFTY_RESULT
				browser.delay(3000)
				int lastPage = FollowedOrganizationsListPage.getPageCount(browser)
				println "....lastPage....."+lastPage
				for(int w=0;w<lastPage;w++){
					def allResult = browser.getLists(ORG_LIST)
					browser.delay(1000)
					if(sortBy.equalsIgnoreCase("Alphabetical")){
						def result = KPCommonPage.isSorted(allResult)
						if(result){
							matchingResult = true
							println "... PASS : List is in Alphabetical order ..."
						}else{
							matchingResult = false
						}
					}
				}
				if(browser.checkEnabled(PAGINATION_NEXT)){
					browser.click PAGINATION_NEXT
				}
			}
				if(matchingResult){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "SortByDisplayedIssue", "Sort By Alphabetical option is not Working.")
				}
			}

		
		
		
		//To add the first Organization to Followed Organization List page
		def static final addToFollowedOrgPage = { browser, formData ->
			browser.delay(3500)
			if(browser.isDisplayed(FOLLOW)){
				KPCommonPage.firstFollowedOrgName = browser.getLists(FIRST_ORG_NAME)[0]
				def followLinks = browser.getLists(FOLLOW)
				if(browser.gettext(FOLLOW).contains("Follow")){
					browser.delay(1500)
					browser.click FOLLOW
					browser.delay(1500)
					return new SuccessOutcome()
				}else{
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FollowLinkDisplayIssue", "Follow Link is not displayed.")
			}
		}

		//To verify that the followed organization is displayed in followed Organization page
		def static final followedOrgDsplyd = { browser, formData ->
			browser.delay(3500)
			if(browser.isDisplayed(FOLLOWED_ORG)){
				def name = browser.gettext(FOLLOWED_ORG)
				if(KPCommonPage.firstFollowedOrgName.equalsIgnoreCase(name)){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "FollowedOganizationIssue", "Followed Organization is not same in the followed org page.")
				}

			}else{
				return KPCommonPage.returnFailureOutcome(browser, "FollowedDisplayIssue", "Followed Organization is not displayed in the followed Organization page.")
			}
		}

		//To remove the first Organization from followed to search org page
		def static final removeFrmFollowedList = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(UNFOLLOW)){
				if(browser.gettext(UNFOLLOW).contains("UnFollow")){
					browser.click UNFOLLOW
					return new SuccessOutcome()
				}else{
					return new SuccessOutcome()
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from wishlist is not displayed.")
			}
		}

		//To verify that the followed org is removed from the followed org page
		def static final isFollowedOrgRemoved = { browser, formData ->
			browser.delay(3000)
			if(browser.isDisplayed(UNFOLLOW)){
				def name = browser.getLists(FOLLOWED_ORG)
				def result = false
				for(int i = 0; i < name.size(); i++){
					if(KPCommonPage.firstFollowedOrgName.equalsIgnoreCase(name[i])){
						result = false
						break
					}else{
						result = true
					}
				}
				if(result){
					return new SuccessOutcome()
				}else{
					return KPCommonPage.returnFailureOutcome(browser, "AddToWishlistDisplayIssue", "Remove from followed org is not displayed.")
				}
			}else{
				return new SuccessOutcome()
			}
		
		}
		
	
		def static final followOrg = { browser, formData ->
			browser.delay(2500)
			if(browser.isDisplayed(ORG_LIST)){
				def orgNames = browser.getLists(ORG_LIST)
				def followLinks = browser.getLists(FOLLOW)
				def xpathToSelect = browser.getListElements(FOLLOW)
				if(orgNames!=null){
					
					while(browser.isDisplayed(FOLLOW)){
							browser.delay(800)
							browser.click FOLLOW
					}
					
				}
			}else{
				return KPCommonPage.returnFailureOutcome(browser, "OrganizationListDisplayIssue", "Organizations are not displaying in followed Organization page.")
			}
		
		}
	
   }	
		
}
	
		
		
		  
          
		
	