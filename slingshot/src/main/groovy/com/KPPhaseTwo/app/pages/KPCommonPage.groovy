package com.KPPhaseTwo.app.pages

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.lang.*;

import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.tools.Browser


/**
 * @author Samir
 * Date: 20/2/2017
 * Common actions 
 */
class KPCommonPage {

	private static def RADIOID = ".//*[@name='radioName'][@value='radioValue']"

	private static def RADIO_BTN_XPATH1 = ".//input[@type='radio']"

	private static def SELECT_MONTH = ".//*[@class='ui-datepicker-month']"

	private static def SELECT_YEAR  = ".//*[@class='ui-datepicker-year']"

	private static def DAY = ".//*[@id='ui-datepicker-div']//a[@href='#']"

	private static def JOB_LAST_DATE = ".//*[@id='jobLastDate']"

	private static def JOB_EXPIRY_DATE = ".//*[@id='jobExpiryDate']"

	private static def JOB_POSTING_DATE = ".//*[@id='jobPostingDate']"

	private static def SELECT_DATE = "//a[.='value']"

	private static final def EDU_QUALIFICATION = ".//input[@name='multipleSelectEducation']"

	private static final def EDU_QUALIFICATION_AUTOSELECT = "//div[@class='autocomplete-list']/ul/li"

	private static final def SKILLS = ".//input[@name='multipleSelectSkills']"

	private static final def SKILLS_LIST = "//div[@class='autocomplete-list']/ul/li"

	private static final def PRE_CERTIFICATE = ".//input[@name='multipleSelectCertificates']"

	private static final def PRE_CERTIFICATE_LIST = "//div[@class='autocomplete-list']/ul/li"

	//Registration
	private static def city,industry

	public static def registrationData = [:]

	//Login
	private static def userName,passWord

	//Pods
	private static def levelLists, industryLists, podName, firstPodName, locationList, firstOrgName

	//Pods using filters
	private static def skillListsFilter,levelListsFilter,industryListsFilter,organizationListsFilter,minDurationListsFilter,maxDurationListsFilter,sortByListsFilter

	//Followed Organizations
	private static def followedLocationListsFilter,searchOrgSortByData,followedIndustryListsFilter
	private static def firstFollowedOrgName

	//Manage Users
	private static def firstUserInList,profileEmailId,profilePassword,usersList,manageUsersSortByData

	private static def adminPassword
    //User
	
	private static def firstName
	private static def middleName
	private static def lastName
	private static def userAge
	private static def userMobileNo
	private static def basicInfo = []
	//Invited User
	private static def invitedUserEmailId
	private static def invitedUserPassword
	private static def invitedUserSiteName
	private static def invitedUserFirstName

	//Organization
	private static def ViewOrgDataVerify = []
	private  static def podWishlistedList = []
	private static def podLst = []
	private static def OrgMobileNumber
	private static def adminEmailId

	private static def IMAGE_LOGO_ORG = "//img[@class='imgPosition']"
	private static def USER_PROFILE_PIC = ".//img[@class='profilePosition user-profile-picture']"
	private static def srcOrgLogoImage
	private static def srcUserProfilePic
	private static def viewSize = " ...View less"

	//Post a job
	private static def jobTitle,jobIndustry,eduQualification,jobSkills,prefferedCertificates
	private static def jobDetailList = []

	//Recommended Training
	private static def noOnDashboard

	//Pagination
	private static def SELECT_FIFTY = ".//*[@id='item_label50']"
	private static def PAGINATION_NEXT = "//li[@class='pagination-next ng-scope']/a"
	private static def sortByData,minTimeData,maxTimeData,viewCount,viewCountOrg

	//Pod details
	private static def pod_amount

	//Pod to buy
	private static def podToBuy_Entry
	private static def typeOfUser = "ADMIN"

	//Edit Organization Profile
	private static def firstNameAdmin

	//Edit User Profile
	private static def dataList = []
	private static def actualDataList = []
	private static def skills = []
	private static def allEducationDetails = []
	private static def WorkExperienceDetails = []
	private static def WorkExperienceJobRole = []
	private static def docName = []
	
	//To return Failure outcome
	static def returnFailureOutcome(def browser, def fileName, def message){
		println message
		browser.takeScreenShot(fileName)
		return new FailureOutcome(message)
	}

	//get coloumns from excel filed which has data
	static def getFinalFeilds(def data, def FEILDS){
		def formFeild = []
		for(int i =0; i<= data.size()-1; i++){
			if(data[i] != ""){
				formFeild.add(FEILDS[i])
			}
		}
		formFeild
	}

	//get coloumns from excel filed which has data
	static def getFinalData(def data, def FIELDS){
		def formData = []
		for(int i =0; i<= data.size()-1; i++){
			if(data[i] != ""){
				formData.add(data[i])
			}
		}
		formData
	}

