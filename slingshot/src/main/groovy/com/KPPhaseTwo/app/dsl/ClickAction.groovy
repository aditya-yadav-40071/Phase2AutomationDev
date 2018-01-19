package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.model.SuccessOutcome
import com.KPPhaseTwo.model.FailureOutcome
import com.KPPhaseTwo.tools.Browser

import com.KPPhaseTwo.app.pages.KPCommonPage


/* Class to take care of operations related to all click actions */
class ClickAction {

	private static Map<String, String> xPathMapping;

	private static final def LINK = "linkname"

	static {

		xPathMapping = new HashMap<String, String>();

		//List of buttons/links with its XPath in the path
		xPathMapping.put("login", ".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']");
		xPathMapping.put("leftMenuButton", "//button[@aria-expanded='false'][@role='button']");
		xPathMapping.put("registerFrmLftMenu", "//a[contains(text(), 'Register')]");
		xPathMapping.put("registerFrmPage", "//button[contains(text(), 'REGISTER')]");
		xPathMapping.put("organizationLnk", "//h4[contains(text(), 'ORGANIZATION')]");
		xPathMapping.put("nextBtn", "//button[contains(text(),'NEXT')]");
		xPathMapping.put("loginFromLeftMenu", ".//a[@href='#/account/login']");
		xPathMapping.put("individualRegister", ".//*[@class='kp-individual kp-large-two-tone-icons']/span[@class='path3']")
		xPathMapping.put("resgisterFromHome", ".//button[contains(@class, 'margin-auto display-block btn btn-primary btn-xlarge get-started-btn') and text() = 'REGISTER']")
		xPathMapping.put("podWorksLogo", "//a[@href='#/home']/img");
		xPathMapping.put("hire", "//a[@ui-sref='.jobDashboard']");

		//Organization Dashboard
		xPathMapping.put("editOrgOnDashboard", "//a[@class='profile-edit-link display-block ng-scope']/h5");
		xPathMapping.put("viewAllRecommended", "//a[@href='#/training/recommended']");
		xPathMapping.put("viewAllOngoing","//a[@ui-sref='kp.ongoingPodOrg']");

		//Change Password
		xPathMapping.put("changePassword", "//a[text()='Change Password']");

		//Search Pods
		xPathMapping.put("searchPods", "//a[text()='Search Pods']");
		xPathMapping.put("searchPodButton", "//button[@class='full-width button-primary']");
		xPathMapping.put("firstPod", "//div[2]/div[1]/div/div/h3/a");
		xPathMapping.put("podWishlist", "//a[contains(text(), 'Pod Wishlist')]");
		xPathMapping.put("dashBoardLink", ".//*[@id='breadcrumbox']/ol/li[1]/a");
		xPathMapping.put("dashboardSearchPodBtn", "//button[text()='Search']")
		//xPathMapping.put("viewAllWishList", ".//*[@id='breadcrumbox']/a[2]");

		//Ongoing Pods
		xPathMapping.put("viewBatchProgress","//a[text()='View Batch Progress']")

		//Forgot Password
		xPathMapping.put("forgotPassword", ".//*[@id='forgot_password']")
		xPathMapping.put("privacyPolicy", "//a[text()='Privacy Policy']");
		xPathMapping.put("termsOfUse", "//a[text()='Terms Of Use']");
		xPathMapping.put("aboutUs", "//a[text()='About Us']");
		xPathMapping.put("contactUs", "//a[text()='Contact Us']");
		xPathMapping.put("goToLoginPage", ".//*[@href='#/account/login']");
		xPathMapping.put("brandImageLink", ".//img[@class='brand_bLogin']");
		xPathMapping.put("privacyPolicy", "//a[contains(text(),'Privacy Policy')]");
		xPathMapping.put("termsOfUse", "//a[contains(text(),'Terms Of Use')]");
		xPathMapping.put("aboutUs", ".//a[@href='#/aboutus']");
		xPathMapping.put("contactUs", ".//a[@href='#/contactus']");
		xPathMapping.put("goToLoginPage", "//form[@id='forgotPasswordMobile']/div[3]/div[2]/a");
		xPathMapping.put("brandImageLink", ".//img[@class='brand']");

		//Manage Admin
		xPathMapping.put("manageAdmins", ".//a[@href='#/orgadmin/listuser']");
		xPathMapping.put("manageAdminBreadCrumb", ".//*[@id='breadcrumbox']/a[@href='#/orgadmin/listuser']");
		xPathMapping.put("addAdmin", "//input[contains(@ng-click,'/orgadmin/adduser')]");
		xPathMapping.put("lastOfItemsPerPage", ".//div[@class='items-per-page-section pull-right pos-r']/label[last()]");
		xPathMapping.put("addAnotherAdmin", ".//button[contains(@ng-click,'showConfirm')]");
		xPathMapping.put("saveToAddAnotherAdmin", ".//button[@ng-click='dialog.hide()']/span");

		//Edit Organization Profile
		xPathMapping.put("editOrganizationProfile", ".//a[@href='#/orgadmin/editprofile']");
		xPathMapping.put("addLatestNewsButton", "//span[contains(@class,'addtext blue')]");
		xPathMapping.put("dashboardBreadcrum", ".//*[@id='breadcrumbox']/ol/li[1]/a")

		//View Organization Profile
		xPathMapping.put("viewOrganizationProfile", "//a[contains(text(), 'View Organization')]");

		//Post Job
		xPathMapping.put("postJob", "//a[text()='Post Job']")
		xPathMapping.put("viewAllPostings", ".//a[@href='/jobs/postlist']");
		xPathMapping.put("postJobBreadcrumb",".//*[@id='breadcrumbox']/ol/li[2]/a");
		xPathMapping.put("fiftyResults",".//label[@for='item5']");

		//User Edit Profile
		xPathMapping.put("userEditProfile",".//div[@class='pos-a full-width full-height']/a");
		xPathMapping.put("basicInfoArrowDown","//*[contains(text(), 'Basic Information*')]");
		xPathMapping.put("basicInfoArrowUp","//*[contains(text(), 'Basic Information*')]");
		xPathMapping.put("viewPublicProfile",".//div[@class='user-profile-container']//button[@ng-click='viewUserProfile()']");
		xPathMapping.put("educationDetailsArrowDown","//*[contains(text(), 'Education Qualifications')]");
		xPathMapping.put("cancelLink",".//a[contains(text(), 'Cancel')]");
		xPathMapping.put("workExperienceArrowDown",".//*[@id='edit_profile_container']/div[1]/div[2]/div/div/div/div[1]/div/div/form/div[3]/div/button");
		xPathMapping.put("workExperienceAddMoreButton","//div[@class='work-exp-container']//button[@class='btn button-secondary']");
		xPathMapping.put("skillsArrowDown",".//button[@class='skills accordion']");
		xPathMapping.put("editProfilelink",".//*[@id='breadcrumbox']/ol/li[2]/a");
		xPathMapping.put("certificateArrowDown",".//div[@class='certificates-container']/button[@class='accordion']");
		xPathMapping.put("certificateAddMore",".//div[@class='certificates-container']//button[@ng-click='addDocument()']");
		
		//Pod details Page
		xPathMapping.put("buyThePod", "//div[@ng-if='buyCourse']/button");

		//License Details
		xPathMapping.put("licenseDetails", "//li[@ng-show='requestedLicenseDetail']/a")
		xPathMapping.put("pendingButton", "//a[contains(text(),'Pending')]")

		//Logout
		xPathMapping.put("logoutLink", "//a[contains(text(), 'Logout')]")

		//Followed Organizations List Page
		xPathMapping.put("followedOrganization","//a[text()='Followed Organization List']");
		xPathMapping.put("search", "//input[@value='SEARCH']");
		xPathMapping.put("primarySearch",".//button[@ng-click='search(searchValue)']");
		xPathMapping.put("firstOrganization",".//*[@id='main_page']/div[2]/div[2]/div/div/div/div[1]/div[3]/div[4]/div/div[2]/div[1]/h3/a");
		xPathMapping.put("bredcrumBack", ".//*[@id='breadcrumbox']/ol/li[2]/a")

		//Manage Users
		xPathMapping.put("manageUsers", "//a[text()='Manage Users']")
		xPathMapping.put("firstPod", "//dashboard-list-widget[@category-type='ongoingTrainings']//div[@ng-repeat='pod in podList'][1]//a")
		xPathMapping.put("addParticipant","//div[@ng-show='showAddParticipants']//button")
		xPathMapping.put("inviteLearner","//button[text()='Invite Learner']")
		xPathMapping.put("firstUser","//div[@class='clearfix content individual-job']/div/div[1]//h3/a")
		xPathMapping.put("back", ".//*[@id='breadcrumbox']/ol/li[2]/a")

		//Pod Customization
		xPathMapping.put("podCustomization","//a[text()='Pods Customization']")
		xPathMapping.put("podsCustomizationBack", ".//*[@id='breadcrumbox']/ol/li[1]")
		
		//Organization Dashboard
		xPathMapping.put("viewAllOngoing","//a[@ui-sref='kp.ongoingPodOrg']");
		xPathMapping.put("searchPodArrow", "//i[@class='fa fa-arrow-right blue']");
		xPathMapping.put("firstPod", ".//div[@class='ng-scope']/div[1]/div[1]/div/div/h3/a");
		
		//Participant List 
		xPathMapping.put("addParticipant", ".//*[@id='overview']//button[contains(text(), 'Add Participant')]");
		xPathMapping.put("inviteLearner", ".//*[@id='main_page']//button[@ng-click='inviteUserToCourse()']");
		xPathMapping.put("inviteLearnerBack", ".//button[contains(text(),'Back')]");
		xPathMapping.put("ongoingBreadcrumb",".//*[@id='breadcrumbox']/ol/li[2]/a")
		
		//Follow Organization
		xPathMapping.put("followOrg", ".//*[@id='overview']//button[contains(text(), 'Add Participant')]");
		
		//Admin Dashboard
		xPathMapping.put("viewAllOngoingTrainings",".//*[@id='dashboard']/div/div/div[4]/div[1]/div[1]/div/div/div/div[1]/div[2]/dashboard-list-widget/div/div/div/a");
	}