	//To get Radio Button Id
	static def getRadioButtonField(def browser, def field, def data){
		def radioId
		def radioValue = browser.getLists(RADIO_BTN_XPATH1, "value")
		def radioNames = browser.getLists(RADIO_BTN_XPATH1, "name")
		for(int i=0; i<= radioValue.size() - 1; i++){
			if(radioValue[i].trim().equalsIgnoreCase(data.trim())){
				def value = radioValue[i].trim()
				radioId = RADIOID.replace("radioValue", value)
				for(int j=0;j<=radioNames.size() -1; j++){
					def name = radioNames[j].trim()
					if(field.contains(name)){
						name = radioNames[j].trim()
						radioId = radioId.replace("radioName", name)
						break
					}
				}
				break
			}
		}
		radioId
	}

	//To select from the auto complete
	static def selectAutoComplete(def browser, def autoCompleteList, def dataToSelect){
		println "Inside SELECT AUTO COMPLETE"
		def xpathToSelect
		def lists = browser.getLists(autoCompleteList)
		println "..........The Value from list is................ "+lists
		xpathToSelect = browser.getListElements(autoCompleteList)
		for(int i =0; i<= lists.size()-1;i++){
			println "..........The Value from list of i is "+i+" ................ "+ lists[i]
			println "..........dataToSelect is................................... "+ dataToSelect
			if(lists[i].trim().equalsIgnoreCase(dataToSelect.trim())){
				println "Matched"
				browser.delay(3000)
				println "Clicking"
				browser.clickElement(xpathToSelect[i])
				println "CLICKED"
				browser.delay(2500)
				break
			}
		}
	}

	
	//i- .//div[@class="pac-container pac-logo"]/div
	///city- .//div[@class='pac-container pac-logo']/div/span[2]/span[@class='pac-matched']
	//.//div[@class='pac-container pac-logo']/div/span[3]
	//To select city with state from the auto complete
	static def selectAutoCompleteForCity(def browser, def autoCompleteList, def stateAutoCompleteList, def dataToSelect){
		def xpathToSelect, stateSelect
		def lists = browser.getLists(autoCompleteList)
		println"lists------------------------------> "+lists
		xpathToSelect = browser.getListElements(autoCompleteList)
		stateSelect = browser.getLists(stateAutoCompleteList)
		println "lists "+lists+ "\nxpathToSelect "+xpathToSelect+ "\nstateSelect "+stateSelect
		for(int i =0; i<= lists.size()-1;i++){
			def wholeCityName = lists[i].trim() + ", "+  stateSelect[i].trim()
			println "wholeCityName : "+wholeCityName
			if(wholeCityName.trim().equalsIgnoreCase(dataToSelect.trim())){
				browser.delay(3000)
				browser.clickElement(xpathToSelect[i])
				browser.delay(1500)
				break
			}
		}
	}

	//to generate unique email address
	public static def  generateRandomEmailAddress(def data){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(10000);
		data = new StringBuilder(data).insert(data.indexOf("@"), randomInt).toString();
		return data;
	}

	//to generate unique phone no up to 10 digits
	public static def generateRandomCellNo(){
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		return number;
	}