	// Main function to take care of click actions
	def static performClick (def browser, def clickKey) {

		println ("Performing Click for key: " + clickKey);
		if(clickKey ==null) return;

		def xpath = xPathMapping.get(clickKey);
		println ("xpath for click is " + xpath);

		if(browser.getElement(Browser.XPATH, xpath) == null || !browser.checkEnabled(xpath)){
			def message = clickKey + "  not found/NotEnabled"
			println "Message   :" +message
			def fileName = clickKey+"Issue"
			return KPCommonPage.returnFailureOutcome(browser, fileName, message)
		}else{
			waitBeforeClick(browser, xpath)
			browser.scrollToElement(browser.getElement(Browser.XPATH, xpath))
			browser.click xpath
			waitAfterClick(browser, xpath)
			return new SuccessOutcome();
		}
	}


	//Delay before click
	def static waitBeforeClick(def browser, def xpath){
		if(xpath.equals("//button[contains(text(), 'REGISTER')]")){
			browser.delay(4000)
			browser.scrollToElement2("//button[contains(text(), 'REGISTER')]")
		}else if(xpath.equals(".//*[@id='main_page']//button[@ng-click='inviteUserToCourse()']")){
		browser.delay(2000)
		browser.scrollToElement2(".//*[@id='main_page']//button[@ng-click='inviteUserToCourse()']")
		}
		if(xpath.equals(".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']")){
			browser.delay(3000)
			browser.scrollToElement2(".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']")
		}
		if(xpath.equals("//div[2]/div[1]/div/div/h3/a")){
			browser.delay(1000)
			KPCommonPage.podName = browser.gettext("//div[2]/div[1]/div/div/h3/a")
		}
		if(xpath.equals(".//a[@href='#/orgadmin/listuser']")){
			KPCommonPage.adminEmailId.clear()
		}
		if(xpath.equals(".//div[@ng-show='getStartedSection']/div/button[@ui-sref='kpna.login']")){
			browser.delay(2000)
		}
		browser.delay(2000)
	}

	//Delay after click
	def static waitAfterClick(def browser, def xpath){
		if(xpath.equals("//button[@class='full-width button-primary']") || xpath.equals(".//li[@class='menu-name-default mb30']/a[@href='#/pods/search']")
		|| xpath.equals("//button[@class='full-width button-primary']") || xpath.equals(".//a[@href='#/privacypolicy']") || xpath.equals(".//a[@href='#/termsofuse']")
		|| xpath.equals(".//a[@href='#/aboutus']") || xpath.equals(".//a[@href='#/contactus']")){
			browser.delay(2000)
		}
		browser.delay(3000)
	}

}