	//To get all the data of pagination
	static def paginationData(def browser, def xpathForData, def attributeValue){
		println "Inside KPCommon"
		def listDataPerPage = []
		def allDataList = []
		if(browser.isDisplayed(SELECT_FIFTY)){
			browser.click SELECT_FIFTY
		}
		if(attributeValue.equals("textData")){
			listDataPerPage = browser.getLists(xpathForData)
			allDataList.addAll(listDataPerPage)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				listDataPerPage = browser.getLists(xpathForData)
				allDataList.addAll(listDataPerPage)
			}
		}
		else if(attributeValue.equals("xpaths")){
			listDataPerPage = browser.getListElements(xpathForData)
			allDataList.addAll(listDataPerPage)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				listDataPerPage = browser.getListElements(xpathForData)
				allDataList.addAll(listDataPerPage)
			}
		}
		else {
			listDataPerPage = browser.getLists(xpathForData,attributeValue)
			allDataList.addAll(listDataPerPage)
			while(browser.checkEnabled(PAGINATION_NEXT)){
				browser.scrollToElement(browser.getElement(Browser.XPATH, PAGINATION_NEXT))
				browser.click PAGINATION_NEXT
				browser.delay(1500)
				listDataPerPage = browser.getLists(xpathForData,attributeValue)
				allDataList.addAll(listDataPerPage)
			}
		}
		println allDataList
		allDataList
	}

	//try to remove the selected skill and organization from the filter pod search
	public static def removeSelectedAutosuggest(def browser,def autoSuggestList,def removeMarkList,def dataToRemove){
		def xpathToRemove
		def lists = browser.getLists(autoSuggestList)
		xpathToRemove = browser.getListElements(removeMarkList)
		for(int i =0; i<= lists.size()-1;i++){
			if(lists[i].trim().equalsIgnoreCase(dataToRemove.trim())){
				browser.delay(3500)
				browser.clickElement(xpathToRemove[i])
				browser.delay(3500)
				break
			}
		}
	}

	//to get all the pod names from Search pod list page in a list
	public static def getPodTextList(def browser,def element, def podList,def fiftyRes){

		def allResult
		browser.scrollToElement2(element)
		browser.delay(2000)
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

	//To check the list is alphabetically sorted or not
	public static def isSorted(def lst){
		def sorted = true;
		def temp
		if(lst.size()>0){
			
			for (int i = 1; i < lst.size(); i++) {
				if(lst.get(i-1).length()>lst.get(i).length()){
					temp     = lst[i-1]
					lst[i-1] = lst[i]
					lst[i]   = temp
				}
				println "The result is::::: "+lst.get(i-1).compareToIgnoreCase(lst.get(i))
				if(lst.get(i-1).compareToIgnoreCase(lst.get(i)) > 0)
				{
					println " Comparing => "+lst.get(i-1)+" with "+lst.get(i)
					sorted = false;
				}
			}
		}
		return sorted
	}


	public static def isPodWishlistPageEmpty(def browser, def formMsgPath){
		def errorMsg = browser.isDisplayed(formMsgPath)
		if(errorMsg){
			return errorMsg
		}else{
			return errorMsg
		}

	}

	//to check number of people are sorted in descending order or not
	public static def isSortedByNoOfPeople(def browser, def NO_OF_PEOPLE){
		browser.delay(1000)
		if(browser.isDisplayed(NO_OF_PEOPLE)){
			def result = false
			def noOfPeople=[]
			def allResult = browser.getLists(NO_OF_PEOPLE)
			def allResultElement = browser.getListElements(NO_OF_PEOPLE)
			outerloop:for(int i = 0; i < allResult.size(); i++){
				browser.scrollToElement(allResultElement[i])
				browser.delay(500)
				String[] duration = allResult[i].split(":")
				noOfPeople.add(Integer.parseInt(duration[1].trim()))
			}
			for (int i = 0; i < noOfPeople.size(); i++) {
				if (noOfPeople.get(i) < noOfPeople.get(i+1)) {
					return false
				}
			}
			return true
		}
		else{
			return KPCommonPage.returnFailureOutcome(browser, "NumberOfPeopleIssue", "Sort By No. of people is not working correct")
		}

	}


	//To Pick the date
	public static def datePicker(def browser, def formDate){
		def dateValue= formDate.split ("-")
		def dayValue = Integer.parseInt(dateValue.last()).toString()
		def monthValue = Integer.parseInt(dateValue[1])-1
		def yearValue = dateValue.first()
		browser.selectDropdownValue(SELECT_YEAR,yearValue)
		def monthInString=  browser.getMonthForInt(monthValue)
		browser.selectDropdownValue(SELECT_MONTH,monthInString)
		browser.selectDate(DAY,dayValue)
	}



	//To select the date from calender popup
	static def userDatePicker (def browser, def date){
		println "Inside KPCommonPage-datepicker"
		if(date.contains("/"))
		{
			def splittedDate=date.split("/")
			def dayValue = splittedDate[0]
			def monthValue = splittedDate[1]
			def yearValue = splittedDate[2]
			println dayValue+"month="+monthValue+"year="+yearValue
			browser.selectDropdownValue(SELECT_YEAR, yearValue)

			//selecting the month
			def monthNo = Integer.parseInt(monthValue)
			def	monthName= browser.getMonthFromNo(monthNo)
			def month = monthName.substring(0, Math.min(monthName.length(), 3));
			browser.selectDropdownValue(SELECT_MONTH, month)
			browser.delay(2000)
			//selecting the date

			def CHANGED_DATE = SELECT_DATE.replace('value', dayValue)
			browser.click CHANGED_DATE
			browser.delay(1500)
			//browser.selectingDate(SELECT_DATE, dayValue)
		}
		else
			println "Not a valid date"
	}
	
	
	public def static getFullName(def firstName,def middleName,def lastName){
		def fullName
		if(!firstName.equals(" ") && firstName!=null){
			if(!middleName.equals(" ") && middleName!=null){
				fullName = firstName +" "+ middleName
			}else{
				fullName = firstName
			}
			if(!lastName.equals(" ") && lastName!=null){
				fullName = fullName+" "+ lastName
			}else{
				fullName
			}
		}
		println "FULL NAME IS "+fullName
		return fullName
	}
}
